package org.starrier.common.utils.encrypt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.starrier.common.utils.encrypt.AESUtils.decrypt;
import static org.starrier.common.utils.encrypt.AESUtils.encrypt;

/**
 * @author starrier
 * @date 2021/1/2
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AESUtils.class)
public class AESUtilsTest {

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