package nl.kaninefatendreef.si.server.model;


import nl.kaninefatendreef.si.server.model.SimplerInvoiceDirectoryEntry;

//Todo register as mapped class for interface
public class SimplerInvoiceDirectoryEntryJson extends SimplerInvoiceDirectoryEntry {

	String id;
	String externalReference = null;
	String kvkNumber = null;
	String btwNumber = null;
	String oinNumber = null;
	String nonTypedEntry1 = null;
	String nonTypedEntry2 = null;
	String nonTypedEntry3 = null;
	String nonTypedEntry4 = null;
	String nonTypedEntry5 = null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExternalReference() {
		return externalReference;
	}
	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}
	public String getKvkNumber() {
		return kvkNumber;
	}
	public void setKvkNumber(String kvkNumber) {
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
	@Override
	public String getKvKNumber() {
		return kvkNumber;
	}
	@Override
	public void setKvKNumber(String kvkNumber) {
		this.kvkNumber = kvkNumber;
		
	}
	

}
