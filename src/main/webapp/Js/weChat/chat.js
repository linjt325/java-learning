$(function() {
	

	appendDialog('left','请输入指令! 如: 帮助')
	
	$('#userInput').on('keyup',function(e){
		if(e.keyCode==13){
			$('#send').click()
		}
	})
	$('#send').on('click',function(e){
		var url='message/query'
		var $input = $('#userInput')
		var message =$input.val()
		if(!message){
			layer.msg('不能发送空消息!',{time:3000,icon:2})
			return
		}
		if(message=='帮助'){
			url='message/list'
		}
		appendDialog('right',message)
		$input.val('')
		$.ajax({
			url:url,
			data:{command:message},
			success:function(result){
				if(result!=null &&result.length>0){
					var content=''
					if(message=='帮助'){
						$.each(result,function(i,n){
							content+='回复['+ n.command +'] : ' + n.description + '<br/>'
						})
					}else{
						content=result[0].content
					}
					appendDialog('left',content)
				}
				
			}
		})
	})
})

var template={
		left:"<li>" +
				"<a class='avatar' >" +
					"<img alt='' src='../Images/verifyCode/qrcode.png'>" +
					"<span class='username'></span>" +
				"</a>" +
				"<div class='content content-left'>" +
					"<span class='reply-time'></span>" +
					"<div class='reply-content'>" +
						"<span class='message-content'></span>" +
					"</div>" +
				"</div>" +
			"</li>",
		right:"<li>" +
					"<div class='content content-right'>" +
						"<span class='reply-time'></span>" +
						"<div class='send-content'>" +
							"<span class='message-content'></span>" +
						"</div>" +
					"</div>" +
					"<a class='avatar' >" +
						"<img alt='' src='../Images/verifyCode/qrcode.png'>" +
						"<span class='username'></span>" +
					"</a>" +
				"</li>"
}

function appendDialog(position,text){
	var $_this
	if(position=='left'){
		$_this = $(template.left)
		$_this.find('.username').text('公众号')
	}else if(position=='right'){
		$_this = $(template.right)
		$_this.find('.username').text('你')
	}
	$_this.find('.reply-time').text(moment().format('llll'))
	$_this.find('.message-content').html(text)
	$('.message-container').append($_this)
}