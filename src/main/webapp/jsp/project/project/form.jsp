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
<div id="project-form-hook" class="project-form-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		  <legend style="font-weight:bold;">项目信息</legend>
		</fieldset>
		  <div class="layui-form-item">
		  	
		  	<div class="layui-inline">
		      <label class="layui-form-label">投标名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="bidName"  autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="bidId">
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
		         <input type="text" style='display:none' name="buildDeptId" />
		      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="buildDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">实施负责人：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="buildManagerName"  autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' name="buildManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="buildManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">销售部门：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="sellDeptName"  autocomplete="off" class="layui-input form-control">
				       <inpu type="text" style='display:none' name="sellDeptId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售负责人：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="sellManagerName"  autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="sellManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="sellManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline" style="padding-right:55px;">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="wbs" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" autocomplete="off" class="layui-input form-control">
		     	  <input type="text" style='display:none' name="custSapCode" />
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="projectTypeFilter" class="form-control">
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
		      <label class="layui-form-label">预估合同金额(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictContractAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		   	 </div>
	     	<div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">成本中心编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="costCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">税率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="taxRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估税后收入(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="netSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="profitRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="profitMount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估工作量(人月)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictWorkload"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估当年收入(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="yearSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估人均成本(元/人月)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictCapitaCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">本年可报收入(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="yearSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline"  style="margin-right:64px;">
		      <label class="layui-form-label">本年毛利(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="currentYearGrossProfit"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估总成本(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="budget"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   	</div>
		   	
		   	 <div class="layui-form-item" style="margin-bottom:0px;">
			   	 <div class="layui-inline" style="margin-right:64px;">
				      <label class="layui-form-label">人力费用(元)：</label>
				       <div class="layui-input-inline">
				         <input type="text" name="workLoad"  autocomplete="off" class="layui-input form-control">
				      </div>
				    </div>
				    
				    <div class="layui-inline" style="margin-right:64px;">
				      <label class="layui-form-label">差旅费用(元)：</label>
				       <div class="layui-input-inline">
				         <input type="text" name="currendYearIncomming"  autocomplete="off" class="layui-input form-control">
				      </div>
				    </div>
				    
				    <div class="layui-inline"  style="margin-right:64px;">
				      <label class="layui-form-label">其他费用(元)：</label>
				       <div class="layui-input-inline">
				         <input type="text" name="currentYearGrossProfit"  autocomplete="off" class="layui-input form-control">
				      </div>
				    </div>
		   	 </div>
		   	
		   	<div class="layui-form-item" style="margin-bottom:0px;">
	   			<div class="layui-inline"  style="margin-right:64px;">
			      <label class="layui-form-label">计提-人力：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="accruedChargesWorkers"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
	   			<div class="layui-inline"  style="margin-right:64px;">
			      <label class="layui-form-label">计提-产品及服务：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="accruedChargesProducts"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		   	</div>
		   	
		   <div class="milepost-list-wrapper" <c:if test="${projectType != '00' }"> style="display:none;"</c:if>>
		    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		 	 <legend style="font-weight:bold;">里程碑</legend>
		     </fieldset>
			   	<!-- 里程碑 名称 数据字典 加       项目类型为整包项目才显示里程碑-->
			   	<ul class="layui-timeline" style="margin-left:20px;">
				  <li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis"></i>
					    <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">需求</h3>
					      <div class="layui-input-inline">
					      	<input type="text" name="requirement" id="requirement-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input type="text" name="requirementremarks" class="layui-input form-control" />
					      </div>
					    </div>
				  </li>
				  
				  <li class="layui-timeline-item">
				    <i class="layui-icon layui-timeline-axis"></i>
				    <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">设计</h3>
					      <div class="layui-input-inline">
					      	<input type="text" name="design" id="design-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input type="text" name="designRemarks" class="layui-input form-control" />
					      </div>
				    </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">开发</h3>
					      <div class="layui-input-inline">
					      	<input type="text" name="devlopment" id="devlopment-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input type="text" name="devlopmentRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">测试</h3>
					      <div class="layui-input-inline">
					      	<input type="text" name="text" id="test-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input type="text" name="testRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon  layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">上线</h3>
					      <div class="layui-input-inline">
					      	<input type="text" name="online" id="online-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input type="text" name="onlineRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">验收</h3>
					      <div class="layui-input-inline">
					      	<input type="text" name="check" id="check-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input type="text" name="checkRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	<i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">运维</h3>
					      <div class="layui-input-inline">
					      	<input type="text" name="production" id="production-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input type="text" name="productionRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				</ul> 
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
			laydate.render({
			    elem: "#requirement-datepick-hook",
			    theme: 'molv',
			    type: 'datetime'
		 	});
			laydate.render({
			    elem: "#design-datepick-hook",
			    theme: 'molv',
			    type: 'datetime'
		 	});
			laydate.render({
			    elem: "#devlopment-datepick-hook",
			    theme: 'molv',
			    type: 'datetime'
		 	});
			laydate.render({
			    elem: "#test-datepick-hook",
			    theme: 'molv',
			    type: 'datetime'
		 	});
			laydate.render({
			    elem: "#online-datepick-hook",
			    theme: 'molv',
			    type: 'datetime'
		 	});
			laydate.render({
			    elem: "#check-datepick-hook",
			    theme: 'molv',
			    type: 'datetime'
		 	});
			laydate.render({
			    elem: "#production-datepick-hook",
			    theme: 'molv',
			    type: 'datetime'
		 	});
	
	// form 表单手动渲染
	  form.render();
	  //选择投标
	  $("#project-form-hook #bidNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'tender?act=tenderForm',
		  		title:"选择投标",
		  		width:"700"
		 });
		  
	  });
	// 项目类型切换 类型为整包项目 显示里程碑
	 form.on('select(projectTypeFilter)', function(data){
	  if(data.value == "00"){
		  var select= 'dd[lay-value=00]'; // 里程碑
		  $('.project-form-wrapper input[name="projectType"]').siblings("div.layui-form-select").find('dl').find(select).click();
		  $("#project-form-hook .milepost-list-wrapper").show();
	  }else{
		  $("#project-form-hook .milepost-list-wrapper").hide();
	  }
  
   });
	
	//选择实施部门
	  $("#project-form-hook #buildDeptNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'org?act=buildDeptForm',
		  		title:"选择实施部门",
		  		width:"400"
		 });
		  
	  });
	  // 选择实施负责人
	  $("#project-form-hook #buildManagerNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'user?act=buildManagerForm',
		  		title:"选择实施负责人",
		  		width:"700"
		 });
	  });
	  // 选择销售部门
	  $("#project-form-hook #sellDeptNameQuery-hook").click(function(){
		  $.openWindow({
		  		url:'org?act=sellDeptForm',
		  		title:"选择销售部门",
		  		width:"400"
		 });
	  });
	  
	  // 选择销售负责人
	  $("#project-form-hook #sellManagerNameQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'user?act=sellManagerForm',
		  		title:"选择销售负责人",
		  		width:"700"
		 	 });
		});
	  
	  // 选择WBS编号
	  $("#project-form-hook #wbsQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'project?act=wbsForm',
		  		title:"选择WBS编号",
		  		width:"700"
		 	 });
		});
	  // 选择客户名称
	  $("#project-form-hook #custNameQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'customer?act=custForm',
		  		title:"选择客户名称",
		  		width:"700"
		 	 });
		});
	
	
	
	var win=$("#project-form-hook").getWindow();
	// 保存
	$("#project-form-hook #customGroup-add-hook").click(function(){
		
		var customerGroupName=$("#project-form-hook input[name='bidName']").val();
		if($.trim(customerGroupName) ==''){
			layer.msg("请输入投标名称");
			return false;
		}
		
		var formDatas=$("#project-form-hook form").serializeObject();
		 var newparam = {}
		 for(var o in formDatas){
			 if(formDatas[o]){
				 newparam[o] = formDatas[o]
			 }
		 }
		 
		$.ajax({
			type:'POST',
			url: '/vote/pmprojectinfo/save',
			 data: JSON.stringify(newparam),
			 contentType:'application/json',
			success:function(res){
				layer.msg("新增成功",{icon:1});
				win.close();
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
	$("#project-form-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
	})
});

</script>