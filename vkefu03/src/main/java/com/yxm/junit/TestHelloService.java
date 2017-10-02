package com.yxm.junit;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yxm.App;
import com.yxm.junit.service.HelloService;
//SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringJUnit4ClassRunner.class)
//指定SpringBoot工程启动工程
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration
public class TestHelloService {
	//测试service
	//@Autowired
	@Autowired
	private HelloService service;
	
	@Test
	public void testPrintHello(){
		Assert.assertEquals("hello", service.printHello());
	}
   
}
