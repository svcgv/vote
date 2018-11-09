<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
</style>
<div class="project-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>项目管理</legend>
	</fieldset>
	<form class="layui-form" id="project-index-form" method="POST" action="">
	   <div class="layui-form-item">
	   
		  	<div class="layui-inline">
		      <label class="layui-form-label">实施部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="buildDeptName"  autocomplete="off" class="layui-input form-control">
		         <input type="hidden" name="buildDeptId" />
		      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="buildDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">实施负责人：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="buildManagerName"  autocomplete="off" class="layui-input form-control">
			         <input type="hidden" name="buildManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="buildManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">销售部门：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="sellDeptName"  autocomplete="off" class="layui-input form-control">
				       <inpu type="hidden" name="sellDeptId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售负责人：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="sellManagerName"  autocomplete="off" class="layui-input form-control">
		         <input type="hidden" name="sellManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="sellManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="wbs" autocomplete="off" class="layui-input form-control">
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="wbsQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		     
		     <div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="projectNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" autocomplete="off" class="layui-input form-control">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline" style="margin-right:40px;">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		        	 <option value="00" selected>整包项目</option>
		        	 <option value="01">人力项目</option>
		        	 <option value="02">订单项目</option>
		        	 <option value="03">内部研发项目</option>
		          </select>
		      </div>
		    </div>
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">状态：</label>
		       <div class="layui-input-inline">
		          <select name="state" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		        	 <option value="00" selected>进行中</option>
		        	 <option value="02">待验收</option>
		        	 <option value="01">结项</option>
		        	 <option value="03">关闭</option>
		          </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectTime" id="createProjectTime-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectTime" id="finishProjectTime-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
		    </div>
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			  </div>
		   </div>
	   </div>
	</form>
	
	<table class="layui-hide" id="projectIndexTable" lay-filter="custom"></table>
	
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="tenderReview">提交评审</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
		    elem: "#createProjectTime-hook",
		    theme: 'molv',
		    type: 'datetime'
	 });
	laydate.render({
		    elem: "#finishProjectTime-hook",
		    theme: 'molv',
		    type: 'datetime'
	 });
  //选择实施部门
  $(".project-info-wrapper #buildDeptNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=buildDept',
	  		title:"选择实施部门",
	  		width:"400"
	 });
	  
  });
  // 选择实施负责人
  $(".project-info-wrapper #buildManagerNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'user?act=buildManager',
	  		title:"选择实施负责人",
	  		width:"700"
	 });
  });
  // 选择销售部门
  $(".project-info-wrapper #sellDeptNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=sellDept',
	  		title:"选择销售部门",
	  		width:"400"
	 });
  });
  
  // 选择销售负责人
  $(".project-info-wrapper #sellManagerNameQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=sellManager',
	  		title:"选择销售负责人",
	  		width:"700"
	 	 });
	});
  
  // 选择WBS编号
  $(".project-info-wrapper #wbsQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'project?act=wbs',
	  		title:"选择WBS编号",
	  		width:"700"
	 	 });
	});
  // 选择项目名称
  $(".project-info-wrapper #projectNameQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'project?act=wbs',
	  		title:"选择项目名称",
	  		width:"700"
	 	 });
	});
  // 选择客户名称
  $(".project-info-wrapper #wbsQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=wbs',
	  		title:"选择客户名称",
	  		width:"700"
	 	 });
	});
  
  
  
  

  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#projectIndexTable',
	    //url:'custom.json',
	    toolbar: '#toolbarDemo',
	    height:'full-200',
	    title: '投标数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'bidId', title:'投标编号',fixed: 'left', sort: true, width:130},
	  	      {field:'bidName', title:'投标名称', width:130},
	  	      {field:'status', title:'评审状态', width:130},
	  	      {field:'bidFirstPrice', title:'投标首次报价金额', width:150},
	  	      {field:'custName', title:'客户名称', width:130},
	  	      {field:'predictAmount', title:'预估收入金额', width:150},
	  	      {field:'predictCost', title:'预估成本', width:120},
	  	      {field:'predictProfitRate', title:'预估利润率'},
	  	      {field:'predictPeriod', title:'预付期限'},
	  	      {field:'payDeptName', title:'交付部门'},
	  	      {field:'sellDeptName', title:'销售部门'},
	  	      {field:'custManagerName', title:'客户经理'},
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:230}
	    ]],
	    cellMinWidth:'90',
	    data:[],
	    page: true
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	      layer.confirm('确认删除行么', function(index){
	        obj.del();
	        layer.close(index);
	        table.reload('customer-table',{
	        	
	        });
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.bidId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.bidId);
	    }else if(obj.event == "tenderReview"){
	    	// 评审
	    	showFromTable('review',data.bidId);
	    }else if(obj.event == "setMoney"){
	    	// 评审
	    	showFromTable('setMoney',data.bidId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#customQuery").click(function(){
		 var queryParams=$("#project-index-form").serializeObject();
		 var newparam = {}
 		 for(var o in queryParams){
 			 if(queryParams[o]){
 				 newparam[o] = queryParams[o]
 			 }
 		 }
		 
		 $.ajax({
			  type: 'POST',
			  url: '/vote/pmconfirmbid/list',
			  data: JSON.stringify(newparam),
			  contentType:'application/json',
			  success: function(res){

				 testData=res.page
			      table.render({
			  	  	id:"customer-table",
			  	    elem: '#projectIndexTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-200',
			  	    title: '投标据表',
			  	    cols: [[
						  {type: 'checkbox', fixed: 'left'},
				  	      {field:'bidId', title:'投标编号',fixed: 'left', sort: true, width:130},
				  	      {field:'bidName', title:'投标名称', width:130},
				  	      {field:'status', title:'评审状态', width:130},
				  	      {field:'bidFirstPrice', title:'投标首次报价金额'},
				  	      {field:'custName', title:'客户名称', width:230},
				  	      {field:'predictAmount', title:'预估收入金额'},
				  	      {field:'predictCost', title:'预估成本'},
				  	      {field:'predictProfitRate', title:'预估利润率'},
				  	      {field:'predictPeriod', title:'预付期限'},
				  	      {field:'payDeptName', title:'交付部门'},
				  	      {field:'sellDeptName', title:'销售部门'},
				  	      {field:'custManagerName', title:'客户经理'},
				  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:230}
			  	    ]],
			  	    cellMinWidth:'100',
			  	    data:testData,
			  	    page: true
			  	  	});
			  
			  },
			  dataType: "json"
			})
			
			
			
			
		
	}); 
	
	/*
	* 新增
	*/
	$(".project-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增投标",
	  		width:"800"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var _width=800;
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改投标信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看投标信息";
		}else if(isEdit == "review"){
			var url='review?act=review&id='+id;
	    	var	title="投标评审";
	    	_width=650;
		}
		/*else if(isEdit == "setMoney"){
			var url='setMoney?act=setMoneyw&id='+id;
	    	var	title="设置付款点";
	    	_width=820;
		}*/
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