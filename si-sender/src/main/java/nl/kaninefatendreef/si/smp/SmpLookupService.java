package nl.kaninefatendreef.si.smp;

import java.io.ByteArrayInputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.busdox.smp.EndpointType;
import org.busdox.smp.ProcessIdentifierType;
import org.busdox.smp.SignedServiceMetadataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import eu.peppol.start.model.ParticipantId;
import eu.peppol.start.model.PeppolDocumentTypeId;
import eu.peppol.start.model.PeppolProcessTypeId;


@Component
public class SmpLookupService implements InitializingBean{

	private static Logger _logger = LoggerFactory.getLogger(SmpLookupService.class);
	
	@Autowired
	private DNSLookupService _dnsLookupService ;

	@Autowired 
	private Environment _environment ; 

	
	Proxy _proxy = null;
	
	private String _smlPeppolCentralDNS = "sml.peppolcentral.org";
	private String _ipAddressSmlCentral = null;
	
	
    /**
     * @param participant
     * @param documentTypeIdentifier
     * @return The endpoint address for the participant and DocumentId
     * @throws SmpSignedServiceMetaDataException 
     * @throws SmpParticipantNotFoundException 
     * @throws RuntimeException If the end point address cannot be resolved for the participant. This is caused by a {@link java.net.UnknownHostException}
     */
    public URL getEndpointAddress(ParticipantId participant, PeppolDocumentTypeId documentTypeIdentifier) throws SmpParticipantNotFoundException, SmpSignedServiceMetaDataException {

        EndpointType endpointType = getEndpointType(participant, documentTypeIdentifier);
        
        String address = endpointType.getEndpointReference().getAddress().getValue();
        _logger.info("Found endpoint address for " + participant.stringValue() + " from SMP: " + address);

        try {
            return new URL(address);
        } catch (Exception e) {
            throw new RuntimeException("SMP returned invalid URL", e);
        }
    }

