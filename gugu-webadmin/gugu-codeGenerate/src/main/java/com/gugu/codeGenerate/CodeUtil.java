package com.gugu.codeGenerate;

/**
 * Created by Daniel on 2015-05-26.
 */
import com.gugu.codeGenerate.def.FtlDef;
import com.gugu.codeGenerate.factory.CodeGenerateFactory;

public class CodeUtil {

	public static void main(String[] args) throws Exception {
		/** 此处修改成你的 表名 和 中文注释 ***/
		String tableName = "member"; //
		String codeName = "用户信息表";// 中文注释 当然你用英文也是可以的
		String authorName="Daniel";//author name 中文，英文都可以
		String entityPackage = "admin";// 实体包  admin
		// 系统中的主键生成方式用自增的方式
		String keyType = FtlDef.KEY_TYPE_02;// 主键生成方式 01:UUID 02:自增
		// dao 层是用mybatis 实现的
		String batisType=FtlDef.KEY_BATISTYPE_M;//dao层的实现，KEY_BATISTYPE_I：Ibatis,KEY_BATISTYPE_M mybatis
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage,keyType,batisType,authorName);
	}

	

}
