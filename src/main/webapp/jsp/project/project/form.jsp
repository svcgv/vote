<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#project-addForm-hook .layui-form-label {
    width: 130px!important;
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
</style>
<div id="project-addForm-hook" class="project-form-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		  <legend style="font-weight:bold;">项目信息</legend>
		</fieldset>
		  <div class="layui-form-item">
		  	
		  	<div class="layui-inline">
		      <label class="layui-form-label">投标名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="bidName"  autocomplete="off" class="layui-input form-control">
		         <input type="hidden" name="bidId">
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="bidNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		  	
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name=projectName  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">实施部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="buildDeptName"  autocomplete="off" class="layui-input form-control">
		         <input type="hidden" name="buildDeptId" />
		      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="buildDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">实施负责人：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="buildManagerName"  autocomplete="off" class="layui-input form-control">
			         <input type="hidden" name="buildManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="buildManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">销售部门：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="sellDeptName"  autocomplete="off" class="layui-input form-control">
				       <inpu type="hidden" name="sellDeptId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售负责人：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="sellManagerName"  autocomplete="off" class="layui-input form-control">
		         <input type="hidden" name="sellManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="sellManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="wbs" autocomplete="off" class="layui-input form-control">
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="wbsQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" autocomplete="off" class="layui-input form-control">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		        	 <option value="00" selected>整包项目</option>
		        	 <option value="01">人力项目</option>
		        	 <option value="02">订单项目</option>
		        	 <option value="03">内部研发项目</option>
		          </select>
		      </div>
		    </div>
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">状态：</label>
		       <div class="layui-input-inline">
		          <select name="state" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		        	 <option value="00" selected>进行中</option>
		        	 <option value="02">待验收</option>
		        	 <option value="01">结项</option>
		        	 <option value="03">关闭</option>
		          </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectTime" id="createProjectTime2-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectTime" id="finishProjectTime2-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
		    </div>
		  </div>
		  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		 	 <legend style="font-weight:bold;">项目预算</legend>
		  </fieldset>
		  <div class="layui-form-item" style="margin-bottom:0px;">
			    <div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">合同金额：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="predictContractAmount"  autocomplete="off" class="layui-input form-control">
			      </div>
			   	 </div>
		    
			    <div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">利润率：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="profitRate"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
			    
			    <div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">利润：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="profitMount"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		     </div>
		     
		     <div class="layui-form-item">
			    <div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">工作量：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="workLoad"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
			    
			    <div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">本年可报收入：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="currendYearIncomming"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
			    
			    <div class="layui-inline" >
			      <label class="layui-form-label">本年毛利：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="currentYearGrossProfit"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		   	</div>
		   	<div class="layui-form-item">
			    <div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">人力费用：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="workLoad"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
			    
			    <div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">差旅费用：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="currendYearIncomming"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
			    
			    <div class="layui-inline" >
			      <label class="layui-form-label">其他费用：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="currentYearGrossProfit"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		   	</div>
		   <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		 	 <legend style="font-weight:bold;">上报收入</legend>
		  </fieldset>
		  <div class="layui-form-item" >
		  		<div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">收入合计：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="allIncomming"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		  		<div class="layui-inline" >
			      <label class="layui-form-label">超报收入：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="overFlowReportIncomming"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		  </div>
		  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		 	 <legend style="font-weight:bold;">其他</legend>
		  </fieldset>
		  <div class="layui-form-item" >
		  		<div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">去年上报的收入：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="lastYearRevenue"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		  		<div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">合同签订日期：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="signContractDate" id="signContractDate-form"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
			    </div>
			    <div class="layui-inline" style="margin-right:64px;">
		      	   <label class="layui-form-label">是否签订：</label>
			       <div class="layui-input-inline">
				        <select name="isSignedContract" lay-verify="required" lay-filter="" class="form-control">
				        	 <option value="">请选择</option>
				        	 <option value="01" selected>是</option>
				        	 <option value="02">否</option>
			          	</select>
			      </div>
			    </div>
			    <div class="layui-inline" style="margin-right:64px;">
		      	   <label class="layui-form-label">工作量确认：</label>
			       <div class="layui-input-inline">
				        <select name="workLoadConfirm" lay-verify="required" lay-filter="" class="form-control">
				        	 <option value="">请选择</option>
				        	 <option value="01" selected>半年确认</option>
				        	 <option value="02">季度确认</option>
				        	 <option value="03">每月确认</option>
			          	</select>
			      </div>
			    </div>
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
		
		//日期
		   laydate.render({
				    elem: "#createProjectTime2-hook",
				    theme: 'molv',
				    type: 'datetime'
			 });
			laydate.render({
				    elem: "#finishProjectTime2-hook",
				    theme: 'molv',
				    type: 'datetime'
			 });
			laydate.render({
				    elem: "#signContractDate-form",
				    theme: 'molv',
				    type: 'datetime'
			 });
		
	// form 表单手动渲染
	  form.render();
	  
	var win=$("#project-addForm-hook").getWindow();
	// 保存
	$("#project-addForm-hook #customGroup-add-hook").click(function(){
		var customerGroupName=$("#project-addForm-hook input[name='bidName']").val();
		if($.trim(customerGroupName) ==''){
			layer.msg("请输入投标名称");
			return false;
		}
		
		var formDatas=$("#project-addForm-hook form").serializeObject();
		$.ajax({
			type:'POST',
			url:'save',
			data:{
				queryParams:formDatas
			},
			success:function(res){
				layer.msg("新增成功",{icon:1});
				win.close();
			},
			error:function(){
				layer.msg("新增失败",{icon:5});
				win.close();
			}
		});
		return false;
	});
	
	// 关闭
	$("#project-addForm-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
	})
});

</script>