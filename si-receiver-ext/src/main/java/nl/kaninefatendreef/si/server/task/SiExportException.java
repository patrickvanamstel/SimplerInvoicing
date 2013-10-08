package nl.kaninefatendreef.si.server.task;

@SuppressWarnings("serial")
public class SiExportException extends Exception{

	public SiExportException(String message) {
		super(message);
	}

	public SiExportException(String message, Throwable t) {
		super(message , t);
	}

	public SiExportException(Throwable t) {
		super(t);
	}

}
