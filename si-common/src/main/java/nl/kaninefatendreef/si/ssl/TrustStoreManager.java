package nl.kaninefatendreef.si.ssl;

import java.security.KeyStore;
import java.security.cert.TrustAnchor;
import java.util.Set;

import nl.kaninefatendreef.si.SIConfigurationException;

public interface TrustStoreManager {

	public String configurationInformationAsString();
	 
	public KeyStore getTruststore() throws SIConfigurationException;

	public Set<TrustAnchor> getTrustAnchors() throws SIConfigurationException;

}
