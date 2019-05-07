package org.starrier.common.utils.encrypt;

import org.apache.commons.codec.binary.Hex;

/**
 * @author starrier
 * @date 2021/1/2
 */
public class AES2Utils {

    /**
     * 加密
     *
     * @param encryptContent 待加密信息
     * @return 加密字符串
     * @throws Exception 加密过程中可能出现的异常信息
     */
    public static String encrypt(String encryptContent) throws Exception {

        if ("".equals(encryptContent) || encryptContent == null) {
            throw new Exception("current encrypt info not allow blank");
        }

        byte[] encrypt = AESUtils.encrypt(encryptContent);

        return Hex.encodeHexString(encrypt);

    }

    /**
     * 解密
     *
     * @param encryptContent 加密后的加密信息
     * @return 解密后的原始信息
     * @throws Exception 加密过程中可能出现的异常信息
     */
    public static String decrypt(String encryptContent) throws Exception {

        if ("".equals(encryptContent) || encryptContent == null) {
            throw new Exception("current decrypt info not allow blank");
        }

        byte[] encryptBytes = Hex.decodeHex(encryptContent.toCharArray());
        byte[] decrypt = AESUtils.decrypt(encryptBytes);

        return new String(decrypt);
    }

}
