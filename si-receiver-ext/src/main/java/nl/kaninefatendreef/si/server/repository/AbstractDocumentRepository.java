package nl.kaninefatendreef.si.server.repository;

import java.io.Serializable;
import java.util.List;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface AbstractDocumentRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	

	
	 public List<SimplerInvoiceDocument> findByFileName(String fileName);
	 public List<SimplerInvoiceDocument> findByProcessed(Boolean processed );
	 public Page<SimplerInvoiceDocument> findByProcessed(Boolean processed , Pageable page);

	

}
