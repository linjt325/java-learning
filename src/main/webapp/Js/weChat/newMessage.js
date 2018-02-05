$(function() {

	$('#submit').on('click',function(e){
		var data=$('#newMessage').serialize()
		
		$.ajax({
			url:"command/insert",
			data:data,
			success:function(result){
				parent.layer.closeAll()
				parent.$('#search').click()
			}
		})
	})
})