package nl.kaninefatendreef.si.server.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sidirectory")
public class SiDirectoryEntry extends nl.kaninefatendreef.si.server.model.SiDirectoryEntry{

	@Id
	private String id;

	@Indexed(unique=true)
	String externalReference = null;

	@Indexed
	String kvkNumber = null;
	@Indexed
	String btwNumber = null;
	@Indexed
	String oinNumber = null;

	@Indexed 
	String nonTypedEntry1 = null;
	
	@Indexed 
	String nonTypedEntry2 = null;
	
	@Indexed 
	String nonTypedEntry3 = null;
	
	@Indexed 
	String nonTypedEntry4 = null;
	
	@Indexed 
	String nonTypedEntry5 = null;
	
	
	public String getNonTypedEntry1() {
		return nonTypedEntry1;
	}
	public void setNonTypedEntry1(String nonTypedEntry1) {
		this.nonTypedEntry1 = nonTypedEntry1;
	}
	public String getNonTypedEntry2() {
		return nonTypedEntry2;
	}
	public void setNonTypedEntry2(String nonTypedEntry2) {
		this.nonTypedEntry2 = nonTypedEntry2;
	}
	public String getNonTypedEntry3() {
		return nonTypedEntry3;
	}
	public void setNonTypedEntry3(String nonTypedEntry3) {
		this.nonTypedEntry3 = nonTypedEntry3;
	}
	public String getNonTypedEntry4() {
		return nonTypedEntry4;
	}
	public void setNonTypedEntry4(String nonTypedEntry4) {
		this.nonTypedEntry4 = nonTypedEntry4;
	}
	public String getNonTypedEntry5() {
		return nonTypedEntry5;
	}
	public void setNonTypedEntry5(String nonTypedEntry5) {
		this.nonTypedEntry5 = nonTypedEntry5;
	}
	public String getKvKNumber() {
		return kvkNumber;
	}
	public void setKvKNumber(String kvkNumber) {
		this.kvkNumber = kvkNumber;
	}
	public String getBtwNumber() {
		return btwNumber;
	}
	public void setBtwNumber(String btwNumber) {
		this.btwNumber = btwNumber;
	}
	public String getOinNumber() {
		return oinNumber;
	}
	public void setOinNumber(String oinNumber) {
		this.oinNumber = oinNumber;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getExternalReference() {
		return externalReference;
	}
	@Override
	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
		
	}

}
