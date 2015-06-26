package com.gugu.commonServlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

/**
 * Servlet Filter implementation class SqlFilter
 */
public class SqlFilter implements Filter {
	Logger logger = Logger.getLogger(this.getClass());

	static String[] splitSql = null;
	static String errorPage = "/";
    /**
     * Default constructor. 
     */
    public SqlFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		response.setCharacterEncoding("UTF-8");
		if (splitSql != null && splitSql.length > 0) {
			Enumeration enum1 = request.getParameterNames();
			while (enum1.hasMoreElements()) {
				String param = enum1.nextElement().toString();
				String value = request.getParameter(param);
				if (!isCorrectContent(value)) {
					response.getWriter().write("<script>alert('包含特殊字符!');</script>");
					//response.sendRedirect(errorPage);
					return ;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String ep = fConfig.getInitParameter("errorPage");// �������ļ�
		if (ep != null) {
			errorPage = ep;
		}

		String sqlStr = fConfig.getInitParameter("sqlStr");// �������ļ�
		if (sqlStr != null) {
			splitSql = sqlStr.split("\\|");
		}
	}

	public static synchronized boolean isCorrectContent(String paraValue) {
		if (StringUtils.isNullOrEmpty(paraValue)) {
			return true;
		}
		for (int i = 0; i < splitSql.length; i++) {
			if (paraValue.toLowerCase().indexOf(""+splitSql[i]+"") != -1) {
				return false;
			}
		}
		return true;
	}
}
