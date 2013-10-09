package nl.kaninefatendreef.si.server.repository.mongo;

import nl.kaninefatendreef.si.server.model.mongo.SiDirectoryEntry;
import nl.kaninefatendreef.si.server.repository.AbstractDocumentRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface SiDirectoryRepository extends AbstractDocumentRepository<SiDirectoryEntry, String>
{

	 public SiDirectoryEntry findById(String Id);
	
	 public SiDirectoryEntry findByKvkNumber(String kvkNumber);
	 public SiDirectoryEntry findByOinNumber(String oinNumber);
	 public SiDirectoryEntry findByBtwNumber(String btwNumber);
	 public SiDirectoryEntry findByExternalReference(String externalReference);
	 
	 public SiDirectoryEntry findByNonTypedEntry1(String nonTypedEntry1);
	 public SiDirectoryEntry findByNonTypedEntry2(String nonTypedEntry2);
	 public SiDirectoryEntry findByNonTypedEntry3(String nonTypedEntry3);
	 public SiDirectoryEntry findByNonTypedEntry4(String nonTypedEntry4);
	 public SiDirectoryEntry findByNonTypedEntry5(String nonTypedEntry5);


	
}
