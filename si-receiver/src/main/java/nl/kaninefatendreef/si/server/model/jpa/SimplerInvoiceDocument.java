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

	
}
