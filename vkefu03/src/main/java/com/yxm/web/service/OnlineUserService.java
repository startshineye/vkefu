package com.founder.focuss.webcc.service;
import com.founder.focuss.webcc.entity.user.User;
public interface OnlineUserService {
    void save(User user);
    void updateUserByUserId(User user);
    User getByUserId(String userId);
}
