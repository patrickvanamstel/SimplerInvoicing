package nl.kaninefatendreef.si.smp;

import java.net.URL;

import eu.peppol.start.model.ParticipantId;

@SuppressWarnings("serial")
public class SmpParticipantNotFoundException extends SmpException {

	private ParticipantId _participantId;

   public SmpParticipantNotFoundException(){
    	super();
    }

    public SmpParticipantNotFoundException(ParticipantId participantId, URL servicesUrl, Throwable t) {
    	super(t);
    	_participantId = participantId;
    }
    
    public SmpParticipantNotFoundException(Throwable t) {
    	super(t);
	}

	public ParticipantId getParticipantId() {
        return _participantId;
    }

    
	public void setParticipantId(ParticipantId participantId) {
		_participantId = participantId;
	}

}
