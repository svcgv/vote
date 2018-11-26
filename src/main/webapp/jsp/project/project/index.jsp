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
	   
	    	<div class="layui-inline" style="padding-right:55px;">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="wbs" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     
		     <div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
	   
		  	<div class="layui-inline">
		      <label class="layui-form-label">实施部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="buildDeptName"  readonly="true" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="buildDeptId" />
		      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="buildDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">实施负责人：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="buildManagerName"  readonly="true" autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none'  name="buildManagerId" />
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
				       <input type="text" name="sellDeptName"  readonly="true" autocomplete="off" class="layui-input form-control">
				       <input type="text" style='display:none'  name="sellDeptId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售负责人：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="sellManagerName" readonly="true" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none'  name="sellManagerId" />
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
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" readonly="true" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none'  name="custId" />     
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline" style="margin-right:40px;">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
		        	      ${projectType.ewTypeHtml}
		          </select>
		      </div>
		    </div>
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">项目状态：</label>
		       <div class="layui-input-inline">
		          <select name="projectStatus" lay-verify="required" lay-filter="" class="form-control">
		      		 	 ${projectStatus.ewTypeHtml}
		          </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间(开始)：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectStartTime" readonly="true" id="createProjectStartTime-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		        <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间(结束)：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectEndTime" readonly="true" id="createProjectEndTime-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间(开始)：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectStartTime" readonly="true" id="finishProjectStartTime-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间(结束)：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectEndTime" readonly="true" id="finishProjectEndTime-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
		    </div>
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook" power ="116401"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
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
  <a class="layui-btn layui-btn-xs" power ="116402" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" power ="116403" lay-event="projectReview">提交评审</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" power ="116404" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" power ="116405" lay-event="del">删除</a>
</script>

<script type="text/javascript">

