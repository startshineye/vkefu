package com.yxm.web.service;
import com.yxm.web.entity.user.User;
public interface OnlineUserService {
    void save(User user);
    void updateUserByUserId(User user);
    User getByUserId(String userId);
}
