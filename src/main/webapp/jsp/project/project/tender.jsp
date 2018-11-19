<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.project-tender-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="project-tender-wrapper">
	<form class="layui-form" id="user-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">投标编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="bidId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >投标项目名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="bidName"  autocomplete="off" class="layui-input form-control" >
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
	<table class="layui-hide" id="tenderTable" lay-filter="tenderFilter" style="overflow:hidden;"></table>
	
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
  var queryParams=$("#user-query-form").serializeObject();	  
  // table render
  table.render({
	    elem: '#tenderTable',
	    id:'tenderTable',
	    url:'/vote/pmprojectinfo/listTender',
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
	    title: '投标数据表',
	    cols: [[
	      {type: 'radio'},
	      {field:'bidId', title:'投标编号', width:130,templet:function(d){
	    	  var jsonStr = JSON.stringify(d);
	    	  return '<div class="jsonData" dataStr='+jsonStr+'>'+d.bidId+'</div>'
	      } },
  	      {field:'bidName', title:'投标项目名称', width:130},
  	      {field:'custCnName', title:'客户名称'}
	    ]],
	    page: true
	  });
	
	// 保存 事件
	var act="${act}";// 区分是index页 form页 赋值问题
	var win=$(".project-tender-wrapper").getWindow();
	$(".project-tender-wrapper").on("click","#save-hook",function(){
		// 遍历选中的radio
		$(".project-tender-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var dataStr=$(this).children("td").eq(1).find(".jsonData").attr("dataStr");
				var obj=JSON.parse(dataStr);
				
				console.log(obj)
				if(act == "tenderForm"){
					// 给form 表单反写值
					//投标名称
					$("#project-form-hook input[name='bidName']").val(obj.bidName);
					$("#project-form-hook input[name='bidId']").val(obj.bidId);
					
					$("#project-form-hook input[name='custName']").val(obj.custCnName);
					$("#project-form-hook input[name='custId']").val(obj.custId);
					
					$("#project-form-hook input[name='buildDeptName']").val(obj.constructionDeptName);
					$("#project-form-hook input[name='buildDeptId']").val(obj.constructionDeptId);
					
					$("#project-form-hook input[name='buildManagerName']").val(obj.constructionDeptManagerName);
					$("#project-form-hook input[name='buildManagerId']").val(obj.constructionDeptManagerId);
					
					$("#project-form-hook input[name='sellDeptName']").val(obj.sellDeptName);
					$("#project-form-hook input[name='sellDeptId']").val(obj.sellDeptId);
					
					$("#project-form-hook input[name='sellManagerName']").val(obj.sellDeptManagerName);
					$("#project-form-hook input[name='sellManagerId']").val(obj.sellDeptManagerId);
					
					// 投标里的字段 和项目里的 请确认  我不是很清楚 反写的可能有误
					$("#project-form-hook input[name='predictContractAmount']").val(obj.predictAmount);
					$("#project-form-hook input[name='profitMount']").val(obj.predictProfitRate);
			 		
			 	}else if(act == "tenderEdit"){
			 	// 给form 表单反写值
					//投标名称
					$("#project-edit-hook input[name='bidName']").val(obj.bidName);
					$("#project-edit-hook input[name='bidId']").val(obj.bidId);
					
					$("#project-edit-hook input[name='custName']").val(obj.custCnName);
					$("#project-edit-hook input[name='custId']").val(obj.custId);
					
					$("#project-edit-hook input[name='buildDeptName']").val(obj.constructionDeptName);
					$("#project-edit-hook input[name='buildDeptId']").val(obj.constructionDeptId);
					
					$("#project-edit-hook input[name='buildManagerName']").val(obj.constructionDeptManagerName);
					$("#project-edit-hook input[name='buildManagerId']").val(obj.constructionDeptManagerId);
					
					$("#project-edit-hook input[name='sellDeptName']").val(obj.sellDeptName);
					$("#project-edit-hook input[name='sellDeptId']").val(obj.sellDeptId);
					
					$("#project-edit-hook input[name='sellManagerName']").val(obj.sellDeptManagerName);
					$("#project-edit-hook input[name='sellManagerId']").val(obj.sellDeptManagerId);
					
					// 投标里的字段 和项目里的 请确认  我不是很清楚 反写的可能有误
					$("#project-edit-hook input[name='predictContractAmount']").val(obj.predictAmount);
					$("#project-edit-hook input[name='profitMount']").val(obj.predictProfitRate);
			 	}
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".project-tender-wrapper").getWindow();
	$(".project-tender-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#user-query-form #userQuery").click(function(){
		  var queryParams=$("#user-query-form").serializeObject();	 
		  var newparam = {}
			 for(var o in queryParams){
				 if(queryParams[o]){
					 newparam[o] = queryParams[o]
				 }
			 }
			console.log(queryParams)
			table.reload('tenderTable',{
				 url: '/vote/pmprojectinfo/listTender',
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



