package com.yxm.web.dao;
import java.util.List;

import com.yxm.web.domain.UserOptLogVO;
/**
 * 用户操作dao
 * @author yxm
 * @date 2017-6-8
 *
 */
public interface UserOptLogDao {
	boolean save(UserOptLogVO userOptLogVO);

	List<UserOptLogVO> getUserOptLogListByLastUserOptId(long lastUserOptId);
}
