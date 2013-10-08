package nl.kaninefatendreef.si.server.repository.jpa;

import java.io.Serializable;
import org.springframework.stereotype.Repository;


import nl.kaninefatendreef.si.server.model.jpa.SiDirectoryEntry;
import nl.kaninefatendreef.si.server.repository.AbstractSiDirectoryRepository;

@Repository
public interface SiDirectoryRepository <ID extends Serializable>extends AbstractSiDirectoryRepository<SiDirectoryEntry, ID>{

	 public SiDirectoryEntry findById(ID Id);
	
	 public SiDirectoryEntry findByKvkNumber(String kvkNumber);
	 public SiDirectoryEntry findByOinNumber(String oinNumber);
	 public SiDirectoryEntry findByBtwNumber(String btwNumber);
	 public SiDirectoryEntry findByExternalReference(String ipReference);


}
