package com.yxm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView index(HttpServletRequest request){
		//获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ModelAndView modelAndView = new ModelAndView();
		if("admin".equals(username) && "123456".equals(password)){
			modelAndView.setViewName("WEB-INF/index.html");
		}else{
			modelAndView.setViewName("login.html");
		}
		return modelAndView;
	}
}
