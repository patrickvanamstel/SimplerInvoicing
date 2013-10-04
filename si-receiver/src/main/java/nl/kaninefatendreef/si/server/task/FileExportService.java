package nl.kaninefatendreef.si.server.task;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

@Component
public class FileExportService implements ExportService{

	File repository = new File("/tmp/sirepo");
	
	@Override
	public void export(SimplerInvoiceDocument simplerInvoiceDocument)
			throws Exception {
	
		repository.mkdirs();
		
		File f = new File(repository , simplerInvoiceDocument.getFileName() );
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(simplerInvoiceDocument.getContent().getDocument());
		fos.flush();
		fos.close();
		
		
	}

	@Override
	public boolean exportSuccess(SimplerInvoiceDocument simplerInvoiceDocument)
			throws Exception {
		return false;
	}

	@Override
	public boolean exportException(SimplerInvoiceDocument simplerInvoiceDocument) {
		return false;
	}

}
