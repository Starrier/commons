package org.starrier.common.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Starrier
 * @date 7/28/2019
 */
public class RedissionUtils {

    private static RedissionUtils redissionUtils;

    private RedissionUtils() {

    }

    public static RedissionUtils getInstance() {
        if (redissionUtils == null) {
            synchronized (RedissionUtils.class) {
                if (redissionUtils == null) {
                     redissionUtils = new RedissionUtils();
                }
            }
        }
        return redissionUtils;
    }
}
