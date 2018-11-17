<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#contract-addForm-hook .layui-form-label {
    width: 120px!important;
}
.formDetail-wrapper .customer-list{
	word-wrap:normal;
	word-break:keep-all;
	padding:5px;
	display: inline-block;
}
.formDetail-wrapper .layui-icon-close-fill{
	position:relative;
	top:1px;
}
</style>
<div id="contract-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		  <div class="layui-form-item" style="margin-bottom:0px;">
		    <div class="layui-inline">
		      <label class="layui-form-label">合同编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="contractCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">合同名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="contractName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">合同金额（元）：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="contractAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">税率（%）：</label>
		      <div class="layui-input-inline">
		       <input type="number" name="taxRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label" style="width:140px!important;">税后合同金额（元）：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="afterTaxContractAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
			  
		    <div class="layui-inline">
		      <label class="layui-form-label">是否科委认定：</label>
		       <div class="layui-input-inline">
		          <select name="isAgree">
		          	<option value="">请选择</option>
		          	<option value="01" selected>是</option>
		          	<option value="02">否</option>
		          </select>
		      </div>
		    </div>
		    		    
		    <div  class="layui-inline">
		   	   <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="sellDeptId">
		       </div>
		       <button type="button"  class="layui-btn layui-btn-sm" id="payDeptNameQuery-form" ><i class="layui-icon layui-icon-search"></i></button>
		     </div> 
		       
		      <div class="layui-inline">
			   <label class="layui-form-label" style="width:80px!important;">客户经理：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="custManagerName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
				   <input type="text" style='display:none' name="custManagerId">
			   </div>
			   <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-form"><i class="layui-icon layui-icon-search"></i></button>
		   </div>
		    <div class="layui-inline" style="margin-right:0px;">
		       <label class="layui-form-label" style="width:80px!important;">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="custId">
				   <input type="text" style='display:none' name="custSapCode">
		      </div>
	      	  <button type="button"  class="layui-btn layui-btn-sm" id="customerQuery-form" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    <div class="layui-inline" style="margin-right: 0px;">
		      <label class="layui-form-label" style="width:110px!important;">OA流程编号：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="oaFlowCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline" style="">
		      <label class="layui-form-label" style="width:80px!important;">公司代码：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="companyCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		  </div>
	     <div class="layui-form-item" style="margin-bottom:0px;">
	     	<div class="layui-inline">
				  <label class="layui-form-label">合同开始日期：</label>
				  <div class="layui-input-inline">
					  <input type="text" name="contractStartTime" id="contractStartTime-form"  autocomplete="off" class="layui-input form-control hasDatepicker">
				  </div>
			  </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">合同结束日期：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="contractEndTime" id="contractEndTime-form" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
			  <div class="layui-inline">
				  <label class="layui-form-label">签订日期：</label>
				  <div class="layui-input-inline">
					  <input type="text" name="signContractDate" id="signContractDate-form" autocomplete="off" class="layui-input form-control hasDatepicker">
				  </div>
			  </div>
			 <div class="layui-inline">
				 <label class="layui-form-label">备注：</label>
				 <div class="layui-input-inline" style="width:323px;">
					 <textarea name="remark"  class="layui-textarea form-control"></textarea>
				 </div>
			 </div>
	     </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="contract-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="contract-close-hook">关闭</a>
    </div>
</div>
<script>
$(function(){
	layui.use(['layer', 'form','laydate'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;
		 //日期
		  laydate.render({
			    elem: "#contractStartTime-form",
			    theme: 'molv'
		 });
        laydate.render({
            elem: "#contractEndTime-form",
            theme: 'molv'
        });
        laydate.render({
            elem: "#signContractDate-form",
            theme: 'molv'
        });
		
		function getParam(){
			var queryParams=$("#contract-addForm-hook form").serializeObject();
			 var newParam = {}
			  for(var i in queryParams){
				  if(queryParams[i]){
					  newParam[i] = queryParams[i]
				  }
			  }
			 if(fileIds){
				 newParam.fileIds=fileIds.join(',')
			 }
			 if(queryParams.open=='on'){
				 newParam.isWorkAreaExplicit='00'
			 }
			 else{
				 newParam.isWorkAreaExplicit='01'
			 }
			  return newParam
		}
		 
	// form 表单手动渲染
	  form.render();
        //输入值变更
        $(document).on('change', '#contract-addForm-hook input[name="contractAmount"]', function(data) {
            var amount = $("#contract-addForm-hook input[name='contractAmount']").val();
            var tax = $("#contract-addForm-hook input[name='taxRate']").val();
            var afterAmount = amount*(1-tax/100);
            $("#contract-addForm-hook input[name='afterTaxContractAmount']").val(afterAmount);
        });
        //输入值变更
        $(document).on('change', '#contract-addForm-hook input[name="taxRate"]', function(data) {
            var amount = $("#contract-addForm-hook input[name='contractAmount']").val();
            var tax = $("#contract-addForm-hook input[name='taxRate']").val();
            var afterAmount = amount*(1-tax/100);
            $("#contract-addForm-hook input[name='afterTaxContractAmount']").val(afterAmount);
        });


	  $("#contract-addForm-hook #payDeptNameQuery-form").click(function(){
		  $.openWindow({
		  		url:'org?act=form',
		  		title:"选择销售部门",
		  		width:"400"
		 });
	  });
	  $("#contract-addForm-hook #customerQuery-form").click(function(){
		  $.openWindow({
		  		url:'customer?act=form',
		  		title:"选择客户名称",
		  		width:"750"
		 });
		  
	  });
	  
	  $("#contract-addForm-hook #custNameQuery-form").on("click",function(){
		  	$.openWindow({
		  		url:'user?act=form',
		  		title:"选择客户经理",
		  		width:"700"
		 	 });
		});
	  
		var win=$("#contract-addForm-hook").getWindow();
		// 保存
		$("#contract-addForm-hook #contract-add-hook").click(function(){
			
			var customerGroupName=$("#contract-addForm-hook input[name='contractName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入合同名称");
				return false;
			}
			
			
			$.ajax({
				type:'POST',
				url:'/vote/pmcontractinfo/save',
				contentType:'application/json',
				data: JSON.stringify(getParam()),
				success:function(res){
                    location.reload();
					layer.msg("新增成功",{icon:1});
					win.close();
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					win.close();
				}
			})
			return false;
		});
		
		// 关闭
		$("#contract-addForm-hook #contract-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});
var fileIds = []
</script>