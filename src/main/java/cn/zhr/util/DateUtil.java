package cn.zhr.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.StringUtils;

public class DateUtil {
	public static final long DAY_MILLI = 24 * 60 * 60 * 1000; // 一天的MilliSecond

	public static final long MINUTE_MILLI = 60 * 1000; // 一分钟的MilliSecond

	//没有分隔符的日期
	public static String DATE_FORMAT_DATEONLYNOSP = "yyyyMMdd"; // 年/月/日
	//没有分隔符的时间
	public static String DATE_FORMAT_TIMEONLYNOSP = "HHmmss"; // 时/分/秒

	/**
	 * 要用到的DATE Format的定义
	 */
	public static String DATE_FORMAT_DATEONLY = "yyyy-MM-dd"; // 年/月/日

	public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"; // 年/月/日

	// 时:分:秒

	public final static String DATE_FORMAT_DATEHOURMIN = "yyyy-MM-dd HH:mm";
	// // 年/月/日 时:分
	
	
	//public final static String DATE_FORMAT_SESSION = "yyyyMMddHHmm"; // 年/月/日
	// 时:分:秒
	 
	 public final static String DATE_FORMAT_LONG = "yyyyMMddHHmmss"; // 年/月/日
	 
	public static SimpleDateFormat sdfDateOnlyNoSp = new SimpleDateFormat(
			DateUtil.DATE_FORMAT_DATEONLYNOSP);
	public static SimpleDateFormat sdfTimeOnlyNoSp = new SimpleDateFormat(
			DateUtil.DATE_FORMAT_TIMEONLYNOSP);
	public static SimpleDateFormat sdfDateHourTimeTime = new SimpleDateFormat(
			DateUtil.DATE_FORMAT_DATEHOURMIN);
	/**
	 * Global SimpleDateFormat object
	 */
	public static SimpleDateFormat sdfDateOnly = new SimpleDateFormat(
			DateUtil.DATE_FORMAT_DATEONLY);

