package nl.kaninefatendreef.si.document;

import nl.kaninefatendreef.si.ssl.KeyStoreManager;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class SIApplicationContext implements ApplicationContextAware {

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
		
//		FileSystemKeyStoreManager fsk = new FileSystemKeyStoreManager();
//        fsk.setKeyStoreFile(new File("/opt/tomcat/7-metro/oxalis/simplerinvoicing.anachron.com.jks"));
//        fsk.setPassword("anachron");
		
		return _keyStoreManager;
	}

	public void setKeyStoreManager(KeyStoreManager keyStoreManager) {
		SIApplicationContext._keyStoreManager = keyStoreManager;
	}

	public static TrustStoreManager getTrustStoreManager() {
		return _trustStoreManager;
	}

	public void setTrustStoreManager(TrustStoreManager trustStoreManager) {
		SIApplicationContext._trustStoreManager = trustStoreManager;
	}
	

}
