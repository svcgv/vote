<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.projectApprove-info-wrapper .layui-form-label{width:100px!important;}
</style>
<div class="projectApprove-info-wrapper">
	<table class="layui-hide" id="productTable" lay-filter="custom"></table>
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="review">评审</a>
</script>

<script type="text/javascript">
var testData=[];
var cols=[
 	[
   	  
 	      {align: 'center', title: '项目信息', colspan: 17},
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
	 	  	   {field:'approveStatus', title:'审批状态',templet:function(d){
	            if(d.approveStatus == "00"){
	          		return "待申请";
	          	}else if(d.approveStatus == "01"){
	          		return "交付部门负责人审批";
	          	}else if(d.approveStatus == "02"){
	          		return "销售部门负责人审批";
	          	}else if(d.approveStatus == "03"){
	          		return "总经理审批";
	          	}else if(d.approveStatus == "04"){
	          		return "审批通过";
	          	}
	          }},
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
 	    	  {field:'predictWorkload', title:'预估工作量（人/月）'},
 	    	  {field:'yearSalary', title:'预估当年收入'},
 	    	 {field:'allIncomming', title:'收入合计'}
 	    ]
   ]
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table;
 
    var queryParams=$("#projectApprove-index-form").serializeObject();
    
  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#productTable',
		  url: '/vote/pmreviewinfo/selectProjectReview',
	    method:'post',
		where:{
	           queryStr: JSON.stringify(queryParams)
	       },
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '立项评审数据表',
	    cols: cols,
		   cellMinWidth:'120',
		   data:testData,
	    page: true
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event == "review"){
	    	// 评审
	    	showFromTable('review',data.projectId);
	    }
	  });
	
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
	   if(isEdit == "review"){
			var url='review?act=review&id='+id;
	    	var	title="立项评审";
		}
	   
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:'90%'
	  	})
	}
});
</script>
</body>
</html>