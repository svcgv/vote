<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.sellTeam-form-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="sellTeam-form-wrapper">
	<form class="layui-form" id="user-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">用户ID：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="userId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >用户名：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="userName"  autocomplete="off" class="layui-input form-control" >
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
	<table class="layui-hide" id="userTable" lay-filter="custom" style="overflow:hidden;"></table>
	
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
	    elem: '#userTable',
	    //url:'custom.json',
	    height:'260',
	    width:"690",
	    title: '用户数据表',
	    cols: [[
	      {type: 'checkbox' },
	      {field:'userId', title:'用户ID', sort: true},
	      {field:'userName', title:'用户名'}
	    ]],
	    data:[
		  		{
		  			"userId":"1000",
					"userName":"系统管理员"
					
				},
				{
		  			"userId":"1001",
					"userName":"admin"
					
				},
				{
		  			"userId":"1002",
					"userName":"Superman"
					
				},
				{
		  			"userId":"1003",
					"userName":"史定波"
					
				}
				
			],
	    page: true
	  });
	
	
	// 保存 事件
	var win=$(".sellTeam-form-wrapper").getWindow();
  	var getExitUser=$("#chosed-user-hook");
	$(".sellTeam-form-wrapper").on("click","#save-hook",function(){
		var ret=[];
		getExitUser.children(".customer-list").each(function(){
			var sapCode2=$(this).children(".customerItem").attr("userId");
			ret.push(sapCode2)
		});
		// 遍历选中的CheckBox
		$(".sellTeam-form-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-checkbox");
			var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
			if(isChecked){
				var userId=$(this).children("td").eq(1).text();
				var userName=$(this).children("td").eq(2).text();
				
				// 遍历不存在的插入
				
				if($.inArray(userId,ret) == -1 ){
					var _html = '<span class="customer-list">'
			         	      +'<span class="customerItem" userId="'+userId+'">'+userName+'</span>'
			               	  +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
			         		  +'</span>';
			         		 getExitUser.append(_html);	  
				}
				
				
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".sellTeam-form-wrapper").getWindow();
	$(".sellTeam-form-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#user-query-form #userQuery").click(function(){
		
		var queryParams=$("#user-query-form").serialize();
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



