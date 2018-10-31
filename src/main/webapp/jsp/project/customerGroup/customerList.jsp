<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.customGroup-form-wrapper .layui-form-label{width:90px;}
</style>
<div style="margin-top:10px;" class="customGroup-form-wrapper" style="height:400px;">
	<form class="layui-form" id="formGroup-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">SAP编码：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="sapCode"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >客户名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custCnName"  autocomplete="off" class="layui-input form-control" >
	      </div>
	    </div>
	    
 	   <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		  </div>
	   </div>
	    
	    
	  </div>
	</form>
	<table class="layui-hide" id="customTable" lay-filter="custom"></table>
</div>

<script type="text/javascript">
$(function(){
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  tableGroup=layui.table;
	  
  // table render
  tableGroup.render({
	    elem: '#customTable',
	    //url:'custom.json',
	    height:'260',
	    title: '客户数据表',
	    cols: [[
	      {type: 'checkbox', fixed: 'left'},
	      {field:'sapCode', title:'sap编号',fixed: 'left', sort: true},
	      {field:'custCnName', title:'客户名称'},
	    ]],
	    data:testData,
	    page: true
	  });
	/*
	* 监听 checkbox 
	*/
	var ids=[]; // 父类传值 默认值
	var names=[];
	var index = parent.layer.getFrameIndex(window.name);
	console.log('formGroup',index)
	tableGroup.on('checkbox', function(obj){
		console.log(obj,111)
	 	if(obj.checked){
	 		ids.push(obj.data.sapCode);
	 		names.push(obj.data.custCnName);
	 	}else{
	 		// 存在删除
	 		for(var i in ids){
	 			if(ids[i].custGroupId == obj.data.sapCode){
	 				ids.splice(i,1);// 删除
	 				names.splice(i,1);// 删除
	 			}
	 		}
	 	}
		console.log(ids,names)
		// 存到父类 
		var formIframe=parent.document.getElementsByTagName('iframe')[0];
			formIframe.contentWindow.document.getElementById("saveCustomerIds").setAttribute("value",ids.join(","));
			formIframe.contentWindow.document.getElementById("saveCustomerNames").setAttribute("value",names.join(","));
		
	});
	
	
	
	
	/*
	* 客户查询按钮
	*/
	$("#customQuery").click(function(){
		
		var queryParams=$("#customer-query-form").serialize();
		console.log(queryParams)
		tableGroup.reload('customerGroup-table',{
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
var testData=[
	  		{
	  			"sapCode":"1000000210",
				"custCnName":"上海浦东发展银行股份有限公司"
				
			},
			{
				"sapCode":"1000000211",
				"custCnName":"南京发展银行股份有限公司"
			}
		]
	
});
</script>



