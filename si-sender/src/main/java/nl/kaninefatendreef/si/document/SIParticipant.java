package nl.kaninefatendreef.si.document;

import java.net.URL;

import eu.peppol.start.model.ChannelId;
import eu.peppol.start.model.ParticipantId;

public class SIParticipant {

	ParticipantId _participantId = null;
	URL _destination ;
	ChannelId _channelId ;
	
	public URL getDestination() {
		return _destination;
	}

	public void setDestination(URL destination) {
		this._destination = destination;
	}

	public ChannelId getChannelId() {
		return _channelId;
	}

	public void setChannelId(ChannelId channelId) {
		this._channelId = channelId;
	}

	
	
	public ParticipantId getParticipantId() {
		return _participantId;
	}

	public void setParticipantId(ParticipantId participantId) {
		this._participantId = participantId;
	}
	
}
