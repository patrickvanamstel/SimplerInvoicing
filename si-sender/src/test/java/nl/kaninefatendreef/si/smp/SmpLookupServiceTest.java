package nl.kaninefatendreef.si.smp;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;

import nl.kaninefatendreef.si.config.ProxyConfig;
import nl.kaninefatendreef.si.config.SmpConfig;
import nl.kaninefatendreef.si.smp.SmpLookupService;
import nl.kaninefatendreef.si.smp.SmpParticipantNotFoundException;
import nl.kaninefatendreef.si.smp.SmpSignedServiceMetaDataException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.peppol.start.model.ParticipantId;
import eu.peppol.start.model.PeppolDocumentTypeIdAcronym;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SmpConfig.class , ProxyConfig.class})
@Configuration
@PropertySource("SmpTest.properties")
@ActiveProfiles({"proxy" })
public class SmpLookupServiceTest {

	@Autowired
	SmpLookupService _smpLookupService = null;
	
	@Test
	public void lookUpTest(){
		URL endpointAddress = null;
		try {
			
			endpointAddress = _smpLookupService.getEndpointAddress(new ParticipantId("9908:810017902"), PeppolDocumentTypeIdAcronym.INVOICE.getDocumentTypeIdentifier());
		} catch (SmpParticipantNotFoundException
				| SmpSignedServiceMetaDataException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		assertTrue(endpointAddress.toString().startsWith("http"));
	}
	
	@Test(expected = SmpParticipantNotFoundException.class)
	public void lookUpTestNotFound() throws SmpParticipantNotFoundException{
		try {
			 _smpLookupService.getEndpointAddress(new ParticipantId("9913:3019999"), PeppolDocumentTypeIdAcronym.INVOICE.getDocumentTypeIdentifier());
		} catch (SmpSignedServiceMetaDataException e) {
			e.printStackTrace();
		}
	}
	
}
