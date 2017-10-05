package com.yxm.web.dao;
import com.yxm.web.domain.SatisfactionVO;
public interface SatisfactionDao {
	 void save(SatisfactionVO satisfaction);
     /**
      * 查询满意度评价与否
      * @param sessionId
      * @param agentId
      * @return
      */
	SatisfactionVO getSatisfactionByThreadIdAndAgentId(Integer threadId,String agentId);
}
