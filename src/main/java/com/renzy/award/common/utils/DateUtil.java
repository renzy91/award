package com.renzy.award.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class DateUtil extends DateUtils {

    private static final String PATTERN_DATETIME_TZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATETIME_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PATTERN_YEAR_MONTH = "yyyy-MM";
    private static final String PATTERN_YEAR = "yyyy";
    public  static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

    public static String formatCurrentDateTime() {
        return formatDateTime(new Date());
    }

    public static String formatCurrentDate() {
        return formatDate(new Date());
    }

    public static String formatDateTimeSSS(Date date) {
        return format(PATTERN_DATETIME_SSS, date);
    }

    public static String formatDateTime(Date date) {
        return format(PATTERN_DATETIME, date);
    }

    public static String formatDate(Date date) {
        return format(PATTERN_DATE, date);
    }

    public static String formatYear(Date date) {
        return format(PATTERN_YEAR, date);
    }

    public static String formatYearMonth(Date date) {
        return format(PATTERN_YEAR_MONTH, date);
    }

    public static String formatDateTimeTZ(Date date) {
        return format(PATTERN_DATETIME_TZ, date);
    }

    public static Date parseDateTimeSSS(String str) {
        return parse(PATTERN_DATETIME_SSS, str);
    }

    public static Date parseDateTime(String str) {
        return parse(PATTERN_DATETIME, str);
    }

    public static Date parseDate(String str) {
        return parse(PATTERN_DATE, str);
    }

    public static Date parseYear(String str) {
        return parse(PATTERN_YEAR, str);
    }

    public static Date parseYearMonth(String str) {
        return parse(PATTERN_YEAR_MONTH, str);
    }

    public static Date parseDateTimeTZ(String str) {
        return parse(PATTERN_DATETIME_TZ, str);
    }

    public static String format(String pattern, Date date) {
        if (null == date) {
            return null;
        }

        SimpleDateFormat FORMATTER = new SimpleDateFormat();
        FORMATTER.applyPattern(pattern);
        return FORMATTER.format(date);
    }

    public static Date parse(String pattern, String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        SimpleDateFormat FORMATTER = new SimpleDateFormat();

        FORMATTER.applyPattern(pattern);

        try {
            return FORMATTER.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当月起止日期时间
     * <p>
     * 以2015年10月份为例，返回的是2015-10-01 00:00:00 和 2015-11-01 00:00:00
     *
     * @param now
     * @return
     */
    public static Date getNextDay(Date now) {
        Calendar tmp = Calendar.getInstance();
        tmp.setTime(now);

        tmp.add(Calendar.DAY_OF_MONTH, 1);
        return tmp.getTime();
    }

    /**
     * 获取当月起止日期时间
     * <p>
     * 以2015年10月份为例，返回的是2015-10-01 00:00:00 和 2015-11-01 00:00:00
     *
     * @param now
     * @return
     */
    public static Pair<Date, Date> getMonthRange(Date now) {
        Calendar tmp = Calendar.getInstance();
        tmp.setTime(now);

        tmp.set(Calendar.DAY_OF_MONTH, 1);
        tmp.set(Calendar.HOUR_OF_DAY, 0);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        Date startDate = tmp.getTime();

        tmp.add(Calendar.MONTH, 1);
        Date endDate = tmp.getTime();

        return Pair.of(startDate, endDate);
    }

    /**
     * 以天为单位计算日期1是否早于日期2 (不含相等情况)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean beforeInDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (isSameDay(date1, date2)) {
            return false;
        } else {
            return date1.before(date2);
        }
    }

    /**
     * 以天为单位计算日期1是否早于日期2 (包含相等情况)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean beforeOrSameInDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return isSameDay(date1, date2) || date1.before(date2);
    }

    /**
     * 以天为单位计算日期1是否晚于日期2 (不含相等情况)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean afterInDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (isSameDay(date1, date2)) {
            return false;
        } else {
            return date1.after(date2);
        }
    }

    /**
     * 以天为单位计算日期1是否晚于日期2 (不含相等情况)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean afterOrSameInDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return isSameDay(date1, date2) || date1.after(date2);
    }

    /**
     * 查看指定日期是否在日期区间内
     *
     * @param target
     * @param from
     * @param to
     * @return
     */
    public static boolean isDateInRange(Date target, Date from, Date to) {

        if (target == null) {
            throw new IllegalArgumentException("The target date must not be null");
        }

        if (from == null) {
            throw new IllegalArgumentException("The from date must not be null");
        }

        if (to == null) {
            throw new IllegalArgumentException("The to date must not be null");
        }

        return beforeOrSameInDay(from, target) && afterOrSameInDay(to, target);

    }

    public static Pair<Date, Date> getDateRange(Date start, int period, int index) {

        Date startDate = getStartDate(start, period, index);
        Date endDate = getEndDate(start, period, index);

        return Pair.of(startDate, endDate);
    }

    public static Pair<Date, Date> getDateRangeV2(Date start, int period, int index) {

        Date startDate = getStartDateV2(start, period, index);
        Date endDate = getEndDateV2(start, period, index);

        return Pair.of(startDate, endDate);
    }

    public static Date getStartDate(Date start, int period, int index) {

        if (index < 1) {
            return null;
        }

        int amount = period * (index - 1);
        Date startDate = addNaturalMonths(start, amount);

        return startDate;
    }

    public static Date getStartDateV2(Date start, int period, int index) {

        if (index < 1) {
            return null;
        }

        int amount = period * (index - 1);
        Date startDate = addNaturalMonthsV2(start, amount);

        return startDate;
    }

    public static Date getEndDate(Date start, int period, int index) {

        if (index < 1) {
            return null;
        }

        Date nextStartDate = getStartDate(start, period, ++index);
        Date endDate = addDays(nextStartDate, -1);

        return endDate;
    }

    public static Date getEndDateV2(Date start, int period, int index) {

        if (index < 1) {
            return null;
        }

        Date nextStartDate = getStartDateV2(start, period, ++index);
        Date endDate = addDays(nextStartDate, -1);

        return endDate;
    }

    /**
     * 获取日期区间列表
     *
     * @param start
     * @param end
     * @param period
     * @return
     */
    public static List<Pair<Date, Date>> getDateRangeList(Date start, Date end, int period) {

        if (start == null || end == null) {
            return null;
        }
        if (!isSameDay(start, end) && start.after(end)) {
            return null;
        }
        if (period < 1) {
            return null;
        }

        int index = 1;

        Date subStart = null;
        Date subEnd = null;

        List<Pair<Date, Date>> rangeList = new ArrayList<Pair<Date, Date>>();

        do {

            Pair<Date, Date> range = getDateRange(start, period, index++);
            subStart = range.getLeft();
            subEnd = range.getRight();

            if (subStart != null && subEnd != null) {
                if (isSameDay(subStart, end)) { // 开始日期等于总结束日期
                    rangeList.add(Pair.of(subStart, subStart));
                } else if (subStart.before(end)) { // 开始日期小于总结束日期
                    if (isSameDay(subEnd, end)) { // 结束日期等于总结束日期
                        rangeList.add(Pair.of(subStart, subEnd));
                    } else if (subEnd.before(end)) { // 结束日期小于总结束日期
                        rangeList.add(Pair.of(subStart, subEnd));
                    } else { // 结束日期大于总结束日期
                        rangeList.add(Pair.of(subStart, end));
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }

        } while (true);

        return rangeList;
    }

    /**
     * 获取日期区间列表
     *
     * @param start
     * @param end
     * @param period
     * @return
     */
    public static List<Pair<Date, Date>> getDateRangeListV2(Date start, Date end, int period) {

        if (start == null || end == null) {
            return null;
        }
        if (!isSameDay(start, end) && start.after(end)) {
            return null;
        }
        if (period < 1) {
            return null;
        }

        int index = 1;

        Date subStart = null;
        Date subEnd = null;

        List<Pair<Date, Date>> rangeList = new ArrayList<Pair<Date, Date>>();

        do {

            Pair<Date, Date> range = getDateRangeV2(start, period, index++);
            subStart = range.getLeft();
            subEnd = range.getRight();

            if (subStart != null && subEnd != null) {
                if (isSameDay(subStart, end)) { // 开始日期等于总结束日期
                    rangeList.add(Pair.of(subStart, subStart));
                } else if (subStart.before(end)) { // 开始日期小于总结束日期
                    if (isSameDay(subEnd, end)) { // 结束日期等于总结束日期
                        rangeList.add(Pair.of(subStart, subEnd));
                    } else if (subEnd.before(end)) { // 结束日期小于总结束日期
                        rangeList.add(Pair.of(subStart, subEnd));
                    } else { // 结束日期大于总结束日期
                        rangeList.add(Pair.of(subStart, end));
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }

        } while (true);

        return rangeList;
    }

    /**
     * 获取两个日期相差天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDiffDays(Date start, Date end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(start);
        c2.setTime(end);

        return (int) ((c2.getTime().getTime() - c1.getTime().getTime()) / 1000 / 3600 / 24);
    }

    /**
     * 获取两个日期之间包含几年几月几日
     *
     * @param start
     * @param end
     * @return
     */
    public static Triple<Integer, Integer, Integer> getIncludeYearMonthDay(Date start, Date end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (!isSameDay && start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int years = 0;
        int months = 0;
        int days = 0;

        Pair<Integer, Integer> diffMonthDay = getIncludeMonthDay(calStart, calEnd);

        int totalMonths = diffMonthDay.getLeft();

        years = totalMonths / 12;
        months = totalMonths % 12;
        days = diffMonthDay.getRight();

        Triple<Integer, Integer, Integer> triple = Triple.of(years, months, days);
        return triple;
    }

    /**
     * 获取两个日期之间包含几年几月几日
     *
     * @param start
     * @param end
     * @return
     */
    public static Triple<Integer, Integer, Integer> getIncludeYearMonthDayV2(Date start, Date end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (!isSameDay && start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int years = 0;
        int months = 0;
        int days = 0;

        Pair<Integer, Integer> diffMonthDay = getIncludeMonthDayV2(calStart, calEnd);

        int totalMonths = diffMonthDay.getLeft();

        years = totalMonths / 12;
        months = totalMonths % 12;
        days = diffMonthDay.getRight();

        Triple<Integer, Integer, Integer> triple = Triple.of(years, months, days);
        return triple;
    }

    /**
     * 获取两个日期之间包含几月几日
     *
     * @param start
     * @param end
     * @return
     */
    public static Pair<Integer, Integer> getIncludeMonthDay(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (!isSameDay && start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int months = 0;
        int days = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(calStart);
        c2.setTime(calEnd);

        int c1Year = c1.get(Calendar.YEAR);
        int c2Year = c2.get(Calendar.YEAR);

        int c1Month = c1.get(Calendar.MONTH) + 1;
        int c2Month = c2.get(Calendar.MONTH) + 1;

        int diffYears = c2Year - c1Year;
        int diffMonths = c2Month - c1Month;

        int totalDiffMonths = diffYears * 12 + diffMonths;

        if (totalDiffMonths == 0) { // 同一月份
            Date cursor = addNaturalMonths(calStart, 1);
            cursor = addDays(cursor, -1);
            if (isSameDay(cursor, calEnd)) {
                months = 1;
            } else {
                days = getIncludeDay(calStart, calEnd);
            }
        } else { // 不同月份
            Date cursor = addNaturalMonths(calStart, totalDiffMonths);
            cursor = addDays(cursor, -1);

            if (isSameDay(cursor, calEnd)) {
                months = totalDiffMonths;
            } else {
                if (cursor.before(calEnd)) {
                    months = totalDiffMonths;
                    cursor = addDays(cursor, 1);

                    Date cursor2 = addNaturalMonths(cursor, 1);
                    cursor2 = addDays(cursor2, -1);
                    if (isSameDay(cursor2, calEnd)) {
                        months++;
                    } else {
                        days = getIncludeDay(cursor, calEnd);
                    }
                } else {
                    months = totalDiffMonths - 1;
                    cursor = addNaturalMonths(calStart, months);
                    days = getIncludeDay(cursor, calEnd);
                }
            }

        }

        Pair<Integer, Integer> pair = Pair.of(months, days);
        return pair;

    }

    /**
     * 获取两个日期之间包含几月几日
     *
     * @param start
     * @param end
     * @return
     */
    public static Pair<Integer, Integer> getIncludeMonthDayV2(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (!isSameDay && start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int months = 0;
        int days = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(calStart);
        c2.setTime(calEnd);

        int c1Year = c1.get(Calendar.YEAR);
        int c2Year = c2.get(Calendar.YEAR);

        int c1Month = c1.get(Calendar.MONTH) + 1;
        int c2Month = c2.get(Calendar.MONTH) + 1;

        int diffYears = c2Year - c1Year;
        int diffMonths = c2Month - c1Month;

        int totalDiffMonths = diffYears * 12 + diffMonths;

        if (totalDiffMonths == 0) { // 同一月份
            Date cursor = addNaturalMonthsV2(calStart, 1);
            cursor = addDays(cursor, -1);
            if (isSameDay(cursor, calEnd)) {
                months = 1;
            } else {
                days = getIncludeDay(calStart, calEnd);
            }
        } else { // 不同月份
            Date cursor = addNaturalMonthsV2(calStart, totalDiffMonths);
            cursor = addDays(cursor, -1);

            if (isSameDay(cursor, calEnd)) {
                months = totalDiffMonths;
            } else {
                if (cursor.before(calEnd)) {
                    months = totalDiffMonths;
                    cursor = addDays(cursor, 1);

                    Date cursor2 = addNaturalMonthsV2(cursor, 1);
                    cursor2 = addDays(cursor2, -1);
                    if (isSameDay(cursor2, calEnd)) {
                        months++;
                    } else {
                        days = getIncludeDay(cursor, calEnd);
                    }
                } else {
                    months = totalDiffMonths - 1;
                    cursor = addNaturalMonthsV2(calStart, months);
                    days = getIncludeDay(cursor, calEnd);
                }
            }

        }

        Pair<Integer, Integer> pair = Pair.of(months, days);
        return pair;

    }

    /**
     * 获取两个日期之间包含天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIncludeDay(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (isSameDay) {
            return 1;
        } else if (start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int days = getDiffDays(calStart, calEnd) + 1;

        return days;
    }

    /**
     * 是否闰年
     *
     * @param date
     * @return
     */
    public static boolean isLeapYear(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);

        int year = c1.get(Calendar.YEAR);

        return isLeapYear(year);
    }

    /**
     * 是否闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {

        boolean isLeapYear = false;
        if (year % 4 == 0 && year % 100 != 0) {
            isLeapYear = true;
        } else if (year % 400 == 0) {
            isLeapYear = true;
        }
        return isLeapYear;
    }

    /**
     * 添加自然年数，对闰年进行特殊处理
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addNaturalYears(Date date, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Date naturalYear = null;

        Date addYear = addYears(date, amount);

        if (isLeapYear(date)) {
            naturalYear = addYear;
        } else {
            if (isLeapYear(addYear)) {
                Calendar c1 = Calendar.getInstance();
                c1.setTime(date);

                int month = c1.get(Calendar.MONTH) + 1;
                int day = c1.get(Calendar.DATE);
                if (month == 2 && day == 28) { // 对2月28日特殊处理
                    naturalYear = addDays(addYear, 1);
                } else {
                    naturalYear = addYear;
                }
            } else {
                naturalYear = addYear;
            }
        }

        return naturalYear;
    }

    /**
     * 添加自然年数，对闰年进行特殊处理
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addNaturalYearsV2(Date date, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Date naturalYear = addYears(date, amount);

        return naturalYear;
    }

    /**
     * 添加自然月数，对月末几天进行特殊处理
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addNaturalMonths(Date date, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Date naturalMonth = null;

        Date addMonth = addMonths(date, amount);

        if (isLastDayOfMonth(date)) {
            naturalMonth = getLastDayOfMonth(addMonth);
        } else {
            if (isLastDayOfMonth(addMonth)) {
                naturalMonth = addDays(addMonth, -1);
            } else {
                naturalMonth = addMonth;
            }
        }

        return naturalMonth;
    }

    /**
     * 添加自然月数，对月末几天进行特殊处理
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addNaturalMonthsV2(Date date, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Date naturalMonth = addMonths(date, amount);

        return naturalMonth;
    }

    /**
     * 获取当月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Date addMonth = addMonths(date, 1);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(addMonth);
        c1.set(Calendar.DATE, 1);

        Date time = c1.getTime();
        Date addDays = addDays(time, -1);

        return addDays;
    }

    /**
     * 某天是否为所在月最后一天
     *
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Date addDay = addDays(date, 1);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int c1Month = c1.get(Calendar.MONTH) + 1;

        Calendar c2 = Calendar.getInstance();
        c2.setTime(addDay);
        int c2Month = c2.get(Calendar.MONTH) + 1;

        return c1Month != c2Month;

    }

    /**
     * 获取日期所在年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int ciYear = c1.get(Calendar.YEAR);

        return ciYear;

    }

    /**
     * 获取日期所在月份,0-based
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int c1Month = c1.get(Calendar.MONTH);

        return c1Month;

    }

    /**
     * 获取日期所在月份,1-based,1月份为1,余下类推
     *
     * @param date
     * @return
     */
    public static int getOneBasedMonth(Date date) {
        int month = getMonth(date);
        return month + 1;
    }

    /**
     * 获取日期月中天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int c1Day = c1.get(Calendar.DATE);

        return c1Day;

    }

    /**
     * 获取目标时间与当前时间间隔的小时数
     *
     * @param targetDate
     * @return
     */
    public static int getDiffHourFromCurrent(Date targetDate) {
        long diff = System.currentTimeMillis() - targetDate.getTime();
        return (int) (diff / 1000 / 60 / 60);
    }

    /**
     * 获取目标时间与当前时间间隔的分钟数
     *
     * @param targetDate
     * @return
     */
    public static int getDiffMinutesFromCurrent(Date targetDate) {
        long diff = System.currentTimeMillis() - targetDate.getTime();
        return (int) (diff / 1000 / 60);
    }

    /**
     * 增加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 增加月数、天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addMonthAndDate(Date date, int months, int days) {
        date = addNaturalMonths(date, months);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 增加月数、天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addMonthAndDateV2(Date date, int months, int days) {
        date = addNaturalMonthsV2(date, months);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 计量两个日期之间包含几个付款周期
     *
     * @param start
     * @param end
     * @param period
     * @return
     */
    public static int calContains(Date start, Date end, int period) {
        Triple<Integer, Integer, Integer> t = getIncludeYearMonthDayV2(start, end);
        int years = t.getLeft();
        int months = t.getMiddle();
        int days = t.getRight();

        if (years > 0) {
            months += years * 12;
        }
        if (days > 0) {
            months += 1;
        }
        if (months % period == 0) {
            return months / period;
        } else {
            return months / period + 1;
        }
    }

    /**
     * 获取该日期前的第一个有效日
     *
     * @param date
     * @return
     */
    public static Date getBeforeValidDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取该日期后的第一个有效日
     *
     * @param date
     * @return
     */
    public static Date getAfterValidDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 天数转换为月数
     * 每月按30天计算
     *
     * @return
     */
    public static Pair<Integer, Integer> calDateInterval(int totalDays, int daysInOneMonth) {
        int months = totalDays / daysInOneMonth;
        int days = totalDays % daysInOneMonth;
        return Pair.of(months, days);
    }

    /**
     * 判断两个日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isDateEqualsInDay(Date date1, Date date2) {
        if (date1 == null) {
            return date2 == null;
        } else {
            return date2 != null && isSameDay(date1, date2);
        }
    }

    public static Date string2Date(String date){
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return DateUtils.parseDate(date.trim(),parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("日期类型转换错误");
        }
    }

}
