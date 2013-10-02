/* Created by steinar on 18.05.12 at 13:35 */
package nl.kaninefatendreef.si.smp;



import java.net.URL;

import eu.peppol.start.model.ParticipantId;

@SuppressWarnings("serial")
public class SmpLookupException extends RuntimeException {

	private ParticipantId _participantId;
    private URL _url;

    public SmpLookupException(ParticipantId participantId, Exception e) {
        super("Unable to perform SMP lookup for " + participantId + "; " + e.getMessage(), e);
        _participantId = participantId;
    }

    public SmpLookupException(ParticipantId participantId, URL servicesUrl, Exception cause) {
        super("Unable to fetch data for " + participantId + " from " + servicesUrl + " ;" + cause.getMessage(), cause);
        _participantId = participantId;
        _url = servicesUrl;
    }

    public ParticipantId getParticipantId() {
        return _participantId;
    }

    public URL getSmpUrl() {
        return _url;
    }
}
