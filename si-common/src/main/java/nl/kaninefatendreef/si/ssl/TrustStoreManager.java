package nl.kaninefatendreef.si.ssl;

import java.security.KeyStore;

import nl.kaninefatendreef.si.SIConfigurationException;

public interface TrustStoreManager {

	public String configurationInformationAsString();
	 
	public KeyStore getTruststore() throws SIConfigurationException;;

}
