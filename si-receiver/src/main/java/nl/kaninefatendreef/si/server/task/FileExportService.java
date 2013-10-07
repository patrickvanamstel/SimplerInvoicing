package nl.kaninefatendreef.si.server.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

@Component
public class FileExportService implements ExportService{

	File repository = new File("/tmp/sirepo");
	
	@Override
	public void export(SimplerInvoiceDocument simplerInvoiceDocument) throws SiExportException{
	
		repository.mkdirs();
		
		File f = new File(repository , simplerInvoiceDocument.getFileName() );
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(simplerInvoiceDocument.getContent().getDocument());
		} catch (Exception e) {
			throw new SiExportBackendDownException(e);
		}finally{
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
		
		
	}

	@Override
	public boolean exportSuccess(SimplerInvoiceDocument simplerInvoiceDocument) {
		return false;
	}

	@Override
	public boolean exportException(SimplerInvoiceDocument simplerInvoiceDocument) {
		return false;
	}

}
