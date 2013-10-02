package nl.kaninefatendreef.si.document;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3._2009._02.ws_tra.AccessPointService;
import org.w3._2009._02.ws_tra.Create;
import org.w3._2009._02.ws_tra.FaultMessage;
import org.w3._2009._02.ws_tra.Resource;

import com.sun.xml.ws.developer.JAXWSProperties;

import eu.peppol.start.model.PeppolMessageHeader;

@Component
public class SISoapProxy implements InitializingBean{

	Logger _logger = LoggerFactory.getLogger(SISoapProxy.class);
	
	private Boolean _soapLogging = false;
	private Integer _connectTimeout = 1000 * 60 * 120;
	private Integer _readTimeout = 1000 * 60 * 120;

	@Autowired
	BlackListService _blackListService = null;
	  
	public void send(URL endpointAddress, final PeppolMessageHeader messageHeader,
			Create soapBody) throws FaultMessage {
		
		if (_blackListService.existInApBlackList(endpointAddress)) {
			throw new RuntimeException(
					"Recipient AP is not avalaible at the moment: "
							+ endpointAddress.toExternalForm()
							+ " . ");
		}

		AccessPointService accesspointService = new AccessPointService(
				getWsdlUrl(), new QName("http://www.w3.org/2009/02/ws-tra",	"accessPointService"));        
        
        accesspointService.setHandlerResolver(new HandlerResolver() {
            @SuppressWarnings("rawtypes")
			public List<Handler> getHandlerChain(PortInfo portInfo) {
                List<Handler> handlerList = new ArrayList<Handler>();
                handlerList.add(new SOAPOutboundHandler(messageHeader));
                return handlerList;
            }
        });

        _logger.debug("Getting remote resource binding port");
        
        Resource port = null;
        try {
        	
        	port = accesspointService.getResourceBindingPort();
            Map<String, Object> requestContext = ((BindingProvider) port).getRequestContext();
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress.toExternalForm());
            requestContext.put(JAXWSProperties.CONNECT_TIMEOUT,  _connectTimeout);
            requestContext.put("com.sun.xml.ws.request.timeout", _readTimeout);

            _logger.info("Performing SOAP request to: " + endpointAddress.toExternalForm());
            
            port.create(soapBody);

            StringBuilder infoMessageBuilder = new StringBuilder();
            infoMessageBuilder.append("Sender:\t" + messageHeader.getSenderId().stringValue() + "\n");
            infoMessageBuilder.append("Recipient:\t" + messageHeader.getRecipientId().stringValue() + "\n");
            infoMessageBuilder.append("Destination:\t" + endpointAddress + "\n");
            infoMessageBuilder.append("Message " + messageHeader.getMessageId() + " has been successfully delivered \n");

            _logger.info(infoMessageBuilder.toString());
            
        } catch (RuntimeException rte) {
            if (_blackListService.isAdd2ApBlackListOnTimeout() && getRootCause(rte) instanceof XMLStreamException) {
                _logger.debug("Timeout exception occured. Will add to ApBlackList: " + endpointAddress);
                _blackListService.add2ApBlackList(endpointAddress, _blackListService.getApBlackListEntryKeepTime());
            }
            throw rte;
        } finally {
            // Creates memory leak if not performed
            if (port != null) {
    	        (new PortCloseThread(port)).start();
            }
        }
	}

	public void afterPropertiesSet() throws Exception {
		 HttpsURLConnection.setDefaultHostnameVerifier(new SISoapProxyHostnameVerifier());
         setDefaultSSLSocketFactory();
         System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "" + getSoapLogging());
	}
	
	public Boolean getSoapLogging() {
		return _soapLogging;
	}

	public void setSoapLogging(Boolean soapLogging) {
		_soapLogging = soapLogging;
	}
	
	public BlackListService getBlackListService() {
		return _blackListService;
	}

	public void setBlackListService(BlackListService blackListService) {
		_blackListService = blackListService;
	}


	// Helper method
	private URL getWsdlUrl() {
		String wsdlLocation = "META-INF/wsdl_v2.0-client.wsdl";
		URL wsdlUrl = getClass().getClassLoader().getResource(wsdlLocation);

		if (wsdlUrl == null) {
			throw new IllegalStateException("Unable to locate WSDL file "
					+ wsdlLocation);
		}
		return wsdlUrl;
	}

	private void setDefaultSSLSocketFactory() {
		try {
			TrustManager[] trustManagers = new TrustManager[] { new AccessPointX509TrustManager(
					null, null) };
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustManagers, new SecureRandom()); // Uses
																		// default
																		// KeyManager
																		// but
																		// our
																		// own
																		// TrustManager
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());

		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			throw new RuntimeException("Error setting socket factory", e);
		} 
	}
	
	// Logic from oxalis
	private Throwable getRootCause(Throwable throwable) {
		Throwable cause = throwable.getCause();
		if (cause != null) {
			throwable = cause;
			while ((throwable = throwable.getCause()) != null) {
				cause = throwable;
			}
		}
		return cause;
	}	

	
}
