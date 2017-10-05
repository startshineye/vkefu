package com.yxm.web.dao;
import com.yxm.web.domain.LeaveMsgVO;
public interface LeaveMsgDao {
	/**
	 * 保存留言
	 * @param leave
	 */
	void save(LeaveMsgVO leave);
}
