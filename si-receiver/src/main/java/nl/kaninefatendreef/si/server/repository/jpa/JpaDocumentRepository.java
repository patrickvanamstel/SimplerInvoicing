package nl.kaninefatendreef.si.server.repository.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.repository.AbstractDocumentRepository;

@Repository
public interface JpaDocumentRepository <ID extends Serializable>extends AbstractDocumentRepository<SimplerInvoiceDocument, ID>{

	 public SimplerInvoiceDocument findById(ID Id);
	
	 public List<nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> findByFileName(String fileName);
	 public List<nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> findByProcessed(Boolean processed );
	 public Page<nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> findByProcessed(Boolean processed , Pageable page);

}
