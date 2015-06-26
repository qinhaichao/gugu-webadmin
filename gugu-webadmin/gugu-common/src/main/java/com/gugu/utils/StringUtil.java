package com.gugu.utils;

import java.text.DecimalFormat;

public class StringUtil {

	public static boolean isEmpty(String key) {
		if (key != null && !"".equals(key.trim())) {
			return false;
		}
		return true;
	}

	public static boolean isEmpty(String... keys) {
		for (String key : keys) {
			if (StringUtil.isEmpty(key)) {
				return true;
			}
		}
		return false;
	}

	public static String decimalFormatPrice(String param) {
		if (param == null || "".equals(param)) {
			param = "0.00";
		}
		double tmp = Double.parseDouble(param);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(tmp);
	}
	

	public static String cleanXss(String str){
		if (str == null || "".equals(str.trim())) {
			return "";
		}
		str = str.replaceAll(" ","");
		return str;
	}
	
	/**
	 * @description 以*(分号)分隔的字符串，去除首尾的*(分号)。
	 * @author qinhc
	 * @createTime 2015上午11:05:00
	 * @param str
	 * @param trim
	 * @return
	 */
	public static String StrRemoveTrim(String str,String trim){
		// str=;2;; 
		String resultStr="";
		String[] strList=str.split(trim);
		for(String s:strList){
			if(!"".equals(s)&&null!=s){
				resultStr=resultStr+s+trim;
			}
		}
		if(resultStr.length()>0){
			resultStr=resultStr.substring(0,resultStr.length()-1);
		}
		return resultStr;
	}
	
	public static void main(String[] args) {

		 System.out.println(StrRemoveTrim(";;;j;;;djdj;ddi;;;",";"));
	}

}
