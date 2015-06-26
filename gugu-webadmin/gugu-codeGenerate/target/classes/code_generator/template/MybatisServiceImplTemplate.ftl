package com.${bussPackage}.service${javasufixPath};

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.${bussPackage}.dao${javasufixPath}.${className}Mapper;
import com.${bussPackage}.models${javasufixPath}.${className};

/**
 * 
 * <br>
 * <b>description：</b>${className}ServiceImpl, ${className}Service 基本接口实现<br>
 * <b>author：</b>${author}<br>
 * <b>datetime：</b> ${dateTime} <br>
 * <b>copyright：<b>copyright(C) ${copyright}<br>
 */
 

@Service
public class ${className}ServiceImpl implements ${className}Service {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ${className}Mapper ${lowerName}Dao;

	@Override
	public void insert(${className} ${lowerName}) {
		if(null!=${lowerName}){
			${lowerName}Dao.insert(${lowerName});
		}
		log.info("${className}ServiceImpl:insert()"+${lowerName}.toString());
	}

	@Override
	public void insertMap(Map<String, Object> map) {
		if(!map.isEmpty()){
			${lowerName}Dao.insertMap(map);
		}
		log.info("${className}ServiceImpl:insertMap()"+map.toString());
	}

	@Override
	public void update(${className} ${lowerName}) {
		if(null!=${lowerName}){
			${lowerName}Dao.update(${lowerName});
		}
		log.info("${className}ServiceImpl:update()"+${lowerName}.toString());
	}

	@Override
	public void updateMap(Map<String, Object> map) {
		if(!map.isEmpty()){
			${lowerName}Dao.updateMap(map);
		}
		log.info("${className}ServiceImpl:updateMap()"+map.toString());
	}

	@Override
	public void delete(int id) {
		${lowerName}Dao.delete(id);
		log.info("${className}ServiceImpl:delete()"+id);
	}

	@Override
	public int count(Map<String, Object> map) {
		log.info("${className}ServiceImpl:count()"+map.toString());
		return ${lowerName}Dao.count(map);
	}

	@Override
	public ${className} getById(int id) {
		log.info("${className}ServiceImpl:getById()"+id);
		return ${lowerName}Dao.getById(id);
	}

	@Override
	public ${className} getByMap(Map<String, Object> map) {
		if(!map.isEmpty()){
			log.info("${className}ServiceImpl:getByMap()"+map.toString());
			return ${lowerName}Dao.getByMap(map);
		}else{
			log.info("${className}ServiceImpl:getByMap(),map key-value is null!");
			return null;
		}
	}

	@Override
	public List<${className}> list(Map<String, Object> map) {
		log.info("${className}ServiceImpl:list()"+map.toString());
		return ${lowerName}Dao.list(map);
	}

}
