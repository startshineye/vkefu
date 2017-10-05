package com.yxm.web.service;

import com.founder.focuss.webcc.domain.UserGenerateVO;
public interface UserService {
  /**
   * 保存用户
   * @param user
   */
  void save(UserGenerateVO user);
  /**
   * 获取用户最后id
   * @return
   */
  Integer getLastId();
  
  UserGenerateVO getByLastId();
  /**
   * 判断user是否为空
   * @return
   */
  Integer isEmpty();
  /**
   * 更新数据
   */
  void update(UserGenerateVO user);
}
