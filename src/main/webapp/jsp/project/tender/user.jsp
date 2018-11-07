<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.tender-form-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="tender-form-wrapper">
	<form class="layui-form" id="user-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">用户ID：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="usrId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >用户名：</label>
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
	  
  // table render
  table.render({
	    elem: '#userTable',
	    id:'user-table',
	    url:'/vote/queryusrinfo/list',
	    method:'post',
		where:{
			queryStr:JSON.stringify(getParam())
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
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
	
	
	// 保存 事件
	var act="${act}";// 区分是index页 form页 赋值问题
	var win=$(".tender-form-wrapper").getWindow();
  	var getExitUser=$("#chosed-user-hook");
	$(".tender-form-wrapper").on("click","#save-hook",function(){
		var ret=[];
		getExitUser.children(".customer-list").each(function(){
			var sapCode2=$(this).children(".customerItem").attr("userId");
			ret.push(sapCode2)
		});
		// 遍历选中的radio
		$(".tender-form-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var userId=$(this).children("td").eq(1).text();
				var userName=$(this).children("td").eq(2).text();
				if(act == "index"){
					$("#tender-index-form input[name='custManagerName']").val(userName);
					$("#tender-index-form input[name='developmentManagerId']").val(userId);
			 		
			 	}else if(act =="add"){ //编辑 修改 页面
			 		$("#tender-addForm-hook input[name='custManagerName']").val(userName);
					$("#tender-addForm-hook input[name='developmentManagerId']").val(userId);
			 	}else if(act =="addDept"){ // 交付部门负责人页面
			 		$("#tender-addForm-hook input[name='constructionDeptManagerName']").val(userName);
					$("#tender-addForm-hook input[name='constructionDeptManagerId']").val(userId);
			 	}else if(act =="addSaleDept"){ //销售部门负责人 页面
			 		$("#tender-addForm-hook input[name='sellDeptManagerName']").val(userName);
					$("#tender-addForm-hook input[name='sellDeptManagerId']").val(userId);
			 	}else if(act =="reviewPay"){ // 投标 评审
			 		$("#review-query-form input[name='payDeptName']").val(userName);
					$("#review-query-form input[name='payDeptId']").val(userId);
			 	}else if(act =="reviewSell"){// 投标 评审
			 		$("#review-query-form input[name='sellDeptName']").val(userName);
					$("#review-query-form input[name='sellDeptId']").val(userId);
			 	}
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".tender-form-wrapper").getWindow();
	$(".tender-form-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#user-query-form #userQuery").click(function(){
		$.ajax({
			  type: 'POST',
			  url: '/vote/queryusrinfo/list',
			  data: JSON.stringify(getParam()),
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



