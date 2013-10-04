package nl.kaninefatendreef.si.server.ssl;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import java.security.cert.X509Certificate;

/**
 * 
 * @author Patrick van Amstel
 *
 */
@Component
public class ValidCertificateCache {

    private boolean _useCache = true;
    private long _timeout = 5 * 60 * 1000; // 5 minutes
    private Map<BigInteger, Long> _validCertificateCache = new ConcurrentHashMap<BigInteger, Long>();

    
    public boolean isValid(X509Certificate x509Certificate) {
        Long timestamp = _validCertificateCache.get(x509Certificate.getSerialNumber());
        
        if (timestamp == null){
        	return false;
        }
        
        if ((System.currentTimeMillis() - timestamp) > _timeout){
        	return false;
        }
        
        return true;
    }
    
    public  void setKnown(X509Certificate x509Certificate) {
        if (_useCache) {
            _validCertificateCache.put(x509Certificate.getSerialNumber(), System.currentTimeMillis());
        }
    }

    void setTimoutForTesting(long timeoutValue) {
        _timeout = timeoutValue;
    }
}
