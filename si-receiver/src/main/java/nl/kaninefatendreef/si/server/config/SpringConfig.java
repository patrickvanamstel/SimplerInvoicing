package nl.kaninefatendreef.si.server.config;

import java.io.File;

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
	Environment environment = null;
	
	@Bean
	public TrustStoreManager peppolTrustStore(){
		FileSystemTrustStoreManager fileSystemTrustStore = new FileSystemTrustStoreManager();
		fileSystemTrustStore.setPassword(environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.truststore.password"));
		fileSystemTrustStore.setKeyStoreFile(new File(environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.truststore.location")));
		return fileSystemTrustStore;
	}

	@Bean 
	public KeyStoreManager peppolKeyStore(){
		FileSystemKeyStoreManager keyStore = new FileSystemKeyStoreManager();
		keyStore.setPassword(environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.keystore.password"));
		keyStore.setKeyStoreFile(new File(environment.getProperty("com.anachron.peppol.configuration.ssl.peppol.keystore.location")));
		return keyStore;
	}

	@Bean
	public AccessPointIdentifier accessPointIdentifier() {
		
		return new AccessPointIdentifier(environment.getProperty("com.anachron.peppol.configuration.accessPointIdentifier"));
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {

		if (
				environment.getProperty("com.anachron.peppol.soap.trace.enabled") != null && 
				environment.getProperty("com.anachron.peppol.soap.trace.enabled").toUpperCase().equals("TRUE")
			)
		{
			HttpAdapter.dump = true;
		}
		
		
	}
	

	
	

	
	
	
}
