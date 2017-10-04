package com.yxm.util.exception;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;

public class GlobalExceptionListener extends ExceptionListenerAdapter{
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionListener.class);
	
	@Override
    public void onEventException(Exception e, List<Object> data, SocketIOClient client) {
		log.info("**onEventException**");
		if(e instanceof IOException){
			log.info(e.getMessage());
		}else{
			log.error(e.getMessage());
		}
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
    	log.info("**onDisconnectException**");
		if(e instanceof IOException){
			log.info(e.getMessage());
		}else{
			log.error(e.getMessage());
		}
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
    	log.info("**onConnectException**");
		if(e instanceof IOException){
			log.info(e.getMessage());
		}else{
			log.error(e.getMessage());
		}
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
    	log.info("**exceptionCaught**");
		if(e instanceof IOException){
			log.info(e.getMessage());
		}else{
			log.error(e.getMessage());
		}
        return true;
    }
}
