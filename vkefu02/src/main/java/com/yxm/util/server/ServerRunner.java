package com.yxm.util.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

@Component
public class ServerRunner implements CommandLineRunner{

	private final SocketIOServer server;
	// private IMEventHandler imEventHandler ;
	@Autowired
	public ServerRunner(SocketIOServer server){
		this.server=server;
	}
	public void run(String... arg0) throws Exception {
		System.out.println("**ServerRunner run**"+server);
		//System.out.println("**imEventHandlerRunner run**"+imEventHandler);
		server.start();
	}

}
