package com.founder.focuss.webcc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.MessageDao;
import com.founder.focuss.webcc.domain.MessageVO;
import com.founder.focuss.webcc.domain.PaginationVO;
import com.founder.focuss.webcc.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
	@Resource(name = "messageDao")
    private MessageDao messageDao;
	@Override
	public void save(MessageVO messageVO) {
		 messageDao.save(messageVO);
	}
	@Override
	public List<MessageVO> getAllByThreadid(String threadid) {
		return messageDao.getAllByThreadid(threadid);
	}
	@Override
	public List<MessageVO> getAllMessage(String userId,
			String agentId,String historyTime) {
		return   messageDao.getAllMessage(userId,agentId,historyTime);
	}
	@Override
	public Long getLastMsgid(String userId, String agentId){
		System.out.println("--执行getLastMsgid--");
		return messageDao.getLastMsgid(userId, agentId);
	}
	@Override
	public List<MessageVO> getAll() {
		return  messageDao.getAll();
	}
	@Override
	public List<MessageVO> getAllByMessageid(String userId, String agentId,
			Integer lastMsgId) {
		return messageDao.getAllByMessageid(userId, agentId, lastMsgId);
	}
	@Override
	public Integer getMessageId(String message, String fromId) {
		return messageDao.getMessageId(message, fromId);
	}
	@Override
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
	@Override
	public Long getMessageTotalBySessionIdAndAgentId(Integer sessionId,
			String agentId) {
		return messageDao.getMessageTotalBySessionIdAndAgentId(sessionId,agentId);
	}
	@Override
	public PaginationVO<MessageVO> getAgentRecord(Integer pageNo,Integer pageSize, Integer sessionId,String agentId){
		List<MessageVO> dataList = messageDao.getAgentRecord(pageNo,pageSize,sessionId,agentId);
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	
	@Override
	public PaginationVO<MessageVO> getUserRecordByUserIdAndAgentId(
			Integer pageNo, Integer pageSize, String userId, String agentId) {
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		List<MessageVO> dataList = messageDao.getUserRecordByUserIdAndAgentId(pageNo,pageSize,userId,agentId);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	@Override
	public Long getMessageTotal(String userId, String agentId) {
		return messageDao.getMessageTotalByUserIdAndAgentId(userId,agentId);
	}
	@Override
	public PaginationVO<MessageVO> getMoreHistoryByUserIdAndAgentId(Integer pageNo,
			Integer pageSize, String userId, String agentId,String minmessageId) {
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		List<MessageVO>dataList= messageDao.getMoreHistoryByUserIdAndAgentId(pageNo,pageSize,userId,agentId,minmessageId);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	@Override
	public PaginationVO<MessageVO> getMoreHistoryByUserIdAndAgentIdNoMinMessageId(Integer pageNo, Integer pageSize, String userId, String agentId) {
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		List<MessageVO> dataList = messageDao.getMoreHistoryByUserIdAndAgentIdNoMinMessageId(pageNo,pageSize,userId,agentId);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	@Override
	public String getResponseTimeByThreadIdAndAgentId(Integer threadId,
			String agentId) {
		return messageDao.getResponseTimeByThreadIdAndAgentId(threadId,agentId);
	}
}
