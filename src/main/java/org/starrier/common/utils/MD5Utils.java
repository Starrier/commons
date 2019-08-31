package org.starrier.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * @author Starrier
 * @date 7/28/2019
 */
public class MD5Utils {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static final String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        return encodeBase64String(bytes);
    }
}
