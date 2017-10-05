package com.yxm.web.service;
import com.yxm.web.domain.BlackListVO;
/**
 * 黑名单service接口
 * @author yxm
 * @date 2016-12-12
 */
public interface BlackListService {
	/**
	 * 添加黑名单
	 * @param blackListVO
	 */
   void add(BlackListVO blackListVO);
   /**
    * 获取黑名单用户信息
    * @param blackListVO
    * @return
    */
   BlackListVO getUserInfo(String userInfo);
   /***
    * 根据用户信息查询黑名单
    * @param userInfo
    * @return
    */
   BlackListVO selectBlackListByUserInfo(String userInfo);
}
