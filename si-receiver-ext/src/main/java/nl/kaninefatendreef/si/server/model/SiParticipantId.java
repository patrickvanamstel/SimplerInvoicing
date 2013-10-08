package nl.kaninefatendreef.si.server.model;

import java.util.StringTokenizer;

import nl.kaninefatendreef.si.server.task.SiExportImplementationException;

/**
 * Implements SimplerInvoicing specific id's
 * @author Patrick van Amstel
 *
 */
public class SiParticipantId {
	

	String firstPart = null;
	String secondPart = null;
	
	public String getFirstPart() {
		return firstPart;
	}

	public void setFirstPart(String firstPart) {
		this.firstPart = firstPart;
	}

	public String getSecondPart() {
		return secondPart;
	}

	public void setSecondPart(String secondPart) {
		this.secondPart = secondPart;
	}



	boolean kvk = false;
	boolean btw = false;
	boolean oin = false;
	
	
	public Boolean isKvk(){
		return kvk;
	}
	
	public Boolean isBtw(){
		return btw;
	}
	
	public Boolean isOin(){
		return oin;
	}
	
	
	
	public SiParticipantId(String identifier) throws SiExportImplementationException{
	
		StringTokenizer tokens = new StringTokenizer(identifier , ":");
		
		if (tokens.countTokens() != 2){
			throw new SiExportImplementationException("The identifier does not contain 2 tokens. This fault should have been dealt with in the acceptance of the document");
		}
		
		firstPart = tokens.nextToken();
		secondPart = tokens.nextToken();
		
		if (firstPart.equals("0106")){
			kvk = true;
		}else if(firstPart.equals("9944")){
			btw = true;
		}else if(firstPart.equals("9954")){
			oin = true;
		}else{
			throw new SiExportImplementationException("The first part of indentifier is not recognized. This fault should have been dealt with in the acceptance of the document");
		}
		
		
	}
	

}
