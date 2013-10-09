package nl.kaninefatendreef.si.server.model.jpa;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "SIMPLER_INVOICE_DOCUMENT")
public class SimplerInvoiceDocument implements nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument{

	
	@Id
	@GeneratedValue	(strategy = GenerationType.IDENTITY)
	@Column(name = "SIMPLER_INVOICE_DOCUMENT_ID")
	private Long id;
	
	@Column(name = "FILENAME")
	String fileName;
	
	@Column(name = "MESSAGE_ID")
	String messageId;
	
	@Column(name = "CHANNEL_ID")
	String channelId;
	
	@Column(name = "RECIPIENT_ID")
	String recipientId;
	
	@Column(name = "SENDER_ID")
	String senderId;
	
	@Column(name = "DOCUMENT_ID")
	String documentId;
	
	@Column(name = "PROCESS_ID")
	String processId;
	
	@Column(name = "REMOTE_HOST")
	String remoteHost;
	
	@Column(name = "ACCESS_POINT_PRINCIPLE")
	String accessPointPrinciple;
	
	// Extra meta information needed for processing the documents
	@Column(name = "PROCESSED")
	boolean processed;
	
	@Column(name = "CREATE_TIME_MS")
	Long createTimeInMs;
	
	@Column(name = "UPDATE_TIME_MS")
	Long updateTimeInMs;

	@Column(name = "PROCESS_STATUS")
	String processStatus;

	@Column(name = "PROCESS_STATUS_MS")
	Long processStatusTimeInMs;
	
	@Column(name = "PROCESS_RETRY")
	Integer procesRetry;
	

	@Column(name = "PROPERTY_1_NAME")
	String property1Name;

	@Column(name = "PROPERTY_1_VALUE")
	String property1Value;
	
	@Column(name = "PROPERTY_2_NAME")
	String property2Name;
	@Column(name = "PROPERTY_2_VALUE")
	String property2Value;

	@Column(name = "PROPERTY_3_NAME")
	String property3Name;
	@Column(name = "PROPERTY_3_VALUE")
	String property3Value;

	@Column(name = "PROPERTY_4_NAME")
	String property4Name;
	@Column(name = "PROPERTY_4_VALUE")
	String property4Value;

	@Column(name = "PROPERTY_5_NAME")
	String property5Name;
	@Column(name = "PROPERTY_5_VALUE")
	String property5Value;

	
	
	
	
	@OneToOne(cascade=CascadeType.ALL)
	SimplerInvoiceDocumentContent content;
	
	public SimplerInvoiceDocumentContent getContent() {
		return content;
	}

	public void setContent(nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent content) {
		this.content = (SimplerInvoiceDocumentContent)content;
	}

	public String getFileName() {
		return fileName;
	}

	public String getId() {
		return "" + id;
	}

	public void setId(String id) {
		
		this.id = new Long(id);
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getAccessPointPrinciple() {
		return accessPointPrinciple;
	}

	public void setAccessPointPrinciple(String accessPointPrinciple) {
		this.accessPointPrinciple = accessPointPrinciple;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public Long getCreateTimeInMs() {
		return createTimeInMs;
	}

	public void setCreateTimeInMs(Long createTimeInMs) {
		this.createTimeInMs = createTimeInMs;
	}

	public Long getUpdateTimeInMs() {
		return updateTimeInMs;
	}

	public void setUpdateTimeInMs(Long updateTimeInMs) {
		this.updateTimeInMs = updateTimeInMs;
	}

	@Override
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
		
	}
	
	@Override
	public String getProcessStatus() {
		return processStatus;
	}

	@Override
	public void setProcessStatusTimeInMs(Long processStatusTimeInMs) {
		this.processStatusTimeInMs = processStatusTimeInMs;
		
	}

	@Override
	public Long getProcessStatusTimeInMs() {
		return processStatusTimeInMs;
	}

	public String getProperty1Name() {
		return property1Name;
	}

	public void setProperty1Name(String property1Name) {
		this.property1Name = property1Name;
	}

	public String getProperty1Value() {
		return property1Value;
	}

	public void setProperty1Value(String property1Value) {
		this.property1Value = property1Value;
	}

	public String getProperty2Name() {
		return property2Name;
	}

	public void setProperty2Name(String property2Name) {
		this.property2Name = property2Name;
	}

	public String getProperty2Value() {
		return property2Value;
	}

	public void setProperty2Value(String property2Value) {
		this.property2Value = property2Value;
	}

	public String getProperty3Name() {
		return property3Name;
	}

	public void setProperty3Name(String property3Name) {
		this.property3Name = property3Name;
	}

	public String getProperty3Value() {
		return property3Value;
	}

	public void setProperty3Value(String property3Value) {
		this.property3Value = property3Value;
	}

	public String getProperty4Name() {
		return property4Name;
	}

	public void setProperty4Name(String property4Name) {
		this.property4Name = property4Name;
	}

	public String getProperty4Value() {
		return property4Value;
	}

	public void setProperty4Value(String property4Value) {
		this.property4Value = property4Value;
	}

	public String getProperty5Name() {
		return property5Name;
	}

	public void setProperty5Name(String property5Name) {
		this.property5Name = property5Name;
	}

	public String getProperty5Value() {
		return property5Value;
	}

	public void setProperty5Value(String property5Value) {
		this.property5Value = property5Value;
	}

	public Integer getProcesRetry() {
		return procesRetry;
	}

	public void setProcesRetry(Integer procesRetry) {
		this.procesRetry = procesRetry;
	}
	
}
