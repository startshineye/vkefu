package com.yxm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yxm.util.Tools;

@Controller
@RequestMapping("/im")
public class IMController {
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		String sessionId = request.getSession().getId();
		String userId = Tools.genIDByKey(sessionId);
		String userName = "Guest_"+userId;
		
		ModelAndView model = new ModelAndView();
		model.setViewName("user/index");
		model.addObject("sessionId",sessionId);
		model.addObject("userId",userId);
		model.addObject("userName",userName);
		model.addObject("hostName", request.getServerName());
		model.addObject("port", request.getServerPort());
		model.addObject("schema", request.getScheme()) ;
		System.out.println("userId:"+userId+" userName:"+userName+" session:"+sessionId);
		return model;
	}
}
