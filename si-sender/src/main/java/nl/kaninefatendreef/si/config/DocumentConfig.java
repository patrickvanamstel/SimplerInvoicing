package nl.kaninefatendreef.si.config;

import nl.kaninefatendreef.si.document.SIParticipant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import eu.peppol.start.model.ParticipantId;



@Configuration
@ComponentScan(basePackages={"nl.kaninefatendreef.si.document","nl.kaninefatendreef.si.config"})
public class DocumentConfig {

	@Autowired
	Environment environment;
	
	@Bean (name = "ParticipantSenderId")
	public SIParticipant siSenderParticipant() {
		
		SIParticipant siParticipant = new SIParticipant();
		
		ParticipantId participantId = new ParticipantId(environment.getProperty("nl.kaninefatendreef.si.sender.id"));
		siParticipant.setParticipantId(participantId);
		siParticipant.setParticipantId(participantId);

		return siParticipant;
	}	 
	 
	 
}
