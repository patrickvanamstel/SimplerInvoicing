package nl.kaninefatendreef.si.server.model;


public interface SimplerInvoiceDocument {

	
	// Internal object id
	public String getId() ;
	public void setId(String id) ;

	/*
	 * Simpler invoicing meta information
	 */
	public String getFileName() ;
	public void setFileName(String fileName) ;
	public String getMessageId() ;
	public void setMessageId(String messageId) ;
	public String getChannelId() ;
	public void setChannelId(String channelId) ;
	public String getRecipientId() ;
	public void setRecipientId(String recipientId) ;
	public String getSenderId() ;
	public void setSenderId(String senderId) ;
	public String getDocumentId() ;
	public void setDocumentId(String documentId) ;
	public String getProcessId() ;
	public void setProcessId(String processId) ;
	public String getRemoteHost() ;
	public void setRemoteHost(String remoteHost) ;
	public String getAccessPointPrinciple() ;
	public void setAccessPointPrinciple(String accessPointPrinciple) ;

	/*
	 * Internal processing information
	 */
	public Long getCreateTimeInMs() ;
	public void setCreateTimeInMs(Long createTimeInMs) ;
	public Long getUpdateTimeInMs() ;
	public void setUpdateTimeInMs(Long updateTimeInMs) ;
	public Boolean getProcessed() ;
	public void setProcessed(Boolean processed) ;
	public void setProcessStatus(String string);
	public String getProcessStatus();
	public void setProcessStatusTimeInMs(Long currentTimeMillis);
	public Long getProcessStatusTimeInMs();

	/**
	 * One 2 One relation.
	 * <p>
	 * For storage and retrieval considerations split this blob from the "meta" information.
	 * @return
	 */
	public SimplerInvoiceDocumentContent getContent();
	public void setContent(SimplerInvoiceDocumentContent content);


	
}
