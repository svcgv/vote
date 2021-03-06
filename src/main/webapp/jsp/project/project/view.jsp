<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#project-view-hook .layui-form-label {
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
<div id="project-view-hook" class="project-form-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		  <legend style="font-weight:bold;">项目信息</legend>
		</fieldset>
		  <div class="layui-form-item">
		  	
		  	<div class="layui-inline">
		      <label class="layui-form-label">投标名称：</label>
		       <div class="layui-input-inline">
		         <input readonly='true'type="text" name="bidName" readonly='true' autocomplete="off" class="layui-input form-control">
		         <input readonly='true'type="text" style='display:none' name="bidId">
		      </div>
		    </div> 
		    
		      <div class="layui-inline" style="padding-right:55px;">
		      <label class="layui-form-label">WBS编号：</label>
		       <div class="layui-input-inline">
	         		<input readonly='true'type="text" name="wbs" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		         <input readonly='true'type="text" name=projectName  autocomplete="off" class="layui-input form-control">
		          <input readonly='true'type="text" style='display:none' name="projectId">
		      </div>
		    </div>
		    
		    <div class="layui-inline"style="margin-right:64px;">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input readonly='true'type="text" name="custName"  readonly='true' autocomplete="off" class="layui-input form-control">
		     	  <input readonly='true'type="text" style='display:none' name="custSapCode" />
		      </div>
	      	</div>
		    
		     <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select  style="width: 200px; background-color: #EEEEEE;" disabled="disabled" name="projectType" lay-verify="required" lay-filter="" class="form-control">
		         		${projectType.ewTypeHtml}
		          </select>
		      </div>
		    </div>
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">项目状态：</label>
		       <div class="layui-input-inline">
		          <select disabled="disabled" name="projectStatus" lay-verify="required" lay-filter="" class="form-control">
		        	 	${projectStatus.ewTypeHtml}
		          </select>
		      </div>
		    </div>
		  
		      <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">审批状态：</label>
		       <div class="layui-input-inline">
		          <select disabled="disabled" name="approveStatus" lay-verify="required" lay-filter="" class="form-control">
		        	 ${approveStatus.ewTypeHtml}
		        </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
		       <label class="layui-form-label">立项时间：</label>
		       <div class="layui-input-inline">
		          <input readonly='true'type="text" name="createProjectTime" readonly='true' id="createProjectTime2-hook" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="padding-right:55px;">
			      <label class="layui-form-label">结项时间：</label>
			       <div class="layui-input-inline">
			          <input readonly='true'type="text" name="finishProjectTime"  readonly='true' id="finishProjectTime2-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
		    </div>
		    
		   <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">是否重点项目：</label>
		       <div class="layui-input-inline">
		          <select disabled="disabled"  name="isImportant" lay-verify="required" lay-filter="" class="form-control">
		        	 ${isImportant.ewTypeHtml}
		          </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">实施部门：</label>
		       <div class="layui-input-inline">
		         <input readonly='true'type="text" name="buildDeptName"  readonly='true' autocomplete="off" class="layui-input form-control">
		         <input readonly='true'type="text" style='display:none' name="buildDeptId" />
		      </div>
		    </div>
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">实施负责人：</label>
			       <div class="layui-input-inline">
			         <input readonly='true'type="text" name="buildManagerName"  readonly='true' autocomplete="off" class="layui-input form-control">
			         <input readonly='true'type="text" style='display:none' name="buildManagerId" />
			      </div>
			  </div>
			  
			  <div class="layui-inline">
			      <label class="layui-form-label">项目经理：</label>
			       <div class="layui-input-inline">
			         <input readonly='true' type="text" name="projectManagerName"  readonly='true' autocomplete="off" class="layui-input form-control">
			         <input readonly='true' type="text" style='display:none' name="projectManagerId" />
			      </div>
		    </div>
		    
		     <div class="layui-inline"style="margin-right:64px;">
			      <label class="layui-form-label">销售部门：</label>
			      <div class="layui-input-inline">
				       <input readonly='true'type="text" name="sellDeptName"  readonly='true' autocomplete="off" class="layui-input form-control">
				       <inpu type="text" style='display:none' name="sellDeptId" />
			      </div>
			   </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">销售负责人：</label>
		       <div class="layui-input-inline">
		         <input readonly='true'type="text" name="sellManagerName"  readonly='true' autocomplete="off" class="layui-input form-control">
		         <input readonly='true'type="text" style='display:none' name="sellManagerId" />
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		         <input readonly='true' type="text" name="custManagerName"  readonly='true' autocomplete="off" class="layui-input form-control">
		         <input readonly='true' type="text" style='display:none' name="custManagerId" />
		      </div>
		    </div>
		    
		  
		    
		    <div class="layui-inline" style="margin-right:64px;">
		       <label class="layui-form-label">所属项目群：</label>
		       <div class="layui-input-inline">
		          <select  disabled="disabled"   name="belongProjectGroupId" lay-verify="required" lay-filter="" class="form-control">
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
		         <input type="number" name="predictContractAmount"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		   	 </div>
		   	 
		   	 	 <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">利润中心编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="profitCode"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   	 
	     	<div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">成本中心编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="costCode"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">税率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="taxRate"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		      
		 	    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润(不含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="netSalary" readonly='true'  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		       <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估当年收入(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="yearSalary"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		       <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润率(%)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictProfitRate" readonly='true'  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估利润(含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictProfitMount" readonly='true'  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估工作量(人/月)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictWorkload"  readonly='true'  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		 	    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估人均收入(元/人月)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictCapitaSalary"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估人均成本(元/人月)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictCapitaCost" readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
				<label class="layui-form-label">人力费用(元)：</label>
				  <div class="layui-input-inline">
				     <input type="number" name="employeeCost"  readonly='true'  autocomplete="off" class="layui-input form-control">
				  </div>
			</div>
		    
		 	 <div class="layui-inline" style="margin-right:64px;">
			 	<label class="layui-form-label">差旅费用(元)：</label>
				 <div class="layui-input-inline">
				     <input type="number" name="businessTripCost" readonly='true' autocomplete="off" class="layui-input form-control">
				 </div>
			</div>
		   
		     <div class="layui-inline"  style="margin-right:64px;">
				  <label class="layui-form-label">其他费用(元)：</label>
				    <div class="layui-input-inline">
				       <input type="number" name="otherCost"  readonly='true' autocomplete="off" class="layui-input form-control">
				    </div>
			</div>
		   	
		   	 <div class="layui-inline"  style="margin-right:64px;">
			      <label class="layui-form-label">计提-外购人力(元)：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="accruedChargesWorkers" readonly='true' autocomplete="off" class="layui-input form-control">
			     </div>
			   </div>
			    
	   		<div class="layui-inline"  style="margin-right:64px;">
			      <label class="layui-form-label">计提-外购产品及服务(元)：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="accruedChargesProducts" readonly='true' autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
				    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估总成本(含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="budgetWithTax"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline" style="margin-right:64px;">
		      <label class="layui-form-label">预估总成本(不含税)(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="budgetNoTax"  readonly='true' autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   </div>
		   	
		   			   	
		 <div class="layui-form-item clearfix" style="margin-bottom:0px;margin-left:10px;">
			<div style="float:left;width: 140px;">
				<p class="layui-form-label">合同引用列表：</p>
			</div>
			<div style="float:left;width:900px;">
				<table class="layui-hide" id="contractTable-chosed" lay-filter="tableFilter1" style="overflow:hidden;"></table>
			</div>
	    </div>
		   	
		   	
		<div class="layui-form-item clearfix" style="margin-bottom:0px;margin-left:10px;">
			<div style="float:left;width: 140px;">
				<p class="layui-form-label">产品引用列表：</p>
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
					      	<input readonly='true'type="text" name="requirement" id="requirement-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="requirementremarks" class="layui-input form-control" />
					      </div>
					    </div>
				  </li>
				  
				  <li class="layui-timeline-item">
				    <i class="layui-icon layui-timeline-axis"></i>
				    <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">设计</h3>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="design" id="design-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="designRemarks" class="layui-input form-control" />
					      </div>
				    </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">开发</h3>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="devlopment" id="devlopment-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="devlopmentRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">测试</h3>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="test" id="test-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="testRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon  layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">上线</h3>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="online" id="online-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="onlineRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	 <i class="layui-icon layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">验收</h3>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="check" id="check-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="checkRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				  
				   <li class="layui-timeline-item">
				   	<i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis"></i>
				   	 <div class="layui-timeline-content layui-text">
					      <h3 class="layui-timeline-title">运维</h3>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="production" id="production-datepick-hook" class="layui-input form-control hasDatepicker" />
					      </div>
					      <div class="layui-input-inline">
					      	<input readonly='true'type="text" name="productionRemarks" class="layui-input form-control" />
					      </div>
				   	 </div>
				  </li>
				</ul> 
		   </div>	
		   
		      <div class="file-hook" style="width:95%;margin:0 auto;">
	          <!-- 反写已上传的数据 -->
		      <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr><th>文件名</th>
				        <th>大小</th>
				        <th>文件类型</th>
				       
				        <th>状态</th>
				        <th> 上传人</th>
				        <th>上传时间</th>
				        <th>操作</th>
				      </tr></thead>
				      <tbody id="wosFileList">
				      <c:forEach items="${file}" var="fileInfo" >
				      	<tr class="edit-wosUploaded">
					      	<td>${fileInfo.fileUploadName}</td>
					      	<td>${fileInfo.fileSize}</td>
					      	<td>
					      		<c:if test="${fileInfo.fileBusinessType=='00' }">
									招标文件
								</c:if>
								<c:if test="${fileInfo.fileBusinessType=='01' }">
									客户需求文件
								</c:if>
								<c:if test="${fileInfo.fileBusinessType=='02' }">
									内部评审文件
								</c:if>
							</td>
					      	<td>已上传</td>
					      	 <td>${fileInfo.usrName}</td>
						      <td>${fileInfo.createTime}</td>
					      	<td><a href="/vote/pmfile/download?id=${fileInfo.fileId}">下载</a></td>
					      	
				      	</tr>
				      	</c:forEach>
				      </tbody>
				    </table>
				  </div>
		  	 </div>
		   	
		   <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
		 	 <legend style="font-weight:bold;">上报收入</legend>
		  </fieldset>
		  
		  <div class="layui-form-item" >
		  		<div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">收入合计：</label>
			       <div class="layui-input-inline">
			         <input readonly='true'type="text" name="allIncomming"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		  		<div class="layui-inline" >
			      <label class="layui-form-label">超报收入：</label>
			       <div class="layui-input-inline">
			         <input readonly='true'type="text" name="overFlowReportIncomming"  autocomplete="off" class="layui-input form-control">
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
			         <input readonly='true'type="text" name="lastYearRevenue"  autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
		  		<div class="layui-inline" style="margin-right:64px;">
			      <label class="layui-form-label">合同签订日期：</label>
			       <div class="layui-input-inline">
			         <input readonly='true'type="text" name="signContractDate" id="signContractDate-form"  autocomplete="off" class="layui-input form-control hasDatepicker">
			      </div>
			    </div>
			    <div class="layui-inline" style="margin-right:64px;">
		      	   <label class="layui-form-label">是否签订：</label>
			       <div class="layui-input-inline">
				        <select  disabled="disabled"name="isSignedContract" lay-verify="required" lay-filter="" class="form-control">
				        	 <option value="">请选择</option>
				        	 <option value="01" selected>是</option>
				        	 <option value="02">否</option>
			          	</select>
			      </div>
			    </div>
			    <div class="layui-inline" style="margin-right:64px;">
		      	   <label class="layui-form-label">工作量确认：</label>
			       <div class="layui-input-inline">
				        <select  disabled="disabled"name="workLoadConfirm" lay-verify="required" lay-filter="" class="form-control">
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
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script>
var chosedContractProject=[];
var chosedProductProject=[];
var chosedLayTable=null;
$(function(){
	layui.use(['layer', 'form','laydate','upload','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate,
	  	  upload=layui.upload;
		  chosedLayTable=layui.table;
		//日期
		  
	
	// form 表单手动渲染
		var data = JSON.parse('${formObj}')
		console.log(data);
	 for (var property in data) {
		 	$("#project-view-hook input[name='"+property+"']").val(data[property]);
		 	if(property=='remark'){
		 		$("#project-addForm-hook textarea[name='"+property+"']").val(data[property]);
		 	}
		 }
	   form.render();
	   
	   var param = {"projectId": data.projectId};
	   $.ajax({
           type: 'POST',
           url: '/vote/pmprojectinfo/listContract',
           data: JSON.stringify(param),
           contentType: 'application/json',
           success: function (res) {
        	 chosedContractProject=res.page;
	      chosedLayTable.render({
	          id:"table-chosedProject1",
	          elem: '#contractTable-chosed',
	          height:'250',
	          title: '合同数据信息',
	          cols: [[
	              {field:'contractCode', title:'合同编号'},
	              {field:'contractName', title:'合同名称'}
	          ]],
	          cellMinWidth:'90',
	          data:chosedContractProject,
	          page: true
	      });
           },
           dataType: "json"
       });
	   
	   $.ajax({
           type: 'POST',
           url: '/vote/pmprojectinfo/listProduct',
           data: JSON.stringify(param),
           contentType: 'application/json',
           success: function (res) {
        	  chosedProductProject=res.page;
      chosedLayTable.render({
          id:"table-chosedProject2",
          elem: '#productTable-chosed',
          height:'250',
          title: '产品数据信息',
          cols: [[
              {field:'productCode', title:'产品编号'},
              {field:'productName', title:'产品名称'}
          ]],
          cellMinWidth:'90',
          data:chosedProductProject,
          page: true
      });   
      },
      dataType: "json"
       });

	
	// 关闭
	$("#project-view-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
	})
});

</script>