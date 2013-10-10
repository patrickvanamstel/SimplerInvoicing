package nl.kaninefatendreef.si.server.controller.document;

/*import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
*/
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;



@Component
public class SimplerInvoicingDocumentAssembler extends ResourceAssemblerSupport<SimplerInvoiceDocument, SimplerInvoicingDocumentResource> {

	
	public SimplerInvoicingDocumentAssembler() {
        super(SimplerInvoicingDocumentController.class, SimplerInvoicingDocumentResource.class);
    }

    @Override
    public SimplerInvoicingDocumentResource toResource(SimplerInvoiceDocument simplerInvoiceDocument) {
    	
    	SimplerInvoicingDocumentResource resource = createResourceWithId(simplerInvoiceDocument.getId(), simplerInvoiceDocument);
    	// Deze alleen als object ook echt nog collecties erin heeft
    	//resource.add(linkTo(methodOn(SimplerInvoicingDocumentController.class).getAuthorBooks(author.getAuthorId())).withRel("books"));
        return resource;
    }

    @Override
    protected SimplerInvoicingDocumentResource instantiateResource(SimplerInvoiceDocument simplerInvoiceDocument) {
    	return new SimplerInvoicingDocumentResource(simplerInvoiceDocument);
    }
}
