package nl.kaninefatendreef.si.smp;

import java.security.Key;
import java.security.PublicKey;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.X509Data;

public class X509KeySelector extends KeySelector {

    public KeySelectorResult select(KeyInfo keyInfo,
                                    KeySelector.Purpose purpose,
                                    AlgorithmMethod method,
                                    XMLCryptoContext context)
            throws KeySelectorException {

        @SuppressWarnings("rawtypes")
		Iterator keyInfoContentIterator = keyInfo.getContent().iterator();
        while (keyInfoContentIterator.hasNext()) {
        	XMLStructure info = (XMLStructure) keyInfoContentIterator.next();
            if (!(info instanceof X509Data)){
                continue;
            }

            X509Data x509Data = (X509Data) info;
            @SuppressWarnings("rawtypes")
			Iterator x509Iterator = x509Data.getContent().iterator();
            while (x509Iterator.hasNext()) {
                Object o = x509Iterator.next();
                if (!(o instanceof X509Certificate))
                    continue;

                final PublicKey key = ((X509Certificate) o).getPublicKey();
                // Make sure the algorithm is compatible
                // with the method.

                if (algEquals(method.getAlgorithm(), key.getAlgorithm())) {
                    X509Certificate x509Certificate = (X509Certificate) o;
                    try {
                        // Ensures the certificate is valid for current date
                        x509Certificate.checkValidity();
                    } catch (CertificateExpiredException e) {
                        throw new KeySelectorException("Certificate of SMP has expired ", e);
                    } catch (CertificateNotYetValidException e) {
                        throw new KeySelectorException("Certificate of SMP not yet valid", e);
                    }
                    return new KeySelectorResult() {
                        public Key getKey() {

                            return key;
                        }
                    };
                }
            }
        }
        throw new KeySelectorException("No key found!");
    }


    private static boolean algEquals(String algURI, String algName) {
        if ((algName.equalsIgnoreCase("DSA") &&
                algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) ||
                (algName.equalsIgnoreCase("RSA") &&
                        algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1))) {
            return true;
        } else {
            return false;
        }
    }
}