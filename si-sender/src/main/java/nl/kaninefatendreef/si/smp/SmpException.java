package nl.kaninefatendreef.si.smp;



@SuppressWarnings("serial")
public class SmpException extends Exception {

	public SmpException(){
		super();
	}
	
	public SmpException(String message){
		super (message);
	}
	
	public SmpException(String message , Throwable t){
		super(message , t);
	}

	public SmpException(Throwable t) {
		super(t);
	}
	
}
