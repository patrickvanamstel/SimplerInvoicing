/* Created by steinar on 23.05.12 at 23:09 */
package eu.peppol.start.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents a type safe PEPPOL Process type identifier.
 *
 * @see "Policy for use of Identifiers, section 5"
 *
 * @author Steinar Overbeck Cook steinar@sendregning.no
 */
public class PeppolProcessTypeId {

    // See Policy 15 and policy 17
    private static final String scheme = "cenbiimeta-procid-ubl";

    private final String processTypeIdentifer;

    public PeppolProcessTypeId(String processTypeIdentifer) {

        if (!processTypeIdentifer.startsWith("urn:")) {
            throw new IllegalArgumentException("PEPPOL process type identifier should start with \"urn\"");
        }

        this.processTypeIdentifer = processTypeIdentifer;
    }

    public String getScheme() {
        return scheme;
    }

    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("PeppolProcessTypeId[\n");
    	builder.append("processTypeIdentifer " + processTypeIdentifer + "\n");
    	builder.append("]\n");
    	return builder.toString();
    }

    public String stringValue() {
		return processTypeIdentifer;
	}
    
    @Override
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
		PeppolProcessTypeId peppolProcessTypeId = (PeppolProcessTypeId) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(processTypeIdentifer,peppolProcessTypeId.processTypeIdentifer)
				.isEquals();
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder(15, 370).
 		       append(processTypeIdentifer).
 		       toHashCode();
    }


    public static PeppolProcessTypeId valueOf(String processTypeIdentifer) {
        return new PeppolProcessTypeId(processTypeIdentifer);
    }

	
}
