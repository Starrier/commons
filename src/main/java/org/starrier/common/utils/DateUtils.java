package org.starrier.common.utils;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.starrier.common.constant.DataConstant.DATE_FORMAT_DEFAULT;

/**
 * @author Starrier
 * @date 2019/4/18
 */
public class DateUtils implements Converter<String, Date> {


    private static final List<String> formarts = Lists.newArrayListWithExpectedSize(4);

    /**
     * @param date
     * @param num
     * @return
     */
    public static String getDay(String date, int num) {
        return getDay(date, num, DATE_FORMAT_DEFAULT);
    }

    /**
     * @param date
     * @param num
     * @param format
     * @return
     */
    public static String getDay(String date, int num, String format) {
        long t = parseStringToLong(date);
        return getDay(t, num, DATE_FORMAT_DEFAULT);
    }

    /**
     * 获取指定日期前后num天的日期
     *
     * @param date
     * @param num
     * @return
     */
    public static String getDay(long date, int num) {
        return getDay(date, num, DATE_FORMAT_DEFAULT);
    }

    /**
     * 获取指定日期前后num天的日期
     *
     * @param date
     * @param num
     * @param format
     * @return
     */
    public static String getDay(long date, int num, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + num);
        return longToString(calendar.getTimeInMillis(), format);
    }

    /**
     * 将毫秒时间转换为yyyy-MM-dd格式的时间
     *
     * @param time
     * @return
     */
    public static String longToString(long time) {
        return longToString(time, DATE_FORMAT_DEFAULT);
    }

    /**
     * 将毫秒时间转换为指定格式的时间
     *
     * @param time
     * @param format
     * @return
     */
    public static String longToString(long time, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT_DEFAULT;
        }
        DateTime dTime = new DateTime(time);
        return dTime.toString(format);
    }

    /**
     * 获取今天开始的时间
     *
     * @return
     */
    public static Timestamp getTodayStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 001);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 获取指定日期开始的当日开始时间
     *
     * @param date
     * @return
     */
    public static long getDayStartTime(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(parseStringToLong(date));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 001);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定日期结束时间
     *
     * @param date
     * @return
     * @author yangwenkui
     * @time 2017年10月6日 下午5:58:58
     */
    public static long getDayEndTime(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(parseStringToLong(date));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获得当前日期
     */
    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd");
    }

    /**
     * 获得当前时间
     *
     * @param format 日期格式
     * @return
     */
    public static String getCurrentTime(String format) {
        DateTime dTime = new DateTime();
        return dTime.toString(format);
    }

    /**
     * 将字符串类型的日期转换为毫秒数
     *
     * @param dateStr
     * @return
     */
    public static long parseStringToLong(String dateStr) {
        dateStr = dateStr.trim();
        if (dateStr.length() == 19 || dateStr.length() == 23) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                        Integer.parseInt(dateStr.substring(5, 7)) - 1,
                        Integer.parseInt(dateStr.substring(8, 10)),
                        Integer.parseInt(dateStr.substring(11, 13)),
                        Integer.parseInt(dateStr.substring(14, 16)),
                        Integer.parseInt(dateStr.substring(17, 19)));
                cal.set(Calendar.MILLISECOND, 0);
                return (cal.getTime().getTime());
            } catch (Exception e) {
                return 0;
            }

        } else if (dateStr.length() == 16) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                        Integer.parseInt(dateStr.substring(5, 7)) - 1,
                        Integer.parseInt(dateStr.substring(8, 10)),
                        Integer.parseInt(dateStr.substring(11, 13)),
                        Integer.parseInt(dateStr.substring(14, 16)));
                cal.set(Calendar.MILLISECOND, 0);
                return (cal.getTime().getTime());
            } catch (Exception e) {
                return 0;
            }

        } else if (dateStr.length() == 14) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                        Integer.parseInt(dateStr.substring(4, 6)) - 1,
                        Integer.parseInt(dateStr.substring(6, 8)),
                        Integer.parseInt(dateStr.substring(8, 10)),
                        Integer.parseInt(dateStr.substring(10, 12)),
                        Integer.parseInt(dateStr.substring(12, 14)));
                cal.set(Calendar.MILLISECOND, 0);
                return (cal.getTime().getTime());
            } catch (Exception e) {
                return 0;
            }
        } else if (dateStr.length() == 10 || dateStr.length() == 11) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                        Integer.parseInt(dateStr.substring(5, 7)) - 1,
                        Integer.parseInt(dateStr.substring(8, 10)), 0, 0, 0);
                cal.set(Calendar.MILLISECOND, 0);
                return (cal.getTime().getTime());
            } catch (Exception e) {
                return 0;
            }
        } else if (dateStr.length() == 8) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                        Integer.parseInt(dateStr.substring(4, 6)) - 1,
                        Integer.parseInt(dateStr.substring(6, 8)), 0, 0, 0);
                cal.set(Calendar.MILLISECOND, 0);
                return (cal.getTime().getTime());
            } catch (Exception e) {
                return 0;
            }
        } else {
            try {
                return Long.parseLong(dateStr);
            } catch (Exception e) {
                return 0;
            }

        }
    }


    static {
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String source) {

        String value = source.trim();

        if (StringUtils.EMPTY.equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(3));
        } else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }


    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    @SneakyThrows(Exception.class)
    public Date parseDate(String dateStr, String format) {
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateStr);
    }
}