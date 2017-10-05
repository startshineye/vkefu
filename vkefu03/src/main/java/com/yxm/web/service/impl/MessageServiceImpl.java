package com.yxm.web.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.yxm.web.dao.MessageDao;
import com.yxm.web.domain.MessageVO;
import com.yxm.web.domain.PaginationVO;
import com.yxm.web.service.MessageService;
@Service("messageService")
public class MessageServiceImpl implements MessageService{
	@Resource(name = "messageDao")
    private MessageDao messageDao;
	public void save(MessageVO messageVO) {
		 messageDao.save(messageVO);
	}
	public List<MessageVO> getAllByThreadid(String threadid) {
		return messageDao.getAllByThreadid(threadid);
	}
	public List<MessageVO> getAllMessage(String userId,
			String agentId,String historyTime) {
		return   messageDao.getAllMessage(userId,agentId,historyTime);
	}
	public Long getLastMsgid(String userId, String agentId){
		System.out.println("--执行getLastMsgid--");
		return messageDao.getLastMsgid(userId, agentId);
	}
	public List<MessageVO> getAll() {
		return  messageDao.getAll();
	}
	public List<MessageVO> getAllByMessageid(String userId, String agentId,
			Integer lastMsgId) {
		return messageDao.getAllByMessageid(userId, agentId, lastMsgId);
	}
	public Integer getMessageId(String message, String fromId) {
		return messageDao.getMessageId(message, fromId);
	}
	public PaginationVO<MessageVO> getMessageListBySessionIdAndThreadId(
			Integer sessionId,Integer threadId) {
		try{
		//messageDao.get
		List<MessageVO> messageList = messageDao.getMessageListBySessionIdAndThreadId(sessionId, threadId);
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		paginationVO.setDataList(messageList);
		return paginationVO;
		}catch(Exception e){
			return null;
		}
	}
	public Long getMessageTotalBySessionIdAndAgentId(Integer sessionId,
			String agentId) {
		return messageDao.getMessageTotalBySessionIdAndAgentId(sessionId,agentId);
	}
	public PaginationVO<MessageVO> getAgentRecord(Integer pageNo,Integer pageSize, Integer sessionId,String agentId){
		List<MessageVO> dataList = messageDao.getAgentRecord(pageNo,pageSize,sessionId,agentId);
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	public PaginationVO<MessageVO> getUserRecordByUserIdAndAgentId(
			Integer pageNo, Integer pageSize, String userId, String agentId) {
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		List<MessageVO> dataList = messageDao.getUserRecordByUserIdAndAgentId(pageNo,pageSize,userId,agentId);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	public Long getMessageTotal(String userId, String agentId) {
		return messageDao.getMessageTotalByUserIdAndAgentId(userId,agentId);
	}
	public PaginationVO<MessageVO> getMoreHistoryByUserIdAndAgentId(Integer pageNo,
			Integer pageSize, String userId, String agentId,String minmessageId) {
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		List<MessageVO>dataList= messageDao.getMoreHistoryByUserIdAndAgentId(pageNo,pageSize,userId,agentId,minmessageId);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	public PaginationVO<MessageVO> getMoreHistoryByUserIdAndAgentIdNoMinMessageId(Integer pageNo, Integer pageSize, String userId, String agentId) {
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		List<MessageVO> dataList = messageDao.getMoreHistoryByUserIdAndAgentIdNoMinMessageId(pageNo,pageSize,userId,agentId);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	public String getResponseTimeByThreadIdAndAgentId(Integer threadId,
			String agentId) {
		return messageDao.getResponseTimeByThreadIdAndAgentId(threadId,agentId);
	}
}
