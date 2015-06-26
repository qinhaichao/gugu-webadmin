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
	  * <p>(��������[��ѡ])
	  * <p>(ִ������[��ѡ])
	  * <p>(ʹ�÷���[��ѡ])
	  * <p>(ע������[��ѡ])
	  *
	  * @Description:��yyyy-MM-dd'T'HH:mm:ss.SSS����ʽ���ڻ�ȡ����
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
	  * <p>(��������[��ѡ])
	  * <p>(ִ������[��ѡ])
	  * <p>(ʹ�÷���[��ѡ])
	  * <p>(ע������[��ѡ])
	  *
	  * @Description:����Date�������ڣ�yyyy-MM-dd��
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
	  * <p>(��������[��ѡ])
	  * <p>(ִ������[��ѡ])
	  * <p>(ʹ�÷���[��ѡ])
	  * <p>(ע������[��ѡ])
	  *
	  * @Description:��yyyy-MM-dd'T'HH:mm:ss.SSS����ʽ���ڻ�ȡʱ��(HH:mm)
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
	  * <p>(��������[��ѡ])
	  * <p>(ִ������[��ѡ])
	  * <p>(ʹ�÷���[��ѡ])
	  * <p>(ע������[��ѡ])
	  *
	  * @Description:��ȡ��ǰ���������ڼ�
	  * @param dt ����Date����
	  * @return String �����ַ���
	  * @Modified:
	 */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
	
	/**
	  * <p>(��������[��ѡ])
	  * <p>(ִ������[��ѡ])
	  * <p>(ʹ�÷���[��ѡ])
	  * <p>(ע������[��ѡ])
	  *
	  * @Description:��֤���֤�е������Ƿ�Ϸ�,�������ڸ�ʽ��20120221
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
	  * @param dateString ��ʽ��yyyy-MM-dd
	  * @return
	  * @throws ParseException Date
	  * @Created:lining 2013��11��7������2:08:45
	  * @Modified:
	 */
	public static Date string2Date(String dateString) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
	}
	
	/**
	 * 
	  * @Description:
	  * @param date
	  * @return String ��ʽ��yyyy-MM-dd
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
	  * <p>(��������[��ѡ])
	  * <p>(ִ������[��ѡ])
	  * <p>(ʹ�÷���[��ѡ])
	  * <p>(ע������[��ѡ])
	  *
	  * @Description:��ǰ�����ַ�����ָ������
	  * @param date ԭ����
	  * @param day ��������
	  * @return String ���Ӻ������
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
	  * @Description: ������������֮���Сʱ��
	  * @param start
	  * @param end
	  * @return int
	  * @Modified:
	 */
	public static double dateCompute(Date start, Date end) {
        double between = (end.getTime() - start.getTime());// �õ����ߵĺ�����
        return Math.abs(between / (60 * 60 * 1000));
	}
	
	/**
	 * 
	  * @Description: ������������֮��ķ��Ӳ�
	  * @param start
	  * @param end
	  * @return int
	  * @Modified:
	 */
	public static double dateComputeM(Date start, Date end) {
		double between = (end.getTime() - start.getTime());// �õ����ߵĺ�����
        return Math.abs(between / (60 * 1000));
	}
	
	/**
	 * 
	  * @Description: ������������֮������
	  * @param start
	  * @param end
	  * @return double
	  * @Modified:
	 */
	public static double dateComputeS(Date start, Date end) {
		double between = (end.getTime() - start.getTime());// �õ����ߵĺ�����
        return Math.abs(between / 1000);
	}
	
	/**
	 * 
	  * @Description: ��������ʱ���
	  * @param startDate ��ʼʱ�䣬��ʽyyyy-MM-dd hh:mm:ss.S
	  * @param endDate ����ʱ�䣬��ʽyyyy-MM-dd hh:mm:ss.S
	  * @return String ���磺1Сʱ23����
	  * @Modified:
	 */
	public static String computationDelayTime(String startDate, String endDate) {
		long start = DateUtil.String2Date(startDate, "yyyy-MM-dd HH:mm:ss.S").getTime();
		long end = DateUtil.String2Date(endDate, "yyyy-MM-dd HH:mm:ss.S").getTime();
		long between_m = (end - start) / (60*1000);	//�ܷ�����
		long hour = between_m / 60;		//Сʱ
		long minute = between_m % 60;	//����
		StringBuilder delayTime = new StringBuilder();
		if(hour != 0) {
			delayTime.append(hour).append("Сʱ");
		}
		delayTime.append(minute).append("����");
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
	  * @Description: ��ȡ��ǰʱ��ǰ30���ʱ��
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
	  * @Description: ��ȡ��ǰʱ��ǰ2���ʱ��
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
	  * @Description: ��ȡ��һ��ʱ��
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
	  * @Description: ��ȡ�� ����ʱ��
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
	 * @Description: ��ȡָ��ʱ���X��ʱ��
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
	  * @Description: ��ȡ��20����ʱ��
	  * @Created:lining 2014��4��29������6:44:31
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
	  * @Description: ��ȡ��ǰʱ��ǰm����
	  * @param m ����
	  * @return String ����ʱ���ʽ��yyyyMMddhhmmss
	  * @Created:lining 2014��4��19������10:35:06
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
	  * @Description: ��ȡ��ǰʱ��ǰm����
	  * @param m ����
	  * @return String ����ʱ���ʽ��pattern
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
	  * <p>(��������[��ѡ])
	  * <p>(ִ������[��ѡ])
	  * <p>(ʹ�÷���[��ѡ])
	  * <p>(ע������[��ѡ])
	  *
	  * @Description: ��ȡָ�����µ����һ������� 
	  * @param yearMonthָ�����£���ʽΪ  yyyy-MM
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
	  * @Description: ��ȡ��ǰ�µ�һ��
	  * @return String ��ʽ��MM/dd/yyyy
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
	 *  Description: ��ȡ��ǰ�µ�һ��
	 * @param pattern ʱ���ʽ
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
	  * @Description: ��ȡ��ǰ�����һ��
	  * @return String ��ʽ��MM/dd/yyyy
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
	  * @Description: ��ȡ��ǰ�����һ��
	  * @return String ��ʽ��yyyy-MM-dd
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
	  * @Description: ��ȡ��һ���µ�һ��
	  * @return String ��ʽ��MM/dd/yyyy
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
	  * @Description: ��ȡ�¸������һ��
	  * @return String ��ʽ��MM/dd/yyyy
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
	  * @Description: ��ȡ�¸������һ��
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
     * ��ȡ����ĵ�һ��
     * @param year
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
    /**
     * ��ȡ��������һ��
     * @param year
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
     
    /**
     * ��ȡĳ���һ������
     * @param year ���
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
     * ��ȡĳ�����һ������
     * @param year ���
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
     * ������������֮���������� 
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
     * ��������ʱ���Ƿ��ڿ�ʼʱ��ͽ���ʱ��֮��
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
