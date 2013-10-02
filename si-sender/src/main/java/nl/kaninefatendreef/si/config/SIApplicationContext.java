package nl.kaninefatendreef.si.config;

import nl.kaninefatendreef.si.ssl.KeyStoreManager;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Singleton to use Spring Beans with the sun wsdl stack.
 * 
 * @author Patrick van Amstel
 *
 */
@Component
public class SIApplicationContext implements ApplicationContextAware {

	static Logger _logger = LoggerFactory.getLogger(SIApplicationContext.class);
	
	static ApplicationContext _applicationContext = null;
	
	static KeyStoreManager _keyStoreManager = null;
	
	static TrustStoreManager _trustStoreManager = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		_applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		    return _applicationContext;
	}
	
	public static KeyStoreManager getKeyStoreManager() {
		
		if (_keyStoreManager == null){
			_keyStoreManager = getApplicationContext().getBean(KeyStoreManager.class);
			_logger.info(_keyStoreManager.configurationInformationAsString());
		}
		
		return _keyStoreManager;
	}

	public void setKeyStoreManager(KeyStoreManager keyStoreManager) {
		SIApplicationContext._keyStoreManager = keyStoreManager;
	}

	public static TrustStoreManager getTrustStoreManager() {
		if (_trustStoreManager == null){
			_trustStoreManager = getApplicationContext().getBean(TrustStoreManager.class);
			_logger.info(_trustStoreManager.configurationInformationAsString());
		}
		return _trustStoreManager;
	}

	public void setTrustStoreManager(TrustStoreManager trustStoreManager) {
		SIApplicationContext._trustStoreManager = trustStoreManager;
	}
	

}
