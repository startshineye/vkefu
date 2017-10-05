package com.yxm.core;
import org.springframework.context.ApplicationContext;
public class DataContext{
	//聊天建立原因
	public static final Integer CHAT_START_CAUSER_USER=0;//用户发起
    public static final Integer CHAT_START_CAUSER_TRANSFER=1;//转移建立
    public static final Integer CHAT_START_CAUSER_GROUPCHAT=2;//群聊
    
    //聊天结束原因
    public static final Integer CHAT_END_CAUSE_AGENT=0;//坐席正常结束
    public static final Integer CHAT_END_CAUSE_AGENTOFFLINE=1;//坐席离线
    public static final Integer CHAT_END_CAUSE_USERTIMEOUT=2;//用户超时
    public static final Integer CHAT_END_CAUSE_NETBROKEN=3;//断网
    public static final Integer CHAT_END_CAUSE_AGENTERROROPT=4;//坐席错误操作
    public static final Integer CHAT_END_CAUSE_USER=5;//用户结束
    public static final Integer CHAT_END_CAUSE_AGENTTRANSFER=6;//坐席转移
    public static final Integer CHAT_END_CAUSE_EXCEPTION=7;//意外结束
	
	//坐席操作相关
	public static final String AGENT_OPT_TYPE = "agentopt";//坐席操作
	public static final String AGENT_LOGIN_STATUS_ON="on";//坐席登录在线
	public static final String AGENT_LOGIN_STATUS_OFF="off";//坐席注销
	
	
	//聊天渠道来源
	public static final Integer SOURCE_WECHAT=1;//微信
	public static final Integer SOURCE_WEBCC=2;//web
	public static final Integer SOURCE_VIDEO=3;//视频
	
	//用户消息来源
	public static final String USER_SOURCE_WEBCC="webcc";//此来源对应于用户的技能组
	public static final String USER_SOURCE_WECHAT="wechat";//来源微信
	
	
	//聊天状态
	public static final Integer CHAT_STATUS_WAITING=0;//等待
	public static final Integer CHAT_STATUS_CHATTING=1;//聊天中
	public static final Integer CHAT_STATUS_END=2;//结束
	
	//聊天消息来源
	public static final Integer CHAT_MESSAGEFROM_USER=1;//用户->坐席
	public static final Integer CHAT_MESSAGEFROM_AGENT=2;//坐席->用户
	
	//聊天消息类型
	public static final Integer CHAT_MESSAGETYPE_TEXT=1;//文本类型
	public static final Integer CHAT_MESSAGETYPE_IMG=2;//图片
	public static final Integer CHAT_MESSAGETYPE_VOICE=3;//语音
	
	//留言状态
	public static final Integer LEAVEMESSAGE_STATUS_UNHANDLE=0;//未处理
	public static final Integer LEAVEMESSAGE_STATUS_HANDLING=1;//处理中
	public static final Integer LEAVEMESSAGE_STATUS_HANDLED=2;//已处理
	
	//操作结果
	public static final Integer OPTRESULT_ERROR=0;//失败
	public static final Integer OPTRESULT_SUCCESS=1;//成功
	
	
	//聊天参数相关
	public static final String PARAM_SATISFACTION = "satisfaction";//满意度
	public static final String PARAM_WELCOME = "welcome";//欢迎词
	public static final String PARAM_MAXUSER = "maxuser";//坐席最大服务数量
	public static final String PARAM_LINK = "link";//fchat连接
	public static final String PARAM_USERQUEUETIMEOUTREMIND = "userqueuetimeoutremind";//客户排队超时提示语
	public static final String PARAM_USERQUEUEREMIND = "userqueueremind";//用户排队提示语
	public static final String PARAM_BALCKLISTREMIND = "balcklistremind";//黑名单提示语
	public static final String PARAM_USERWAITINGTIME = "userwaitingtime";//用户等待时间
	
	//角色
	public static final String ROLE_AGENT = "agent";
	public static final String ROLE_ADMIN = "admin";
	
