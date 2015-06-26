package com.${bussPackage}.controller${javasufixPath};

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.${bussPackage}.controller.base.BaseController;
import com.${bussPackage}.models${javasufixPath}.${className};
import com.${bussPackage}.service${javasufixPath}.${className}Service;
import com.${bussPackage}.view.BaseInfo;

/**
 * 
 * <br>
 * <b>description：</b>${className}Controller, ${className}Controller controller实现<br>
 * <b>author：</b>${author}<br>
 * <b>datetime：</b> ${dateTime} <br>
 * <b>copyright：<b>copyright(C) ${copyright}<br>
 */
@Controller
@RequestMapping("/${lowerName}")
public class ${className}Controller extends BaseController {

	Logger log = Logger.getLogger(this.getClass());
	private static int pageSize = 10;
	private static int pageNum = 1;

	@Autowired
	private ${className}Service ${lowerName}Service;

	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response) {
		${className} ${lowerName} = new ${className}();
		BaseInfo baseInfo = new BaseInfo();
		// ${lowerName} set property
		try {
			${lowerName}Service.insert(${lowerName});
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("${className}Controller:save()" + e.getMessage());
			log.error("${className}Controller:save()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/savemap")
	@ResponseBody
	public String saveMap(HttpServletRequest request,
			HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		try {
			${lowerName}Service.insertMap(param);
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("${className}Controller:saveMap()" + e.getMessage());
			log.error("${className}Controller:saveMap()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(HttpServletRequest request,
			HttpServletResponse response) {
		${className} ${lowerName} = new ${className}();
		BaseInfo baseInfo = new BaseInfo();
		// ${lowerName} set property
		try {
			${lowerName}Service.update(${lowerName});
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("${className}Controller:update()" + e.getMessage());
			log.error("${className}Controller:update()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/updatemap")
	@ResponseBody
	public String updateMap(HttpServletRequest request,
			HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		try {
			${lowerName}Service.updateMap(param);
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("${className}Controller:updateMap()" + e.getMessage());
			log.error("${className}Controller:updateMap()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,
			HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		try {
			if (null != param.get("id")) {
				${lowerName}Service.delete(Integer.parseInt(param.get("id")
						.toString()));
				baseInfo.setRc(0);
				baseInfo.setMsg("删除成功");
			} else {
				baseInfo.setRc(-1);
				baseInfo.setMsg("删除失败,id为空!");
				log.info("${className}Controller:delete()" + "操作的id为空");
			}
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("删除失败");
			log.info("${className}Controller:delete()" + e.getMessage());
			log.error("${className}Controller:delete()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/getbyid")
	@ResponseBody
	public String getById(HttpServletRequest request,
			HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		${className} ${lowerName} = new ${className}();
		try {
			if (null != param.get("id")) {
				${lowerName} = ${lowerName}Service.getById(Integer.parseInt(param.get("id")
						.toString()));
				baseInfo.setRc(0);
				baseInfo.setMsg("查询成功");
				baseInfo.setContent(toJson(${lowerName}));
			} else {
				baseInfo.setRc(-1);
				baseInfo.setMsg("查询失败,id为空!");
				log.info("${className}Controller:getById()" + "操作的id为空");
			}
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("查询失败");
			log.info("${className}Controller:getById()" + e.getMessage());
			log.error("${className}Controller:getById()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/count")
	@ResponseBody
	public String count(HttpServletRequest request, HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		int count = ${lowerName}Service.count(param);
		baseInfo.setRc(0);
		baseInfo.setMsg("查询成功");
		baseInfo.setContent(toJson(count));
		return toJson(baseInfo);
	}

	@RequestMapping("/getbymap")
	@ResponseBody
	public String getByMap(HttpServletRequest request,
			HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		${className} ${lowerName} = new ${className}();
		try {
			if (!param.isEmpty()) {
				${lowerName} = ${lowerName}Service.getByMap(param);
				baseInfo.setRc(0);
				baseInfo.setMsg("查询成功");
				baseInfo.setContent(toJson(${lowerName}));
			} else {
				baseInfo.setRc(-1);
				baseInfo.setMsg("查询失败,查询参数为空!");
				log.info("${className}Controller:getByMap()" + "查询参数为空");
			}
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("查询失败");
			log.info("${className}Controller:getByMap()" + e.getMessage());
			log.error("${className}Controller:getByMap()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest request,
			HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);

		//eg: name desc 
		String sortColumns="";
		if (null != param.get("pageSize")) {
			pageSize = Integer.parseInt(param.get("pageSize").toString());
		}
		if (null != param.get("pageNum")) {
			pageNum = Integer.parseInt(param.get("pageNum").toString());
		}
		int offset = (pageNum - 1) * pageSize;
		param.put("pageSize", pageSize);
		param.put("offset", offset);
		param.put("sortColumns", sortColumns);
		
		try {
			List<${className}> list = ${lowerName}Service.list(param);
			baseInfo.setRc(0);
			baseInfo.setMsg("查询成功");
			baseInfo.setContent(toJson(list));
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("查询失败");
			log.info("${className}Controller:getList()" + e.getMessage());
			log.error("${className}Controller:getList()" + e);
		}
		return toJson(baseInfo);
	}

}
