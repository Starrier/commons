package org.starrier.common.utils.encrypt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.starrier.common.utils.encrypt.AESUtils.decrypt;
import static org.starrier.common.utils.encrypt.AESUtils.encrypt;

/**
 * @author starrier
 * @date 2021/1/2
 */
class AESUtilsTest {

    String encryptParams = "commons";

    @Test
    @DisplayName("encrypt key")
    void encryptTest() throws Exception {
        encrypt(encryptParams);
    }

    @Test
    @DisplayName("decrypt key")
    void decryptTest() throws Exception {

        byte[] encrypt = encrypt(encryptParams);
        byte[] decrypt = decrypt(encrypt);


    }
}