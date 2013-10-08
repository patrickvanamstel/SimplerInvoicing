package nl.kaninefatendreef.si.server.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIMPLER_INVOICE_SI_DIRECTORY")
public class SiDirectoryEntry implements nl.kaninefatendreef.si.server.model.SiDirectoryEntry{


	@Id
	@GeneratedValue	(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(unique=true,name="EXTERNAL-REFERENCE")
	String externalReference = null;
	
	@Column(name="KVK-NUMBER")
	String kvkNumber = null;
	
	@Column(name="BTW-NUMBER")
	String btwNumber = null;
	
	@Column(name="OIN-NUMBER")
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
		return "" + id;
	}
	public void setId(Long id) {
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