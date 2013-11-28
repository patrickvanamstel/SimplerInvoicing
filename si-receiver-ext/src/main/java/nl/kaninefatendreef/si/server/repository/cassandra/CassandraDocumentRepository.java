package nl.kaninefatendreef.si.server.repository.cassandra;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

public class CassandraDocumentRepository {

	public SimplerInvoiceDocument save(
			nl.kaninefatendreef.si.server.model.cassandra.SimplerInvoiceDocument cassandraDocument) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<? extends SimplerInvoiceDocument> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<? extends SimplerInvoiceDocument> findByFileName(
			String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	public SimplerInvoiceDocument findOne(String long1) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SimplerInvoiceDocument> findByProcessed(Boolean processedBoolean) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<SimplerInvoiceDocument> findByProcessed(
			Boolean processedBoolean, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterable<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
