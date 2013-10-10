package nl.kaninefatendreef.si.server.model;

import java.util.Iterator;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class SimpleInvoiceApplicationUserPage implements Page<SimplerInvoicingApplicationUser>{

	private Page <? extends nl.kaninefatendreef.si.server.model.SimplerInvoicingApplicationUser> _simplerInvoiceApplicationUserPage ;
	
	public SimpleInvoiceApplicationUserPage(Page <? extends nl.kaninefatendreef.si.server.model.SimplerInvoicingApplicationUser> simplerInvoiceDirectroyEntryPage){
		_simplerInvoiceApplicationUserPage = simplerInvoiceDirectroyEntryPage;
	}
	
	@Override
	public int getNumber() {
		return _simplerInvoiceApplicationUserPage.getNumber();
	}

	@Override
	public int getSize() {
		return _simplerInvoiceApplicationUserPage.getSize();
	}

	@Override
	public int getTotalPages() {
		return _simplerInvoiceApplicationUserPage.getTotalPages();
	}

	@Override
	public int getNumberOfElements() {
		return _simplerInvoiceApplicationUserPage.getNumberOfElements();
	}

	@Override
	public long getTotalElements() {
		return _simplerInvoiceApplicationUserPage.getTotalElements();
	}

	@Override
	public boolean hasPreviousPage() {
		return _simplerInvoiceApplicationUserPage.hasPreviousPage();
	}

	@Override
	public boolean isFirstPage() {
		return _simplerInvoiceApplicationUserPage.isFirstPage();
	}

	@Override
	public boolean hasNextPage() {
		return _simplerInvoiceApplicationUserPage.hasNextPage();
	}

	@Override
	public boolean isLastPage() {
		return _simplerInvoiceApplicationUserPage.isLastPage();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<SimplerInvoicingApplicationUser> iterator() {
		return (Iterator<SimplerInvoicingApplicationUser>) _simplerInvoiceApplicationUserPage.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List <SimplerInvoicingApplicationUser> getContent() {
		return (List<SimplerInvoicingApplicationUser>) _simplerInvoiceApplicationUserPage.getContent();
	}

	@Override
	public boolean hasContent() {
		return _simplerInvoiceApplicationUserPage.hasContent();
	}

	@Override
	public Sort getSort() {
		return _simplerInvoiceApplicationUserPage.getSort();
	}

}
