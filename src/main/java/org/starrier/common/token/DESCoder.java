package org.starrier.common.token;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

import static org.starrier.common.token.TokenConstant.KEY_ALGORTHM;

/**
 * @author : Starrier
 * @date 2019/3/6 0006
 * <p>
 * Description :
 */
public class DESCoder {

    @SneakyThrows(Exception.class)
    private Key toKey(byte[] key) {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORTHM);
        return keyFactory.generateSecret(dks);
    }

    @SneakyThrows(Exception.class)
    public Key toKey(String key) {
        byte[] keyBytes = Base64.decodeBase64(key);
        return toKey(keyBytes);
    }
}