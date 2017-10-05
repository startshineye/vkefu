package com.yxm.web.service;
import com.yxm.web.domain.LeaveMsgVO;
public interface LeaveMsgService {
	/**
	 * 保存留言
	 * @param leave
	 */
	void save(LeaveMsgVO leave); 
}
