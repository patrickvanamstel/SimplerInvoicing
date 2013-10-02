package nl.kaninefatendreef.si.document;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public class SISoapProxyHostnameVerifier implements HostnameVerifier {

    public boolean verify(final String hostname, final SSLSession session) {
        try {
        	session.getPeerPrincipal();
        } catch (SSLPeerUnverifiedException e) {
        	return false;
        }
        return true;
    }

}