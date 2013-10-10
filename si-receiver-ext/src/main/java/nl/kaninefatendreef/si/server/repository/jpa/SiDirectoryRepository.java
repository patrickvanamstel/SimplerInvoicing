package nl.kaninefatendreef.si.server.repository.jpa;

import java.io.Serializable;
import org.springframework.stereotype.Repository;


import nl.kaninefatendreef.si.server.model.jpa.SiDirectoryEntry;
import nl.kaninefatendreef.si.server.repository.AbstractSiDirectoryRepository;

@Repository
public interface SiDirectoryRepository <ID extends Serializable>extends AbstractSiDirectoryRepository<SiDirectoryEntry, ID>{

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
