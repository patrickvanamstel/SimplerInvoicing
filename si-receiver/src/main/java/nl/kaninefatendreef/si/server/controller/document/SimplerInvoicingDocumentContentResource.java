package nl.kaninefatendreef.si.server.controller.document;

import org.springframework.hateoas.ResourceSupport;


public class SimplerInvoicingDocumentContentResource  extends ResourceSupport{

	byte [] _content = null; 

	public byte[] getContent() {
		return _content;
	}

	public void setContent(byte[] content) {
		_content = content;
	}

	public SimplerInvoicingDocumentContentResource(){
	}
	
	public SimplerInvoicingDocumentContentResource( byte[] content) {
		_content = content;
	}
	
	
}
