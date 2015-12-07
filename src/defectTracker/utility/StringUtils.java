package defectTracker.utility;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class StringUtils {

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
