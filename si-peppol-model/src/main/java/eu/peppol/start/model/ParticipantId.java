package eu.peppol.start.model;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Steinar Overbeck Cook
 *         <p/>
 *         Created by
 *         User: steinar
 *         Date: 04.12.11
 *         Time: 18:48
 */
public class ParticipantId {
    private static String NO_AGENCY_CODE_NO_VAT = "9908";
    private static String NO_AGENCY_CODE_VAT = "9909";

    // The weight array obtained from Br\u00F8nn\u00F8ysund, used to validate a norwegian org no
    static final Integer[] ORG_NO_WEIGHT = new Integer[]{3, 2, 7, 6, 5, 4, 3, 2};
    static final int MODULUS_11 = 11;

    private String _participantId;

    public static String getScheme() {
        return _scheme;
    }

    private static final String _scheme = "iso6523-actorid-upis";

    public ParticipantId(String recipientId) {
        if (recipientId == null) {
            throw new IllegalArgumentException("ParticipantId requires a non-null argument");
        }
        _participantId = recipientId;
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
		ParticipantId participantId = (ParticipantId) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(_participantId,
						participantId._participantId)
				.isEquals();
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder(13, 370).
 		       append(_participantId).
 		       toHashCode();
         }

    public String stringValue() {
        return _participantId;
    }

    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("ParticipantId [\n");
    	builder.append("value " + _participantId + "\n");
    	builder.append("]\n");
    	return builder.toString();
    }

    
    public static boolean isValidParticipantIdentifier(String participantIdString) {
        if (participantIdString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(\\d{4}):(.+)");
        Matcher matcher = pattern.matcher(participantIdString);

        if (!matcher.find()) {
            return false;
        }

        String agencyCode = matcher.group(1);

        if (agencyCode.equals(NO_AGENCY_CODE_NO_VAT) || agencyCode.equals(NO_AGENCY_CODE_VAT)) {
            String organisationNumber = matcher.group(2);
            return isValidOrganisationNumber(organisationNumber);
        } else {
            return true;
        }
    }




    static boolean isValidOrganisationNumber(String org) {
        if (org == null || org.length() != 9 || !Character.isDigit(org.charAt(8))) {
            return false;
        }

        int actualCheckDigit = org.charAt(8) - 48;
        List<Integer> weights = Arrays.asList(3, 2, 7, 6, 5, 4, 3, 2);
        int sum = 0;

        for (int i = 0; i < 8; i++) {
            char next = org.charAt(i);

            if (!Character.isDigit(next)) {
                return false;
            }

            int digit = (int) next - 48;
            sum += digit * weights.get(i);
        }

        int modulus = sum % 11;

        /** don't subtract from length if the modulus is 0 */
        if ((modulus == 0) && (actualCheckDigit == 0)) {
            return true;
        }

        int calculatedCheckDigit = 11 - modulus;
        return actualCheckDigit == calculatedCheckDigit;
    }
}
