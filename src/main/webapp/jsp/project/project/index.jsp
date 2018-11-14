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
		         <input type="text" name="buildDeptName" readonly="true" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="buildDeptId" />
		      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="buildDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">实施负责人：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="buildManagerName" readonly="true" autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' name="buildManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="buildManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">项目经理：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="projectManagerName" readonly="true" autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' name="projectManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="projectManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">销售部门：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="sellDeptName" readonly="true" autocomplete="off" class="layui-input form-control">
				       <inpu type="text" style='display:none' name="sellDeptId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售负责人：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="sellManagerName" readonly="true" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="sellManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="sellManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		   <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="custManagerName" readonly="true" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="custManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="custManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="wbs" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     
		     <div class="layui-inline" style="padding-right:55px;">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="custSapCode" />     
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline" style="margin-right:40px;">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		        	 ${projectType.ewTypeHtml }
		          </select>
		      </div>
		    </div>
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">项目状态：</label>
		       <div class="layui-input-inline">
		          <select name="state" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		        	 ${state.ewTypeHtml }
		          </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间(开始时间)：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectStartTime" id="createProjectStartTime-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		        <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间(结束时间)：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectEndTime" id="createProjectEndTime-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间(开始时间)：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectStartTime" id="finishProjectStartTime-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间(结束时间)：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectEndTime" id="finishProjectEndTime-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">是否有效：</label>
		       <div class="layui-input-inline">
		          <select name="isDelete" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		        	 ${isUseful.ewTypeHtml }
		          </select>
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
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript">
var col=[
	[
  	  
	      {align: 'center', title: '项目信息', colspan: 16},
	      {align: 'center', title: '项目预算', colspan: 6},
	      {align: 'center', title: '上报收入', colspan: 2},
	      {align: 'center',field: 'lastYearRevenue', title: '历年收入', rowspan: 2},
	      {align: 'center',field: 'signContractDate', title: '合同签订日期', rowspan: 2},
	      {align: 'center',field: 'isSignedContract', title: '是否签订', rowspan: 2},
	      {align: 'center',field: 'workLoadConfirm', title: '工作量确认', rowspan: 2},
	      {align: 'center',fixed: 'right', title:'操作', toolbar: '#barDemo', width:230}
	      
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
	  	      {field:'currentYearFollow', title:'是否重点项目'},
	  	      {field:'isSignedContract', title:'是否签订'},
	  	      {field:'state', title:'项目状态'},
	  	      {field:'projectType', title:'项目类型'},
	    	  {field:'predictContractAmount', title:'合同金额'},
	    	  {field:'profitRate', title:'利润率'},
	    	  {field:'profitMount', title:'利润'},
	    	  {field:'workLoad', title:'工作量'},
	    	  {field:'currendYearIncomming', title:'本年可报收入'},
	    	  {field:'currentYearGrossProfit', title:'本年毛利'},
	    	  
	    	  {field:'allIncomming', title:'收入合计'},
	    	  
	    ]
  ]
//一般直接写在一个js文件中
var lay = layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  
  //日期
   laydate.render({
		    elem: "#createProjectStartTime-hook",
		    theme: 'molv',
		    type: 'date'
	 });
  
   laydate.render({
	    elem: "#createProjectEndTime-hook",
	    theme: 'molv',
	    type: 'date'
});

	laydate.render({
		    elem: "#finishProjectStartTime-hook",
		    theme: 'molv',
		    type: 'date'
	 });
	
	laydate.render({
	    elem: "#finishProjectEndTime-hook",
	    theme: 'molv',
	    type: 'date'
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
  // 选择项目经理
  $(".project-info-wrapper #projectManagerNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'user?act=projectManager',
	  		title:"选择项目经理",
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
  
  // 选择客户经理
  $(".project-info-wrapper #custManagerNameQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=custManager',
	  		title:"选择客户经理",
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
  $(".project-info-wrapper #custNameQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'customer?act=cust',
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
	    cols: col,
	    cellMinWidth:'120',
	    data:[
	    	
	    	],
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
	        del(data.projectId)
	        layer.close(index);
	        table.reload('customer-table',{
	        	
	        });
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.projectId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.projectId);
	    }else if(obj.event == "tenderReview"){
	    	// 评审
	    	showFromTable('review',data.projectId);
	    }else if(obj.event == "setMoney"){
	    	// 评审
	    	showFromTable('setMoney',data.projectId);
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
			  url: '/vote/pmprojectinfo/list',
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
			  	    cols: col,
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
	  		title:"新增项目",
	  		width:"90%"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var _width='90%';
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改项目信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看项目信息";
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:_width
	  	})
		
	}
	var that =this
	rendertable(that)
	
});
var testData=[];

function rendertable(that){
	var queryParams=$("#project-index-form").serializeObject();
	 var newparam = {}
	 for(var o in queryParams){
		 if(queryParams[o]){
			 newparam[o] = queryParams[o]
		 }
	 }
	$.ajax({
			  type: 'POST',
			  url: '/vote/pmprojectinfo/list',
			  data: JSON.stringify(newparam),
			  contentType:'application/json',
			  success: function(res){

				 testData=res.page
				 that.table.render({
			  	  	id:"customer-table",
			  	    elem: '#projectIndexTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-200',
			  	    title: '投标据表',
			  	    cols: col,
			  	    cellMinWidth:'100',
			  	    data:testData,
			  	    page: true
			  	  	});
			  
			  },
			  dataType: "json"
			})
}

function del(id){
	var par = {projectId:id,isDelete:'01'}
	$.ajax({
		  type: 'POST',
		  url: '/vote/pmprojectinfo/update',
		  data: JSON.stringify(par),
		  contentType:'application/json',
		  success: function(res){

			  rendertable( lay)
		  
		  },
		  dataType: "json"
		})
}

</script>
</body>
</html>