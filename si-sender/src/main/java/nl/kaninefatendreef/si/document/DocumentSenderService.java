package nl.kaninefatendreef.si.document;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3._2009._02.ws_tra.Create;
import org.w3._2009._02.ws_tra.FaultMessage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eu.peppol.start.model.MessageId;
import eu.peppol.start.model.PeppolDocumentTypeId;
import eu.peppol.start.model.PeppolMessageHeader;
import eu.peppol.start.model.PeppolProcessTypeId;
import eu.peppol.start.model.ParticipantId;

@Component
public class DocumentSenderService implements InitializingBean{

	String documentTypeIdentifier = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2::Invoice##urn:www.cenbii.eu:transaction:biicoretrdm010:ver1.0:#urn:www.peppol.eu:bis:peppol4a:ver1.0::2.0";
	String peppolProcessTypeId = "urn:www.cenbii.eu:profile:bii04:ver1.0";
	
	@Autowired
	private SIParticipant _endPointSiSender = null;

	@Autowired(required=false)
	private SIProxy _siProxy = null;

	@Autowired
	private SISoapProxy _siSoapProxy = null;
	
	private Boolean _validateUBLDocument = true;


	public SIDocumentSenderResult send(InputStream inputStream , SIParticipant endPointSiReceiver )
		throws SIDocumentSenderException{
		
		SIDocumentSenderResult siDocumentSenderResult = new SIDocumentSenderResult();
		
		// Put into memory
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);

			// Also validate agains the xsd and or schematron
			
			if (_validateUBLDocument){
				//TODO add validator
			}
			
		} catch (ParserConfigurationException | SAXException | IOException   e) {
			throw new SIDocumentSenderException(e);
		}
		
        ParticipantId senderId = getEndPointSiSender().getParticipantId();
        ParticipantId recipientId = endPointSiReceiver.getParticipantId();
        
        Create soapBody = new Create();
        soapBody.getAny().add(document.getDocumentElement());

        PeppolMessageHeader messageHeader= new PeppolMessageHeader();
        messageHeader.setChannelId(endPointSiReceiver.getChannelId());

        MessageId messageId = new MessageId("uuid:" + UUID.randomUUID().toString());
        
        messageHeader.setMessageId(messageId);
        
        //TODO Deze moet ergens komen
        messageHeader.setDocumentTypeIdentifier(PeppolDocumentTypeId.valueOf(documentTypeIdentifier));

        messageHeader.setPeppolProcessTypeId(new PeppolProcessTypeId(peppolProcessTypeId));
        messageHeader.setSenderId(senderId);
        messageHeader.setRecipientId(recipientId);

        try {
			getSiSoapProxy().send(endPointSiReceiver.getDestination(), messageHeader, soapBody);
		} catch (FaultMessage e) {
			throw new SIDocumentSenderException(e);
		}
        // Opzoeken wat er hier precies gebeurd
        		
        siDocumentSenderResult.setMessageId(messageId);
        siDocumentSenderResult.setSenderId(messageHeader.getSenderId());
        siDocumentSenderResult.setRecipientId(messageHeader.getRecipientId());
        siDocumentSenderResult.setPeppolProcessTypeId(messageHeader.getPeppolProcessTypeId());
        siDocumentSenderResult.setChannelId(messageHeader.getChannelId());
		
		return siDocumentSenderResult;
	}




	@Override
	public void afterPropertiesSet() throws Exception {

		if (_siProxy != null){
			_siProxy.configure();
		}

        System.setProperty("com.sun.xml.ws.client.ContentNegotiation", "none");
        System.setProperty("com.sun.xml.wss.debug", "FaultDetail");

		
	}
	

	// Getters and Setters
	
	public SIProxy getSiProxy() {
		return _siProxy;
	}




	public void setSiProxy(SIProxy siProxy) {
		this._siProxy = siProxy;
	}
	
	public Boolean getValidateUBLDocument() {
		return _validateUBLDocument;
	}




	public void setValidateUBLDocument(Boolean validateUBLDocument) {
		this._validateUBLDocument = validateUBLDocument;
	}
	

	public SIParticipant getEndPointSiSender() {
		return _endPointSiSender;
	}




	public void setEndPointSiSender(SIParticipant endPointSiSender) {
		this._endPointSiSender = endPointSiSender;
	}

	public SISoapProxy getSiSoapProxy() {
		return _siSoapProxy;
	}




	public void setSiSoapProxy(SISoapProxy siSoapProxy) {
		this._siSoapProxy = siSoapProxy;
	}
	
}