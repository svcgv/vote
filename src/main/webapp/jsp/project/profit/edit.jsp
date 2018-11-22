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
<div id="profit-edit-hook" class="profit-form-wrapper" style="margin-top:10px;">
	<form class="layui-form" id='projectEditForm' action="" lay-filter="form-detail">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		  <legend style="font-weight:bold;">利润中心信息</legend>
		</fieldset>
		  <div class="layui-form-item"  id="project-form-edit">
		  	
		  
		    
		   
		    <div class="layui-inline">
			      <label class="layui-form-label">利润中心编号：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="profitId" id="profitId" value="${profit.profitId}" readonly='true' autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' id="orgId" name="orgId" />
			      </div>
			     
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">所属机构：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="orgName"  value="${profit.orgName}"  autocomplete="off" class="layui-input form-control">
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
	
	  // 选择销售部门
	  $("#profit-edit-hook #sellDeptNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'org?act=edit',
		  		title:"选择销售部门",
		  		width:"400"
		 });
	  });
	  
	 
	
	var win=$("#profit-edit-hook").getWindow();
	// 保存
	$("#profit-edit-hook #customGroup-edit-hook").click(function(){
		
		
		
		var formDatas=$("#profit-edit-hook form").serializeObject();
		 var newparam = {}
		 for(var o in formDatas){
			 if(formDatas[o]){
				 newparam[o] = formDatas[o]
			 }
		 }
		 
		$.ajax({
			type:'POST',
			url: '/vote/profitInfo/update',
			 data: JSON.stringify(newparam),
			 contentType:'application/json',
			success:function(res){
				layer.msg("修改成功",{icon:1});
				win.close();
				location.reload();
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
	$("#profit-edit-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
	})
});

</script>