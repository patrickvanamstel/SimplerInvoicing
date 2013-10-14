package nl.kaninefatendreef.si.smp;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import nl.kaninefatendreef.si.constant.SIConfigurationProperties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Only works when there is a DNS service that resolves the peppol dns.
 * <p>
 * 
 * 
 * @author Patrick van Amstel
 *
 */
@Component
public class DNSLookupService implements InitializingBean{

	@Autowired
	Environment environment;
	
	Boolean _checkInetAddress = true;
	
    public boolean domainExists(URL url) {
    	if (!_checkInetAddress){
    		return true;
    	}
        try {
            InetAddress.getByName(url.getHost());
            return true;
        } catch (UnknownHostException exception) {
            return false;
        }
    }

	@Override
	public void afterPropertiesSet() throws Exception {

	
		if (environment.containsProperty(SIConfigurationProperties.PEPPOL_SML_DNS_VALIDATE.getValue())){
			if (environment.getProperty(SIConfigurationProperties.PEPPOL_SML_DNS_VALIDATE.getValue()).equalsIgnoreCase("true")){
				_checkInetAddress = true;
			}else{
				_checkInetAddress = false;
			}
		}
		
		
	}
}
