package com.yxm.web.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxm.util.DateUtil;
import com.yxm.web.domain.LeaveMsgVO;
import com.yxm.web.domain.UserOptLogVO;
import com.yxm.web.service.LeaveMsgService;
import com.yxm.web.service.UserOptLogService;
/**
 * 留言消息controller
 * @author yxm
 * @date 2016-11-21
 */
@Controller
@RequestMapping("/leavemsg")
public class LeaveMsgController {
	@Autowired
    private LeaveMsgService leaveMsgService;
	@Autowired
	private UserOptLogService userOptService;
	@RequestMapping("/index")
	public String index(){
		return "leaveword/leave";
	}
	@RequestMapping("save")
	@ResponseBody
	public Object save(HttpServletRequest request){
		//{"success":true} 成功  {"success":false} 失败
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userInfo = request.getParameter("userInfo");
		String mobile = request.getParameter("mobile");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
	    String time = DateUtil.datetimeFormat.format(new Date());
	    
	    UserOptLogVO userOptLogVO = new UserOptLogVO();
	    userOptLogVO.setUserId(userId);
		userOptLogVO.setUserName(userName);
		userOptLogVO.setOptCode(3);//1-留言
		userOptLogVO.setQueueId("");
		userOptLogVO.setStartTime(time);
		userOptLogVO.setRemark("");
		try {
			if((!"".equals(email) || !"".equals(mobile) || !"".equals(qq)) && !"".equals(userInfo) && !"".equals(message)){
				LeaveMsgVO leaveMsgVO = new LeaveMsgVO();
				leaveMsgVO.setUserId(userId);
				leaveMsgVO.setUserName(userName);
				leaveMsgVO.setUserInfo(userInfo);
				leaveMsgVO.setMobile(mobile);
				leaveMsgVO.setQq(qq);
				leaveMsgVO.setStartTime(time);
				leaveMsgVO.setEmail(email);
				leaveMsgVO.setMessage(message);
				leaveMsgVO.setStatus(0);
				leaveMsgService.save(leaveMsgVO);
				userOptLogVO.setOptResult(1);
				userOptService.save(userOptLogVO);
				jsonMap.put("success", true);
			}else{
				jsonMap.put("success", false);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			userOptLogVO.setOptResult(0);//失败
			userOptService.save(userOptLogVO);
			jsonMap.put("success", false);
		}
		return jsonMap;
	}
}
