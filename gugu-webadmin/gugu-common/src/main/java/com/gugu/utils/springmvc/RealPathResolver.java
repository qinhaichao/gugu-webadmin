package com.gugu.utils.springmvc;

/**
 * ����·���ṩ��
 */
public interface RealPathResolver {
	/**
	 * ��þ���·��
	 * 
	 * @param path
	 * @return
	 * @see javax.servlet.ServletContext#getRealPath(String)
	 */
	public String get(String path);
}
