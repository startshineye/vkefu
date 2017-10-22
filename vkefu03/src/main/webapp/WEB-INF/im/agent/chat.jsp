<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
 <meta charset="UTF-8">
    <title>在线客服平台</title>
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
	<link rel="stylesheet" type="text/css" href="plugin/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/common/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_bmgv5kod196q1tt9.css">
	
	<!-- im -->
	<link rel="stylesheet" type="text/css" href="css/im/entim.css">
	<link rel="stylesheet" type="text/css" href="plugin/kindeditor/themes/simple/simple.css">
	<link rel="stylesheet" type="text/css" href="css/backstage.css" media="all">
	
	<!-- 	加载js文件 -->
	<script type="text/javascript" src="plugin/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="plugin/layui/layui.js"></script> 
	<script type="text/javascript" src="js/im.js"></script>
	<script type="text/javascript" src="plugin/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="plugin/kindeditor/lang/zh-CN.js"></script>
	<script type="text/javascript" src="js/socket.io.js"></script>
</head>
<body>
<div id="containter" class="clearfix" style="margin-right:200px;">
	<div class="content-left">
		<div class="chat-above" id="above">
			<div class="clearfix message connect-message"> 
				<span id="connect-message" style="background-color:#FFFFFF;color:#4665d4;">
					<a href="javascript:void(0)" style="color:#4665d4;">
						<i class="layui-icon">&#xe63a;</i>
						查看更多消息
					</a>
				</span>
			</div>
					<div class="clearfix chat-block">
					<!-- 坐席部分 -->
						<div class="chat-right"> 
							<img class="user-img" src="/im/img/user.png" alt="">
							<div class="chat-message">
								<label class="time">2017-10-21 19:18:13</label>
								<label class="user">坐席1101</label> 
							</div>
							<div class="chatting-right">
								<i class="arrow"></i>
								<div class="chat-content">请问,有什么为你服务?</div>
							</div>
						</div>
					</div>
					<div class="clearfix chat-block">
					<!-- 用户部分 -->
						<div class="chat-left"> 
							<img class="user-img" src="/im/img/user.png" alt="">
							<div class="chat-message">
								<label class="user">访客1</label> 						
								<label class="time">2017-10-21 19:18:20</label>
							</div>
							<div class="chatting-left">
								<i class="arrow"></i>
								<div class="chat-content">系统怎么用?</div>
							</div>
						</div>
					</div>
		</div>   
		<div class="chat-bottom" id="bottom">
			<textarea id="message" name="content" style="visibility:hidden;"></textarea>
			<div class="btn-push clearfix">
				<div style="float:left;height:24px;line-height:34px;margin: 5px 20px 10px 5px;" id="surplus">0/200</div>
				<button type="button" class="send-btn active special  clearfix" id="sent" onclick="sendMessage()" style="background-color:#32c24d;font-weight: 200;padding:5px;">
					发送
				</button>
			</div>
		</div> 
	</div>
	<div class="content-rig">
		<div class="content-head">
			<p>用户信息</p>
		</div>
		<div class="content-list">
			<ul>
				<li>
					<p>姓名：张三</p>
				</li>
				<li>
					<p>部门：呼叫中心</p>
				</li>
				<li>
					<p>邮件：yxm@qq.com</p>
				</li>
				<li>
					<p>电话：13001955858</p>
				</li>
			</ul>
		</div>
		<div class="content-bottom">
			<span id="welcome-message">
				<p>欢迎使用优客服企业IM</p>
				<p>QQ群：<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=637134af30a27220211c843d801ada14700aca69ee8f4acf13f795fe38ea7b94"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="优客服开源项目" title="优客服开源项目"></a></p>
				<p>优客服企业IM为收费产品</p>
				<p>详情请咨询：<a href="http://www.ukewo.cn" target="_blank" style="color:#32c24d;">优客服</a></p>
			</span>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	var editor , words;
	KindEditor.ready(function (K) {
		editor = K.create('textarea[name="content"]', {
			autoHeightMode: false,
			width: "100%",
			resizeType: 0,
			themeType: 'simple',
			fontsize: 14,
			newlineTag : "br" , 
			filterMode:true,
			items: ['emoticons', 'cut'],
			afterChange : function() {
				var count = this.count() ;
				
				words = this.count("text") ;
				var pattern = '字数：' + words + '字'; 
				document.getElementById('surplus').innerHTML = "字符： "+count+"  ，文字：" + pattern; //输入显示
				 ////////
			},
			afterCreate : function() { //设置编辑器创建后执行的回调函数
	            var self = this;
	            //Ctrl+Enter提交表单
	            K.ctrl(document, 13, function() {
	                self.sync();
	                sendMessage();
	            });
	            K.ctrl(self.edit.doc, 13, function() {
	                self.sync();
	                sendMessage();
	            });
	        } 
		});
	});
	KindEditor.options.cssData = "body { font-size: 15px; font-family:'Microsoft Yahei', 'Helvetica', 'Simsun', 'Arial';}";
	var R3Ajax = {
		ajax:function(opt){
			var xhr = this.createXhrObject();
			xhr.onreadystatechange = function(){
				if(xhr.readyState!=4) return ;
				(xhr.status===200 ?
					opt.success(xhr.responseText,xhr.responseXML):
					opt.error(xhr.responseText,xhr.status));
			}
			xhr.open(opt.type,opt.url,true);
			if(opt.type!=='post') 
				opt.data=null;
			else
				xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			opt.data = this.parseQuery(opt.data);
			xhr.send(opt.data);
		},
		post:function(url,success,data){
			var popt = {
				url:url,
				type:'post',
				data:data,
				success:success,
				error:function(data){}
			}
			this.ajax(popt);
		},
		get:function(url,success){
			var gopt = {
				url:url,
				type:'get',
				success:success,
				error:function(){}
			}
			this.ajax(gopt);
		},
		createXhrObject:function(){
			var methods = [
				function(){ return new XMLHttpRequest();},
				function(){ return new ActiveXObject('Msxml2.XMLHTTP');},
				function(){ return new ActiveXObject('Microsoft.XMLHTTP');}
			];
			for(var i=0;len=methods.length,i<len;i++){
				try{
					methods[i]();
				}catch(e){
					continue;
				}
				this.createXhrObject = methods[i];
				return methods[i]();
			}
			throw new Error('Could not create an XHR object.');
		},
		parseQuery:function(json){
			if(typeof json == 'object'){
				var str = '';
				for(var i in json){
					str += "&"+i+"="+encodeURIComponent(json[i]);
				}
				return str.length==0 ? str : str.substring(1);
			}else{
				return json;
			}
		}
	};
	Date.prototype.format = function(fmt) { 
		 var o = { 
			"M+" : this.getMonth()+1,                 //月份 
			"d+" : this.getDate(),                    //日 
			"h+" : this.getHours(),                   //小时 
			"m+" : this.getMinutes(),                 //分 
			"s+" : this.getSeconds(),                 //秒 
			"q+" : Math.floor((this.getMonth()+3)/3), //季度 
			"S"  : this.getMilliseconds()             //毫秒 
		}; 
		if(/(y+)/.test(fmt)) {
				fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		}
		 for(var k in o) {
			if(new RegExp("("+ k +")").test(fmt)){
				 fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
			 }
		 }
		return fmt; 
	} 
	var R3Helper = {
		resize : function(){
			var height = document.body.offsetHeight ;
			document.getElementById('above').style.height = (height - 194 - 50)+"px" ;
		}
	}
