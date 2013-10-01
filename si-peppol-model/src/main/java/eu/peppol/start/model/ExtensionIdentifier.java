/* Created by steinar on 20.05.12 at 13:02 */
package eu.peppol.start.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Type safe value object holding a PEPPOL extension identifier.
 *
 * @author Steinar Overbeck Cook steinar@sendregning.no
 */
public class ExtensionIdentifier {
    private final String extensionIdValue;

    public ExtensionIdentifier(String extensionIdValue) {
        this.extensionIdValue = extensionIdValue;
    }

    public static ExtensionIdentifier valueFor(String extensionIdValue) {
        return new ExtensionIdentifier(extensionIdValue);
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
		ExtensionIdentifier extensionIdentifier = (ExtensionIdentifier) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(extensionIdValue,
						extensionIdentifier.extensionIdValue)
				.isEquals();
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder(12, 370).
 		       append(extensionIdValue).
 		       toHashCode();
    }

    @Override
    public String toString() {
        return extensionIdValue;
    }
}
