package com.yxm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent")
public class AgentController {
	
   @RequestMapping("/index")
   public String index(){
	   return "agent/index";
   }
}
