package com.gugu.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;



public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);
	private static DefaultHttpClient httpClient;
	private static DefaultHttpClient httpClient_proxy;

	private static String proxyHost = "10.99.60.201";// B2CMainConfig.getProxyHost();//
														// 代理ip地址
	private static int proxyPort = 8080;// B2CMainConfig.getProxyPort();// 代理端口

	private static int maxConLifeTimeMs = 300000;
	private static int defaultMaxConPerHost = 50;
	private static int maxTotalConn = 10000;
	private static int conTimeOutMs = 10000;
	private static int soTimeOutMs = 30000;

	static {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			// TrustManager[] tm = { new MyX509TrustManager() };

			Scheme http = new Scheme("http", 80,
					PlainSocketFactory.getSocketFactory());
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			// sslcontext.init(null, tm, new java.security.SecureRandom());
			sslcontext.init(null, null, null);
			SSLSocketFactory sf = new SSLSocketFactory(sslcontext,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);// STRICT_HOSTNAME_VERIFIER
			Scheme https = new Scheme("https", 443, sf);
			SchemeRegistry sr = new SchemeRegistry();
			sr.register(http);
			sr.register(https);
			PoolingClientConnectionManager cm = new PoolingClientConnectionManager(
					sr, maxConLifeTimeMs, TimeUnit.MILLISECONDS);
			cm.setMaxTotal(maxTotalConn);
			cm.setDefaultMaxPerRoute(defaultMaxConPerHost);
			// 普通http客户端
			httpClient = new DefaultHttpClient(cm);
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, conTimeOutMs);
			httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, soTimeOutMs);
			httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
					CookiePolicy.IGNORE_COOKIES);
			httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
				public void process(final HttpResponse response,
						final HttpContext context) throws HttpException,
						IOException {
					HttpEntity entity = response.getEntity();
					Header ceheader = entity.getContentEncoding();
					if (ceheader != null
							&& ceheader.getValue().toLowerCase()
									.contains("gzip")) {
						response.setEntity(new GzipDecompressingEntity(response
								.getEntity()));
					}
				}
			});
			// 代理http客户端
			httpClient_proxy = new DefaultHttpClient(cm);
			httpClient_proxy.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, conTimeOutMs);
			httpClient_proxy.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, soTimeOutMs);
			httpClient_proxy.getParams().setParameter(
					ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
			HttpHost proxy = new HttpHost(proxyHost, proxyPort);
			httpClient_proxy.getParams().setParameter(
					ConnRoutePNames.DEFAULT_PROXY, proxy);
		} catch (Exception e) {
			log.error("HttpExecutor init error", e);
		}
	}

	/**
	 * 
	 * @Description:无代理调用
	 * @param request
	 * @return
	 * @throws Exception
	 *             HttpResponse
	 * @Created:lining 2013年12月26日下午4:57:17
	 * @Modified:
	 */
	public static HttpResponse execute(HttpUriRequest request) throws Exception {
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(request);
		} catch (Exception e) {
			throw e;
		}
		return httpResponse;
	}

	/**
	 * 
	 * @Description:有代理调用
	 * @param request
	 * @return
	 * @throws Exception
	 *             HttpResponse
	 * @Created:lining 2013年12月26日下午4:57:33
	 * @Modified:
	 */
	public static HttpResponse executeProxy(HttpUriRequest request)
			throws Exception {
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient/* _proxy */.execute(request);
		} catch (Exception e) {
			throw e;
		}
		return httpResponse;
	}

	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = HttpUtil.executeProxy(get);

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				System.out.println("访问成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
