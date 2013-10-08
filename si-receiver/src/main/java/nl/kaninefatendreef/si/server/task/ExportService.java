package nl.kaninefatendreef.si.server.task;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

public interface ExportService {

	ExportServiceResult export(SimplerInvoiceDocument simplerInvoiceDocument) throws SiExportException;

	boolean exportSuccess(SimplerInvoiceDocument simplerInvoiceDocument) throws SiExportException;

	boolean exportException(SimplerInvoiceDocument simplerInvoiceDocument)throws SiExportException;


}
