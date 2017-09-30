package com.yxm.web.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yxm.web.entity.Hello;
/**
 * 使用@RestController代替@controller和@ResponseBody
 * @author yxm
 */
@RestController
public class HelloController implements EnvironmentAware{
	
	private String JAVA_HOME;
	
	@RequestMapping("/hello")
    public Hello hello(){
		
    	Hello hello = new  Hello();
    	hello.setId(110);
    	hello.setName(JAVA_HOME);
    	return hello;
    }
	//在程序一启动就执行
	public void setEnvironment(Environment environment) {
		JAVA_HOME = environment.getProperty("JAVA_HOME");
		 System.out.println("Controller里获取参数  ["+JAVA_HOME+"]");
	}
}
