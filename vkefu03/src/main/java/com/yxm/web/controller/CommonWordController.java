package com.founder.focuss.webcc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.founder.focuss.webcc.domain.CommonWordVO;
import com.founder.focuss.webcc.service.CommonWordService;

@Controller
@RequestMapping("commonword")
public class CommonWordController {
	
	@Autowired
	private CommonWordService commonWordService;

	@RequestMapping("/get")
	@ResponseBody
	public Object getCommonWord(HttpServletRequest request){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		try {
			List<CommonWordVO> commonWordList = commonWordService.getCommonWordList();
			jsonMap.put("commonWordList", commonWordList);
		} catch (Exception e) {
			jsonMap.put("commonWordList", "");
		}
		return jsonMap;
	}
}
