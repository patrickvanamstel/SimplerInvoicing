package nl.kaninefatendreef.si.constant;




/**
 * All configurable properties are put in one place.
 * 
 * @author Patrick van Amstel
 *
 */
public enum SIConfigurationProperties {

	PEPPOL_SML_DNS_NAME					("nl.kaninefatendreef.si.peppol.dns.name"),
	PEPPOL_SML_DNS_IP					("nl.kaninefatendreef.si.peppol.dns.ip"),
	PEPPOL_SML_DNS_VALIDATE				("nl.kaninefatendreef.si.peppol.dns.validate"),
	PEPPOL_SML_DNS_PROXY_NAME			("nl.kaninefatendreef.si.peppol.dns.proxy.name"),
	PEPPOL_SML_DNS_PROXY_PORT			("nl.kaninefatendreef.si.peppol.dns.proxy.port"),

	SI_SENDER_ID 						("nl.kaninefatendreef.si.sender.id"),
	SI_KEYSTORE_FILE 					("nl.kaninefatendreef.si.keystore.file"),
	SI_KEYSTORE_PASS 					("nl.kaninefatendreef.si.keystore.password"),
	SI_TRUSTSTORE_FILE 					("nl.kaninefatendreef.si.truststore.file"),
	SI_TRUSTSTORE_PASS 					("nl.kaninefatendreef.si.truststore.password");	

    
    
    private String _value;
    
    SIConfigurationProperties(String value) {
        _value = value;
    }

    public String getValue(){
    	return _value;
    }
    
	
}
