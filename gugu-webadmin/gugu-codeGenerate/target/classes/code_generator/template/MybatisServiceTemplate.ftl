package com.${bussPackage}.service${javasufixPath};

import java.util.List;
import java.util.Map;

import com.${bussPackage}.models${javasufixPath}.${className};

/**
 * 
 * <br>
 * <b>description：</b>${className}Service, ${codeName} service基本接口<br>
 * <b>author：</b>${author}<br>
 * <b>datetime：</b> ${dateTime} <br>
 * <b>copyright：<b>copyright(C) ${copyright}<br>
 */
 
public interface ${className}Service {
	
	public void insert(${className} ${lowerName});

	public void insertMap(Map<String, Object> map);

	public void update(${className} ${lowerName});

	public void updateMap(Map<String, Object> map);

	public void delete(int id);

	public ${className} getById(int id);

	public int count(Map<String, Object> map);

	public ${className} getByMap(Map<String, Object> map);

	public List<${className}> list(Map<String, Object> map);

}
