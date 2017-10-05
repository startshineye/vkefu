package com.yxm.web.dao;
import com.yxm.web.domain.UserGenerateVO;
public interface UserGenerateDao {
   void save(UserGenerateVO user);
   Integer getLastId();
   UserGenerateVO getByLastId();
   /**
    * 判断user是否为空
    * @return
    */
   int isEmpty();
   /**
    * 更新数据
    */
   void update(UserGenerateVO user);
}
