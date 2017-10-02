package com.yxm.util.server;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.yxm.core.Context;
import com.yxm.util.server.handler.AgentEventHandler;
import com.yxm.util.server.handler.IMEventHandler;

@Component
public class ServerRunner implements CommandLineRunner{

	private final SocketIOServer server;
	@Autowired
	public ServerRunner(SocketIOServer server){
		System.out.println("***ServerRunner*** server "+server);
		this.server=server;
	}
	public void run(String... arg0) throws Exception {
		//System.out.println("**ServerRunner run**"+server+" imEventHandler"+imEventHandler+" agentHandler"+agentHandler);
		//System.out.println("**imEventHandlerRunner run**"+imEventHandler);
		server.getNamespace(Context.NameSpaceEnum.IM.getNamespace()).addListeners(new IMEventHandler(server));
		server.getNamespace(Context.NameSpaceEnum.AGENT.getNamespace()).addListeners(new AgentEventHandler(server));
		Collection<SocketIONamespace> allNamespaces = server.getAllNamespaces();
		for (SocketIONamespace socketIONamespace : allNamespaces) {
			System.out.println(" socketIONamespace"+socketIONamespace.getName());
		}
		server.start();
	}
}
