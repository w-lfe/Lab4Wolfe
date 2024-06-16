/** Project: Lab 4
 * Purpose Details: key creation via webservice/json
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/12
 * Last Date Changed:
 * Rev:

 */


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HMACUtil {
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String SECRET_KEY = "mysecretkey";

    /**
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String calculateHMAC(String data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] hmacBytes = mac.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(hmacBytes);
    }

    public static boolean verifyHMAC(String data, String hmac) throws Exception {
        return calculateHMAC(data).equals(hmac);
    }
}

