package com.yxm.web.service;
/**
 * 系统初始化service
 * @author yxm
 * @date 2016-11-15
 */
public interface SystemInitService {
   public void run();
   public void clearAgentOptTimeSlice();
   public void clearUserOptQueue();
}
