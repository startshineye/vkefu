<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>在线客户</title>
	<meta name="keywords" content="LarryCMS后台登录界面" />
    <meta name="description" content="LarryCMS Version:1.09" />
	<meta name="Author" content="larry" />
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="Shortcut Icon" href="/favicon.ico" />
	
    <!-- 加载css -->
	<link rel="stylesheet" type="text/css" href="./plugin/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/login.css" media="all">
	
	<!-- 加载js -->
	<script type="text/javascript" src="./plugin/layui/layui.js"></script>
    <script type="text/javascript" src="./js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="./plugin/jParticle/jparticle.jquery.js"></script>
    <script type="text/javascript" src="./plugin/jquery/jquery.tips.js"></script>
    <!-- <script type="text/javascript" src="../common/js/larry-canvs.js"></script> -->
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div class="larry-canvas" id="canvas"></div>
<div class="layui-layout layui-layout-login" id="login_box">
	<h1>
		 <strong>在线客服</strong>
	</h1>
	<div class="layui-user-icon larry-login">
		 <input id= "loginname" type="text" placeholder="账号" class="login_txtbx"/>
	</div>
	<div class="layui-pwd-icon larry-login">
		 <input id = "password" type="password" placeholder="密码" class="login_txtbx"/>
	</div>
    <div class="layui-val-icon larry-login">
    	<div class="layui-code-box">
    		<input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx">
            <img src="images/verifyimg.png" alt="" class="verifyImg" id="verifyImg" onclick="javascript:this.src='xxx'+Math.random();">
    	</div>
    </div>
    <div class="layui-submit larry-login">
    	<input type="button" value="立即登陆" class="submit_btn"/>
    </div>
    <div class="layui-login-text">
    	<!-- <p>© 2016-2017 Larry 版权所有</p>
        <p>鄂ICP <a href="http://www.larrycms.com" title="">larrycms.com</a></p> -->
    </div>
</div>
</body>
</html>
