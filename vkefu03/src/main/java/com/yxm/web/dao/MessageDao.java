package com.yxm.web.dao;
import java.util.List;

import com.yxm.web.domain.MessageVO;
/**
 * 消息类dao
 * @author ye_xinming
 */
public interface MessageDao {
	/**
	 * 保存Message
	 * @param messageVO
	 */
  Integer save(MessageVO messageVO);
  /**
   * 获取 threadid对应的所有聊天记录
   * @param threadid
   * @return
   */
 List<MessageVO> getAllByThreadid(String threadid);
 /**
  * 通过userId,agentId获取聊天记录
  * @param userId
  * @param agentId
  * @return
  */
 List<MessageVO> getAllMessage(String userId,String agentId,String historyTime);
 /**
  * 通过userId,agentId,lastmsgid获取聊天记录
  * @param userId
  * @param agentId
  * @return
  */
 List<MessageVO> getAllByMessageid(String userId,String agentId,Integer lastMsgId);
 /**
  * 通过userId,agentId获取最新messageid
  * @param userId
  * @param agentId
  * @return
  */
 Long getLastMsgid(String userId,String agentId);
 Integer getMessageId(String message,String fromId);
 List<MessageVO> getAll();
 /**
  * 获取聊天信息前20条记录
  * @param threadId
  * @return
  */
List<MessageVO> getMessageListBySessionIdAndThreadId(Integer sessionId,Integer threadId);
/**
 * 获取未读消息
 * @param agentId
 * @param sessionId
 * @param lastmsgId
 * @return
 */
List<MessageVO> getUnreadMessage(Integer threadId,Integer sessionId,Integer lastmsgId);
/** 
 * 根据sessionId获取ThreadList
 * @param sessionId
 */
List<MessageVO> getUnreadUserMessage(Integer sessionId,Integer lastmsgId);
/************
 * 根据sessionId和threadId获取聊天条数
 * @param sessionId
 * @param threadId
 * @return
 */
Long getMessageTotalBySessionIdAndAgentId(Integer sessionId,String agentId);
/***
 * 获取座席端聊天记录
 * @param pageNo
 * @param pageSize
 * @param sessionId
 * @param threadId
 * @return
 */
List<MessageVO> getAgentRecord(Integer pageNo, Integer pageSize,
		Integer sessionId,String agentId);
/**
 * 获取坐席响应时间
 * @param threadId
 * @param agentId
 * @return String
 */
String getResponseTimeByThreadIdAndAgentId(Integer threadId, String agentId);
/**
 * 获取聊天更多
 * @param pageSize 
 * @param pageNo 
 * @param userId
 * @param agentId
 * @param minmessageId 
 * @return
 */
List<MessageVO> getMoreHistoryByUserIdAndAgentId(Integer pageNo, Integer pageSize, String userId, String agentId, String minmessageId);
/**
 * 获取用户聊天记录
 * @param pageNo
 * @param pageSize
 * @param userId
 * @param agentId
 * @return
 */
List<MessageVO> getUserRecordByUserIdAndAgentId(Integer pageNo,Integer pageSize, String userId, String agentId);
/**
 * 根据userId和agentId获取聊天条数
 * @param userId
 * @param agentId
 * @return
 */
Long getMessageTotalByUserIdAndAgentId(String userId, String agentId);
/**
 * 获取聊天更多
 * @param pageNo
 * @param pageSize
 * @param userId
 * @param agentId
 * @return
 */
List<MessageVO> getMoreHistoryByUserIdAndAgentIdNoMinMessageId(Integer pageNo,
		Integer pageSize, String userId, String agentId);
}
