package nl.kaninefatendreef.si.document;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
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
import org.springframework.stereotype.Component;
import org.w3._2009._02.ws_tra.AccessPointService;
import org.w3._2009._02.ws_tra.Create;
import org.w3._2009._02.ws_tra.FaultMessage;
import org.w3._2009._02.ws_tra.Resource;

import com.sun.xml.ws.developer.JAXWSProperties;

import eu.peppol.start.model.PeppolMessageHeader;


@Component
public class SISoapProxy implements InitializingBean{

	Logger logger = LoggerFactory.getLogger(SISoapProxy.class);
	
	Boolean _soapLogging = false;
	
	//soapDispatcher.enableSoapLogging(soapLogging);
	
	private Integer connectTimeout = 1000 * 60 * 120;
	private Integer readTimeout = 1000 * 60 * 120;

	private boolean add2ApBlackListOnTimeout = true;
	private Integer apBlackListEntryKeepTime = 1000 * 60 * 120;

	private Map<URL, Long> apBlackList = Collections.synchronizedMap(new LinkedHashMap<URL, Long>());

	  private URL getWsdlUrl() {
	        String wsdlLocation = "META-INF/wsdl_v2.0-client.wsdl";
	        URL wsdlUrl = getClass().getClassLoader().getResource(wsdlLocation);

	        if (wsdlUrl == null) {
	            throw new IllegalStateException("Unable to locate WSDL file " + wsdlLocation);
	        }
	        return wsdlUrl;
	    }