var testData=[];
var col=[
	[
  	  
	      {align: 'center', title: '项目信息', colspan: 16},
	      {align: 'center', title: '项目预算', colspan: 6},
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
	  	      {field:'projectStatus', title:'项目状态',templet:function(d){
              	if(d.projectStatus == "00"){
            		return "进行中";
            	}else if(d.projectStatus == "01"){
            		return "结项";
            	}else if(d.projectStatus == "02"){
            		return "待验收";
            	}else if(d.projectStatus == "03"){
            		return "关闭";
            	}
            }},
	  	      {field:'projectType', title:'项目类型',templet:function(d){
              	if(d.projectType == "00"){
            		return "整包项目";
            	}else if(d.projectType == "01"){
            		return "人力项目";
            	}else if(d.projectType == "02"){
            		return "订单项目";
            	}else if(d.projectType == "03"){
            		return "内部研发项目";
            	}
            }},
	  	      {field:'isImportant', title:'是否重点项目',templet:function(d){
              	if(d.isImportant == "00"){
            		return "是";
            	}else if(d.isImportant == "01"){
            		return "否";
            	}
            }},
	    	  {field:'predictContractAmount', title:'合同金额'},
	    	  {field:'predictProfitRate', title:'预估利润率'},
	    	  {field:'predictProfitMount', title:'预估利润'},
	    	  {field:'predictWorkload', title:'预估工作量'},
	    	  {field:'yearSalary', title:'预估当年收入'},
	    	  {field:'allIncomming', title:'收入合计'}
	    	  
	    ]
  ]
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
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
	  
	  var buildDeptId = $("#project-index-form input[name='buildDeptId']").val();
	   
		if($.trim(buildDeptId) ==''){
			layer.msg("请选择实施部门");
			return false;
		}
	  
	  $.openWindow({
	  		url:'user?act=buildManager&orgNo='+buildDeptId+'&roleCode=BUILD_DEPT_NAME',
	  		title:"选择实施负责人",
	  		width:"700"
	 });
  });
  // 选择项目经理
  $(".project-info-wrapper #projectManagerNameQuery-hook").click(function(){
	  
	  var buildDeptId = $("#project-index-form input[name='buildDeptId']").val();
	   
		if($.trim(buildDeptId) ==''){
			layer.msg("请选择实施部门");
			return false;
		}
	  
	  $.openWindow({
	  		url:'user?act=projectManager&orgNo='+buildDeptId+'&roleCode=PROJECT_MANGER',
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
	  
	  var sellDeptId = $("#project-index-form input[name='sellDeptId']").val();
	   
		if($.trim(sellDeptId) ==''){
			layer.msg("请选择销售部门");
			return false;
		}
	  
	  	$.openWindow({
	  		url:'user?act=sellManager&orgNo='+sellDeptId+'&roleCode=SELL_DEPT_MANAGER',
	  		title:"选择销售负责人",
	  		width:"700"
	 	 });
	});
  
  // 选择客户经理
  $(".project-info-wrapper #custManagerNameQuery-hook").on("click",function(){
	  
	  var sellDeptId = $("#project-index-form input[name='sellDeptId']").val();
	   
		if($.trim(sellDeptId) ==''){
			layer.msg("请选择销售部门");
			return false;
		}
	  
	  	$.openWindow({
	  		url:'user?act=custManager&orgNo='+sellDeptId+'&roleCode=CUST_MANAGER',
	  		title:"选择客户经理",
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
  
  
  
  var queryParams=$("#project-index-form").serializeObject();

  // table render
  table.render({
	  	id:"customer-table",
	  elem: '#projectIndexTable',
	  url: '/vote/pmprojectinfo/list',
	   method:'post',
		where:{
           queryStr: JSON.stringify(queryParams)
       },
       contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-200',
	    title: '项目数据表',
	  cols: col,
	   cellMinWidth:'120',
	   data:testData,
	   page: true    ,
       done:function(){
           $.buttonAuthority();
       }
	 });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	    	
	      	if(data.approveStatus != '00'){
	    		layer.msg("当前已发起过评审，不可删除",{icon:5});
	    		return
	    	}
	    	
	      layer.confirm('确认删除行么', function(index){
	        obj.del();
	        var param = {}
            param.isDelete = '01'
            param.projectId = data.projectId
            $.ajax({
                type: 'POST',
                url: '/vote/pmprojectinfo/updateDelete',
                data: JSON.stringify(param),
                contentType: 'application/json',
                success: function (res) {
                    console.log(res)
                },
                dataType: "json"
            })

            layer.close(index);
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.projectId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.projectId);
	    }else if(obj.event == "projectReview"){
	    	if(data.approveStatus!=='00'){
	    		layer.msg("当前已发起过评审，请勿重复发起",{icon:5});
	    		return;
	    	}
	    	
	    	 var queryParams=$("#project-index-form").serializeObject();
	    	
	    	 $.ajax({
	                type:'POST',
	                url:'/vote/pmprojectinfo/submit',
	                contentType:'application/json',
	                data: JSON.stringify(data),
	                success:function(res){
	                	if(res.code!=0){
	                		 layer.msg(res.msg,{icon:3});
	                		 return
	                	}
	                    layer.msg("发起评审成功",{icon:1});
	                    table.reload('customer-table',{
	        				url:'/vote/pmprojectinfo/list',
	        				page:{
	        					curr:1 //从第一页开始
	        				},
	        			    method:'post',
	        				where:{
	        					queryStr:JSON.stringify(queryParams)
	        				},
	        				contentType: 'application/json',
	        			    response: {
	        			    	dataName: 'page'
	        			    }

	        			})
	                },
	                error:function(){
	                    layer.msg("发起评审失败",{icon:5});
	                },
	                dataType: "json"
	            });
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#customQuery").click(function(){
		 var queryParams=$("#project-index-form").serializeObject();
		 
			table.reload('customer-table',{
				 url: '/vote/pmprojectinfo/list',
				page:{
					curr:1 //从第一页开始
				},
				 method:'post',
					where:{
						queryStr:JSON.stringify(queryParams)
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
		}else if(isEdit == "projectReview"){
			var url='review?act=projectReview&id='+id;
	    	var	title="项目立项审批";
	    	_width="400px";
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:_width
	  	})
		
	}
	
});

</script>
</body>
</html>