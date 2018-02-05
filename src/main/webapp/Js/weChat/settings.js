$(function() {
	var option = {
			url:'command/query',
			queryParams:{command:'',description:''},
//			title : "自动回复内容设置",
			fitColumns : true,
			striped : true,
			scrollbarSize:0,
			columns : [ [ 
//              {
//				title : "check",
//				checkbox : true,
//				width : 20
//			}, 
			{
				field : "id",
				title : "序号",
				width : 50,
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
				formatter:function(value,row,index){
					return '<button type="button" class="btn btn-sm active btn-info modify" data-index='+index+' >修改</button>'+
					'<button type="button" class="btn btn-sm active btn-danger delete" data-index='+index+' >删除</button>'
				}
			} ] ],
			onLoadSuccess:function(){
				//表格中删除按钮
				$('.delete').off('click').on('click',function(){
					var $grid = $('#dataList')
//					var id = $(this).parent().parent().siblings('td[field=id]').value
					var id = $grid.datagrid('getRows')[$(this).data('index')].id
					$.ajax({
						url:'command/delete',
						traditional:true,
						data:{id:[id]},
						success:function(result){
							console.log(result)
							if(result){
								layer.msg('删除成功!',{icon:1,time:3000})
								$grid.datagrid('reload')
							}else{
								layer.msg('删除失败!',{icon:4,time:3000})
							}
						}
					})
				})

			}
			
	}
	
	//初始化表格
	gridInit('#dataList',option)
	
	//查询按钮
	$('#search').on('click',function(e){
		$('#dataList').datagrid('load',getFormData('searchForm'))
	})
	//新增消息按钮
	$('#add').on('click',function(){
		layer.open({
			title:"新增消息",
			type:2,
			skin:"layui-layer-molv",
			content:"newMessage",
			area:['400px','330px']
		})
	})
	
})

function getFormData(id){
	var form = $('#'+id)
	var inputs = form.find('input')
	var data={}
	$.each(inputs,function(i,n){
		data[n.name] = n.value
	})
	return data
}

function gridInit(container,option){
	
	var $container = $(container)
	var default_opt = {
//			title : "自动回复内容设置",
			fitColumns : true,
			striped : true,
			scrollbarSize:0,
			columns : [ [ ] ],
			data:[]
	}
	
	$container.datagrid(option)
}