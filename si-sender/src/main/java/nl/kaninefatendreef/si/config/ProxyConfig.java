package nl.kaninefatendreef.si.config;

import nl.kaninefatendreef.si.document.SIProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Profile(value="proxy")
@Configuration
public class ProxyConfig {

	@Autowired
	Environment environment; 
	
	@Bean 
	public SIProxy siProxy(){
		
		SIProxy proxy = new SIProxy();
		
		proxy.setHttpProxyHost(propertyFromEnvironmentWhenFound("nl.kaninefatendreef.si.sender.proxy.host"));
		proxy.setHttpProxyPort(propertyFromEnvironmentWhenFound("nl.kaninefatendreef.si.sender.proxy.port"));
		
		proxy.setHttpsProxyHost(propertyFromEnvironmentWhenFound("nl.kaninefatendreef.si.sender.proxy.host"));
		proxy.setHttpsProxyPort(propertyFromEnvironmentWhenFound("nl.kaninefatendreef.si.sender.proxy.port"));
		
		
		proxy.setUserName(propertyFromEnvironmentWhenFound("nl.kaninefatendreef.si.sender.proxy.user"));
		proxy.setPassWord(propertyFromEnvironmentWhenFound("nl.kaninefatendreef.si.sender.proxy.name"));
		return proxy;
		
	}

	private String propertyFromEnvironmentWhenFound(String propertyName){
		
		if (!environment.containsProperty(propertyName)){
			return null;
		}
		
		String propertyValue = environment.getProperty(propertyName);
		if (propertyValue.equals("")){
			return null;
		}
		return propertyValue;
		
		
	}
	
	
}
