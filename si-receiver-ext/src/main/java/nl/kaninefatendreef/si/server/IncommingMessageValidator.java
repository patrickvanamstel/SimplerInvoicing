package nl.kaninefatendreef.si.server;

public interface IncommingMessageValidator {

	public boolean validateIncommingMessage();

	// When null or empty result is valid. Otherwise result is fault
	public String validateRecipientId(String recipientId);

	public String validateSenderId(String senderId);

	public String validateIncommingDocument(byte[] xmlDocument);
	
	
}
