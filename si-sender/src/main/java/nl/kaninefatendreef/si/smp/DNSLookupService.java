package nl.kaninefatendreef.si.smp;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

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

		if (environment.containsProperty("nl.kaninefaten.si.peppol.dns.proxy.name"))
		{
    		System.setProperty("http.proxyHost", environment.getProperty("nl.kaninefaten.si.peppol.dns.proxy.name"));
			System.setProperty("http.proxyPort", environment.getProperty("nl.kaninefaten.si.peppol.dns.proxy.port"));
		}
		
		if (environment.containsProperty("nl.kaninefaten.si.peppol.dns.validate")){
			if (environment.getProperty("nl.kaninefaten.si.peppol.dns.validate").equalsIgnoreCase("true")){
				_checkInetAddress = true;
			}else{
				_checkInetAddress = false;
			}
		}
		
		
	}
}
