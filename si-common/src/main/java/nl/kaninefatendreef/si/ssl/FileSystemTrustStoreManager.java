package nl.kaninefatendreef.si.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import nl.kaninefatendreef.si.SIConfigurationException;


public class FileSystemTrustStoreManager implements TrustStoreManager {

	private File _keyStoreFile = null;
	private String _password = null;

	private KeyStore _truststore = null;

	public KeyStore getTruststore() throws SIConfigurationException {
		
		if (_truststore == null){
			
			FileInputStream inputStream = null;
	        try {
	        	inputStream = new FileInputStream(getKeyStoreFile());
	            KeyStore keyStore = KeyStore.getInstance("JKS");
				keyStore.load(inputStream, getPassword().toCharArray());
				_truststore = keyStore;

			} catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e) {
				throw new SIConfigurationException(e);
			}finally{
				if (inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						throw new IllegalStateException(e);
					}
				}
			}
		}
		return _truststore;
	}
	
	// Getters and Setters

	public File getKeyStoreFile() {
		return _keyStoreFile;
	}

	public void setKeyStoreFile(File keyStoreFile) {
		_keyStoreFile = keyStoreFile;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}

}
