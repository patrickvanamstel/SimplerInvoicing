package nl.kaninefatendreef.si.server.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Maps a Pageable Domain Object to a Pageable Resource Object.
 * 
 * @author Patrick van Amstel
 *
 * @param <Y> - Domain object
 * @param <T> - Resource object
 */
public class PageResourceDomainToResourceAssembler<Y, T extends ResourceSupport> implements Page<T>{

	Page<Y> _originalPage;
	ResourceAssemblerSupport<Y, T> _resourceAssemblerSupport = null;
	
	public PageResourceDomainToResourceAssembler(Page<Y> pageOriginal , ResourceAssemblerSupport<Y, T> ra){
		_originalPage = pageOriginal;
		_resourceAssemblerSupport = ra;
	}
	
	@Override
	public int getNumber() {
		return _originalPage.getNumber();
	}

	@Override
	public int getSize() {
		return _originalPage.getSize();
	}

	@Override
	public int getTotalPages() {
		return _originalPage.getTotalPages();
	}

	@Override
	public int getNumberOfElements() {
		return _originalPage.getNumberOfElements();
	}

	@Override
	public long getTotalElements() {
		return _originalPage.getTotalElements();
	}

	@Override
	public boolean hasPreviousPage() {
		return _originalPage.hasPreviousPage();
	}

	@Override
	public boolean isFirstPage() {
		return _originalPage.isFirstPage();
	}

	@Override
	public boolean hasNextPage() {
		return _originalPage.hasNextPage();
	}

	@Override
	public boolean isLastPage() {
		return _originalPage.isLastPage();
	}

	@Override
	public Iterator<T> iterator() {

		return getContent().iterator();
	}

	@Override
	public List<T> getContent() {
		
		ArrayList<T> list = new ArrayList<>();
		for (Y y : _originalPage.getContent())
		{
			list.add(_resourceAssemblerSupport.toResource(y));
		}
		return list;
	}

	@Override
	public boolean hasContent() {
		return _originalPage.hasContent();
	}

	@Override
	public Sort getSort() {
		return _originalPage.getSort();
	}

	
	
	
}
