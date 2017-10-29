package com.yxm.util;
import com.corundumstudio.socketio.SocketIOClient;
import com.yxm.util.factory.ACDFactory;
import com.yxm.web.domain.MessageVO;
import com.yxm.web.entity.agent.Agent;
public class OnlineUtil {
	public static MessageVO getRequestMessage(String userId, String userName, SocketIOClient client) {
		//分配坐席
		Agent allotAgent = ACDFactory.allotAgent("webcc");
		
		//组装消息
		MessageVO messageVO = new MessageVO();
		if(allotAgent!=null){
			messageVO.setFromId(allotAgent.getAgentId());
			messageVO.setFromName(allotAgent.getAgentName());
			messageVO.setMessage("很高兴为您服务!");
		}else{
			messageVO.setMessage("坐席全忙");
		}
		return messageVO;
	}

}
