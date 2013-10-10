package nl.kaninefatendreef.si.server.repository.jpa;

import java.io.Serializable;
import org.springframework.stereotype.Repository;


import nl.kaninefatendreef.si.server.model.jpa.SimplerInvoicingApplicationUser;
import nl.kaninefatendreef.si.server.repository.AbstractSiDirectoryRepository;

@Repository
public interface SimplerInvoicingApplicationUserRepository <ID extends Serializable>extends AbstractSiDirectoryRepository<SimplerInvoicingApplicationUser, ID>{

	 public SimplerInvoicingApplicationUser findById(String Id);
	
	 public SimplerInvoicingApplicationUser findByUsername(String username);


}
