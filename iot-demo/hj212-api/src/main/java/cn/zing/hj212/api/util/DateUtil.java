package cn.zing.hj212.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 14:00
 */
public class DateUtil {

    public static final String DF_ALL23 = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String DF_ALL19 = "yyyy-MM-dd HH:mm:ss";
    public static final String DF_DAY19 = "yyyy-MM-dd 00:00:00";
    public static final String DF_HOUR19 = "yyyy-MM-dd HH:00:00";
    public static final String DF_SEC19 = "yyyy-MM-dd HH:mm:00";
    public static final String DF_SEC17 = "yyyy-MM-dd HH:mm";
    public static final String DF_HOUR14 = "yyyy-MM-dd HH";
    public static final String DF_APP10 = "yyyy-MM-dd";
    public static final String DF_APP8 = "yyyyMMdd";
    public static final String DF_YM = "yyyyMM";
    public static final String DF_Yw = "yyyyww";
    public static final String DF_Y = "yyyy";
    public static final String DF_SN12 = "yyyyMMddHHmm";
    public static final String DF_SALL17 = "yyyyMMddHHmmssSSS";
    public static final String DF_SALL14 = "yyyyMMddHHmmss";
    public static final String DF_SN15 = "yyMMddHHmmssSSS";
    public static final String DF_HMS9 = "HHmmssSSS";
    public static final String DF_HMS6 = "HHmmss";
    public static final String DF_HMSS12 = "HH:mm:ss SSS";
    public static final String DF_HMS8 = "HH:mm:ss";

    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(getCurrentDate());
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getLastDater() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getTomorrowDater() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static String getCurDate() {
        return new SimpleDateFormat(DF_APP10).format(getCurrentDate());
    }

    public static String getLastDate() {
        return new SimpleDateFormat(DF_APP10).format(getLastDater());
    }

    public static String getTomorrowDate() {
        return new SimpleDateFormat(DF_APP10).format(getTomorrowDater());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat(DF_ALL19).format(getCurrentDate());
    }

    public static String getCurrentMilliTime() {
        return new SimpleDateFormat(DF_ALL23).format(getCurrentDate());
    }

