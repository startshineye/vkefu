package com.yxm.web.service;
import java.util.List;
import com.yxm.web.domain.MessageVO;
import com.yxm.web.domain.PaginationVO;
public interface MessageService {
	/**
	 * 保存消息
	 * @param messageVO
	 */
   void save(MessageVO messageVO);
   /**
    * 根据threadid获取所有记录
    * @param threadid
    * @return
    */
   List<MessageVO> getAllByThreadid(String threadid);
   /**
    * 根据userId，agentId，historyTime查询记录
    * @param userId
    * @param agentId
    * @param historyTime
    * @return
    */
   List<MessageVO> getAllMessage(String userId,String agentId,String historyTime);
   /**
    * 根据userId,agentId,lastMsgId获取所有记录
    * @param threadid
    * @return
    */
   List<MessageVO> getAllByMessageid(String userId,String agentId,Integer lastMsgId);
   /**
    * 获取最新消息记录
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
    PaginationVO<MessageVO> getMessageListBySessionIdAndThreadId(Integer sessionId,Integer threadId);
    /***
     * 获取某个用户（sessionId）的历史
     * @param pageNo
     * @param pageSize
     * @param sessionId
     * @return
     */
    PaginationVO<MessageVO> getUserRecordByUserIdAndAgentId(Integer pageNo,
			Integer pageSize, String userId, String agentId);
	 /***
     * 获取总聊天记录数
     * @param sessionId
     * @return
     */
	Long getMessageTotal(String userId,String agentId);
	/***************************
	 * 获取总聊天记录数
	 * @param sessionId
	 * @param threadId
	 * **********************************/
	Long getMessageTotalBySessionIdAndAgentId(Integer sessionId,String agentId);
	/****
	 * 获取坐席端聊天记录
	 * @param pageNo
	 * @param pageSize
	 * @param sessionId
	 * @param threadId
	 * @return
	 */
	PaginationVO<MessageVO> getAgentRecord(Integer pageNo, Integer pageSize,
			Integer sessionId,String agentId);
	/**
	 * 获取聊天更多
	 * @param pageSize 
	 * @param pageNo 
	 * @param minmessageId 
	 * @return
	 */
	PaginationVO<MessageVO> getMoreHistoryByUserIdAndAgentId(Integer pageNo, Integer pageSize, String userId,String agentId, String minmessageId);
	/**
	 * 获取聊天记录
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @param agentId
	 * @return
	 */
	PaginationVO<MessageVO> getMoreHistoryByUserIdAndAgentIdNoMinMessageId(Integer pageNo, Integer pageSize, String userId, String agentId);
	/**
	 * 获取坐席响应时间
	 * @param threadId
	 * @param agentId
	 * @return
	 */
	String getResponseTimeByThreadIdAndAgentId(Integer threadId, String agentId);
}
