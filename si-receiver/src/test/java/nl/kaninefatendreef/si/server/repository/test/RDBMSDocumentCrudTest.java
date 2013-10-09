package nl.kaninefatendreef.si.server.repository.test;

import static org.junit.Assert.*;
import nl.kaninefatendreef.si.server.config.SpringMongoConfig;
import nl.kaninefatendreef.si.server.config.SpringRDBMSConfig;
import nl.kaninefatendreef.si.server.config.SpringServerContext;
import nl.kaninefatendreef.si.server.model.SiDirectoryEntry;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent;
import nl.kaninefatendreef.si.server.repository.ActiveDocumentRepository;
import nl.kaninefatendreef.si.server.repository.ActiveSiDirectoryRepository;
import nl.kaninefatendreef.si.server.repository.mongo.DocumentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes={ SpringRDBMSConfig.class , SpringTestConfig.class})
@ActiveProfiles({"rdbms"})
@Configuration
@PropertySource({"postgresstest.properties"}) 
//@Transactional(propagation=Propagation.REQUIRED)
public class RDBMSDocumentCrudTest {
	
	@Autowired
	ActiveDocumentRepository activeDocumentRepository;
	
	
	@Autowired
	ActiveSiDirectoryRepository activeSiDirectoryRepository;
	
	@Test
	public void entryTest(){
		
		System.out.println("Start");
	
		SiDirectoryEntry entry = activeSiDirectoryRepository.createSiIpDirectoryEntry();
		entry.setBtwNumber("BTW*#&#*");
		entry.setOinNumber("XXX983");
		entry.setExternalReference("TestEenIdKnownbyIP");
		entry.setKvKNumber("KvK38737");
		
		try{
		
		activeSiDirectoryRepository.save(entry);
		}catch (Throwable t){
			t.printStackTrace();
			throw (t);
		}
		
	}
	
	//@Test
	public void initTest(){

		System.out.println("---------------------- Start");
		
//		SimplerInvoiceDocument document = new SimplerInvoiceDocument();
//		document.setChannelId("EenChannelId");
//		document.setProcessed(true);
//		documentRepository.save(document);
		
			System.out.println("---------------------- " + activeDocumentRepository.count());
			
			SimplerInvoiceDocument simplerInvoicingMessage = activeDocumentRepository.createSimplerInvoiceDocument();
			nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocumentContent simplerInvoicingMessageContent = (nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocumentContent)activeDocumentRepository.createSimplerInvoiceDocumentContent();
			simplerInvoicingMessageContent.setDocument("Dit is een test".getBytes());

//			simplerInvoicingMessageContent.setSimplerInvoiceDocument((nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocument)simplerInvoicingMessage);
			
			simplerInvoicingMessage.setContent(simplerInvoicingMessageContent);
            simplerInvoicingMessage.setFileName("messageFileName");
            simplerInvoicingMessage.setMessageId("peppolMessageHeader.getMessageId().getMessageId()");
            simplerInvoicingMessage.setChannelId("peppolMessageHeader.getChannelId().getChannelId()");
            simplerInvoicingMessage.setSenderId("peppolMessageHeader.getSenderId().stringValue()");
            simplerInvoicingMessage.setRecipientId("peppolMessageHeader.getRecipientId().stringValue()");
            simplerInvoicingMessage.setAccessPointPrinciple("peppolMessageHeader.getRemoteAccessPointPrincipal().getName()");

            simplerInvoicingMessage.setDocumentId("peppolMessageHeader.getDocumentTypeIdentifier().stringValue()");
            simplerInvoicingMessage.setProcessId("peppolMessageHeader.getPeppolProcessTypeId().stringValue()");
            simplerInvoicingMessage.setRemoteHost("peppolMessageHeader.getRemoteHost()");
            
            simplerInvoicingMessage.setProcessed(false);
            simplerInvoicingMessage.setCreateTimeInMs(System.currentTimeMillis());
            simplerInvoicingMessage.setUpdateTimeInMs(System.currentTimeMillis());
			
            SimplerInvoiceDocument savedDoc = activeDocumentRepository.save(simplerInvoicingMessage);
            
            
            System.out.println(savedDoc.getId());
            
            SimplerInvoiceDocument message = activeDocumentRepository.findOne(savedDoc.getId());
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println(message.getId());
            System.out.println(message.getContent());
            assertNotNull(message.getContent().getDocument());
            assertTrue(message.getContent().getDocument().length > 0);
            System.out.println(message.getId() + message.getContent().getDocument());
            System.out.println(message.getContent().getDocument().length);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            
			

		
		System.out.println("---------------------- Start");
		System.out.println("End");
		
	}
	
	
}