    public static String formatDate(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDF10(Date date) {
        return formatDate(DF_APP10, date);
    }

    public static String formatDF19(Date date) {
        if (date == null)
            return null;
        return formatDate(DF_ALL19, date);
    }

    public static Date parseDF19(String date) throws ParseException {
        return new SimpleDateFormat(DF_ALL19).parse(date);
    }

    public static Date parseDate(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    public static String formatDF19(Date date, String nullval) {
        if (null == date)
            return nullval;
        else
            return formatDate(DF_ALL19, date);
    }

    public static String formatDF10(Date date, String nullval) {
        if (null == date)
            return nullval;
        else
            return formatDate(DF_APP10, date);
    }

    public static String formatFromDF19ToDF14(String time) throws ParseException {
        Date date = parseDate(time, DateUtil.DF_ALL19);
        return formatDate(DateUtil.DF_SALL14, date);
    }

    /**
     * 对时间的指定字段进行加减yyyy-MM-01 00:00:00
     *
     * @return
     * @throws ParseException
     */
    public static String addTime(String time, int field, int amount) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 对日期的指定字段进行加减操作
     *
     * @param date   要加减的日期
     * @param field  要加减的字段
     * @param amount 要加减的数量
     * @return
     */
    public static Date addDate(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    public static String getFirstDayTimeOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    public static String getLatestDayTime(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    public static String getLatestDayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    public static String getNextDayTime(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    public static String getLastDayTime(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    public static String getNextDay(String day) throws ParseException {
        Date date = DateUtil.parseDate(day, DF_APP10);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF10(calendar.getTime());
    }

    public static String getLastDay(String day) throws ParseException {
        Date date = DateUtil.parseDate(day, DF_APP10);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF10(calendar.getTime());
    }

    public static String getLastDayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取每一月开始的第一个1小时时间点yyyy-MM-01 00:00:00
     *
     * @return
     */
    public static String getFirstHourTimeOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间最近的1小时时间点
     *
     * @param time 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getLatestHourTime(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取最近的1小时时间点
     *
     * @return
     */
    public static String getLatestHourTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间上一个1小时时间点
     *
     * @param time 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getLastHourTime(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间上一个1小时时间点
     *
     * @return
     */
    public static String getLastHourTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间最近的下一个小时时间点
     *
     * @param time 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getNextHourTime(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取每一月开始的第一个五分钟时间点yyyy-MM-01 00:00:00
     *
     * @return
     */
    public static String getFirstMin5TimeOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间最近的五分钟时间点
     *
     * @param time 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getLatestMin5Time(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int min = calendar.get(Calendar.MINUTE);
        int num = min % 5;
        calendar.add(Calendar.MINUTE, num * (-1));
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间最近的分钟时间点
     *
     * @param time         格式：yyyy-MM-dd HH:mm:ss
     * @param timeInterval 时间间隔
     * @return
     * @throws ParseException
     */
    public static String getLatestMinTime(String time, int timeInterval) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int min = calendar.get(Calendar.MINUTE);
        int num = min % timeInterval;
        calendar.add(Calendar.MINUTE, num == 0 ? 0 : timeInterval - num);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDate(DF_SEC19, calendar.getTime());
    }

    /**
     * 获取最近的五分钟时间点
     *
     * @return
     */
    public static String getLatestMin5Time() {
        Calendar calendar = Calendar.getInstance();
        int min = calendar.get(Calendar.MINUTE);
        int num = min % 5;
        calendar.add(Calendar.MINUTE, num * (-1));
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间上一个五分钟时间点
     *
     * @param time 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getLastMin5Time(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int min = calendar.get(Calendar.MINUTE);
        int num = min % 5;
        calendar.add(Calendar.MINUTE, num * (-1) - 5);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    public static String getLastMin5Time() {
        Calendar calendar = Calendar.getInstance();
        int min = calendar.get(Calendar.MINUTE);
        int num = min % 5;
        calendar.add(Calendar.MINUTE, num * (-1) - 5);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 获取一个时间最近的下一个五分钟时间点
     *
     * @param time 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getNextMin5Time(String time) throws ParseException {
        Date date = DateUtil.parseDate(time, DF_ALL19);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int min = calendar.get(Calendar.MINUTE);
        int num = min % 5;
        calendar.add(Calendar.MINUTE, 5 - num);
        calendar.set(Calendar.SECOND, 0);
        return DateUtil.formatDF19(calendar.getTime());
    }

    /**
     * 取得某天是一年中的多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(7);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得某天所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得某天所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /** */
    /**
     * 取得某一年共有多少周
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }

    /**
     * 获取某一年某一周中的日期
     *
     * @param year
     * @param week
     * @return
     * @description
     */
    public static List<String> getWeekDays(int year, int week) {
        List<String> list = new ArrayList<String>();
        Date date = getFirstDayOfWeek(year, week);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < 7; i++) {
            list.add(formatDate(DF_APP10, calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek1(int year, int week) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);
        Date firstDate = getFirstDayOfWeek(calFirst.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime());

        return firstDate;
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周 2009-01-04为2008年最后一周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek1(int year, int week) {
        Calendar calLast = Calendar.getInstance();
        calLast.set(year, 0, 7);
        Date firstDate = getLastDayOfWeek(calLast.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        Date lastDate = getLastDayOfWeek(cal.getTime());

        return lastDate;
    }

    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month - 1, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得下月的第一天
     *
     * @return
     */
    public static Date getFirstDayOfNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
        cal.set(Calendar.DAY_OF_MONTH, 1);// 日，设为一号
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 取得下月的第一天
     *
     * @return
     */
    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
        cal.set(Calendar.DAY_OF_MONTH, 1);// 日，设为一号
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getFirstDayOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date firstDay = calendar.getTime();
        return firstDay;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getLastDayOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date lastDay = calendar.getTime();
        return lastDay;
    }

    /**
     * 获取某半年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getFirstDayOfHalfYear(int year, int half) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, (half % 2) == 0 ? Calendar.JULY : Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = calendar.getTime();
        return firstDay;
    }

    /**
     * 获取某半年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getLastDayOfHalfYear(int year, int half) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, (half % 2) == 0 ? Calendar.DECEMBER : Calendar.JUNE);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        Date lastDay = calendar.getTime();
        return lastDay;
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3;
        } else if (quarter == 2) {
            month = 6;
        } else if (quarter == 3) {
            month = 9;
        } else if (quarter == 4) {
            month = 12;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    public static String getYYYYMM(Date date) {
        if (null != date) {
            return formatDate("yyyyMM", date);
        }
        return null;
    }

    public static String getCurYYYYMM() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year + (month > 9 ? "" : "0") + month;
    }

    public static String getCurYYYY() {
        return formatDate("yyyy", getCurrentDate());
    }

    public static String getLastYYYYMM() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return year + (month > 9 ? "" : "0") + month;
    }

    public static String getYYYYMM(String time) throws ParseException {
        Date date = parseDF19(time);
        return getYYYYMM(date);
    }

    public static boolean isValidDate(String format, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 是否为润年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一周的第一天
        cal.setTime(date);
        int num = cal.get(Calendar.WEEK_OF_YEAR);
        return num;
    }

    public static int getWeek() {
        return getWeek(new Date());
    }

    public static int getWeeksInYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        int weeks = cal.getWeeksInWeekYear();
        return weeks;
    }

    public static String getReportWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一周的第一天
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        return year + "" + (week < 10 ? "0" : "") + week;
    }

    public static String getReportWeek() {
        return getReportWeek(new Date());
    }

    /**
     * 上一个
     *
     * @param reportWeek
     * @return
     */
    public static String getLastReportWeek(String reportWeek) {
        if (StringUtil.isBlank(reportWeek) || reportWeek.length() < 6) {
            throw new IllegalArgumentException("周格式：yyyyWE");
        }
        int year = Integer.parseInt(reportWeek.substring(0, 4));
        int week = Integer.parseInt(reportWeek.substring(4, 6));
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        int weeks = cal.getWeeksInWeekYear();
        if (week < 1 || week > weeks) {
            throw new IllegalArgumentException("周数不在当年的范围之内");
        }
        if (week == 1) {
            year = year - 1;
            cal.set(Calendar.YEAR, year);
            week = cal.getWeeksInWeekYear();
        } else {
            week = week - 1;
        }
        return year + "" + (week < 10 ? "0" : "") + week;
    }

    /**
     * 下一个
     *
     * @param reportWeek
     * @return
     */
    public static String getNextReportWeek(String reportWeek) {
        if (StringUtil.isBlank(reportWeek) || reportWeek.length() < 6) {
            throw new IllegalArgumentException("周格式：yyyyWE");
        }
        int year = Integer.parseInt(reportWeek.substring(0, 4));
        int week = Integer.parseInt(reportWeek.substring(4, 6));
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        int weeks = cal.getWeeksInWeekYear();
        if (week < 1 || week > weeks) {
            throw new IllegalArgumentException("周值错误");
        }
        if (week == weeks) {
            year = year + 1;
            week = 1;
        } else {
            week = week + 1;
        }
        return year + "" + (week < 10 ? "0" : "") + week;
    }

    public static String getReportMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + "" + (month < 10 ? "0" : "") + month;
    }

    public static String getReportMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + "" + (month < 10 ? "0" : "") + month;
    }

    public static String getLastReportMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month == 1) {
            year = year - 1;
            month = 12;
        } else {
            month = month - 1;
        }
        return year + "" + (month < 10 ? "0" : "") + month;
    }

    public static String getNextReportMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month == 12) {
            year = year + 1;
            month = 1;
        } else {
            month = month + 1;
        }
        return year + "" + (month < 10 ? "0" : "") + month;
    }

    public static String getLastReportMonth(String reportMonth) {
        if (StringUtil.isBlank(reportMonth) || reportMonth.length() < 6) {
            throw new IllegalArgumentException("月格式：yyyyMO");
        }
        int year = Integer.parseInt(reportMonth.substring(0, 4));
        int month = Integer.parseInt(reportMonth.substring(4, 6));
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("月度值错误");
        }
        if (month == 1) {
            year = year - 1;
            month = 12;
        } else {
            month = month - 1;
        }
        return year + "" + (month < 10 ? "0" : "") + month;
    }

    public static String getNextReportMonth(String reportMonth) {
        if (StringUtil.isBlank(reportMonth) || reportMonth.length() < 6) {
            throw new IllegalArgumentException("月格式：yyyyMO");
        }
        int year = Integer.parseInt(reportMonth.substring(0, 4));
        int month = Integer.parseInt(reportMonth.substring(4, 6));
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("月度值错误");
        }
        if (month == 12) {
            year = year + 1;
            month = 1;
        } else {
            month = month + 1;
        }
        return year + "" + (month < 10 ? "0" : "") + month;
    }

    public static String getReportQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int quarter = calendar.get(Calendar.MONTH) / 3 + 1;
        return year + "0" + quarter;
    }

    public static String getReportQuarter() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int quarter = calendar.get(Calendar.MONTH) / 3 + 1;
        return year + "0" + quarter;
    }

    public static String getLastReportQuarter(String reportQuarter) {
        if (StringUtil.isBlank(reportQuarter) || reportQuarter.length() < 6) {
            throw new IllegalArgumentException("季度格式：yyyyQU");
        }
        int year = Integer.parseInt(reportQuarter.substring(0, 4));
        int quarter = Integer.parseInt(reportQuarter.substring(4, 6));
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("季度值必须为01,02,03,04");
        }
        if (quarter == 1) {
            year = year - 1;
            quarter = 4;
        } else {
            quarter = quarter - 1;
        }
        return year + "" + (quarter < 10 ? "0" : "") + quarter;
    }

    public static String getNextReportQuarter(String reportQuarter) {
        if (StringUtil.isBlank(reportQuarter) || reportQuarter.length() < 6) {
            throw new IllegalArgumentException("季度格式：yyyyQU");
        }
        int year = Integer.parseInt(reportQuarter.substring(0, 4));
        int quarter = Integer.parseInt(reportQuarter.substring(4, 6));
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("季度值必须为01,02,03,04");
        }
        if (quarter == 4) {
            year = year + 1;
            quarter = 1;
        } else {
            quarter = quarter + 1;
        }
        return year + "" + (quarter < 10 ? "0" : "") + quarter;
    }

    public static String getReportHalfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int halfYear = month < 6 ? 1 : 2;
        return year + "0" + halfYear;
    }

    public static String getReportHalfYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int halfYear = month < 6 ? 1 : 2;
        return year + "0" + halfYear;
    }

