package com.yxm.util.factory;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.lang.StringUtils;
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
	public static Queue<Object> webccUserQueue = new LinkedList<Object>();
	//等待用户队列
	public static Queue<Object> wechatUserQueue = new LinkedList<Object>();
	//微博用户队列
	public static Queue<Object> weiboUserQueue = new LinkedList<Object>();
	//系统坐席可服务总人数
	public static final Integer SEVICE_MAX = 5;
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
	                      freeAgentQueue.remove(temp);
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
    * 获取一个webcc用户
    * @param message
    * @param queue
    * @return Queue
    */
   public static synchronized User getWebccUserQueue(){
	   //等待用户队列
	   User user = (User)webccUserQueue.poll();
	   System.out.println("*********出用户为:********"+user.toString());
	   return user;
   } 
}













