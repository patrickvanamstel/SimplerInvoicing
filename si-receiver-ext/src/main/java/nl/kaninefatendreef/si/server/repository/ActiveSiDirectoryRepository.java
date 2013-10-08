package nl.kaninefatendreef.si.server.repository;

import nl.kaninefatendreef.si.server.model.SiDirectoryEntry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ActiveSiDirectoryRepository implements ApplicationContextAware {

	ApplicationContext _applicationContext = null;
	
	@Autowired(required=false)
	nl.kaninefatendreef.si.server.repository.mongo.SiDirectoryRepository mongoSiDirectoryRepository = null;
	
	@Autowired(required=false)
	nl.kaninefatendreef.si.server.repository.jpa.SiDirectoryRepository<Long>  jpaSiDirectoryRepository = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		_applicationContext = applicationContext;
		
	}
	
	public SiDirectoryEntry createSiIpDirectoryEntry(){

		if (isProfileActive("mongodb")){
			return new nl.kaninefatendreef.si.server.model.mongo.SiDirectoryEntry();	
		}else if (isProfileActive("rdbms")){
			return new nl.kaninefatendreef.si.server.model.jpa.SiDirectoryEntry();
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
	
	public SiDirectoryEntry save (SiDirectoryEntry siIpDirectoryEntry){
		
		if (isProfileActive("mongodb")){
			nl.kaninefatendreef.si.server.model.mongo.SiDirectoryEntry entry = (nl.kaninefatendreef.si.server.model.mongo.SiDirectoryEntry)siIpDirectoryEntry;
			return mongoSiDirectoryRepository.save(entry);	
		}else if (isProfileActive("rdbms")){
			nl.kaninefatendreef.si.server.model.jpa.SiDirectoryEntry entry = (nl.kaninefatendreef.si.server.model.jpa.SiDirectoryEntry)siIpDirectoryEntry;
			return jpaSiDirectoryRepository.save(entry);	
		}else{
			return null;
		}
		
	}


	public SiDirectoryEntry findByKvkNumber(
			String kvkNumber) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByKvkNumber(kvkNumber);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByKvkNumber(kvkNumber);	
		}else{
			return null;
		}
	}

	public SiDirectoryEntry findByBtwNumber(
			String btwNumber) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByBtwNumber(btwNumber);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByBtwNumber(btwNumber);	
		}else{
			return null;
		}
	}

	public SiDirectoryEntry findByOinNumber(
			String oinNumber) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByOinNumber(oinNumber);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByOinNumber(oinNumber);	
		}else{
			return null;
		}
	}

	public SiDirectoryEntry findByExternalReference(
			String externalReference) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByExternalReference(externalReference);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByExternalReference(externalReference);	
		}else{
			return null;
		}
	}

	
	public SiDirectoryEntry findOne(String siIpDirectoryEntryId) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findOne(siIpDirectoryEntryId);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findOne(new Long(siIpDirectoryEntryId));
		}else{
			return null;
		}
		
	}



	public long count() {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.count();	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.count();
		}else{
			return -1;
		}
	}

	public Iterable<?> findAll() {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findAll();
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findAll();
		}else{
			return null;
		}
	}

	public void delete(SiDirectoryEntry siIpDirectoryEntry) {
		if (isProfileActive("mongodb")){
			mongoSiDirectoryRepository.delete(siIpDirectoryEntry.getId());	
		}else if (isProfileActive("rdbms")){
			jpaSiDirectoryRepository.delete(new Long(siIpDirectoryEntry.getId()));
		}
	}


	
}