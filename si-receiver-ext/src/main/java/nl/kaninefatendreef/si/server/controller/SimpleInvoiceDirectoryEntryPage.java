package nl.kaninefatendreef.si.server.controller;

import java.util.Iterator;
import java.util.List;

import nl.kaninefatendreef.si.server.model.SiDirectoryEntry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class SimpleInvoiceDirectoryEntryPage implements Page<SiDirectoryEntry>{

	private Page <? extends nl.kaninefatendreef.si.server.model.SiDirectoryEntry> _simplerInvoiceDirectoryEntryPage ;
	
	public SimpleInvoiceDirectoryEntryPage(Page <? extends nl.kaninefatendreef.si.server.model.SiDirectoryEntry> simplerInvoiceDirectroyEntryPage){
		_simplerInvoiceDirectoryEntryPage = simplerInvoiceDirectroyEntryPage;
	}
	
	@Override
	public int getNumber() {
		return _simplerInvoiceDirectoryEntryPage.getNumber();
	}

	@Override
	public int getSize() {
		return _simplerInvoiceDirectoryEntryPage.getSize();
	}

	@Override
	public int getTotalPages() {
		return _simplerInvoiceDirectoryEntryPage.getTotalPages();
	}

	@Override
	public int getNumberOfElements() {
		return _simplerInvoiceDirectoryEntryPage.getNumberOfElements();
	}

	@Override
	public long getTotalElements() {
		return _simplerInvoiceDirectoryEntryPage.getTotalElements();
	}

	@Override
	public boolean hasPreviousPage() {
		return _simplerInvoiceDirectoryEntryPage.hasPreviousPage();
	}

	@Override
	public boolean isFirstPage() {
		return _simplerInvoiceDirectoryEntryPage.isFirstPage();
	}

	@Override
	public boolean hasNextPage() {
		return _simplerInvoiceDirectoryEntryPage.hasNextPage();
	}

	@Override
	public boolean isLastPage() {
		return _simplerInvoiceDirectoryEntryPage.isLastPage();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<SiDirectoryEntry> iterator() {
		return (Iterator<SiDirectoryEntry>) _simplerInvoiceDirectoryEntryPage.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List <SiDirectoryEntry> getContent() {
		return (List<SiDirectoryEntry>) _simplerInvoiceDirectoryEntryPage.getContent();
	}

	@Override
	public boolean hasContent() {
		return _simplerInvoiceDirectoryEntryPage.hasContent();
	}

	@Override
	public Sort getSort() {
		return _simplerInvoiceDirectoryEntryPage.getSort();
	}

}
