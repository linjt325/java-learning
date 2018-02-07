<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" -->
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0,text/html; charset=UTF-8,"  >
<script type="text/javascript" src="../Plugins/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../Plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../Js/weChat/chat.js"></script>
<!-- layerjs cdn -->
<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
<!-- <script src="https://cdn.bootcss.com/moment.js/2.20.1/moment-with-locales.min.js"></script> -->
<script src="https://cdn.bootcss.com/moment.js/2.20.1/moment.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.20.1/locale/zh-cn.js"></script>

<!-- <link href="https://cdn.bootcss.com/bootstrap/3.0.2/css/bootstrap.css" rel="stylesheet"> -->
<link href="../Plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../Css/weChat/chat.css" rel="stylesheet">

<!-- layerjs cdn -->
<link href="https://cdn.bootcss.com/layer/3.1.0/theme/default/layer.css" rel="stylesheet">

<title>公众号</title>

</head>
<body>
	<div class="container" style="height:667px;width:375px;border:1px solid #ccc;overflow:hidden" id ="container">
		<div style="height:620px;width:100%;overflow:auto;padding:10px;"> 
			<ul class="message-container">
<!-- 				<li> -->
<!-- 					<a class="avatar" > -->
<!-- 						<img alt="" src="../Images/verifyCode/qrcode.png"> -->
<!-- 						<span class="username">张三</span> -->
<!-- 					</a> -->
<!-- 					<div> -->
<!-- 						<span class="reply-time">2018-02-05 00:00:00</span> -->
<!-- 						<div class='reply-content'> -->
<!-- 							<span class='message-content'>回复aaaaaaaaaaaaaaaas飒飒撒打算大所多阿萨德阿萨德阿萨德阿萨德啊实打实大上大神的阿萨德啊aaaada复aaa</span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<div class="content"> -->
<!-- 						<span class="message-time">2018-02-05 00:00:00</span> -->
<!-- 						<div class='send-content'> -->
<!-- 							<span class='message-content'>发送</span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<a class="avatar"> -->
<!-- 						<img alt="" src="../Images/verifyCode/qrcode.png"> -->
<!-- 						<span class="username">张三</span> -->
<!-- 					</a> -->
<!-- 				</li> -->
			</ul>
		</div> <!-- background:#ccc; -->
		<div style="height:47px;width:100%;display:flex;padding:0px">
			<div style="line-height:44px">
				 <span class="glyphicon glyphicon-menu-right"> </span>
			</div>
			<div style="flex:1;line-height:44px">
				<input class="form-control" style="height:44px" type="text" placeholder="请输入" id="userInput" >
			</div>
			<div style="width:50px;line-height:44px">
				<button id ='send' class="btn btn-default" style="height:44px;border-radius: 10px;" type="button">发送</button>
			</div>
		</div>
	</div>
</body>
</html>