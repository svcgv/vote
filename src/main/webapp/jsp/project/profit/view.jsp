<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#project-edit-hook .layui-form-label {
    width: 180px!important;
}
.project-form-wrapper .customer-list{
	word-wrap:normal;
	word-break:keep-all;
	padding:5px;
	display: inline-block;
}
.project-form-wrapper .layui-icon-close-fill{
	position:relative;
	top:1px;
}
.project-form-wrapper .milepost-list-wrapper .layui-input-inline{
	margin-right:20px;
}
</style>
<div id="project-edit-hook" class="project-form-wrapper" style="margin-top:10px;">
	<form class="layui-form" id='projectEditForm' action="" lay-filter="form-detail">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		  <legend style="font-weight:bold;">项目信息</legend>
		</fieldset>
		  <div class="layui-form-item"  id="project-form-edit">
		  	
		  
		    
		   
		    <div class="layui-inline">
			      <label class="layui-form-label">利润中心编号：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="costId" id="costId" value="${profit.profitId}" readonly='true' autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' id="orgId" name="orgId" />
			      </div>
			     
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">所属机构：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="orgName"  value="${profit.orgName}" autocomplete="off" class="layui-input form-control">
				       <inpu type="text" style='display:none' name="orgId" value="${profit.orgId}" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    
		   	
		     
		   
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="customGroup-edit-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script>
$(function(){
	layui.use(['layer', 'form','laydate','upload'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate,
	  	  upload=layui.upload;
		
		//日期
		   laydate.render({
				    elem: "#createProjectTime2-hook",
				    theme: 'molv',
				    type: 'date'
			 });
			laydate.render({
				    elem: "#finishProjectTime2-hook",
				    theme: 'molv',
				    type: 'date'
			 });
			laydate.render({
			    elem: "#signContractDate-form",
			    theme: 'molv',
			    type: 'date'
		 	});
			laydate.render({
			    elem: "#requirement-datepick-hook",
			    theme: 'molv',
			    type: 'date'
		 	});
			laydate.render({
			    elem: "#design-datepick-hook",
			    theme: 'molv',
			    type: 'date'
		 	});
			laydate.render({
			    elem: "#devlopment-datepick-hook",
			    theme: 'molv',
			    type: 'date'
		 	});
			laydate.render({
			    elem: "#test-datepick-hook",
			    theme: 'molv',
			    type: 'date'
		 	});
			laydate.render({
			    elem: "#online-datepick-hook",
			    theme: 'molv',
			    type: 'date'
		 	});
			laydate.render({
			    elem: "#check-datepick-hook",
			    theme: 'molv',
			    type: 'date'
		 	});
			laydate.render({
			    elem: "#production-datepick-hook",
			    theme: 'molv',
			    type: 'date'
		 	});
	
	// form 表单手动渲染
// 		var data = JSON.parse('${Cost}')
// 		console.log(data);
// 	 for (var property in data) {
// 		 console.log(property,data[property]);
// 		 	$("#projectEditForm input[name='"+property+"']").val(data[property]);
// 		 	console.log(property,data[property]);
// 		 	if(property=='remark'){
// 		 		$("#projectEditForm textarea[name='"+property+"']").val(data[property]);
// 		 	}
// 		 }
// 	   form.render();
	  //选择投标
	  $("#project-edit-hook #bidNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'tender?act=tenderEdit',
		  		title:"选择投标",
		  		width:"700"
		 });
		  
	  });
	// 项目类型切换 类型为整包项目 显示里程碑
	 form.on('select(projectTypeFilter)', function(data){
	  if(data.value == "00"){
		  var select= 'dd[lay-value=00]'; // 里程碑
		  $('.project-form-wrapper input[name="projectType"]').siblings("div.layui-form-select").find('dl').find(select).click();
		  $("#project-edit-hook .milepost-list-wrapper").show();
	  }else{
		  $("#project-edit-hook .milepost-list-wrapper").hide();
	  }
  
   });
	
	//选择实施部门
	  $("#project-edit-hook #buildDeptNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'org?act=buildDeptEdit',
		  		title:"选择实施部门",
		  		width:"400"
		 });
		  
	  });
	  // 选择实施负责人
	  $("#project-edit-hook #buildManagerNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'user?act=buildManagerEdit',
		  		title:"选择实施负责人",
		  		width:"700"
		 });
	  });
	  
	  
	  // 选择项目经理
	  $("#project-edit-hook #projectManagerNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'user?act=projectManagerEdit',
		  		title:"项目经理",
		  		width:"700"
		 });
	  });
	  
	  // 选择销售部门
	  $("#project-edit-hook #sellDeptNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'org?act=sellDeptEdit',
		  		title:"选择销售部门",
		  		width:"400"
		 });
	  });
	  
	  // 选择销售负责人
	  $("#project-edit-hook #sellManagerNameQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'user?act=sellManagerEdit',
		  		title:"选择销售负责人",
		  		width:"700"
		 	 });
		});
	  
	  // 选择客户经理
	  $("#project-edit-hook #custManagerNameQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'user?act=custManagerEdit',
		  		title:"选择客户经理",
		  		width:"700"
		 	 });
		});
	  
	  // 选择WBS编号
	  $("#project-edit-hook #wbsQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'project?act=wbsEdit',
		  		title:"选择WBS编号",
		  		width:"700"
		 	 });
		});
	  // 选择客户名称
	  $("#project-edit-hook #custNameQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'customer?act=custEdit',
		  		title:"选择客户名称",
		  		width:"700"
		 	 });
		});
	
		//选择成本中心编号
	   $("#project-edit-hook #costCodeQuery-hook").on("click",function(){
		   
		   var buildDeptId = $("#project-edit-hook input[name='buildDeptId']").val();
		   
			if($.trim(buildDeptId) ==''){
				layer.msg("请选择实施部门");
				return false;
			}
		   
			$.openWindow({
				url:'costCode?act=costCodeEdit&orgId='+buildDeptId,
		  		title:"选择成本中心编号",
		  		width:"700"
		 	 });
		});
	
	var win=$("#project-edit-hook").getWindow();
	// 保存
	$("#project-edit-hook #customGroup-edit-hook").click(function(){
		
		var projectName=$("#project-edit-hook input[name='projectName']").val();
		if($.trim(projectName) ==''){
			layer.msg("请输入项目名称");
			return false;
		}
		
		var formDatas=$("#project-edit-hook form").serializeObject();
		 var newparam = {}
		 for(var o in formDatas){
			 if(formDatas[o]){
				 newparam[o] = formDatas[o]
			 }
		 }
		 
		$.ajax({
			type:'POST',
			url: '/vote/pmprojectinfo/update',
			 data: JSON.stringify(newparam),
			 contentType:'application/json',
			success:function(res){
				layer.msg("修改成功",{icon:1});
				win.close();
			},
			error:function(){
				layer.msg("修改失败",{icon:5});
				win.close();
			},
			 dataType: "json"
		});
		return false;
	});
	
	// 关闭
	$("#project-edit-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
	})
});

</script>