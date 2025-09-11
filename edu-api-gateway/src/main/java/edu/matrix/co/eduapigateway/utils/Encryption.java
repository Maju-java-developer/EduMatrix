package edu.matrix.co.eduapigateway.utils;
/**
 * @author Majid.Hussain
 * @type Encryption
 * @since 8/7/2023
 */

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryption {

    private static final String SECRET_KEY = "MOTOR-VEHICLE-REGISTRATION-SINDH";
    private static final String ALGORITHM = "AES";

    public static String encrypt(String source) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(source.getBytes());
        return Base64.getUrlEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encoded) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encoded);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
