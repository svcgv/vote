<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.tender-cust-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="tender-cust-wrapper">
	<form class="layui-form" id="customer-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">客户编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >客户名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custName"  autocomplete="off" class="layui-input form-control" >
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
	    title: '客户数据表',
	    cols: [[
	      {type: 'radio' },
	      {field:'custId', title:'客户ID', sort: true},
	      {field:'custName', title:'客户名称'}
	    ]],
	    data:[
		  		{
		  			"custId":"1000",
					"custName":"系统管理员"
					
				},
				{
		  			"custId":"1001",
					"custName":"admin"
					
				},
				{
		  			"custId":"1002",
					"custName":"Superman"
					
				},
				{
		  			"custId":"1003",
					"custName":"史定波"
					
				}
				
			],
	    page: true
	  });
	
	
	// 保存 事件
	var act="${act}";// 区分是index页 form页 赋值问题
	var win=$(".tender-cust-wrapper").getWindow();
  	var getExitUser=$("#chosed-user-hook");
	$(".tender-cust-wrapper").on("click","#save-hook",function(){
		var ret=[];
		getExitUser.children(".customer-list").each(function(){
			var sapCode2=$(this).children(".customerItem").attr("custId");
			ret.push(sapCode2)
		});
		// 遍历选中的radio
		$(".tender-cust-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var custId=$(this).children("td").eq(1).text();
				var custName=$(this).children("td").eq(2).text();
				if(act == "index"){
					//$("#tender-index-form input[name='custManagerName']").val(custName);
					//$("#tender-index-form input[name='developmentManagerId']").val(custId);
			 	}else if(act =="addCust"){ //编辑 修改 页面
			 		$("#tender-addForm-hook input[name='custName']").val(custName);
					$("#tender-addForm-hook input[name='custId']").val(custId);
			 	}else if(act =="reviewPay"){ // 投标 评审
			 		//$("#review-query-form input[name='payDeptName']").val(custName);
					//$("#review-query-form input[name='payDeptId']").val(custId);
			 	}else if(act =="reviewSell"){// 投标 评审
			 		//$("#review-query-form input[name='sellDeptName']").val(custName);
					//$("#review-query-form input[name='sellDeptId']").val(custId);
			 	}
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".tender-cust-wrapper").getWindow();
	$(".tender-cust-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#customer-query-form #userQuery").click(function(){
		
		var queryParams=$("#customer-query-form").serialize();
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



