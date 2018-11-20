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
		         <input type="text" name="groupManagerCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline">
			   <label class="layui-form-label">项目群名称：</label>
			   <div class="layui-input-inline">
			       <input type="text" name="projectGroupName"  autocomplete="off" class="layui-input form-control">
			    </div>
		    </div>
		 </div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label" >创建日期(开始)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="groupCreateTimeStart" id="groupCreateTimeStart" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">创建日期(结束)：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="groupCreateTimeEnd" id="groupCreateTimeEnd"  autocomplete="off" class="layui-input form-control hasDatepicker">
			       </div>
		    </div>
		    
		    	<div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">是否有效：</label>
		       <div class="layui-input-inline">
		          <select name="isDelete" lay-verify="required" lay-filter="projectTypeFilter" class="form-control">
		        	 ${isUseful.ewTypeHtml}
		          </select>
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
  
	 var queryParams=$("#projectGroup-index-form").serializeObject();
	
  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#projectGroupIndexTable',
	    url: '/vote/pmprojectgroupinfo/list',
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
	    title: '项目群数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'groupManagerCode', title:'项目群编号', width:130},
	  	      {field:'projectGroupName', title:'项目群名称', width:130},
	  	      {field:'groupCreatorName', title:'创建人' },
	  	      {field:'groupCreateTime', title:'创建日期' },
	  	    	{field:'isDelete', title:'是否有效',templet:function(d){
	              	if(d.isDelete == "00"){
	            		return "有效";
	            	}else if(d.isDelete == "01"){
	            		return "无效";
	            	}
	            }},
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
	    ]],
	    cellMinWidth:'120',
	    data:testData,
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
	        var param = {}
            param.isDelete = '01'
            param.projectGroupId = data.projectGroupId
            $.ajax({
                type: 'POST',
                url: '/vote/pmprojectgroupinfo/update',
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
	    	showFromTable('edit',data.projectGroupId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.projectGroupId);
	    }else if(obj.event == "projectReview"){
	    	// 评审
	    	showFromTable('review',data.projectGroupId);
	    }else if(obj.event == "setMoney"){
	    	// 评审
	    	showFromTable('setMoney',data.projectGroupId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $(".projectGroup-info-wrapper #projectGroupIndexQuery").click(function(){
		 var queryParams=$("#projectGroup-index-form").serializeObject();
	
			table.reload('customer-table',{
				 url: '/vote/pmprojectgroupinfo/list',
				page:{
					curr:1 //从第一页开始
				},
				 method:'post',
					where:{
	                    queryStr: JSON.stringify(queryParams)
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