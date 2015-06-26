package com.gugu.utils.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * �ļ��洢�ӿ�
 */
public interface Repository {
	public String storeByExt(String ext, InputStream in) throws IOException;

	public String storeByName(String name, InputStream in) throws IOException;

	public boolean retrieve(String name, OutputStream out);
}
