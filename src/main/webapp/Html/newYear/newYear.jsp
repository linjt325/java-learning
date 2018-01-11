<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8"-->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<title>新年快乐!</title>
<style>
	html,body{
		width:100%;
		height:100%;
	}
	.containers{
		position:relative;
		width:100%;
		height:100%;
	}
	.img-wrapper{
		position:absolute;
		width:100%;
		top:0;
		right:0;
		left:0;
		bottom:100px;
		background:url("../Images/newYear/newYear.jpg");
		background-size:cover;
		background-position:center center;
	}
	.footer-wrapper{
		position:absolute;
		width:100%;
		height:100px;
		bottom:0;
		display:flex;
	}
	.text{
		background:#f5f6f7;
		flex:1;
		padding:10px;
		font-family:"Microsoft-YaHei";
		
	}
	.text p{
		margin-bottom:6px;
	}
	.text h4{
		font-weight:700;
		color:#c3092a;
	}
	.img-sm{
		width:100px;
		height:100px;
		background:#f4f5f6;
	}
	.img-sm img{
		width:100%;
		height:100%;
	}
</style>
</head>
<body>
<div class="containers">
	<div class="img-wrapper"></div>
	<div class="footer-wrapper">
		<div class="text">
			<center><b>&lt/2017&gtHello 2018&lt2018&gt</b></center>
			<h4>扫码有一个流程比较复杂的惊喜!</h4>
		</div>
		<div class="img-sm">
			<img alt="红包" src="../Images/newYear/redpack.png">
		</div>
	</div>
<%! Integer count=0; %>
<% count++;
	System.out.println(count);
%>
</div>


<!-- <script type="text/javascript" src="../Plugins/jquery/jquery-1.10.1.js" ></script>
<script type="text/javascript" src="../Plugins/bootstrap/js/bootstrap.min.js"></script> -->
</body>
</html>