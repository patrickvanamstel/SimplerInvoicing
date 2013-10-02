package nl.kaninefatendreef.si;

@SuppressWarnings("serial")
public class SIConfigurationException extends Exception{

	public SIConfigurationException(Throwable t) {
		super(t);
	}

	public SIConfigurationException(String message) {
		super(message);
	}

}
