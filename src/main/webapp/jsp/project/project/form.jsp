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
		         <input type="text" name="bidName"  readonly="true"  autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="bidId">
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="bidNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		  	
		  	<div class="layui-inline" style="padding-right:55px;">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="wbs" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		  	
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" readonly="true"  autocomplete="off" class="layui-input form-control">
		     	  <input type="text" style='display:none' name="custSapCode" />
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="projectTypeFilter" class="form-control">
		        	    ${projectType.ewTypeHtml}
		          </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="createProjectTime" id="createProjectTime2-hook" readonly="true"  autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="finishProjectTime" id="finishProjectTime2-hook"  readonly="true"  autocomplete="off" class="layui-input form-control hasDatepicker">
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
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">实施部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="buildDeptName" readonly="true"  autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="buildDeptId" />
		      </div>
		         <button type="button"  class="layui-btn layui-btn-sm" id="buildDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">实施负责人：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="buildManagerName"  readonly="true"  autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' name="buildManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="buildManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">项目经理：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="projectManagerName" readonly="true"  autocomplete="off" class="layui-input form-control">
			         <input type="text" style='display:none' name="projectManagerId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="projectManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">销售部门：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="sellDeptName"  readonly="true"  autocomplete="off" class="layui-input form-control">
				       <input type="text" style='display:none' name="sellDeptId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售负责人：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="sellManagerName"  readonly="true"  autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="sellManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="sellManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="custManagerName"  readonly="true"  autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="custManagerId" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="custManagerNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		
		    
		    <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">所属项目群：</label>
		       <div class="layui-input-inline">
		          <select name="belongProjectGroupId" lay-verify="required" lay-filter="" class="form-control">
		        	      ${projectGroup.ewTypeHtml}
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
		         <input type="number" name="predictContractAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		   	 </div>
		   	 
		   	 <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">利润中心编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="profitCode"  readonly="true"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   	 
	     	<div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">成本中心编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="costCode"  readonly="true"  autocomplete="off" class="layui-input form-control">
		      </div>
		      
		      <button type="button"  class="layui-btn layui-btn-sm" id="costCodeQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">税率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="taxRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润(不含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="netSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		      <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估当年收入(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="yearSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictProfitRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润(含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictProfitMount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估工作量(人月)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictWorkload"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估人均收入(元/人月)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictCapitaSalary"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估人均成本(元/人月)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictCapitaCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
				<label class="layui-form-label">人力费用(元)：</label>
				<div class="layui-input-inline">
				   <input type="number" name="employeeCost"  autocomplete="off" class="layui-input form-control">
				</div>
			</div>
				    
			<div class="layui-inline" style="margin-right:64px;">
				<label class="layui-form-label">差旅费用(元)：</label>
				<div class="layui-input-inline">
				     <input type="number" name="businessTripCost"  autocomplete="off" class="layui-input form-control">
				 </div>
			</div>
		    
		    <div class="layui-inline"  style="margin-right:64px;">
				  <label class="layui-form-label">其他费用(元)：</label>
				  <div class="layui-input-inline">
				      <input type="number" name="otherCost"  autocomplete="off" class="layui-input form-control">
				   </div>
			</div>
				    
			<div class="layui-inline"  style="margin-right:64px;">
			      <label class="layui-form-label">计提-外购人力(元)：</label>
			       <div class="layui-input-inline">
			         <input type="number" name="accruedChargesWorkers"  autocomplete="off" class="layui-input form-control">
			      </div>
			</div>
			
	   		<div class="layui-inline"  style="margin-right:64px;">
			    <label class="layui-form-label">计提-外购产品及服务(元)：</label>
			    <div class="layui-input-inline">
			       <input type="number" name="accruedChargesProducts"  autocomplete="off" class="layui-input form-control">
			    </div>
			</div>
				    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估总成本(含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="budgetWithTax"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估总成本(不含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="budgetNoTax"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   	</div>
		   	
		 <div class="layui-form-item clearfix" style="margin-bottom:0px;margin-left:10px;">
			<div style="float:left;width: 140px;">
				<p class="layui-form-label">合同引用列表：</p>
				<div>
					<button type="button"   class="layui-btn " id="contractQuery-hook" style="margin-right:15px;padding:0 9px;height:32px;line-height:32px;"><i class="layui-icon"></i>合同引用</button>
				</div>
			</div>
			<div style="float:left;width:900px;">
				<table class="layui-hide" id="contractTable-chosed" lay-filter="tableFilter1" style="overflow:hidden;"></table>
			</div>
	    </div>
		   	
		   	
		<div class="layui-form-item clearfix" style="margin-bottom:0px;margin-left:10px;">
			<div style="float:left;width: 140px;">
				<p class="layui-form-label">产品引用列表：</p>
				<div>
					<button type="button"   class="layui-btn " id="productQuery-hook" style="margin-right:15px;padding:0 9px;height:32px;line-height:32px;"><i class="layui-icon"></i>产品引用</button>
				</div>
			</div>
			<div style="float:left;width:900px;">
				<table class="layui-hide" id="productTable-chosed" lay-filter="tableFilter2" style="overflow:hidden;"></table>
			</div>
	    </div>
		   	
		   <div class="milepost-list-wrapper"  <c:if test="${projectTypeSelected != '00' }"> style="display:none;"</c:if>>
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
					      	<input type="text" name="test" id="test-datepick-hook" class="layui-input form-control hasDatepicker" />
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
		   
		   <div class="layui-form-item clearfix" style="width:95%;margin:10px 10px;">
		      <div class="layui-upload">
		      <div style="display:inline-block;width:100%;">
			  	<button type="button" class="layui-btn" id="wosUploads"><i class="layui-icon"></i>选择文件</button> 
			  	<button type="button" class="layui-btn" id="wosListAction">开始上传</button>
		  	  </div>
				  <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr><th>文件名</th>
				        <th>大小</th>
				        <th>文件类型</th>
				        <th>状态</th>
				         <th>操作</th>
				      </tr></thead>
				      <tbody id="wosFileList"></tbody>
				    </table>
				  </div>
			 </div> 
		  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="customGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script type="text/html" id="barFormDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
var chosedContractProject=[];
var chosedProductProject=[];
var chosedLayTable=null;
var fileIds = [];
$(function(){
	layui.use(['layer', 'form','laydate','upload','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate,
	  	  upload=layui.upload;
	     chosedLayTable=layui.table;
		
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
	
	  function getFileTableParams(){
		  var files=[]
		  var tbody = document.getElementById('wosFileList')
		  if(tbody.children){
			  for(var i = 0;i<tbody.children.length;i++){
				  var item={}
				  item.fileName = tbody.children[i].children[0].innerText
				  item.fileType = tbody.children[i].children[2].children[0].children[0].value
				  files.push(item)
			  }
		  }
		  return files;
	  }
	  
	//多文件上传
	  var demoListView = $('#wosFileList')
	  ,uploadListIns = upload.render({
		  before:function(obj){
		    	this.data={uploadType:'00',fileTypes:JSON.stringify(getFileTableParams())}
		    	console.log('before',obj)
		    },
	    elem: '#wosUploads'
	    ,url: '/vote/pmfile/upload'
	    ,accept: 'file'
	    ,multiple: true
	    ,auto: false
	    ,bindAction: '#wosListAction'
	    ,choose: function(obj){   
	    	console.log('choose',obj)
	      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	      //读取本地文件
	      obj.preview(function(index, file, result){
	        var tr = $(['<tr id="upload-'+ index +'">'
	          ,'<td>'+ file.name +'</td>'
	          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
	          ,'<td>'
	          ,' <div class="layui-input-inline">'
	         	 ,'<select name="fileBusinessType" lay-verify="required" lay-filter="" class="form-control">'
		          ,'<option value="">请选择</option>'
		        	,'<option value="00" selected>招标文件</option>'
		        	,'<option value="01">客户需求文件</option>'
		        	,'<option value="02" >内部评审文件</option>'
		        	,'</select>'
					,'</div>'
	        ,'</td>'

	        ,'<td>等待上传</td>'
	          ,'<td>'
	            ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
	            ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
	          ,'</td>'
	          
	        ,'</tr>'].join(''));
	        //单个重传
	        tr.find('.demo-reload').on('click', function(){
	          obj.upload(index, file);
	        });
	        
	        //删除
	        tr.find('.demo-delete').on('click', function(){
	          delete files[index]; //删除对应的文件
	          tr.remove();
	          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        demoListView.append(tr);
	        setTimeout(function(){
		        form.render();
	        },400)
	      });
	    }
	    ,done: function(res, index, upload){
	      if(res.code == 0){ //上传成功
	    	  fileIds.push(res.fileIds)
	        var tr = demoListView.find('tr#upload-'+ index)
	        ,tds = tr.children();
	        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
	        tds.eq(3).html(''); //清空操作
	        return delete this.files[index]; //删除文件队列已经上传成功的文件
	        
	      }
	      this.error(index, upload);
	    }
	    ,error: function(index, upload){
	      var tr = demoListView.find('tr#upload-'+ index)
	      ,tds = tr.children();
	      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	  })
	
	  //table render
      chosedLayTable.render({
          id:"table-chosedProject1",
          elem: '#contractTable-chosed',
          height:'250',
          title: '合同数据信息',
          cols: [[
              {field:'contractCode', title:'合同编号'},
              {field:'contractName', title:'合同名称'},
              {fixed: 'right', title:'操作', toolbar: '#barFormDemo', width:100}
          ]],
          cellMinWidth:'90',
          data:chosedContractProject,
          page: true
      });
	  
      chosedLayTable.on('tool(tableFilter1)', function(obj){
          var data = obj.data;
          if(obj.event === 'del'){
              layer.confirm('确认删除行么', function(index){
                  obj.del();
                  // 删除
                  for(var k in chosedContractProject){
                      if(data.contractId == chosedContractProject[k].contractId ){
                    	  chosedContractProject.splice(k,1)
                      }
                  }
                  console.log(chosedContractProject,'exit');
                  chosedLayTable.reload('table-chosedProject1',{
                      data:chosedContractProject
                  })
                  layer.close(index);

              });
          }
      });
	  
      //table render
      chosedLayTable.render({
          id:"table-chosedProject2",
          elem: '#productTable-chosed',
          height:'250',
          title: '产品数据信息',
          cols: [[
              {field:'productCode', title:'产品编号'},
              {field:'productName', title:'产品名称'},
              {fixed: 'right', title:'操作', toolbar: '#barFormDemo', width:100}
          ]],
          cellMinWidth:'90',
          data:chosedProductProject,
          page: true
      });

      
      chosedLayTable.on('tool(tableFilter2)', function(obj){
          var data = obj.data;
          if(obj.event === 'del'){
              layer.confirm('确认删除行么', function(index){
                  obj.del();
                  // 删除
                  for(var k in chosedProductProject){
                      if(data.productId == chosedProductProject[k].productId ){
                    	  chosedProductProject.splice(k,1)
                      }
                  }
                  console.log(chosedProductProject,'exit');
                  chosedLayTable.reload('table-chosedProject2',{
                      data:chosedProductProject
                  })
                  layer.close(index);

              });
          }
      });
      
      // 选择合同
      $("#project-form-hook #contractQuery-hook").on("click",function(){
    	  	$.openWindow({
    	  		url:'contract',
    	  		title:"选择合同",
    	  		width:"700"
    	 	 });
    	}); 
      
      // 选择产品
      $("#project-form-hook #productQuery-hook").on("click",function(){
    	  	$.openWindow({
    	  		url:'product',
    	  		title:"选择产品",
    	  		width:"700"
    	 	 });
    	}); 

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
		  
		   var buildDeptId = $("#project-form-hook input[name='buildDeptId']").val();
		   
			if($.trim(buildDeptId) ==''){
				layer.msg("请选择实施部门");
				return false;
			}
		  
		  $.openWindow({
		  		url:'user?act=buildManagerForm&orgNo='+buildDeptId+'&roleCode=BUILD_DEPT_NAME',
		  		title:"选择实施负责人",
		  		width:"700"
		 });
	  });
	  
	  // 选择项目经理
	  $("#project-form-hook #projectManagerNameQuery-hook").click(function(){
		  
		   var buildDeptId = $("#project-form-hook input[name='buildDeptId']").val();
		   
			if($.trim(buildDeptId) ==''){
				layer.msg("请选择实施部门");
				return false;
			}
		  
		  $.openWindow({
		  		url:'user?act=projectManagerForm&orgNo='+buildDeptId+'&roleCode=PROJECT_MANGER',
		  		title:"选择项目经理",
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
		  
		  var sellDeptId = $("#project-form-hook input[name='sellDeptId']").val();

			if($.trim(sellDeptId) ==''){
				layer.msg("请选择销售部门");
				return false;
			}
		  
		  	$.openWindow({
		  		url:'user?act=sellManagerForm&orgNo='+sellDeptId+'&roleCode=SELL_DEPT_MANAGER',
		  		title:"选择销售负责人",
		  		width:"700"
		 	 });
		});
	  
	  // 选择客户经理
	  $("#project-form-hook #custManagerNameQuery-hook").on("click",function(){
		  
		  var sellDeptId = $("#project-form-hook input[name='sellDeptId']").val();
		   
			if($.trim(sellDeptId) ==''){
				layer.msg("请选择销售部门");
				return false;
			}
		  
		  	$.openWindow({
		  		url:'user?act=custManagerForm&orgNo='+sellDeptId+'&roleCode=CUST_MANAGER',
		  		title:"选择客户经理",
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
		//选择成本中心编号
	   $("#project-form-hook #costCodeQuery-hook").on("click",function(){
		   
		   var buildDeptId = $("#project-form-hook input[name='buildDeptId']").val();
		   
			if($.trim(buildDeptId) ==''){
				layer.msg("请选择实施部门");
				return false;
			}
		   
			$.openWindow({
				url:'costCode?act=costCodeForm&orgId='+buildDeptId,
		  		title:"选择成本中心编号",
		  		width:"700"
		 	 });
		});
	
	
	var win=$("#project-form-hook").getWindow();
	// 保存
	$("#project-form-hook #customGroup-add-hook").click(function(){
		
		if(!fileIds){
			layer.msg("请上传文件");
			return false;
		}
		
		var customerGroupName=$("#project-form-hook input[name='bidName']").val();
		if($.trim(customerGroupName) ==''){
			layer.msg("请输入投标名称");
			return false;
		}

		var queryParams=$("#project-form-hook form").serializeObject();
		
		 var newParam = {}
		  for(var i in queryParams){
			  if(queryParams[i]){
				  newParam[i] = queryParams[i]
			  }
		  }
		 if(fileIds){
			 newParam.fileIds=fileIds.join(',')
		 }
	
		 if(chosedContractProject){
			 newParam.pmContractInfo = chosedContractProject
		 }
		
		 if(chosedProductProject){
			 newParam.pmProductInfo = chosedProductProject
			 
		 }
		$.ajax({
			type:'POST',
			url: '/vote/pmprojectinfo/save',
			 data: JSON.stringify(newParam),
			 contentType:'application/json',
			success:function(res){
				  location.reload();
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