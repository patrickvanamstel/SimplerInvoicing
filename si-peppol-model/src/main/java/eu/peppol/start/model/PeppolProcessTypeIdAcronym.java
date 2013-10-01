package eu.peppol.start.model;

/**
 * Acronyms for the various PEPPOL processes. Makes life a little easier, as the
 * PeppolProcessTypeId only represents a type safe value of any kind of string.
 *
 * According to Policy 16:
 * <em></em>PEPPOL processes are identified by the respective BII processes. The process identifier has to match the BII profile ID.</em>
 *
 * @author Steinar Overbeck Cook
 *
 *         Created by
 *         User: steinar
 *         Date: 04.12.11
 *         Time: 19:18
 *
 * @see "Tranport Policy for using Identifiers"
 */
public enum PeppolProcessTypeIdAcronym {


    ORDER_ONLY("urn:www.cenbii.eu:profile:bii03:ver1.0"),
    INVOICE_ONLY("urn:www.cenbii.eu:profile:bii04:ver1.0"),
    PROCUREMENT("urn:www.cenbii.eu:profile:bii06:ver1.0");

    private PeppolProcessTypeId _peppolProcessTypeId;


    private PeppolProcessTypeIdAcronym(String profileId) {
        _peppolProcessTypeId = PeppolProcessTypeId.valueOf(profileId);
    }

    public PeppolProcessTypeId getPeppolProcessTypeId() {
        return _peppolProcessTypeId;
    }

    public String toString() {
    	StringBuilder builder = new StringBuilder();
        builder.append("PeppolProcessTypeIdAcronym[\n");
        builder.append("peppolProcessTypeId" + _peppolProcessTypeId +"\n");
        builder.append("]\n");
        return builder.toString();
    }
}
