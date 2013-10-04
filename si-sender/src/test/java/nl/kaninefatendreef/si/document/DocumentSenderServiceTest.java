package nl.kaninefatendreef.si.document;

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

import nl.kaninefatendreef.si.config.DocumentConfig;
import nl.kaninefatendreef.si.config.SSLFilesystemConfig;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.peppol.start.model.ChannelId;
import eu.peppol.start.model.ParticipantId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DocumentConfig.class, SSLFilesystemConfig.class})
@Configuration
@PropertySource({"DocumentTest.properties" , "file:///secret/si.properties"}) // secret properties are properties of the keystores. They do not belong in a public repo
@ActiveProfiles("FileSystemKeystore")
public class DocumentSenderServiceTest {

	@Autowired
	DocumentSenderService _documentSenderService = null;

	
	@BeforeClass
	public static void initTest(){
		// When debugging line below is handy
		//System.setProperty("com.sun.xml.wss.provider.wsit.SecurityTubeFactory.dump", "true");
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
			//endPointSiReceiver.setDestination(new URL("https://project.anachron.com/oxalis/accessPointService"));
			endPointSiReceiver.setDestination(new URL("http://localhost:8080/si-receiver/accessPointService"));
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
