package org.starrier.common.utils.datatime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取上n个小时整点小时时间
     *
     * @param date specific date
     *             <blockquote><pre>
     *                         getLastHourTime(new Date(),0);
     *                           </pre></blockquote>
     * @return datetime String
     * <blockquote>
     * 2020-02-25 14:00:00
     * </blockquote>
     */
    public static String getLastHourTime(Date date, int n) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) - n);
        date = ca.getTime();
        return sdf.format(date);
    }

    /**
     * 获取当前时间的整点小时时间
     *
     * @param date
     * @return
     */
    public static String getCurrHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        date = ca.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }


    /**
     * 获取指定天数的前几天日期，返回 {@link Date} 类型数据
     *
     * @param specificBeforeDate 指定获取前多少天
     * @return 指定日期的 {@link Date} 类型数据
     */
    public static Date getSpecificDate(Integer specificBeforeDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return date;
    }


}


