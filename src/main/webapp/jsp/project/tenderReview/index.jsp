<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.tender-info-wrapper .layui-form-label{width:130px!important;}
</style>
<div class="tender-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>投标评审管理</legend>
	</fieldset>
	<form class="layui-form" id="tender-index-form" method="POST" action="">
	   <div class="layui-form-item">
	   
		  	<div class="layui-inline">
		      <label class="layui-form-label">投标名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="bidName"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预估收入（元）：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估成本（元）：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="predictCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估利润率（%）：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictProfitRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    

		   <div class="layui-inline">
			   <label class="layui-form-label">预付开始期限：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="predictPeriodStart" id="predictPeriodStartDate-edit" autocomplete="off" class="layui-input form-control hasDatepicker">
			   </div>
			   <span class="f-placeholder"></span>
		   </div>
		   <div class="layui-inline">
			   <label class="layui-form-label">预付结束期限：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="predictPeriodEnd" id="predictPeriodEndDate-edit" autocomplete="off" class="layui-input form-control hasDatepicker">
			   </div>
			   <span class="f-placeholder"></span>
		   </div>
		   
		    <div class="layui-inline">
		      <label class="layui-form-label">交付部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="constructionDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="constructionDeptId">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="payOrgQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="sellDeptId">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
	     	<div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="custManagerId">
		      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:25px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="tenderReviewQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			  </div>
		   </div>
		   
	   </div>
	</form>
	<table class="layui-hide" id="productTable" lay-filter="custom"></table>
	
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">评审</a>
</script>

<script type="text/javascript">

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  
  //日期
	  laydate.render({
		    elem: "#predictPeriodStartDate-edit",
		    theme: 'molv',
		    
	 });
    laydate.render({
        elem: "#predictPeriodEndDate-edit",
        theme: 'molv',
        
    });
    
    function getParam(){
		var queryParams=$("#tender-index-form").serializeObject();
		 var newParam = {}
		  for(var i in queryParams){
			  if(queryParams[i]){
				  newParam[i] = queryParams[i]
			  }
		  }
		  return newParam
	}
  // 选择机构
  $(".tender-info-wrapper #orgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=index',
	  		title:"选择销售部门",
	  		width:"400"
	 });
	  
  });
  $(".tender-info-wrapper #payOrgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=pay',
	  		title:"选择交付部门",
	  		width:"400"
	 });
	  
  });
  
  
  // 选择人员
  $(".tender-info-wrapper #userQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=index',
	  		title:"选择客户经理",
	  		width:"700"
	 	 });
	});

  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#productTable',
	    url:'/vote/pmreviewinfo/selectBidReview',
	    method:'post',
		where:{
			queryStr:JSON.stringify(getParam())
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '投标数据表',
	    cols: cols,
	    cellMinWidth:'90',
	    
	    page: true
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	   
	    if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.bidId);
	    }else if(obj.event == "tenderReview"){
	    	// 评审
	    	showFromTable('review',data.bidId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#tenderReviewQuery").click(function(){
		 var queryParams=$("#tender-index-form").serializeObject();
		  var newParam = {}
		  for(var i in queryParams){
			  if(queryParams[i]){
				  newParam[i] = queryParams[i]
			  }
		  }
		  queryParams = newParam
		  var par = {}
		  par.queryStr=JSON.stringify(queryParams)
		 $.ajax({
			  type: 'POST',
			  url: '/vote/pmreviewinfo/selectBidReview',
			  data: JSON.stringify(par),
			  contentType:'application/json',
			  success: function(res){
			      console.log(res)
			      testData=res.page
			      table.render({
			  	  	id:"customer-table",
			  	    elem: '#productTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-250',
			  	    title: '投标据表',
			  	    cols: cols,
			  	    cellMinWidth:'100',
			  	    data:testData,
			  	    page: true
			  	  	});},
			  dataType: "json"
			});
		
	}); 
	
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var _width=800;
	  if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看投标信息";
		}else if(isEdit == "review"){
			var url='review?act=review&id='+id;
	    	var	title="投标评审";
	    	_width=600;
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:_width
	  	})
		
	}
	
});
var testData=[];
var cols=[[
	  {type: 'checkbox', fixed: 'left'},
	      {field:'bidCode', title:'投标编号',fixed: 'left', sort: true, width:130},
	      {field:'bidName', title:'投标名称', width:130},
  	    {field:'status', title: '评审状态', width: 200
      	      ,templet: function(d){
      	    	if(d.status=='00'){
      	        	return '已录入'
      	        }
      	    	if(d.status=='01'){
      	        	return '交付部门负责人评审'
      	        }
      	    	if(d.status=='02'){
      	        	return '销售部门负责人评审'
      	        }
      	    	if(d.status=='03'){
      	        	return '技术总监评审'
      	        }
      	    	if(d.status=='04'){
      	        	return '评审完成'
      	        }
      	    	else{
      	    		return '数据待完善'
      	    	}
      	      },rowspan: 2
      	    },
	      {field:'firstBidAmount', title:'首次报价（元）', width:150},
	      {field:'custCnName', title:'客户', width:130},
	      {field:'predictAmount', title:'预估收入（元）', width:150},
	      {field:'predictCost', title:'预估成本（元）', width:120},
	      {field:'predictProfitRate', title:'预估利润率（%）'},
    {field:'predictPeriodStart', title:'预付开始期限'},
    {field:'predictPeriodEnd', title:'预付结束期限'},
	      {field:'constructionDeptName', title:'交付部门'},
	      {field:'sellDeptName', title:'销售部门'},
	      {field:'projectManagerName', title:'项目经理'},
	      {field:'custManagerName', title:'客户经理'},
	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
]]
</script>
</body>
</html>