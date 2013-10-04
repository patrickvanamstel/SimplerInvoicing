package nl.kaninefatendreef.si.server.repository;

import java.util.List;

import nl.kaninefatendreef.si.server.controller.SimpleInvoiceDocumentPage;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent;
import nl.kaninefatendreef.si.server.repository.jpa.JpaDocumentRepository;
import nl.kaninefatendreef.si.server.repository.mongo.DocumentRepository;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class ActiveDocumentRepository implements ApplicationContextAware {

	ApplicationContext _applicationContext = null;
	
	@Autowired(required=false)
	DocumentRepository documentRepository = null;
	
	@Autowired(required=false)
	JpaDocumentRepository <Long> jpaDocumentRepository = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		_applicationContext = applicationContext;
		
	}
	
	public SimplerInvoiceDocument createSimplerInvoiceDocument(){

		if (isProfileActive("mongodb")){
			return new nl.kaninefatendreef.si.server.model.mongo.SimplerInvoiceDocument();	
		}else if (isProfileActive("rdbms")){
			return new nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocument();
		}else{
			return null;
		}
	}

	public SimplerInvoiceDocumentContent createSimplerInvoiceDocumentContent(){

		if (isProfileActive("mongodb")){
			return new nl.kaninefatendreef.si.server.model.mongo.SimplerInvoiceDocumentContent();	
		}else if (isProfileActive("rdbms")){
			return new nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocumentContent();
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
	
	public SimplerInvoiceDocument save (SimplerInvoiceDocument document){
		
		if (isProfileActive("mongodb")){
			nl.kaninefatendreef.si.server.model.mongo.SimplerInvoiceDocument mongoDocument = (nl.kaninefatendreef.si.server.model.mongo.SimplerInvoiceDocument)document;
			return documentRepository.save(mongoDocument);	
		}else if (isProfileActive("rdbms")){
			nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocument mongoDocument = (nl.kaninefatendreef.si.server.model.jpa.SimplerInvoiceDocument)document;
			return jpaDocumentRepository.save(mongoDocument);	
		}else{
			return null;
		}
		
	}

	public Page<SimplerInvoiceDocument> findAll(Pageable pageable) {
		if (isProfileActive("mongodb")){
			return new SimpleInvoiceDocumentPage(documentRepository.findAll( pageable));
		}else if (isProfileActive("rdbms")){
			return new SimpleInvoiceDocumentPage(jpaDocumentRepository.findAll( pageable));
		}else{
			return null;
		}
	}

	public Iterable<? extends SimplerInvoiceDocument> findByFileName(
			String fileName) {
		if (isProfileActive("mongodb")){
			return documentRepository.findByFileName(fileName);	
		}else if (isProfileActive("rdbms")){
			return jpaDocumentRepository.findByFileName(fileName);
		}else{
			return null;
		}
	}

	public SimplerInvoiceDocument findOne(String simplerinvoiceDocumentId) {
		if (isProfileActive("mongodb")){
			return documentRepository.findOne(simplerinvoiceDocumentId);	
		}else if (isProfileActive("rdbms")){
			return jpaDocumentRepository.findOne(new Long(simplerinvoiceDocumentId));
		}else{
			return null;
		}
		
	}


	public List<SimplerInvoiceDocument> findByProcessed(
			Boolean processedBoolean) {
		if (isProfileActive("mongodb")){
			return documentRepository.findByProcessed(processedBoolean );	
		}else if (isProfileActive("rdbms")){
			return jpaDocumentRepository.findByProcessed(processedBoolean);
		}else{
			return null;
		}
	}

	
	public Page<SimplerInvoiceDocument> findByProcessed(
			Boolean processedBoolean, Pageable pageable) {
		if (isProfileActive("mongodb")){
			return documentRepository.findByProcessed(processedBoolean , pageable);	
		}else if (isProfileActive("rdbms")){
			return jpaDocumentRepository.findByProcessed(processedBoolean, pageable);
		}else{
			return null;
		}
	}

	public long count() {
		if (isProfileActive("mongodb")){
			return documentRepository.count();	
		}else if (isProfileActive("rdbms")){
			return jpaDocumentRepository.count();
		}else{
			return -1;
		}
	}

	public Iterable<?> findAll() {
		if (isProfileActive("mongodb")){
			return documentRepository.findAll();
		}else if (isProfileActive("rdbms")){
			return jpaDocumentRepository.findAll();
		}else{
			return null;
		}
	}

	public void delete(SimplerInvoiceDocument simplerInvoiceDocument) {
		if (isProfileActive("mongodb")){
			documentRepository.delete(simplerInvoiceDocument.getId());	
		}else if (isProfileActive("rdbms")){
			jpaDocumentRepository.delete(new Long(simplerInvoiceDocument.getId()));
		}
	}


	
}
