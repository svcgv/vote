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
		         <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control">
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
			      <label class="layui-form-label">项目经理：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="projectManagerName"  autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' name="projectManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="projectManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
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
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="custManagerName"  autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="custManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="custManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
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
		          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
		        	    ${projectType.ewTypeHtml}
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
		    
		   	<div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">是否重点项目：</label>
		       <div class="layui-input-inline">
		          <select name="isImportant" lay-verify="required" lay-filter="" class="form-control">
		        	 ${isImportant.ewTypeHtml}
		          </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">所属项目群：</label>
		       <div class="layui-input-inline">
		          <select name="belongProjectGroup" lay-verify="required" lay-filter="" class="form-control">
		        	 <option value="">请选择</option>
		          </select>
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
		      <label class="layui-form-label">利润中心编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="profitCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   	 
	     	<div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">成本中心编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="costCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		      
		      <button type="button"  class="layui-btn layui-btn-sm" id="costCodeQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">税率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="taxRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润(不含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="netSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		      <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估当年收入(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="yearSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictProfitRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润(含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictProfitMount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估工作量(人月)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictWorkload"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		  
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估人均收入(元/人月)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictCapitaSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估人均成本(元/人月)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictCapitaCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
				 <label class="layui-form-label">人力费用(元)：</label>
				    <div class="layui-input-inline">
				      <input type="text" name="employeeCost"  autocomplete="off" class="layui-input form-control">
				    </div>
			</div>
				    
				    	    <div class="layui-inline" style="margin-right:64px;">
				      <label class="layui-form-label">差旅费用(元)：</label>
				       <div class="layui-input-inline">
				         <input type="text" name="businessTripCost"  autocomplete="off" class="layui-input form-control">
				      </div>
				    </div>
		    
		    
		        <div class="layui-inline"  style="margin-right:64px;">
				      <label class="layui-form-label">其他费用(元)：</label>
				       <div class="layui-input-inline">
				         <input type="text" name="otherCost"  autocomplete="off" class="layui-input form-control">
				      </div>
				    </div>
				    
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
				    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估总成本(含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="budgetWithTax"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估总成本(不含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="budgetNoTax"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   	</div>
		   	
		  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		 	 <legend style="font-weight:bold;">合同信息</legend>
		  </fieldset>
		   	
		   <div class="milepost-list-wrapper" <c:if test="${projectTypeSelected != '00' }"> style="display:none;"</c:if>>
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
				    type: 'date'
			 });
			laydate.render({
				    elem: "#finishProjectTime2-hook",
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
	  form.render();
	})
});

</script>