    public static String getLastHalfYear(String reportHalfYear) {
        if (StringUtil.isBlank(reportHalfYear) || reportHalfYear.length() < 6) {
            throw new IllegalArgumentException("半年格式：yyyyHY");
        }
        int year = Integer.parseInt(reportHalfYear.substring(0, 4));
        int hyear = Integer.parseInt(reportHalfYear.substring(4, 6));
        if (hyear < 1 || hyear > 2) {
            throw new IllegalArgumentException("半年值必须为01,02");
        }
        if (hyear == 1) {
            year = year - 1;
            hyear = 2;
        } else {
            hyear = hyear - 1;
        }
        return year + "" + (hyear < 10 ? "0" : "") + hyear;
    }

    public static String getNextReportHalfYear(String reportHalfYear) {
        if (StringUtil.isBlank(reportHalfYear) || reportHalfYear.length() < 6) {
            throw new IllegalArgumentException("半年格式：yyyyHY");
        }
        int year = Integer.parseInt(reportHalfYear.substring(0, 4));
        int hyear = Integer.parseInt(reportHalfYear.substring(4, 6));
        if (hyear < 1 || hyear > 2) {
            throw new IllegalArgumentException("半年值必须为01,02");
        }
        if (hyear == 2) {
            year = year + 1;
            hyear = 1;
        } else {
            hyear = hyear + 1;
        }
        return year + "" + (hyear < 10 ? "0" : "") + hyear;
    }

