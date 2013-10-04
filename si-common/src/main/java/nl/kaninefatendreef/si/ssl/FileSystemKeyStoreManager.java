package  nl.kaninefatendreef.si.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import nl.kaninefatendreef.si.SIConfigurationException;



public class FileSystemKeyStoreManager implements KeyStoreManager {
	
	private File _keyStoreFile = null;
	private String _password = null;
	private String _aliasPrivateKey = null;
	private String _aliasCertificate = null;

	private KeyStore _keyStore = null;
	private PrivateKey _privateKey = null;
	private X509Certificate _x509Certificate = null;
	

	@Override
	public String configurationInformationAsString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileSystemKeyStoreManager : \n");
		if (_keyStoreFile != null){
			builder.append("Location : " + _keyStoreFile.getAbsolutePath() + "\n");
		}
		return builder.toString();
	}
	
	public KeyStore getKeyStore() throws SIConfigurationException{
		if (_keyStore == null){
			FileInputStream inputStream = null;
	        try {
	        	inputStream = new FileInputStream(getKeyStoreFile());
	            KeyStore keyStore = KeyStore.getInstance("JKS");
				keyStore.load(inputStream, getPassword().toCharArray());
				_keyStore = keyStore;

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
		return _keyStore;        
	}

	@Override
	public X509Certificate getCertificate() throws SIConfigurationException {
		if (_x509Certificate == null){
			try {
				Enumeration <String> aliases = getKeyStore().aliases();
				while(aliases.hasMoreElements()){
					String alias = aliases.nextElement();
					X509Certificate certificate = (X509Certificate)getKeyStore().getCertificate(alias);
					
					if (getAliasCertificate() == null){
						_x509Certificate = certificate;
						break;
					}
					
					if (getAliasCertificate().equals(alias)){
						_x509Certificate = certificate;
						break;
					}
				}
				
				if (_x509Certificate == null){
					throw new SIConfigurationException("No certificate found in keystore on location " + getKeyStoreFile().getAbsolutePath() + " with alias " + getAliasCertificate()); 
				}
			} catch (KeyStoreException e) {
				throw new SIConfigurationException(e);
			}
		}
		return _x509Certificate;
	}


	
	public PrivateKey getPrivateKey() throws SIConfigurationException {
	if (_privateKey == null){
			try{
				Enumeration <String> aliases = getKeyStore().aliases();
				while(aliases.hasMoreElements()){
					
					String alias = aliases.nextElement();
					Key keyFromKeyStore = getKeyStore().getKey(alias, getPassword().toCharArray());
	                if (keyFromKeyStore instanceof PrivateKey) {
	                	if (getAliasPrivateKey() == null){
	                		_privateKey = (PrivateKey)keyFromKeyStore;
	                		break;
	                	}
	                	
	                	if (alias.equals(getAliasPrivateKey())){
	                		_privateKey = (PrivateKey)keyFromKeyStore;
	                		break;
	                	}
	                }
				}
			}catch(KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e){
				throw new SIConfigurationException(e);
			}
		}
		if (_privateKey == null){
			// Hier had hij gezet moeten zijn
			throw new SIConfigurationException("No private key found in keystore on location " + getKeyStoreFile().getAbsolutePath() + " with alias " + getAliasPrivateKey());
			
		}
		return _privateKey;
		
	}

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
		_password = password;
	}

	public String getAliasPrivateKey() {
		return _aliasPrivateKey;
	}

	public void setAliasPrivateKey(String aliasPrivateKey) {
		_aliasPrivateKey = aliasPrivateKey;
	}
	public String getAliasCertificate() {
		return _aliasCertificate;
	}

	public void setAliasCertificate(String aliasCertificate) {
		_aliasCertificate = aliasCertificate;
	}







}
