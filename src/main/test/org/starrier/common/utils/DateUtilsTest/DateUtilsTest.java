package org.starrier.common.utils.DateUtilsTest;

import org.starrier.common.utils.datatime.DateUtils;

import java.util.Date;

public class DateUtilsTest {

    public static void main(String[] args) {
        String lastHourTime = DateUtils.getLastHourTime(new Date(), 1);
        System.out.println(lastHourTime);
        String lastHourTime1 = DateUtils.getLastHourTime(new Date(), 0);
        System.out.println(lastHourTime1);
    }
}
