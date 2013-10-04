package nl.kaninefatendreef.si.server.model.mongo;




import java.util.UUID;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
//import javax.persistence.Id;
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
	
	UUID uuid;
	

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

	public UUID getUuid() {
		return uuid;
	}


	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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


	
}

