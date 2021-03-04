package org.starrier.common.token;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.starrier.common.token.TokenConstant.KEY_ALGORTHM;

/**
 * @author : Starrier
 * @date 2019/3/6 0006
 * <p>
 * Description :
 */
public class DESCoder {

    private Key toKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORTHM);
        return keyFactory.generateSecret(dks);
    }

    public Key toKey(String key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        byte[] keyBytes = Base64.decodeBase64(key);
        return toKey(keyBytes);
    }
}