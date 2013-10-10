package nl.kaninefatendreef.si.server.repository.mongo;

import nl.kaninefatendreef.si.server.model.mongo.SimplerInvoicingApplicationUser;
import nl.kaninefatendreef.si.server.repository.AbstractDocumentRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface SimplerInvoicingApplicationUserRepository extends AbstractDocumentRepository<SimplerInvoicingApplicationUser, String>
{

	 public SimplerInvoicingApplicationUser findById(String Id);
	 public SimplerInvoicingApplicationUser findByUsername(String username);

	
}
