<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.projectApprove-info-wrapper .layui-form-label{width:100px!important;}
</style>
<div class="projectApprove-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>立项审批</legend>
	</fieldset>
	<form class="layui-form" id="projectApprove-index-form" method="POST" action="">
	   <div class="layui-form-item" style="margin-bottom:0px;">
	   		<div class="layui-inline">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="wbs" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="wbsQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		  	<div class="layui-inline">
		       <label class="layui-form-label">项目名称：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="projectName" readonly="readonly"   autocomplete="off" class="layui-input form-control disabledColor">
			      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="projectNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline" style="">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
		        	      ${projectType.ewTypeHtml}
		          </select>
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">实施部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="buildDeptName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
		         <input type="text" style='display:none' name="buildDeptId" />
		      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="buildDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">项目经理：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="projectManagerName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
			         <input type="text" style='display:none' name="projectManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="projectManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="sellDeptId">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="custManagerId">
		      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="custManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    </div>
	    <div class="layui-form-item" style="margin-bottom:0px;">
		    <div class="layui-inline">
		       <label class="layui-form-label">立项开始时间：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectStartTime"  id="createProjectStartTime-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		       <span class="f-placeholder"></span>
		    </div>
	        <div class="layui-inline">
		       <label class="layui-form-label">立项结束时间：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectEndTime" id="createProjectEndTime-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		       <span class="f-placeholder"></span>
		    </div>
		   </div>
		 <div class="layui-form-item" style="margin-bottom:0px;">
		    <div class="layui-inline" >
			      <label class="layui-form-label">结项开始时间：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectStartTime" id="finishProjectStartTime-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
			       <span class="f-placeholder"></span>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">结项结束时间：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectEndTime" id="finishProjectEndTime-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
			       <span class="f-placeholder"></span>
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
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="review">评审</a>
</script>

<script type="text/javascript">

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table;
  //日期
  laydate.render({
		    elem: "#projectApprove-index-form #createProjectStartTime-hook",
		    theme: 'molv',
		    type: 'date'
	});
 
  laydate.render({
	    elem: "#projectApprove-index-form #createProjectEndTime-hook",
	    theme: 'molv',
	    type: 'date'
	});
	
	laydate.render({
	    elem: "#projectApprove-index-form #finishProjectStartTime-hook",
	    theme: 'molv',
	    type: 'date'
	 });
	
	laydate.render({
	    elem: "#projectApprove-index-form #finishProjectEndTime-hook",
	    theme: 'molv',
	    type: 'date'
});
  
  // 选择WBS编号
  $("#projectApprove-index-form #wbsQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'project?act=index',
	  		title:"选择WBS编号",
	  		width:"700"
	 	 });
	});
  // 选择项目名称
  $("#projectApprove-index-form #projectNameQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'project?act=index',
	  		title:"选择项目名称",
	  		width:"700"
	 	 });
	});
  // 实施部门
    $("#projectApprove-index-form #buildDeptNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'org?act=buildDept',
		  		title:"选择实施部门",
		  		width:"400"
		 });

    });
    // 选择项目经理
    $("#projectApprove-index-form #projectManagerNameQuery-hook").click(function(){
	  	  $.openWindow({
	  	  		url:'user?act=projectManager',
	  	  		title:"选择项目经理",
	  	  		width:"700"
	  	  });
    });
    // 选择销售部门
    $("#projectApprove-index-form #sellDeptNameQuery-hook").click(function(){
  	  $.openWindow({
  	  		url:'org?act=sellDept',
  	  		title:"选择销售部门",
  	  		width:"400"
  	 });
    });
    // 选择客户经理
    $("#projectApprove-index-form #custManagerNameQuery-hook").on("click",function(){
  	  	$.openWindow({
  	  		url:'user?act=custManager',
  	  		title:"选择客户经理",
  	  		width:"700"
  	 	 });
  	});
    var cols=[
         	[
           	  
         	      {align: 'center', title: '项目信息', colspan: 16},
         	      {align: 'center', title: '项目预算', colspan: 4},
         	      {field:'allIncomming', title:'收入合计', rowspan: 2},
         	      {align: 'center',field: 'lastYearRevenue', title: '历年收入', rowspan: 2},
         	      {align: 'center',field: 'signContractDate', title: '合同签订日期', rowspan: 2},
         	      {align: 'center',field: 'isSignedContract', title: '是否签订', rowspan: 2},
         	      {align: 'center',field: 'workLoadConfirm', title: '工作量确认', rowspan: 2},
         	      {align: 'center',fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
         	      
          		],
         	    [
         	    	  {type: 'checkbox', fixed: 'left'},
         	 	      {field:'wbs', title:'WBS编号', width:150},
         	  	      {field:'projectName', title:'项目名称'},
         	  	      {field:'custName', title:'客户名称', width:150},
         	  	      {field:'buildDeptName', title:'实施部门',sort: true, width:130},
         	  	      {field:'buildManagerName', title:'实施负责人' ,sort: true, width:130},
         	  	      {field:'projectManagerName', title:'项目经理' ,sort: true, width:130},
         	  	      {field:'sellDeptName', title:'销售部门', width:130},
         	  	      {field:'sellManagerName', title:'销售负责人', width:130},
         	  	      {field:'custManagerName', title:'客户经理', width:130},
         	  	      {field:'createProjectTime', title:'立项时间'},
         	  	      {field:'finishProjectTime', title:'结项时间'},
         	  	      {field:'projectStatus', title:'项目状态'},
         	  	      {field:'projectType', title:'项目类型'},
         	  	      {field:'isImportant', title:'是否重点项目'},
         	    	  {field:'predictContractAmount', title:'合同金额'},
         	    	  
         	    	  {field:'predictProfitRate', title:'预估利润率'},
         	    	  {field:'predictProfitMount', title:'预估利润'},
         	    	  {field:'predictWorkload', title:'预估工作量'},
         	    	  {field:'yearSalary', title:'预估当年收入'},
         	    	  
         	    	
         	    	  
         	    ]
           ]
  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#productTable',
	 //   url:'/vote/pmreviewinfo/selectBidReview',
	    method:'post',
		where:{
			//queryStr:JSON.stringify(getParam())
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '立项审批数据表',
	    cols: cols,
	    cellMinWidth:'90',
	    
	    page: true,
	    data:[{wbs:1,projectName:"项目1"},{wbs:2,projectName:"项目2"}]
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event == "review"){
	    	// 评审
	    	showFromTable('review',data.wbs);
	    }else if(obj.event == "view"){
	    	showFromTable('view',data.wbs);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#tenderReviewQuery").click(function(){
		 var queryParams=$("#projectApprove-index-form").serializeObject();
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
			 // url: '/vote/pmreviewinfo/selectBidReview',
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
			  	    title: '立项审批数据表',
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
	   if(isEdit == "review"){
			var url='review?act=review&id='+id;
	    	var	title="立项评审";
	    	_width=600;
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看";
	    	_width="90%";
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