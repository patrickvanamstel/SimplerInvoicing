package nl.kaninefatendreef.si.ssl;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import nl.kaninefatendreef.si.SIConfigurationException;

public interface KeyStoreManager {

	public String configurationInformationAsString();
	
	public KeyStore getKeyStore() throws SIConfigurationException;
	public PrivateKey getPrivateKey()throws SIConfigurationException;
	public X509Certificate getCertificate() throws SIConfigurationException;

}
