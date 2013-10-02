package nl.kaninefatendreef.si.document;

import org.w3c.dom.Document;

public interface SIDocumentValidator {

	void validate(Document document ) throws SIDocumentValidationException;

}