    /**
     * Retrieves the end point certificate for the given combination of receiving participant id and document type identifer.
     *
     * @param participant            receiving participant
     * @param documentTypeIdentifier document type to be sent
     * @return The X509Certificate for the given ParticipantId and DocumentId
     * @throws SmpSignedServiceMetaDataException 
     * @throws SmpParticipantNotFoundException 
     * @throws RuntimeException If the end point address cannot be resolved for the participant. This is caused by a {@link java.net.UnknownHostException}
     */
    public X509Certificate getEndpointCertificate(ParticipantId participant, PeppolDocumentTypeId documentTypeIdentifier) throws SmpParticipantNotFoundException, SmpSignedServiceMetaDataException {

        try {
            String body = getEndpointType(participant, documentTypeIdentifier).getCertificate();
            String endpointCertificate = "-----BEGIN CERTIFICATE-----\n" + body + "\n-----END CERTIFICATE-----";
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(endpointCertificate.getBytes()));

        } catch (CertificateException e) {
            throw new RuntimeException("Failed to get certificate from SMP for " + ParticipantId.getScheme() + ":" + participant.stringValue());
        }
    }

    private EndpointType getEndpointType(ParticipantId participant, PeppolDocumentTypeId documentTypeIdentifier) throws SmpParticipantNotFoundException, SmpSignedServiceMetaDataException {

         SignedServiceMetadataType serviceMetadata = getServiceMetaData(participant, documentTypeIdentifier);

            return serviceMetadata
                    .getServiceMetadata()
                    .getServiceInformation()
                    .getProcessList()
                    .getProcess()
                    .get(0)
                    .getServiceEndpointList()
                    .getEndpoint()
                    .get(0);

    }


    private SignedServiceMetadataType getServiceMetaData(ParticipantId participant, PeppolDocumentTypeId documentTypeIdentifier) throws SmpSignedServiceMetaDataException, SmpParticipantNotFoundException {

        URL smpUrl = null;
        try {
            smpUrl = getSmpUrl(participant, documentTypeIdentifier);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to construct URL for " + participant + ", documentType" + documentTypeIdentifier + "; " + e.getMessage(), e);
        }

        InputSource smpContents = null;
        try {
        	_logger.debug("Constructed SMP url: " + smpUrl.toExternalForm());
            smpContents = SmpLookupServiceDelegate.getUrlContent(smpUrl , _proxy);
            
            
        }catch (SmpParticipantNotFoundException se){
        	se.setParticipantId(participant);
        	throw (se);
        }catch (Exception e) {
            throw new SmpSignedServiceMetaDataException(participant, documentTypeIdentifier, smpUrl, e);
        }

        try {

            // Parses the XML response from the SMP
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(smpContents);

            // Validates the signature
            SmpResponseValidator smpResponseValidator = new SmpResponseValidator(document);
            if (!smpResponseValidator.isSmpSignatureValid()) {
                throw new IllegalStateException("SMP response contained invalid signature");
            }

/**
 * Uncomment code below if PEPPOL decides we need to follow the chain of trust for the SMP certificate.
 */
            // Validates the certificate supplied with the signature
/*
            if (!OxalisCertificateValidator.getInstance().validate(smpResponseValidator.getCertificate())) {
                throw new IllegalStateException("SMP Certificate not valid for " + smpUrl);
            }
*/
            Unmarshaller unmarshaller = JAXBContext.newInstance(SignedServiceMetadataType.class).createUnmarshaller(); 
            return unmarshaller.unmarshal(document, SignedServiceMetadataType.class).getValue();
        } catch (Exception e) {
            throw new SmpSignedServiceMetaDataException(participant, documentTypeIdentifier, smpUrl, e);
        }
    }


    private URL getSmpUrl(ParticipantId participantId, PeppolDocumentTypeId documentTypeIdentifier) throws Exception {

        String scheme = ParticipantId.getScheme();
        String value = participantId.stringValue();
        String hostname = "B-" + SmpLookupServiceDelegate.calculateMD5(value.toLowerCase()) + "." + scheme + "." + _smlPeppolCentralDNS;
        String encodedParticipant = URLEncoder.encode(scheme + "::" + value, "UTF-8");
        String encodedDocumentId = URLEncoder.encode(eu.peppol.start.model.PeppolDocumentTypeIdAcronym.getScheme() + "::" + documentTypeIdentifier.stringValue(), "UTF-8");

        if (_ipAddressSmlCentral != null){
        	_logger.debug("Using ip address to fetch endpoints url.");
        	hostname = _ipAddressSmlCentral;
        }
        
        return new URL("http://" + hostname + "/" + encodedParticipant + "/services/" + encodedDocumentId);
    }

    private URL getServiceGroupURL(ParticipantId participantId) throws SmpLookupException {
        String scheme = ParticipantId.getScheme();
        String value = participantId.stringValue();

        try {
            String hostname = "B-" + SmpLookupServiceDelegate.calculateMD5(value.toLowerCase()) + "." + scheme + "." + _smlPeppolCentralDNS;

            // Example: iso6523-actorid-upis%3A%3A9908:810017902
            String encodedParticipant = URLEncoder.encode(scheme + "::", "UTF-8") + value;

            return new URL("http://" + hostname + "/" + encodedParticipant);
        } catch (Exception e) {
            throw new SmpLookupException(participantId, e);
        }
    }

    /**
     * Retrieves a group of URLs representing the documents accepted by the given participant id
     *
     * @param participantId participant id to look up
     * @return list of URLs representing each document type accepted
     * @throws SmpParticipantNotFoundException 
     */
    public List<PeppolDocumentTypeId> getServiceGroups(ParticipantId participantId) throws SmpLookupException, ParticipantNotRegisteredException, SmpParticipantNotFoundException {

        // Creates the URL for the service meta data for the supplied participant
        URL serviceGroupURL = getServiceGroupURL(participantId);

        if (!isParticipantRegistered(serviceGroupURL)) {
            throw new ParticipantNotRegisteredException(participantId);
        }

        InputSource smpContents = SmpLookupServiceDelegate.getUrlContent(serviceGroupURL , _proxy);

        // Parses the XML response from the SMP
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        Document document;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(smpContents);

            // Locates the namespace URI of the root element
            String nameSpaceURI = document.getDocumentElement().getNamespaceURI();
            NodeList nodes = document.getElementsByTagNameNS(nameSpaceURI, "ServiceMetadataReference");

            List<PeppolDocumentTypeId> result = new ArrayList<PeppolDocumentTypeId>();

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                String hrefAsString = element.getAttribute("href");
                // Gets rid of all the funny %3A's...
                hrefAsString = URLDecoder.decode(hrefAsString, "UTF-8");
                // Grabs the entire text string after "busdox-docid-qns::"
                String docTypeAsString = hrefAsString.substring(hrefAsString.indexOf("busdox-docid-qns::") + "busdox-docid-qns::".length());

                // Parses and creates the document type id
                PeppolDocumentTypeId peppolDocumentTypeId = PeppolDocumentTypeId.valueOf(docTypeAsString);

                result.add(peppolDocumentTypeId);
            }

            return result;

        } catch (Exception e) {
            throw new SmpLookupException(participantId, serviceGroupURL , e);
        }
    }

    /**
     * Each participant has its own sub-domain in peppolcentral, therefore if one does not
     * exist it means participant is not registered.
     */
    private boolean isParticipantRegistered(URL serviceGroupURL) {
        return _dnsLookupService.domainExists(serviceGroupURL);
    }


    public PeppolProcessTypeId getProcessIdentifierForDocumentType(ParticipantId participantId, PeppolDocumentTypeId documentTypeIdentifier) throws SmpSignedServiceMetaDataException, SmpParticipantNotFoundException {
        SignedServiceMetadataType serviceMetaData = getServiceMetaData(participantId, documentTypeIdentifier);
        // SOAP generated type...
        ProcessIdentifierType processIdentifier = serviceMetaData.getServiceMetadata().getServiceInformation().getProcessList().getProcess().get(0).getProcessIdentifier();
        // Converts SOAP generated type into something nicer
        return PeppolProcessTypeId.valueOf(processIdentifier.getValue());
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		
		if (_environment.containsProperty("nl.kaninefatendreef.si.peppol.dns")){
			_smlPeppolCentralDNS = _environment.getProperty("nl.kaninefatendreef.si.peppol.dns.name");
		}
		if (_environment.containsProperty("nl.kaninefatendreef.si.peppol.dns.ip")){
			_ipAddressSmlCentral = _environment.getProperty("nl.kaninefatendreef.si.peppol.dns.ip");
		}
		if (_environment.containsProperty("nl.kaninefatendreef.si.peppol.dns.proxy.name"))
		{
			//_proxy = new Proxy , _environment.getProperty("nl.kaninefaten.si.peppol.dns.proxy.port"));			
			SocketAddress addr = new InetSocketAddress(_environment.getProperty("nl.kaninefatendreef.si.peppol.dns.proxy.name"),3128);
			_proxy = new Proxy(Proxy.Type.HTTP, addr);
		}
		
		

		
		
	}
}
