//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.03 at 06:38:09 AM CEST 
//


package org.busdox.smp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceGroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceGroupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://busdox.org/transport/identifiers/1.0/}ParticipantIdentifier"/>
 *         &lt;element name="ServiceMetadataReferenceCollection" type="{http://busdox.org/serviceMetadata/publishing/1.0/}ServiceMetadataReferenceCollectionType"/>
 *         &lt;element name="Extension" type="{http://busdox.org/serviceMetadata/publishing/1.0/}ExtensionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceGroupType", namespace = "http://busdox.org/serviceMetadata/publishing/1.0/", propOrder = {
    "participantIdentifier",
    "serviceMetadataReferenceCollection",
    "extension"
})
public class ServiceGroupType {

    @XmlElement(name = "ParticipantIdentifier", namespace = "http://busdox.org/transport/identifiers/1.0/", required = true)
    protected ParticipantIdentifierType participantIdentifier;
    @XmlElement(name = "ServiceMetadataReferenceCollection", required = true)
    protected ServiceMetadataReferenceCollectionType serviceMetadataReferenceCollection;
    @XmlElement(name = "Extension")
    protected ExtensionType extension;

    /**
     * Gets the value of the participantIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ParticipantIdentifierType }
     *     
     */
    public ParticipantIdentifierType getParticipantIdentifier() {
        return participantIdentifier;
    }

    /**
     * Sets the value of the participantIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParticipantIdentifierType }
     *     
     */
    public void setParticipantIdentifier(ParticipantIdentifierType value) {
        this.participantIdentifier = value;
    }

    /**
     * Gets the value of the serviceMetadataReferenceCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceMetadataReferenceCollectionType }
     *     
     */
    public ServiceMetadataReferenceCollectionType getServiceMetadataReferenceCollection() {
        return serviceMetadataReferenceCollection;
    }

    /**
     * Sets the value of the serviceMetadataReferenceCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceMetadataReferenceCollectionType }
     *     
     */
    public void setServiceMetadataReferenceCollection(ServiceMetadataReferenceCollectionType value) {
        this.serviceMetadataReferenceCollection = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

}
