package eu.peppol.start.model;



public class MessageId {

	private String _messageId;

    public String getMessageId() {
		return _messageId;
	}


	public void setMessageId(String messageId) {
	    if (messageId == null) {
            throw new IllegalArgumentException("MessageId requires a non-null string");
        }
		_messageId = messageId;
	}

	public MessageId(String messageId) {
        if (messageId == null) {
            throw new IllegalArgumentException("MessageId requires a non-null string");
        }
        _messageId = messageId;
    }
    
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("MessageId [\n");
    	builder.append("_messageId : " + _messageId + "\n");
    	builder.append("]\n");
        return _messageId;
        
    }
}
