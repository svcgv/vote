<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
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
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预估收入金额：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估成本：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="predictCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估利润率：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictProfitRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">预付期限：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="predictPeriod" id="predictPeriodDate" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">交付部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="payDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="payDeptId">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="payOrgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="sellDeptId">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="custManagerId">
		      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
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
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="tenderReview">评审</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">查看</a>
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
		    elem: "#predictPeriodDate",
		    theme: 'molv',
		    type: 'datetime'
	 });
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
	    //url:'custom.json',
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '投标数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'bidId', title:'投标编号',fixed: 'left', sort: true, width:130},
	  	      {field:'bidName', title:'投标名称', width:130},
	  	      {field:'status', title:'评审状态', width:130},
	  	      {field:'bidFirstPrice', title:'投标首次报价金额', width:150},
	  	      {field:'custName', title:'客户', width:130},
	  	      {field:'predictAmount', title:'预估收入金额', width:150},
	  	      {field:'predictCost', title:'预估成本', width:120},
	  	      {field:'predictProfitRate', title:'预估利润率'},
	  	      {field:'predictPeriod', title:'预付期限'},
	  	      {field:'payDeptName', title:'交付部门'},
	  	      {field:'sellDeptName', title:'销售部门'},
	  	      {field:'custManagerName', title:'客户经理'},
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
	    ]],
	    cellMinWidth:'90',
	    data:[
	          {
	    	bidId:"123",
	    	bidName:"asdas",
	    	status:"未评审",
	    	bidFirstPrice:"2000",
	    	custName:"asdasd",
	    	predictAmount:"210",
	    	predictCost:"123",
	    	predictProfitRate:"0.8",
	    	predictPeriod:"2018-12-12",
	    	payDeptName:"开发2部",
	    	sellDeptName:"销售一部",
	    	custManagerName:"赫本"
	    },
	    {
	    	bidId:"123",
	    	bidName:"asdas",
	    	status:"交付已评审",
	    	bidFirstPrice:"2000",
	    	custName:"asdasd",
	    	predictAmount:"210",
	    	predictCost:"123",
	    	predictProfitRate:"0.8",
	    	predictPeriod:"2018-12-12",
	    	payDeptName:"开发2部",
	    	sellDeptName:"销售一部",
	    	custManagerName:"赫本"
	    },
	    {
	    	bidId:"123",
	    	bidName:"asdas",
	    	status:"销售已评审",
	    	bidFirstPrice:"2000",
	    	custName:"asdasd",
	    	predictAmount:"210",
	    	predictCost:"123",
	    	predictProfitRate:"0.8",
	    	predictPeriod:"2018-12-12",
	    	payDeptName:"开发2部",
	    	sellDeptName:"销售一部",
	    	custManagerName:"赫本"
	    },
	    {
	    	bidId:"123",
	    	bidName:"asdas",
	    	status:"评审通过",
	    	bidFirstPrice:"2000",
	    	custName:"asdasd",
	    	predictAmount:"210",
	    	predictCost:"123",
	    	predictProfitRate:"0.8",
	    	predictPeriod:"2018-12-12",
	    	payDeptName:"开发2部",
	    	sellDeptName:"销售一部",
	    	custManagerName:"赫本"
	    },
	    {
	    	bidId:"123",
	    	bidName:"asdas",
	    	status:"评审未通过",
	    	bidFirstPrice:"2000",
	    	custName:"asdasd",
	    	predictAmount:"210",
	    	predictCost:"123",
	    	predictProfitRate:"0.8",
	    	predictPeriod:"2018-12-12",
	    	payDeptName:"开发2部",
	    	sellDeptName:"销售一部",
	    	custManagerName:"赫本"
	    }
	    ],
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
		 var queryParams=$("#tender-index-form").serialize();
		 $.ajax({
			  type: 'POST',
			  url: '/vote/pmcompanyinfo/list',
			  data: queryParams,
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
			  	    cols: [[
						   {type: 'checkbox', fixed: 'left'},
				  	      {field:'bidId', title:'投标编号',fixed: 'left', sort: true, width:130},
				  	      {field:'bidName', title:'投标名称', width:130},
				  	      {field:'status', title:'评审状态', width:130},
				  	      {field:'bidFirstPrice', title:'投标首次报价金额', width:150},
				  	      {field:'custName', title:'客户', width:130},
				  	      {field:'predictAmount', title:'预估收入金额', width:150},
				  	      {field:'predictCost', title:'预估成本', width:120},
				  	      {field:'predictProfitRate', title:'预估利润率'},
				  	      {field:'predictPeriod', title:'预付期限'},
				  	      {field:'payDeptName', title:'交付部门'},
				  	      {field:'sellDeptName', title:'销售部门'},
				  	      {field:'custManagerName', title:'客户经理'},
				  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
			  	    ]],
			  	    cellMinWidth:'100',
			  	    data:[],
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
var testData=null;
</script>
</body>
</html>