package org.starrier.common.utils;

import java.util.regex.Pattern;

public class StringNumberUtils {

    // 判断字符串是否是浮点数
    public static boolean isFloat(String target) {

        if (target == null || target.length() == 0) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[-\\+]?([1-9]\\d*.\\d*|0\\.\\d*[1-9]\\d*)");
        return pattern.matcher(target).matches();
    }

    /**
     * 判断字符串是否是 Integer 整数
     *
     * @param target 目标字符串
     * @return 是否是 Integer 整数
     */
    public static boolean isInteger(String target) {
        if (target == null || target.length() == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(target).matches();
    }


    public static void main(String[] args) {
        boolean integer = isInteger("100.0");
        System.out.println(integer);
    }
}
