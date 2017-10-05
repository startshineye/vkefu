package com.yxm.web.service;
import com.yxm.web.domain.SatisfactionVO;
public interface SatisfactionService {
	/**
	 * 保存信息
	 * @param threadVO
	 */
	void save(SatisfactionVO satisfaction);
	 
    /**
     * 根据threadId和agentId获取满意度
     * @param threadId
     * @param agentId
     * @return
     */
	SatisfactionVO getSatisfactionByThreadIdAndAgentId(Integer threadId,
			String agentId);
}
