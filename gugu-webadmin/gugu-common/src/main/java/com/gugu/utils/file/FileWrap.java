package com.gugu.utils.file;

import java.io.File;
import java.io.FileFilter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;

import com.gugu.utils.Constants;
import com.gugu.utils.image.ImageUtils;

/**
 * �ļ���װ��
 * 
 * ����ǰ̨��ʾ�ļ���Ϣ
 */
public class FileWrap {
	/**
	 * �ɱ༭�ĺ�׺��
	 */
	public static final String[] EDITABLE_EXT = new String[] { "html", "htm",
			"css", "js", "txt" };
	private File file;
	private String rootPath;
	private FileFilter filter;
	private List<FileWrap> child;
	private String filename;
	
	private Boolean valid;

	/**
	 * ������
	 * 
	 * @param file
	 *            ����װ���ļ�
	 */
	public FileWrap(File file) {
		this(file, null);
	}

	/**
	 * ������
	 * 
	 * @param file
	 *            ����װ���ļ�
	 * @param rootPath
	 *            ��·�������ڼ������·��
	 */
	public FileWrap(File file, String rootPath) {
		this(file, rootPath, null);
	}

	/**
	 * ������
	 * 
	 * @param file
	 *            ����װ���ļ�
	 * @param rootPath
	 *            ��·�������ڼ������·��
	 * @param filter
	 *            �ļ���������Ӧ�����������ļ�
	 */
	public FileWrap(File file, String rootPath, FileFilter filter) {
		this.file = file;
		this.rootPath = rootPath;
		this.filter = filter;
	}
	/**
	 * ������
	 * 
	 * @param file
	 *            ����װ���ļ�
	 * @param rootPath
	 *            ��·�������ڼ������·��
	 */
	public FileWrap(File file, String rootPath, FileFilter filter,Boolean valid) {
		this.file = file;
		this.rootPath = rootPath;
		this.filter = filter;
		this.valid=valid;
	}

	/**
	 * �Ƿ�����༭
	 * 
	 * @param ext
	 *            �ļ���չ��
	 * @return
	 */
	public static boolean editableExt(String ext) {
		ext = ext.toLowerCase(Locale.ENGLISH);
		for (String s : EDITABLE_EXT) {
			if (s.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��������ļ��������ݸ�·��(rootPath)��
	 * 
	 * @return
	 */
	public String getName() {
		String path = file.getAbsolutePath();
		String relPath = path.substring(rootPath.length());
		return relPath.replace(File.separator, Constants.SPT);
	}

	/**
	 * ����ļ�·�����������ļ�����·����
	 * 
	 * @return
	 */
	public String getPath() {
		String name = getName();
		return name.substring(0, name.lastIndexOf('/'));
	}

	/**
	 * ����ļ�����������·�����ļ�����
	 * 
	 * ��û��ָ�����ƣ��򷵻��ļ���������ơ�
	 * 
	 * @return
	 */
	public String getFilename() {
		return filename != null ? filename : file.getName();
	}

	/**
	 * ��չ��
	 * 
	 * @return
	 */
	public String getExtension() {
		return FilenameUtils.getExtension(file.getName());
	}

	/**
	 * ����޸�ʱ�䡣������(long)��
	 * 
	 * @return
	 */
	public long getLastModified() {
		return file.lastModified();
	}

	/**
	 * ����޸�ʱ�䡣������(Timestamp)��
	 * 
	 * @return
	 */
	public Date getLastModifiedDate() {
		return new Timestamp(file.lastModified());
	}

	/**
	 * �ļ���С����λKB��
	 * 
	 * @return
	 */
	public long getSize() {
		return file.length() / 1024 + 1;
	}
	

	/**
	 * ����ļ���ͼ������
	 * <ul>
	 * <li>directory = folder</li>
	 * <li>jpg,jpeg = jpg</li>
	 * <li>gif = gif</li>
	 * <li>html,htm = html</li>
	 * <li>swf = swf</li>
	 * <li>txt = txt</li>
	 * <li>���� = unknow</li>
	 * </ul>
	 * 
	 * @return
	 */
	public String getIco() {
		if (file.isDirectory()) {
			return "folder";
		}
		String ext = getExtension().toLowerCase();
		if (ext.equals("jpg") || ext.equals("jpeg")) {
			return "jpg";
		} else if (ext.equals("png")) {
			return "png";
		} else if (ext.equals("gif")) {
			return "gif";
		} else if (ext.equals("html") || ext.equals("htm")) {
			return "html";
		} else if (ext.equals("swf")) {
			return "swf";
		} else if (ext.equals("txt")) {
			return "txt";
		} else if (ext.equals("sql")) {
			return "sql";
		}else {
			return "unknow";
		}
	}

	/**
	 * ����¼�Ŀ¼
	 * 
	 * ���û��ָ���¼�Ŀ¼���򷵻��ļ���������¼��ļ���������FileFilter��
	 * 
	 * @return
	 */
	public List<FileWrap> getChild() {
		if (this.child != null) {
			return this.child;
		}
		File[] files;
		if (filter == null) {
			files = getFile().listFiles();
		} else {
			files = getFile().listFiles(filter);
		}
		List<FileWrap> list = new ArrayList<FileWrap>();
		if (files != null) {
			Arrays.sort(files, new FileComparator());
			for (File f : files) {
				FileWrap fw = new FileWrap(f, rootPath, filter);
				list.add(fw);
			}
		}
		return list;
	}

	/**
	 * ��ñ���װ���ļ�
	 * 
	 * @return
	 */
	public File getFile() {
		return this.file;
	}

	/**
	 * �Ƿ�ͼƬ
	 * 
	 * @return
	 */
	public boolean isImage() {
		if (isDirectory()) {
			return false;
		}
		String ext = getExtension();
		return ImageUtils.isValidImageExt(ext);
	}

	/**
	 * �Ƿ�ɱ༭
	 * 
	 * @return
	 */
	public boolean isEditable() {
		if (isDirectory()) {
			return false;
		}
		String ext = getExtension().toLowerCase();
		for (String s : EDITABLE_EXT) {
			if (s.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �Ƿ�Ŀ¼
	 * 
	 * @return
	 */
	public boolean isDirectory() {
		return file.isDirectory();
	}

	/**
	 * �Ƿ��ļ�
	 * 
	 * @return
	 */
	public boolean isFile() {
		return file.isFile();
	}

	/**
	 * ���ô���װ���ļ�
	 * 
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * ָ������
	 * 
	 * @param name
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * ָ���¼�Ŀ¼
	 * 
	 * @param child
	 */
	public void setChild(List<FileWrap> child) {
		this.child = child;
	}
	

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}


	/**
	 * �ļ��Ƚ������ļ��п�ǰ�š�
	 */
	public static class FileComparator implements Comparator<File> {
		public int compare(File o1, File o2) {
			if (o1.isDirectory() && !o2.isDirectory()) {
				return -1;
			} else if (!o1.isDirectory() && o2.isDirectory()) {
				return 1;
			} else {
				return o1.compareTo(o2);
			}
		}
	}
}