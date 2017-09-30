package com.yxm.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 普通类调用Spring bean对象：
 * 说明：
 * 1、此类需要放到App.java同包或者子包下才能被扫描，否则失效。
 * @author Administrator
 */
@Component
public class BeanUtil implements ApplicationContextAware{
	
	private static ApplicationContext  applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
	if(BeanUtil.applicationContext==null)
		BeanUtil.applicationContext=applicationContext;
	   System.out.println("---------------------------------------------------------------------");
       System.out.println("========ApplicationContext配置成功,在普通类可以通过调用BeanUtil.getAppContext()获取applicationContext对象,applicationContext="+BeanUtil.applicationContext+"========");
       System.out.println("---------------------------------------------------------------------");
	}
	
	public static ApplicationContext getApplicationContext(){
	  return applicationContext;
	}
	
	//通过name获取bean
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
	
	//通过class获取bean
	public static <T> Object getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}
	
	//通过name和class获取bean
	public static <T> Object getBean(String name,Class<T> clazz){
		return getApplicationContext().getBean(name, clazz);
	}
}
