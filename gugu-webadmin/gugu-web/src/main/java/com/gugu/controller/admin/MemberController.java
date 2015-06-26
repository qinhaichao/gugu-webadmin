package com.gugu.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gugu.controller.base.BaseController;
import com.gugu.models.admin.Member;
import com.gugu.service.admin.MemberService;
import com.gugu.view.BaseInfo;

/**
 * 
 * <br>
 * <b>description：</b>MemberController, MemberController controller实现<br>
 * <b>author：</b>Daniel<br>
 * <b>datetime：</b> 2015-06-25 19:34:00 <br>
 * <b>copyright：<b>copyright(C) 2015, gugu151.com<br>
 */

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	Logger log = Logger.getLogger(this.getClass());
	private static int pageSize = 10;
	private static int pageNum = 1;

	@Autowired
	private MemberService memberService;

	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response) {
		Member member = new Member();
		BaseInfo baseInfo = new BaseInfo();
		// member set property
		try {
			memberService.insert(member);
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("MemberController:save()" + e.getMessage());
			log.error("MemberController:save()" + e);
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
			memberService.insertMap(param);
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("MemberController:saveMap()" + e.getMessage());
			log.error("MemberController:saveMap()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(HttpServletRequest request,
			HttpServletResponse response) {
		Member member = new Member();
		BaseInfo baseInfo = new BaseInfo();
		// member set property
		try {
			memberService.update(member);
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("MemberController:update()" + e.getMessage());
			log.error("MemberController:update()" + e);
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
			memberService.updateMap(param);
			baseInfo.setRc(0);
			baseInfo.setMsg("保存成功");
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("保存失败");
			log.info("MemberController:updateMap()" + e.getMessage());
			log.error("MemberController:updateMap()" + e);
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
				memberService.delete(Integer.parseInt(param.get("id")
						.toString()));
				baseInfo.setRc(0);
				baseInfo.setMsg("删除成功");
			} else {
				baseInfo.setRc(-1);
				baseInfo.setMsg("删除失败,id为空!");
				log.info("MemberController:delete()" + "操作的id为空");
			}
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("删除失败");
			log.info("MemberController:delete()" + e.getMessage());
			log.error("MemberController:delete()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/getbyid")
	@ResponseBody
	public String getById(HttpServletRequest request,
			HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		Member member = new Member();
		try {
			if (null != param.get("id")) {
				member = memberService.getById(Integer.parseInt(param.get("id")
						.toString()));
				baseInfo.setRc(0);
				baseInfo.setMsg("查询成功");
				baseInfo.setContent(toJson(member));
			} else {
				baseInfo.setRc(-1);
				baseInfo.setMsg("查询失败,id为空!");
				log.info("MemberController:getById()" + "操作的id为空");
			}
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("查询失败");
			log.info("MemberController:getById()" + e.getMessage());
			log.error("MemberController:getById()" + e);
		}
		return toJson(baseInfo);
	}

	@RequestMapping("/count")
	@ResponseBody
	public String count(HttpServletRequest request, HttpServletResponse response) {
		BaseInfo baseInfo = new BaseInfo();
		Map<String, Object> param = getRequestParam(request);
		int count = memberService.count(param);
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
		Member member = new Member();
		try {
			if (!param.isEmpty()) {
				member = memberService.getByMap(param);
				baseInfo.setRc(0);
				baseInfo.setMsg("查询成功");
				baseInfo.setContent(toJson(member));
			} else {
				baseInfo.setRc(-1);
				baseInfo.setMsg("查询失败,查询参数为空!");
				log.info("MemberController:getByMap()" + "查询参数为空");
			}
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("查询失败");
			log.info("MemberController:getByMap()" + e.getMessage());
			log.error("MemberController:getByMap()" + e);
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
			List<Member> list = memberService.list(param);
			baseInfo.setRc(0);
			baseInfo.setMsg("查询成功");
			baseInfo.setContent(toJson(list));
		} catch (Exception e) {
			baseInfo.setRc(-1);
			baseInfo.setMsg("查询失败");
			log.info("MemberController:getList()" + e.getMessage());
			log.error("MemberController:getList()" + e);
		}
		return toJson(baseInfo);
	}

}
