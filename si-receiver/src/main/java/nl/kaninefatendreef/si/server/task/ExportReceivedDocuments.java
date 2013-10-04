package nl.kaninefatendreef.si.server.task;


import java.util.List;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;
import nl.kaninefatendreef.si.server.repository.ActiveDocumentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ExportReceivedDocuments {

	Logger _logger = LoggerFactory.getLogger(ExportReceivedDocuments.class);
	
	@Autowired
	ExportService exportService;
	
	@Autowired
	ActiveDocumentRepository _activeDocumentRepository = null;
	
	@Scheduled(fixedDelay=50000)
	public void processNotProcessed(){
		List <SimplerInvoiceDocument> simplerInvoiceDocuments = _activeDocumentRepository.findByProcessed(false);
		for (SimplerInvoiceDocument simplerInvoiceDocument : simplerInvoiceDocuments){
			try{
				simplerInvoiceDocument.setProcessStatus("START_EXPORT");
				simplerInvoiceDocument.setProcessStatusTimeInMs(System.currentTimeMillis());
				simplerInvoiceDocument.setProcessed(true);
				_activeDocumentRepository.save(simplerInvoiceDocument);

				exportService.export(simplerInvoiceDocument);
				simplerInvoiceDocument.setProcessStatus("SUCCESS_EXPORT");
				simplerInvoiceDocument.setProcessStatusTimeInMs(System.currentTimeMillis());
				_activeDocumentRepository.save(simplerInvoiceDocument);
			}catch (Throwable t){
				simplerInvoiceDocument.setProcessStatus("EXCEPION_EXPORT");
				simplerInvoiceDocument.setProcessStatusTimeInMs(System.currentTimeMillis());
				_activeDocumentRepository.save(simplerInvoiceDocument);
			}
		}
	}
	
	// Deal with three export statussen
	public void processProcessedSuccess(){
		List <SimplerInvoiceDocument> simplerInvoiceDocuments = _activeDocumentRepository.findByProcessed(true);
		for (SimplerInvoiceDocument simplerInvoiceDocument : simplerInvoiceDocuments){
			try{
				if (exportService.exportSuccess(simplerInvoiceDocument))
				{
					_activeDocumentRepository.delete(simplerInvoiceDocument);
				}
			}catch (Throwable t){
				_logger.error("Deleting document occured with exceptions " + t);
			}
		}
	}
	
	// Deal with three export statussen
	public void processProcessedException(){
		List <SimplerInvoiceDocument> simplerInvoiceDocuments = _activeDocumentRepository.findByProcessed(true);
		for (SimplerInvoiceDocument simplerInvoiceDocument : simplerInvoiceDocuments){
			try{
				if (exportService.exportException(simplerInvoiceDocument))
				{
					simplerInvoiceDocument.setProcessed(false);
					_activeDocumentRepository.save(simplerInvoiceDocument);
				}
			}catch (Throwable t){
				_logger.error("Deleting document occured with exceptions " + t);
			}
		}
	}

	
	
	
	
}
