
package org.w3._2009._02.ws_tra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faultcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faultstring" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="details" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "action",
    "faultcode",
    "faultstring",
    "details"
})
@XmlRootElement(name = "StartException", namespace = "http://busdox.org/2010/02/channel/fault")
public class StartException {

    @XmlElement(namespace = "http://busdox.org/2010/02/channel/fault", required = true)
    protected String action;
    @XmlElement(namespace = "http://busdox.org/2010/02/channel/fault", required = true)
    protected String faultcode;
    @XmlElement(namespace = "http://busdox.org/2010/02/channel/fault", required = true)
    protected String faultstring;
    @XmlElement(namespace = "http://busdox.org/2010/02/channel/fault", required = true)
    protected String details;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the faultcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultcode() {
        return faultcode;
    }

    /**
     * Sets the value of the faultcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultcode(String value) {
        this.faultcode = value;
    }

    /**
     * Gets the value of the faultstring property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultstring() {
        return faultstring;
    }

    /**
     * Sets the value of the faultstring property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultstring(String value) {
        this.faultstring = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetails(String value) {
        this.details = value;
    }

}
