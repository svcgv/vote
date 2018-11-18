<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.project-project-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="project-project-wrapper">
	<form class="layui-form" id="project-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">项目编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="projectId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >项目名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control" >
	      </div>
	    </div>
	    
 	   <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="userQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		  </div>
	   </div>
	    
	  </div>
	</form>
	<table class="layui-hide" id="projectTable" lay-filter="custom" style="overflow:hidden;"></table>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">
$(function(){
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
	  
  
	var queryParams=$("#project-query-form").serializeObject();
  // table render
  table.render({
	    elem: '#projectTable',
	    url: '/vote/pmprojectinfo/list',
	    method:'post',
		where:{
			queryStr:JSON.stringify(queryParams)
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    height:'260',
	    width:"690",
	    title: '项目表信息',
	    cols: [[
	      {type: 'radio' },
	      {field:'projectId', title:'项目编号', sort: true},
	      {field:'projectName', title:'项目名称'}
	    ]],
	    page: true
	  });
  var act="${act}";
	console.log(act,'project')
	// 保存 事件
	var win=$(".project-project-wrapper").getWindow();
	$(".project-project-wrapper").on("click","#save-hook",function(){
		// 遍历选中的radio
		$(".project-project-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var projectId=$(this).children("td").eq(1).text();
				var projectName=$(this).children("td").eq(2).text();
				if(act == "wbs"){
					$(".project-info-wrapper input[name='projectName']").val(projectId);
					$(".project-info-wrapper input[name='wbs']").val(projectName);
			 	}else if(act == "wbsForm"){
					$("#project-form-hook input[name='wbs']").val(projectId);
					$("#project-form-hook input[name='projectName']").val(projectName);
			 	}
				
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".project-project-wrapper").getWindow();
	$(".project-project-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#project-query-form #userQuery").click(function(){
		
		var queryParams=$("#project-query-form").serialize();
		 var newparam = {}
		 for(var o in queryParams){
			 if(queryParams[o]){
				 newparam[o] = queryParams[o]
			 }
		 }
		console.log(queryParams)
		table.reload('projectTable',{
			 url: '/vote/pmprojectinfo/list',
			page:{
				curr:1 //从第一页开始
			},
			 method:'post',
				where:{
					queryStr:JSON.stringify(newparam)
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
	
});
	
});



</script>



