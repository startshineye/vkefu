package com.yxm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yxm.util.Tools;
import com.yxm.util.factory.ACDFactory;
import com.yxm.web.entity.agent.Agent;

@Controller
@RequestMapping("/agent")
public class AgentController {
	
   @RequestMapping("/index")
   public ModelAndView index(HttpServletRequest request){
	   String sessionId = request.getSession().getId();
		String agentId = "1101";
		String agentName = "yxm";
		ModelAndView model = new ModelAndView();
		model.setViewName("im/agent/index");
		model.addObject("sessionId",sessionId);
		model.addObject("agentId",agentId);
		model.addObject("agentName",agentName);
		model.addObject("hostName", request.getServerName());
		model.addObject("port", request.getServerPort());
		model.addObject("schema", request.getScheme()) ;
		System.out.println("agentId:"+agentId+" agentName:"+agentName+" session:"+sessionId);
		
		//进入队列
		Agent agent = new Agent();
		agent.setAgentId(agentId);
		agent.setAgentName(agentName);
		ACDFactory.freeAgentQueue.add(agent);
		return model;
   }
   
   @RequestMapping("/chat")
   public ModelAndView chat(HttpServletRequest request){
	   String sessionId = request.getSession().getId();
		String agentId = "1101";
		String agentName = "yxm";
		ModelAndView model = new ModelAndView();
		model.setViewName("im/agent/chat");
		model.addObject("sessionId",sessionId);
		model.addObject("agentId",agentId);
		model.addObject("agentName",agentName);
		model.addObject("hostName", request.getServerName());
		model.addObject("port", request.getServerPort());
		model.addObject("schema", request.getScheme()) ;
		System.out.println("agentId:"+agentId+" agentName:"+agentName+" session:"+sessionId);
		return model;
   }
}
