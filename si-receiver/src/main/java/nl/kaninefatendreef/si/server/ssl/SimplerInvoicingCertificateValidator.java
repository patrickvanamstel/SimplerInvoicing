package nl.kaninefatendreef.si.server.ssl;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import nl.kaninefatendreef.si.SIConfigurationException;
import nl.kaninefatendreef.si.ssl.TrustStoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



// TODO opruimen
@Component
public class SimplerInvoicingCertificateValidator implements InitializingBean{

	Logger _logger = LoggerFactory.getLogger(SimplerInvoicingCertificateValidator.class); 
	
	@Autowired
	private ValidCertificateCache _validCertificateCache = null;

	@Autowired
    private TrustStoreManager _peppolTrustStore =  null;
	
	private CertificateFactory _certificateFactory = null;
   
    //TODO : Make this configurable
    private Boolean _certificateRevocationEnabled = false;
    private Boolean _ocspEnabled = false;

    /**
     * Validates the supplied certificate against the PEPPOL chain of trust.
     *
     *
     * @param x509Certificate
     * @throws CertPathValidatorException if the supplied certificate fails validation.
     */
    public boolean validate(X509Certificate x509Certificate) throws CertPathValidatorException {

        String certificateInfo = x509Certificate.getSerialNumber() + " " + x509Certificate.getSubjectDN().getName();

        _logger.debug("Validation of certificate " + certificateInfo + " requested");

        if (_validCertificateCache.isValid(x509Certificate)) {
        	_logger.debug("Certificate " + certificateInfo + " found in cache of trusted certificates.");
            return true;
        }
        _logger.debug("Certificate " + certificateInfo + " not found in cache, performing OCSP and CRLDP (optional) validation");

        PKIXParameters pkixParameters = null;
        try {
            pkixParameters = new PKIXParameters(_peppolTrustStore.getTrustAnchors());
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalStateException("Unable to create PKIXParameters; " + e.getMessage(), e);
        } catch (SIConfigurationException e) {
        	throw new IllegalStateException("Unable to create PKIXParameters; " + e.getMessage(), e);
		}
        
        //pkixParameters.setRevocationEnabled(true);
        //Security.setProperty("ocsp.enable", "true");
        //System.setProperty("com.sun.security.enableCRLDP", "" + t);
        //PvA 2013 aangepast naar geen validatie tegen ca
        pkixParameters.setRevocationEnabled(_certificateRevocationEnabled);
        Security.setProperty("ocsp.enable", "" + _ocspEnabled);

        // Enables CRL Distribution Points extension, which is disabled by default for compatibility reasons
        System.setProperty("com.sun.security.enableCRLDP", "" + true);

        CertPathValidator certPathValidator = null;
        try {
            certPathValidator = CertPathValidator.getInstance("PKIX");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Unable to create instance of certificate path valdiator");
        }

        try {

            CertPath certPath = _certificateFactory.generateCertPath(Arrays.asList(x509Certificate));
            
            _logger.debug("Certificate " + certificateInfo + ", trying to validate");
            
           	CertPathValidatorResult validatorResult = certPathValidator.validate(certPath, pkixParameters);

            // Insert serial number of this certificate to improve performance
            _validCertificateCache.setKnown(x509Certificate);

            _logger.debug("Certificate " + certificateInfo + ", validated OK");
            return true;

        } catch (CertificateException e) {
            throw new IllegalStateException("Unable to establish cert path for certificate " + x509Certificate, e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalStateException("Error during certificate validation: " + e.getMessage(), e);
        }
    }


	@Override
	public void afterPropertiesSet() throws Exception {
		  _certificateFactory = CertificateFactory.getInstance("X.509");		
	}
}
