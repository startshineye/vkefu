package com.founder.focuss.webcc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.founder.focuss.webcc.domain.ChatRecordVO;
import com.founder.focuss.webcc.domain.MessageVO;
import com.founder.focuss.webcc.domain.ThreadVO;
import com.founder.focuss.webcc.domain.UncheckedMessageVO;
import com.founder.focuss.webcc.service.ChatRecordService;
import com.founder.focuss.webcc.service.MessageService;
import com.founder.focuss.webcc.service.ThreadService;

@Service("chatRecordService")
public class ChatRecordServiceImpl implements ChatRecordService {
	@Autowired
	private ThreadService threadService;

	@Autowired
	private MessageService messageService;

	private static final Logger logger;

	static {
		logger = Logger.getLogger(ChatRecordServiceImpl.class);
	}

	@Override
	public ChatRecordVO<MessageVO> getRecordById(HttpServletRequest request,Integer pageNo, Integer pageSize) {
		try {
			List<MessageVO> dataList = new ArrayList<MessageVO>();
			// 1.获取参数
			String userId = request.getParameter("userId");
			String agentId = request.getParameter("agentId");
			String historyTime = request.getParameter("historyTime");
			// 2.根据userid和agentid获取list
			List<MessageVO> messageList = messageService.getAllMessage(userId,agentId, historyTime);
			int size = messageList.size();
			// int size = ChatRecordUtil.chatRecordList.size();
			ChatRecordVO<MessageVO> chatRecordVO = new ChatRecordVO<MessageVO>();
			int pageTotal = size % pageSize == 0 ? size / pageSize : size/ pageSize + 1;
			// chatRecordVO.setTotol(size);//聊天记录可允许按键次数
			pageNo = pageTotal - pageNo + 1;
			int num = 0;
			for (Object object : messageList) {
				if (num >= (pageNo - 1) * pageSize && num < pageNo * pageSize) {
					dataList.add((MessageVO) object);
				}
				num++;
			}
			chatRecordVO.setTotal(size);// 聊天记录可允许按键次数
			chatRecordVO.setPerTotal(dataList.size());
			chatRecordVO.setPageTotal(pageTotal);
			chatRecordVO.setDataList(dataList);
			return chatRecordVO;
		} catch (Exception e) {
			logger.error("ChatRecordVO getRecordById is error");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UncheckedMessageVO<MessageVO> getUncheckedMessage(String userId,
			Integer lastmsgid) {
		try {
			// 1.根据userid从threadid中获取最新的userid和agentid返回对象
			ThreadVO lastThreadVO = threadService.getLastById(userId);
			// 2.根据userid和agentid获取list
			List<MessageVO> messageList = messageService.getAllByMessageid(userId, lastThreadVO.getAgentId(), lastmsgid);
			Long lastMsgId = messageService.getLastMsgid(userId,lastThreadVO.getAgentId());
			UncheckedMessageVO<MessageVO> uncheckedMessageVO = new UncheckedMessageVO<MessageVO>();
			uncheckedMessageVO.setLastMsgId(lastMsgId);
			uncheckedMessageVO.setDataList(messageList);
			return uncheckedMessageVO;
		} catch (Exception e) {
			logger.error("UncheckedMessageVO getUncheckedMessage is error");
			e.printStackTrace();
			return null;
		}
	}

}
