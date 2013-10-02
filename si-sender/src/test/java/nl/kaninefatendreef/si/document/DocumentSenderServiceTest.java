package nl.kaninefatendreef.si.document;

import nl.kaninefatendreef.si.config.SmpConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SmpConfig.class})
@Configuration
@PropertySource("DocumentTest.properties")
public class DocumentSenderServiceTest {

	@Autowired
	DocumentSenderService _documentSenderService = null;
	
	@Test
	public void sendDocument(){

	
	}
	
	
}
