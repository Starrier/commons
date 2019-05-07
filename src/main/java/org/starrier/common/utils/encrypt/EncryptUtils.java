package org.starrier.common.utils.encrypt;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @author imperator
 * @date 2019-09-09
 */
public enum EncryptUtils {

    /**
     * SINGLETON
     */
    SINGLETON;

    private static final String SECRET = "Starrier";

    private static final String CHARSET = "UTF-8";


    public String sha(String raw) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(raw.getBytes(CHARSET));
        return Hex.encodeHexString(messageDigest.digest());
    }

    private Cipher createAesCipher() throws Exception {
        return Cipher.getInstance("AES");
    }

    public String encryptByAes(String raw) throws Exception {
        Cipher aesCipher = createAesCipher();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(SECRET.getBytes(CHARSET)));
        SecretKey secretKey = keyGenerator.generateKey();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] bytes = aesCipher.doFinal(raw.getBytes(CHARSET));
        return Hex.encodeHexString(bytes);
    }

    public String decryptByAes(String raw) throws Exception {
        byte[] bytes = Hex.decodeHex(raw);
        Cipher aesCipher = createAesCipher();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(SECRET.getBytes(CHARSET)));
        SecretKey secretKey = keyGenerator.generateKey();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(aesCipher.doFinal(bytes), CHARSET);
    }

}
