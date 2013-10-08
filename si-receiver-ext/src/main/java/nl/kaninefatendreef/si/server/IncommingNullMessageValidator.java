package nl.kaninefatendreef.si.server;

import org.springframework.stereotype.Component;


@Component
public class IncommingNullMessageValidator implements IncommingMessageValidator{
	
	public boolean validateIncommingMessage(){
		return false;
	}

	// When null or empty result is valid. Otherwise result is fault
	public String validateRecipientId(String recipientId){
		return null;
	}

	public String validateSenderId(String senderId){
		return null;
	}

	public String validateIncommingDocument(byte[] xmlDocument){
		return null;
	}
	
	
}
