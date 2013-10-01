/* Created by steinar on 20.05.12 at 13:00 */
package eu.peppol.start.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TransactionIdentifier {

    private final String _transactionId;

    public TransactionIdentifier(String transactionId) {
        _transactionId = transactionId;
    }

    public static TransactionIdentifier create(String transactionId) {
        return new TransactionIdentifier(transactionId);
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
		TransactionIdentifier transactionIdentifier = (TransactionIdentifier) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(_transactionId,
						transactionIdentifier._transactionId)
				.isEquals();
    }

    public int hashCode() {
    	return new HashCodeBuilder(16, 370).
 		       append(_transactionId).
 		       toHashCode();
    }


    // Durf deze niet te veranderen.
    public String toString() {
        return _transactionId;
    }
}
