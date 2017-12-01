<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<base href="<%=basePath%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取验证码</title>
<script type="text/javascript" src="Plugins/jquery/jquery-1.10.1.js"></script>

</head>
<body>
	<table>
		<tr>
			<td>验证码:</td>
			<td>
				<img id="verifyCode" alt="图挂了"  >
				  <a href='#' onclick="javascript:changeImage()" style="color:red;"><label style="color:red;">看不清？</label></a>
			</td>
		</tr>
	</table>

</body>
<script type="text/javascript">
changeImage()
function changeImage(){
	var img=document.getElementById("verifyCode")
	//chrome 中无法直接访问服务器磁盘上的文件,可以通过tomcat 设置虚拟目录进行访问
	$.ajax({
		url:'authImage',
		success:function(data){
			if(data!=null&&data!=''){
				img.src="Images/verifyCode/" +data+".jpg"
			}
		}
		
	})
}
</script>
</html>