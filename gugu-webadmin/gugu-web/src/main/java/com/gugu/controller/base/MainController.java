package com.gugu.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController extends BaseController {

	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/main")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("main");
		mv.addObject("username", "Daniel");
		return mv;
	}
	
	public String login(HttpServletRequest request,
			HttpServletResponse response){
		
		return "login";
	}

}
