package com.gugu.utils.file;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import com.gugu.utils.Num62;

/**
 * �ļ������ɰ�����
 */
public class FileNameUtils {
	/**
	 * ���ڸ�ʽ�����󣬽���ǰ���ڸ�ʽ����yyyyMM��ʽ����������Ŀ¼��
	 */
	public static final DateFormat pathDf = new SimpleDateFormat("yyyyMM");
	/**
	 * ���ڸ�ʽ�����󣬽���ǰ���ڸ�ʽ����ddHHmmss��ʽ�����������ļ�����
	 */
	public static final DateFormat nameDf = new SimpleDateFormat("ddHHmmss");

	/**
	 * ���ɵ�ǰ���¸�ʽ���ļ�·��
	 * 
	 * yyyyMM 200806
	 * 
	 * @return
	 */
	public static String genPathName() {
		return pathDf.format(new Date());
	}

	/**
	 * �����Ե�ǰ�ա�ʱ�俪ͷ��4λ��������ļ���
	 * 
	 * ddHHmmss 03102230
	 * 
	 * @return 10λ�����ļ���
	 */
	public static String genFileName() {
		return nameDf.format(new Date())
				+ RandomStringUtils.random(4, Num62.N36_CHARS);
	}

	/**
	 * �����Ե�ǰʱ�俪ͷ��4λ��������ļ���
	 * 
	 * @param ext
	 *            �ļ�����׺������'.'
	 * @return 10λ�����ļ���+�ļ���׺
	 */
	public static String genFileName(String ext) {
		return genFileName() + "." + ext;
	}
	
	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}

	public static void main(String[] args) {
		System.out.println(genPathName());
		System.out.println(genFileName());
	}
}
