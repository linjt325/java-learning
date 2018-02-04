$(function() {
	var option = {
			url:'command/list',
			queryParams:{command:'',description:''},
			title : "自动回复内容设置",
			fitColumns : true,
			striped : true,
			columns : [ [ {
				title : "check",
				checkbox : true,
				width : 20
			}, {
				field : "id",
				title : "序号",
				width : 100,
			}, {
				field : "command",
				title : "指令",
				width : 100,
			}, {
				field : "description",
				title : "描述",
				width : 100,
			}, {
				title : "操作",
				field : "5",
				width : 100,
				formatter:function(){
					
					return '<button type="button" class="btn btn-sm active btn-info modify" contenteditable="true">修改</button>'+
					'<button type="button" class="btn btn-sm active btn-danger delete" contenteditable="true">删除</button>'
				}
			} ] ],
			data:[]
	}

	$('#add').on('click',function(){
//		alert("新增")
		layer.open({
			type:2,
			content:"newMessage",
			area:['20%','50%']
			
		})
	})
//	$('#dataList').datagrid(option)
})