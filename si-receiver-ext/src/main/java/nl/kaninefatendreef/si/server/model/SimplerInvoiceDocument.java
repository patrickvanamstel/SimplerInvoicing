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
	public Integer getProcesRetry();
	public void setProcesRetry(Integer retries);
	
	
	
	public String getProperty1Name();
	public void setProperty1Name(String propertyName);
	public String getProperty1Value();
	public void setProperty1Value(String propertyName);
	
	public String getProperty2Name();
	public void setProperty2Name(String propertyName);
	public String getProperty2Value();
	public void setProperty2Value(String propertyName);

	public String getProperty3Name();
	public void setProperty3Name(String propertyName);
	public String getProperty3Value();
	public void setProperty3Value(String propertyName);

	public String getProperty4Name();
	public void setProperty4Name(String propertyName);
	public String getProperty4Value();
	public void setProperty4Value(String propertyName);

	public String getProperty5Name();
	public void setProperty5Name(String propertyName);
	public String getProperty5Value();
	public void setProperty5Value(String propertyName);


	/**
	 * One 2 One relation.
	 * <p>
	 * For storage and retrieval considerations split this blob from the "meta" information.
	 * @return
	 */
	public SimplerInvoiceDocumentContent getContent();
	public void setContent(SimplerInvoiceDocumentContent content);


	
}
