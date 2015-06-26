package com.gugu.codeGenerate.factory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gugu.codeGenerate.CommonPageParser;
import com.gugu.codeGenerate.CreateBean;
import com.gugu.codeGenerate.WriteXmlUtil;
import com.gugu.codeGenerate.def.CodeResourceUtil;
import com.gugu.codeGenerate.def.FtlDef;

public class CodeGenerateFactory
{
  private static final Log log = LogFactory.getLog(CodeGenerateFactory.class);
  private static String url = CodeResourceUtil.URL;
  private static String username = CodeResourceUtil.USERNAME;
  private static String passWord = CodeResourceUtil.PASSWORD;
  private static String base_service = CodeResourceUtil.BASE_SERVCIE;
  private static String buss_package=CodeResourceUtil.BUSSI_PACKAGE;
//  private static String projectPath = getProjectPath();
  
  public static void codeGenerate(String tableName, String codeName, String entityPackage, String keyType,String batisType,String author)
  {
    CreateBean createBean = new CreateBean();
    createBean.setMysqlInfo(url, username, passWord);
    

    String className = createBean.getTablesNameToClassName(tableName);
    String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
    

    String pckPath = CodeResourceUtil.SOURCE_ROOT_PACKAGE+"\\com\\" + CodeResourceUtil.BUSSI_PACKAGE_URL + "\\";
    
    String modelPath="";
    String mapperPath="";
    String mapperXmlPath="";
    String servicePath="";
    String serviceImplPath="";
    String controllerPath="";
    String javasufixPath="";

    if(StringUtils.isEmpty(entityPackage)){
    	modelPath+="models\\"+ className + ".java";
    	mapperPath="dao\\"  + className + "Mapper.java";
        mapperXmlPath="dao\\"  + className + "Mapper.xml";
        servicePath="service\\"  + className + "Service.java";
        serviceImplPath="service\\"  + className + "ServiceImpl.java";
        controllerPath="controller\\"  + className + "Controller.java";
    }else{
    	modelPath+="models\\" + entityPackage + "\\"+ className + ".java";
    	mapperPath="dao\\"  + entityPackage + "\\"+ className + "Mapper.java";
        mapperXmlPath="dao\\"  + entityPackage + "\\"+ className + "Mapper.xml";
        servicePath="service\\"  + entityPackage + "\\"+ className + "Service.java";
        serviceImplPath="service\\"  + entityPackage + "\\"+ className + "ServiceImpl.java";
        controllerPath="controller\\"  + entityPackage + "\\"+ className + "Controller.java";
    	javasufixPath="."+entityPackage.replace("\\", ".");

    }

    Map<String,Object> context = new HashMap<String,Object>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat formatYYYY = new SimpleDateFormat("yyyy");
    SimpleDateFormat formatYMDHms = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    context.put("javasufixPath", javasufixPath);
    context.put("author", author);
    context.put("className", className);
    context.put("lowerName", lowerName);
    context.put("codeName", codeName);
    context.put("tableName", tableName);
    context.put("bussPackage", buss_package);
    context.put("entityPackage", entityPackage);
    context.put("keyType", keyType);
    context.put("baseService", base_service);
    context.put("dateTime", format.format(new Date()));
    context.put("copyright", formatYYYY.format(new Date())+", gugu151.com");
    Random rand=new Random();
    context.put("randnum", formatYMDHms.format(new Date())+rand.nextInt(100)+"L");
    try
    {
      context.put("feilds", createBean.getBeanFeilds(tableName));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    try
    {
      if(batisType.equals(FtlDef.KEY_BATISTYPE_I)){
    	  Map<String, Object> sqlMap = createBean.getAutoCreateSqlIbatis(tableName);
          context.put("columnDatas", createBean.getColumnDatas(tableName));
          context.put("SQL", sqlMap);
      }else{
    	  Map<String, Object> sqlMap = createBean.getAutoCreateSql(tableName);
          context.put("columnDatas", createBean.getColumnDatas(tableName));
          context.put("SQL", sqlMap);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return;
    }
    
    if(batisType.equals(FtlDef.KEY_BATISTYPE_I)){
    	// 暂且空着
    }else{
    	CommonPageParser.WriterPage(context, "MybatisModelTemplate.ftl", getProjectPath("gugu-models")+pckPath, modelPath);
        CommonPageParser.WriterPage(context, "MybatisMapperTemplate.ftl", getProjectPath("gugu-dao")+pckPath, mapperPath);
        CommonPageParser.WriterPage(context, "MybatisMapperXmlTemplate.xml", getProjectPath("gugu-dao")+pckPath, mapperXmlPath);
        CommonPageParser.WriterPage(context, "MybatisServiceTemplate.ftl", getProjectPath("gugu-service")+pckPath, servicePath);
        CommonPageParser.WriterPage(context, "MybatisServiceImplTemplate.ftl", getProjectPath("gugu-service")+pckPath, serviceImplPath);
        CommonPageParser.WriterPage(context, "MybatisControllerTemplate.ftl", getProjectPath("gugu-web")+pckPath, controllerPath);
    }
    
     
	System.out.println("----------------------------代码生成完毕---------------------------");
  }
  
  public static String getProjectPath(String projectName)
  {
	String p=System.getProperty("user.dir");
	String path="";
	path=p.substring(0,p.lastIndexOf("\\"))+"\\"+projectName+"\\";
    //String path = System.getProperty("user.dir").replace("service", projectName) + "\\";
    return path;
  }
  
	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(getProjectPath("gugu-dao"));
	}
}
