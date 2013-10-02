package nl.kaninefatendreef.si.document;

import nl.kaninefatendreef.si.SIException;

@SuppressWarnings("serial")
public class SIDocumentSenderException extends SIException {

	public SIDocumentSenderException(Throwable  t) {
		super(t);
	}

	public SIDocumentSenderException(String message) {
		super(message);
	}

}
