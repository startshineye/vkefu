package com.founder.focuss.webcc.controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.jl.player.Player;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import sun.misc.BASE64Decoder;

import com.founder.focuss.core.DataContext;
import com.founder.focuss.utils.DateUtil;
import com.founder.focuss.utils.factory.ACDFactory;
import com.founder.focuss.webcc.domain.AgentUserVO;
import com.founder.focuss.webcc.domain.MessageVO;
import com.founder.focuss.webcc.domain.ThreadVO;
import com.founder.focuss.webcc.entity.agent.Agent;
import com.founder.focuss.webcc.service.AgentUserService;
import com.founder.focuss.webcc.service.MessageService;
import com.founder.focuss.webcc.service.OnlineAgentService;
import com.founder.focuss.webcc.service.ThreadService;
import com.founder.focuss.webcc.service.WebSocketService;
import com.founder.focuss.webcc.websocket.SystemWebSocketHandler;
/**
 * 文件上传类
 * 
 * @author yxm
 * @date 2016-09-20
 */
@Controller
@RequestMapping("/file")
public class FileController {
	private String puserid;
	private String puseridfromagent;
	private String pwechatuserIdFromAgent;
	@Autowired
	private ThreadService threadService;
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private OnlineAgentService onlineAgentService;
	
	@Autowired
	private AgentUserService agentUserService;
	@Autowired
	private WebSocketService webSocketService;
	@Autowired
	private SystemWebSocketHandler webSocketHandler;
	private static final Logger logger;
	private static final Map<String, String> TypeMap = new HashMap<String, String>();
	// 设置允许上传呢的类型
	static {
		logger = Logger.getLogger(FileController.class);
		TypeMap.put("image", "gif,jpg,jpeg,png,bmp");
		TypeMap.put("file",
				"doc,docx,xls,xlsx,ppt,pptx,txt,pdf,gif,jpg,jpeg,png,bmp");
	}
	// 设置文件大小 为3M
	public static long fileSize = 100 * 1024 * 1024;
    
	@RequestMapping("prepareScreen")
	@ResponseBody
	public Object prepareScreen(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> jsonMap = new HashMap<String,String>();
		try {
			puserid = request.getParameter("userId");
			String agentId = request.getParameter("agentId");
			puseridfromagent = request.getParameter("userIdFromAgent");
			pwechatuserIdFromAgent = request
					.getParameter("wechatuserIdFromAgent");
			System.out.println("puserid"+puserid+"puseridfromagent"+puseridfromagent+"pwechatuserIdFromAgent"+pwechatuserIdFromAgent+"agentId"+agentId);
			jsonMap.put("success","true");
		} catch (Exception e) {
		   e.printStackTrace();
		  jsonMap.put("success", "fail");
		}
		return jsonMap;
		}
	/**
	 * @param file
	 * @param filePre
	 * @param request
	 * @param response
	 * @return message: -2没有坐席-1 没有文件上传 0 上传成功 1 上传失败 2 文件超过上传大小 3 文件格式错误 4 上传文件路径非法 5
	 *         上传目录没有写权限
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("fileUpload")
	@ResponseBody
	public Object fileUpload(MultipartHttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		//文件上传之前需要判断坐席是否在线
		String agentId=request.getParameter("agentId");
		//Agent agent = (Agent)ACDUtil.agentInfoMap.get(agentId);
		Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
		if(agent==null){
			agent=onlineAgentService.getByAgentId(agentId);
			ACDFactory.createAgentManager().addAgent(agent);
		}
		if(agent!=null){//agent为null说明坐席已经离开
			String status = agent.getStatus();
			if("offline".equals(status)){//说明没有坐席
				jsonMap.put("fileUpload",-2);
				return jsonMap;
			}else{
				//在线时候的处理
				 try {
					  /* String basePath = request.getScheme() + "://"
								+ request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath() + "/";*/
						Integer source = 0;
						Iterator<String> it = request.getFileNames();
						String key = (String) it.next();  
						MultipartFile file = request.getFile(key);
						System.out.println("执行文件上传");
						String userid = request.getParameter("userId");
						String sessionId = request.getParameter("sessionId");
						String useridfromagent = request.getParameter("userIdFromAgent");
						if(useridfromagent != null){
							source = Integer.parseInt(request.getParameter("source"));
						}
					 
