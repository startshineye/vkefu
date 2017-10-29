package com.yxm.util.manager.user;
import java.util.List;

import com.yxm.web.domain.MessageVO;
import com.yxm.web.entity.user.User;
public interface IUserManager {
    boolean addUser(User user);
    boolean removeUser(User user);
    User getUser(String userId);
    boolean contains(String userId);
	boolean putMessage(String userId, MessageVO message);
	List<MessageVO> getMessage(String userId);
	boolean removeUser(String userId);
	String getUserIp(String userId);
}
