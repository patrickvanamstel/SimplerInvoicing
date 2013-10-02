/* Created by steinar on 23.05.12 at 23:29 */
package nl.kaninefatendreef.si.smp;


import java.net.URL;

import eu.peppol.start.model.ParticipantId;
import eu.peppol.start.model.PeppolDocumentTypeId;

@SuppressWarnings("serial")
public class SmpSignedServiceMetaDataException extends Exception {

    private final ParticipantId _participant;
    private final PeppolDocumentTypeId _documentTypeIdentifier;
    private final URL _smpUrl;

    public SmpSignedServiceMetaDataException(ParticipantId participant, PeppolDocumentTypeId documentTypeIdentifier, URL smpUrl, Exception e) {
        super("Unable to find information for participant: " + participant + ", documentType: " + documentTypeIdentifier + ", at url: " + smpUrl + " ; " + e.getMessage(), e);

        _participant = participant;
        _documentTypeIdentifier = documentTypeIdentifier;
        _smpUrl = smpUrl;
    }

    public ParticipantId getParticipant() {
        return _participant;
    }

    public PeppolDocumentTypeId getDocumentTypeIdentifier() {
        return _documentTypeIdentifier;
    }

    public URL getSmpUrl() {
        return _smpUrl;
    }
}
