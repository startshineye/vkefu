package com.yxm.web.dao;

import com.yxm.web.entity.user.User;

public interface OnlineUserDao {
   void insert(User user);
   void update(User user);
   User getByUserId(String userId);
}
