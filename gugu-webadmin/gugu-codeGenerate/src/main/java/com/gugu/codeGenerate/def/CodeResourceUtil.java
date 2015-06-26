package com.gugu.codeGenerate.def;

import java.util.Locale;
/**
 * @description 配置文件工具类
 */
import java.util.ResourceBundle;

public class CodeResourceUtil {
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("code_generator/code_generator", Locale.getDefault());

	public static void main(String[] args) {
       System.out.println(getDIVER_NAME());
	}

	public static String DIVER_NAME = "com.mysql.jdbc.Driver";

	public static String URL = "jdbc:mysql://10.120.20.155:3306/b2c?useUnicode=true&characterEncoding=UTF-8";

	public static String USERNAME = "root";

	public static String PASSWORD = "root";

	public static String DATABASE_NAME = "b2c";

	public static String DATABASE_TYPE = "mysql";
	public static String DATABASE_TYPE_MYSQL = "mysql";
	public static String DATABASE_TYPE_ORACLE = "oracle";

	public static String SOURCE_ROOT_PACKAGE = "";
	public static String BUSSI_PACKAGE = "lenovo";
	public static String BUSSI_PACKAGE_URL = "lenovo";

	public static String BASE_SERVCIE = "";

	public static String TEMPLATEPATH;

	public static String SYSTEM_ENCODING;
	
	public static String MAPPER_PACKAGE="src/main/resources";
	static {
		DIVER_NAME = getDIVER_NAME();
		URL = getURL();
		USERNAME = getUSERNAME();
		PASSWORD = getPASSWORD();
		DATABASE_NAME = getDATABASE_NAME();
		SYSTEM_ENCODING = getSystemEncoding();
		TEMPLATEPATH = getTemplatepath();
		SOURCE_ROOT_PACKAGE = getSourceRootPackage();
		BASE_SERVCIE = getBaseServcie();
		BUSSI_PACKAGE = getBussiPackage();
		BUSSI_PACKAGE_URL = BUSSI_PACKAGE.replace(".", "/");
		MAPPER_PACKAGE=getMapperPackage();
		if ((URL.indexOf("mysql") >= 0) || (URL.indexOf("MYSQL") >= 0))
			DATABASE_TYPE = DATABASE_TYPE_MYSQL;
		else if ((URL.indexOf("oracle") >= 0) || (URL.indexOf("ORACLE") >= 0)) {
			DATABASE_TYPE = DATABASE_TYPE_ORACLE;
		}

		

	}

	// 读取code_generator.properties配置文件中信息
	public static final String getDIVER_NAME() {
		return bundle.getString("diver_name");
	}

	public static final String getURL() {
		return bundle.getString("url");
	}

	public static final String getUSERNAME() {
		return bundle.getString("username");
	}

	public static final String getPASSWORD() {
		return bundle.getString("password");
	}

	public static final String getDATABASE_NAME() {
		return bundle.getString("database_name");
	}

	public static final String getSourceRootPackage() {
		return bundle.getString("source_root_package");
	}

	public static final String getBaseServcie() {
		return bundle.getString("base_servcie");
	}

	public static final String getTemplatepath() {
		return bundle.getString("templatepath");
	}

	public static final String getSystemEncoding() {
		return bundle.getString("system_encoding");
	}

	public static final String getBussiPackage() {
		return bundle.getString("bussi_package");
	}
	
	public static final String getMapperPackage() {
		return bundle.getString("mapper_package");
	}

}
