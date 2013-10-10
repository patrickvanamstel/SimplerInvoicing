package nl.kaninefatendreef.si.server.repository;


import nl.kaninefatendreef.si.server.model.SimpleInvoiceDirectoryEntryPage;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDirectoryEntry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public SimplerInvoiceDirectoryEntry createSiIpDirectoryEntry(){

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
	
	public SimplerInvoiceDirectoryEntry save (SimplerInvoiceDirectoryEntry siIpDirectoryEntry){

		SimplerInvoiceDirectoryEntry siDirectoryEntry = createSiIpDirectoryEntry();
		siDirectoryEntry.setId(siIpDirectoryEntry.getId());
		siDirectoryEntry.setBtwNumber(siIpDirectoryEntry.getBtwNumber());
		siDirectoryEntry.setOinNumber(siIpDirectoryEntry.getOinNumber());
		siDirectoryEntry.setKvKNumber(siIpDirectoryEntry.getKvKNumber());
		siDirectoryEntry.setExternalReference(siIpDirectoryEntry.getExternalReference());
		siDirectoryEntry.setNonTypedEntry1(siIpDirectoryEntry.getNonTypedEntry1());
		siDirectoryEntry.setNonTypedEntry2(siIpDirectoryEntry.getNonTypedEntry2());
		siDirectoryEntry.setNonTypedEntry3(siIpDirectoryEntry.getNonTypedEntry3());
		siDirectoryEntry.setNonTypedEntry4(siIpDirectoryEntry.getNonTypedEntry4());
		siDirectoryEntry.setNonTypedEntry5(siIpDirectoryEntry.getNonTypedEntry5());

		//TODO
		// Resource mapper
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.save((nl.kaninefatendreef.si.server.model.mongo.SiDirectoryEntry)siDirectoryEntry);	
		}else if (isProfileActive("rdbms")){
			
			
			
			return jpaSiDirectoryRepository.save((nl.kaninefatendreef.si.server.model.jpa.SiDirectoryEntry)siDirectoryEntry);	
		}else{
			return null;
		}
		
	}


	public SimplerInvoiceDirectoryEntry findByKvkNumber(
			String kvkNumber) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByKvkNumber(kvkNumber);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByKvkNumber(kvkNumber);	
		}else{
			return null;
		}
	}

	public SimplerInvoiceDirectoryEntry findByBtwNumber(
			String btwNumber) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByBtwNumber(btwNumber);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByBtwNumber(btwNumber);	
		}else{
			return null;
		}
	}

	public SimplerInvoiceDirectoryEntry findByOinNumber(
			String oinNumber) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByOinNumber(oinNumber);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByOinNumber(oinNumber);	
		}else{
			return null;
		}
	}

	public SimplerInvoiceDirectoryEntry findByExternalReference(
			String externalReference) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findByExternalReference(externalReference);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findByExternalReference(externalReference);	
		}else{
			return null;
		}
	}

	
	public SimplerInvoiceDirectoryEntry findOne(String siIpDirectoryEntryId) {
		if (isProfileActive("mongodb")){
			return mongoSiDirectoryRepository.findOne(siIpDirectoryEntryId);	
		}else if (isProfileActive("rdbms")){
			return jpaSiDirectoryRepository.findOne(new Long(siIpDirectoryEntryId));
		}else{
			return null;
		}
		
	}

	
	public SimplerInvoiceDirectoryEntry findByNonTypedEntry1(String nonTypedEntry1) {
		if (isProfileActive("mongodb")) {
			return mongoSiDirectoryRepository
					.findByNonTypedEntry1(nonTypedEntry1);
		} else if (isProfileActive("rdbms")) {
			return jpaSiDirectoryRepository
					.findByNonTypedEntry1(nonTypedEntry1);
		} else {
			return null;
		}

	}

	public SimplerInvoiceDirectoryEntry findByNonTypedEntry2(String nonTypedEntry2) {
		if (isProfileActive("mongodb")) {
			return mongoSiDirectoryRepository
					.findByNonTypedEntry2(nonTypedEntry2);
		} else if (isProfileActive("rdbms")) {
			return jpaSiDirectoryRepository
					.findByNonTypedEntry2(nonTypedEntry2);
		} else {
			return null;
		}

	}

	public SimplerInvoiceDirectoryEntry findByNonTypedEntry3(String nonTypedEntry3) {
		if (isProfileActive("mongodb")) {
			return mongoSiDirectoryRepository
					.findByNonTypedEntry3(nonTypedEntry3);
		} else if (isProfileActive("rdbms")) {
			return jpaSiDirectoryRepository
					.findByNonTypedEntry3(nonTypedEntry3);
		} else {
			return null;
		}

	}

	public SimplerInvoiceDirectoryEntry findByNonTypedEntry4(String nonTypedEntry4) {
		if (isProfileActive("mongodb")) {
			return mongoSiDirectoryRepository
					.findByNonTypedEntry4(nonTypedEntry4);
		} else if (isProfileActive("rdbms")) {
			return jpaSiDirectoryRepository
					.findByNonTypedEntry4(nonTypedEntry4);
		} else {
			return null;
		}

	}

	public SimplerInvoiceDirectoryEntry findByNonTypedEntry5(String nonTypedEntry5) {
		if (isProfileActive("mongodb")) {
			return mongoSiDirectoryRepository
					.findByNonTypedEntry5(nonTypedEntry5);
		} else if (isProfileActive("rdbms")) {
			return jpaSiDirectoryRepository
					.findByNonTypedEntry5(nonTypedEntry5);
		} else {
			return null;
		}

	}	
	
	public Page<SimplerInvoiceDirectoryEntry> findAll(Pageable pageable) {
		if (isProfileActive("mongodb")){
			return new SimpleInvoiceDirectoryEntryPage(mongoSiDirectoryRepository.findAll( pageable));
		}else if (isProfileActive("rdbms")){
			return new SimpleInvoiceDirectoryEntryPage(jpaSiDirectoryRepository.findAll( pageable));
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

	public void delete(SimplerInvoiceDirectoryEntry siIpDirectoryEntry) {
		if (isProfileActive("mongodb")){
			mongoSiDirectoryRepository.delete(siIpDirectoryEntry.getId());	
		}else if (isProfileActive("rdbms")){
			jpaSiDirectoryRepository.delete(new Long(siIpDirectoryEntry.getId()));
		}
	}


	
}
