package nl.kaninefatendreef.si.smp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import org.xml.sax.InputSource;

public class SmpLookupServiceDelegate {

    private static final String ENCODING_GZIP = "gzip";
    private static final String ENCODING_DEFLATE = "deflate";
    private static final String ALGORITHM_MD5 = "MD5";
    
    
    public static String calculateMD5(String value) throws MessageDigestException {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM_MD5);
        } catch (NoSuchAlgorithmException e) {
            throw new MessageDigestException("Unable to digest " + value ,  e);
        }
        messageDigest.reset();
        try {
            messageDigest.update(value.getBytes("iso-8859-1"), 0, value.length());
        } catch (UnsupportedEncodingException e) {
        	throw new MessageDigestException("Unable to digest " + value ,  e);

        }
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            String hex = Integer.toHexString(0xFF & b);

            if (hex.length() == 1) {
                sb.append('0');
            }

            sb.append(hex);
        }

        return sb.toString();
    }

    /**
     * Gets the content of a given url.
     * @throws SmpParticipantNotFoundException 
     */
    public static InputSource getUrlContent(URL url , Proxy proxy) throws SmpParticipantNotFoundException {

        HttpURLConnection httpURLConnection = null;
        try {
        	
        	if (proxy == null){
        		httpURLConnection = (HttpURLConnection) url.openConnection();
        	}else{
        		httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
        	}
            httpURLConnection.connect();
            
            String encoding = httpURLConnection.getContentEncoding();
            InputStream in = httpURLConnection.getInputStream();
            InputStream result;

            if (encoding != null && encoding.equalsIgnoreCase(ENCODING_GZIP)) {
                result = new GZIPInputStream(in);
            } else if (encoding != null && encoding.equalsIgnoreCase(ENCODING_DEFLATE)) {
                result = new InflaterInputStream(in);
            } else {
                result = in;
            }

            String xml = readInputStreamIntoString(result);

            return new InputSource(new StringReader(xml));

        } catch (java.net.UnknownHostException e){
        	// Participant not fond
        	throw new SmpParticipantNotFoundException(e);
        }catch (IOException e) {
        	// Proxy fault
        	throw new SmpParticipantNotFoundException(e);
        } catch (Exception e) {
          throw new RuntimeException("Problem reading URL data at " + url.toExternalForm(), e);
        } finally {
        	if (httpURLConnection != null){
        		httpURLConnection.disconnect();
        	}
        }

    }

    static String readInputStreamIntoString(InputStream result)  {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(result, Charset.forName("UTF-8")));
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read data from InputStream " + e, e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                // Ignore any problems related to closing of input stream
            }
        }
        return sb.toString();
    }
}
