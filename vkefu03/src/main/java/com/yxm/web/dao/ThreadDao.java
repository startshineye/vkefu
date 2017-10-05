package com.yxm.web.dao;
import java.util.List;

import com.yxm.web.domain.ThreadVO;
public interface ThreadDao {
	/**
	 * 根据agentId获取聊天列表
	 * @param userId
	 * @return
	 */
	List<Integer> getThreadListByAgentId(String agentId);
	/**
	 *  根据agentId获取聊天列表
	 * @param userId
	 * @return
	 */
	List<Integer> getThreadIdListByAgentId(String agentId);
	/**
	 * 根据userId获取聊天列表
	 * @param userId
	 * @return
	 */
	List<Integer> getThreadIdListByUserId(String userId);
	/**
    * 根据userId获取聊天列表
	 * @param userId
	 * @return
	 */
	List<ThreadVO> getThreadListByUserId(String userId);
	/**
	 * 保存聊天
	 * @param threadVO
	 */
  void save(ThreadVO threadVO);
  /**
   * 更新聊天
   * @param threadVO
   */
  void update(ThreadVO threadVO);
    /***
     * 根据sessionId和agentId来修改状态
     * @param status
     * @param sessionId
     * @param agentId
     */
  void updateStatusBySessionIdAndAgentId(Integer status,Integer sessionId,String agentId);
  /***
   * 根据sessionId和userId来修改状态
   * @param status
   * @param sessionId
   * @param agentId
   */
void updateStatusBySessionIdAndUserId(Integer status,Integer sessionId,String userId);
  /**
   * 根据threadid改变坐席状态
   * @param agentId
   */
  void updateStatusByThreadId(Integer threadId,Integer status);
    /**
   * 根据agentId和sessionId获取status
   * @param agentId
   * @param sessionId
   * @return
   */
  Integer getStatusByAgentIdAndSessionId(String agentId,Integer sessionId);
 
  /**
   * 获取所有聊天
   * @return
   */
  List<ThreadVO> getAll();
  /**
   * 获取群聊组
   * @return
   */
  List<ThreadVO> getGroupThreadBySource(Integer source);
  /**
   * 根据agentId,userId查询最近的记录
   * @param agentId
   * @param userId
   * @return List<ThreadVO>
   */
  ThreadVO getLastThreadByAgentIdAndUserId(String agentId,String userId);
  /**
   * 根据userId获取最新的对象
   * @param userId
   * @return
   */
  ThreadVO getLastById(String userId);
  /**
   * 根据两个id来修改
   * @param userId
   * @param agentId
   */
  void updateById(String param,String userId,String agentId);
  /******
   * 更改满意度评价
   * @param sessionId
   * @param agentId
   */
void updateStatisfactionBySessionIdAndAgentId(String stisfaction,Integer sessionId, String agentId);


/** 
 * 根据状态2获取会话id
 * @param agentId
 */
Integer[] getSessionIdByEndStatusAndAgentId(String agentId);

/*
*//**
 * 根据sessionId和agentId获取ThreadId
 * @param sessionId
 * @param agentId
 * @return
 *//*
Integer getThreadIdBySessionIdAndAgentId(Integer sessionId, String agentId);
*/

/***
 * 根据状态2和userId获取会话id
 * @param userId
 */
Integer[] getSessionIdByEndStatusAndUserId(String userId);
/**
 * 根据status=2与agentId获取ThreadList
 * @param agentId
 * @return
 */
List<ThreadVO> getThreadListByEndStatusAndAgentId(String agentId);
/**
 * 根据status=2与UserId获取ThreadList,判断此用户对应的会话是否结束
 * @param userId
 * @return
 */
List<ThreadVO> getThreadListByEndStatusAndUserId(String userId);
 
/**
 * NEW 根据agentuserid status更改thread表中状态
 * @param i
 * @param agentUserId
 */
void updateStatusByEndStatusAndAgentUserId(int i, Integer agentUserId);
/**
 * NEW 根据agentUserId和status获取threadId
 * @param agentUserId
 * @return
 */
Integer[] getThreadIdByEndStatusAndAgentUserId(Integer agentUserId);
/**
 * NEW 聊天结束
 * @param i
 * @param responseTime
 * @param j
 * @param endTime
 * @param userId
 * @param agentId
 */
void agentEndByEndStatusAndUserIdAndAgentId(Integer status, String responseTime,Integer endCause,
		String endTime, String userId, String agentId);
/**
 * NEW 根据agentuserid判断是否有未结束的thread
 * @param agentUserId
 */
ThreadVO getThreadByEndStatusAndAgentUserId(Integer agentUserId);
/**
 * NEW 根据status,userId,agentId获取thread
 * @param userId
 * @param agentId
 * @return
 */
ThreadVO getThreadByEndStatusAndUserIdAndAgentId(String userId, String agentId);
/**
 * 更改最近一次的聊天记录
 * @param i
 * @param userId
 * @param agentId
 */
void updateLastStatusByUserIdAndAgentId(Integer status, String userId, String agentId);
/**
 * 根据agentuserid更改最新一条记录
 * @param status
 * @param agentUserId
 */
void updateLastStatusByAgentUserId(Integer status,Integer agentUserId);
}
