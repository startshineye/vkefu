<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>im</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/js/socket.io.js"></script>

<style>
body {
	padding: 20px;
}

#console {
	height: 400px;
	overflow: auto;
}

.username-msg {
	color: orange;
}

.connect-msg {
	color: green;
}

.disconnect-msg {
	color: red;
}

.send-msg {
	color: #888
}
</style>
</head>
<script type="text/javascript">
 var userId ="${userId}";
 var hostName="${hostName}";
 var port="${port}";
 var schema="${schema}";
 var userName = "${userName}";
 var sessionId="${sessionId}";
</script>
<script type="text/javascript" src="/js/user/user.js"></script>
<body>
	 <h1>${userId}</h1>
    <br />
	<p>hostname:${hostName} port:${port} schema:${schema}</p>
    <span>用户:${userName}登录  sessionId:${sessionId}</span>
    <div id="console" class="well"></div>
    <form class="well form-inline" onsubmit="return false;">
       <!--  <input id="name" class="input-xlarge" type="text" placeholder="用户名称. . . " /> -->
        <input id="msg" class="input-xlarge" type="text" placeholder="发送内容. . . " />
        <button type="button" onclick="sendMessage();" class="btn">Send</button>
        <button type="button" onclick="sendDisconnect();" class="btn">Disconnect</button>
    </form>
</body>
</html>
