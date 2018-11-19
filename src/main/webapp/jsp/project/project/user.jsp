<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.project-user-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="project-user-wrapper">
	<form class="layui-form" id="user-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">用户编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="usrId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >用户名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="usrName"  autocomplete="off" class="layui-input form-control" >
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
	<table class="layui-hide" id="userTable" lay-filter="custom" style="overflow:hidden;"></table>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">

var url1='/vote/queryusrinfo/queryUserByRoleCodeUnderOrgNo'
var url2='/vote/queryusrinfo/list'
var act="${act}";// 区分是index页 form页 赋值问题
var orgNo="${orgNo}";
var roleCode="${roleCode}"
$(function(){
	function getParam(){
		var queryParams=$("#user-query-form").serializeObject();
		 var newParam = {}
		  for(var i in queryParams){
			  if(queryParams[i]){
				  newParam[i] = queryParams[i]
			  }
		  }
		  return newParam
	}
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
  var par = getParam()
  if(orgNo){
		par.orgNo=orgNo
	}
	if(roleCode){
		par.roleCode=roleCode
	}
  // table render
  table.render({
	    elem: '#userTable',
	    id:'user-table',
	
	    height:'260',
	    width:"690",
	    title: '用数据表',
	    cols: [[
	      {type: 'radio' },
	      {field:'usrId', title:'用户ID', sort: true},
	      {field:'usrName', title:'用户名'}
	    ]],
	    page: true
	  });
	
  
  $.ajax({
	  type: 'POST',
	  url: orgNo?url1:url2,
	  data: JSON.stringify(par),
	  contentType:'application/json',
	  success: function(res){
	      console.log(res)
	      testData=res.page

	      table.render({
	  	    elem: '#userTable',
	  	    //url:'custom.json',
	  	    height:'260',
	  	    width:"690",
		    title: '用户数据表',
		    cols: [[
		      {type: 'radio' },
		      {field:'usrId', title:'用户编号', sort: true},
		      {field:'usrName', title:'用户名'}
		    ]],
	  	    data:testData,
	  	    page: true
	  	  });
	      
	      
	      
	      ;},
	  dataType: "json"
	})
	
	// 保存 事件

	var win=$(".project-user-wrapper").getWindow();
	$(".project-user-wrapper").on("click","#save-hook",function(){
		// 遍历选中的radio
		$(".project-user-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var userId=$(this).children("td").eq(1).text();
				var userName=$(this).children("td").eq(2).text();
				if(act == "buildManager"){
					// index
					$("#project-index-form input[name='buildManagerName']").val(userName);
					$("#project-index-form input[name='buildManagerId']").val(userId);
			 	}else if(act =="sellManager"){ 
			 		// index
			 		$("#project-index-form input[name='sellManagerName']").val(userName);
					$("#project-index-form input[name='sellManagerId']").val(userId);
			 	}else if(act =="buildManagerForm"){
			 		// form
			 		$("#project-form-hook input[name='buildManagerName']").val(userName);
					$("#project-form-hook input[name='buildManagerId']").val(userId);
			 	}else if(act =="sellManagerForm"){ 
			 		// form
			 		$("#project-form-hook input[name='sellManagerName']").val(userName);
					$("#project-form-hook input[name='sellManagerId']").val(userId);
			 	}else if(act =="reviewPay"){ // 投标 评审
			 		//$("#review-query-form input[name='payDeptName']").val(userName);
					//$("#review-query-form input[name='payDeptId']").val(userId);
			 	}else if(act =="reviewSell"){// 投标 评审
			 		//$("#review-query-form input[name='sellDeptName']").val(userName);
					//$("#review-query-form input[name='sellDeptId']").val(userId);
			 	}else if(act == "projectManager"){
					// index
					$("#project-index-form input[name='projectManagerName']").val(userName);
					$("#project-index-form input[name='projectManagerId']").val(userId);
			 	}else if(act =="custManager"){ 
			 		// index
			 		$("#project-index-form input[name='custManagerName']").val(userName);
					$("#project-index-form input[name='custManagerId']").val(userId);
			 	}else if(act =="projectManagerForm"){
			 		// form
			 		$("#project-form-hook input[name='projectManagerName']").val(userName);
					$("#project-form-hook input[name='projectManagerId']").val(userId);
			 	}else if(act =="custManagerForm"){ 
			 		// form
			 		$("#project-form-hook input[name='custManagerName']").val(userName);
					$("#project-form-hook input[name='custManagerId']").val(userId);
			 	}else if(act =="buildManagerEdit"){ 
			 		$("#project-edit-hook input[name='buildManagerName']").val(userName);
					$("#project-edit-hook input[name='buildManagerId']").val(userId);
			 	}else if(act =="projectManagerEdit"){ 
			 		$("#project-edit-hook input[name='projectManagerName']").val(userName);
					$("#project-edit-hook input[name='projectManagerId']").val(userId);
			 	}else if(act =="sellManagerEdit"){ 
			 		$("#project-edit-hook input[name='sellManagerName']").val(userName);
					$("#project-edit-hook input[name='sellManagerId']").val(userId);
			 	}else if(act =="custManagerEdit"){ 
			 		$("#project-edit-hook input[name='custManagerName']").val(userName);
					$("#project-edit-hook input[name='custManagerId']").val(userId);
			 	}
				
				
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".project-user-wrapper").getWindow();
	$(".project-user-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#user-query-form #userQuery").click(function(){
		var par = getParam()
		
		if(orgNo){
			par.orgNo=orgNo
		}
		if(roleCode){
			par.roleCode=roleCode
		}
		
		$.ajax({
			  type: 'POST',
			  url: orgNo?url1:url2,
			  data: JSON.stringify(par),
			  contentType:'application/json',
			  success: function(res){
			      console.log(res)
			      testData=res.page

			      table.render({
			  	    elem: '#userTable',
			  	    //url:'custom.json',
			  	    height:'260',
			  	    width:"690",
				    title: '用户数据表',
				    cols: [[
				      {type: 'radio' },
				      {field:'usrId', title:'用户ID', sort: true},
				      {field:'usrName', title:'用户名'}
				    ]],
			  	    data:testData,
			  	    page: true
			  	  });
			      
			      
			      
			      ;},
			  dataType: "json"
			})
	});
	
});
});

</script>
