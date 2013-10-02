package nl.kaninefatendreef.si;

@SuppressWarnings("serial")
public class SIException extends Exception{

	public SIException(Throwable t) {
		super(t);
	}

	public SIException(String message) {
		super(message);
	}

	public SIException(String message, Throwable t) {
		super(message , t);
	}

}
