$(function() {

	var option = {
			url:'message/queryByPage',
			queryParams:{command:'',description:''},
//			title : "自动回复内容设置",
			fitColumns : true,
			striped : true,
			scrollbarSize:0,
			height:550,
			pagination:true,
			pageSize:10,
			pageList:[10,20,50],
			pagePosition:'bottom',
			columns : [ [ 
              {
				title : "check",
				checkbox : true,
				width : 20
			}, 
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
					return '<button type="button" class="btn btn-sm active btn-info operation modify" data-index='+index+' >修改</button>'+
					'<button type="button" class="btn btn-sm active btn-danger operation delete" data-index='+index+' >删除</button>'
				}
			} ] ],
			onLoadSuccess:function(){
				//表格中删除按钮
				$('.delete').off('click').on('click',function(){
					$this = $(this)
					
					layer.confirm('确认删除?',{btn:['确认','取消']},function(){
						
						var $grid = $('#dataList')
						var id = $grid.datagrid('getRows')[$this.data('index')].id
						$.ajax({
							url:'message/delete',
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
				})
				
				$('.modify').off('click').on('click',function(){
					
					$this = $(this)
					var $grid = $('#dataList')
					var id = $grid.datagrid('getRows')[$this.data('index')].id
					
					layerOpen("修改消息",2,"newMessage?type=modify&id="+id)
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
		layerOpen("新增消息",2,"newMessage?mode=add")
	})
	//批量删除
	$('#deleteBatch').on('click',function(){
		var $grid = $('#dataList')
		var selections =$grid.datagrid('getSelections')
		layer.confirm('是否确认删除'+selections.length+'行?',{btn:['删除','我在想想']},function(){
			var ids=[]
			$.each(selections,function(i,n){
				ids[i]=n.id
			})
			$.ajax({
				url:'message/delete',
				traditional:true,
				data:{id:ids},
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
function layerOpen(title,type,content){
	layer.open({
		title:title,
		type:2,
		skin:"layui-layer-molv",
		content:content,
		area:['400px','330px']
	})
}