package com.lockie.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DateUtil {

	public static String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String TIMEFORMATJOIN = "yyyyMMddHHmmss";
	public static String DATEFORMAT = "yyyy-MM-dd";
	public static String MONTHFORMAT = "yyyy-MM";
	public static String DATEFORMATJOIN = "yyyyMMdd";

	/**
	 * 把日期字符串格式化成日期类型
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date convert2Date(String dateStr, String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		simple.setLenient(false);
		try {
			return simple.parse(dateStr);
		} catch (ParseException e) {
			log.error("字符转日期异常", e);
		}
		return null;
	}

	public static Date convert2Date(String dateStr) {
		SimpleDateFormat simple = new SimpleDateFormat(TIMEFORMAT);
		simple.setLenient(false);
		try {
			return simple.parse(dateStr);
		} catch (ParseException e) {
			log.error("日期转字符异常", e);
		}
		return null;
	}

	public static Date convertDateFormat(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat simple = new SimpleDateFormat(DATEFORMAT);
		simple.setLenient(false);
		try {
			return simple.parse(dateStr);
		} catch (ParseException e) {
			log.error("日期转字符异常", e);
		}
		return null;
	}

	/**
	 * 把日期类型格式化成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String convert2String(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}

	/**
	 * 获取当前日期
	 * 
	 * @return Date
	 */
	public static Date currentDate() {
		return new Date();
	}

	/**
	 * 转sql的time格式
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp convertSqlTime(Date date) {
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	/**
	 * 转sql的日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date convertSqlDate(Date date) {
		java.sql.Date dateTemp = new java.sql.Date(date.getTime());
		return dateTemp;
	}

	/**
	 * 获取当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	/**
	 * 获取当前日期
	 * 
	 * @param
	 * @return
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat(TIMEFORMAT).format(new Date());
	}

	/**
	 * 获取当前日期
	 *
	 * @param
	 * @return
	 */
	public static Date getCurrDate() {
		return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 获取当前日期(年月日格式)
	 *
	 * @param
	 * @return
	 */
	public static String getCurrentDateYMD() {
		return new SimpleDateFormat(DATEFORMAT).format(new Date());
	}

	/**
	 * 获取当前日期(年月日格式) 20190801
	 *
	 * @param
	 * @return
	 */
	public static String getCurrentDateYMDStr() {
		return new SimpleDateFormat(DATEFORMATJOIN).format(new Date());
	}

	/**
	 * 获取当前日期(年月日格式) 20190801
	 *
	 * @param
	 * @return
	 */
	public static String getCurrentDateYMDHMSStr() {
		return new SimpleDateFormat(TIMEFORMATJOIN).format(new Date());
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static Long getTimestamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(getTimestamp());
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static Long getTimestampSecond() {
		return System.currentTimeMillis() / 1000L;
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static Integer getTimestampStandard() {
		return getTimestampSecond().intValue();
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static Integer getTimestampInt(Timestamp timestamp) {
		Long time = timestamp.getTime() / 1000L;
		return time.intValue();
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static Integer getTimestampInt() {
		return getTimestampSecond().intValue();
	}

	/**
	 * 获取月份的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取日期的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期下个月的月
	 *
	 * @param date
	 * @return
	 */
	public static int getNextMonthNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期下个月的年
	 *
	 * @param date
	 * @return
	 */
	public static int getNextMonthYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获取日期的时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR);
	}

	/**
	 * 获取日期的分种
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 获取日期的秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 获取星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek - 1;
	}

	/**
	 * 获取哪一年共有多少周
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekNumOfYear(c.getTime());
	}

	/**
	 * 取得某天是一年中的多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekNumOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 取得某天所在周的第一天
	 * 
	 * @param
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime();
	}

	/**
	 * 取得某天所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		return c.getTime();
	}

	/**
	 * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
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
	 * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周, 2009-01-04为
	 * 2008年最后一周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
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

	private static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(calendarField, amount);
			return c.getTime();
		}
	}

	/*
	 * 1则代表的是对年份操作， 2是对月份操作， 3是对星期操作， 5是对日期操作， 11是对小时操作， 12是对分钟操作， 13是对秒操作， 14是对毫秒操作
	 */

	/**
	 * 增加年
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYears(Date date, int amount) {
		return add(date, 1, amount);
	}

	/**
	 * 增加月
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonths(Date date, int amount) {
		return add(date, 2, amount);
	}

	/**
	 * 增加周
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addWeeks(Date date, int amount) {
		return add(date, 3, amount);
	}

	/**
	 * 增加天
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDays(Date date, int amount) {
		return add(date, 5, amount);
	}

	/**
	 * 增加时
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHours(Date date, int amount) {
		return add(date, 11, amount);
	}

	/**
	 * 增加分
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMinutes(Date date, int amount) {
		return add(date, 12, amount);
	}

	/**
	 * 增加秒
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addSeconds(Date date, int amount) {
		return add(date, 13, amount);
	}

	/**
	 * 增加毫秒
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return add(date, 14, amount);
	}

	/**
	 * time差
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static long diffTimes(Date before, Date after) {
		return after.getTime() - before.getTime();
	}

	/**
	 * 秒差
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static long diffSecond(Date before, Date after) {
		return (after.getTime() - before.getTime()) / 1000;
	}

	/**
	 * 分种差
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int diffMinute(Date before, Date after) {
		return (int) (after.getTime() - before.getTime()) / 1000 / 60;
	}

	/**
	 * 时差
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int diffHour(Date before, Date after) {
		return (int) ((after.getTime() - before.getTime()) / 1000 / 60 / 60);
	}

	/**
	 * 时差
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static Long diffHours(Date before, Date after) {
		return (after.getTime() - before.getTime()) / 1000 / 60 / 60;
	}

	/**
	 * 天数差
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public static int diffDay(Date before, Date after) {
		if (before == null || after == null) {
			return 0;
		}
		return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
	}

	/**
	 * 月差
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int diffMonth(Date before, Date after) {
		int monthAll = 0;
		int yearsX = diffYear(before, after);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(before);
		c2.setTime(after);
		int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		monthAll = yearsX * 12 + monthsX;
		int daysX = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
		if (daysX > 0) {
			monthAll = monthAll + 1;
		}
		return monthAll;
	}

	/**
	 * 年差
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int diffYear(Date before, Date after) {
		return getYear(after) - getYear(before);
	}

	/**
	 * 设置23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date setEndDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 设置00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date setStartDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTime();
	}

	public static Long[] getTimestamp(String beginDate, String endDate) {
		Long[] timestamp = new Long[2];
		long num = 1000;
		if (beginDate != null && endDate != null) {
			timestamp[0] = setStartDay(convert2Date(beginDate, DATEFORMAT)).getTime() / num;
			timestamp[1] = setEndDay(convert2Date(endDate, DATEFORMAT)).getTime() / num;
		}
		return timestamp;
	}

	public static String convertTimestamp(Long time) {
		return convert2String(new Date(time * 1000L), TIMEFORMAT);
	}

	public static String convertDate(Long time) {
		if (time == null) {
			return "";
		}
		return convert2String(new Date(time), DATEFORMAT);
	}

	public static String convertDateTimeStr(Long time) {
		if (time == null) {
			return "";
		}
		return convert2String(new Date(time), TIMEFORMAT);
	}

	public static Map<String, Long> getMondayToSunday() {
		Map<String, Long> map = new HashMap<String, Long>();
		Calendar cal = Calendar.getInstance();
		int n = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (n == 0) {
			n = 7;
		}
		cal.add(Calendar.DATE, -(7 + (n - 1)));// 上周一的日期
		Date monday = cal.getTime();
		map.put("monday", setStartDay(monday).getTime() / 1000L);
		cal.add(Calendar.DATE, 6);
		Date sunday = cal.getTime();
		map.put("sunday", setEndDay(sunday).getTime() / 1000L);
		return map;
	}

	/**
	 * 格式化日期
	 *
	 * @param time yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String time) {
		return new SimpleDateFormat(time);
	}

	/**
	 * 获取过去的时间
	 *
	 * @param past 已过的天数
	 * @return yyyy-MM-dd
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取过去的时间
	 *
	 * @param past 已过的天数
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getPastDateToString(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(TIMEFORMAT);
		String result = format.format(today);
		return result;
	}

	/**
	 * 通过时间戳获取date对象
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDateByTime(Long time) {
		if (time == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = format.format(calendar.getTime());
		return result;
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getTime(Date date, String patten) {
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat(patten);
		String result = format.format(calendar.getTime());
		return result;
	}

	/**
	 * 获取距离当天还有多少天
	 *
	 * @param date
	 * @return
	 */
	public static int getLeftDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		long dateTime = calendar.getTimeInMillis();

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		long todayTime = today.getTimeInMillis();

		return (int) ((dateTime - todayTime) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 获取距离date多少天的日期，addNum可以是负数
	 * 
	 * @param date
	 * @param addNum
	 * @return
	 */
	public static Date getDateByAddNum(Date date, int addNum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, addNum);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当月第一天
	 *
	 * @param
	 * @return
	 */
	public static long getFirstOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取该日期的下个月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstOfNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当月最后一天
	 *
	 * @param
	 * @return
	 */
	public static long getLastOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis() - 1;
	}

	/**
	 * 获取当月最后一天
	 *
	 * @param
	 * @return
	 */
	public static long getLastOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis() - 1;
	}

	/**
	 * 获取下几个月的第一天 0点0分0秒
	 * 
	 * @return
	 */
	public static Date getNextNumMonthFirstDay(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当月月份
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		return getTime(new Date(), "yyyy-MM");
	}

	/**
	 * 获取当月月份
	 *
	 * @return
	 */
	public static String getCurrentMonthNum() {
		return getTime(new Date(), "MM");
	}

	public static String getCurrentMonth(Date date) {
		return getTime(date, "yyyy-MM");
	}

	/**
	 * 获取上一月月份
	 */
	public static String getPreMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return getTime(calendar.getTime(), "yyyy-MM");
	}

	public static String getNextMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		return getTime(calendar.getTime(), "yyyy-MM");
	}

	public static String getNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return getTime(calendar.getTime(), "yyyy-MM");
	}

	/**
	 * 获取当月天数
	 * 
	 * @return
	 */
	public static int getDaysCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return getDaysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取当月天数
	 * 
	 * @return
	 */
	public static int getDaysCurrentMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getDaysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 剩余天数占用比
	 * 
	 * @return
	 */
	public static float getLastDaysRate() {
		int days = getDaysCurrentMonth();
		int currentDay = getDay(new Date());
		return (days - currentDay + 1.0f) / days;
	}

	/**
	 * 剩余天数占用比
	 * 
	 * @return
	 */
	public static float getLastDaysRate(Date date) {
		int days = getDaysCurrentMonth(date);
		int currentDay = getDay(date);
		return (days - currentDay + 1.0f) / days;
	}

	/**
	 * 使用天数占用比
	 * 
	 * @return
	 */
	public static float getUsedDaysRate() {
		int days = getDaysCurrentMonth();
		int currentDay = getDay(new Date());
		return (currentDay - 1.0f) / days;
	}

	/**
	 * 使用天数占用比
	 * 
	 * @return
	 */
	public static float getUsedDaysRate(Date date) {
		int days = getDaysCurrentMonth(date);
		int currentDay = getDay(date);
		return (currentDay - 1.0f) / days;
	}

	public static float getRangeDayRate(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		int startDay = start.get(Calendar.DAY_OF_MONTH);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int endDay = end.get(Calendar.DAY_OF_MONTH);

		return (endDay - startDay + 1.0f) / getDaysCurrentMonth(startDate);
	}

	public static float getYearNum(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		long time = (end.getTimeInMillis() - start.getTimeInMillis()) / (1000 * 60 * 60 * 24);

		return time / 365f;
	}

	/**
	 * 计算两个日期之间的间隔天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long startToEnd(Date startDate, Date endDate) {
		String[] startStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate).split("-");
		String[] endStr = new SimpleDateFormat("yyyy-MM-dd").format(endDate).split("-");
		Integer startYear = Integer.parseInt(startStr[0]);
		Integer startMonth = Integer.parseInt(startStr[1]);
		Integer startDay = Integer.parseInt(startStr[2]);
		Integer endYear = Integer.parseInt(endStr[0]);
		Integer endMonth = Integer.parseInt(endStr[1]);
		Integer endDay = Integer.parseInt(endStr[2]);
		LocalDate endLocalDate = LocalDate.of(endYear, endMonth, endDay);
		LocalDate startLocalDate = LocalDate.of(startYear, startMonth, startDay);
		return startLocalDate.until(endLocalDate, ChronoUnit.DAYS);
	}

	// 季度首月
	public static final int QUARTER_BEGIN_MONTHS[] = { 1, 4, 7, 10 };

	// 半年首月
	public static final int HALF_BEGIN_MONTHS[] = { 1, 6 };

	// 全年首月
	public static final int YEAR_BEGIN_MONTHS[] = { 1 };

	// 季度末尾月
	public static final int QUARTER_END_MONTHS[] = { 3, 6, 9, 12 };

	// 半年末尾月
	public static final int HALF_END_MONTHS[] = { 6, 12 };

	// 全年末尾月
	public static final int YEAR_END_MONTHS[] = { 12 };

	/**
	 * 当前月是否为指定月
	 * 
	 * @return
	 */
	public static boolean isContainMonths(int[] quarterMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		for (int i = 0; i < quarterMonths.length; i++) {
			if (currentMonth == quarterMonths[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 每月: 当前月最后一天 23:59:59
	 * 
	 * @return
	 */
	public static Date getCurrentMonthEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 每月: 下个月首天 00:00:00
	 * 
	 * @return
	 */
	public static Date getNextMonthBeginTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 每月: 下个月尾天 23:59:59
	 * 
	 * @return
	 */
	public static Date getNextMonthEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 季度: 下个季度 首天 00:00:00
	 * 
	 * @return
	 */
	public static Date getNextQuarterBeginTime() {
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 3) {
			calendar.set(Calendar.MONTH, 3);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			calendar.set(Calendar.MONTH, 6);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			calendar.set(Calendar.MONTH, 9);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			calendar.add(Calendar.YEAR, 1);
			calendar.set(Calendar.MONTH, 0);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 季度: 下个季度 尾天 23:59:59
	 * 
	 * @return
	 */
	public static Date getNextQuarterEndTime() {
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 3) {
			calendar.set(Calendar.MONTH, 5);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			calendar.set(Calendar.MONTH, 8);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			calendar.set(Calendar.MONTH, 11);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			calendar.add(Calendar.YEAR, 1);
			calendar.set(Calendar.MONTH, 2);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 季度: 当前季度 尾天 23:59:59
	 * 
	 * @return
	 */
	public static Date getCurrentQuarterEndTime() {
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 3) {
			calendar.set(Calendar.MONTH, 2);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			calendar.set(Calendar.MONTH, 5);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			calendar.set(Calendar.MONTH, 8);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			calendar.set(Calendar.MONTH, 11);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 半年: 下个半年 首天 00:00:00
	 * 
	 * @return
	 */
	public static Date getNextHalfBeginTime() {
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 6) {
			calendar.set(Calendar.MONTH, 6);
		} else if (currentMonth >= 7 && currentMonth <= 12) {
			calendar.add(Calendar.YEAR, 1);
			calendar.set(Calendar.MONTH, 0);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 半年: 下个半年 尾天 23:59:59
	 * 
	 * @return
	 */
	public static Date getNextHalfEndTime() {
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 6) {
			calendar.set(Calendar.MONTH, 11);
		} else if (currentMonth >= 7 && currentMonth <= 12) {
			calendar.add(Calendar.YEAR, 1);
			calendar.set(Calendar.MONTH, 5);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 半年: 当前半年 尾天 23:59:59
	 * 
	 * @return
	 */
	public static Date getCurrentHalfEndTime() {
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 6) {
			calendar.set(Calendar.MONTH, 5);
		} else if (currentMonth >= 7 && currentMonth <= 12) {
			calendar.set(Calendar.MONTH, 11);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 全年: 明年 首天 00:00:00
	 * 
	 * @return
	 */
	public static Date getNextYearBeginTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 全年: 明年 尾天 23:59:59
	 * 
	 * @return
	 */
	public static Date getNextYearEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 全年: 今年 尾天 23:59:59
	 * 
	 * @return
	 */
	public static Date getCurrentYearEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getYesterday(Date date) {
		if (null == date) {
			throw new NullPointerException("the date is null or empty!");
		}
		// 对日期的操作,我们需要使用 Calendar 对象
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		// 判断是星期几
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		Date incomeDate = calendar.getTime();
		if (dayOfWeek == 1 || dayOfWeek == 7) {
			// 递归
			return getYesterday(incomeDate);
		}
		return incomeDate;
	}

	/**
	 * 本日开始时间
	 * 
	 * @author: yuez
	 * @date: 2019/10/18 16:37
	 **/
	public static Date getThisDayStartDate() {
		LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		return Date.from(todayStart.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 本周一开始时间
	 * 
	 * @author: yuez
	 * @date: 2019/10/18 16:37
	 **/
	public static Date getThisWeekStartDate() {
		LocalDate now = LocalDate.now();
		LocalDate firstday = now.with(DayOfWeek.MONDAY);
		return Date.from(firstday.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 本周最后一天结束时间
	 * 
	 * @return
	 */
	public static Date getThisWeekEndDate() {
		LocalDate now = LocalDate.now();
		LocalDate lastWeekDay = now.with(DayOfWeek.SUNDAY);
		return Date.from(lastWeekDay.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
	}

	/**
	 * 本月一开始时间
	 * 
	 * @author: yuez
	 * @date: 2019/10/18 16:37
	 **/
	public static Date getThisMonthStartDate() {
		LocalDate now = LocalDate.now();
		LocalDate firstday = LocalDate.of(now.getYear(), now.getMonth(), 1);
		return Date.from(firstday.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 时分秒为“00:00:00”将该时间减去一秒钟
	 * 
	 * @author: chenjl
	 * @date: 2019/11/21
	 **/
	public static String minusOneSecond(String dateStr) {
		if (dateStr != null && !dateStr.contains("00:00:00")) {
			return dateStr;
		}
		SimpleDateFormat simple = new SimpleDateFormat(TIMEFORMAT);
		simple.setLenient(false);
		try {
			Date date = simple.parse(dateStr);
			date.setTime(date.getTime() - 1000);
			return simple.format(date);
		} catch (ParseException e) {
			log.error("时分秒为“00:00:00”转字符异常", e);
			return dateStr;
		}
	}

	public static String getDayAndHour(Integer hours) {
		if (hours == null || hours == 0) {
			return "-";
		}
		if (hours < 24) {
			return hours + "小时";
		}
		Integer day = hours / 24;
		Integer hour = hours % 24;
		if (hour == 0) {
			return day + "天";
		}
		return MessageFormat.format("{0}天{1}小时", day, hour);
	}

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// 判断 并转换时间格式 ditNumber = 43607.4166666667
	public static Date getDitNumberTime(String ditNumber) {
		// 如果不是数字
		if (!isNumeric(ditNumber)) {
			return null;
		}
		// 如果是数字 小于0则 返回
		BigDecimal bd = new BigDecimal(ditNumber);
		int days = bd.intValue();// 天数
		int mills = (int) Math.round(bd.subtract(new BigDecimal(days)).doubleValue() * 24 * 3600);

		// 获取时间
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, 1);
		c.add(Calendar.DATE, days - 2);
		int hour = mills / 3600;
		int minute = (mills - hour * 3600) / 60;
		int second = mills - hour * 3600 - minute * 60;
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);

		return c.getTime();
	}

	// 校验是否数据含小数点
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+\\.*[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	// 获取某日期前一天半夜23:59:59
	public static Date getBeforedayEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		return cal.getTime();
	}

	//获取上个月的第一天
	public static Date getFrontFirstDay(){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
	}

	//获取上个月的最后一天
	public static Date getFrontLastDay(){
		Calendar calendar=Calendar.getInstance();
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	//根据传入日期数字获取当月该天 格式：2020-01-01 23:59:59
	public static Date getInputCurrenMonthEndDay(int endDay){
		Calendar calendar=Calendar.getInstance();
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, endDay);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	//根据传入日期数字获取上月该天 格式：2020-01-01 00:00:00
	public static Date getInputFrontMonthStartDay(int startDay){
		Calendar calendar=Calendar.getInstance();
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, startDay);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTime();
	}

	//转换日期去掉毫秒
	public static Date covertTime(Date date)  {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(TIMEFORMAT);
		Timestamp now = new Timestamp(date.getTime());
		String str = df.format(now);
		Date newDate = null;
		try {
			newDate = df.parse(str);
		} catch (ParseException e) {
			log.error("转换日期去掉毫秒异常");
		}
		return newDate;
	}
}
