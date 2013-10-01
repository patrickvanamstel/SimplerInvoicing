package eu.peppol.start.model;

import static eu.peppol.start.model.IdentifierName.CHANNEL_ID;
import static eu.peppol.start.model.IdentifierName.DOCUMENT_ID;
import static eu.peppol.start.model.IdentifierName.MESSAGE_ID;
import static eu.peppol.start.model.IdentifierName.PROCESS_ID;
import static eu.peppol.start.model.IdentifierName.RECIPIENT_ID;
import static eu.peppol.start.model.IdentifierName.SENDER_ID;

import java.security.Principal;

import javax.xml.namespace.QName;

import com.sun.xml.ws.api.message.HeaderList;

public class PeppolMessageHeader {

    MessageId _messageId;
    ChannelId _channelId;
    ParticipantId _recipientId;
    ParticipantId _senderId;
    PeppolDocumentTypeId _documentTypeIdentifier;
    PeppolProcessTypeId _peppolProcessTypeId;
    String _remoteHost;
    Principal _remoteAccessPointPrincipal;

    
    public PeppolMessageHeader(){}
    
    private static final String NAMESPACE_TRANSPORT_IDS = "http://busdox.org/transport/identifiers/1.0/";
    
    public PeppolMessageHeader(HeaderList headerList) {
    	_messageId =  new MessageId(getContent(headerList, MESSAGE_ID));
    	_channelId = new ChannelId(getContent(headerList, CHANNEL_ID));
    	_recipientId = new ParticipantId(getContent(headerList, RECIPIENT_ID.stringValue()));
    	_senderId =  new ParticipantId(getContent(headerList, SENDER_ID.stringValue()));
    	_documentTypeIdentifier = PeppolDocumentTypeId.valueOf(getContent(headerList, DOCUMENT_ID));
    	_peppolProcessTypeId = PeppolProcessTypeId.valueOf(getContent(headerList, PROCESS_ID));
    }
    
    private static String getContent(HeaderList headerList, String identifierName) {
        return headerList.get(getQName(identifierName), false).getStringContent();
    }

    
    private static String getContent(HeaderList headerList, IdentifierName identifierName) {
        return headerList.get(getQName(identifierName), false).getStringContent();
    }

    private static QName getQName(IdentifierName identifierName) {
        return getQName(identifierName.stringValue());
    }
    
    private static QName getQName(String headerName) {
        return new QName(NAMESPACE_TRANSPORT_IDS, headerName);
    }


    
    public MessageId getMessageId() {
        return _messageId;
    }

    public void setMessageId(MessageId messageId) {
        this._messageId = messageId;
    }

    public ChannelId getChannelId() {
        return _channelId;
    }

    public void setChannelId(ChannelId channelId) {
        this._channelId = channelId;
    }

    public ParticipantId getRecipientId() {
        return _recipientId;
    }

    public void setRecipientId(ParticipantId recipientId) {
        this._recipientId = recipientId;
    }

    public ParticipantId getSenderId() {
        return _senderId;
    }

    public void setSenderId(ParticipantId senderId) {
        this._senderId = senderId;
    }

    public PeppolDocumentTypeId getDocumentTypeIdentifier() {
        return _documentTypeIdentifier;
    }

    public void setDocumentTypeIdentifier(PeppolDocumentTypeId documentTypeIdentifier) {
        this._documentTypeIdentifier = documentTypeIdentifier;
    }

    public PeppolProcessTypeId getPeppolProcessTypeId() {
        return _peppolProcessTypeId;
    }

    public void setPeppolProcessTypeId(PeppolProcessTypeId peppolProcessTypeId) {
        this._peppolProcessTypeId = peppolProcessTypeId;
    }

    public String getRemoteHost() {
        return _remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this._remoteHost = remoteHost;
    }

    public Principal getRemoteAccessPointPrincipal() {
        return _remoteAccessPointPrincipal;
    }

    public void setRemoteAccessPointPrincipal(Principal remoteAccessPointPrincipal) {
        this._remoteAccessPointPrincipal = remoteAccessPointPrincipal;
    }

    @Override
    public String toString() {
    	
    	StringBuilder stringBuilder = new StringBuilder("PeppolMessageHeader [\n");
    	
    	stringBuilder.append("messageId : " +  _messageId + "\n");
    	stringBuilder.append("channelId : " +  _channelId+ "\n");
    	stringBuilder.append("recipientId : " +  _recipientId+ "\n");
    	stringBuilder.append("senderId : " +  _senderId+ "\n");
    	stringBuilder.append("documentTypeIdentifier : " +  _documentTypeIdentifier+ "\n");
    	stringBuilder.append("peppolProcessTypeId : " +  _peppolProcessTypeId+ "\n");
    	stringBuilder.append("remoteHost : " +  _remoteHost+ "\n");
    	stringBuilder.append("remoteAccessPointPrincipal : " +  _remoteAccessPointPrincipal+ "\n");
    	stringBuilder.append("]\n");
        
        return stringBuilder.toString();
    	
        
    }
}
