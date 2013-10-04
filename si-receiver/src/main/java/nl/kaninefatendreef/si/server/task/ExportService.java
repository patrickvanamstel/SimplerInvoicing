package nl.kaninefatendreef.si.server.task;

import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

public interface ExportService {

	void export(SimplerInvoiceDocument simplerInvoiceDocument) throws Exception;

	boolean exportSuccess(SimplerInvoiceDocument simplerInvoiceDocument) throws Exception;

	boolean exportException(SimplerInvoiceDocument simplerInvoiceDocument);


}
