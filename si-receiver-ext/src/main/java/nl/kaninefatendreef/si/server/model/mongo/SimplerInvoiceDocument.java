package nl.kaninefatendreef.si.server.model.mongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class SimplerInvoiceDocument implements nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument{

	/*
	 * No _ naming.
	 * Otherwise the default finder implentation in spring data does not work.
	 * 
	 */
	
	@Id
	private String id;
	
	String fileName;
	String messageId;
	String channelId;
	String recipientId;
	String senderId;
	String documentId;
	String processId;
	String remoteHost;
	String accessPointPrinciple;
	
	// Extra meta information needed for processing the documents
	Boolean processed;
	Long createTimeInMs;
	Long updateTimeInMs;

	String processStatus;
	Long processStatusTimeInMs;
	Integer procesRetry;
	
	String property1Name;
	String property1Value;
	
	String property2Name;
	String property2Value;

	String property3Name;
	String property3Value;

	String property4Name;
	String property4Value;

	String property5Name;
	String property5Value;


	public void setProperty5Value(String property5Value) {
		this.property5Value = property5Value;
	}

	SimplerInvoiceDocumentContent content;
	
	public SimplerInvoiceDocumentContent getContent() {
		return content;
	}


	public String getFileName() {
		return fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public void setContent(
			nl.kaninefatendreef.si.server.model.SimplerInvoiceDocumentContent content) {
		this.content = (SimplerInvoiceDocumentContent) content;

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


	@Override
	public Integer getProcesRetry() {
		return procesRetry;
	}


	@Override
	public void setProcesRetry(Integer procesRetry) {
		this.procesRetry = procesRetry ;
		
	}


	
}

