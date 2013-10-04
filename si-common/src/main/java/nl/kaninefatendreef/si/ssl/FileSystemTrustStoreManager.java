package nl.kaninefatendreef.si.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.Set;

import nl.kaninefatendreef.si.SIConfigurationException;


public class FileSystemTrustStoreManager implements TrustStoreManager {

	private File _keyStoreFile = null;
	private String _password = null;

	private KeyStore _truststore = null;

	@Override
	public String configurationInformationAsString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileSystemTrustStoreManager : \n");
		if (_keyStoreFile != null){
			builder.append("Location : " + _keyStoreFile.getAbsolutePath() + "\n");
		}
		return builder.toString();
	}

	
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

	private Set<TrustAnchor> _trustAnchors = null;
	
	@Override
	public Set<TrustAnchor> getTrustAnchors() throws SIConfigurationException {

		if (_trustAnchors != null){
			return _trustAnchors;
		}
		PKIXParameters pkixParameters = null;
		try {
			pkixParameters = new  PKIXParameters( getTruststore());
		} catch (KeyStoreException | InvalidAlgorithmParameterException | SIConfigurationException e) {
			throw new SIConfigurationException(e);
		}
		_trustAnchors = pkixParameters.getTrustAnchors(); 
		return _trustAnchors;
		

	}
}
