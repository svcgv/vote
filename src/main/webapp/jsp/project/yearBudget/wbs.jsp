<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
.budget-cust-wrapper .layui-form-label{width:80px!important;}
</style>
<div style="margin-top:10px;" class="budget-cust-wrapper">
	<form class="layui-form" id="customer-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">WBS编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="wbsCode"  autocomplete="off" class="layui-input form-control">
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
		    <button type="button" class="layui-btn layui-btn-sm" id="wbsQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
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
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
	  
  // table render
  table.render({
	    elem: '#userTable',
	    //url:'custom.json',
	    height:'260',
	    width:"690",
	    title: '客户数据表',
	    cols: [[
	      {type: 'radio' },
	      {field:'wbsCode', title:'wbs编号', sort: true},
	      {field:'projectName', title:'项目名称'}
	    ]],
	    data:[
		  		{
		  			"wbsCode":"1000",
					"projectName":"交行项目"
					
				},
				{
		  			"wbsCode":"1001",
					"projectName":"内网项目"
					
				},
				{
		  			"wbsCode":"1002",
					"projectName":"方维项目"
					
				},
				{
		  			"wbsCode":"1003",
					"projectName":"兴业银行项目"
					
				}
				
			],
	    page: true
	  });
	
	
	// 保存 事件
	var act="${act}";// 区分是index页 form页 赋值问题
	var index ="${index}";
	var YIndex ="${YIndex}";
	var win=$(".budget-cust-wrapper").getWindow();
	$(".budget-cust-wrapper").on("click","#save-hook",function(){
		// 遍历选中的radio
		$(".budget-cust-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var wbsCode=$(this).children("td").eq(1).text();
				var projectName=$(this).children("td").eq(2).text();
				if(act == "indexSearch"){
					$(".budget-info-wrapper input[name='projectName']").val(projectName);
					$(".budget-info-wrapper input[name='wbsCode']").val(wbsCode);
				}else if(act == "addWBS" || act == "addProjectName"){
			 		// form 2 
			 		console.log(index,YIndex,'index')
			 		$(".budget-wrapper").find("tbody tr").eq(YIndex).find(".project-list").eq(0).children(".item").eq(index).find("input[name='wbsCode']").val(wbsCode);
			 		$(".budget-wrapper").find("tbody tr").eq(YIndex).find(".project-list").eq(1).children(".item").eq(index).find("input[name='projectName']").val(projectName);
			 	}
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".budget-cust-wrapper").getWindow();
	$(".budget-cust-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#customer-query-form #wbsQuery").click(function(){
		
		var queryParams=$("#customer-query-form").serialize();
		console.log(queryParams)
		table.reload('customerGroup-table',{
			url:'form',
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
			
		})
	});
	
});
	
});



</script>



