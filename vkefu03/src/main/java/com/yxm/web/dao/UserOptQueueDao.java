package com.yxm.web.dao;
import com.yxm.web.domain.UserOptQueueVO;
/**
 * 坐席操作dao
 * @author yxm
 * @date 2017-6-8
 */
public interface UserOptQueueDao {
	/**
	 * 保存
	 * @param userOptQueueVO
	 */
	void save(UserOptQueueVO userOptQueueVO);
    /**
     * 根据id更新
     * @param optCode
     * @param startTime
     * @param id
     */
	void updateUserOptQueueById(Integer optCode, String startTime, Integer id);
	/**
	 * 清空表
	 */
	void delAll();
}
