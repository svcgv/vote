<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style type="text/css">
.layui-form-label{width:120px!important;}
</style>
<div class="projectGroup-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>项目群管理</legend>
	</fieldset>
	<form class="layui-form" id="projectGroup-index-form" method="POST" action="">
	   <div class="layui-form-item" style="margin-bottom:0px;">
		  	<div class="layui-inline">
		      <label class="layui-form-label">项目群编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="projectGroupId"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">项目群名称：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="projectGroupName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
			       </div>
			       <button type="button"  class="layui-btn layui-btn-sm" id="projectGroupNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		 </div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label" >创建时间(开始)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="groupCreateTimeStart" id="groupCreateTimeStart" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">创建时间(结束)：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="groupCreateTimeEnd" id="groupCreateTimeEnd"  autocomplete="off" class="layui-input form-control hasDatepicker">
			       </div>
		    </div>
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:30px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="projectGroupIndexQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			  </div>
		   </div>
	   </div>
	</form>
	
	<table class="layui-hide" id="projectGroupIndexTable" lay-filter="projectGroup"></table>
	
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

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  
  //日期
   laydate.render({
		    elem: "#groupCreateTimeStart",
		    theme: 'molv'
	 });
	laydate.render({
		    elem: "#groupCreateTimeEnd",
		    theme: 'molv'
	 });
  
  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#projectGroupIndexTable',
	    //url:'custom.json',
	    toolbar: '#toolbarDemo',
	    height:'full-200',
	    title: '项目群数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'projectGroupId', title:'项目群编号', width:130},
	  	      {field:'projectGroupName', title:'项目群名称', width:130},
	  	      {field:'groupCreatorName', title:'项目群创建人' },
	  	      {field:'groupCreateTime', title:'项目群创建时间' },
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
	    ]],
	    cellMinWidth:'120',
	    data:[
				{
					projectGroupId:"001",
					projectGroupName:"项目群一",
					groupCreatorName:"陈军",
					groupCreateTime:"2018-11-11"
				},
				{
					projectGroupId:"002",
					projectGroupName:"项目群一",
					groupCreatorName:"陈军",
					groupCreateTime:"2018-11-11"
				},
				{
					projectGroupId:"003",
					projectGroupName:"项目群一",
					groupCreatorName:"陈军",
					groupCreateTime:"2018-11-11"
				},
	    	],
	    page: true
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(projectGroup)', function(obj){
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
	    }else if(obj.event == "projectReview"){
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
	 $(".projectGroup-info-wrapper #projectGroupIndexQuery").click(function(){
		 var queryParams=$("#projectGroup-index-form").serializeObject();
		 var newparam = {}
 		 for(var o in queryParams){
 			 if(queryParams[o]){
 				 newparam[o] = queryParams[o]
 			 }
 		 }
		 
		 $.ajax({
			  type: 'POST',
			  url: '/vote/pmprojectgroupinfo/list',
			  data: JSON.stringify(newparam),
			  contentType:'application/json',
			  success: function(res){

				 testData=res.page
			      table.render({
			  	  	id:"customer-table",
			  	    elem: '#projectGroupIndexTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-200',
			  	    title: '项目群数据表',
			  	    cols: [[
						  {type: 'checkbox', fixed: 'left'},
				  	      {field:'projectGroupId', title:'项目群编号', width:130},
				  	      {field:'projectGroupName', title:'项目群名称', width:130},
				  	      {field:'groupCreatorName', title:'项目群创建人' },
				  	      {field:'groupCreateTime', title:'项目群创建时间' },
				  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
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
	$(".projectGroup-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增项目",
	  		width:"600px"
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
	
});
var testData=[];
</script>
</body>
</html>