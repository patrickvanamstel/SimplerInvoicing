package nl.kaninefatendreef.si.server.model;

public abstract class SiDirectoryEntry {

	public abstract String getExternalReference();
	public abstract void setExternalReference(String ipReference);
	
	public abstract String getKvKNumber();
	public abstract String getBtwNumber();
	public abstract String getOinNumber();
	
	
	
	public abstract void setKvKNumber(String kvkNumber);
	public abstract void setBtwNumber(String btwNumber);
	public abstract void setOinNumber(String ionNumber);
	
	
	public abstract String getNonTypedEntry1();
	public abstract void setNonTypedEntry1(String nonTypedEntry1) ;
	public abstract String getNonTypedEntry2();
	public abstract void setNonTypedEntry2(String nonTypedEntry2);
	public abstract String getNonTypedEntry3();
	public abstract void setNonTypedEntry3(String nonTypedEntry3);
	public abstract String getNonTypedEntry4();
	public abstract void setNonTypedEntry4(String nonTypedEntry4);
	public abstract String getNonTypedEntry5();
	public abstract void setNonTypedEntry5(String nonTypedEntry5);
	
	public abstract String getId();
	public abstract void setId(String id);
	
	
}
