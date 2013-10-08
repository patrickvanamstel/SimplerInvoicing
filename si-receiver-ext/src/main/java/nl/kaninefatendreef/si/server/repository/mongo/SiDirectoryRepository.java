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
	 


	
}
