/* Created by steinar on 14.05.12 at 00:46 */
package nl.kaninefatendreef.si.smp;


@SuppressWarnings("serial")
public class SmpSecurityException extends RuntimeException {

    public SmpSecurityException(String message) {
        super(message);
    }

	public SmpSecurityException(String message, Throwable  t) {
		super(message , t);
	}
}
