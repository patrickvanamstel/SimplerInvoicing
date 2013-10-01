package com.anachron.si.smp;

import java.net.URL;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.anachron.si.client.configuration.SIDocumentSenderConfiguration;
import com.anachron.si.client.configuration.SIDocumentSenderSSLConfiguration;

import eu.peppol.start.identifier.ParticipantId;
import eu.peppol.start.identifier.PeppolDocumentTypeIdAcronym;

@ContextConfiguration(classes={SIDocumentSenderSSLConfiguration.class,SIDocumentSenderConfiguration.class})
@ActiveProfiles({"dev" , "FileSystemKeystores"})
@Configuration
@PropertySource("test.properties")
public class SMPManagerTest{

	
	@Test
	public void lookUpTest(){
		System.out.println("Start");
	
		URL endpointAddress;
		
		endpointAddress = new SmpLookupManager().getEndpointAddress(new ParticipantId("9913:30190386"), PeppolDocumentTypeIdAcronym.INVOICE.getDocumentTypeIdentifier());
        
        System.out.println(endpointAddress);
		
		System.out.println("End");
	}
	
	
}
