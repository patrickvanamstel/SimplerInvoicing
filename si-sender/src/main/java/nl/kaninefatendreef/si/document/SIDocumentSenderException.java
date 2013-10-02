package nl.kaninefatendreef.si.document;

@SuppressWarnings("serial")
public class SIDocumentSenderException extends Exception {

	public SIDocumentSenderException(Throwable  t) {
		super(t);
	}

	public SIDocumentSenderException(String message) {
		super(message);
	}

}
