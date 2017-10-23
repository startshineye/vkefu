<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>在线咨询</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 
 <!-- 加载css -->
<link rel="shortcut icon" type="image/x-icon" href="/images/favicon.ico?t=1489039620156"/>
 <link rel="stylesheet" type="text/css" href="/im/css/ukefu.css">
 <link rel="stylesheet" id="skin" type="text/css" href="/im/css/default/ukefu.css">
 <!-- kindeditor -->
 <link rel="stylesheet" type="text/css" href="/im/js/kindeditor/themes/default/default.css">
   
<!-- 加载js -->
 <script type="text/javascript" src="/im/js/kindeditor/kindeditor-min.js"></script>
 <script type="text/javascript" src="/im/js/kindeditor/lang/zh-CN.js"></script>
 <script src="/im/js/socket.io.js"></script>
    <script type="text/javascript">
		var editor , words;
        KindEditor.ready(function (K) {
            editor = K.create('textarea[name="content"]', {
                autoHeightMode: false,
                width: "100%",
                resizeType: 0,
                themeType: 'simple',
                fontsize: 16,
				newlineTag : "br" , 
				uploadJson : "/im/image/upload.html?userid=${userid!''}",
                allowFileManager : false,
                allowInsertUpload:false,		//增加的参数，上传图片后是否插入到当前区域
                allowImageRemote:false,
				filterMode:true,
                items: ['emoticons', 'cut' , 'image'],
				htmlTags: {img : ['src', 'width', 'height', 'border', 'alt', 'title', 'align', '.width', '.height', '.border'] , br:[]}  ,
				afterChange : function() {
					var count = this.count() ;
					var limitNum = 300;  //设定限制字数
					var pattern = '还可以输入' + limitNum + '字'; 
					var strValue = this.html();
					if(count > limitNum) {
						pattern = ('字数超过限制，请适当删除部分内容');
						//超过字数限制自动截取			 						
						strValue = strValue.substring(0,limitNum);
						editor.html(strValue);      
					} else {
						//计算剩余字数
						var result = limitNum - this.count(); 
						pattern = '还可以输入' +  result + '字'; 
						if(result < 20){
							document.getElementById('surplus').style.color = "red" ;	
						}else{
							document.getElementById('surplus').style.color = "#000000" ;								
						}
					}
					if(this.count("text") == 0){
						strValue= "" ;	
					}
					if(words != this.count("text")){
						socket.emit('message', {
								appid : "${appid!''}",
								userid:"${userid!''}",
								type:"writing",
								session:"${sessionid!''}",
								orgi:"${orgi!''}",
								message : strValue
						});
					}
					words = this.count("text") ;
					document.getElementById('surplus').innerHTML = count+"/"+limitNum+" , " + pattern; //输入显示
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
		window.onbeforeunload = function(){
			return "您确定断开对话？" ;
		}
    </script>
    <!-- kindeditor -->
</head>
<body style="overflow:hidden;" class="ukefu-point-text">
<div class="large ukefu-im-theme <#if type?? && type='text'>ukefu-theme-border-${inviteData.consult_dialog_color!''}</#if>">
    <div id="containter" class="clearfix">
    	<div id="header" class="theme${inviteData.consult_dialog_color!''}">
	   		<img src="<#if inviteData?? && inviteData.consult_dialog_logo??>/res/image.html?id=${inviteData.consult_dialog_logo?url}<#else>/images/logo.png</#if>" style="height:30px;padding:10px;">
	   		<div class="ukefu-func-tab">
		   		<ul>
		   			<#if models?? && models["xiaoe"]?? && models["xiaoe"] == true && inviteData.ai>
		   				<li><a href="/im/index.html?appid=${appid!''}&orgi=${orgi!''}&ai=true<#if client??>&client=${client!''}</#if><#if type??>&type=text</#if><#if skill??>&skill=${skill!''}</#if><#if agent??>&agent=${agent!''}</#if>&userid=${userid!''}&sessionid=${sessionid!''}&t=${.now?long}">智能客服</a></li>
		   				<li class="cur"><a href="javascript:void(0)">人工坐席</a></li>
		   			<#else>
		   				<li class="cur"><a href="javascript:void(0)">人工坐席</a></li>
		   			</#if>
		   		</ul>
	   		</div>
	    </div>
        <div class="content-left">
            <div class="chat-above" id="above">
            	<div class="clearfix message  welcome"> <span id="welcome-message">${(inviteData.dialog_message!'欢迎您来咨询！所有客户均可以免费注册试用，有关技术支持和商业咨询可以申请加入我们官方QQ群：555834343.')?no_esc}</span></div>
				<#if chatMessageList?? && chatMessageList.content??>
					<#list chatMessageList.content?reverse as chatMessage>
						<#if chatMessage.userid?? && userid?? && chatMessage.calltype?? && chatMessage.calltype = "in">
							<div class="clearfix chat-block">
								<div class="chat-right"> 
									<img class="user-img" src="/im/img/user.png" alt="">
									<div class="chat-message">
										<label class="time">${chatMessage.createtime!''}</label>
										<label class="user">${chatMessage.username!''}</label> 
									</div>
									<div class="chatting-right">
										<i class="arrow arrow${inviteData.consult_dialog_color!''}"></i>
										<div class="chat-content theme${inviteData.consult_dialog_color!''}"><#include "/apps/im/media/message.html"></div>
									</div>
								</div>
							</div>
						<#else>
							<div class="clearfix chat-block">
								<div class="chat-left"> 
									<img class="user-img" src="<#if inviteData?? && inviteData.consult_dialog_headimg??>/res/image.html?id=${inviteData.consult_dialog_headimg?url}<#else>/images/agent.png</#if>" alt="">
									<div class="chat-message">
										<label class="user">${chatMessage.username!''}</label> 
										<label class="time">${chatMessage.createtime!''}</label>										
									</div>
									<div class="chatting-left">
										<i class="arrow"></i>
										<div class="chat-content"><#include "/apps/im/media/message.html"></div>
									</div>
								</div>
							</div>
						</#if>
					</#list>
				</#if>
			</div>
            <div class="chat-bottom" id="bottom">
				<textarea id="message" name="content" style="visibility:hidden;"></textarea>
				<div class="btn-push clearfix">
					<div style="float:left;height:34px;line-height:34px;margin: 10px 20px 10px 5px;" id="surplus">0/200</div>
					<button type="button" class="send-btn active special  clearfix" id="sent" onclick="sendMessage()">
						发送
					</button>
				</div>
			</div>
        </div>
        <div class="content-rig">
            <div class="content-list" style="padding-top:50px;">
            	<div class="content-head">
	                <p>信息提示</p>
	            </div>
                <ul>
                	<#if inviteData.dialog_name?? && inviteData.dialog_name != "">
                    <li>
                        <p>名称：${inviteData.dialog_name!''}</p>
                    </li>
                    </#if>
                    <#if inviteData.dialog_address?? && inviteData.dialog_address != "">
                    <li>
                        <p>地址：${inviteData.dialog_address!''}</p>
                    </li>
                    </#if>
                    <#if inviteData.dialog_phone?? && inviteData.dialog_phone != "">
                    <li>
                        <p>电话：${inviteData.dialog_phone!''}</p>
                    </li>
                    </#if>
                    <#if inviteData.dialog_mail?? && inviteData.dialog_mail != "">
                    <li>
                        <p>邮件：${inviteData.dialog_mail!''}</p>
                    </li>
                    </#if>
                    <#if inviteData.dialog_mail?? && inviteData.dialog_introduction != "">
                    <li>
                        <p style="text-indent:25px;line-height:25px;">${(inviteData.dialog_introduction!'')?no_esc}</p>
                    </li>
                    </#if>
                </ul>
            </div>
            <div class="content-pic" style="width:100%;height:192px;">
                <img src="/res/image.html?id=${inviteData.dialog_ad!''}" style="height:190px;width:100%;">
            </div>
        </div>
    </div>
    <div id="footer"></div>
</div>
<!--调查问卷弹框-->
<div class="diaShade" id="diaShade" style="display: none"></div>
<div class="dialogWrap" id="dialogWrap" style="display: none">
    <div class="dialogCon">
        <form action="#" id="commentContent">
            <h2 class="diaHeader clearfix">
                <span>评价</span>
                <hr>
            </h2>
            <p class="title">您是否对此次服务满意?</p>
            <!--评价-->
            <p>
                <span>评价：</span>
                <input type="radio" name="comment" checked="" id="04">
                <label for="04" class="radio">非常满意</label>
                <input type="radio" name="comment" id="03">
                <label for="03" class="radio">满意</label>
                <input type="radio" name="comment" id="02">
                <label for="02" class="radio">一般</label>
                <input type="radio" name="comment" id="01">
                <label for="01" class="radio">不满意</label>
                <input type="radio" name="comment" id="00">
                <label for="00" class="radio">不满意</label>
            </p>
            <!--描述-->
            <p>
                <span>描述：</span>
                <textarea name="" id="descript"></textarea>
            </p>
            <!--按钮-->
            <p class="submitBtnWrap">
				<input type="button" class="btn submitBtn" id="submitBtn" value="确 定" onclick="popup('none')">
            </p>
        </form>
    </div>
</div>
<script>
	var service_end = false ;
	R3Helper.resize();
    // 调查问卷
    var diaShade=document.getElementById('diaShade');
    var dialogWrap=document.getElementById('dialogWrap');
    function popup(para) {
        diaShade.style.display=para;
        dialogWrap.style.display=para;
    }
    document.getElementById('above').scrollTop = document.getElementById('above').scrollHeight  ;	//滚动到 对话内容的 底部
    // 参数连接
	var hostname = location.hostname ;
    var socket = io.connect('${schema!'http'}://'+hostname+':${port}/im/user?userid=${userid!''}&orgi=${orgi!''}&session=${sessionid!''}&appid=${appid!''}&osname=${(osname!'')?url}&browser=${(browser!'')?url}<#if skill??>&skill=${skill}</#if><#if username??>&nickname=${username}</#if><#if agent??>&agent=${agent}</#if>');
    socket.on('connect',function(){
        //service.sendRequestMessage(); 
		//output('<span id="connect-message">'+ new Date().format("yyyy-MM-dd hh:mm:ss") + ' 开始沟通' +'</span>' , 'message connect-message');	 		
    })
    socket.on("agentstatus",function(data){
       document.getElementById('connect-message').innerHTML=data.message;
    })
    socket.on("status",function(data){
		output('<span id="connect-message">'+data.message+'</span>' , 'message connect-message');     
		if(data.messageType == "end"){
			service_end = true ;
			editor.readonly();
		}
    })
    socket.on('message', function(data) {
        var chat=document.getElementsByClassName('chatting-left').innerText;
        chat = data.message;
        if(data.messageType == "image"){
        	chat = "<a href='"+data.message+"_original' target='_blank'><img src='"+data.message+"' class='ukefu-media-image'/></a>" ;
        }
		if(data.calltype == "in"){
			output('<div class="chat-right"> <img class="user-img" src="/im/img/user.png" alt=""><div class="chat-message"><label class="time">'+data.createtime+'</label><label  class="user">'+data.nickName+'</label> </div><div class="chatting-right"><i class="arrow arrow${inviteData.consult_dialog_color!''}"></i><div class="chat-content theme${inviteData.consult_dialog_color!''}">'+chat+'</div></div>' , "chat-block");
		}else if(data.calltype == "out"){
			output('<div class="chat-left"> <img class="user-img" src="<#if inviteData?? && inviteData.consult_dialog_headimg??>/res/image.html?id=${inviteData.consult_dialog_headimg?url}<#else>/images/agent.png</#if>" alt=""><div class="chat-message"><label  class="user">'+data.nickName+'</label><label class="time">'+data.createtime+'</label> </div><div class="chatting-left"><i class="arrow"></i><div class="chat-content">'+chat+'</div></div>' , "chat-block");	
		}
    });
    
    socket.on('disconnect',function() {
        output('<span id="connect-message">连接坐席失败，在线咨询服务不可用</span>' , 'message connect-message');
    });
    function sendDisconnect(){
        socket.disconnect();
    }
    function sendMessage() {
		editor.sync();
		var count = editor.count("text");
		if(count>0 && service_end == false){
	        var message = document.getElementById('message').value;
	        if(message!= ""){ 
				socket.emit('message', {
						appid : "${appid!''}",
						userid:"${userid!''}",
						type:"message" ,
						session:"${sessionid!''}",
						orgi:"${orgi!''}",
						message : message
				});
			}
		}else if(service_end == true){
			alert("坐席已断开和您的对话");	
		}
		editor.html('');
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
	function update(id , message) {
        document.getElementById(id).innerHTML= message;
    }
    
    var message={
        // text:data.message,
        // picture:function(){

        // }
        // file:function(){

        // }
        // lang:function(){

        // }
        // goods:function(){

        // }
        // POI:function(){

        // }

    }
    // 回车事件
    document.onkeyup=function(e){
        if(!e) e=window.event;
        if((e.keyCode||e.which)==13){
            document.getElementById('sent').click();
        }
    }
	window.onresize = function(){
		R3Helper.resize();		
	};
</script>
</body>
</html>