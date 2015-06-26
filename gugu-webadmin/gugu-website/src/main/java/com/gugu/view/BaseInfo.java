package com.gugu.view;

import java.util.HashMap;
import java.util.Map;


public class BaseInfo {
	private static final Map<Integer, BaseInfo> map = new HashMap<Integer, BaseInfo>();
	static {
		map.put(400, new BaseInfo(400, "请求参数错误"));
		map.put(401, new BaseInfo(401, "此操作需要登录"));
	}

	public static BaseInfo errInfo(Integer key) {
		return map.get(key);
	}

	public BaseInfo() {
	}

	public BaseInfo(Integer rc, String msg) {
		this.rc = rc;
		this.msg = msg;
	}
	
	public BaseInfo(Integer rc, String msg,String content) {
		this.rc = rc;
		this.msg = msg;
		this.content=content;
	}

	public BaseInfo(String msg) {
		this.msg = msg;
	}

	public BaseInfo(Integer rc) {
		this.rc = rc;
	}

	private int rc;
	private String msg = "";
	private String content="";

	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
