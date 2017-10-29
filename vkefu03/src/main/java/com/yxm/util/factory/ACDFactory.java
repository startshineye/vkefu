package com.yxm.util.factory;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.yxm.util.manager.agent.AgentManager;
import com.yxm.util.manager.agent.IAgentManager;
import com.yxm.util.manager.user.IUserManager;
import com.yxm.util.manager.user.UserManager;
import com.yxm.web.entity.agent.Agent;
import com.yxm.web.entity.user.User;
/**
 * web端坐席工具类
 * @author yxm
 * @date 2016-08-29
 */
public class ACDFactory {
	//空闲坐席队列
	public static Queue<Agent> freeAgentQueue = new LinkedList<Agent>();
	//等待用户队列
	public static LinkedList<Object> webccUserQueue = new LinkedList<Object>();
	//等待用户队列
	public static Queue<Object> wechatUserQueue = new LinkedList<Object>();
	//微博用户队列
	public static Queue<Object> weiboUserQueue = new LinkedList<Object>();
	
	//public static  ConcurrentHashMap<String,List<ParamVO>> param_maxuser_allagent_map = new ConcurrentHashMap<String,List<ParamVO>>();
	//public static  ConcurrentHashMap<String,ParamVO> param_maxuser_admin = new ConcurrentHashMap<String,ParamVO>();
	//系统坐席可服务总人数
	public static final Integer SEVICE_MAX = 5;
	
    public static IAgentManager createAgentManager(){
    	return AgentManager.getInstance();
    }
    public static IUserManager createUserManager(){
    	 return UserManager.getInstance();
    }
   /**
    * 为用户分配坐席
    * @param group
    * @param queue
    * @return Object
    * */
   public static synchronized Agent allotAgent(String group){
	    /**
		 * 查询条件，当前在线的 坐席，并且 未达到最大 服务人数的坐席
		 */
	   Agent temp=null;
	   //1.空闲队列大小为0，则返回空
	   if(freeAgentQueue.isEmpty()){
		   return temp;
	   }else{
		 //2.遍历list，取出第一个符合条件的元素，然后移除
		   for (Agent agent:freeAgentQueue) {
			   if(agent!=null){
				   //查询含有group的对象
				   if(agent.getGroups().contains(group)){
			    	   //获取对象
				       temp = agent;
			    	  //先踢出
                       freeAgentQueue.remove(temp);
                       //然后放到最后面
                       freeAgentQueue.add(temp);
                       break;
				   } 
			   }
		    }
		   return temp;
	   }
   }
    /**
     * 根据agentId移除坐席
     * @param agentId
     */
   public static synchronized void removeAgent(String agentId){
	   //1.空闲队列大小为0，则返回空
	   if(freeAgentQueue.size()==0){
		   return;
	   }else{
		 //2.遍历list，取出第一个符合条件的元素，然后移除
		   for (Object obj:freeAgentQueue) {
			   System.err.println("obj="+obj);
			   Agent ob = (Agent)obj;
			   System.out.println("agentId:"+ob.getAgentId());
			   System.out.println(agentId.equals(ob.getAgentId()));
			   //查看agentId对应的坐席是否存在
			   if(agentId.equals(ob.getAgentId())){
				   freeAgentQueue.remove(obj);
				   break;
			   }
		    }
	   }
	   System.out.println("空闲坐席队列:"+freeAgentQueue);
	   System.out.println("在线用户队列:"+webccUserQueue);
   }
   /**
    * 生成一个空闲坐席队列(将坐席加入到队列)
    * @param message
    * @param queue
    * @return Queue
    */
   public static synchronized void buildFreeAgentQueue(Agent agent){
	   boolean flag = true;//用于判断坐席是否存在空闲队列中
	   //防止加入null
	   if(agent!=null){
		   //1.如果坐席已经存在就不再加入
		   if(!freeAgentQueue.isEmpty()){
			   for (Agent temp:freeAgentQueue) {
				   if(temp.getAgentId().equals(agent.getAgentId())){
					  //遍历完队列，直到找到与插入坐席相同的，则结束
					  flag=false;
					  break;
				   } 
			   } 
			   if(flag){
				   freeAgentQueue.add(agent);
			   }
		   }else{
			   freeAgentQueue.add(agent);
		   }
	   }
	  
	   System.out.println("空闲坐席队列:"+freeAgentQueue);
   }
   /**
    * 根据agentId判断是否存在
    * @return
    */
   public static synchronized boolean containsFreeAgent(String agentId){
	   if(!"".equals(agentId)){
		   for (Object object : freeAgentQueue) {
				Agent agent = (Agent)object;
				if(agentId.equals(agent.getAgentId())){
					return true;
				}
			   }
	   }
	   return false;
   }
   /**
    * 生成一个webcc用户队列
    * @param message
    * @param queue
    * @return Queue
    */
   public static synchronized void  addWebccUserQueue(Object object){
	   Boolean isExist = false;
	   if(object!=null){
		   User inUser = (User)object;
		   String userId = inUser.getUserId();
		   if(!StringUtils.isBlank(userId)){
			   /**
			    *防止连接中断后多次进入排队
			    */
			   for (Object obj : webccUserQueue) {
					User user = (User)obj;
					if(userId.equals(user.getUserId())){
						isExist=true;
						break;
					}
				}
			   if(!isExist){
				   webccUserQueue.add(object);
			   }
		   }
	   }
   }
   /**
    * 获取第一个webcc用户,但是不移除
    * @param message
    * @param queue
    * @return Queue
    */
   public static synchronized User getFirstWebccUserQueue(){
	   //等待用户队列
	   User user = null;
	   if(!webccUserQueue.isEmpty()){
		   user = (User)webccUserQueue.getFirst();
	   }
	   //User user = (User)webccUserQueue.getFirst();
	   return user;
   }
   
   /**
    * 为了使线程安全,则需要不管是弹出特定元素还是弹出第一个,都用一个方法
    * @param userId
    * @param type true 说明是从根据用户id查询用户并移除(用于留言时候的移除) 否则正常分配坐席弹出第一个元素
    * @return
    */
   public static synchronized User pollOrPollSpecialUser(String userId,boolean type){
	   int index=0;
	   boolean isExist = false;
	   User user=null;
	   if(webccUserQueue.size()>0){
		   if(type){//根据userid分配坐席
			   //首先判断webccUserQueue是否有值
				   if(!StringUtils.isBlank(userId)){
					   //循环查找
					   for (Object  userqueue: webccUserQueue) {
						  User userinqueue = (User)userqueue;
						  if(userId.equals(userinqueue.getUserId())){
							 isExist=true;
							 break;
						  }else{
							  index++;
						  }
					   }
					  //条件取出
					  if(isExist){
						  user = (User)webccUserQueue.remove(index);
					  }
				   }
		   }else{//弹出第一个元素
			   user = (User)webccUserQueue.poll();
		   }
	   }
	   return user;
   }
}
