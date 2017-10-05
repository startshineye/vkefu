package com.yxm.web.dao;
import java.util.List;
import com.yxm.web.domain.ParamVO;
/**
 * 参数Dao
 * @author yxm
 * @date 2016-11-17
 */
public interface ParamDao{
	/**
	 * 获取用户排队提示语
	 * @param description
	 * @param agentId
	 * @return
	 */
	 ParamVO getUserQueueRemindByAgentId(String description,String role,String agentId);
	 /**
	  * 获取管理员设置的排队提示语
	  * @return
	  */
	 ParamVO getUserQueueRemindByAdmin(String description,String role);
	 /**
	  * 获取管理员的黑名单提示语(黑名单提示语只有管理员才有)
	  * @return
	  */
	 ParamVO getBalcklistRemindByAdmin(String description,String role);
	 /**
	  * 获取用户排队超时提示语(只有管理员才有)
	  * @return
	  */
	 ParamVO getUserQueueTimeoutRemindByAdmin(String description,String role);
	 /**
	  * 坐席获取最大接入用户数
	  * @return
	  */
	 ParamVO getMaxUserByAgentId(String description,String role,String agentId);
	 /**
	  * 坐席获取最大用户接入数
	  * @param description
	  * @param role
	  * @return
	  */
	 ParamVO getMaxUserByAdmin(String description,String role);
	 /**
	  * 获取坐席配置的欢迎词
	  * @param description
	  * @param role
	  * @param agentId
	  * @return
	  */
	 ParamVO getWelcomeByAgentId(String description,String role,String agentId);
	 /**
	  * 获取管理员配置的欢迎词
	  * @return
	  */
	 ParamVO getWelcomeByAdmin(String description,String role);
	 /*
	  * 获取管理员配置的排队时间
	  * @param description
	  * @param role
	  * @return
	  */
	 ParamVO getUserWaitingTimeByAdmin(String description,String role);
	 /**
	  * 获取管理员配置连接（只有管理员才有）
	  * @param description
	  * @param role
	  * @param agentId
	  * @return
	  */
	 ParamVO getLinkByAdmin(String description,String role);
	 /**
	  * 获取坐席自己的满意度
	  * @param description
	  * @param role
	  * @param agentId
	  * @return
	  */
	 List<ParamVO> getSatisfactionByAgentId(String description,String role,String agentId);
	  /**
	   * 获取管理员满意度
	   * @param description
	   * @param role
	   * @return
	   */
	 List<ParamVO> getSatisfactionByAdmin(String description,String role);
}



































