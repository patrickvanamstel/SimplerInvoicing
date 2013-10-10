package nl.kaninefatendreef.si.server.controller.document;

import org.springframework.hateoas.ResourceSupport;


public class SimplerInvoiceDocumentContentResource  extends ResourceSupport{

	byte [] _content = null; 

	public byte[] getContent() {
		return _content;
	}

	public void setContent(byte[] content) {
		_content = content;
	}

	public SimplerInvoiceDocumentContentResource(){
	}
	
	public SimplerInvoiceDocumentContentResource( byte[] content) {
		_content = content;
	}
	
	
}
