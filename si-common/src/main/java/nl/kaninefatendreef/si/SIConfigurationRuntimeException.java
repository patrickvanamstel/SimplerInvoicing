package nl.kaninefatendreef.si;

@SuppressWarnings("serial")
public class SIConfigurationRuntimeException extends RuntimeException{

	public SIConfigurationRuntimeException(Throwable e) {
		super(e);
	}

	public SIConfigurationRuntimeException(String message) {
		super(message);
	}

	public SIConfigurationRuntimeException(String message,Throwable e) {
		super(message,e);
	}
}
