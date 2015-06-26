package com.gugu.commonServlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet implementation class XssHttpServletRequestWrapper
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private static String[]filterChars;
	private static String[]replaceChars;
	public XssHttpServletRequestWrapper(HttpServletRequest request,String filterChar,String replaceChar,String splitChar) {
		super(request);
		if(filterChar!=null&&filterChar.length()>0){
			filterChars=filterChar.split(splitChar);
		}
		if(replaceChar!=null&&replaceChar.length()>0){
			replaceChars=replaceChar.split(splitChar);
		}
	}
	public String getQueryString() {
		String value = super.getQueryString();
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	/**
	 * ����getParameter���������������Ͳ���ֵ����xss���ˡ�<br/>
	 * �����Ҫ���ԭʼ��ֵ����ͨ��super.getParameterValues(name)����ȡ<br/>
	 * getParameterNames,getParameterValues��getParameterMapҲ������Ҫ����
	 */
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	public String[] getParameterValues(String name) {
		String[]parameters=super.getParameterValues(name);
		if (parameters==null||parameters.length == 0) {
			return null;
		}
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = xssEncode(parameters[i]);
		}
		return parameters;
	}
	
	/**
	 * ����getHeader���������������Ͳ���ֵ����xss���ˡ�<br/>
	 * �����Ҫ���ԭʼ��ֵ����ͨ��super.getHeaders(name)����ȡ<br/> getHeaderNames Ҳ������Ҫ����
	 */
	public  String getHeader(String name) {

		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	/**
	 * ����������xss©���İ���ַ�ֱ���滻��ȫ���ַ�
	 * 
	 * @param s
	 * @return
	 */
	public static String xssEncode(String s) {
		if (s == null || s.equals("")) {
			return s;
		}
		try {
			s = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < filterChars.length; i++) {
			if(s.contains(filterChars[i])){
				s=s.replace(filterChars[i], replaceChars[i]);
			}
		}
		return s;
	}
}
