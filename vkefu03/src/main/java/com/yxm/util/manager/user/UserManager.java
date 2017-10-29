package com.yxm.util.manager.user;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;

import com.yxm.web.domain.MessageVO;
import com.yxm.web.entity.user.User;
public class UserManager implements IUserManager{
	//Feild
	private static Map<String,User> userMap;
	private static Map<String,List<MessageVO>> messageMap;
    private static UserManager manager = new UserManager();
	//Constructor
	private UserManager(){
		userMap = new ConcurrentHashMap<>();
		messageMap = new ConcurrentHashMap<>();
	}
	//method
	public static IUserManager getInstance(){
		return manager;
	}
	@Override
	public boolean addUser(User user){
		if(user!=null){
			String userId = user.getUserId();
			if(!StringUtils.isBlank(userId)){
				//添加之前先移除之前的
				removeUser(userId);
				userMap.put(userId, user);
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean removeUser(String userId){
		if(!StringUtils.isBlank(userId)){
			userMap.remove(userId);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeUser(User user) {
		if(user!=null){
			String userId = user.getUserId();
			return removeUser(userId);
		}
		return false;
	}
	@Override
	public User getUser(String userId) {
		if(userMap.containsKey(userId)){
			return userMap.get(userId);
		}
		return null;
	}
	@Override
	public boolean contains(String userId) {
		if(userMap.containsKey(userId)){
			return true;
		}
		return false;
	}
	@Override
	public boolean putMessage(String userId,MessageVO message){
		if(userMap.containsKey(userId)){
			//向用户对应的redis中添加消息
			List<MessageVO> list = messageMap.get(userId);
			list.add(message);
			messageMap.put(userId, list);
			System.err.println("用户"+userId+"putMessage 后的 messageMap"+messageMap.toString());
			return true;
		}
		return false;
	}
	@Override
	public List<MessageVO> getMessage(String userId){
		List<MessageVO> tempList = new ArrayList<MessageVO>();
		if(userMap.containsKey(userId)){
			//从redis里面取出消息
			tempList = messageMap.get(userId);
			List<MessageVO> messageList  =  new ArrayList<MessageVO>();
			messageMap.put(userId, messageList);
			System.err.println("用户"+userId+"get Message 后的 messageMap"+messageMap.toString());
			return tempList;
		}
		return tempList;
	}
	@Override
	public String getUserIp(String userId){
		if(userMap.containsKey(userId)){
			return userMap.get(userId).getIp();
		}
		return "";
	}
}
