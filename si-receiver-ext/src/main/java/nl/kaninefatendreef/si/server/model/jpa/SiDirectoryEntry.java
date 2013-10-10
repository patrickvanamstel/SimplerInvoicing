package nl.kaninefatendreef.si.server.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SIMPLER_INVOICE_DIRECTORY")
public class SiDirectoryEntry extends nl.kaninefatendreef.si.server.model.SimplerInvoiceDirectoryEntry{


	@Id
	@GeneratedValue	(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(unique=true,name="EXTERNAL_REFERENCE")
	String externalReference = null;
	
	@Column(unique=true,name="KVK_NUMBER")
	String kvkNumber = null;
	
	@Column(unique=true,name="BTW_NUMBER")
	String btwNumber = null;
	
	@Column(unique=true,name="OIN_NUMBER")
	String oinNumber = null;

	@Column(unique=true,name="NON_TYPED_ENTRY_1")
	String nonTypedEntry1 = null;
	
	@Column(unique=true,name="NON_TYPED_ENTRY_2")
	String nonTypedEntry2 = null;
	
	@Column(unique=true,name="NON_TYPED_ENTRY_3")
	String nonTypedEntry3 = null;
	
	@Column(unique=true,name="NON_TYPED_ENTRY_4")
	String nonTypedEntry4 = null;
	
	@Column(unique=true,name="NON_TYPED_ENTRY_5")
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
	@Override
	public void setId(String id) {
		this.id = new Long(id);
		
	}

}