/* Created by steinar on 20.05.12 at 12:07 */
package eu.peppol.start.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents a PEPPOL Customization Identifier contained within a PEPPOL  Document Identifier, for which the
 * pattern is
 * <pre>
 *     &lt;transactionId>:#&lt;extensionId>[#&lt;extensionId>]
 * </pre>
 * I.e. a string followed by ":#" followed by another string followed by an optional string starting with "#"
 * and followed by another string.

 * @author Steinar Overbeck Cook steinar@sendregning.no
 *
 * @see "PEPPOL Policy for us of Identifiers v2.2"
 */
public class CustomizationIdentifier {


    static Pattern customizationIdentifierPattern = Pattern.compile("(.*):#([^#]*)(?:#(.*))?");
    private final TransactionIdentifier transactionIdentifier;
    private final ExtensionIdentifier extensionIdentifier1;
    private final ExtensionIdentifier extensionIdentifier2;

    public CustomizationIdentifier(TransactionIdentifier transactionIdentifier, ExtensionIdentifier extensionIdentifier1, ExtensionIdentifier extensionIdentifier2) {

        this.transactionIdentifier = transactionIdentifier;
        this.extensionIdentifier1 = extensionIdentifier1;
        this.extensionIdentifier2 = extensionIdentifier2;

    }

    public CustomizationIdentifier(TransactionIdentifier transactionIdentifier, ExtensionIdentifier extensionIdentifier) {
        this(transactionIdentifier, extensionIdentifier, null);
    }

    public static CustomizationIdentifier create(String s) {

        CustomizationIdentifier result = null;

        Matcher matcher = customizationIdentifierPattern.matcher(s);
        if (!matcher.find()){
            throw new IllegalArgumentException(s + " not recognized as customization identifier");
        }

        String transactionId = matcher.group(1);
        TransactionIdentifier transactionIdentifier = TransactionIdentifier.create(transactionId);

        String extensionIdValue = matcher.group(2);
        ExtensionIdentifier extensionIdentifier1 = ExtensionIdentifier.valueFor(extensionIdValue);

        // Is there a second extension within the customization string?
        if (matcher.group(3) != null) {
            ExtensionIdentifier extensionIdentifier2 = ExtensionIdentifier.valueFor(matcher.group(3));
            result = new CustomizationIdentifier(transactionIdentifier, extensionIdentifier1, extensionIdentifier2);
        } else {
            result = new CustomizationIdentifier(transactionIdentifier, extensionIdentifier1);
        }
        return result;
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
		CustomizationIdentifier customizationIdentifier = (CustomizationIdentifier) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(transactionIdentifier,customizationIdentifier.transactionIdentifier)
				.append(extensionIdentifier1,customizationIdentifier.extensionIdentifier1)
				.append(extensionIdentifier2,customizationIdentifier.extensionIdentifier2)
				.isEquals();
    }

    
    public int hashCode() {
        
    	return new HashCodeBuilder(11, 370).
 		       append(transactionIdentifier).
 		      append(extensionIdentifier1).
 		     append(extensionIdentifier2).
 		       toHashCode();
    }

    
    // Durf ik niet te veranderen
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(transactionIdentifier).append(":#");
        sb.append(extensionIdentifier1);
        if (extensionIdentifier2 != null) {
            sb.append("#").append(extensionIdentifier2);
        }
        return sb.toString();
    }

    public TransactionIdentifier getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public ExtensionIdentifier getFirstExtensionIdentifier() {
        return extensionIdentifier1;
    }

    public ExtensionIdentifier getSecondExtensionIdentifier() {
        return extensionIdentifier2;
    }
}
