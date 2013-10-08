package nl.kaninefatendreef.si.server.config;

import nl.kaninefatendreef.si.constant.SIConfigurationProperties;
import nl.kaninefatendreef.si.server.IncommingMessageValidator;
import nl.kaninefatendreef.si.server.repository.ActiveDocumentRepository;
import nl.kaninefatendreef.si.server.ssl.SimplerInvoicingCertificateValidator;
import nl.kaninefatendreef.si.ssl.KeyStoreManager;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static IncommingMessageValidator _incommingMessageValidator = null;

	private static Logger logger = LoggerFactory.getLogger(SpringServerContext.class); 
	
	
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


	public static IncommingMessageValidator getIncommingMessageValidator() {
		if (_incommingMessageValidator == null){
			
			String [] validationBeans = _applicationContext.getBeanNamesForType(IncommingMessageValidator.class);
			if (validationBeans.length == 0){
				throw new IllegalStateException("There must be at least 1 bean for the validation of the incomming message");
			}else if (validationBeans.length == 1){
				_incommingMessageValidator = _applicationContext.getBean(IncommingMessageValidator.class);	
			}else if (validationBeans.length == 2){
				for (String validationBean : validationBeans){
					if (!validationBean.equals("incommingNullMessageValidator")){
						_incommingMessageValidator = (IncommingMessageValidator) _applicationContext.getBean(validationBean);		
					}
				}
				
			}else if (validationBeans.length > 2){

				boolean found = false;
				String componentName = _applicationContext.getEnvironment().getProperty(SIConfigurationProperties.SI_RECEIVER_EXPORT_COMPONENT_NAME.getValue());
				for (String validationBean : validationBeans){

					if (!validationBean.equals("incommingNullMessageValidator") && validationBean.equals(componentName)){
						_incommingMessageValidator = (IncommingMessageValidator) _applicationContext.getBean(validationBean);
						found = true;
					}
				}
				if (!found){
					logger.error("Component with name " + componentName + " not found in environment with property " + SIConfigurationProperties.SI_RECEIVER_EXPORT_COMPONENT_NAME.getValue() );
				}

				
				
			}else{
				_incommingMessageValidator = _applicationContext.getBean(IncommingMessageValidator.class);
			}
			
			
		
		
		}
		return _incommingMessageValidator;
	}

}
