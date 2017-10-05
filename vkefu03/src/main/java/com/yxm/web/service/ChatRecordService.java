package com.yxm.web.service;
import javax.servlet.http.HttpServletRequest;

import com.yxm.web.domain.ChatRecordVO;
import com.yxm.web.domain.MessageVO;
import com.yxm.web.domain.UncheckedMessageVO;
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
