package eu.peppol.security.callbacks;

import java.security.KeyStore;
import java.security.PrivateKey;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

import nl.kaninefatendreef.si.SIConfigurationException;
import nl.kaninefatendreef.si.SIConfigurationRuntimeException;
import nl.kaninefatendreef.si.server.config.SpringServerContext;
import nl.kaninefatendreef.si.ssl.KeyStoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.wss.impl.callback.KeyStoreCallback;
import com.sun.xml.wss.impl.callback.PrivateKeyCallback;

/**
 * 
 * @author Patrick van Amstel
 *
 */
public final class KeyStoreCallbackHandler implements CallbackHandler {

	
    public static final Logger _logger = LoggerFactory.getLogger(KeyStoreCallbackHandler.class);

    public KeyStoreCallbackHandler() {
    }

    public void handle(Callback[] callbacks) {

    	_logger.debug("Handling KeyStoreCallbackHandler found in the wsdl  \n" +
    			"<sc:KeyStore wspp:visibility=\"private\" \n " +
                "callbackHandler=\"eu.peppol.security.callbacks.KeyStoreCallbackHandler\"/>");

    	// Spring wirering inside sun wsit infrastructure.
    	// If someone knows a more elegant way..
    	KeyStoreManager peppolKeyStore = SpringServerContext.getPeppolKeyStore();
    	
        for (Callback callback : callbacks) {

            if (callback instanceof KeyStoreCallback) {
                KeyStore keystore = null;
				try {
					keystore = peppolKeyStore.getKeyStore();
				} catch (SIConfigurationException e) {
					_logger.error("Returning of the key store failed " + peppolKeyStore , e);
					throw new SIConfigurationRuntimeException(e);
				}
                ((KeyStoreCallback) callback).setKeystore(keystore);

            } else if (callback instanceof PrivateKeyCallback) {

                PrivateKey privateKey = null;
				try {
					privateKey = peppolKeyStore.getPrivateKey();
				} catch (SIConfigurationException e) {
					_logger.error("Returning of private key in the key store failed " + peppolKeyStore , e);
					throw new SIConfigurationRuntimeException(e);
				}
                ((PrivateKeyCallback) callback).setKey(privateKey);
            }
        }
    }
}
