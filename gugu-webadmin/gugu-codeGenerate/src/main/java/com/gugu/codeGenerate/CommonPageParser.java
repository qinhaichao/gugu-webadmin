package com.gugu.codeGenerate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gugu.codeGenerate.factory.CodeGenerateFactory;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CommonPageParser {
	private static final String CONTENT_ENCODING = "UTF-8";
	private static final Log log = LogFactory.getLog(CommonPageParser.class);
	private static boolean isReplace = true;
	private static Configuration configuration = null;
	private static Map dataMap;
	
	static {
		configuration = new Configuration();
		configuration.setDefaultEncoding(CONTENT_ENCODING);
	}
	
	public static void WriterPage(Map<String,Object> context, String templateName,
			String fileDirPath, String targetFile) {
		dataMap = context;
		TemplateLoader templateLoader = null;
		String path = "";
		Template t=null;
		// 使用FileTemplateLoader
		try {
			String templateBasePath = CodeGenerateFactory.getProjectPath(
					"gugu-codeGenerate").replace("\\", "/")
					+ "src/main/resources/code_generator/template";
			templateLoader = new FileTemplateLoader(new File(templateBasePath));
			configuration.setTemplateLoader(templateLoader); 
			path = "/"+templateName;
			t = configuration.getTemplate(path, CONTENT_ENCODING);
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		try {
			File file = new File(fileDirPath + targetFile);
			if (!file.exists()) {
				new File(file.getParent()).mkdirs();
			} else if (isReplace) {
				log.info("替换文件:" + file.getAbsolutePath());
			}
			Writer out = null;
			try {
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter oWriter = new OutputStreamWriter(fos, CONTENT_ENCODING);// 这个地方对流的编码不可或缺，
				out = new BufferedWriter(oWriter);
				t.process(dataMap, out);
				fos.close();
				oWriter.close();
				out.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("生成文件：" + file.getAbsolutePath());
			log.info("生成文件：" + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
