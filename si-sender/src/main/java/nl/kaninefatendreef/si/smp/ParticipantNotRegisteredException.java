package nl.kaninefatendreef.si.smp;

import eu.peppol.start.model.ParticipantId;

@SuppressWarnings("serial")
public class ParticipantNotRegisteredException extends SmpException{

	ParticipantId _participantId;
	
	public ParticipantNotRegisteredException(ParticipantId participantId) {
		_participantId = participantId;
	}

}
