package nl.kaninefatendreef.si.server.model.mongo;


public class SimplerInvoiceDocumentContent implements nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent{
	
	byte [] document = null;

	public byte[] getDocument() {
		return document;
	}
	public void setDocument(byte[] document) {
		this.document = document;
	}
	

}
