<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
<script type="text/javascript" src="../Plugins/easyui/js/easyui.min.js"></script>
<script type="text/javascript" src="../Plugins/easyui/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../Js/weChat/settings.js"></script>

<!-- layerjs cdn -->
<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
<link href="https://cdn.bootcss.com/layer/3.1.0/theme/default/layer.css" rel="stylesheet">

<link href="../Css/weChat/all.css" rel="stylesheet"/>
<link href="../Plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="../Plugins/easyui/easyui.css" rel="stylesheet"/>

<title>微信自动回复配置</title>
</head>
<body>
	<div class="container container-fluid theme-showcase" id =" container">
		<ol class="breadcrumb">
		    <li><a href="javaScript:void()">Home</a></li>
		<!--     <li><a href="javaScript:void()">2013</a></li> -->
		    <li class="active">内容设置</li>
		</ol>
		<h1>内容设置</h1>
		<hr>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<!-- 	<ul class="nav navbar-nav"> -->
		<!-- 		<li class="active"> -->
		<!-- 			<a href="#">Link</a> -->
		<!-- 		</li> -->
		<!-- 		<li> -->
		<!-- 			<a href="#">Link</a> -->
		<!-- 		</li> -->
		<!-- 		</li> -->
		<!-- 	</ul> -->
			<form class="navbar-form navbar-left" role="search" action="index">
				<div class="form-group">
					<input type="text" class="form-control" name = 'command' value = '${command }' placeholder="指令名称">
					<input type="text" class="form-control" name = 'description' value = '${description }' placeholder="描述">
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
			<div id ="operation" style="float:right">
				<button id= "add" type="button" class="btn active btn-default" ><i class= "glyphicon glyphicon-plus-sign"></i>新增</button>
				<button id= "delete" type="button" class="btn active btn-default"><i class= "glyphicon glyphicon-remove"></i>删除</button>
			</div>
		</div>
		
		<div class="show" >
			<table class="dataList" id="dataList" ></table>
		</div>
		
		<table class= "table table-border " id="jstlDataList" >
			<tr>
				<td><input type="checkbox" id="all" /></td>
				<td>序号</td>
				<td>指令名称</td>
				<td>描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${message}" var="item"  varStatus="status" >
				<tr <c:if test="${status.index %2 != 0 }"> style="background:#ECF6EE;"</c:if> >
					<td><input type="checkbox" /></td>
					<td>${item.id }</td>
					<td>${item.command }</td>
					<td>${item.description }</td>
					<td>
						<button type="button" class="btn btn-sm active btn-info modify" >修改</button>
						<button type="button" class="btn btn-sm active btn-danger delete" >删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>