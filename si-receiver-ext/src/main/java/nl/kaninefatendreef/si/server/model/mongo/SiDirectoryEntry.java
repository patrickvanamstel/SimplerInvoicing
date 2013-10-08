package nl.kaninefatendreef.si.server.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sidirectory")
public class SiDirectoryEntry implements nl.kaninefatendreef.si.server.model.SiDirectoryEntry{

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
