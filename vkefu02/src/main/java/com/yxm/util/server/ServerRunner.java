package com.yxm.util.server;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.yxm.core.Context;
import com.yxm.util.server.handler.AgentEventHandler;

@Component
public class ServerRunner implements CommandLineRunner{

	private final SocketIOServer server;
	//private IMEventHandler imEventHandler ;
	private AgentEventHandler agentHandler;
	@Autowired
	public ServerRunner(SocketIOServer server,AgentEventHandler agentHandler){
		System.out.println("***ServerRunner*** server "+server);
		this.server=server;
		//this.imEventHandler=imEventHandler;
		this.agentHandler=agentHandler;
	}
	public void run(String... arg0) throws Exception {
		//System.out.println("**ServerRunner run**"+server+" imEventHandler"+imEventHandler+" agentHandler"+agentHandler);
		//System.out.println("**imEventHandlerRunner run**"+imEventHandler);
		//server.getNamespace(Context.NameSpaceEnum.IM.getNamespace()).addListeners(imEventHandler);
		server.getNamespace(Context.NameSpaceEnum.AGENT.getNamespace()).addListeners(agentHandler);
		Collection<SocketIONamespace> allNamespaces = server.getAllNamespaces();
		for (SocketIONamespace socketIONamespace : allNamespaces) {
			System.out.println(" socketIONamespace"+socketIONamespace.getName());
		}
		server.start();
	}
}
