package com.yxm.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxm.web.domain.CommonWordVO;
import com.yxm.web.service.CommonWordService;
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