    public static String getReportYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year + "";
    }

    public static String getReportYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year + "";
    }

    public static String getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year + "";
    }

    public static String getLastYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - 1;
        return year + "";
    }

    public static String getNextYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) + 1;
        return year + "";
    }

    public static String getLastReportYear(String reportYear) {
        if (StringUtil.isBlank(reportYear) || reportYear.length() != 4) {
            throw new IllegalArgumentException("年格式：yyyy");
        }
        int year = Integer.parseInt(reportYear);
        year = year - 1;
        return year + "";
    }

    public static String getNextReportYear(String reportYear) {
        if (StringUtil.isBlank(reportYear) || reportYear.length() != 4) {
            throw new IllegalArgumentException("年格式：yyyy");
        }
        int year = Integer.parseInt(reportYear);
        year = year + 1;
        return year + "";
    }

    /**
     * 计算两个数据的小时差
     *
     * @param date
     * @param otherDate
     * @return
     * @throws ParseException
     */

    public static String getIntervalHours(String date, String otherDate) throws ParseException {
        return getIntervalHours(parseDF19(date), parseDF19(otherDate));
    }

    /**
     * 计算两个数据的小时差
     *
     * @param date
     * @param otherDate
     * @return
     * @throws ParseException
     */

    public static String getIntervalMins(String date, String otherDate) throws ParseException {
        return getIntervalMins(parseDF19(date), parseDF19(otherDate));
    }

    /**
     * 计算两个数据的日期差
     *
     * @param date
     * @param otherDate
     * @return
     * @throws ParseException
     */

    public static String getIntervalDays(String date, String otherDate, String format) throws ParseException {
        return getIntervalDays(parseDate(date, format), parseDate(otherDate, format));
    }

    /**
     * 计算两个数据的小时差
     *
     * @param date
     * @param otherDate
     * @return
     */

    public static String getIntervalHours(Date date, Date otherDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long startDate = calendar.getTimeInMillis();
        calendar.setTime(otherDate);
        long endDate = calendar.getTimeInMillis();
        if (startDate > endDate) {
            return null;
        } else {
            long between_hours = (endDate - startDate) / 3600000L;
            return String.valueOf(between_hours);
        }
    }

    /**
     * 计算两个数据的时间差
     *
     * @param date
     * @param otherDate
     * @return
     */

    public static String getIntervalMins(Date date, Date otherDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long startDate = calendar.getTimeInMillis();
        calendar.setTime(otherDate);
        long endDate = calendar.getTimeInMillis();
        if (startDate > endDate) {
            return null;
        } else {
            Double between_hours = (endDate - startDate) / 60000d;
            return String.valueOf(between_hours);
        }
    }

    /**
     * 计算两个数据的时间差
     *
     * @param date
     * @param otherDate
     * @return
     */

    public static String getIntervalDays(Date date, Date otherDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long startDate = calendar.getTimeInMillis();
        calendar.setTime(otherDate);
        long endDate = calendar.getTimeInMillis();
        if (startDate > endDate) {
            return null;
        } else {
            Double between_hours = (endDate - startDate) / 86400000d;
            return String.valueOf(between_hours);
        }
    }

    /**
     * 时间排序
     *
     * @param date
     * @return
     */

    public static String sortTime(String date) {

        List<String> dates = Arrays.asList(date.split(","));
        Collections.sort(dates, (a, b) -> {
            return a.compareTo(b);
        });
        String result = "";
        for (int i = 0; i < dates.size(); i++) {
            if (i == 0) {
                result = dates.get(0);
            } else {
                result += "," + dates.get(i);
            }
        }
        return result;
    }

    public static String getSeqNo(String appCode) {
        return appCode + DateUtil.getCurrentDate(DateUtil.DF_SALL17);
    }

    public static int getBetweenDays(String stime, String etime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = df.parse(stime);
        Date eDate = df.parse(etime);
        return getDiffDays(sdate, eDate);
    }

    /**
     * 比较两个数据的范围大小
     *
     * @param oldStr "0.56~0.85"
     * @param newStr "0.9"
     * @return "0.56~0.9"
     */
    public static String comparMergeStringRange(String oldStr, String newStr) {
        List<String> oldStrList = Arrays.asList(oldStr.split("~"));
        String resultStr = "";
        if (null != oldStrList && oldStrList.size() == 1) {
            if (Double.parseDouble(newStr) < Double.parseDouble(oldStrList.get(0))) {
                resultStr += newStr + "~" + oldStrList.get(0);
            } else if (Double.parseDouble(newStr) == Double.parseDouble(oldStrList.get(0))) {
                resultStr += newStr;
            } else {
                resultStr += oldStrList.get(0) + "~" + newStr;
            }
        } else {
            if (Double.parseDouble(newStr) <= Double.parseDouble(oldStrList.get(0))) {
                resultStr += newStr + "~" + oldStrList.get(1);
            } else {
                if (Double.parseDouble(newStr) < Double.parseDouble(oldStrList.get(1))) {
                    resultStr += oldStrList.get(0) + "~" + oldStrList.get(1);
                } else {
                    resultStr += oldStrList.get(0) + "~" + newStr;
                }
            }
        }
        return resultStr;
    }

    /**
     * 根据时间类型返回对应时间格式 （如2019-01-09,2019-01-08,2019-01-07,2019-01-06,2019-01-11
     * 合并成2019-01-06~2019-01-09、2019-01-11）
     *
     * @return
     */

    /**
     * 根据开始时间和结束时间分割时间，返回时间列表
     *
     * @param startTime
     * @param endTime
     * @param curTime   : min :表示以5分钟分割 hour:表示以小时分割，day：表示以天分割
     * @return
     * @throws ParseException
     */

    public static boolean isInTime(String startTime, String endTime, String curTime) throws ParseException {
        DateFormat fmt = new SimpleDateFormat(DF_SALL14);
        long begin = fmt.parse(startTime).getTime(); // 开始时间
        long end = fmt.parse(endTime).getTime(); // 结束时间
        long cur = fmt.parse(curTime).getTime(); // 结束时间
        if (begin <= cur && end >= cur) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param time
     * @return
     */
    @SuppressWarnings("unused")
    public static String getLastDayOfYearString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer year = Integer.parseInt(time.substring(0, 4));
        Integer month = Integer.parseInt(time.substring(4, 6));
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return sdf.format(calendar.getTime());
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param time
     * @return
     */
    @SuppressWarnings("unused")
    public static String getLastDayOfMonthString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer year = Integer.parseInt(time.substring(0, 4));
        Integer month = Integer.parseInt(time.substring(5, 7));
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return sdf.format(calendar.getTime());
    }

    public static List<String> getMonthBetweenDates(String startTime, String endTime, String dataType) throws ParseException {
        List<String> monthList = new ArrayList<>();
        // 定义起始日期
        Date startDate = new SimpleDateFormat(DF_ALL19).parse(startTime);
        // 定义结束日期
        Date endDate = new SimpleDateFormat(DF_ALL19).parse(endTime);
        // 定义日期实例
        Calendar calendar = Calendar.getInstance();
        // 设置日期起始时间
        calendar.setTime(startDate);
        // 判断是否到结束日期
        while (calendar.getTime().before(endDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            String month = sdf.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
            monthList.add(month);
        }
        return monthList;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int differentDaysByMillisecond(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse(endTime);
        Date date = format.parse(startTime);

        int days = (int) ((date1.getTime() - date.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 判断是否是日期的格式 2017-07-31 08:32:00
     *
     * @param timeStr
     * @return
     */
    public static boolean isDateFormatAsDFALL19(String timeStr) {
        String regex = "\\d{4}-\\d{2}-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(timeStr);
        return matcher.matches();
    }

    public static boolean isDateFormatAsDFAPP10(String timeStr) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(timeStr);
        return matcher.matches();
    }

    public static boolean isDateFormatAsDFYM6(String timeStr) {
        String regex = "\\d{4}\\d{2}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(timeStr);
        return matcher.matches();
    }
}


