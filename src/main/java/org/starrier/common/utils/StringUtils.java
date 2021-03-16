package org.starrier.common.utils;

/**
 * @author starrier
 * @date 2021/3/15
 */
public class StringUtils {

    public static boolean isBlank(CharSequence cs) {
        int strLen = length(cs);
        if (strLen == 0) {
            return true;
        } else {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
