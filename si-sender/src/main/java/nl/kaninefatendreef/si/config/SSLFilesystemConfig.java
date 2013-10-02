package nl.kaninefatendreef.si.config;

import java.io.File;

import nl.kaninefatendreef.si.ssl.FileSystemKeyStoreManager;
import nl.kaninefatendreef.si.ssl.FileSystemTrustStoreManager;
import nl.kaninefatendreef.si.ssl.KeyStoreManager;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

/**
 * Only instantiate when FileSystemKeystore is one of the active profiles.
 * 
 * @author Patrick van Amstel
 *
 */
@Profile("FileSystemKeystore")
public class SSLFilesystemConfig {

	
	@Autowired
	Environment environment;
	
	@Bean
	public KeyStoreManager fileSystemKeyStoreManager() {
		
		FileSystemKeyStoreManager fileSystemKeyStoreManager = new FileSystemKeyStoreManager();
		
		fileSystemKeyStoreManager.setKeyStoreFile(new File(environment.getProperty("nl.kaninefatendreef.si.keystore.file")));
		fileSystemKeyStoreManager.setPassword(environment.getProperty("nl.kaninefatendreef.si.keystore.password"));
		
		return fileSystemKeyStoreManager;
	}	 
	
	@Bean
	public TrustStoreManager fileSystemTrustStoreManager() {
		
		FileSystemTrustStoreManager fileSystemTrustStoreManager = new FileSystemTrustStoreManager();
		fileSystemTrustStoreManager.setKeyStoreFile(new File(environment.getProperty("nl.kaninefatendreef.si.truststore.file")));
		fileSystemTrustStoreManager.setPassword(environment.getProperty("nl.kaninefatendreef.si.truststore.password"));
		
		return fileSystemTrustStoreManager;
	}	 

	
}
