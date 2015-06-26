package com.gugu.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class BigDecimalUtil {

	private static Logger logger = Logger.getLogger(BigDecimalUtil.class);

	public static String add(String augend, String addend) {
		return new BigDecimal(augend != null && !augend.equals("") ? augend
				: "0").add(
				new BigDecimal(addend != null && !addend.equals("") ? addend
						: "0")).toString();
	}

	public static String subtract(String minuend, String subtrahend) {
		return new BigDecimal(minuend != null && !minuend.equals("") ? minuend
				: "0")
				.subtract(
						new BigDecimal(subtrahend != null
								&& !subtrahend.equals("") ? subtrahend : "0"))
				.toString();
	}

	public static String mul(String multiplicand, String multiplier) {
		return new BigDecimal(
				multiplicand != null && !multiplicand.equals("") ? multiplicand
						: "0")
				.multiply(
						new BigDecimal(multiplier != null
								&& !multiplier.equals("") ? multiplier : "0"))
				.toString();
	}

	public static int compareToZero(String str) {
		return new BigDecimal(str != null && !str.equals("") ? str : "0")
				.compareTo((new BigDecimal("0")));
	}

	/**
	 * @function 将字符串数字转换指定格式
	 * @param number
	 * @param format
	 *            （例如：0.00,0.000）
	 * @return
	 */
	public static String numberStrformat(String number, String format) {
		try {
			BigDecimal big = new BigDecimal(
					(number != null && !"".equals(number)) ? number : "0");
			DecimalFormat d = new DecimalFormat("0.00");
			return d.format(big);
		} catch (Exception e) {
			logger.error("将字符串数字转换指定格式异常！", e);
			return "0.00";
		}
	}
}
