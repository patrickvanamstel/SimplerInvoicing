package nl.kaninefatendreef.si.server.repository;

import nl.kaninefatendreef.si.server.model.SimpleInvoiceApplicationUserPage;
import nl.kaninefatendreef.si.server.model.SimplerInvoicingApplicationUser;
import nl.kaninefatendreef.si.server.repository.jpa.SimplerInvoicingApplicationUserRepository;
import nl.kaninefatendreef.si.server.ssl.SimplerInvoicingUserApplicationPasswordHash;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class ActiveSimplerInvoicingApplicationUserRepository implements ApplicationContextAware {

	ApplicationContext _applicationContext = null;
	
	@Autowired(required=false)
	SimplerInvoicingApplicationUserRepository<Long> jpaSimplerInvoicingApplicationUserRepository = null;
	
	@Autowired(required=false)
	nl.kaninefatendreef.si.server.repository.mongo.SimplerInvoicingApplicationUserRepository mongoSimplerInvoicingApplicationUserRepository = null;

	@Autowired(required=false)
	nl.kaninefatendreef.si.server.repository.cassandra.SimplerInvoicingApplicationUserRepository cassandraSimplerInvoicingApplicationUserRepository = null;

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		_applicationContext = applicationContext;
		
	}
	
	public SimplerInvoicingApplicationUser createSimplerInvoicingApplicationUser(){

		if (isProfileActive("mongodb")){
			return new nl.kaninefatendreef.si.server.model.mongo.SimplerInvoicingApplicationUser();	
		}else if (isProfileActive("rdbms")){
			return new nl.kaninefatendreef.si.server.model.jpa.SimplerInvoicingApplicationUser();
		}else if (isProfileActive("cassandra")){
			return new nl.kaninefatendreef.si.server.model.cassandra.SimplerInvoicingApplicationUser();
		}else{
			return null;
		}
	}

	private boolean isProfileActive(String profile){
		
		for (String profileInList : _applicationContext.getEnvironment().getActiveProfiles()){
			if (profileInList.equals(profile)){
				return true;
			}
		}
		return false;
	}
	
	public SimplerInvoicingApplicationUser save (SimplerInvoicingApplicationUser user){
		
		SimplerInvoicingApplicationUser userObject = createSimplerInvoicingApplicationUser();
		
		if (user.getId() != null){
			userObject.setId(user.getId());
		}
		
		userObject.setUsername(user.getUsername());
		
		if (user.getPassword() != null){
			userObject.setPassword(SimplerInvoicingUserApplicationPasswordHash.hash(user.getPassword())); 
		}
		
		userObject.setRoles(user.getRoles());
		
		if (isProfileActive("mongodb")){
			nl.kaninefatendreef.si.server.model.mongo.SimplerInvoicingApplicationUser mongoDocument = (nl.kaninefatendreef.si.server.model.mongo.SimplerInvoicingApplicationUser)userObject;
			return mongoSimplerInvoicingApplicationUserRepository.save(mongoDocument);	
		}else if (isProfileActive("rdbms")){
			nl.kaninefatendreef.si.server.model.jpa.SimplerInvoicingApplicationUser mongoDocument = (nl.kaninefatendreef.si.server.model.jpa.SimplerInvoicingApplicationUser)userObject;
			return jpaSimplerInvoicingApplicationUserRepository.save(mongoDocument);	
		}else if (isProfileActive("cassandra")){
			nl.kaninefatendreef.si.server.model.cassandra.SimplerInvoicingApplicationUser cassandraDocument = (nl.kaninefatendreef.si.server.model.cassandra.SimplerInvoicingApplicationUser)userObject;
			return cassandraSimplerInvoicingApplicationUserRepository.save(cassandraDocument);	
		}else{
			return null;
		}
		
	}

	public Page<SimplerInvoicingApplicationUser> findAll(Pageable pageable) {
		if (isProfileActive("mongodb")){
			return new SimpleInvoiceApplicationUserPage(mongoSimplerInvoicingApplicationUserRepository.findAll( pageable));
		}else if (isProfileActive("rdbms")){
			return new SimpleInvoiceApplicationUserPage(jpaSimplerInvoicingApplicationUserRepository.findAll( pageable));
		}else if (isProfileActive("cassandra")){
			return new SimpleInvoiceApplicationUserPage(cassandraSimplerInvoicingApplicationUserRepository.findAll( pageable));
		}else{
			return null;
		}
	}

	public SimplerInvoicingApplicationUser findByUsername(
			String user) {
		if (isProfileActive("mongodb")){
			return mongoSimplerInvoicingApplicationUserRepository.findByUsername(user);	
		}else if (isProfileActive("rdbms")){
			return jpaSimplerInvoicingApplicationUserRepository.findByUsername(user);
		}else if (isProfileActive("cassandra")){
			return cassandraSimplerInvoicingApplicationUserRepository.findByUsername(user);
		}else{
			return null;
		}
	}

	public SimplerInvoicingApplicationUser findOne(String userId) {
		if (isProfileActive("mongodb")){
			return mongoSimplerInvoicingApplicationUserRepository.findOne(userId);	
		}else if (isProfileActive("rdbms")){
			return jpaSimplerInvoicingApplicationUserRepository.findOne(new Long(userId));
		}else if (isProfileActive("cassandra")){
			return cassandraSimplerInvoicingApplicationUserRepository.findOne(userId);
		}else{
			return null;
		}
	}


	

	public long count() {
		if (isProfileActive("mongodb")){
			return mongoSimplerInvoicingApplicationUserRepository.count();	
		}else if (isProfileActive("rdbms")){
			return jpaSimplerInvoicingApplicationUserRepository.count();
		}else if (isProfileActive("cassandra")){
			return cassandraSimplerInvoicingApplicationUserRepository.count();
		}else{
			return -1;
		}
	}

	public Iterable<?> findAll() {
		if (isProfileActive("mongodb")){
			return mongoSimplerInvoicingApplicationUserRepository.findAll();
		}else if (isProfileActive("rdbms")){
			return jpaSimplerInvoicingApplicationUserRepository.findAll();
		}else if (isProfileActive("cassandra")){
			return cassandraSimplerInvoicingApplicationUserRepository.findAll();
		}else{
			return null;
		}
	}

	public void delete(SimplerInvoicingApplicationUser user) {
		if (isProfileActive("mongodb")){
			mongoSimplerInvoicingApplicationUserRepository.delete(user.getId());	
		}else if (isProfileActive("rdbms")){
			jpaSimplerInvoicingApplicationUserRepository.delete(new Long(user.getId()));
		}else if (isProfileActive("cassandra")){
			cassandraSimplerInvoicingApplicationUserRepository.delete(user.getId());
		}
	}


	
}
