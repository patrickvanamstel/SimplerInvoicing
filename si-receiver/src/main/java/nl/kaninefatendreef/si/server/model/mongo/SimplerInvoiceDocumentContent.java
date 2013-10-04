package nl.kaninefatendreef.si.server.model.mongo;



public class SimplerInvoiceDocumentContent implements nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent{

	
	byte [] _document = null;

	public byte[] getDocument() {
		return _document;
	}
	public void setDocument(byte[] document) {
		_document = document;
	}
	

}
