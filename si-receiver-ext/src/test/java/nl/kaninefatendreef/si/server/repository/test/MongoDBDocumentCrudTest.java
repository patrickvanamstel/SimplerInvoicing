package nl.kaninefatendreef.si.server.repository.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.UUID;

import nl.kaninefatendreef.si.server.config.SpringMongoConfig;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent;
import nl.kaninefatendreef.si.server.repository.ActiveDocumentRepository;
import nl.kaninefatendreef.si.server.repository.mongo.DocumentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes={ SpringMongoConfig.class , SpringTestConfig.class})
@Configuration
@PropertySource({"mongotest.propeties"}) 
@ActiveProfiles({"mongodb"})
public class MongoDBDocumentCrudTest {


	@Autowired
	ActiveDocumentRepository activeRepository;
	
	@Test
	public void init(){
		
		SimplerInvoiceDocument doc2 = (SimplerInvoiceDocument) activeRepository.createSimplerInvoiceDocument();
		SimplerInvoiceDocumentContent doc2Content = (SimplerInvoiceDocumentContent) activeRepository.createSimplerInvoiceDocumentContent();
		doc2Content.setDocument("hallo".getBytes());
		doc2.setChannelId("Iets");
		doc2.setContent(doc2Content);
		SimplerInvoiceDocument d = activeRepository.save(doc2);
		assertNotNull(activeRepository.findOne(d.getId()));
		
		
		
		
	}
	
		
}
