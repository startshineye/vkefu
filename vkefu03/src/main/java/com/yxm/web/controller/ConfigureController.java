package com.yxm.web.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 参数配置相关Controller
 * @author yxm
 * @date 2016-10-12
 */
@Controller
@RequestMapping("/config")
public class ConfigureController {
  /*  @Resource(name = "autoReplyService")
	private AutoReplyService autoReplyService;*/
	/**
	 * 自动回复配置
	 * @param request
	 */
	@RequestMapping("autoreply")
	@ResponseBody
	public Object ConfigAutoReply(HttpServletRequest request,Integer orderNo){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		//1.获取参数
		String agentId = request.getParameter("agentId");
		String content = request.getParameter("content");
		 
		System.out.println("agentId:"+agentId+"content:"+content+"orderNo:"+orderNo);
		try{
		//2.查询数据库，有的话就更新，没有的话就插入
		/*int count = autoReplyService.selectById(agentId);*/
		/*if(count>0){
			autoReplyService.configAutoReply(agentId, content, orderNo);
		}else{
            autoReplyService.insertAutoReply(agentId, content, orderNo);			
		}*/
		//3.jsonMap返回值
		jsonMap.put("success",true);
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("success",false);
		}
		return jsonMap;
	}
}
