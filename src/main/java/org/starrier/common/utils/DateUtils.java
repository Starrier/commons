package org.starrier.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.starrier.common.annotation.logger.ExceptionZero;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static org.starrier.common.constant.Constant.FOUR;
import static org.starrier.common.constant.DataConstant.DATE_FORMAT_DEFAULT;

/**
 * @author Starrier
 * @date 2019/4/18
 */
public class DateUtils implements Converter<String, Date> {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";
    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";
    private static final List<String> FOR_MARTS = Lists.newArrayListWithExpectedSize(FOUR);

    static {
        FOR_MARTS.add("yyyy-MM");
        FOR_MARTS.add("yyyy-MM-dd");
        FOR_MARTS.add("yyyy-MM-dd hh:mm");
        FOR_MARTS.add("yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 格式化时间，格式为 yyyyMMddHHmmss
     *
     * @param localDateTime LocalDateTime
     * @return 格式化后的字符串
     */
    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    /**
     * 根据传入的格式，格式化时间
     *
     * @param localDateTime LocalDateTime
     * @param format        格式
     * @return 格式化后的字符串
     */
    public static String formatFullTime(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 根据传入的格式，格式化时间
     *
     * @param date   Date
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化 CST类型的时间字符串
     *
     * @param date   CST类型的时间字符串
     * @param format 格式
     * @return 格式化后的字符串
     * @throws ParseException 异常
     */
    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return DateUtils.getDateFormat(usDate, format);
    }

    /**
     * 格式化 Instant
     *
     * @param instant Instant
     * @param format  格式
     * @return 格式化后的字符串
     */
    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param date {@link String}
     * @param num  {@link Integer}
     * @return {@link String}
     */
    public static String getDay(String date, int num) {
        return getDay(date, num, DATE_FORMAT_DEFAULT);
    }

    /**
     * @param date 字符串日期
     * @param num
     * @param format
     * @return 字符串日期
     */
    public static String getDay(String date, int num, String format) {
        return getDay(parseStringToLong(date), num, DATE_FORMAT_DEFAULT);
    }

    /**
     * 获取指定日期前后num天的日期
     *
     * @param date 毫秒数
     * @param num 指定距今的天数
     * @return 字符串日期
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
        return new DateTime().toString(format);
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
     *
     * 2017年10月6日 下午5:58:58
     * 获取指定日期结束时间
     *
     * @param date 时间
     * @return 毫秒数
     * @author yangwenkui
     *
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
     * @return {@link String}
     */
    public static String getCurrentTime(String format) {
        return new DateTime().toString(format);
    }

    /**
     * 将字符串类型的日期转换为毫秒数
     *
     * @param dateStr is the start of time.
     * @return {@link Long}
     */
    @ExceptionZero
    public static long parseStringToLong(String dateStr) {
        dateStr = dateStr.trim();
        Calendar cal = Calendar.getInstance();
        if (dateStr.length() == 19 || dateStr.length() == 23) {

            cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                    Integer.parseInt(dateStr.substring(5, 7)) - 1,
                    Integer.parseInt(dateStr.substring(8, 10)),
                    Integer.parseInt(dateStr.substring(11, 13)),
                    Integer.parseInt(dateStr.substring(14, 16)),
                    Integer.parseInt(dateStr.substring(17, 19)));
            cal.set(Calendar.MILLISECOND, 0);
            return (cal.getTime().getTime());
        } else if (dateStr.length() == 16) {
            cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                    Integer.parseInt(dateStr.substring(5, 7)) - 1,
                    Integer.parseInt(dateStr.substring(8, 10)),
                    Integer.parseInt(dateStr.substring(11, 13)),
                    Integer.parseInt(dateStr.substring(14, 16)));
            cal.set(Calendar.MILLISECOND, 0);
            return (cal.getTime().getTime());
        } else if (dateStr.length() == 14) {
            cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                    Integer.parseInt(dateStr.substring(4, 6)) - 1,
                    Integer.parseInt(dateStr.substring(6, 8)),
                    Integer.parseInt(dateStr.substring(8, 10)),
                    Integer.parseInt(dateStr.substring(10, 12)),
                    Integer.parseInt(dateStr.substring(12, 14)));
            cal.set(Calendar.MILLISECOND, 0);
            return (cal.getTime().getTime());
        } else if (dateStr.length() == 10 || dateStr.length() == 11) {
            cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                    Integer.parseInt(dateStr.substring(5, 7)) - 1,
                    Integer.parseInt(dateStr.substring(8, 10)), 0, 0, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return (cal.getTime().getTime());
        } else if (dateStr.length() == 8) {
            cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                    Integer.parseInt(dateStr.substring(4, 6)) - 1,
                    Integer.parseInt(dateStr.substring(6, 8)), 0, 0, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return (cal.getTime().getTime());
        } else {
            return Long.parseLong(dateStr);
        }
    }

    /**
     * 根据时间戳，获取当天凌晨时间  2019-10-18 00:00:00.0
     *
     * @return {@link Timestamp}
     */
    public static Timestamp getCurrentDayStartTime() {
        //当日零点零分零秒的毫秒数
        long zero = todayZeroTime();
        return new Timestamp(zero);
    }

    /**
     * 根据时间戳，获取当天最晚时间段  2019-10-18 23:59:59.999
     *
     * @return {@link Timestamp}
     */
    public static Timestamp getCurrentDayEndTime() {
        long zero = todayZeroTime();
        //今天23点59分59秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;
        return new Timestamp(twelve);
    }

    /**
     * 今天零点零分零秒的毫秒数
     *
     * @return 今天零点零分零秒的毫秒数
     */
    private static long todayZeroTime() {
        return System.currentTimeMillis() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }

    @Override
    public Date convert(String source)  {
        String value = source.trim();
        if (StringUtils.EMPTY.equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            try {
                return parseDate(source, FOR_MARTS.get(0));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            try {
                return parseDate(source, FOR_MARTS.get(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$")) {
            try {
                return parseDate(source, FOR_MARTS.get(2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            try {
                return parseDate(source, FOR_MARTS.get(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
    }

    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    public Date parseDate(String dateStr, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateStr);
    }
}