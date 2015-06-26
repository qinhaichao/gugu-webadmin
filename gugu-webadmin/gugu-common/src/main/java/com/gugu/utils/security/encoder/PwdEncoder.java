package com.gugu.utils.security.encoder;

/**
 * ������ܽӿ�
 */
public interface PwdEncoder {
	/**
	 * �������
	 * 
	 * @param rawPass
	 *            δ�������룬null��Ϊ�մ�
	 * @return ���ܺ�����
	 */
	public String encodePassword(String rawPass);

	/**
	 * �������
	 * 
	 * @param rawPass
	 *            δ�������룬null��Ϊ�մ�
	 * @param salt
	 *            ������
	 * @return
	 */
	public String encodePassword(String rawPass, String salt);

	/**
	 * ��֤�����Ƿ���ȷ
	 * 
	 * @param encPass
	 *            ��������
	 * @param rawPass
	 *            δ�������룬null��Ϊ�մ�
	 * @return true:������ȷ��false:�������
	 */
	public boolean isPasswordValid(String encPass, String rawPass);

	/**
	 * ��֤�����Ƿ���ȷ
	 * 
	 * @param encPass
	 *            ��������
	 * @param rawPass
	 *            δ�������룬null��Ϊ�մ�
	 * @param salt
	 *            ������
	 * @return true:������ȷ��false:�������
	 */
	public boolean isPasswordValid(String encPass, String rawPass, String salt);
}
