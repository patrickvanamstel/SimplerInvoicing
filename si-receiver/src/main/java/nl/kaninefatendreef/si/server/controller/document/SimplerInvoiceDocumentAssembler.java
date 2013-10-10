package nl.kaninefatendreef.si.server.controller.document;

/*import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
*/
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;



@Component
public class SimplerInvoiceDocumentAssembler extends ResourceAssemblerSupport<SimplerInvoiceDocument, SimplerInvoiceDocumentResource> {

	
	public SimplerInvoiceDocumentAssembler() {
        super(SimplerInvoicingDocumentController.class, SimplerInvoiceDocumentResource.class);
    }

    @Override
    public SimplerInvoiceDocumentResource toResource(SimplerInvoiceDocument simplerInvoiceDocument) {
    	
    	SimplerInvoiceDocumentResource resource = createResourceWithId(simplerInvoiceDocument.getId(), simplerInvoiceDocument);
    	// Deze alleen als object ook echt nog collecties erin heeft
    	//resource.add(linkTo(methodOn(SimplerInvoicingDocumentController.class).getAuthorBooks(author.getAuthorId())).withRel("books"));
        return resource;
    }

    @Override
    protected SimplerInvoiceDocumentResource instantiateResource(SimplerInvoiceDocument simplerInvoiceDocument) {
    	return new SimplerInvoiceDocumentResource(simplerInvoiceDocument);
    }
}