						String wechatuserIdFromAgent = request
								.getParameter("wechatuserIdFromAgent");
						System.out.println("---id---" + userid + "---useridfromagent---"
								+ useridfromagent + "---wechatuserIdFromAgent---"
								+ wechatuserIdFromAgent+"----agentId-------"+agentId);
						logger.info("file name is:" + file.getOriginalFilename());
						String oldName = file.getOriginalFilename();
						
						System.out.println("--oldName--"+oldName);
						String sub_savePath = request.getSession().getServletContext()
								.getRealPath("/")
								+ "file/";
						
						//-----根据日期创建文件目录----------start
						//1.获取当前时间
						Date date = new Date();
						//2.格式化转为字符串
						String format = DateUtil.ymd_sdf.format(date);
						//3.整体文件路径
						String savePath=sub_savePath+format;
						//-----根据日期创建文件目录----------end
						
						System.out.println("文件保存地址：" + savePath);
						
						System.out.println("文件大小"+file.getSize());
						// 文件不为空时
						if (!file.isEmpty()) {
							System.out.println("文件大小为：" + file.getSize());
							// 当文件超过设置大小，则不运行文件
							if (file.getSize() > fileSize) {
								jsonMap.put("fileUpload",2);
								return jsonMap;
							}
							// 获取文件名后缀
							String originalFilename = file.getOriginalFilename();
							System.out.println("文件名：" + originalFilename);
							String fileSuffix = originalFilename.substring(
									originalFilename.lastIndexOf(".") + 1).toLowerCase();
							System.out.println("文件后缀名：" + fileSuffix);
							System.out.println(TypeMap.get("file").split(","));
							System.out.println(Arrays.asList(TypeMap.get("file").split(",")));
							System.out.println(Arrays.asList(TypeMap.get("file").split(","))
									.contains(fileSuffix));
							// 判断该类型的文件是否在允许上传的文件类型内
							if (!Arrays.asList(TypeMap.get("file").split(",")).contains(
									fileSuffix)) {
								jsonMap.put("fileUpload",3);// 3 文件格式错误
								return jsonMap;
							}
							if (!ServletFileUpload.isMultipartContent(request)) {
								jsonMap.put("fileUpload", false);
								return jsonMap;
							}
							// 检查上传文件的目录
							File uploadDir = new File(savePath);
							
							if(!uploadDir.exists()){
								uploadDir.mkdirs();
							}
							// 是否有上传的权限
							if (!uploadDir.canWrite()) {
								jsonMap.put("fileUpload",5);//是否有文件权限
								return jsonMap;
							}
							// 保存文件（保证上传文件名不重复）
							// 获取随机名称
							String longTime = date.getTime() + "";
							// 获取文件全名
							String fullName = longTime + "." + fileSuffix;
							try {
								// 创建文件
								File saveFile = new File(savePath, fullName);
								// 保存文件
								file.transferTo(saveFile);
								System.err.println("保存文件成功");
								// FileTranser.saveFielByFileName(file, uploadPath, newname);
								String fileMsg = fullName + "#" + oldName + "#" + fileSuffix;
								// 文件保存成功后在前台显示，通过WebsocketSession
								System.out.println("--图片的fileMsg--" + fileMsg);
								if (userid != null) {
									System.out.println("---userid----");
									sendfileToUser(userid, 1, fileMsg, "webcc",agentId,sessionId,"");
								} else if (useridfromagent != null) {
									System.out.println("---useridfromagent----");
									if(source==2){//webcc端
										sendfileToUser(useridfromagent, 2, fileMsg, "webcc",agentId,sessionId,"");
									}else if(source==3){//微博
										String imgUrl = "http://focuss.tunnel.qydev.com/focuss"+"/file/"+format+"/"+fullName;
										System.out.println("图片URL为:"+imgUrl);
										sendfileToUser(useridfromagent, 2, fileMsg, "weibo",agentId,sessionId,imgUrl);
									} 
								}  
								jsonMap.put("fileUpload",0);//文件上传成功
								return jsonMap;
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
								System.out.println("文件保存失败");
								jsonMap.put("fileUpload",1);//文件上传失败
								return jsonMap;
							}
						} else {
							//没有文件上传 -1
							jsonMap.put("fileUpload",-1);
							return jsonMap;
						}
				} catch (Exception e) {
					jsonMap.put("fileUpload",1);//文件上传失败
					return jsonMap;
				}
			}
		}else{
			jsonMap.put("fileUpload",1);//文件上传失败
			return jsonMap;
		}
	}
	/**
	 * 文件下载
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("fileDownload")
	public void fileDownload(HttpServletRequest request,
			HttpServletResponse response) {
		String filename = request.getParameter("filename");
		// 获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
		String path = request.getServletContext().getRealPath("/");

		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		// 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ filename);
		ServletOutputStream out;
		// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
		System.out.println("文件位置" + path + "file/" + filename);
		
		//格式化转为字符串
		String format = DateUtil.ymd_sdf.format(new Date());
				 
				 
		String filePath = path+"file/"+format+"/"+filename;
		System.out.println("文件位置" + filePath);
		
		File file = new File(filePath);

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();
			// 4.读取文件
			int b = 0;
			byte[] buffer = new byte[1024];
			while (b != -1) {
				b = fileInputStream.read(buffer);
				System.out.println(b);
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			fileInputStream.close();
			out.close();
			out.flush();
		} catch (Exception e) {
			logger.info("******fileDownload error********");
			e.printStackTrace();
		}
	}
	/**
	 * 把文件消息发送给客户和坐席
	 * @param id
	 * @param type
	 * （type=1:表示文件是从客户到坐席 type=2:表示文件是从坐席到客户）
	 * @param fileName
	 */
	private void sendfileToUser(String userId, int type, String fileName,
			String chatType,String agentId,String sessionId,String imgUrl) {
		try {
			 int num=0;//定义一个变量控制发送信息到用户只有一次
			 List<AgentUserVO> agentUserList = new ArrayList<AgentUserVO>();
             /**
              * 根据id找到此聊天表对象
              */
			 AgentUserVO agentUser = agentUserService.getAgentUserByUserIdAndAgentId(userId, agentId);
			 if(agentUser!=null){
				 ThreadVO threadVO =  threadService.getThreadByEndStatusAndAgentUserId(agentUser.getAgentUserId());
				 if(threadVO!=null){
					 /**
						 * 发送给用户信息
						 */
						MessageVO userMessage = new MessageVO();
						userMessage.setFromId(userId);
						userMessage.setFromName(agentUser.getUserName());
						userMessage.setMessageFrom(1);// 1:u-->a 2:a-->u
						if (type == 2) {//文件从坐席到客户
							userMessage.setFromId(agentId);
							userMessage.setFromName(agentUser.getAgentName());
							userMessage.setMessageFrom(2);// 1:u-->a 2:a-->u
							userMessage.setShow("坐席"+agentId);
						}else{//文件从用户到坐席
							userMessage.setFromId(userId);
							userMessage.setFromName(agentUser.getUserName());
							userMessage.setMessageFrom(1);// 1:u-->a 2:a-->u
							userMessage.setShow(agentUser.getUserName());
						}
						userMessage.setOwnerId(userId);
						userMessage.setOwnerName(agentUser.getUserName());
						userMessage.setMessage(fileName);
						userMessage.setMessageTime(DateUtil.datetimeFormat.format(new Date()));
						userMessage.setMessageType(2);// 1-文本 2-文件类型 3-语音
						userMessage.setSessionId(agentUser.getSessionId());
						userMessage.setSource(agentUser.getSource());
						userMessage.setThreadId(threadVO.getThreadId());
						userMessage.setStatus(1);//默认条件下变成已读信息    0-未读  1-已读
						messageService.save(userMessage);
						
			            Integer messageId = userMessage.getMessageId();
			            
			            /**
			             * 推送给坐席信息
			             */
						if ("webcc".equals(chatType)) {// webcc端
							/**
							 * 获取sessionId所对应的聊天条数(threadid)
							 */
							Integer chatStatusEnd = DataContext.CHAT_STATUS_END;
							Integer chattypeChat = DataContext.CHATTYPE_CHAT;
							agentUserList = agentUserService.getAgentUserListByEndStatusAndChatTypeAndUserId(chatStatusEnd, chattypeChat, userId);
						 
							for (AgentUserVO agentUserVO : agentUserList) {
								ThreadVO thread = threadService.getThreadByEndStatusAndAgentUserId(agentUserVO.getAgentUserId());
								
								List<WebSocketSession> agentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(thread.getAgentId());
								List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
								if(!webSocketService.isChatEnd(userId, agentId, Integer.parseInt(sessionId), agentSession)){
								// 组装消息
								MessageVO agentMessage = new MessageVO();
								agentMessage.setMessage(fileName);
								if (type == 1){
									agentMessage.setFromId(userId);// 发给本坐席
									agentMessage.setFromName(agentUserVO.getUserName());
									agentMessage.setMessageFrom(1);// 1:u-->a 2:a-->u
									agentMessage.setShow(agentUserVO.getUserName());
								} else {
									agentMessage.setFromId(agentId);// 发给除本坐席
									agentMessage.setFromName(agentId);
									agentMessage.setOwnerId(userId);
									agentMessage.setOwnerName(agentUserVO.getUserName());
									if(!agentId.equals(agentUserVO.getAgentId())){//表示发给本坐席,当做坐席
										agentMessage.setMessageFrom(1);// 1:u-->a 2:a-->u
										agentMessage.setShow("坐席"+agentId);
									}else{//表示发给其他坐席，当做客户
										agentMessage.setMessageFrom(2);// 1:u-->a 2:a-->u
										agentMessage.setShow("坐席"+agentId);
									}
								}
								agentMessage.setMessageTime(DateUtil.datetimeFormat
										.format(new Date()));
								agentMessage.setMessageType(2);//1.文本  2.文件  3.语音
								agentMessage.setSource(agentUserVO.getSource());
								agentMessage.setSessionId(agentUserVO.getSessionId());
								agentMessage.setThreadId(thread.getThreadId());
								agentMessage.setStatus(1);//
								System.out.println(".....mesageVO:......" + agentMessage);

								// 3.消息保存完毕
								if (!agentId.equals(agentUserVO.getAgentId())) {// 如果是当前坐席无需再保存信息,因为客户发送就已经保存信息
									messageService.save(agentMessage);
			                  	}else{
			                  		agentMessage.setMessageId(messageId);
								}
								/**
								 * 推送文件消息
								 */
								webSocketHandler.sendMessageToAgent(agentUserVO.getAgentId(),agentMessage);//坐席
						        //WebSocketSession stateSession = ACDFactory.createStateSocketAgentManager().getSocketSession(agentId);
								List<WebSocketSession> stateSession = ACDFactory.createStateSocketAgentManager().getSocketSessionList(agentId);
								webSocketHandler.sendTipMessage(stateSession, new TextMessage(
										userMessage.toJson()));//推送消息给坐席提示按钮闪烁
							}
							if(num==0){
						        webSocketHandler.sendMessageToUser(userId,userMessage);//用户
							    num++;	
							}  
						 }
					   }	
				 }
			 }
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 语音播放
	 */
	@RequestMapping("play")
	public void voicePlay(HttpServletRequest request) {
		String playfile = request.getParameter("playfile");
		System.out.println(playfile);
		String filePath = request.getServletContext().getRealPath("/") + "file"
				+ "/" + playfile;
		System.out.println(filePath);
		Player player = null;
		try {
			BufferedInputStream buffer = new BufferedInputStream(
					new FileInputStream(filePath));
			player = new Player(buffer);
			player.play();
			System.out.println("===========");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e);
		}
	}
	/**
	 * 截图文件保存上传
	 */
	@RequestMapping("agentCapture")
	@ResponseBody
	public Object agentCapture(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        //String fileName = java.util.UUID.randomUUID().toString();  
        String fileName = new Date().getTime() + ".jpg";
        try{  
        	/**
        	 * 获取参数
        	 */
	    	String userId = request.getParameter("userId");
            String agentId = request.getParameter("agentId");
            String img = request.getParameter("img");
            String sessionId = request.getParameter("sessionId");
        	
            /**s
             * 构造路径
             */
        	String upPath = request.getSession().getServletContext().getRealPath("/")+"file/";
			//-----根据日期创建文件目录----------start
			//1.获取当前时间
			Date date = new Date();
			//2.格式化转为字符串
			String format = DateUtil.ymd_sdf.format(date);
			//3.整体文件路径
			String currentDirPath=upPath+format;
			//-----根据日期创建文件目录----------end
        	 
            byte[] fileDat = ImageStr2Bytes(img);  
            File currentDir = new File(currentDirPath);  
            if(!currentDir.exists())  
            {  
            	currentDir.mkdirs();
            }  
            file = new File(currentDirPath+"/"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(fileDat);
            
            /**
             * 图片发送给坐席与客户
             */
	        String oldName = "截图.jpg";
	        String fileMsg = fileName + "#" + oldName + "#" + "png";
			// 文件保存成功后在前台显示，通过WebsocketSession
			sendfileToUser(userId, 2, fileMsg, "webcc",agentId,sessionId,"");
			jsonMap.put("printScreen",true);
			return jsonMap;
        }catch(Exception e){  
            e.printStackTrace();
            jsonMap.put("printScreen", false);
			return jsonMap;
        }finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
	}
	
	@RequestMapping("userCapture")
	@ResponseBody
	public Object userCapture(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        //String fileName = java.util.UUID.randomUUID().toString();  
        String fileName = new Date().getTime() + ".jpg";
        try{  
        	/**
        	 * 获取参数
        	 */
	    	String userId = request.getParameter("userId");
            String agentId = request.getParameter("agentId");
            String img = request.getParameter("img");
            String sessionId = request.getParameter("sessionId");
        	
        	/**
        	 * 构造路径
        	 */
        	String upPath = request.getSession().getServletContext().getRealPath("/")+"file/";
			//-----根据日期创建文件目录----------start
			//1.获取当前时间
			Date date = new Date();
			//2.格式化转为字符串
			String format = DateUtil.ymd_sdf.format(date);
			//3.整体文件路径
			String currentDirPath=upPath+format;
        	 
			/**
			 * 图片保存
			 */
            byte[] fileDat = ImageStr2Bytes(img);  
            File currentDir = new File(currentDirPath);  
            if(!currentDir.exists())  
            {  
            	currentDir.mkdirs();
            }  
            file = new File(currentDirPath+"/"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(fileDat);
            
            /**
             * 图片发送给坐席与客户
             */
	        String oldName = "截图.jpg";
	        String fileMsg = fileName + "#" + oldName + "#" + "png";
			// 文件保存成功后在前台显示，通过WebsocketSession
		    sendfileToUser(userId, 1, fileMsg, "webcc",agentId,sessionId,"");
			jsonMap.put("printScreen",true);
			return jsonMap;
        }catch(Exception e){  
            e.printStackTrace();
            jsonMap.put("printScreen", false);
			return jsonMap;
        }finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
	}
	/**
	 * 截图文件上传
	 * @param request
	 * @param response
	 */
	@RequestMapping("printScreen")
	@ResponseBody
	public Object savePrintScreen(HttpServletRequest request, HttpServletResponse response){
		     Map<String, Object> jsonMap = new HashMap<String,Object>();
		     String agentId = request.getParameter("agentId");
			// Agent agent = (Agent)ACDUtil.agentInfoMap.get(agentId);
		     Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
		     if(agent==null){
		    	 agent=onlineAgentService.getByAgentId(agentId);
		    	 ACDFactory.createAgentManager().addAgent(agent);
		     }
			if(agent!=null){
					String status = agent.getStatus();
					if(!"online".equals(status)){//说明没有坐席
						jsonMap.put("printScreen", "noagent");
						return jsonMap;
					}else{
					 	 try{
						   //-----------------------截图上传-----------------------------start
							String outMessage = "";
							String picdata = request.getParameter("picdata");
							String sessionId = request.getParameter("sessionId");
							
							BASE64Decoder decoder = new BASE64Decoder();
							
							byte[] picbyte = decoder.decodeBuffer(picdata);
							
							String fileName = new Date().getTime() + ".jpg";
							//request.getContextPath().
							String upPath = request.getSession().getServletContext()
									.getRealPath("/")+"file/";
							
							//-----根据日期创建文件目录----------start
							//1.获取当前时间
							Date date = new Date();
							//2.格式化转为字符串
							String format = DateUtil.ymd_sdf.format(date);
							//3.整体文件路径
							String savePath=upPath+format;
							//-----根据日期创建文件目录----------end
							
							System.out.println(savePath);
							//创建文件
							File f = new File(savePath);
							if(!f.exists()){
								f.mkdirs();
							}
							//从以下文件读取
							FileOutputStream fos = new FileOutputStream(savePath + "/" + fileName);
							System.out.println(fileName+" "+savePath);
							try {
								fos.write(picbyte);
								fos.flush();
								fos.close();
								outMessage = "{\"code\":0,\"info\":\"" + fileName + "\"}";
							} catch (IOException e) {
								e.printStackTrace();
								outMessage = "{\"code\":1,\"message\":\"" + e.toString() + "\"}";
							}
							response.setHeader("Content-type","text/html;charset=UTF-8"); //指定消息头以UTF-8码表读数据 
					        response.getOutputStream().write(outMessage.getBytes("utf-8"));
					        response.getOutputStream().flush();
					        response.getOutputStream().close();
					        //-----------------------截图上传-----------------------------end

					        //把图片发送给坐席与客户
					        String oldName = "截图.jpg";
					        String fileMsg = fileName + "#" + oldName + "#" + "png";
							// 文件保存成功后在前台显示，通过WebsocketSession
							System.out.println("--图片的fileMsg--" + fileMsg);
							if (puserid != null) {
								System.out.println("---userid----");
								sendfileToUser(puserid, 1, fileMsg, "webcc",agentId,sessionId,"");
							} else if (puseridfromagent != null) {
								System.out.println("---useridfromagent----");
								sendfileToUser(puseridfromagent, 2, fileMsg, "webcc",agentId,sessionId,"");
							}  
							jsonMap.put("printScreen",true);
							return jsonMap;
					  }catch(Exception e){
						  e.printStackTrace();
						  jsonMap.put("printScreen", false);
						  return jsonMap;
					  }
					}
				}else{
					 jsonMap.put("printScreen", false);
					  return jsonMap;
				}
	 }
	
	public static byte[] ImageStr2Bytes(String imgStr) {  
        BASE64Decoder decoder = new BASE64Decoder();  
        try {  
            byte[] b = decoder.decodeBuffer(imgStr);  
            return b;  
        } catch (Exception e) {  
  
        }  
        return null;  
    }  
	}
