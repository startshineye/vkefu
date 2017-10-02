package com.yxm.util.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.yxm.core.Context;
import com.yxm.util.server.handler.AgentEventHandler;
import com.yxm.util.server.handler.IMEventHandler;

@Component
public class ServerRunner implements CommandLineRunner{

	private final SocketIOServer server;
	private IMEventHandler imEventHandler ;
	private AgentEventHandler agentHandler;
	@Autowired
	public ServerRunner(SocketIOServer server,IMEventHandler imEventHandler,AgentEventHandler agentHandler){
		System.out.println("***ServerRunner*** server "+server);
		this.server=server;
		this.imEventHandler=imEventHandler;
		this.agentHandler=agentHandler;
	}
	public void run(String... arg0) throws Exception {
		System.out.println("**ServerRunner run**"+server);
		//System.out.println("**imEventHandlerRunner run**"+imEventHandler);
		server.getNamespace(Context.NameSpaceEnum.IM.getNamespace()).addListeners(imEventHandler);
		server.start();
	}
}
