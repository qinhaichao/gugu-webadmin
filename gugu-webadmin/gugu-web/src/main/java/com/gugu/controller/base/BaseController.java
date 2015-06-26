package com.gugu.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.util.DateUtil;

import com.gugu.commonServlet.XssHttpServletRequestWrapper;
import com.gugu.utils.JsonUtil;
import com.gugu.utils.ThreadLocalReqAndResp;

public class BaseController {
	protected String toJson(Object obj) {
		if (obj instanceof String) {
			return (String) obj;
		}
		String rs = null;
		try {
			JsonUtil.OM.setDateFormat(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss"));
			rs = JsonUtil.OM.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs == null) {
			rs = "{\"rc\":-1}";// 解析JSON异常/IO异常
		}
		return rs;
	}

	protected HttpServletRequest request() {
		return ThreadLocalReqAndResp.getRequest();
	}

	protected HttpSession session() {
		return ThreadLocalReqAndResp.getSession();
	}

	/**
	 * GUID
	 */
	protected String randomUUID() {
		return UUID.randomUUID().toString();
	}

	protected int randomNum(int num){
		Random random=new Random();
		int n=random.nextInt(num);
		return n;
	}
	protected String getRemoteAddr() {
		return request().getHeader("X-Forward-For");
	}

	protected String getSessionid() {
		return this.session().getId();
	}

	protected boolean isNotNull(Object... obj) {
		if (obj != null) {
			for (Object o : obj) {
				if (o == null) {
					return false;
				} else if (o instanceof String) {
					String encoded = XssHttpServletRequestWrapper
							.xssEncode((String) o);
					o = encoded;
					if (o == null) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	protected boolean isNull(Object... obj) {
		return !isNotNull(obj);
	}

	/**
	 * @获取请求参数中所有的信息
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getRequestParam(
			final HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				//String value = request.getParameter(en);
				res.put(en, request.getParameter(en));
				if (res.get(en) == null || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
	
	protected String getNowDate(){
		return DateUtil.formatDate(new Date(), "yyyy-MM-dd");
	}
	
	protected String getNowTime(){
		return DateUtil.formatDate(new Date(), "HH:mm:ss");
	}
	
	protected String getNowDateTime(){
		return DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

}
