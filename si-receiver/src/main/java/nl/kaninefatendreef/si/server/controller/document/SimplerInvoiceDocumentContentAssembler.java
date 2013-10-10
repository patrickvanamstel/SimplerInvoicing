package nl.kaninefatendreef.si.server.controller.document;


import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class SimplerInvoiceDocumentContentAssembler extends ResourceAssemblerSupport<SimplerInvoiceDocumentContent, SimplerInvoiceDocumentContentResource> {

	

	public SimplerInvoiceDocumentContentAssembler() {
        super(SimplerInvoicingDocumentController.class, SimplerInvoiceDocumentContentResource.class);
    }

    @Override
    public SimplerInvoiceDocumentContentResource toResource(SimplerInvoiceDocumentContent simplerInvoiceDocumentFile) {
    	
    	SimplerInvoiceDocumentContentResource simplerInvoiceDocumentContentResource = new SimplerInvoiceDocumentContentResource();
    	simplerInvoiceDocumentContentResource.setContent(simplerInvoiceDocumentFile.getDocument());
    	return simplerInvoiceDocumentContentResource;
    }

    @Override
    protected SimplerInvoiceDocumentContentResource instantiateResource(SimplerInvoiceDocumentContent simplerInvoiceDocumentContent) {
    	return new SimplerInvoiceDocumentContentResource(simplerInvoiceDocumentContent.getDocument());
    }

}
