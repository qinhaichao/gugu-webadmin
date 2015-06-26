package com.gugu.codeGenerate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class WriteXmlUtil {

	public static int getQueryNodeConsumer(String filePath, String noderef,
			Map<String, String> attrMap) {
		// <dubbo:reference id="customerService"
		// interface="com.lenovo.customer.api.CustomerService" />
		int count = 0;
		Document document = getPath(filePath, "UTF-8");
		List<Element> list = document.getRootElement().elements();
		for (Element li : list) {
			if (noderef.equals(li.attributeValue("id"))) {
				count++;
			}
		}
		return count;

	}

	public static int getQueryNodeProvider(String filePath, String noderef,
			Map<String, String> attrMap) {
		// <dubbo:service interface="com.lenovo.customer.api.CustomerService"
		// ref="customerService" />
		int count = 0;
		Document document = getPath(filePath, "UTF-8");
		List<Element> list = document.getRootElement().elements();
		for (Element li : list) {
			if (noderef.equals(li.attributeValue("ref"))) {
				count++;
			}
		}
		return count;
	}
	
	public static int getQueryNodeSqlMap(String filePath, String noderef,
			Map<String, String> attrMap) {
		//<sqlMap resource="sqlmap/MembersSqlMap.xml" />
		int count = 0;
		Document document = getPath(filePath, "UTF-8");
		List<Element> list = document.getRootElement().elements();
		for (Element li : list) {
			if (noderef.equals(li.attributeValue("ref"))) {
				count++;
			}
		}
		return count;
	}

	public static void addNodeConsumer(String filePath, String noderef,
			Map<String, String> attrMap) {
		if (getQueryNodeConsumer(filePath, noderef, attrMap) < 1) {
			Document document = getPath(filePath, "UTF-8");
			Element root = document.getRootElement();// 获取根节点
			Element node = root.addElement("dubbo:reference");
			for (Map.Entry<String, String> entry : attrMap.entrySet()) {
				node.addAttribute((String) entry.getKey(),
						(String) entry.getValue());
			}
			try {
				getXMLWrite(document, filePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("修改" + filePath + "成功");
		} else {
			System.out.println("已添");
		}
	}

	public static void addNodeProvider(String filePath, String noderef,
			Map<String, String> attrMap){
		// <dubbo:service interface="com.lenovo.customer.api.CustomerService"
		// ref="customerService" />
		if (getQueryNodeProvider(filePath, noderef, attrMap) < 1) {
			Document document = getPath(filePath, "UTF-8");
			Element root = document.getRootElement();// 获取根节点
			Element node = root.addElement("dubbo:service");
			for (Map.Entry<String, String> entry : attrMap.entrySet()) {
				node.addAttribute((String) entry.getKey(),
						(String) entry.getValue());
			}
			try {
				getXMLWrite(document, filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("修改" + filePath + "成功");
		} else {
			System.out.println("已添");
		}
	}
	
	public static void addNodeSqlMap(String filePath, String noderef,
			Map<String, String> attrMap){
		//<sqlMap resource="sqlmap/MembersSqlMap.xml" />
		if (getQueryNodeProvider(filePath, noderef, attrMap) < 1) {
			Document document = getPath(filePath, "UTF-8");
			Element root = document.getRootElement();// 获取根节点
			Element node = root.addElement("sqlMap");
			for (Map.Entry<String, String> entry : attrMap.entrySet()) {
				node.addAttribute((String) entry.getKey(),
						(String) entry.getValue());
			}
			try {
				getXMLWrite(document, filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("修改" + filePath + "成功");
		} else {
			System.out.println("已添");
		}
	}

	public static void getXMLWrite(Document document, String filePath)
			throws Exception {
		OutputFormat of = new OutputFormat(" ", true);
		of.setEncoding("UTF-8");
		XMLWriter xw = new XMLWriter(new FileWriter(filePath), of);
		xw.setEscapeText(false);
		xw.write(document);
		xw.close();
		//System.out.println(document.asXML());
	}

	public static Document getPath(String filePath, String coding) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			saxReader
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-external-dtd",
							false);
			BufferedReader read = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(filePath)), coding));
			document = saxReader.read(read);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static void main(String[] args) {
		String filePath = "D:\\word\\litwork\\m2code-generate\\service\\src\\main\\resources\\dubbo_config\\dubbo-customer-provider.xml";
		try {
			Map<String, String> map = new HashMap();
			map.put("interface", "com.lenovo.customer.api.GoodsinfoService");
			map.put("ref", "goodsinfoService");
			WriteXmlUtil.addNodeProvider(filePath, "goodsinfoService", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
