package com.yxm.web.service;
import java.util.List;

import com.yxm.web.domain.ParamVO;
/**
 * 参数service
 * @author yxm
 * @date 2016-11-17
 */
public interface ParamService {
	/**
	 * 获取用户排队提示语
	 * @param description
	 * @param agentId
	 * @return
	 */
	 ParamVO getUserQueueRemind(String description,String agentId);
	 /**
	  * 获取管理员的黑名单提示语(黑名单提示语只有管理员才有)
	  * @return
	  */
	 ParamVO getBalcklistRemind(String description);
	 /**
	  * 获取用户排队超时提示语(只有管理员才有)
	  * @return
	  */
	 ParamVO getUserQueueTimeoutRemind(String description);
	 /**
	  * 坐席获取最大接入用户数
	  * @return
	  */
	 ParamVO getMaxUser(String description,String agentId);
	 /**
	  * 获取坐席配置的欢迎词
	  * @param description
	  * @param role
	  * @param agentId
	  * @return
	  */
	 ParamVO getWelcome(String description,String agentId);
	 /*
	  * 获取管理员配置的排队时间
	  * @param description
	  * @param role
	  * @return
	  */
	 ParamVO getUserWaitingTime(String description);
	 /**
	  * 获取管理员配置连接（只有管理员才有）
	  * @param description
	  * @param role
	  * @param agentId
	  * @return
	  */
	 ParamVO getLink(String description);
	 /**
	  * 获取坐席自己的满意度
	  * @param description
	  * @param role
	  * @param agentId
	  * @return
	  */
	 List<ParamVO> getSatisfaction(String description,String agentId);
	 /**
	  * 获取管理员自己的欢迎词
	  * @param description
	  * @return
	  */
	ParamVO getWelcome(String description);
}
