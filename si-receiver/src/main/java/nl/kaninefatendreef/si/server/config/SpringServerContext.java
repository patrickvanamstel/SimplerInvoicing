package nl.kaninefatendreef.si.server.config;

import nl.kaninefatendreef.si.server.repository.ActiveDocumentRepository;
import nl.kaninefatendreef.si.server.ssl.SimplerInvoicingCertificateValidator;
import nl.kaninefatendreef.si.ssl.KeyStoreManager;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;



/**
 * Bridge between spring wireing and Sun wire-ing.
 * 
 * @author Patrick van Amstel
 */
@Component
public class SpringServerContext implements ApplicationContextAware {

	private static ApplicationContext _applicationContext = null;
	private static TrustStoreManager _peppolTrustStore = null;
	private static KeyStoreManager _peppolKeyStore = null;
	private static SimplerInvoicingCertificateValidator _simplerInvoicingCertificateValidator = null;
	private static ActiveDocumentRepository _activeDocumentRepository = null;

	public static ActiveDocumentRepository getActiveDocumentRepository(){
		if (_activeDocumentRepository == null){
			_activeDocumentRepository = _applicationContext.getBean(ActiveDocumentRepository.class);
		}
		return _activeDocumentRepository;
	}
	
	public static KeyStoreManager getPeppolKeyStore() {

		if (_peppolKeyStore == null){
			_peppolKeyStore = _applicationContext.getBean(KeyStoreManager.class);
		}
		return _peppolKeyStore;
	}

	public static TrustStoreManager getPeppolTrustStore() {
		if (_peppolTrustStore == null){
			_peppolTrustStore = _applicationContext.getBean(TrustStoreManager.class);
		}
		return _peppolTrustStore;
	}

	public static SimplerInvoicingCertificateValidator getSimplerInvoicingCertificateValidator(){
		if (_simplerInvoicingCertificateValidator == null){
			_simplerInvoicingCertificateValidator = _applicationContext.getBean(SimplerInvoicingCertificateValidator.class);
		}
		return _simplerInvoicingCertificateValidator;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringServerContext._applicationContext = applicationContext;
	}

}
