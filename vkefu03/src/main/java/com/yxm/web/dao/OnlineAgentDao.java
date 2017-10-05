package com.yxm.web.dao;
import com.yxm.web.entity.agent.Agent;
public interface OnlineAgentDao {
  void insert(Agent agent);
  void update(Agent agent);
  Agent getByAgentId(String agentId);
  String getLoginTimeByAgentId(String agentId);
  String getAgentLoginByAgentId(String agentId);
  /**
   * 更改坐席状态
   * @param status
   * @param agentId
   */
  void updateAgentStatusByAgentId(String status, String agentId);
}
