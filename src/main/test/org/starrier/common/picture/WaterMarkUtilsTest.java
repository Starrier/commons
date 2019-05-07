package org.starrier.common.picture;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author starrier
 * @date 2021/1/7
 */
class WaterMarkUtilsTest {

    @Test
    void pressImage() {

        String waterMarkImagePath = "";
        String targetImagePath = "";
        WaterMarkUtils. pressImage(waterMarkImagePath, targetImagePath, 880, 560);

    }

    @Test
    void pressText() {

        WaterMarkUtils.pressText("@Starrier", "", "宋体", 0,
                new Color(248, 248, 255), 50, 1110, 570);
    }
}