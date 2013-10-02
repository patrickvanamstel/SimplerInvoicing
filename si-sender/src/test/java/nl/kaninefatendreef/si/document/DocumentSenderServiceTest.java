package nl.kaninefatendreef.si.document;

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
@PropertySource({"DocumentTest.properties" , "file:///secret/si.properties"})
@ActiveProfiles("FileSystemKeystore")
public class DocumentSenderServiceTest {

	@Autowired
	DocumentSenderService _documentSenderService = null;
	
	@Test
	public void sendDocument(){

		
		File testFile = new File("./src/test/resources/nl/kaninefatendreef/si/document/ubl.xml");
				
		//System.setProperty("com.sun.xml.wss.provider.wsit.SecurityTubeFactory.dump", "true");
		FileInputStream inputStream = null;;
		try {
			inputStream = new FileInputStream(testFile);
			SIParticipant endPointSiReceiver = new SIParticipant();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			IOUtils.copy(inputStream, baos);
			
			inputStream.close();
			

			endPointSiReceiver.setDestination(new URL("https://project.anachron.com/oxalis/accessPointService"));
			//endPointSiReceiver.setDestination(new URL("http://localhost:8080/oxalis/accessPointService"));
			//endPointSiReceiver.setDestination(new URL("http://localhost:8080/rest-server/accessPointService"));
			//endPointSiReceiver.setDestination(new URL("http://localhost:8080/eu-peppol-server-console/accessPointService"));
			
			endPointSiReceiver.setChannelId(new ChannelId("CH1"));
			endPointSiReceiver.setParticipantId(new ParticipantId("9908:1000000110"));

			
			
			for (int i = 0 ; i < 20 ; i++){
				
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   Start");
				long startPersistMeasurement1 = System.currentTimeMillis();
				long startPersistMeasurement2 = 0 ;
				startPersistMeasurement2 = System.currentTimeMillis();
				System.out.println("Start ddddddddddddddddd " + i);
				ByteArrayInputStream bin = new ByteArrayInputStream(baos.toByteArray());
				SIDocumentSenderResult result = _documentSenderService.send(bin, endPointSiReceiver);
				long endPersistMeasurement = System.currentTimeMillis();
				 
				System.out.println("Persisting took (i)" + i + ":" + (startPersistMeasurement2 - startPersistMeasurement1) + "," + (endPersistMeasurement - startPersistMeasurement2 )  );

			}
			
		} catch (FileNotFoundException | SIDocumentSenderException | MalformedURLException e) {
			e.printStackTrace();
			fail(e.toString());
		} catch (Throwable t){
			t.printStackTrace();
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
