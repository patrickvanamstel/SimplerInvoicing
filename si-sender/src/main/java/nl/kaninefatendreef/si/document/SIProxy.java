package nl.kaninefatendreef.si.document;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * Proxy to be used when using the sun Resource implementation for the SAML2 communication.
 * 
 * @author Patrick van Amstel
 *
 */
public class SIProxy {

	class ProxyAuthenticator extends Authenticator {
	    private String _userName; 
	    private String _password;

	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(_userName, _password.toCharArray());
	    }

	    public ProxyAuthenticator(String userName, String password) {
	        this._userName = userName;
	        this._password = password;
	    }
	}
	
	private String _userName = null;
	private String _passWord = null;
	private String _httpProxyHost = null;
	private String _httpProxyPort = null;
	private String _httpsProxyHost = null;
	private String _httpsProxyPort = null;

	public void configure() {

		if (getUserName() != null && getPassWord() != null){
			Authenticator.setDefault(new ProxyAuthenticator(getUserName() , getPassWord()));
		}
		
		if (getHttpProxyHost() != null){
			System.setProperty("http.proxyHost", getHttpProxyHost());
		}
		if (getHttpProxyPort() != null){
			System.setProperty("http.proxyPort", getHttpProxyPort());
		}
		if (getHttpsProxyHost() != null){
			System.setProperty("https.proxyHost", getHttpsProxyHost());
		}
		if (getHttpsProxyPort() != null){
			System.setProperty("https.proxyPort", getHttpsProxyPort());
		}
	}

	
	// Getters and Setters
	
	public String getUserName() {
		return _userName;
	}



	public void setUserName(String userName) {
		this._userName = userName;
	}



	public String getPassWord() {
		return _passWord;
	}



	public void setPassWord(String passWord) {
		this._passWord = passWord;
	}



	public String getHttpProxyHost() {
		return _httpProxyHost;
	}



	public void setHttpProxyHost(String httpProxyHost) {
		this._httpProxyHost = httpProxyHost;
	}



	public String getHttpProxyPort() {
		return _httpProxyPort;
	}



	public void setHttpProxyPort(String httpProxyPort) {
		this._httpProxyPort = httpProxyPort;
	}



	public String getHttpsProxyHost() {
		return _httpsProxyHost;
	}



	public void setHttpsProxyHost(String httpsProxyHost) {
		this._httpsProxyHost = httpsProxyHost;
	}



	public String getHttpsProxyPort() {
		return _httpsProxyPort;
	}



	public void setHttpsProxyPort(String httpsProxyPort) {
		this._httpsProxyPort = httpsProxyPort;
	}


	public Proxy getProxy() {
		configure();
		return null;
	}


	
}
