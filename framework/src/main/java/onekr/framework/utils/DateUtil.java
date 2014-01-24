/**
 * @Project: main-framework
 * @File: DateUtil.java
 * @package onekr.framework.utils
 * @Description:
 * @author micwing
 * @date 2013-3-26 下午4:52:45
 * @version V1.0
 *
 * Copyright (c) 2013 OneKr Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.framework.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/** 
 * @ClassName: DateUtil 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:32:31 
 */ 
public final class DateUtil {

	String COOKIE_KEY_USERID = "uid";

	static final DateFormat YYYYMMDDHHMMSS_FORMAT = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss");

	static final DateFormat YYYYMMDD_FORMAT = new java.text.SimpleDateFormat(
			"yyyyMMdd");

	static final DateFormat YYYY_MM_DD_FORMAT = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	static final DateFormat CN_TIME_FORMAT = new java.text.SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm:ss");
	
	static final DateFormat YYYYMMDD2_FORMAT = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	
	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式：yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_2 = "yyyy-MM-dd";

	/**
	 * 格式化日期函数 内部使用
	 * 
	 * 根据指定格式对当前日期进行格式化
	 * 
	 * @param date
	 *            当前日期
	 * @param format
	 *            需要转化的格式
	 * @return String 转换后的字符串格式日期
	 */
	public static String parseDate(Date date, String format) {

		SimpleDateFormat dateformat = new SimpleDateFormat(format);

		return dateformat.format(date);
	}

