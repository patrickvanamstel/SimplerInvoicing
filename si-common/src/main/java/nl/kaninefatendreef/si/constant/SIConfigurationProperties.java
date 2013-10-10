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
	SI_TRUSTSTORE_PASS 					("nl.kaninefatendreef.si.truststore.password"),	

    SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_DIALECT 		("hibernate.dialect"), 
    SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_HM2DLLAUTO 	("hibernate.hbm2ddl.auto"),  
    SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_SHOW_SQL 	("hibernate.show_sql" ), 
    SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_FORMAT_SQL 	("hibernate.format_sql"), 
    SI_RECEIVER_RDBMS_DRIVER_CLASSNAME				("nl.kaninefatendreef.si.rdbms.classname"),
    SI_RECEIVER_RDBMS_DRIVER_URL					("nl.kaninefatendreef.si.rdbms.url"),
    SI_RECEIVER_RDBMS_DRIVER_USERNAME				("nl.kaninefatendreef.si.rdbms.username"),
    SI_RECEIVER_RDBMS_DRIVER_PASSWORD				("nl.kaninefatendreef.si.rdbms.password"),
    SI_RECEIVER_EXPORT_COMPONENT_NAME				("nl.kaninefatendreef.si.export.component.name"),
	SI_RECEIVER_VALIDATOR_COMPONENT_NAME			("nl.kaninefatendreef.si.validator.component.name"),
	
	SI_RECEIVER_APP_USER_CREATE						("nl.kaninefatendreef.si.app.user.create"),
	SI_RECEIVER_APP_USER_NAME						("nl.kaninefatendreef.si.app.user.name"),
	SI_RECEIVER_APP_USER_PASS						("nl.kaninefatendreef.si.app.user.pass");
	
    
    private String _value;
    
    SIConfigurationProperties(String value) {
        _value = value;
    }

    public String getValue(){
    	return _value;
    }
    
	
}
