package com.yxm.web.controller;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxm.web.service.BlackListService;
import com.yxm.web.service.OnlineUserService;
import com.yxm.web.service.ParamService;
import com.yxm.web.service.UserOptLogService;
import com.yxm.web.service.UserService;
import com.yxm.web.service.WebSocketService;
/**
 * 坐席所对应的控制器
 * 
 * @author yxm
 * @date 2016-10-05
 */

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private UserOptLogService userOptService;
	
	@Autowired
	private BlackListService blackListService;
	
	@Autowired
	private WebSocketService webSocketService;
	
	@Autowired
	private OnlineUserService onlineUserService;
	 
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response,Model model){
		try {
			//String remoteAddr = request.getRemoteAddr();
			String remoteAddr = IPTools.getRealIp(request);
			String time = DateUtil.datetimeFormat.format(new Date());
			System.out.println("remoteAddr="+remoteAddr);
			
			//是否在黑名单中
			BlackListVO userInfo = blackListService.selectBlackListByUserInfo(remoteAddr);
			if(userInfo!=null && !"".equals(userInfo)){
				//返回到错误提示页面
				//获取黑名单里面的提示语放到session中返回
				ParamVO blackListVo = paramService.getBalcklistRemind(DataContext.PARAM_BALCKLISTREMIND);
				model.addAttribute("blacklistreminder",  blackListVo.getParamValue());
				return "common/error";
			}else{
				HttpSession session = request.getSession();
				String userName = request.getParameter("userName");
				//String userId=sessionId;
				String userId = null;
				
				//cookie设置
				Cookie[] cookies = request.getCookies();
				int flag = 0;// 用于标记前端是否含有此cookie如果没有为0
				for (Cookie co : cookies) {
					if ("cookieuserid".equals(co.getName())) {
						flag = 1;// 说明含有cookie
						System.out.println(co.getValue());
						userId=co.getValue();
					}
				}
				// 说明没有cookie，此时创建cookie，并且新增一个
				if (flag == 0) {
					// 设置cookie
					//判断user表是否为空
					UserGenerateVO usr = new UserGenerateVO();
					userService.save(usr);
					Integer lastId = userService.getLastId();
					usr.setUserId(lastId);
					usr.setUserName("访客"+lastId);
					userService.update(usr);
					UserGenerateVO uservo = userService.getByLastId();
					userId = ""+uservo.getUserId();
					 Cookie cookie = null;
					 cookie = new Cookie("cookieuserid",""+uservo.getUserId());
					 response.addCookie(cookie);
				}
				 //设置欢迎语
				 ParamVO welcome = paramService.getWelcome(DataContext.PARAM_WELCOME);
	             if(welcome!=null){
	            	 session.setAttribute("welcomeWord", welcome.getParamValue());
	             }
				//设置返回前端
			    Person person = new Person();
			    person.setPersonId(userId);
				if(userName==null){
					person.setPersonName("访客"+userId);
					userName = "访客"+userId;
				}else{
					person.setPersonName(userName);
				}
				person.setType("1");
				session.setAttribute("person",person);
				System.out.println("用户:"+person);
				
				/**
				 * 进入用户信息队列
				 */
				User user = new User();
				user.setIp(remoteAddr);
				user.setUserId(userId);
				user.setUserName(userName);
				user.setSource(DataContext.USER_SOURCE_WEBCC);
				user.setLoginTime(time);
				user.setChatStatus(DataContext.USER_CHATSTATUS_ON);
				ACDFactory.createUserManager().addUser(user);
				User user_tbl = onlineUserService.getByUserId(userId);
				if(user_tbl==null){
					onlineUserService.save(user);
				}
				return "user/chat";
				}
		  }catch (Exception e) {
			e.printStackTrace();
			return "common/errorUser";
		}
	}
	@RequestMapping("/leave")
	public String leavemsg(HttpServletRequest request){
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		request.setAttribute("userId",userId);
		request.setAttribute("userName",userName);
		/**
		 * 记录留言日志
		 */
		Integer optCode = DataContext.USER_OPTCODE_LEAVEMSG;
		Integer optResult = DataContext.OPTRESULT_SUCCESS;
		String remark="";
		String queueId = "";
		String startTime = DateUtil.datetimeFormat.format(new Date());
		userOptLog(userId, userName, optCode, queueId, startTime, remark, optResult);
		return "leaveword/leave";
	}
	/**
	 * 用户重新发起聊天
	 * @param request
	 */
	@RequestMapping("/rechat")
	public void reChat(HttpServletRequest request){
		try{
			String ip = request.getRemoteAddr();
			String userId = request.getParameter("userId");
			String userName = request.getParameter("userName");
			//String group = request.getParameter("group");
			String startTime = DateUtil.datetimeFormat.format(new Date());
			//Integer lastMsgId = Integer.parseInt(request.getParameter("lastMsgId"));
			User user = ACDFactory.createUserManager().getUser(userId);
			if(user==null){
				user = onlineUserService.getByUserId(userId);
			}
			if(user!=null){//取出以后不为空才执行
				//更新用户新登录时间
				user.setLoginTime(startTime);
				user.setChatStatus(DataContext.USER_CHATSTATUS_ON);
				ACDFactory.createUserManager().addUser(user);
				
				/**
				 * 户进队列记录日志
				 */
				Integer optCode = DataContext.USER_OPTCODE_ENQUEUE;
				String queueId = "";
				String remark = "";
				Integer optResult = DataContext.OPTRESULT_SUCCESS;
				userOptLog(userId, userName, optCode, queueId, startTime, remark, optResult);
				/**
				 * 用户进入队列
				 */
				ACDFactory.addWebccUserQueue(user);
				webSocketService.chat();//分配坐席用户聊天
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public void userOptLog(String userId,String userName,Integer optCode,String queueId,String startTime,String remark,Integer optResult){
		  /**
			 * 户进队列记录日志
			 */
			UserOptLogVO userOptLogVO = new UserOptLogVO();
			userOptLogVO.setUserId(userId);
			userOptLogVO.setUserName(userName);
			userOptLogVO.setOptCode(optCode);
			userOptLogVO.setQueueId(queueId);
			userOptLogVO.setStartTime(startTime);
			userOptLogVO.setRemark(remark);
			userOptLogVO.setOptResult(optResult);
			userOptService.save(userOptLogVO);
	  }
}