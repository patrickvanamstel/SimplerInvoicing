//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.03 at 06:38:09 AM CEST 
//


package org.busdox.smp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceMetadataReferenceCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceMetadataReferenceCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceMetadataReference" type="{http://busdox.org/serviceMetadata/publishing/1.0/}ServiceMetadataReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceMetadataReferenceCollectionType", namespace = "http://busdox.org/serviceMetadata/publishing/1.0/", propOrder = {
    "serviceMetadataReference"
})
public class ServiceMetadataReferenceCollectionType {

    @XmlElement(name = "ServiceMetadataReference")
    protected List<ServiceMetadataReferenceType> serviceMetadataReference;

    /**
     * Gets the value of the serviceMetadataReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceMetadataReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceMetadataReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceMetadataReferenceType }
     * 
     * 
     */
    public List<ServiceMetadataReferenceType> getServiceMetadataReference() {
        if (serviceMetadataReference == null) {
            serviceMetadataReference = new ArrayList<ServiceMetadataReferenceType>();
        }
        return this.serviceMetadataReference;
    }

}
