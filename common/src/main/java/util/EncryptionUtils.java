package util;
/**
 *
 *@author Shahzeb.Iqbal
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
}
