package com.gugu.utils.springmvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * ���ʻ���Ϣ������
 */
public final class MessageResolver {
	/**
	 * ��ù��ʻ���Ϣ
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param code
	 *            ���ʻ�����
	 * @param args
	 *            �滻����
	 * @return
	 * @see org.springframework.context.MessageSource#getMessage(String,
	 *      Object[], Locale)
	 */
	public static String getMessage(HttpServletRequest request, String code,
			Object... args) {
		WebApplicationContext messageSource = RequestContextUtils
				.getWebApplicationContext(request);
		if (messageSource == null) {
			throw new IllegalStateException("WebApplicationContext not found!");
		}
		LocaleResolver localeResolver = RequestContextUtils
				.getLocaleResolver(request);
		Locale locale;
		if (localeResolver != null) {
			locale = localeResolver.resolveLocale(request);
		} else {
			locale = request.getLocale();
		}
		return messageSource.getMessage(code, args, locale);
	}
}
