package com.gugu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	public static String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	private static Map<String,SimpleDateFormat> formatMap = new HashMap<String,SimpleDateFormat>();
	
	public static String formatDate(Date date, String pattern){
		SimpleDateFormat format = formatMap.get(pattern);
		if(format == null){
			format = new SimpleDateFormat(pattern);
		}
		return format.format(date);
	}
	
	public static Date parseDate(String str, String pattern) throws ParseException {
		SimpleDateFormat format = formatMap.get(pattern);
		if(format == null){
			format = new SimpleDateFormat(pattern);
		}
		return format.parse(str);
	}
	
	public static Date parseAceDate(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(str);
	}
	
	public static Date parseCheckInDate(String str) throws ParseException {
		return new SimpleDateFormat("yyyyMMdd HHmm").parse(str);
	}
	
	public static Date parseYyyyMmDdDate(String str) throws ParseException {
		return new SimpleDateFormat("yyyyMMdd").parse(str);
	}
	
	public static String formatYyyyMmDdDate(Date date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * 
	  * <p>(适用条件[可选])
	  * <p>(执行流程[可选])
	  * <p>(使用方法[可选])
	  * <p>(注意事项[可选])
	  *
	  * @Description:（yyyy-MM-dd'T'HH:mm:ss.SSS）格式日期获取日期
	  * @param str
	  * @return
	  * @throws ParseException String
	  * @Modified:
	 */
	public static String str2DateStr(String str) throws ParseException {
		Date date = parseAceDate(str);
		return str2DateStr(date);
	}
	
	/**
	 * 
	  * <p>(适用条件[可选])
	  * <p>(执行流程[可选])
	  * <p>(使用方法[可选])
	  * <p>(注意事项[可选])
	  *
	  * @Description:返回Date对象日期（yyyy-MM-dd）
	  * @param str
	  * @return
	  * @throws ParseException String
	  * @Modified:
	 */
	public static String str2DateStr(Date date) throws ParseException {
		String dateStr =  new SimpleDateFormat("yyyy-MM-dd").format(date);
		return dateStr;
	}
	
	/**
	 * 
	  * <p>(适用条件[可选])
	  * <p>(执行流程[可选])
	  * <p>(使用方法[可选])
	  * <p>(注意事项[可选])
	  *
	  * @Description:（yyyy-MM-dd'T'HH:mm:ss.SSS）格式日期获取时间(HH:mm)
	  * @param str
	  * @return
	  * @throws ParseException String
	  * @Modified:
	 */
	public static String str2TimeStr(String str) throws ParseException {
		Date date = parseAceDate(str);
		String timeStr =  new SimpleDateFormat("HH:mm").format(date);
		return timeStr;
	}
	
	/**
	 * 
	  * <p>(适用条件[可选])
	  * <p>(执行流程[可选])
	  * <p>(使用方法[可选])
	  * <p>(注意事项[可选])
	  *
	  * @Description:获取当前日期是星期几
	  * @param dt 日期Date对象
	  * @return String 星期字符串
	  * @Modified:
	 */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
	
	/**
	  * <p>(适用条件[可选])
	  * <p>(执行流程[可选])
	  * <p>(使用方法[可选])
	  * <p>(注意事项[可选])
	  *
	  * @Description:验证身份证中的日期是否合法,接受日期格式：20120221
	  * @param date
	  * @return boolean
	  * @Modified:
	 */
	public static boolean verifyDate(String date) {
		String v = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)";
		Pattern p = Pattern.compile(v);
		Matcher m = p.matcher(date);
		return m.matches();
	}
	
	/**
	 * 
	  * @Description: 
	  * @param dateString 格式：yyyy-MM-dd
	  * @return
	  * @throws ParseException Date
	  * @Created:lining 2013年11月7日下午2:08:45
	  * @Modified:
	 */
	public static Date string2Date(String dateString) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
	}
	
	/**
	 * 
	  * @Description:
	  * @param date
	  * @return String 格式：yyyy-MM-dd
	  * @Modified:
	 */
	public static String date2String(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String date2String(Date date, String dateFormat) {
		SimpleDateFormat sdf= new SimpleDateFormat(dateFormat);
		String dateString = sdf.format(date);
		return dateString;
	}
	
	public static Date String2Date(String dateString, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getNow() {
		return new Date();
	}
	
	/**
	 * 
	  * <p>(适用条件[可选])
	  * <p>(执行流程[可选])
	  * <p>(使用方法[可选])
	  * <p>(注意事项[可选])
	  *
	  * @Description:当前日期字符串加指定天数
	  * @param date 原日期
	  * @param day 增加天数
	  * @return String 增加后的日期
	  * @Modified:
	 */
	public static String addDay(String date , int day){
		
		try {
			Date objDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Calendar c = Calendar.getInstance();  
			c.setTime(objDate);
			c.add(Calendar.DATE, day);
			Date newDate = c.getTime();
			String newDateStr = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
			return newDateStr;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String addMonth(String date , int day){
		
		try {
			Date objDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Calendar c = Calendar.getInstance();  
			c.setTime(objDate);
			c.add(Calendar.MONTH, day);
			Date newDate = c.getTime();
			String newDateStr = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
			newDateStr = newDateStr.substring(0, 8)+ "01";
			return newDateStr;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	  * @Description: 计算两个日期之间的小时差
	  * @param start
	  * @param end
	  * @return int
	  * @Modified:
	 */
	public static double dateCompute(Date start, Date end) {
        double between = (end.getTime() - start.getTime());// 得到两者的毫秒数
        return Math.abs(between / (60 * 60 * 1000));
	}
	
	/**
	 * 
	  * @Description: 计算两个日期之间的分钟差
	  * @param start
	  * @param end
	  * @return int
	  * @Modified:
	 */
	public static double dateComputeM(Date start, Date end) {
		double between = (end.getTime() - start.getTime());// 得到两者的毫秒数
        return Math.abs(between / (60 * 1000));
	}
	
	/**
	 * 
	  * @Description: 计算两个日期之间的秒差
	  * @param start
	  * @param end
	  * @return double
	  * @Modified:
	 */
	public static double dateComputeS(Date start, Date end) {
		double between = (end.getTime() - start.getTime());// 得到两者的毫秒数
        return Math.abs(between / 1000);
	}
	
	/**
	 * 
	  * @Description: 计算两个时间差
	  * @param startDate 开始时间，格式yyyy-MM-dd hh:mm:ss.S
	  * @param endDate 结束时间，格式yyyy-MM-dd hh:mm:ss.S
	  * @return String 例如：1小时23分钟
	  * @Modified:
	 */
	public static String computationDelayTime(String startDate, String endDate) {
		long start = DateUtil.String2Date(startDate, "yyyy-MM-dd HH:mm:ss.S").getTime();
		long end = DateUtil.String2Date(endDate, "yyyy-MM-dd HH:mm:ss.S").getTime();
		long between_m = (end - start) / (60*1000);	//总分钟数
		long hour = between_m / 60;		//小时
		long minute = between_m % 60;	//分钟
		StringBuilder delayTime = new StringBuilder();
		if(hour != 0) {
			delayTime.append(hour).append("小时");
		}
		delayTime.append(minute).append("分钟");
		return delayTime.toString();
	}
	
	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
		String result = sdf.format(date);
		return result;
	}
	
	public static String dateFormatF(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String result = sdf.format(date);
		return result;
	}
	//2013-08-08
	public static String dateFormatX(String date) {
		String result = "";
		if(date.indexOf("-")!=-1){
			String[] d = date.split("-");
			if(d.length>2){
				result = d[1] + "/" + d[2] + "/" + d[0];
			}
		}
		return result;
	}
	
	/**
	 * 
	  * @Description: 获取当前时间前30天的时间
	  * @return Date
	  * @Modified:
	 */
	public static Date getBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		calendar.getTime();
		return calendar.getTime();
	}
	
	/**
	 * 
	  * @Description: 获取当前时间前2天的时间
	  * @return Date
	  * @Modified:
	 */
	public static Date getBeforeTwo() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		calendar.getTime();
		return calendar.getTime();
	}
	
	/**
	 * 
	  * @Description: 获取后一天时间
	  * @Modified:
	 */
	public static String getNextOne(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return date2String(calendar.getTime(), "yyyyMMdd");
	}
	
	/**
	 * 
	  * @Description: 获取后 七天时间
	  * @Modified:
	 */
	public static String getNextSeven(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		return date2String(calendar.getTime(), "yyyyMMdd");
	}
	/**
	 * 
	 * @Description: 获取指定时间后X天时间
	 * @Modified:
	 */
	public static Date getNextSeven(Date date,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	/**
	 * 
	  * @Description: 获取后20分钟时间
	  * @Created:lining 2014年4月29日下午6:44:31
	  * @Modified:
	 */
	public static Date getNextMinute(int m) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.MINUTE, m);
		return calendar.getTime();
	}
	
	/**
	 * 
	  * @Description: 获取当前时间前m分钟
	  * @param m 分钟
	  * @return String 返回时间格式：yyyyMMddhhmmss
	  * @Created:lining 2014年4月19日上午10:35:06
	  * @Modified:
	 */
	public static String getBeforeM(int m) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.MINUTE, -m);
		Date date = calendar.getTime();
		return date2String(date, "yyyyMMddHHmmss");
	}
	
	/**
	 * 
	  * @Description: 获取当前时间前m分钟
	  * @param m 分钟
	  * @return String 返回时间格式：pattern
	  * @Modified:
	 */
	public static String getBeforeM(int m,String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.MINUTE, -m);
		Date date = calendar.getTime();
		return date2String(date, pattern);
	}
	
	/**
	  * <p>(适用条件[可选])
	  * <p>(执行流程[可选])
	  * <p>(使用方法[可选])
	  * <p>(注意事项[可选])
	  *
	  * @Description: 获取指定年月的最后一天的日期 
	  * @param yearMonth指定年月，格式为  yyyy-MM
	  * @return
	  * @throws ParseException
	  * @Modified:
	 */
	public static Date getMonthLastDay(String yearMonth) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		Date date = parseDate(yearMonth, "yyyy-MM");
		calendar.setTime(date);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.roll(Calendar.DAY_OF_MONTH, -1);
		
		return calendar.getTime();
	}
	
	/**
	 * 
	  * @Description: 获取当前月第一天
	  * @return String 格式：MM/dd/yyyy
	  * @Modified:
	 */
	public static String getFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String result = sdf.format(calendar.getTime());
		return result;
	}
	/**
	 *  Description: 获取当前月第一天
	 * @param pattern 时间格式
	 * @return
	 */
	public static String getFirstDate(String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String result = sdf.format(calendar.getTime());
		return result;
	}
	/**
	 * 
	  * @Description: 获取当前月最后一天
	  * @return String 格式：MM/dd/yyyy
	  * @Modified:
	 */
	public static String getLastDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String result = sdf.format(calendar.getTime());
		return result;
	}
	
	/**
	 * 
	  * @Description: 获取当前月最后一天
	  * @return String 格式：yyyy-MM-dd
	  * @Modified:
	 */
	public static String getLastDate2() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = sdf.format(calendar.getTime());
		return result;
	}
	
	/**
	 * 
	  * @Description: 获取下一个月第一天
	  * @return String 格式：MM/dd/yyyy
	  * @Modified:
	 */
	public static String getNextMonthFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String result = sdf.format(calendar.getTime());
		return result;
	}
	
	/**
	 * 
	  * @Description: 获取下个月最后一天
	  * @return String 格式：MM/dd/yyyy
	  * @Modified:
	 */
	public static String getNextMonthLastDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String result = sdf.format(calendar.getTime());
		return result;
	}
	
	/**
	 * 
	  * @Description: 获取下个月最后一天
	  * @return String yyyy-MM-dd
	  * @Modified:
	 */
	public static String getNextMonthLastDate2() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNow());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = sdf.format(calendar.getTime());
		return result;
	}
	
	/**
     * 获取当年的第一天
     * @param year
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
    /**
     * 获取当年的最后一天
     * @param year
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
     
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
     
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
         
        return currYearLast;
    }
 
	public static Date dateFormatCheckIn(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.parse(date);
	}
	
	/** 
     * 计算两个日期之间相差的天数 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int daysBetween(Date date1,Date date2)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));         
    } 
    
    /**
     * 计算现在时间是否在开始时间和结束时间之间
     * @param startdatestr
     * @param enddatestr
     * @return
     */
    public static boolean isNowBetween(String startdatestr,String enddatestr){
    	long nowdate= new Date().getTime();
    	long startdate = DateUtil.String2Date(startdatestr, "yyyy-MM-dd'T'HH:mm:ss").getTime();
		long enddate = DateUtil.String2Date(enddatestr, "yyyy-MM-dd'T'HH:mm:ss").getTime();
		if (nowdate>startdate && enddate > nowdate) {
			return true;
		}
    	return false;
    }
    
    
    

}
