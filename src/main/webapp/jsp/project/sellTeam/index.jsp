<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
</style>
<div class="custom-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>销售团队管理</legend>
	</fieldset>
	<form class="layui-form" id="customer-query-form" method="POST" action="">
	   <div class="layui-form-item">
	   
		  	<div class="layui-inline">
		      <label class="layui-form-label">团队名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="groupName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">团队编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="groupCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">是否有效：</label>
		      <div class="layui-input-inline">
		        <select name="isUseful" lay-verify="required" lay-filter="" class="form-control">
		        	 ${isUseful.ewTypeHtml }
		        </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">开始时间：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="startTime" id="startTime" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">结束时间：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="endTime" id="endTime"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		  
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			     
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			  </div>
		   </div>
	   </div>
	</form>
	<table class="layui-hide" id="customTable" lay-filter="custom"></table>
	
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
		    elem: "#startTime",
		    theme: 'molv',
		    type: 'datetime'
	 });
	 laydate.render({
		    elem: "#endTime",
		    theme: 'molv',
		    type: 'datetime'
	 });

  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#customTable',
	    //url:'custom.json',
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '销售团队数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'groupCode', title:'团队编号',fixed: 'left', width:110, sort: true},
	  	      {field:'groupName', title:'团队名称', width:230},
	  	      {field:'ownerOrgId', title:'所属机构编号', width:230},
	  	      {field:'orgName', title:'所属机构名称', width:230},
	  	      {field:'createTime', title:'创建时间'},
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
	    ]],
	    cellMinWidth:'90',
	    data:[
              {"groupCode":1,"groupName":"销售team1","ownerOrgId":"销售一部"},
              {"groupCode":1,"groupName":"销售team1","ownerOrgId":"销售一部"},
              {"groupCode":1,"groupName":"销售team1","ownerOrgId":"销售一部"}
              
       		],
	    page: true
	  });
	/*
	* 监听头工具栏事件 
	*/
	table.on('toolbar(custom)', function(obj){
	  var checkStatus = table.checkStatus(obj.config.companyCode)
	  ,data = checkStatus.data; //获取选中的数据
	  switch(obj.event){
	   
	    case 'deleteData': //删除操作
	      if(data.length === 0){
	        layer.msg('请选择一行');
	      } else {
	    	  console.log(checkStatus.data)
	        layer.alert('删除：'+ checkStatus.data);
	      }
	    break;
	  };
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
	    	showFromTable('edit',data.groupCode);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.groupCode);
	    }
	  });
	/*
	* 公司查询按钮
	*/
	 $("#customQuery").click(function(){
		 var queryParams=$("#customer-query-form").serializeObject();
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
			  	    elem: '#customTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-250',
			  	    title: '销售数据表',
			  	    cols: [[
			  	    	  {type: 'checkbox', fixed: 'left'},
				  	      {field:'groupCode', title:'团队编号',fixed: 'left', width:110, sort: true},
				  	      {field:'groupName', title:'团队名称', width:230},
				  	      {field:'ownerOrgId', title:'所属机构编号', width:230},
				  	      {field:'orgName', title:'所属机构名称', width:230},
				  	      {field:'createTime', title:'创建时间'},
				  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
			  	    ]],
			  	    cellMinWidth:'90',
			  	    /*data:[
						{"groupCode":44,"groupName":"销售team1","ownerOrgId":"销售一部"},
						{"groupCode":55,"groupName":"销售team1","ownerOrgId":"销售一部"},
						{"groupCode":66,"groupName":"销售team1","ownerOrgId":"销售一部"}
			       	],*/
			  	    page: true
			  	  	});},
			  dataType: "json"
			});
		//var queryParams=$("#customer-query-form").serialize();
		/* table.reload('customer-table',{
			url:'/vote/bmcustomerinfo/list',
			page:{
				curr:1 //从第一页开始
			},
			method:'post',
			where:{
				queryStr:queryParams
			},
			done:function(res){
				console.log(res)
			}
			
		}) */
		
	}); 
	
	/*
	* 新增团队
	*/
	$("#add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增销售团队",
	  		width:"700"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改销售团队信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看销售团队信息";
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:"700"
	  	})
		
	}
	
});
var testData=null;
</script>
</body>
</html>