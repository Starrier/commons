package org.starrier.common.utils.encrypt;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author starrier
 * @date 2021/1/2
 */

class AES2UtilsTest {

    String test = "test";

    @Test
    public void encryptTest() throws Exception {

        String encrypt = AES2Utils.encrypt(test);

    }

    @Test
    public void decryptTest() throws Exception {
        String encrypt = AES2Utils.encrypt(test);
        String decrypt = AES2Utils.decrypt(encrypt);
        Assert.isTrue(Objects.equals(decrypt, test));
    }
}