package org.starrier.common.picture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.*;

/**
 * @author starrier
 * @date 2021/1/7
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WaterMarkUtils.class)
class WaterMarkUtilsTest {

    @Test
    void pressImage() {

        String waterMarkImagePath = "";
        String targetImagePath = "";
        WaterMarkUtils.pressImage(waterMarkImagePath, targetImagePath, 880, 560);

    }

    @Test
    void pressText() {

        WaterMarkUtils.pressText("@Starrier", "", "宋体", 0,
                new Color(248, 248, 255), 50, 1110, 570);
    }
}