	public void send(URL endpointAddress, final PeppolMessageHeader messageHeader,
			Create soapBody) throws FaultMessage {
		
        if (existInApBlackList(endpointAddress)) {
            throw new RuntimeException("Recipient AP is not avalaible at the moment: "
                    + endpointAddress.toExternalForm()
                    + " . Please contact system administrator.");
        }

      
        AccessPointService accesspointService = new AccessPointService(
                
        		
        		getWsdlUrl(),
                new QName("http://www.w3.org/2009/02/ws-tra", "accessPointService")
                );

        

        
        
        
        
        accesspointService.setHandlerResolver(new HandlerResolver() {

            @SuppressWarnings("rawtypes")
			public List<Handler> getHandlerChain(PortInfo portInfo) {
                List<Handler> handlerList = new ArrayList<Handler>();
                handlerList.add(new SOAPOutboundHandler(messageHeader));
                return handlerList;
            }
        });

        logger.debug("Getting remote resource binding port");
        Resource port = null;
        try {

        	//PvA
/*        	WebServiceFeature ws = new MakeConnectionSupportedFeature();
            port = accesspointService.getResourceBindingPort(ws);
*/            

        	
        	port = accesspointService.getResourceBindingPort();
            Map<String, Object> requestContext = ((BindingProvider) port).getRequestContext();
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress.toExternalForm());
            requestContext.put(JAXWSProperties.CONNECT_TIMEOUT,  connectTimeout);
            requestContext.put("com.sun.xml.ws.request.timeout", readTimeout);


            // Allows us to verify the name of the remote host.
//            requestContext.put(JAXWSProperties.HOSTNAME_VERIFIER, createHostnameVerifier());

            logger.info("Performing SOAP request to: " + endpointAddress.toExternalForm());
            port.create(soapBody);

            logger.info("Sender:\t" + messageHeader.getSenderId().stringValue());
            logger.info("Recipient:\t" + messageHeader.getRecipientId().stringValue());
            logger.info("Destination:\t" + endpointAddress);
            logger.info("Message " + messageHeader.getMessageId() + " has been successfully delivered");

        } catch (RuntimeException rte) {

            if (isAdd2ApBlackListOnTimeout() && getRootCause(rte) instanceof XMLStreamException) {
                logger.debug("Timeout exception occured. Will add to ApBlackList: " + endpointAddress);
                add2ApBlackList(endpointAddress, getApBlackListEntryKeepTime());
            }
            throw rte;
        } finally {

            // Creates memory leak if not performed
        	
            if (port != null) {
    	        (new PortCloseThread(port)).start();
            }
        
        }

		
	}
	
	
	 public static Throwable getRootCause(Throwable throwable) {
	        Throwable cause = throwable.getCause();
	        if (cause != null) {
	            throwable = cause;
	            while ((throwable = throwable.getCause()) != null) {
	                cause = throwable;
	            }
	        }
	        return cause;
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

		} catch (Exception e) {
			throw new RuntimeException("Error setting socket factory", e);
		}
	}
	
	class OxalisHostnameVerifier implements HostnameVerifier {

	    public boolean verify(final String hostname, final SSLSession session) {
	        try {
	            //Principal peerPrincipal = session.getPeerPrincipal();
	        	session.getPeerPrincipal();
	        } catch (SSLPeerUnverifiedException e) {
	            //Log.debug("Unable to retrieve SSL peer principal " + e);
	        	return false;
	        }
	        return true;
	    }

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		 HttpsURLConnection.setDefaultHostnameVerifier(new OxalisHostnameVerifier());
         setDefaultSSLSocketFactory();
         System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "" + getSoapLogging());
	}
	
	public Boolean getSoapLogging() {
		return _soapLogging;
	}


	public void setSoapLogging(Boolean soapLogging) {
		this._soapLogging = soapLogging;
	}
	
	
  public Map<String, Date> getApBlackListAsString() {
        Map<String, Date> map = new LinkedHashMap<String, Date>();
        synchronized (apBlackList) {
            for (Iterator<Entry<URL, Long>> iter = apBlackList.entrySet().iterator(); iter.hasNext(); ) {
                Entry<URL, Long> entry = iter.next();
                Date value = null;
                if (entry.getValue() != null && entry.getValue() > 0) {
                    value = new Date(entry.getValue());
                }
                map.put(entry.getKey().toExternalForm(), value);
            }
        }

        return map;
    }


  public void setApBlackListFromString(Set<String> apBlackList)
	            throws Exception {
	        synchronized (apBlackList) {
	            clearApBlackList();
	            for (Iterator<String> iter = apBlackList.iterator(); iter.hasNext(); ) {
	                String destination = iter.next();
	                if (destination != null && destination.trim().length() > 0) {
	                    add2ApBlackList(new URL(destination), 0);
	                }
	            }
	        }
	    }

	    public boolean isAdd2ApBlackListOnTimeout() {
	        return add2ApBlackListOnTimeout;
	    }

	    public  void setAdd2ApBlackListOnTimeout(boolean add2ApBlackListOnTimeout) {
	        this.add2ApBlackListOnTimeout = add2ApBlackListOnTimeout;
	    }

	    public Integer getApBlackListEntryKeepTime() {
	        return apBlackListEntryKeepTime;
	    }

	    public void setApBlackListEntryKeepTime(Integer apBlackListEntryKeepTime) {
	        this.apBlackListEntryKeepTime = apBlackListEntryKeepTime;
	    }

	    public  void add2ApBlackList(URL destination, Integer apBlackListEntryKeepTime) {
	        if (apBlackListEntryKeepTime == null || apBlackListEntryKeepTime == 0) {
	            getApBlackList().put(destination, new Long(0));
	        } else {
	            getApBlackList().put(destination, System.currentTimeMillis() + apBlackListEntryKeepTime);
	        }
	    }

	    public void removeFromApBlackList(URL destination) {
	        getApBlackList().remove(destination);
	    }

	    public boolean existInApBlackList(URL destination) {
	        if (!getApBlackList().containsKey(destination)) {
	            return false;
	        }
	        Long keepTime = getApBlackList().get(destination);
	        if (keepTime == null || keepTime == 0) {
	            return true;
	        } else if (System.currentTimeMillis() < keepTime) {
	            return true;
	        }

	        getApBlackList().remove(destination);
	        return false;
	    }

	    public void clearApBlackList() {
	        getApBlackList().clear();
	    }

	    public Map<URL, Long> getApBlackList() {
	        return apBlackList;
	    }
	
	
}
