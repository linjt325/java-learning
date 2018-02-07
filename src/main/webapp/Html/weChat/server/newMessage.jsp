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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Plugins/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../Plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../Js/weChat/newMessage.js"></script>
<!-- <script type="text/javascript" src="../Plugins/easyui/js/easyui.min.js"></script> -->
<!-- <script type="text/javascript" src="../Plugins/easyui/js/easyui-lang-zh_CN.js"></script> -->

<link href="https://cdn.bootcss.com/bootstrap/3.0.2/css/bootstrap.css" rel="stylesheet">
<!-- <link href="../Plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"/> -->
<!-- <link href="../Plugins/easyui/easyui.css" rel="stylesheet"/> -->

<title>微信自动回复配置</title>

</head>
<body>
	<div class="container theme-showcase" style="padding:15px" id ="container">
		<form id= "newMessage" role="form" action="command/insert">
			 <div class="form-group" style="display:none">
			 	<label>id</label>
			 	<input class="form-control" type="text" name ="id"  value ="${message.id }">
			 </div>
			 <div class="form-group">
			 	<label>命令</label>
			 	<input class="form-control" type="text" name ="command" value ="${message.command }">
			 </div>
			 <div class="form-group">
			 	<label>描述</label>
			 	<input class="form-control" type="text" name ="description"  value ="${message.description }">
			 </div>
			 <div class="form-group">
			 	<label>详情</label>
			 	<input class="form-control" type="text" name ="content"  value ="${message.content }">
			 </div>
<!-- 			 style="display:none"  -->
			<button id ="submit" type="button" class="btn btn-default btn-block">新增</button>
		</form>
<!-- 		<button id ="submit1" type="button" class="btn btn-default btn-block">新增</button> -->
	</div>
</body>
</html>