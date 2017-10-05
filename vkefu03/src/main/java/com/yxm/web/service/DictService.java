package com.founder.focuss.webcc.service;

import com.founder.focuss.webcc.domain.DictVO;
public interface DictService {
  /**
   * 保存用户
   * @param user
   */
  void save(DictVO dictVO);
  
  Integer getIdByDictType(String type);

}
   
