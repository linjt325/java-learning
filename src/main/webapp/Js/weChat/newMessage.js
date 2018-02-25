$(function() {
	var mode =getQueryString("mode");
	var baseUrl = "message/";
	var url = "";
	var submitButton = $(submit);
	if(mode=='add'){
		url = baseUrl + "insert"; 
		submitButton.text("新增")
	}else{
		url = baseUrl + "update"; 
		submitButton.text("保存更新")
	}
	
	$('#submit').on('click',function(e){
		var data=$('#newMessage').serialize();
		
		$.ajax({
			url:url,
			data:data,
			success:function(result){
				parent.layer.closeAll();
				parent.$('#search').click()
			}
		})
	});
	$('#submitBatch').on('click',function(e){
		var data =[{
			command:'batch1',
			description:'batch1',
			content:'batch1'
		},{
			command:'batch2',
			description:'batch2',
			content:'batch2'
		},{
			command:'batch3',
			description:'batch3',
			content:'batch3'
		}];
		//测试批量
		$.ajax({
			url:url+'Batch',
			data:JSON.stringify(data),
			type : 'post',//指定请求类型,需要将参数放到request中
			contentType : "application/json; charset=utf-8",
			success:function(result){
				parent.layer.closeAll();
				parent.$('#search').click()
			}
		})
	})
});

function getQueryString(name){
	var reg= new RegExp("([[?]|&])"+name+"=([^&]*)(&|$)");
	var r = location.search.match(reg);
	if(r != null){
		return decodeURI(r[2])
	}
}