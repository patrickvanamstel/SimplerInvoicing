package nl.kaninefatendreef.si.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import nl.kaninefatendreef.si.constant.SIConfigurationProperties;
import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

@Component
public class ActiveExportService implements ApplicationContextAware{

	Logger logger = LoggerFactory.getLogger(ActiveExportService.class);
	
	ApplicationContext _applicationContext = null;
	ExportService _exportService = null;
	
	@Autowired
	Environment environment = null;
	
	
	private ExportService getExportService(){
		if (_exportService == null){
			
			String [] exportServiceNames = _applicationContext.getBeanNamesForType(ExportService.class);
			
			 //fileExportService,deliveryServiceExportService
			
			if (exportServiceNames.length == 1){
				_exportService = _applicationContext.getBean(ExportService.class);
			}else if (exportServiceNames.length == 2){
				for (String exportServiceName : exportServiceNames){
					if (!exportServiceName.equals("fileExportService")){
						_exportService = (ExportService) _applicationContext.getBean(exportServiceName);		
					}
				}
			}else if (exportServiceNames.length > 2){
				boolean found = false;
				String componentName = environment.getProperty(SIConfigurationProperties.SI_RECEIVER_EXPORT_COMPONENT_NAME.getValue());
				for (String exportServiceName : exportServiceNames){

					if (!exportServiceName.equals("fileExportService") && exportServiceName.equals(componentName)){
						_exportService = (ExportService) _applicationContext.getBean(exportServiceName);
						found = true;
					}
				}
				if (!found){
					logger.error("Component with name " + componentName + " not found in environment with property " + SIConfigurationProperties.SI_RECEIVER_EXPORT_COMPONENT_NAME.getValue() );
				}
			}else{
				_exportService = _applicationContext.getBean(ExportService.class);
			}
			
		}
		return _exportService;
	}
	
	ExportServiceResult export(SimplerInvoiceDocument simplerInvoiceDocument) throws SiExportException{
		return getExportService().export(simplerInvoiceDocument);
	}

	boolean exportSuccess(SimplerInvoiceDocument simplerInvoiceDocument) throws SiExportException{
		return getExportService().exportSuccess(simplerInvoiceDocument);
	}

	boolean exportException(SimplerInvoiceDocument simplerInvoiceDocument)throws SiExportException{
		return getExportService().exportException(simplerInvoiceDocument);
	}

	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		_applicationContext = applicationContext;
		
	}


}
