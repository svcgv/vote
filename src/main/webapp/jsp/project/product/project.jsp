<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.product-form-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="product-form-wrapper">
	<form class="layui-form" id="project-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">项目编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="projectId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >项目名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control" >
	      </div>
	    </div>
	    
 	   <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="userQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		  </div>
	   </div>
	    
	  </div>
	</form>
	<table class="layui-hide" id="projectTable" lay-filter="custom" style="overflow:hidden;"></table>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">
$(function(){
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
	  
  // table render
  table.render({
	    elem: '#projectTable',
	    //url:'custom.json',
	    height:'260',
	    width:"690",
	    title: '项目表信息',
	    cols: [[
	      {type: 'checkbox' },
	      {field:'projectId', title:'项目编号', sort: true},
	      {field:'projectName', title:'项目名称'}
	    ]],
	    data:[
		  		{
		  			"projectId":"1000",
					"projectName":"雪松-软件研发外包战略供应商招标"
					
				},
				{
		  			"projectId":"1001",
					"projectName":"深圳数位传媒科技有限公司IT人力外包"
					
				},
				{
		  			"projectId":"1002",
					"projectName":"信贷及相关系统技术服务项目"
					
				},
				{
		  			"projectId":"1003",
					"projectName":"新华保险人力外包服务项目"
					
				}
				
			],
	    page: true
	  });
	
	
	// 保存 事件
	var win=$(".product-form-wrapper").getWindow();
  	var getExitProject=$("#product-addForm-hook #chosed-project-hook");
	$(".product-form-wrapper").on("click","#save-hook",function(){
		var ret=[];
		getExitProject.children(".customer-list").each(function(){
			var sapCode2=$(this).children(".customerItem").attr("projectId");
			ret.push(sapCode2)
		});
		// 遍历选中的CheckBox
		$(".product-form-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-checkbox");
			var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
			if(isChecked){
				var projectId=$(this).children("td").eq(1).text();
				var projectName=$(this).children("td").eq(2).text();
				
				// 遍历不存在的插入
				
				if($.inArray(projectId,ret) == -1 ){
					var _html = '<span class="customer-list">'
			         	      +'<span class="customerItem" projectId="'+projectId+'">'+projectName+'</span>'
			               	  +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
			         		  +'</span>';
			         		 getExitProject.append(_html);	  
				}
				
				
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".product-form-wrapper").getWindow();
	$(".product-form-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#project-query-form #userQuery").click(function(){
		
		var queryParams=$("#project-query-form").serialize();
		console.log(queryParams)
		table.reload('customerGroup-table',{
			url:'form',
			page:{
				curr:1 //从第一页开始
			},
			method:'post',
			where:{
				queryStr:queryParams
			},
			done:function(res){
				console.log(res)
			}
			
		})
	});
	
});
	
});



</script>



