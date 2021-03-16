package org.starrier.common.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author starrier
 * @date 2020/12/28
 */
public class AESUtils {

    private static final String INIT_VECTOR = "encryptionIntVec";
    private static final String SEED = "coupon.restful";
    private static final String ALGORITHM = "AES";
    private static final String ALGORITHM_HIGH ="AES/CBC/PKCS5PADDING";

    /**
     * 加密
     *
     * @param content Data content to be encrypted
     * @return encrypt data
     * @throws Exception not allow bank
     */
    public static byte[] encrypt(String content) throws Exception {

        if ("".equals(content) || content == null) {
            throw new Exception("encrypt content not allow blank");
        }
        SecretKey secretKey = getSecretKey();
        return encryptAES(content.getBytes(StandardCharsets.UTF_8), secretKey);

    }

    /**
     * 解密
     *
     * @param encrypt encrypted data to be decrypted
     * @return decrypt data
     * @throws Exception exception
     */
    public static byte[] decrypt(byte[] encrypt) throws Exception {

        SecretKey secretKey = getSecretKey();
        return decryptAES(encrypt, secretKey);

    }


    /**
     * 获得一个 密钥长度为 256 位的 AES 密钥，
     * 如果环境支持，最好选择使用  256 位加密，更加安全
     *
     * @return 返回经 BASE64 处理之后的密钥字符串
     */
    public static String getStrKeyAES() throws NoSuchAlgorithmException {

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(SEED.getBytes());

        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        // 这里可以是 128、192、256、越大越安全
        keyGen.init(128, secureRandom);

        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());

    }

    /**
     * 将使用 Base64 加密后的字符串类型的 secretKey 转为 SecretKey
     *
     * @param strKey
     * @return SecretKey
     */
    public static SecretKey strKey2SecretKey(String strKey) {

        byte[] bytes = Base64.getDecoder().decode(strKey);
        return new SecretKeySpec(bytes, ALGORITHM);

    }

    /**
     * 加密
     *
     * @param content   待加密内容
     * @param secretKey 加密使用的 AES 密钥
     * @return 加密后的密文 byte[]
     */
    public static byte[] encryptAES(byte[] content, SecretKey secretKey) throws Exception {

        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(ALGORITHM_HIGH);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey,iv);
        return cipher.doFinal(content);

    }

    /**
     * 解密
     *
     * @param content   待解密内容
     * @param secretKey 解密使用的 AES 密钥
     * @return 解密后的明文 byte[]
     */
    public static byte[] decryptAES(byte[] content, SecretKey secretKey) throws Exception {

        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(ALGORITHM_HIGH);
        cipher.init(Cipher.DECRYPT_MODE, secretKey,iv);
        return cipher.doFinal(content);

    }

    public static SecretKey getSecretKey() throws NoSuchAlgorithmException {

        // 获得经 BASE64 处理之后的 AES 密钥
        String strKeyAES = getStrKeyAES();

        // 将 BASE64 处理之后的 AES 密钥转为 SecretKey
        return strKey2SecretKey(strKeyAES);

    }


}
