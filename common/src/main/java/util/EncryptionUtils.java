package util;
/**
 *
 *@author Majid.Hussain
 *@since 6/27/2023
 *@type EncryptionUtils
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {

    static String keyText = "MOTORVEHICLEREGISTRATION";
    private static final String ALGO = "AES";

    public static String encryptAES(String plainText) throws Exception {
        byte[] keyBytes = keyText.getBytes("UTF-8");
        byte[] plainTextBytes = plainText.getBytes("UTF-8");
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, new IvParameterSpec(new byte[16]));
        byte[] cipherBytes = cipher.doFinal(plainTextBytes);
        return Base64.getEncoder().encodeToString(cipherBytes);
    }

    public static String decryptAES(String value) throws Exception {
        SecretKey key = new SecretKeySpec(keyText.getBytes("UTF-8"), "AES");
        Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        dcipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        byte[] dec = Base64.getDecoder().decode(value);
        byte[] utf8 = dcipher.doFinal(dec);
        return new String(utf8, "UTF8");
    }

    private static SecretKeySpec getKey() {
        return new SecretKeySpec(keyText.getBytes(), ALGO);
    }

    public static String encryptId(Number id) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            return Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(cipher.doFinal(id.toString().getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting ID", e);
        }
    }

    public static Long decryptId(String encryptedId) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            String decrypted = new String(cipher.doFinal(Base64.getUrlDecoder().decode(encryptedId)));
            return Long.valueOf(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting ID", e);
        }
    }

}
