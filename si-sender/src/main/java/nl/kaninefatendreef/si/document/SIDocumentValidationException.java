package nl.kaninefatendreef.si.document;

import nl.kaninefatendreef.si.SIException;

@SuppressWarnings("serial")
public class SIDocumentValidationException extends SIException {

	public SIDocumentValidationException(String message) {
		super(message);
	}
	
	public SIDocumentValidationException(Throwable t) {
		super(t);
	}

	public SIDocumentValidationException(String message , Throwable t) {
		super(message , t);
	}

}
