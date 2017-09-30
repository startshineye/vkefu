package com.yxm.util.cache;

import java.util.List;

import com.corundumstudio.socketio.SocketIOClient;

public interface BaseClient {
  //获取
  public List<SocketIOClient> getClients(String key);
  //存放
  public void putClient(String key,SocketIOClient client);
  //移除
  public void removeClient(String key,String id);
}
