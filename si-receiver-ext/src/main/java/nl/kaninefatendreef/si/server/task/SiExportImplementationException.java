package nl.kaninefatendreef.si.server.task;





@SuppressWarnings("serial")
public class SiExportImplementationException extends SiExportException {

	public SiExportImplementationException(Throwable t) {
		super(t);
	}

	public SiExportImplementationException(String message,
			Throwable t) {
		super(message , t);
	}

	public SiExportImplementationException(String message) {
		super(message);
	}

}
