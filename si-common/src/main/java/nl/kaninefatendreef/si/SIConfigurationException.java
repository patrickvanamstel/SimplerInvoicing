package nl.kaninefatendreef.si;

@SuppressWarnings("serial")
public class SIConfigurationException extends SIException{

	public SIConfigurationException(Throwable t) {
		super(t);
	}

	public SIConfigurationException(String message) {
		super(message);
	}

	public SIConfigurationException(String message , Throwable t) {
		super(message, t);
	}

	
}
