<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#project-form-hook .layui-form-label {
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
<div id="cost-form-hook" class="cost-form-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		  <legend style="font-weight:bold;">成本中心信息</legend>
		</fieldset>
		  <div class="layui-form-item">
		  	
		  	
		    
		   
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">成本中心编号：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="costId"  autocomplete="off" class="layui-input form-control">
			         
			      </div>
			     
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">所属机构：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="orgName"  autocomplete="off" class="layui-input form-control">
				        <input type="text" name="orgId" style='display:none' autocomplete="off" class="layui-input form-control">
				       
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="costOrgNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		   
		   	
		 
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="customGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
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
		
		
	// form 表单手动渲染
	  form.render();
	  //选择投标
	 
	  // 选择销售部门
	  $("#cost-form-hook #costOrgNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'org?act=costForm',
		  		title:"选择销售部门",
		  		width:"400"
		 });
	  });
	  
	
	
	
	var win=$("#cost-form-hook").getWindow();
	// 保存
	$("#cost-form-hook #customGroup-add-hook").click(function(){
		
		var formDatas=$("#cost-form-hook form").serializeObject();
		 var newparam = {}
		 for(var o in formDatas){
			 if(formDatas[o]){
				 newparam[o] = formDatas[o]
			 }
		 }
		 
		$.ajax({
			type:'POST',
			url: '/vote/costInfo/save',
			 data: JSON.stringify(newparam),
			 contentType:'application/json',
			success:function(res){
				layer.msg("新增成功",{icon:1});
				win.close();
				location.reload();
			},
			error:function(){
				layer.msg("新增失败",{icon:5});
				win.close();
			},
			 dataType: "json"
		});
		return false;
	});
	
	// 关闭
	$("#cost-form-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
	})
});

</script>