package eu.peppol.security.callbacks;

import com.sun.xml.wss.impl.callback.KeyStoreCallback;

import nl.kaninefatendreef.si.SIConfigurationException;
import nl.kaninefatendreef.si.server.config.SpringServerContext;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

import java.security.KeyStore;

public final class TrustStoreCallbackHandler implements CallbackHandler {

	
    public static final Logger _logger = LoggerFactory.getLogger(TrustStoreCallbackHandler.class);

    public TrustStoreCallbackHandler() {}

    public void handle(Callback[] callbacks) {

    	_logger.debug("Handling TrustStoreCallbackHandler found in the wsdl  \n" +
    			"<TrustStore wspp:visibility=\"private\" \n " +
                "callbackHandler=\"eu.peppol.security.callbacks.TrustStoreCallbackHandler\"/>");
    	
    	// Spring wire into sun ws wire.
    	TrustStoreManager peppolTrustStore = SpringServerContext.getPeppolTrustStore();
    	
        for (Callback callback : callbacks) {

            if (callback instanceof KeyStoreCallback) {
            	_logger.debug("Returning truststore");
                KeyStore truststore = null;
				try {
					truststore = peppolTrustStore.getTruststore();
				} catch (SIConfigurationException e) {
					_logger.error("Could not find the truststore " + truststore , e);
				}
                ((KeyStoreCallback) callback).setKeystore(truststore);
            }
        }
    }
}