</script>
<script>
var agentId = "${agentId}";
var agentName = "${agentName}";
var sessionId ="${sessionId}";
	layui.use('element', function(){
	  var $ = layui.jquery
	  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
	});
	var hostname = location.hostname ;
	var socket = io.connect('http://localhost:8078/im/agent?agentId='+agentId+'&agentName='+agentName+'&sessionId='+sessionId+'');
	socket.on('message', function(data) {
		var chat=document.getElementsByClassName('chatting-left').innerText;
		chat = data.message;
		if(data.calltype == "out"){
			output('<div class="chat-right"> <img class="user-img" src="/im/img/user.png" alt=""><div class="chat-message"><label class="time">'+data.createtime+'</label><label  class="user">'+data.agentid+'</label></div><div class="chatting-right"><i class="arrow"></i><div class="chat-content">'+data.message+'</div></div>' , "chat-block");
		}else if(data.calltype == "in"){
			//output('<div class="chat-left"> <img class="user-img" src="/im/img/user.png" alt=""><div class="chat-message"><label  class="user">${entimuser.uname}</label><label class="time">'+data.createtime+'</label> </div><div class="chatting-left"><i class="arrow"></i><div class="chat-content">'+chat+'</div></div>' , "chat-block");	
		}    
	});
	socket.on('status',function(data){
		if(data.contextid == '${entimuser.id}'){
			if(data.message == 'online'){
				top.$('#chat_${entimuser.id}').removeClass('offline').removeClass('online').addClass('online').attr('title' , '在线');
			}else if(data.message == 'offline'){
				top.$('#chat_${entimuser.id}').removeClass('online').removeClass('offline').addClass('offline').attr('title' , '离线');
			}
		}
	})
	function sendMessage() {
		editor.sync();
		var count = editor.count("text");
		if(count>0){
		var message = document.getElementById('message').value;
			if(message!= ""){ 
				socket.emit('message', {
					agentid:agentId,
					type:"message" ,
					sessionid:sessionId,
					userid:"",
					message : message
				});
			}
			editor.html('');
		}
	}
	function output(message , clazz) {
		if(clazz == "message connect-message"){
			var messages = document.getElementsByClassName("connect-message") ;
			for(inx =0 ; inx < messages.length ; ){
				document.getElementById('above').removeChild(messages[inx]) ;
				inx++ ;
			}			
		}
		var element = ("<div class='clearfix "+clazz+"'>" +" " + message + "</div>");
		document.getElementById('above').innerHTML= (document.getElementById('above').innerHTML + element);
		document.getElementById('above').scrollTop = document.getElementById('above').scrollHeight  ;
	}
	document.getElementById('above').scrollTop = document.getElementById('above').scrollHeight  ;
	/* <#if online?? && online == true>
	top.$('#chat_${entimuser.id}').removeClass('offline').addClass('online').attr('title' , '在线');
	</#if> */
</script>
</html>