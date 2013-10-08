package nl.kaninefatendreef.si.server.task;



@SuppressWarnings("serial")
public class SiExportBackendDownException extends SiExportException{

	public SiExportBackendDownException(String message) {
		super(message);
	}

	public SiExportBackendDownException(Throwable e) {
		super(e);
	}

}
