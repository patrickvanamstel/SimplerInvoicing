package nl.kaninefatendreef.si.server.config;

public enum SIServerProperties {

	SI_RECEIVER_RDBMS_JPA_MODEL ("nl.kaninefatendreef.si.server.model.jpa") ,
	SI_RECEIVER_RDBMS_JPA_REPOSITORY ("nl.kaninefatendreef.si.server.repository.jpa");
	
    private String _value;
    
    SIServerProperties(String value) {
        _value = value;
    }

    public String getValue(){
    	return _value;
    }

	
}
