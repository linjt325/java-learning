$(function() {
	var mode =getQueryString("mode")
	var baseUrl = "message/"
	var url = ""
	var submitButton = $(submit)
	if(mode=='add'){
		url = baseUrl + "insert" 
		submitButton.text("新增")
	}else{
		url = baseUrl + "update" 
		submitButton.text("保存更新")
	}
	
	$('#submit').on('click',function(e){
		var data=$('#newMessage').serialize()
		
		$.ajax({
			url:url,
			data:data,
			success:function(result){
				parent.layer.closeAll()
				parent.$('#search').click()
			}
		})
	})
})

function getQueryString(name){
	var reg= new RegExp("([[?]|&])"+name+"=([^&]*)(&|$)")
	var r = location.search.match(reg)
	if(r != null){
		return decodeURI(r[2])
	}
}