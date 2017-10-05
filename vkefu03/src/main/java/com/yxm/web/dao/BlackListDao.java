package com.yxm.web.dao;
import java.util.List;
import com.yxm.web.domain.BlackListVO;
/**
 * 黑名单Dao
 * @author yxm
 * @date 2016-11-24
 */
public interface BlackListDao {
	/**
	   * 获取所有黑名单
	   * @return List<BlackListVO>
	   */
	   List<BlackListVO> getAllBlackList();
	  /**
	   * 插入黑名单
	   * @param blackListVO
	   */
	  void insert(BlackListVO blackListVO);
	  /**
	   * 获取黑名单
	   * @param userInfo
	   * @return
	   */
	   BlackListVO getBlackList(String userInfo);
	  /***
	   * 根据用户信息查询黑名单
	   * @param userInfo
	   * @return
	   */
	  BlackListVO selectBlackListByUserInfo(String userInfo);
}
