package nl.kaninefatendreef.si.server.model;

public interface SiDirectoryEntry {

	public String getExternalReference();
	public void setExternalReference(String ipReference);
	
	public String getKvKNumber();
	public String getBtwNumber();
	public String getOinNumber();
	
	public void setKvKNumber(String kvkNumber);
	public void setBtwNumber(String btwNumber);
	public void setOinNumber(String ionNumber);
	public String getId();
	
	
}
