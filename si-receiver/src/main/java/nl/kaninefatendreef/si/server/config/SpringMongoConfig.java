package nl.kaninefatendreef.si.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = {"nl.kaninefatendreef.si.server.repository.mongo"})
@Profile("mongodb")
public class SpringMongoConfig {
 
	@Autowired
	Environment environment = null;

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		
		String mongoDbName = environment.getProperty("com.anachron.si.server.db.mongodb.name"); 
		
		if (mongoDbName == null){
			mongoDbName = "si-database";
		}
	
		return new SimpleMongoDbFactory(new MongoClient(), mongoDbName);
	}
 
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
 
	}
 
}