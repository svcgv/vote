<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body>
<script src="${ctx }/common/queryCode?projectType=PROJECT_TYPE" type="text/javascript"></script>
<style>
.layui-form-label{width:120px!important;}
</style>
<div class="contract-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>合同管理</legend>
	</fieldset>
	<form class="layui-form" id="contract-index-form" method="POST" action="">
	   <div class="layui-form-item" style="margin-bottom:0px;">
		  	<div class="layui-inline">
		      <label class="layui-form-label">年份：</label>
		       <div class="layui-input-inline">
		         <select name="year" id="getYearRange"></select>
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		     <div class="layui-inline" >
		      <label class="layui-form-label">合同名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="contractName"  autocomplete="off" class="layui-input form-control">
		      </div>
				 <span class="f-placeholder"></span>
		    </div>
		    
		   <div class="layui-inline">
			   <label class="layui-form-label">销售部门：</label>
			    <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="sellDeptId">
		      </div>
	      	  <button type="button"  class="layui-btn layui-btn-sm" id="payDeptNameQuery-hook" ><i class="layui-icon layui-icon-search"></i></button>
		   </div>
		   <div class="layui-inline">
			   <label class="layui-form-label">客户经理：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="custCnName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
				   <input type="text" style='display:none' name="custId">
				   <input type="text" style='display:none' name="custSapCode">
			   </div>
			   <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		   </div>
		   
		    <div class="layui-inline">
		      <label class="layui-form-label">OA流程编号：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="oaFlowCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="custId">
		      </div>
	      	  <button type="button"  class="layui-btn layui-btn-sm" id="customerQuery-hook" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    </div>
		    <div class="layui-form-item" style="margin-bottom:0px;">
			     <div class="layui-inline" >
			      <label class="layui-form-label">创建日期(开始)：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="createTimeStart" id="startTime" autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
			      <span class="f-placeholder"></span>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">创建日期(结束)：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="createTimeEnd" id="endTime"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
			      <span class="f-placeholder"></span>
			    </div>
	 	   	<div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:16px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			  </div>
		   </div>
				<div class="layui-inline">
					<label class="layui-form-label">是否有效：</label>
					<div class="layui-input-inline">
						<select name="isDelete" lay-verify="required" lay-filter="" class="form-control">
							${isUseful.ewTypeHtml }
						</select>
					</div>
				</div>
	   </div>
	</form>
	<table class="layui-hide" id="contractTable" lay-filter="contract"></table>
	
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript">
$(function(){
	//年份
	var currentYear=(new Date()).getFullYear();
    var _optionsHtml="<option value=''>请选择</option>";
	for(var i=currentYear;i>=2010;i--){
		if(i == currentYear){
		_optionsHtml+="<option selected='true' value='"+i+"'>"+i+("年")+"</option>";
		}else{
		_optionsHtml+="<option value='"+i+"'>"+i+("年")+"</option>";
		}
	}
  $(".contract-info-wrapper #getYearRange").html(_optionsHtml);
})
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  //日期
	  laydate.render({
	    elem: "#startTime",
	    theme: 'molv'
	 });
	 laydate.render({
	    elem: "#endTime",
	    theme: 'molv'
	 });
	 //form.render();
	 
	  function getParam(){
			var queryParams=$("#contract-index-form").serializeObject();
			 var newParam = {}
			  for(var i in queryParams){
				  if(queryParams[i]){
					  newParam[i] = queryParams[i]
				  }
			  }
			  return newParam
		}
  // 选择机构
  $(".contract-info-wrapper #payDeptNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=index',
	  		title:"选择销售部门",
	  		width:"400"
	 });
	  
  });
  $(".contract-info-wrapper #customerQuery-hook").click(function(){
	  $.openWindow({
	  		url:'customer?act=index',
	  		title:"选择客户名称",
	  		width:"750"
	 });
	  
  });
  

  // 选择人员
  $(".contract-info-wrapper #custNameQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=index',
	  		title:"选择客户经理",
	  		width:"700"
	 	 });
	});
  
  

  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#contractTable',
	    url:'/vote/pmcontractinfo/list',
	    method:'post',
		where:{
			queryStr:JSON.stringify(getParam())
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-200',
	    title: '合同数据表',
	    cols: [[
	    	{type: 'checkbox', fixed: 'left'},
	  	    {field:'oaFlowCode', title:'OA流程编号',fixed: 'left', sort: true, width:130},
            {field:'contractName', title: '合同名称', width: 200},
            {field:'contractAmount', title:'合同金额', width:130},
            {field:'wbs', title: '项目编号', width: 130},
            {field:'projectName', title:'项目名称', width:200},
            {field:'projectType', title:'项目类型', width:130,templet:function(d){
                return getCodeValue(d.projectType,projectType);
            }},
            {field:'yearNumer', title:'年份', width:120},
            {field:'taxRate', title:'税率', width:130},
            {field:'afterTaxContractAmount', title:'税后合同金额'},
            {field:'signContractDate', title:'签订日期'},
            {field:'contractStartTime', title:'合同开始日期'},
            {field:'contractEndTime', title:'合同结束日期', width:150},
            {field:'isAgree', title:'是否科委认定', width:150},
            {field:'sellDeptName', title:'销售部门'},
            {field:'custManagerName', title:'客户经理'},
            {field:'companyCode', title:'公司代码'},
            {field:'custSapCode', title:'客户SAP编号'},
            {field:'custName', title:'客户名称'},
	  	    {fixed: 'right', title:'操作', toolbar: '#barDemo', width:250}
	    ]],
	    cellMinWidth:'90',
	    page: true,
	    data:[{
	    	contractCode:"123"
	    }]
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(contract)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	      layer.confirm('确认删除行么', function(index){
              $.ajax({
                  type:"POST",
                  url:"/vote/pmcontractinfo/update",
                  data:JSON.stringify({'contractId':data.contractId,'isDelete':'01'}),
                  contentType:'application/json',
                  success:function(data){
                      table.reload('customer-table');
                  }
              });
              obj.del();
	        layer.close(index);
//	        table.reload('customer-table',{});
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.contractId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.contractId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#customQuery").click(function(){
		 console.log("test");
		 table.reload('customer-table',{
				url:'/vote/pmcontractinfo/list',
				page:{
					curr:1 //从第一页开始
				},
			    method:'post',
				where:{
					queryStr:JSON.stringify(getParam())
				},
				contentType: 'application/json',
			    response: {
			    	dataName: 'page'
			    }

			})
			
	}); 
	
	/*
	* 新增
	*/
	$(".contract-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增合同",
	  		width:"100%"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var _width='100%';
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改合同信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看合同信息";
		}
		
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:_width
	  	})
		
	}
	
});
var testData=[];
</script>
</body>
</html>