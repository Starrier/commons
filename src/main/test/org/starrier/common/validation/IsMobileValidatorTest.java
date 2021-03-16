package org.starrier.common.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.starrier.common.utils.encrypt.AES2Utils;


/**
 * @author starrier
 * @date 2021/1/2
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IsMobileValidator.class)
class IsMobileValidatorTest {

    @Test
    void initialize() {

    }

    @Test
    @DisplayName("mobilePhone param validate")
    void isValid() {

        String mobilePhone = "12234567889";
    }
}