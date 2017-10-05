package com.yxm.web.dao;
import java.util.List;
import com.yxm.web.domain.CommonWordVO;
/**
 * 常用语Dao
 * @author yxm
 * @date 2016-11-24
 */
public interface CommonWordDao {
  /**
   * 获取所有黑名单
   * @return List<BlackListVO>
   */
   List<CommonWordVO> getCommonWordList();
}
