package com.yxm.base.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 * @author yxm
 *
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
	
/*	 @Scheduled(cron = "0/3 * * * * ?") // 每3秒执行一次
	public void schedule(){
		System.out.println("SchedulingConfig method schedule");
	}*/

}
