package com.yxm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.yxm.core.Context;
/** 大家也许会看到有些demo使用了3个注解： @Configuration @EnableAutoConfiguration @ComponentScan
 * 其实：@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于使用三个注解
 *@SpringBootApplication声明应用启动类
 */
@SpringBootApplication
public class App 
{
	//注入application.properties的属性到指定变量中
	@Value("${im.server.host}")
    private String host;
	
	@Value("${im.server.port}")
	private Integer port;
	
	@Bean
	public SocketIOServer socketIOServer(){
		System.out.println("**SocketIOServer method**");
		Configuration configuration = new Configuration();
		configuration.setHostname(host);
		configuration.setPort(port);
		
		 //该处可以用来进行身份验证 
		configuration.setAuthorizationListener(new  AuthorizationListener(){
			public boolean isAuthorized(HandshakeData data){
				//http://localhost:8081?username=test&password=test  
                //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证  
               //String username = data.getSingleUrlParam("username");  
               //String password = data.getSingleUrlParam("password");
				return true;
			}
		});
		final SocketIOServer socketIOServer = new SocketIOServer(configuration);
		System.out.println("namespace"+Context.NameSpaceEnum.IM.toString());
		//socketIOServer.addNamespace(Context.NameSpaceEnum.IM.getNamespace());//绑定namespace
		socketIOServer.addNamespace(Context.NameSpaceEnum.AGENT.getNamespace());
		System.out.println("Configuration host"+host+" port"+port+" socketIOServer"+socketIOServer);
		return socketIOServer;
	}
	
	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer){
		System.out.println("**springAnnotationScanner method**");
		return new SpringAnnotationScanner(socketIOServer);
	}
    public static void main( String[] args )
    {
    	/**
    	 * 使用SpringApplication.run启动应用
    	 */
    	System.out.println("main excute!");
        SpringApplication.run(App.class, args);
    }
}










