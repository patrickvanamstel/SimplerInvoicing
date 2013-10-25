package nl.kaninefatendreef.si.server.config;

import java.io.File;
import java.util.ArrayList;

import nl.kaninefatendreef.si.constant.SIConfigurationProperties;
import nl.kaninefatendreef.si.server.model.SimplerInvoicingApplicationUser;
import nl.kaninefatendreef.si.server.repository.ActiveSimplerInvoicingApplicationUserRepository;
import nl.kaninefatendreef.si.ssl.FileSystemKeyStoreManager;
import nl.kaninefatendreef.si.ssl.FileSystemTrustStoreManager;
import nl.kaninefatendreef.si.ssl.KeyStoreManager;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sun.xml.ws.transport.http.HttpAdapter;

import eu.peppol.start.model.AccessPointIdentifier;

@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {
		"nl.kaninefatendreef.si.server"})
public class SpringConfig implements InitializingBean{
	
	@Autowired
	Environment _environment = null;
	
	@Autowired 
	ActiveSimplerInvoicingApplicationUserRepository _activeSimplerInvoicingApplicationUserRepository = null;
	
	
	@Bean
	public TrustStoreManager peppolTrustStore(){
		FileSystemTrustStoreManager fileSystemTrustStore = new FileSystemTrustStoreManager();
		fileSystemTrustStore.setPassword(_environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.truststore.password"));
		fileSystemTrustStore.setKeyStoreFile(new File(_environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.truststore.location")));
		return fileSystemTrustStore;
	}

	@Bean 
	public KeyStoreManager peppolKeyStore(){
		FileSystemKeyStoreManager keyStore = new FileSystemKeyStoreManager();
		keyStore.setPassword(_environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.keystore.password"));
		keyStore.setKeyStoreFile(new File(_environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.keystore.location")));
		return keyStore;
	}

	@Bean
	public AccessPointIdentifier accessPointIdentifier() {
		return new AccessPointIdentifier(_environment.getProperty("com.anachron.peppol.configuration.accessPointIdentifier"));
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {

		if (
				_environment.getProperty("com.anachron.peppol.soap.trace.enabled") != null && 
				_environment.getProperty("com.anachron.peppol.soap.trace.enabled").toUpperCase().equals("TRUE")
			)
		{
			HttpAdapter.dump = true;
		}
		if (_activeSimplerInvoicingApplicationUserRepository != null){
			// Create default user
			// Configurable by properties passed

			if (_environment.containsProperty(SIConfigurationProperties.SI_RECEIVER_APP_USER_CREATE.getValue())){
				
				String property = _environment.getProperty(SIConfigurationProperties.SI_RECEIVER_APP_USER_CREATE.getValue());
				if (property.equalsIgnoreCase("true")){
					
					String userName = null;
					if (_environment.containsProperty(SIConfigurationProperties.SI_RECEIVER_APP_USER_NAME.getValue())){
						userName =  _environment.getProperty(SIConfigurationProperties.SI_RECEIVER_APP_USER_NAME.getValue());
					}
					
					String password = null;
					if (_environment.containsProperty(SIConfigurationProperties.SI_RECEIVER_APP_USER_PASS.getValue())){
						password =  _environment.getProperty(SIConfigurationProperties.SI_RECEIVER_APP_USER_PASS.getValue());
					}
					
					if (userName != null && password != null){
						 SimplerInvoicingApplicationUser user = _activeSimplerInvoicingApplicationUserRepository.findByUsername(userName);
						 if (user == null){
							 // lets create
							 SimplerInvoicingApplicationUser userNew =  _activeSimplerInvoicingApplicationUserRepository.createSimplerInvoicingApplicationUser();
							 userNew.setPassword(password); 
							 userNew.setUsername(userName);
							 ArrayList<String> roles = new ArrayList<>();
							 roles.add("USER_ROLE");
							 roles.add("ADMIN_ROLE");
							 roles.add("REST_ROLE");
							 userNew.setRoles(roles);
							 _activeSimplerInvoicingApplicationUserRepository.save(userNew);
						 }
					}
						
					}
					
				}
				
			}


			
			

		
	}
	

	
	

	
	
	
}
