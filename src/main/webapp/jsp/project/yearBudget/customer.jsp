<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
.budget-cust-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="budget-cust-wrapper">
	<form class="layui-form" id="customer-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">客户编号：</label>
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
	
	
  table.render({
	    elem: '#userTable',
	    id:'customerGroup-table',
	    url:'/vote/pmcustomerinfo/list',
	    method:'post',
		where:{
			queryStr:JSON.stringify({})
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    height:'260',
	    width:"690",
	    title: '客户数据表',
	    cols: [[
	      {type: 'radio' },
	      {field:'sapCode', title:'客户SAP编号', sort: true},
	      {field:'custCnName', title:'客户名称',templet:function(d){
	    	  return '<div data-id="'+d.custId+'">'+d.custCnName+'</div>'
	      }}
	    ]],
	    page: true
	  })
  // table render
  
	// 保存 事件
	var act="${act}";// 区分是index页 form页 赋值问题
	var win=$(".budget-cust-wrapper").getWindow();
	$(".budget-cust-wrapper").on("click","#save-hook",function(){
		// 遍历选中的radio
		$(".budget-cust-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var custId=$(this).children("td").eq(1).text();
				var custName=$(this).children("td").eq(2).text();
				 if(act == "indexSearch"){
			 		$(".budget-info-wrapper input[name='custName']").val(custName);
					$(".budget-info-wrapper input[name='custId']").val(custId);
			 	}else if(act == "choseOldCustomer"){
			 		$.ajax({
						type:"GET",
						url:"trTempl",
						data:{
							custName:custName,
							custId:custId
						},
						success:function(html){
							var win2=$(".budget-wrapper").getWindow();
							$(".budget-wrapper tbody").append(html);
							$(".j-budgetYear").insertYearOption();
							budgetForm.render();
							win2.resize();
						}
					})
			 	}
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".budget-cust-wrapper").getWindow();
	$(".budget-cust-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	
$("#customer-query-form #userQuery").click(function(){
		
		var queryParams=$("#customer-query-form").serializeObject();
		var newparam = {}
		 for(var o in queryParams){
			 if(queryParams[o]){
				 newparam[o] = queryParams[o]
			 }
		 }
		table.reload('customerGroup-table',{
			url:'/vote/pmcustomerinfo/list',
			page:{
				curr:1 //从第一页开始
			},
		    method:'post',
			where:{
				queryStr:JSON.stringify(newparam)
			},
			contentType: 'application/json',
		    response: {
		    	dataName: 'page'
		    },
			done:function(res){
				console.log(res)
			}
			
		}) 
	});
	
	
	
});
	
});


var testData=[]
</script>