	//坐席操作
	public static final Integer AGENT_OPTCODE_ONLINE=0;//在线
	public static final Integer AGENT_OPTCODE_BUSYLINE=1;//忙碌
	public static final Integer AGENT_OPTCODE_OFFLINE=2;//离线
	public static final Integer AGENT_OPTCODE_STARTCHAT=30;//开始聊天
	public static final Integer AGENT_OPTCODE_ENDCHAT=31;//结束聊天
	public static final Integer AGENT_OPTCODE_CONFERENCEIN=40;//加入会议 
	public static final Integer AGENT_OPTCODE_CONFERENCEOUT=41;//退出会议
	public static final Integer AGENT_OPTCODE_TRANSFERIN=50;//转入
	public static final Integer AGENT_OPTCODE_TRANSFEROUT=51;//转出
	public static final Integer AGENT_OPTCODE_TRICHATOUT=60;//群聊发起
	public static final Integer AGENT_OPTCODE_TRICHATIN=61;//群聊接入
	public static final Integer AGENT_OPTCODE_SATISFACT=7;//评价
	public static final Integer AGENT_OPTCODE_BLACKLIST=8;//黑名单
	public static final Integer AGENT_OPTCODE_LOGIN=90;//登录
	public static final Integer AGENT_OPTCODE_LOGOUT=91;//注销
	public static final Integer AGENT_OPTCODE_UNLOGOUT=100;//未注销的意外操作
	
	//用户操作
	public static final Integer USER_OPTCODE_ENQUEUE=10;//进队列
	//public static final Integer USER_OPTCODE_OUTQUEUE=11;//出队列
	public static final Integer USER_OPTCODE_CHAT=11;//聊天--出队列方式一
	public static final Integer USER_OPTCODE_LEAVEMSG=12;//留言--出队列方式二
	public static final Integer USER_OPTCODE_WAITTIMEOUT=13;//等待超时--出队列方式三
	
	public static final Integer USER_OPTCODE_ENDCHAT=21;//结束聊天
	public static final Integer USER_OPTCODE_LOGIN=30;//登录
	public static final Integer USER_OPTCODE_LOGOUT=31;//注销
	public static final Integer USER_OPTCODE_OFFLINE=4;//离线
	public static final Integer USER_OPTCODE_SATISFACT=5;//用户评价
	
	//用户聊天状态
	public static final String USER_CHATSTATUS_END="end";//用户聊天结束
	public static final String USER_CHATSTATUS_ON="on";//用户聊天进行中
	
	
	//坐席状态
	public static final String AGENT_STATUS_ONLINE="online";//坐席在线
	public static final String AGENT_STATUS_OFFLINE="offline";//坐席离线
	public static final String AGENT_STATUS_BUSYLINE="busyline";//坐席忙碌
	
	//坐席默认企业号
	public static final Integer AGENT_ENTID_DEFAULT=-1;//坐席默认企业号
	public static final String AGENT_QUEUEID_DEFAULT="";//坐席默认排队id
	public static final String AGENT_REMARK_DEFAULT="";//坐席默认评论
	public static final Integer AGENT_THREADID_DEFAULT=-1;//坐席默认聊天id
	public static final String AGENT_TOAGENTID_DEFAULT="";//默认toAgentId
	public static final String AGENT_TOUSERID_DEFAULT="";//默认toUserId
	public static final String AGENT_USERID_DEFAULT="";//默认userId
	public static final String AGENT_USERNAME_DEFAULT="";//默认userName
	
	//聊天类型
	public static final Integer CHATTYPE_CHAT=0;//无聊天
	public static final Integer CHATTYPE_SINGLE=1;//单聊天
	public static final Integer CHATTYPE_TRANSFER=2;//转移
	public static final Integer CHATTYPE_GROUPCHAT=3;//群聊三方
	public static final Integer CHATTYPE_CONFRENCE=4;//会议
	
	//webcc通知CTI
	//public static final String WEBCC_
	private static ApplicationContext applicationContext ;
	
	public static ApplicationContext getContext(){
		return applicationContext ;
	}
	public static void setApplicationContext(ApplicationContext context){
		applicationContext = context ;
	}
	
}

