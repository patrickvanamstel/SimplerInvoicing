package nl.kaninefatendreef.si.document;
/*
 * Version: MPL 1.1/EUPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at:
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Copyright The PEPPOL project (http://www.peppol.eu)
 *
 * Alternatively, the contents of this file may be used under the
 * terms of the EUPL, Version 1.1 or - as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL
 * (the "Licence"); You may not use this work except in compliance
 * with the Licence.
 * You may obtain a copy of the Licence at:
 * http://www.osor.eu/eupl/european-union-public-licence-eupl-v.1.1
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 * If you wish to allow use of your version of this file only
 * under the terms of the EUPL License and not to allow others to use
 * your version of this file under the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and
 * other provisions required by the EUPL License. If you do not delete
 * the provisions above, a recipient may use your version of this file
 * under either the MPL or the EUPL License.
 */

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Set;

public class AccessPointX509TrustManager implements X509TrustManager {

	Logger logger = LoggerFactory.getLogger(AccessPointX509TrustManager.class);
	
    private Set<String> commonNames;
    private X509Certificate rootCertificate;

    private X509TrustManager defaultTrustManager = null;

    public AccessPointX509TrustManager(Set<String> acceptedCommonNames, X509Certificate acceptedRootCertificate) {
        this.rootCertificate = acceptedRootCertificate;
        this.commonNames = acceptedCommonNames;

        // Locates and saves the default trust manager, i.e. the one supplied with the Java runtime
        defaultTrustManager = locateAndSaveDefaultTrustManager();

    }

    private X509TrustManager locateAndSaveDefaultTrustManager() {
        String algorithm = TrustManagerFactory.getDefaultAlgorithm();
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(algorithm);
            instance.init((KeyStore) null); // Initialises the trust manager factory with the default CA certs installed
            int length = instance.getTrustManagers().length;
            TrustManager[] trustManagers = instance.getTrustManagers();
            for (TrustManager trustManager : trustManagers) {
                if (trustManager instanceof X509TrustManager) {
                    return  (X509TrustManager) trustManager;
                }
            }

        } catch (NoSuchAlgorithmException e) {
        	logger.error("Unable to obtain instances of the TrustManagerFactory for algorithm " + algorithm);
        } catch (KeyStoreException e) {
        	logger.error("Unable to initialize the trust manager");
        }
        return null;
    }

    public final void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
    	logger.debug("Checking client certificates");
        checkPrincipal(chain);
    }

    public final void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        for (X509Certificate x509Certificate : chain) {
        	logger.debug("Inspecting peer certificate " + x509Certificate.getSubjectX500Principal() + ", issued by " + x509Certificate.getIssuerX500Principal());
        }

        // Invokes the default JSSE Trust Manager in order to check the SSL peer certificate.
        try {
            if (defaultTrustManager != null){
                defaultTrustManager.checkServerTrusted(chain, authType);
            } else {
            	logger.warn("No default trust manager established upon creation of " + this.getClass().getSimpleName());
            }
        } catch (CertificateException e) {
            X509Certificate x509Certificate = chain[0];
            logger.warn("Server SSL sertificate " + x509Certificate + " is not trusted: " + e + "\nThis cause might be a missing root certificate in your local truststore");
        }
        checkPrincipal(chain);
        logger.debug("Void SSL server certificate check performed.");
    }

    public final X509Certificate[] getAcceptedIssuers() {
    	logger.debug("Returning trusted root certificates");
        return new X509Certificate[]{rootCertificate};
    }

    private void checkPrincipal(final X509Certificate[] chain) throws CertificateException {

        if (commonNames == null) {
            return;
        }

        String[] array = chain[0].getSubjectX500Principal().toString().split(",");

        for (String s : array) {

            int x = s.indexOf("CN=");

            if (x >= 0) {
                String curCN = s.substring(x + 3);

                if (commonNames.contains(curCN)) {
                    StringBuilder logappender = new StringBuilder();
                    logappender.append("Accepted issuer: ");
                    logappender.append(s.substring(x + 3));

                    logger.info(logappender.toString());
                    logger.info("Accepted issuer: " + s.substring(x + 3));

                    return;
                }
            }
        }

        logger.error("No accepted issuer: " + chain[0].getSubjectX500Principal());
        throw new CertificateException("Remote principal is not trusted");
    }
}
