package com.gugu.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * @description ֧�������е���־��¼������
 * @author qinhc123
 * 2015����11:58:49
 */
public class PayLogUtil {
	private final static Logger logger = Logger.getLogger(PayLogUtil.class);
	final static String LOG_STRING_REQ_MSG_BEGIN = "==============================REQUEST MSG BEGIN==============================";
	final static String LOG_STRING_REQ_MSG_END = "==============================REQUEST MSG END==============================";
	final static String LOG_STRING_RSP_MSG_BEGIN = "============================== RSPONSE MSG BEGIN==============================";
	final static String LOG_STRING_RSP_MSG_END = "============================== RSPONSE MSG END==============================";
	public final static int LOG_STRING_REQ = 0;
	public final static int LOG_STRING_RSP = 1;

	/**
	 * ��¼ERROR��־
	 * 
	 * @param cont
	 * @param ex
	 */
	public static void writeErrorLog(String cont, Throwable ex) {
		logger.error(cont, ex);
	}

	/**
	 * ��¼ERORR��־
	 * 
	 * @param cont
	 */
	public static void writeErrorLog(String cont) {
		logger.error(cont);
	}

	/**
	 * ��¼��ͨ��־
	 * 
	 * @param cont
	 */
	public static void writeLog(String cont) {
		logger.info(cont);
	}

	/**
	 * ��¼ͨ�ű���
	 * 
	 * @param msg
	 */
	public static void writeMessage(String msg) {
		logger.info(msg);
	}

	/**
	 * ��¼ͨ�ű���
	 * 
	 * @param msg
	 */
	public static void writeMessage(Map msg, int REQ_RSP_FLAG) {
		if (msg != null) {
			StringBuilder content = new StringBuilder();
			content.append("\r\n");
			if (0 == REQ_RSP_FLAG) {
				content.append(LOG_STRING_REQ_MSG_BEGIN);
			} else if (1 == REQ_RSP_FLAG) {
				content.append(LOG_STRING_RSP_MSG_BEGIN);
			} else {
				return;
			}
			Iterator<Entry<String, String>> it = msg.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> en = it.next();
				content.append("\r\n[" + en.getKey() + "] = [" + en.getValue()
						+ "]");
			}
			content.append("\r\n");
			if (0 == REQ_RSP_FLAG) {
				content.append(LOG_STRING_REQ_MSG_END);
			} else if (1 == REQ_RSP_FLAG) {
				content.append(LOG_STRING_RSP_MSG_END);
			}

			writeMessage(content.toString());
		}
	}

	public static void debug(String cont) {
		if (logger.isDebugEnabled()) {
			logger.debug(cont);
		}
	}

	public static void debug(Map msg, int REQ_RSP_FLAG) {
		if (logger.isDebugEnabled()) {
			if (msg != null) {
				StringBuilder content = new StringBuilder();
				content.append("\r\n");
				if (0 == REQ_RSP_FLAG) {
					content.append(LOG_STRING_REQ_MSG_BEGIN);
				} else if (1 == REQ_RSP_FLAG) {
					content.append(LOG_STRING_RSP_MSG_BEGIN);
				} else {
					return;
				}
				Iterator<Entry<String, String>> it = msg.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, String> en = it.next();
					content.append("\r\n[" + en.getKey() + "] = ["
							+ en.getValue() + "]");
				}
				content.append("\r\n");
				if (0 == REQ_RSP_FLAG) {
					content.append(LOG_STRING_REQ_MSG_END);
				} else if (1 == REQ_RSP_FLAG) {
					content.append(LOG_STRING_RSP_MSG_END);
				}

				debug(content.toString());
			}
		}
	}
}
