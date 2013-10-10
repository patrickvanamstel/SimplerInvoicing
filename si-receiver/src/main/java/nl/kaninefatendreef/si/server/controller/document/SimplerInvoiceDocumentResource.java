package nl.kaninefatendreef.si.server.controller.document;


import nl.kaninefatendreef.si.server.model.SimplerInvoiceDocument;

import org.springframework.hateoas.ResourceSupport;


public class SimplerInvoiceDocumentResource extends ResourceSupport {

	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		builder.append("id : " +  _id + "\n");
		builder.append("fileName : " +  _fileName + "\n");
		builder.append(" : " +  _messageId + "\n");
		builder.append(" : " +_channelId + "\n");
		builder.append(" : " +_recipientId + "\n");
		builder.append(" : " +_senderId + "\n");
		builder.append(" : " +_documentId + "\n");
		builder.append(" : " +_processId + "\n");
		builder.append(" : " +_remoteHost + "\n");
		builder.append(" : " +_accessPointPrinciple + "\n");
		builder.append("processed : " +_processed + "\n");
		builder.append("updateTimeInMs : " +_updateTimeInMs + "\n");
		builder.append("createTimeInMs : " +_createTimeInMs + "\n");
		
		return builder.toString();
	}
	
	public SimplerInvoiceDocumentResource() {}
	
	public SimplerInvoiceDocumentResource(
			SimplerInvoiceDocument simplerInvoiceDocument) {
		
		_id = simplerInvoiceDocument.getId();
		_fileName= simplerInvoiceDocument.getFileName();
		_messageId= simplerInvoiceDocument.getMessageId();
		_channelId= simplerInvoiceDocument.getChannelId();
		_recipientId= simplerInvoiceDocument.getRecipientId();
		_senderId= simplerInvoiceDocument.getSenderId();
		_documentId= simplerInvoiceDocument.getDocumentId();
		_processId= simplerInvoiceDocument.getProcessId();
		_remoteHost= simplerInvoiceDocument.getRemoteHost();
		_accessPointPrinciple= simplerInvoiceDocument.getAccessPointPrinciple();
		_createTimeInMs = simplerInvoiceDocument.getCreateTimeInMs();
		_updateTimeInMs = simplerInvoiceDocument.getUpdateTimeInMs();
		_processed = simplerInvoiceDocument.getProcessed();
		
	}
	
	
	private String _id;
	private String _fileName;
	private String _messageId;
	private String _channelId;
	private String _recipientId;
	private String _senderId;
	private String _documentId;
	private String _processId;
	private String _remoteHost;
	private String _accessPointPrinciple;
	
	private Boolean _processed;
	private Long _updateTimeInMs;
	private Long _createTimeInMs;


	public Boolean getProcessed() {
		return _processed;
	}

	public void setProcessed(Boolean processed) {
		this._processed = processed;
	}

	public Long getUpdateTimeInMs() {
		return _updateTimeInMs;
	}

	public void setUpdateTimeInMs(Long updateTimeInMs) {
		this._updateTimeInMs = updateTimeInMs;
	}

	public Long getCreateTimeInMs() {
		return _createTimeInMs;
	}

	public void setCreateTimeInMs(Long createTimeInMs) {
		this._createTimeInMs = createTimeInMs;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		this._fileName = fileName;
	}

	public String getMessageId() {
		return _messageId;
	}

	public void setMessageId(String messageId) {
		this._messageId = messageId;
	}

	public String getChannelId() {
		return _channelId;
	}

	public void setChannelId(String channelId) {
		this._channelId = channelId;
	}

	public String getRecipientId() {
		return _recipientId;
	}

	public void setRecipientId(String recipientId) {
		this._recipientId = recipientId;
	}

	public String getSenderId() {
		return _senderId;
	}

	public void setSenderId(String senderId) {
		this._senderId = senderId;
	}

	public String getDocumentId() {
		return _documentId;
	}

	public void setDocumentId(String documentId) {
		this._documentId = documentId;
	}

	public String getProcessId() {
		return _processId;
	}

	public void setProcessId(String processId) {
		this._processId = processId;
	}

	public String getRemoteHost() {
		return _remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this._remoteHost = remoteHost;
	}

	public String getAccessPointPrinciple() {
		return _accessPointPrinciple;
	}

	public void setAccessPointPrinciple(String accessPointPrinciple) {
		this._accessPointPrinciple = accessPointPrinciple;
	}


	
	
	
	
}
