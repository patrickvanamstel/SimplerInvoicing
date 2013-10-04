/*
 * Copyright (c) 2011,2012,2013 UNIT4 Agresso AS.
 *
 * This file is part of Oxalis.
 *
 * Oxalis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Oxalis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Oxalis.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.peppol.inbound.server;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.Set;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Action;
import javax.xml.ws.BindingType;
import javax.xml.ws.FaultAction;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.SOAPBinding;

import nl.kaninefatendreef.si.server.config.SpringServerContext;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.w3._2009._02.ws_tra.Create;
import org.w3._2009._02.ws_tra.CreateResponse;
import org.w3._2009._02.ws_tra.Delete;
import org.w3._2009._02.ws_tra.DeleteResponse;
import org.w3._2009._02.ws_tra.FaultMessage;
import org.w3._2009._02.ws_tra.Get;
import org.w3._2009._02.ws_tra.GetResponse;
import org.w3._2009._02.ws_tra.Put;
import org.w3._2009._02.ws_tra.PutResponse;
import org.w3._2009._02.ws_tra.StartException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;
import com.sun.xml.ws.rx.mc.api.MakeConnectionSupported;
import com.sun.xml.wss.SubjectAccessor;
import com.sun.xml.wss.XWSSecurityException;

import eu.peppol.start.model.PeppolMessageHeader;



@WebService	(
		serviceName = "accessPointService", 
		portName = "ResourceBindingPort", 
		endpointInterface = "org.w3._2009._02.ws_tra.Resource", 
		targetNamespace = "http://www.w3.org/2009/02/ws-tra", 
		wsdlLocation = "WEB-INF/wsdl/accessPointService/wsdl_v2.0.wsdl")
@BindingType(value = SOAPBinding.SOAP11HTTP_BINDING)
@HandlerChain(file = "handler-chain.xml")
@Addressing
@MakeConnectionSupported // https://metro.java.net/guide/ch11.html
public class accessPointService {

    public static final Logger _logger = LoggerFactory.getLogger(accessPointService.class);

	public accessPointService() {
		_logger.info("Attempting to create the AccessPointService.");
    }

    @javax.annotation.Resource
    private WebServiceContext _webServiceContext;

    @Action(input = "http://www.w3.org/2009/02/ws-tra/Create",
            output = "http://www.w3.org/2009/02/ws-tra/CreateResponse",
            fault = {@FaultAction(className = org.w3._2009._02.ws_tra.FaultMessage.class,
                    value = "http://busdox.org/2010/02/channel/fault")})
    public CreateResponse create(Create body) throws 
    	FaultMessage, 
    	CertificateException, 
    	NoSuchAlgorithmException, 
    	NoSuchProviderException, 
    	IOException, 
    	KeyStoreException {

        try {

      	
            // Retrieves the PEPPOL message header
            PeppolMessageHeader messageHeader = getPeppolMessageHeader();

            // For testing purposes, allows client to force a fault
            if (messageHeader.getChannelId().getChannelId().contains("FAULT")) {
                throw new IllegalStateException("Keyword FAULT seen in channel");
            }

            _logger.info("Received message "  + messageHeader);

            // Thread logging enabled
            MDC.put("msgId", messageHeader.getMessageId().getMessageId());
            MDC.put("senderId", messageHeader.getSenderId().stringValue());
            MDC.put("channelId", messageHeader.getChannelId().getChannelId());
        
            // This does not seem to give any meaning, unless the spec says so, this code is obsolete.
            // verifyThatThisDocumentIsForUs(messageHeader);

            Document document = ((Element) body.getAny().get(0)).getOwnerDocument();
            // Invokes the message persistence

            persistMessage(messageHeader, document);

            CreateResponse createResponse = new CreateResponse();
            
            return createResponse;

        } catch (Throwable e) {

        	_logger.error("Problem while handling inbound document: " + e.getMessage(), e);

            StartException faultInfo = new StartException();
            faultInfo.setAction("http://busdox.org/2010/02/channel/fault");
            faultInfo.setFaultcode("s:Sender");
            faultInfo.setFaultstring("ServerError");
            faultInfo.setDetails("Unexpected error in document handling: " + e.getMessage());

            throw new FaultMessage("ERROR:", faultInfo, e);
        } finally {
            // Clears the SLF4J Message Diagnostic Context
            MDC.clear();
        }
    }

    private Principal fetchAccessPointPrincipal(WebServiceContext webServiceContext) {

        // Retrieves the Principal from the request
        Subject subj = null;
        try {
            subj = getSubjectFromSoapRequest(webServiceContext);
        } catch (XWSSecurityException e) {
        	_logger.warn("Unable to retrieve security Subject from SOAP request" + e.getMessage(), e);
            return new Principal() {

                @Override
                public String getName() {
                    return "AP Subject retrieval failed";
                }
            };
        }

        Set<Principal> principals = subj.getPrincipals();

        // A Subject may have several Principal objects associated, fetch the first
        Iterator<Principal> principalIterator = principals.iterator();
        if (principalIterator.hasNext()) {
            Principal principal = principalIterator.next();
            return principal;
        }

        return new Principal() {
            @Override
            public String getName() {
                return "unknown AP principal";
            }
        };
    }

    private Subject getSubjectFromSoapRequest(WebServiceContext webServiceContext) throws XWSSecurityException {
        return SubjectAccessor.getRequesterSubject(webServiceContext);
    }


    /**
     * Extracts metadata from the SOAP Header, i.e. the routing information and invokes a pluggable
     * message persistence in order to allow for storage of the meta data and the message itself.
     *
     * @param document                   the XML document.
     *
     */
    void persistMessage(PeppolMessageHeader peppolMessageHeader, Document document) {

    	long startPersistMeasurement = System.currentTimeMillis();
        
    	// Invokes whatever has been configured in META-INF/services/.....
        try {
            
            SimplerInvoiceDocument simplerInvoicingMessage = SpringServerContext.getActiveDocumentRepository().createSimplerInvoiceDocument();
            {
	            // Fetching all information from the message
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	
	            Writer writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF-8"));
	
	            StreamResult result = new StreamResult(writer);
	
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer;
	            transformer = tf.newTransformer();
	            transformer.transform(new DOMSource(document), result);
	            byteArrayOutputStream.close();

	            SimplerInvoiceDocumentContent content = SpringServerContext.getActiveDocumentRepository().createSimplerInvoiceDocumentContent();
	            content.setDocument(byteArrayOutputStream.toByteArray());
	            simplerInvoicingMessage.setContent(content);
            
            }
            
            String messageFileName = peppolMessageHeader.getMessageId().getMessageId().replace(":", "_") + ".xml";

            simplerInvoicingMessage.setFileName(messageFileName);
            simplerInvoicingMessage.setMessageId(peppolMessageHeader.getMessageId().getMessageId());
            simplerInvoicingMessage.setChannelId(peppolMessageHeader.getChannelId().getChannelId());
            simplerInvoicingMessage.setSenderId(peppolMessageHeader.getSenderId().stringValue());
            simplerInvoicingMessage.setRecipientId(peppolMessageHeader.getRecipientId().stringValue());
            simplerInvoicingMessage.setAccessPointPrinciple(peppolMessageHeader.getRemoteAccessPointPrincipal().getName());
            simplerInvoicingMessage.setDocumentId(peppolMessageHeader.getDocumentTypeIdentifier().stringValue());
            simplerInvoicingMessage.setProcessId(peppolMessageHeader.getPeppolProcessTypeId().stringValue());
            simplerInvoicingMessage.setRemoteHost(peppolMessageHeader.getRemoteHost());
            simplerInvoicingMessage.setProcessed(false);
            simplerInvoicingMessage.setCreateTimeInMs(System.currentTimeMillis());
            simplerInvoicingMessage.setUpdateTimeInMs(System.currentTimeMillis());
            
            SimplerInvoiceDocument savedSimplerInvoiceDocument =  SpringServerContext.getActiveDocumentRepository().save(simplerInvoicingMessage);
            SimplerInvoiceDocument message = SpringServerContext.getActiveDocumentRepository().findOne(savedSimplerInvoiceDocument.getId());
            
            Long size = null;
            if (message.getContent() != null){
            	size = new Long(message.getContent().getDocument().length);
            }
            
            _logger.info("Created document with id " + message.getId() + " content with id " + size);
        } catch (Throwable e) {
        	e.printStackTrace();
            _logger.error("Unable to persist: " + e.getMessage(), e);
            throw new IllegalStateException("Unable to persist message " + peppolMessageHeader + "; " + e.getMessage(), e);
        }
        
        long endPersistMeasurement = System.currentTimeMillis();
        
        _logger.debug("Persisting took " + (endPersistMeasurement - startPersistMeasurement) );
        
    }

    
    private PeppolMessageHeader getPeppolMessageHeader() {
        MessageContext messageContext = _webServiceContext.getMessageContext();
        HeaderList headerList = (HeaderList) messageContext.get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
        
        PeppolMessageHeader peppolMessageHeader = new PeppolMessageHeader(headerList);

        // Retrieves the IP address or hostname of the remote host, which is useful for auditing.
        HttpServletRequest request = (HttpServletRequest) _webServiceContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
        peppolMessageHeader.setRemoteHost(request.getRemoteHost());

        // The Principal of the remote host is just a difficult word for their X.509 certificate Distinguished Name (DN)
        Principal remoteAccessPointPrincipal = fetchAccessPointPrincipal(_webServiceContext);
        peppolMessageHeader.setRemoteAccessPointPrincipal(remoteAccessPointPrincipal);

        return peppolMessageHeader;
    }


//    private void verifyThatThisDocumentIsForUs(PeppolMessageHeader messageHeader) {
//
//        try {
//            X509Certificate recipientCertificate = new SmpLookupManager().getEndpointCertificate(
//                    messageHeader.getRecipientId(),
//                    messageHeader.getDocumentTypeIdentifier());
//
//            if (peppolKeyStore.isOurCertificate(recipientCertificate)) {
//                _logger.info("SMP lookup OK");
//            } else {
//            	_logger.info("SMP lookup indicates that document was sent to the wrong access point");
//                throw new FaultMessage("This message was sent to the wrong Access Point", new StartException());
//            }
//        } catch (Exception e) {
//        	_logger.info("SMP lookup fails, we assume the message is for us; reason " + e.getMessage());
//        }
//    }

    public GetResponse get(Get body) {
        throw new UnsupportedOperationException();
    }

    public PutResponse put(Put body) {
        throw new UnsupportedOperationException();
    }

    public DeleteResponse delete(Delete body) {
        throw new UnsupportedOperationException();
    }




}
