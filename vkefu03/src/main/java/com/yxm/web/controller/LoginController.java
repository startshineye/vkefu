package com.yxm.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/admin")
public class LoginController {

	@RequestMapping("/login")
	@ResponseBody
	public Object index(HttpServletRequest request){
		//获取参数
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		
		String errInfo = "";
		Map<String,String> map = new HashMap<String,String>();
		
		//判断用户名,密码
		if(!StringUtils.isBlank(loginname) && !StringUtils.isBlank(password)){
			if("admin".equals(loginname) && "123456".equals(password)){
				errInfo="success";
			}else{
				errInfo="loginerror";
			}
		}
		if(!StringUtils.isBlank(code)){
			if("bzzr".equals(code)){
				errInfo="codeerror";
			}
		}
		map.put("result", errInfo);
		return map;
	}
}
