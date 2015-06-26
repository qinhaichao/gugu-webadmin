package com.gugu.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @description HttpClient �Ĺ�����
 * @author qinhc123
 * @2015����6:03:09
 */
public class HttpClientUtil {

	private static Logger logger = Logger.getLogger(HttpClientUtil.class);

	/**
	 * @description ����httpClient post ����json ��ʽ����
	 * @author qinhc
	 * @2015����6:03:09
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static String executeHttpPost(String url, String body)
			throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		StringEntity entity = new StringEntity(body, "utf-8");// ���������������
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		method.setEntity(entity);
		String resData = "";
		// ����ʱ
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		// ��ȡ��ʱ
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
		try {
			HttpResponse result = httpClient.execute(method);
			// ������������ؽ��
			resData = EntityUtils.toString(result.getEntity());
			logger.info("������Ϣ���ص����ݣ�" + resData);
		} catch (Exception e) {
			logger.error("������Ϣ�����������" + e.getMessage());
		} finally {
			method.releaseConnection();
		}
		return resData;
	}

	/**
	 * @description
	 * @author qinhc
	 * @2015����6:00:41
	 * @param url
	 * @param body
	 * @param type
	 *            ���������
	 * @return
	 * @throws Exception
	 */
	public static String executeHttpPostType(String url, String body,
			String type) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		StringEntity entity = new StringEntity(body, "utf-8");// ���������������
		entity.setContentEncoding("UTF-8");
		entity.setContentType(type);
		method.setEntity(entity);
		String resData = "";
		// ����ʱ
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		// ��ȡ��ʱ
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
		try {
			HttpResponse result = httpClient.execute(method);
			// ������������ؽ��
			resData = EntityUtils.toString(result.getEntity());
			logger.info("������Ϣ���ص����ݣ�" + resData);
		} catch (Exception e) {
			logger.error("������Ϣ�����������" + e.getMessage());
		} finally {
			method.releaseConnection();
		}
		return resData;
	}
}