	/**
	 * get current time
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getCurTime() {
		return new Timestamp(Calendar.getInstance().getTime().getTime());
	}

	public static String getTime() {
		Timestamp time = new Timestamp(Calendar.getInstance().getTime()
				.getTime());
		return time.toString();
	}

	/**
	 * 把yyyyMMdd格式的字符串转换成Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date getDateOfShortStr(String dateStr) {
		java.sql.Date da = null;
		try {
			da = new java.sql.Date(YYYYMMDD_FORMAT.parse(dateStr).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 把日期转换成 yyyyMMdd格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortStrDate(java.util.Date date) {
		return YYYYMMDD_FORMAT.format(date);
	}

	/**
	 * 把日期转换成 yyyyMMddHHmmss格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortStrDateTime(java.util.Date date) {
		return YYYYMMDDHHMMSS_FORMAT.format(date);
	}

	/**
	 * 把yyyyMMdd格式字符串转换成 java.sql.Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date getSqlDateByShortStr(String dateStr) {
		java.sql.Date da = null;
		try {
			da = new java.sql.Date(YYYYMMDD_FORMAT.parse(dateStr).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 把yyyyMMddHHmmss格式字符串转换成 java.sql.Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date getSqlDateTimeByShortStr(String dateStr) {
		java.sql.Date da = null;
		try {
			da = new java.sql.Date(YYYYMMDDHHMMSS_FORMAT.parse(dateStr)
					.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 把yyyyMMdd格式字符串转换成 java.util.Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.util.Date getUtilDateByShortStr(String datestr) {
		try {
			return YYYYMMDD_FORMAT.parse(datestr);
		} catch (ParseException e) {
			throw new RuntimeException("参数格式错误");
		}
	}

	/**
	 * 把yyyyMMddHHmmss格式字符串转换成 java.util.Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.util.Date getUtilDateTimeByShortStr(String datestr) {
		try {
			return YYYYMMDDHHMMSS_FORMAT.parse(datestr);
		} catch (ParseException e) {
			throw new RuntimeException("参数格式错误");
		}
	}

	public static int getDaysofMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		cld.set(5, 1);
		cld.add(2, 1);
		cld.add(6, -1);
		int days = cld.get(5);
		return days;
	}

	public static int getWeeksofMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		cld.set(5, 1);
		cld.add(2, 1);
		cld.add(6, -1);
		int weeks = cld.get(4);
		return weeks;
	}

	public static boolean isCurrentMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		int currentYear = cld.get(1);
		int currentMonth = cld.get(2);
		cld.setTime(date1);
		int year = cld.get(1);
		int month = cld.get(2);
		boolean currentFlag = false;
		if (currentYear == year && currentMonth == month)
			currentFlag = true;
		return currentFlag;
	}

	public static int getFirstDayofWeek(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		cld.set(5, 1);
		int dayOfFirstSunday = cld.get(7) - 1;
		return dayOfFirstSunday;
	}

	public static int getFirstWeekofMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		cld.set(5, 1);
		int week = cld.get(3);
		return week;
	}

	public static Date getFirstDayofMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		cld.set(5, 1);
		return cld.getTime();
	}

	public static Date getLastDayofMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		cld.set(5, 1);
		return cld.getTime();
	}

	public static int getDayofMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		int day = cld.get(5);
		return day;
	}

	public static int getMonth(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		int month = cld.get(2);
		return month;
	}

	public static int getYear(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		int year = cld.get(1);
		return year;
	}

	public static int getDayofWeek(Date date1) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		int dayOfSunday = cld.get(7);
		return dayOfSunday;
	}

	public static int getCurrentDayofMonth() {
		Calendar cld = Calendar.getInstance();
		int day = cld.get(5);
		return day;
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	
	/**
	 * 毫秒相加
	 * 
	 * @param date
	 *            日期
	 * @param mm
	 *            毫米数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, long mm) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + mm);
		return c.getTime();
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	public static int diffDateToHour(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60 * 60));
	}
	
	public static int diffDateToMinute(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60));
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	public static String formatDateTime(java.sql.Timestamp ts) {
		String temp = String.valueOf(ts);
		if (temp != null && temp != "" && temp != "null")
			temp = temp.substring(0, 16);
		else if (temp == "null")
			temp = "";
		return temp;
	}

	/**
	 * 判断是否同年同月
	 * 
	 * @param t1
	 *            日期1
	 * @param t2
	 *            日期2
	 * @return
	 */
	public static boolean isSameMonth(Timestamp t1, Timestamp t2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(t1);
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH);
		System.out.println("year1:" + year1 + "month1:" + month1);
		cal.setTime(t2);
		int year2 = cal.get(Calendar.YEAR);
		int month2 = cal.get(Calendar.MONTH);
		System.out.println("year2:" + year2 + "month2:" + month2);
		if (year1 == year2 && month1 == month2)
			return true;
		return false;
	}

	/**
	 * 把日期格式化成中文格式
	 */
	public static String parseCnDate(Date date) {
		return CN_TIME_FORMAT.format(date);
	}
	
	/**
	 * Convert Date to String
	 * 
	 * @param date
	 *            date
	 * @return string value
	 */
	public static String cnvDate2Str(Date date) {
		if (date == null) {
			return "";
		}
		// 时间格式
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_2);
		return sdf.format(date);
	}

	/**
	 * Convert Date to String
	 * 
	 * @param date
	 *            date
	 * @return string value.
	 */
	public static String cnvDate2StrF1(Date date) {
		if (date == null) {
			return "";
		}
		// 时间格式
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1);
		return sdf.format(date);
	}
	
	public static Date cnvStrF22Date(String datestr) {
		try {
			return YYYYMMDD2_FORMAT.parse(datestr);
		} catch (ParseException e) {
			throw new RuntimeException("参数格式错误");
		}
	}
	
	public static Date cnvStrF12Date(String datestr) {
		try {
			return YYYY_MM_DD_FORMAT.parse(datestr);
		} catch (ParseException e) {
			throw new RuntimeException("参数格式错误");
		}
	}
	
	/**
	 * 计算两个日期的时间差
	 * 
	 * @param formatTime1
	 * @param formatTime2
	 * @return
	 */
	public static String getTimeDifference(Date date1,
			Date date2) {
		SimpleDateFormat timeformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		long t1 = 0L;
		long t2 = 0L;
		try {
			t1 = timeformat.parse(getDateNumberFormat(date1))
					.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			t2 = timeformat.parse(getDateNumberFormat(date2))
					.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理

		long totalSeconds = (t1 -t2) / 1000;

		int days = (int) (totalSeconds / 3600 / 24);

		int hours = (int) ((totalSeconds - days * 3600 * 24) / 3600);

		int minutes = (int) ((totalSeconds - days * 3600 * 24 - hours * 3600) / 60);

		int seconds = (int) (totalSeconds - days * 3600 * 24 - hours * 3600 - minutes * 60);
		
		String hoursStr;
		
		String minutesStr;
		
		String secondsStr;
		if(String.valueOf(hours).length() ==1){
			hoursStr = "0"+hours;
		}else{
			hoursStr =""+hours;
		}
		
		if(String.valueOf(minutes).length() ==1){
			minutesStr = "0"+minutes;
		}else{
			minutesStr =""+minutes;
		}
		
		if(String.valueOf(seconds).length() ==1){
			secondsStr = "0"+seconds;
		}else{
			secondsStr =""+seconds;
		}

		return days + "天" + hoursStr + ":" + minutesStr + ":" + secondsStr;
	}

	/**
	 * 格式化时间 Locale是设置语言敏感操作
	 * 
	 * @param formatTime
	 * @return
	 */
	public static String getDateNumberFormat(Date date) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				new Locale("zh", "cn"));
		return m_format.format(date);
	}
	
	/**
	 * 给出的日期的前几天或后几天的计算方式，num为负，则为此日期的前num天(分钟)；为正，则为后num天(分钟)
	 * flag为d，是天数，为m是分钟数
	 * 返回的是yyyy-MM-dd HH:mm:ss的字符串类型
	 * @param date
	 * @param num
	 * @return
	 */

	public static String getDatePlusByType(Date date,int type,int num){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1);
		Calendar calendar = new GregorianCalendar();		
		calendar.clear();
		calendar.setTime(date);
		calendar.add(type,num);
	    String dateStr = sdf.format(calendar.getTime());
		return dateStr;		
	}
	/**
	 * 返回的是yyyy-MM-dd的字符串类型,type=Calendar.DATE或者其他
	 */
	public static String getDatePlusByType2(Date date,int type,int num){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_2);
		Calendar calendar = new GregorianCalendar();		
		calendar.clear();
		calendar.setTime(date);
		calendar.add(type,num);
	    String dateStr = sdf.format(calendar.getTime());
		return dateStr;		
	}

	/**
	 * 获取当前时间字符串
	 * 
	 * @return 当前时间字符串
	 */
	public static String getNow() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		return format.format(today);
	}
	
	/**
	 * 获取当前时间字符串
	 * 
	 * @return 当前时间字符串
	 */
	public static String getNow(String fmt) {
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		Date today = new Date();
		return format.format(today);
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String formatDate(Date date, String formatStr){
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
	
	/**
	 * 计算两个日期的工作时间差（去掉晚上6点之后到次日9点之前；去掉周末）
	 * @return
	 */
	public static long getTimeDiff(Date startDate, Date endDate) {
		
		/*
		 * step 调整开始时间
		 */
		String startDateStr = DateUtil.cnvDate2StrF1(startDate);
		int startHour = Integer.parseInt(startDateStr.substring(11, 13));
		
		//去掉晚上
		if (startHour < 9) {//将时间调整到9:00
			startDate = DateUtil.cnvStrF12Date(startDateStr.substring(0, 10) + " 09:00:00");
		} else if (startHour >= 18) {//将时间调整到次日9:00
			startDate = DateUtil.cnvStrF12Date(DateUtil.cnvDate2StrF1(DateUtil.addDate(startDate, 1)).substring(0, 10) + " 09:00:00");
		}
		
		//去掉周末
		Calendar sdate = Calendar.getInstance();
		sdate.setTimeInMillis(startDate.getTime());
		if (sdate.get(Calendar.DAY_OF_WEEK) == 7) {//周六
			startDate = DateUtil.cnvStrF12Date(DateUtil.cnvDate2StrF1(DateUtil.addDate(startDate, 2)).substring(0, 10) + " 09:00:00");
			sdate.setTimeInMillis(startDate.getTime());
		}
		if (sdate.get(Calendar.DAY_OF_WEEK) == 1) {//周日
			startDate = DateUtil.cnvStrF12Date(DateUtil.cnvDate2StrF1(DateUtil.addDate(startDate, 1)).substring(0, 10) + " 09:00:00");
			sdate.setTimeInMillis(startDate.getTime());
		}
				

		/*
		 * step 调整结束时间
		 */
		String endDateStr = DateUtil.cnvDate2StrF1(endDate);
		int endHour = Integer.parseInt(endDateStr.substring(11, 13));
		
		//去掉晚上
		if (endHour < 9) {//将时间调整到前一日的18:00
			endDate = DateUtil.cnvStrF12Date(DateUtil.cnvDate2StrF1(DateUtil.addDate(endDate, -1)).substring(0, 10) + " 18:00:00");
		} else if (endHour >= 18) {//将时间调整到18:00
			endDate = DateUtil.cnvStrF12Date(endDateStr.substring(0, 10) + " 18:00:00");
		}
		
		//去掉周末
		Calendar edate = Calendar.getInstance();
		edate.setTimeInMillis(endDate.getTime());
		if (edate.get(Calendar.DAY_OF_WEEK) == 7) {//周六
			endDate = DateUtil.cnvStrF12Date(DateUtil.cnvDate2StrF1(DateUtil.addDate(endDate, -1)).substring(0, 10) + " 18:00:00");
			edate.setTimeInMillis(endDate.getTime());
		}
		if (edate.get(Calendar.DAY_OF_WEEK) == 1) {//周日
			endDate = DateUtil.cnvStrF12Date(DateUtil.cnvDate2StrF1(DateUtil.addDate(endDate, -2)).substring(0, 10) + " 18:00:00");
			edate.setTimeInMillis(endDate.getTime());
		}
		
		
		/*
		 * step 计算时间差
		 */
		long millSecends = endDate.getTime() - startDate.getTime();
		
		
		/*
		 * step 去掉中间的周末
		 */
		Calendar snextM = getNextMonday(sdate);
		Calendar enextM = getNextMonday(edate);
		//获取这两个日期之间的实际天数
		int days = getDaysBetween(snextM, enextM);
		//获取这两个日期之间的非工作日数(两个周一之间的天数肯定能被7整除，并且工作日数量占其中的2/7)
		int weekEndNum = days/7*2; 
		if (weekEndNum != 0) {
			millSecends -= weekEndNum*24*60*60*1000;
		}
		
		/*
		 * step 去掉中间的晚上
		 */
		int dayNum = DateUtil.diffDate(endDate, startDate);
		int endTimeInt = Integer.parseInt(DateUtil.cnvDate2StrF1(endDate).substring(11, 19).replace(":", "")); 
		int startTimeInt = Integer.parseInt(DateUtil.cnvDate2StrF1(startDate).substring(11, 19).replace(":", "")); 
		if (endTimeInt <= startTimeInt) {
			dayNum += 1;
		}
			
		if (dayNum - weekEndNum > 0) {
			millSecends -= (dayNum - weekEndNum)*15*60*60*1000;
		}
		
		if (millSecends < 0) {
			return 0;
		}
		
		return millSecends;
	}
	
	public static Calendar getNextMonday(Calendar cal){
		int addnum=9-cal.get(Calendar.DAY_OF_WEEK);
		if(addnum==8)addnum=1;//周日的情况
		cal.add(Calendar.DATE, addnum);
		return cal;
	}
	
	/**
	 * 获取两个日期之间的实际天数，支持跨年
	 */
	public static int getDaysBetween(Calendar start, Calendar end){
		if(start.after(end)){
			Calendar swap = start;
			start = end;
			end = swap;
		}
		int days = end.get(Calendar.DAY_OF_YEAR)- start.get(Calendar.DAY_OF_YEAR);
		int y2 = end.get(Calendar.YEAR);
		if (start.get(Calendar.YEAR) != y2) {
			start = (Calendar) start.clone();
			do {
			    days += start.getActualMaximum(Calendar.DAY_OF_YEAR);
			    start.add(Calendar.YEAR, 1);
			}while(start.get(Calendar.YEAR) != y2);
		}
		return days;
	}
	
	
	
}
