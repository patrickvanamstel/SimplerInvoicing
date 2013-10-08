package nl.kaninefatendreef.si.server.controller;

import java.util.Iterator;
import java.util.List;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class SimpleInvoiceDocumentPage implements Page<SimplerInvoiceDocument>{

	private Page <? extends nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> _simplerInvoiceDocumentPage ;
	
	public SimpleInvoiceDocumentPage(Page <? extends nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> simplerInvoiceDocumentPage){
		_simplerInvoiceDocumentPage = simplerInvoiceDocumentPage;
	}
	
	@Override
	public int getNumber() {
		return _simplerInvoiceDocumentPage.getNumber();
	}

	@Override
	public int getSize() {
		return _simplerInvoiceDocumentPage.getSize();
	}

	@Override
	public int getTotalPages() {
		return _simplerInvoiceDocumentPage.getTotalPages();
	}

	@Override
	public int getNumberOfElements() {
		return _simplerInvoiceDocumentPage.getNumberOfElements();
	}

	@Override
	public long getTotalElements() {
		return _simplerInvoiceDocumentPage.getTotalElements();
	}

	@Override
	public boolean hasPreviousPage() {
		return _simplerInvoiceDocumentPage.hasPreviousPage();
	}

	@Override
	public boolean isFirstPage() {
		return _simplerInvoiceDocumentPage.isFirstPage();
	}

	@Override
	public boolean hasNextPage() {
		return _simplerInvoiceDocumentPage.hasNextPage();
	}

	@Override
	public boolean isLastPage() {
		return _simplerInvoiceDocumentPage.isLastPage();
	}

	@Override
	public Iterator<SimplerInvoiceDocument> iterator() {
		return (Iterator<SimplerInvoiceDocument>) _simplerInvoiceDocumentPage.iterator();
	}

	@Override
	public List <SimplerInvoiceDocument> getContent() {
		return (List<SimplerInvoiceDocument>) _simplerInvoiceDocumentPage.getContent();
	}

	@Override
	public boolean hasContent() {
		return _simplerInvoiceDocumentPage.hasContent();
	}

	@Override
	public Sort getSort() {
		return _simplerInvoiceDocumentPage.getSort();
	}

}
