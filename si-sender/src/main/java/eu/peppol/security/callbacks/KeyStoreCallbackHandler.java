package eu.peppol.security.callbacks;

import java.security.KeyStore;
import java.security.PrivateKey;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

import nl.kaninefatendreef.si.SIConfigurationException;
import nl.kaninefatendreef.si.config.SIApplicationContext;
import nl.kaninefatendreef.si.ssl.KeyStoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.wss.impl.callback.KeyStoreCallback;
import com.sun.xml.wss.impl.callback.PrivateKeyCallback;

public final class KeyStoreCallbackHandler implements CallbackHandler {

    public static final Logger logger = LoggerFactory.getLogger(KeyStoreCallbackHandler.class);

    public KeyStoreCallbackHandler() {
    }

    public void handle(Callback[] callbacks) {
    	
    	KeyStoreManager keyStoreManager = SIApplicationContext.getKeyStoreManager();

    	try{
	        for (Callback callback : callbacks) {
	            if (callback instanceof KeyStoreCallback) {
	                KeyStore keystore = keyStoreManager.getKeyStore();
	                ((KeyStoreCallback) callback).setKeystore(keystore);
	            } else if (callback instanceof PrivateKeyCallback) {
	                PrivateKey privateKey = keyStoreManager.getPrivateKey();
	                ((PrivateKeyCallback) callback).setKey(privateKey);
	            }
	        }
    	}catch (SIConfigurationException e){
    		logger.error("Fetching keystore failed in the callback",e);
    		throw new IllegalStateException(e);
    	}
    }
}
