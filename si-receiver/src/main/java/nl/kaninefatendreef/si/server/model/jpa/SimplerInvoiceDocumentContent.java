package nl.kaninefatendreef.si.server.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SIMPLER_INVOICE_DOCUMENT_CONTENT")
public class SimplerInvoiceDocumentContent implements nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent{

	@Id
	@Column(name="SIMPLER_INVOICE_DOCUMENT_CONTENT_ID", unique=true, nullable=false)
	@GeneratedValue
    private Long id;
	
	
	@Column(name = "DOCUMENT")
	byte [] document = null;

	public byte[] getDocument() {
		return document;
	}
	public void setDocument(byte[] document) {
		this.document = document;
	}
	

	
}
