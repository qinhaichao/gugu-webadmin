package com.gugu.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ThreadLocalReqAndResp {
	private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();
	public static HttpServletRequest getRequest() {
		return request.get();
	}
	public static void setRequest(HttpServletRequest req) {
		request.set(req);
	}
	public static void closeRequest(){
		request.remove();
	}
	public static HttpSession getSession() {
		return session.get();
	}
	public static void setSession(HttpSession ses) {
		session.set(ses);
	}
	public static void closeSession(){
		session.remove();
	}
}
