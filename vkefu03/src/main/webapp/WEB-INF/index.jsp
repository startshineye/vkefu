<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./plugin/layui/css/layui.css" media="all">
	<script type="text/javascript" src="./plugin/layui/layui.js"></script> 
	<script type="text/javascript" src="./plugin/layui/lay/modules/layim.js"></script>
  </head>
  <body>
     
  </body>
  <script type="text/javascript">
 layui.use('layim', function(layim){
  //先来个客服模式压压精
	 //基础配置
	  layim.config({
	 
	    init: {
	    	url: 'json/getList.json',
	    	data: {}
	    } //获取主面板列表信息，下文会做进一步介绍
	 
	    //获取群员接口（返回的数据格式见下文）
	    ,members: {
	      url: 'json/getMembers.json' //接口地址（返回的数据格式见下文）
	      ,type: 'get' //默认get，一般可不填
	      ,data: {} //额外参数
	    }
	    
	    //上传图片接口（返回的数据格式见下文），若不开启图片上传，剔除该项即可
	    ,uploadImage: {
	      url: '' //接口地址
	      ,type: 'post' //默认post
	    } 
	    
	    //上传文件接口（返回的数据格式见下文），若不开启文件上传，剔除该项即可
	    ,uploadFile: {
	      url: '' //接口地址
	      ,type: 'post' //默认post
	    }
	    //扩展工具栏，下文会做进一步介绍（如果无需扩展，剔除该项即可）
	    ,tool: [{
	      alias: 'code' //工具别名
	      ,title: '代码' //工具名称
	      ,icon: '&#xe64e;' //工具图标，参考图标文档
	    }]
	    
	    ,msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
	    ,find: layui.cache.dir + 'css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
	    ,chatLog: layui.cache.dir + 'css/modules/layim/html/chatLog.html' //聊天记录页面地址，若不开启，剔除该项即可
	  });
});
  </script>
</html>
