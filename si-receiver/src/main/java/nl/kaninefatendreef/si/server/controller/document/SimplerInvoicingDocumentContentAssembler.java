package nl.kaninefatendreef.si.server.controller.document;


import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class SimplerInvoicingDocumentContentAssembler extends ResourceAssemblerSupport<SimplerInvoiceDocumentContent, SimplerInvoicingDocumentContentResource> {

	

	public SimplerInvoicingDocumentContentAssembler() {
        super(SimplerInvoicingDocumentController.class, SimplerInvoicingDocumentContentResource.class);
    }

    @Override
    public SimplerInvoicingDocumentContentResource toResource(SimplerInvoiceDocumentContent simplerInvoiceDocumentFile) {
    	
    	SimplerInvoicingDocumentContentResource simplerInvoiceDocumentContentResource = new SimplerInvoicingDocumentContentResource();
    	simplerInvoiceDocumentContentResource.setContent(simplerInvoiceDocumentFile.getDocument());
    	return simplerInvoiceDocumentContentResource;
    }

    @Override
    protected SimplerInvoicingDocumentContentResource instantiateResource(SimplerInvoiceDocumentContent simplerInvoiceDocumentContent) {
    	return new SimplerInvoicingDocumentContentResource(simplerInvoiceDocumentContent.getDocument());
    }

}
