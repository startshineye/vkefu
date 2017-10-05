package com.founder.focuss.webcc.service;

import javax.servlet.http.HttpServletRequest;

import com.founder.focuss.webcc.domain.ChatRecordVO;
import com.founder.focuss.webcc.domain.MessageVO;
import com.founder.focuss.webcc.domain.UncheckedMessageVO;

public interface ChatRecordService {
	/**
	 * 根据userid获取聊天记录
	 * @param userid
	 * @param count
	 * @return
	 */
   ChatRecordVO<MessageVO> getRecordById(HttpServletRequest request,Integer pageNo,Integer pageSize);
   
   UncheckedMessageVO<MessageVO> getUncheckedMessage(String userid,Integer lastmsgid);
}
