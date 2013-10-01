
package org.w3._2009._02.ws_tra;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.w3._2009._02.ws_tra package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MessageIdentifier_QNAME = new QName("http://busdox.org/transport/identifiers/1.0/", "MessageIdentifier");
    private final static QName _ChannelIdentifier_QNAME = new QName("http://busdox.org/transport/identifiers/1.0/", "ChannelIdentifier");
    private final static QName _ProcessIdentifier_QNAME = new QName("http://busdox.org/transport/identifiers/1.0/", "ProcessIdentifier");
    private final static QName _SenderIdentifier_QNAME = new QName("http://busdox.org/transport/identifiers/1.0/", "SenderIdentifier");
    private final static QName _DocumentIdentifier_QNAME = new QName("http://busdox.org/transport/identifiers/1.0/", "DocumentIdentifier");
    private final static QName _ParticipantIdentifier_QNAME = new QName("http://busdox.org/transport/identifiers/1.0/", "ParticipantIdentifier");
    private final static QName _RecipientIdentifier_QNAME = new QName("http://busdox.org/transport/identifiers/1.0/", "RecipientIdentifier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3._2009._02.ws_tra
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link ProcessIdentifierType }
     * 
     */
    public ProcessIdentifierType createProcessIdentifierType() {
        return new ProcessIdentifierType();
    }

    /**
     * Create an instance of {@link Put }
     * 
     */
    public Put createPut() {
        return new Put();
    }

    /**
     * Create an instance of {@link ResourceCreated }
     * 
     */
    public ResourceCreated createResourceCreated() {
        return new ResourceCreated();
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link GetResponse }
     * 
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link DocumentIdentifierType }
     * 
     */
    public DocumentIdentifierType createDocumentIdentifierType() {
        return new DocumentIdentifierType();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link PutResponse }
     * 
     */
    public PutResponse createPutResponse() {
        return new PutResponse();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link ParticipantIdentifierType }
     * 
     */
    public ParticipantIdentifierType createParticipantIdentifierType() {
        return new ParticipantIdentifierType();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link StartException }
     * 
     */
    public StartException createStartException() {
        return new StartException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://busdox.org/transport/identifiers/1.0/", name = "MessageIdentifier")
    public JAXBElement<String> createMessageIdentifier(String value) {
        return new JAXBElement<String>(_MessageIdentifier_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://busdox.org/transport/identifiers/1.0/", name = "ChannelIdentifier")
    public JAXBElement<String> createChannelIdentifier(String value) {
        return new JAXBElement<String>(_ChannelIdentifier_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessIdentifierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://busdox.org/transport/identifiers/1.0/", name = "ProcessIdentifier")
    public JAXBElement<ProcessIdentifierType> createProcessIdentifier(ProcessIdentifierType value) {
        return new JAXBElement<ProcessIdentifierType>(_ProcessIdentifier_QNAME, ProcessIdentifierType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParticipantIdentifierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://busdox.org/transport/identifiers/1.0/", name = "SenderIdentifier")
    public JAXBElement<ParticipantIdentifierType> createSenderIdentifier(ParticipantIdentifierType value) {
        return new JAXBElement<ParticipantIdentifierType>(_SenderIdentifier_QNAME, ParticipantIdentifierType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentIdentifierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://busdox.org/transport/identifiers/1.0/", name = "DocumentIdentifier")
    public JAXBElement<DocumentIdentifierType> createDocumentIdentifier(DocumentIdentifierType value) {
        return new JAXBElement<DocumentIdentifierType>(_DocumentIdentifier_QNAME, DocumentIdentifierType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParticipantIdentifierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://busdox.org/transport/identifiers/1.0/", name = "ParticipantIdentifier")
    public JAXBElement<ParticipantIdentifierType> createParticipantIdentifier(ParticipantIdentifierType value) {
        return new JAXBElement<ParticipantIdentifierType>(_ParticipantIdentifier_QNAME, ParticipantIdentifierType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParticipantIdentifierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://busdox.org/transport/identifiers/1.0/", name = "RecipientIdentifier")
    public JAXBElement<ParticipantIdentifierType> createRecipientIdentifier(ParticipantIdentifierType value) {
        return new JAXBElement<ParticipantIdentifierType>(_RecipientIdentifier_QNAME, ParticipantIdentifierType.class, null, value);
    }

}
