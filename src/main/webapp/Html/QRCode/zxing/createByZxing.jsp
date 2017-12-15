<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用zxing生成二维码</title>
<script type="text/javascript" src="../Plugins/jquery/jquery-1.10.1.js"></script>
</head>
<body>
	<h1>不添加logo</h1>
	<div>
		<span >请输入二维码的内容</span>
		<input id ="content">
		<button class="create" withLogo='false' >生成二维码</button>
	</div>
	
	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#987cb9 SIZE=3>
	
	<div>
		<h1>添加logo</h1>
		<span>请上传logo</span>
		<input id="file" type="file" />
		<span >请输入二维码的内容</span>
		<input id ="content1">
		<button class="create" withLogo='true'>生成二维码</button>
	</div>
	<img id="img" alt="看不到我也没办法啊" src="" > 
</body>
<script type="text/javascript">
	$('.create').on('click',function(eve){
		var withLogo=$(this).attr('withLogo')=='true'?true:false;
		var content
		var param
		if(withLogo){
			var logo
			var file=$('#file')[0].files[0];
				content=$('#content1').val()
			if(!file){
				alert("请选择文件")
				return 
			}else if((file.size/1024)>10){
				var form = new FormData();   
	             form.append("file", file);// 文件对象     
	      
	             // XMLHttpRequest 对象  
	             var xhr = new XMLHttpRequest();      
	             xhr.open("post", "../upload", true);      
	             xhr.onload = function () {   
// 	                 alert(xhr.responseText);   
	                    logo=xhr.responseText
						param={"content":content,withLogo:withLogo,logo:logo,isFile:true}
						console.log(logo)
						var src="data:image/png;base64,";
						$.ajax({
							url:'getQRCode',
							data:param,
							type:'POST',
							success:function(data){
								$('#img').attr('src',src+data)
							}
						})
	             };   
	             xhr.send(form);  
			}else{
				var image = new Image();  
				image.onload=function(){
					logo=getBase64Image(image)
					param={"content":content,withLogo:withLogo,logo:logo,isFile:false}
					console.log(logo)
					var src="data:image/png;base64,";
					$.ajax({
						url:'getQRCode',
						data:param,
						type:'POST',
						success:function(data){
							$('#img').attr('src',src+data)
						}
					})
					
				}
				image.src =  window.URL.createObjectURL(file); 
			}
		}else{//不带logo的二维码
			content=$('#content').val()
			param={"content":content}
			var src="data:image/png;base64,";
			$.ajax({
				url:'getQRCode',
				data:param,
				success:function(data){
					$('#img').attr('src',src+data)
				}
			})
		}
	})
	

function getBase64Image(img) {  
     var canvas = document.createElement("canvas");  
     canvas.width = img.width;  
     canvas.height = img.height;  
     var ctx = canvas.getContext("2d");  
     ctx.drawImage(img, 0, 0, img.width, img.height);  
     var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();  
     var dataURL = canvas.toDataURL("image/"+ext);  
     return dataURL;  
}  

</script>
</html>