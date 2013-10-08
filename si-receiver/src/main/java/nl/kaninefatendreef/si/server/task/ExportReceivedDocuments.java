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

				ExportServiceResult exportServiceResult = exportService.export(simplerInvoiceDocument);
				
				simplerInvoiceDocument.setProcessStatus("SUCCESS_EXPORT");
				simplerInvoiceDocument.setProcessStatusTimeInMs(System.currentTimeMillis());
				
				if (exportServiceResult != null){
					simplerInvoiceDocument.setProperty1Name(exportServiceResult.getProperty1Name());
					simplerInvoiceDocument.setProperty1Value(exportServiceResult.getProperty1Value());

					simplerInvoiceDocument.setProperty2Name(exportServiceResult.getProperty2Name());
					simplerInvoiceDocument.setProperty2Value(exportServiceResult.getProperty2Value());

					simplerInvoiceDocument.setProperty3Name(exportServiceResult.getProperty3Name());
					simplerInvoiceDocument.setProperty3Value(exportServiceResult.getProperty3Value());

					simplerInvoiceDocument.setProperty4Name(exportServiceResult.getProperty4Name());
					simplerInvoiceDocument.setProperty4Value(exportServiceResult.getProperty4Value());

					simplerInvoiceDocument.setProperty5Name(exportServiceResult.getProperty5Name());
					simplerInvoiceDocument.setProperty5Value(exportServiceResult.getProperty5Value());
				
				}
				
				_activeDocumentRepository.save(simplerInvoiceDocument);
			}catch (SiExportBackendDownException downException){
				simplerInvoiceDocument.setProcessStatus("EXCEPION_BACKEND_DOWN_EXPORT");
				simplerInvoiceDocument.setProcessStatusTimeInMs(System.currentTimeMillis());
				_activeDocumentRepository.save(simplerInvoiceDocument);
			}catch (SiExportImplementationException impleException){
				simplerInvoiceDocument.setProcessStatus("EXCEPION_IMPL_EXPORT");
				simplerInvoiceDocument.setProcessStatusTimeInMs(System.currentTimeMillis());
				_activeDocumentRepository.save(simplerInvoiceDocument);
			} catch (SiExportException e) {
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
