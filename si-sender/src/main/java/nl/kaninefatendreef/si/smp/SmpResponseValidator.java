package nl.kaninefatendreef.si.smp;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.X509Data;

import java.security.cert.X509Certificate;
import java.util.Iterator;

public class SmpResponseValidator {

    private XMLSignature _xmlSignature;
    private final DOMValidateContext _domValidateContext;

    /**
     *  Uses the XML Document to create an XMLSignature object, which we may inspect later.
     *
     *  Holding the XMLSignature as an instance variable, enables us to inspect and retrieve other
     *  parts of the signature, like for instance the certificate supplied together with the signature.
     *
     * @param smpResponse the W3C DOM object representing the response from a PEPPOL SMP.
     */
    public SmpResponseValidator(Document smpResponse){
        NodeList nodeList = smpResponse.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nodeList.getLength() < 1) {
            throw new SmpSecurityException("Element <Signature> not found in SMP XML response");
        }

        Node signatureNode = nodeList.item(0);    // Retrieves the <Signature> W3C Node

        // The X509KeySelector is our own helper class, assisting us when retrieving the public key used for
        // subsequent signature validation operations.
        _domValidateContext = new DOMValidateContext(new X509KeySelector(), signatureNode);

        // Unmarshals the XMLSignature, i.e. gets rid of white space etc.
        XMLSignatureFactory xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM");
        try {
            _xmlSignature = xmlSignatureFactory.unmarshalXMLSignature(_domValidateContext);
        } catch (MarshalException e) {
            throw new SmpSecurityException("Unable to unmarshal the XML signature", e);
        }
    }


    /**
     * Determines if the signature is valid, i.e. nobody has tampered with the contents.
     *
     * The certificate chain is not inspected to determine whether the certificate holding the public key,
     * is valid and legal. This is a separate operation, which requires access to a truststore.
     *
     * @return true if the signature is valid, false otherwise.
     */
    public boolean isSmpSignatureValid() {
        try {
            return _xmlSignature.validate(_domValidateContext);
        } catch (XMLSignatureException e) {
            throw new SmpSecurityException("Unable to validate signature: " + e, e);
        }
    }

    /**
     * Retrieves the certificate of the SMP, which signed the response.
     *
     * @return the certificate used to sign the request or null if not found.
     */
    public X509Certificate getCertificate() {
        @SuppressWarnings("rawtypes")
		Iterator  keyInfoIterator = _xmlSignature.getKeyInfo().getContent().iterator();
        while (keyInfoIterator.hasNext()) {
            XMLStructure info = (XMLStructure) keyInfoIterator.next();
            if (!(info instanceof X509Data))
                continue;

            X509Data x509Data = (X509Data) info;
            @SuppressWarnings("rawtypes")
			Iterator x509ContentIterator = x509Data.getContent().iterator();
            while (x509ContentIterator.hasNext()) {
                Object o = x509ContentIterator.next();
                if (!(o instanceof X509Certificate))
                    continue;
                else
                    return (X509Certificate) o;
            }
        }
        return null;    // Did not find an X509Certificate together with the signature
    }
}
