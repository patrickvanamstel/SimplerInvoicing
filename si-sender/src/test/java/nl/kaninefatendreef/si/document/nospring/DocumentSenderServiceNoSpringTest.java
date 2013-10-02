package nl.kaninefatendreef.si.document.nospring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import nl.kaninefatendreef.si.config.SIApplicationContext;
import nl.kaninefatendreef.si.constant.SIConfigurationProperties;
import nl.kaninefatendreef.si.document.BlackListService;
import nl.kaninefatendreef.si.document.DocumentSenderService;
import nl.kaninefatendreef.si.document.SIDocumentSenderException;
import nl.kaninefatendreef.si.document.SIDocumentSenderResult;
import nl.kaninefatendreef.si.document.SIParticipant;
import nl.kaninefatendreef.si.document.SIProxy;
import nl.kaninefatendreef.si.document.SISoapProxy;
import nl.kaninefatendreef.si.ssl.FileSystemKeyStoreManager;
import nl.kaninefatendreef.si.ssl.FileSystemTrustStoreManager;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.peppol.start.model.ChannelId;
import eu.peppol.start.model.ParticipantId;

public class DocumentSenderServiceNoSpringTest {

	
	static DocumentSenderService _documentSenderService = null;
	
	@BeforeClass
	public static void initTest(){

		// When debugging line below is handy
		
		Properties environment = new Properties();
		try {
			environment.load(new FileInputStream("/secret/si.properties"));
		} catch (IOException e) {
			fail(e.getMessage());
		} 
		
		
		FileSystemTrustStoreManager trustStoreManager = new FileSystemTrustStoreManager();
		trustStoreManager.setPassword(environment.getProperty(SIConfigurationProperties.SI_TRUSTSTORE_PASS.getValue()));
		trustStoreManager.setKeyStoreFile(new File(environment.getProperty(SIConfigurationProperties.SI_TRUSTSTORE_FILE.getValue())));
		
		
		FileSystemKeyStoreManager keyStoreManager = new FileSystemKeyStoreManager();
		keyStoreManager.setPassword(environment.getProperty(SIConfigurationProperties.SI_KEYSTORE_PASS.getValue()));
		keyStoreManager.setKeyStoreFile(new File(environment.getProperty(SIConfigurationProperties.SI_KEYSTORE_FILE.getValue())));
		
		SIApplicationContext.setTrustStoreManager(trustStoreManager);
		SIApplicationContext.setKeyStoreManager(keyStoreManager);

		BlackListService blackListService = new BlackListService();
		SISoapProxy sISoapProxy = new SISoapProxy();
		sISoapProxy.setBlackListService(blackListService);
		try {
			sISoapProxy.afterPropertiesSet();
		} catch (Exception e) {
			fail(e.toString());
		}
		
		_documentSenderService = new DocumentSenderService();
		
		_documentSenderService.setSiSoapProxy(sISoapProxy);
		
		ParticipantId senderId = new ParticipantId("9908:1000000110");
		SIParticipant participant = new SIParticipant();
		participant.setParticipantId(senderId);
		
		_documentSenderService.setEndPointSiSender(participant);
		
		
		
	}
	
	//This is not a unit test, but an integration test
	@Test
	public void sendDocument(){
		
		File testFile = new File("./src/test/resources/nl/kaninefatendreef/si/document/ubl.xml");
		
		FileInputStream inputStream = null;;
		try {
			inputStream = new FileInputStream(testFile);
			SIParticipant endPointSiReceiver = new SIParticipant();

			// Copy bytes into memory for fast test
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(inputStream, baos);
			inputStream.close();
			
			// This is not a unit test url
			endPointSiReceiver.setDestination(new URL("https://project.anachron.com/oxalis/accessPointService"));
			endPointSiReceiver.setChannelId(new ChannelId("CH1"));
			endPointSiReceiver.setParticipantId(new ParticipantId("9908:1000000110"));

			ByteArrayInputStream bin = new ByteArrayInputStream(baos.toByteArray());
			SIDocumentSenderResult result = _documentSenderService.send(bin, endPointSiReceiver);

			assertNotNull(result.getMessageId().getMessageId());
			
		} catch (FileNotFoundException | SIDocumentSenderException | MalformedURLException e) {
			fail(e.toString());
		} catch (Throwable t){
			fail(t.toString());
		}finally{
			if (inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					fail(e.toString());
				}
			}
		}
	
	}
	
	
}
