package eu.peppol.security.callbacks;

import java.security.KeyStore;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

import nl.kaninefatendreef.si.SIConfigurationException;
import nl.kaninefatendreef.si.config.SIApplicationContext;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.wss.impl.callback.KeyStoreCallback;

public final class TrustStoreCallbackHandler implements CallbackHandler {

    public static final Logger log = LoggerFactory.getLogger(TrustStoreCallbackHandler.class);

    public TrustStoreCallbackHandler() {
    }

    public void handle(Callback[] callbacks) {
    	
    	TrustStoreManager trustStoreManagerSI = SIApplicationContext.getTrustStoreManager();
    	

        for (Callback callback : callbacks) {

            if (callback instanceof KeyStoreCallback) {
				try {
					KeyStore truststore = trustStoreManagerSI.getTruststore();
					((KeyStoreCallback) callback).setKeystore(truststore);
				} catch (SIConfigurationException e) {
					throw new IllegalStateException(e);
				}
                
            }
        }
    }
}
