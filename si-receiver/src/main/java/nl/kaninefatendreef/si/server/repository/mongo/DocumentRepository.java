package nl.kaninefatendreef.si.server.repository.mongo;

import java.util.List;
import java.util.UUID;

import nl.kaninefatendreef.si.server.model.mongo.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.repository.AbstractDocumentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends AbstractDocumentRepository<SimplerInvoiceDocument, String>
{

	 public SimplerInvoiceDocument findById(String Id);
	 //public SimplerInvoiceDocument findByUuid(UUID uuid);
	
	 public List<nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> findByFileName(String fileName);
	 public List<nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> findByProcessed(Boolean processed );
	 public Page<nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument> findByProcessed(Boolean processed , Pageable page);


	
}
