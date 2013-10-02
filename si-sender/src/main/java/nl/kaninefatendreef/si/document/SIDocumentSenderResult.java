package nl.kaninefatendreef.si.document;

import eu.peppol.start.model.ChannelId;
import eu.peppol.start.model.MessageId;
import eu.peppol.start.model.PeppolProcessTypeId;
import eu.peppol.start.model.ParticipantId;

public class SIDocumentSenderResult {

	private MessageId _messageId = null;
	private ParticipantId _senderId = null;
	private ParticipantId _recipientId = null;
	private ChannelId _channelId = null;
	private PeppolProcessTypeId _peppolProcessTypeId = null;

	public String toString(){
		StringBuilder builder = new StringBuilder();

		builder.append("SIDocumentSenderResult [\n");
		builder.append("MessageId :" + _messageId + "\n");
		builder.append("SenderId :" +  _senderId + "\n");
		builder.append("RecipientId :" +  _recipientId + "\n");
		builder.append("ChannelId :" +  _channelId + "\n");
		builder.append("PeppolProcessTypeId :" +  _peppolProcessTypeId + "\n");
		builder.append("]\n");
		
		return builder.toString();
	}
	

	// Getters and Setters
	
	public MessageId getMessageId() {
		return _messageId;
	}


	public void setMessageId(MessageId messageId) {
		_messageId = messageId;
	}


	public ParticipantId getSenderId() {
		return _senderId;
	}


	public void setSenderId(ParticipantId senderId) {
		_senderId = senderId;
	}


	public ParticipantId getRecipientId() {
		return _recipientId;
	}


	public void setRecipientId(ParticipantId recipientId) {
		_recipientId = recipientId;
	}


	public ChannelId getChannelId() {
		return _channelId;
	}


	public void setChannelId(ChannelId channelId) {
		_channelId = channelId;
	}


	public PeppolProcessTypeId getPeppolProcessTypeId() {
		return _peppolProcessTypeId;
	}


	public void setPeppolProcessTypeId(PeppolProcessTypeId peppolProcessTypeId) {
		_peppolProcessTypeId = peppolProcessTypeId;
	}


}