	public static SimpleDateFormat sdfDateTime = new SimpleDateFormat(
			DateUtil.DATE_FORMAT_DATETIME);
	/**
	 *  以固定格式获取当前日
	 * 默认返回格式为yyy-MM-dd
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format){
		Date date = new Date();
		if(StringUtils.isEmpty(format)){
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String result = sdf.format(date);
		return result;
	}
	
	public static String getNoSpSysDateString() {
		return toString(new java.util.Date(System.currentTimeMillis()),
				DateUtil.sdfDateOnlyNoSp);
	}
	
	public static String getNoSpSysTimeString() {
		return toString(new java.util.Date(System.currentTimeMillis()),
				DateUtil.sdfTimeOnlyNoSp);
	}
	
	/**
	 * 利用指定SimpleDateFormat instance转换java.util.Date到String
	 * 
	 * @param dt
	 *            java.util.Date instance
	 * @param formatter
	 *            SimpleDateFormat Instance
	 * @return
	 * @since 1.0
	 * @history
	 */
	public static String toString(java.util.Date dt, SimpleDateFormat formatter) {
		String sRet = null;

		try {
			sRet = formatter.format(dt).toString();
		} catch (Exception e) {
			e.printStackTrace();
			sRet = null;
		}

		return sRet;
	}
	/**
	 * 以YYYY-MM-DD HH24:MI:SS格式返回系统日期时间
	 * 
	 * @return 系统日期时间
	 * @since 1.0
	 * @history
	 */
	public static String getSysDateTimeString() {
		return toString(new java.util.Date(System.currentTimeMillis()),
				DateUtil.sdfDateTime);
	}
	/**
	 * 验证系统时间是否在活动有效期内
	 *
	 * @param start
	 * @param end
	 * @param df	为空默认格式为yyyy-MM-dd hh:mm:SS
	 * @return
	 * @throws ParseException
	 */
	public static boolean checkTodayInValidDate(String start, String end, DateFormat df) throws ParseException{
		//为防止数据库中老数据此字段数据为空，判断都为空是不进行有效限制。
		if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end))
			return true;
		if(df == null)
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//yyyy-MM-dd HH:mm:ss 24小时制，yyyy-MM-dd hh:mm:SS-12小时制
		Date today = new Date();
		long todayTime = today.getTime();
		long pst = df.parse(start).getTime();
		long pet = df.parse(end).getTime();
		// 计算当前日期是否在商品限制日期内
		if(todayTime < pst || todayTime > pet){
			// 不在限制日期内
			return false;
		}
		return true;
	}
	/**
	 * 返回long型的SYSDATE
	 * 
	 * @return long型的SYSDATE
	 * @since 1.0
	 * @history
	 */
	public static long getSysDateLong() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取day以前的日期
	 * @param format
	 * @param day
	 * @return
	 */
	public static String getSomeDayBefore(String format,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
		if(StringUtils.isEmpty(format)){
			format="yyyyMMdd";
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime()); 
	}
	
	
	/**
	 * 以YYYY-MM-DD格式返回当前系统日期
	 * @Author jinsizhao
	 * @CreationTime 2016年12月20日.下午2:26:01 
	 * @return
	 */
	public static String getSysDateString() {
		return toString(new java.util.Date(System.currentTimeMillis()),
				DateUtil.sdfDateOnly);
	}
	

	/**
	 * 获取日期的毫秒值
	 * @Author jinsizhao
	 * @CreationTime 2016年12月21日.上午10:11:45 
	 * @param date
	 * @param format
	 * @return
	 */
	public static long getDateMillisecond(String date){
		long l = 0;
		try {
			l = sdfDateOnly.parse(date).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	
	/**
	 * 判断两个日期是否为连续日期
	 * @Author jinsizhao
	 * @CreationTime 2016年12月21日.上午10:14:40 
	 * @param thisDate
	 * @param lastSignDate
	 * @return
	 */
	public static boolean IsDateContinus(String thisDate,String lastSignDate){
		long thisTime = getDateMillisecond(thisDate);
		long lastTime = getDateMillisecond(lastSignDate);
		return (thisTime - lastTime <= 1*24*60*60*1000);
	}
	
	/**
	 * 以YYYY-MM-DD HH24:MI格式返回系统日期时间
	 * @Author jinsizhao
	 * @CreationTime 2016年12月21日.下午4:16:38 
	 * @return
	 */
	public static String getSysDateHourMinuteTimeString() {
		return toString(new java.util.Date(System.currentTimeMillis()),
				DateUtil.sdfDateHourTimeTime);
	}
	/**
	 * 从java.sql.Timestamp Object 中取出hour:minute
	 * 
	 * @param timestamp ,
	 *            java.sql.Timestamp Object
	 * @return Hour:Minute format string
	 * @since 1.0
	 * @history
	 */
	public static String getCurrentHourString() {
		String out = null;
		java.sql.Timestamp time = new Timestamp(System.currentTimeMillis());
		GregorianCalendar obj = DateUtil.convertTimestampToCalendar(time);
		int hour = obj.get(GregorianCalendar.HOUR_OF_DAY);
		if (hour < 10) {
			out = "0" + String.valueOf(hour);
		} else {
			out = String.valueOf(hour);
		}
		return out;
	}
	/**
	 * 把java.sql.Timestamp Object 转换为java.util.GregorianCalendar Object
	 * 
	 * @param timestamp ,
	 *            java.sql.Timestamp Object
	 * @return java.util.GregorianCalendar Object
	 * @since 1.0
	 * @history
	 * @deprecated please use confertToCalendar(java.sql.Timestamp)
	 */
	public static java.util.GregorianCalendar convertTimestampToCalendar(
			java.sql.Timestamp timestamp) {
		return convertToCalendar(timestamp);
	}
	/**
	 * 把java.sql.Timestamp Object 转换为java.util.GregorianCalendar Object
	 * 
	 * @param timestamp ,
	 *            java.sql.Timestamp Object
	 * @return java.util.GregorianCalendar Object
	 * @since 1.0
	 * @history
	 */
	public static java.util.GregorianCalendar convertToCalendar(
			java.sql.Timestamp timestamp) {
		GregorianCalendar obj = new GregorianCalendar();
		// Modified by ChenJP 2000/11/17
		obj.setTime(DateUtil.convertTimestampToDate(timestamp));
		// 下面的method不能用，long ==> int 精度不对
		// obj.set(GregorianCalendar.MILLISECOND , (int)timestamp.getTime() );
		return obj;
	}
	/**
	 * 把java.sql.Timestamp Object 转换为java.util.Date Object
	 * 
	 * @param timestamp ,
	 *            java.sql.Timestamp Object
	 * @return java.util.Date Object
	 * @since 1.0
	 * @history
	 */
	public static java.util.Date convertTimestampToDate(
			java.sql.Timestamp timestamp) {
		java.util.Date date = null;
		/*
		 * modified by ChenJP 2000/11/17 String temp = null; temp =
		 * timestamp.toString (); temp = temp.substring
		 * (0,DateUtil.DATE_FORMAT_DATETIME.length ()); temp = temp.replace
		 * ('-','/'); try{ date = DateUtil.sdfDateTime.parse (temp);
		 * }catch(Exception e){ e.printStackTrace(); date = null; }
		 */
		date = new Date(timestamp.getTime());
		return date;
	}
	/**
	 * 从java.sql.Timestamp Object 中取出hour:minute
	 * 
	 * @param timestamp ,
	 *            java.sql.Timestamp Object
	 * @return Hour:Minute format string
	 * @since 1.0
	 * @history
	 */
	public static String getCurrentMinuteString() {
		String out = null;
		java.sql.Timestamp time = new Timestamp(System.currentTimeMillis());
		GregorianCalendar obj = DateUtil.convertTimestampToCalendar(time);
		int minute = obj.get(GregorianCalendar.MINUTE);
		if (minute < 10) {
			out = "0" + String.valueOf(minute);
		} else {
			out = String.valueOf(minute);
		}
		return out;
	}
}
