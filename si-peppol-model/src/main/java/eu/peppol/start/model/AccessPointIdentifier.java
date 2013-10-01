package eu.peppol.start.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AccessPointIdentifier {

	public static final AccessPointIdentifier TEST = new AccessPointIdentifier("NO-TEST-AP");
	
    private String _accessPointIdentifierValue;
    
    public AccessPointIdentifier(String accessPointIdentifierValue) {
        _accessPointIdentifierValue = accessPointIdentifierValue;
    }

    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("AccessPointIdentifier [ \n");
    	builder.append("_accessPointIdentifierValue : " + _accessPointIdentifierValue + "\n");
    	builder.append("]\n");
        return _accessPointIdentifierValue;
    }

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		AccessPointIdentifier accessPointIdentifier = (AccessPointIdentifier) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(_accessPointIdentifierValue,
						accessPointIdentifier._accessPointIdentifierValue)
				.isEquals();
	}    
  
    @Override
    public int hashCode() {
    	
    	return new HashCodeBuilder(10, 370).
    		       append(_accessPointIdentifierValue).
    		       toHashCode();
    }
}
