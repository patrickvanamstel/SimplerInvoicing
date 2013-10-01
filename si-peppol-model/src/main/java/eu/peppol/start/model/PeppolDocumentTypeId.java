/* Created by steinar on 20.05.12 at 14:11 */
package eu.peppol.start.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents a type safe PEPPOL Document Identifier, textually represented thus:
 * <pre>
 *     &lt;root NS>::&lt;document element local name>##&lt;customization id>::&lt;version>
 * </pre>
 *
 * @author Steinar Overbeck Cook
 * @see "PEPPOL Policy for us of Identifiers v2.2, POLICY 13"
 * @see PeppolDocumentTypeIdAcronym
 */
public class PeppolDocumentTypeId {

    String rootNameSpace;
    String localName;
    CustomizationIdentifier customizationIdentifier;
    String version;

    private static String scheme = "busdox-docid-qns";

    /**
     * <pre>
     *     &lt;root NS>::&lt;document element local name>##&lt;customization id>::&lt;version>
     * </pre>
     */
    static Pattern documentIdPattern = Pattern.compile("(urn:.*)::(.*)##(urn:.*)::(.*)");

    public PeppolDocumentTypeId(String rootNameSpace, String localName, CustomizationIdentifier customizationIdentifier, String version) {

        this.rootNameSpace = rootNameSpace;
        this.localName = localName;
        this.customizationIdentifier = customizationIdentifier;
        this.version = version;
    }

    /**
     * Parses the supplied text string into the separate components of a PEPPOL Document Identifier.
     *
     * @param documentIdAsText textual representation of a document identifier.
     * @return type safe instance of DocumentTypeIdentifier
     */
    public static PeppolDocumentTypeId valueOf(String documentIdAsText) {

        Matcher matcher = documentIdPattern.matcher(documentIdAsText);
        if (matcher.matches()) {
            String rootNameSpace = matcher.group(1);
            String localName = matcher.group(2);
            String customizationIdAsText = matcher.group(3);
            String version = matcher.group(4);

            CustomizationIdentifier customizationIdentifier = CustomizationIdentifier.create(customizationIdAsText);
            return new PeppolDocumentTypeId(rootNameSpace, localName, customizationIdentifier, version);
        } else
            throw new IllegalArgumentException("Unable to parse " + documentIdAsText + " into PEPPOL Document Type Identifier");
    }

    public static String getScheme() {
        return scheme;
    }

    public String stringValue(){
        final StringBuilder sb = new StringBuilder();
        sb.append(rootNameSpace);
        sb.append("::").append(localName);
        sb.append("##").append(customizationIdentifier);
        sb.append("::").append(version);
        return sb.toString();
    }
    
    public String toString(){
        StringBuilder builder = new StringBuilder();
        
        builder.append("PeppolDocumentTypeId[\n");
        builder.append("rootNameSpace " + rootNameSpace + "\n");
        builder.append("localName " + localName+ "\n");
        builder.append("customizationIdentifier " + customizationIdentifier+ "\n");
        builder.append("version " + version+ "\n");
        builder.append("]\n");
        return builder.toString();
    }


    public String getRootNameSpace() {
        return rootNameSpace;
    }

    public String getLocalName() {
        return localName;
    }

    public CustomizationIdentifier getCustomizationIdentifier() {
        return customizationIdentifier;
    }

    public String getVersion() {
        return version;
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
		PeppolDocumentTypeId peppolDocumentTypeId = (PeppolDocumentTypeId) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(customizationIdentifier,peppolDocumentTypeId.customizationIdentifier)
				.append(localName,peppolDocumentTypeId.localName)
				.append(rootNameSpace,peppolDocumentTypeId.rootNameSpace)
				.append(version,peppolDocumentTypeId.version)
				.isEquals();
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder(14, 370).
 		       append(customizationIdentifier).
 		       append(localName).
 		       append(rootNameSpace).
 		       append(version).
 		       toHashCode();
    }
    
    
}
