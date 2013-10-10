package nl.kaninefatendreef.si.server.controller.document;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import nl.kaninefatendreef.si.server.controller.AbstractController;
import nl.kaninefatendreef.si.server.controller.PageResource;
import nl.kaninefatendreef.si.server.controller.PageResourceDomainToResourceAssembler;
import nl.kaninefatendreef.si.server.controller.ResourceNotFoundException;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent;
import nl.kaninefatendreef.si.server.repository.ActiveDocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ExposesResourceFor(SimplerInvoiceDocumentResource.class)
@RequestMapping("/documents")
public class SimplerInvoicingDocumentController extends AbstractController {

	@Autowired
    private ActiveDocumentRepository _documentRepository;
   
	
	
	@Autowired
    private SimplerInvoiceDocumentAssembler _simplerInvoicDocumentResourceAssembler;
	
	@Autowired
    private SimplerInvoiceDocumentContentAssembler _simplerInvoiceDocumentContentAssembler;
	
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createNewSimplerInvoiceDocument(@RequestBody SimplerInvoiceDocument simplerInvoiceDocument) {
    	_documentRepository.save(simplerInvoiceDocument);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(methodOn(getClass()).getSimplerInvoiceDocument(simplerInvoiceDocument.getId())).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
	@ResponseBody 
	public PageResource<SimplerInvoiceDocumentResource> listAsPageResource(
			@RequestParam(required=false,defaultValue="0") int page,
			@RequestParam(required=false,defaultValue="20") int size) {

    	Pageable pageable = new PageRequest(
    		page,size,new Sort("id")
		);
    	
    	Page<SimplerInvoiceDocument> simplerInvoiceDocumentPage = _documentRepository.findAll(pageable);
    	PageResourceDomainToResourceAssembler<SimplerInvoiceDocument , SimplerInvoiceDocumentResource > pageResourceDomainToResourceAssembler = new PageResourceDomainToResourceAssembler<>(simplerInvoiceDocumentPage , _simplerInvoicDocumentResourceAssembler);
    	
    	PageResource<SimplerInvoiceDocumentResource> pr = new PageResource<SimplerInvoiceDocumentResource>(pageResourceDomainToResourceAssembler,"page","size");
    	Link link = linkTo(SimplerInvoicingDocumentController.class).slash("/?fileName=?").withRel("findByFileName");
    	pr.add(link);
    	
    	return pr;
	}
    
    // TODO deze werkt niet
    @RequestMapping(value="//" , method = RequestMethod.GET)
	@ResponseBody 
	public Iterable<SimplerInvoiceDocumentResource> list() {
   	Pageable pageable = new PageRequest(
    		0,20,new Sort("id")
		);
    	
    	Page<SimplerInvoiceDocument> simplerInvoiceDocumentPage = _documentRepository.findAll(pageable);
    	PageResourceDomainToResourceAssembler<SimplerInvoiceDocument , SimplerInvoiceDocumentResource > pageResourceDomainToResourceAssembler = new PageResourceDomainToResourceAssembler<>(simplerInvoiceDocumentPage , _simplerInvoicDocumentResourceAssembler);
    	return new PageResource<SimplerInvoiceDocumentResource>(pageResourceDomainToResourceAssembler,"page","size");
	}
    
    @RequestMapping(method = {RequestMethod.GET} , params="fileName")
    @ResponseBody
    public List<SimplerInvoiceDocumentResource> getSimplerInvoiceDocumentByFileName(@RequestParam(value="fileName") String fileName) {
    	return _simplerInvoicDocumentResourceAssembler.toResources(_documentRepository.findByFileName(fileName));
    }

    @RequestMapping(method = {RequestMethod.GET} , params="processed")
    @ResponseBody
    public PageResource<SimplerInvoiceDocumentResource> getSimplerInvoiceDocumentsProcessed(
    		@RequestParam(value="processed") String processed,
    		@RequestParam(required=false,defaultValue="0") int page,
			@RequestParam(required=false,defaultValue="20") int size) {

    	Pageable pageable = new PageRequest(
        		page,size,new Sort("id")
    		);
        	
    	Boolean processedBoolean = new Boolean(processed);
    	Page<SimplerInvoiceDocument> simplerInvoiceDocumentPage = _documentRepository.findByProcessed(processedBoolean, pageable);
    	PageResourceDomainToResourceAssembler<SimplerInvoiceDocument , SimplerInvoiceDocumentResource > pageResourceDomainToResourceAssembler = new PageResourceDomainToResourceAssembler<>(simplerInvoiceDocumentPage , _simplerInvoicDocumentResourceAssembler);
    	PageResource<SimplerInvoiceDocumentResource> pr = new PageResource<SimplerInvoiceDocumentResource>(pageResourceDomainToResourceAssembler,"page","size");

    	return pr;
    }

    
    @RequestMapping(value = "/{simplerinvoiceDocumentId}", method = RequestMethod.GET)
    @ResponseBody
    public SimplerInvoiceDocumentResource getSimplerInvoiceDocument(@PathVariable("simplerinvoiceDocumentId") String simplerinvoiceDocumentId) {

    	SimplerInvoiceDocument simplerInvoiceDocument = _documentRepository.findOne(simplerinvoiceDocumentId);
    	SimplerInvoiceDocumentResource simplerInvoiceDocumentResource = _simplerInvoicDocumentResourceAssembler.toResource(simplerInvoiceDocument);
    	Link link = linkTo(SimplerInvoicingDocumentController.class).slash(simplerinvoiceDocumentId+ "/content").withRel("content");
    	simplerInvoiceDocumentResource.add(link);
        return simplerInvoiceDocumentResource;
    }
    
    @RequestMapping(value = "/{simplerinvoiceDocumentId}/content", method = RequestMethod.GET)
    @ResponseBody
    public SimplerInvoiceDocumentContentResource getSimplerInvoiceDocumentFile(@PathVariable("simplerinvoiceDocumentId") String simplerinvoiceDocumentId) {
        return _simplerInvoiceDocumentContentAssembler.toResource(findSimplerInvoiceDocumentContent(simplerinvoiceDocumentId));
    }

    private SimplerInvoiceDocumentContent findSimplerInvoiceDocumentContent(String simplerinvoiceDocumentId) {
    	
    	SimplerInvoiceDocument simplerInvoiceDocument = _documentRepository.findOne(simplerinvoiceDocumentId);
    	if (simplerInvoiceDocument == null) {
            throw new ResourceNotFoundException("Unable to SimplerInvoiceDocumument with id=" + simplerinvoiceDocumentId);
        }
        return simplerInvoiceDocument.getContent();
    }


 
    
    
}
