package org.starrier.common.utils.encrypt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

/**
 * @author starrier
 * @date 2021/1/2
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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
        Assert.isTrue(decrypt.equals(encrypt), "success");
    }
}