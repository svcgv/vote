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
				   <label name="contractCode"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">合同名称：</label>
		       <div class="layui-input-inline">
				   <label name="contractName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">合同金额（元）：</label>
		       <div class="layui-input-inline">
				   <label name="contractAmount"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">税率（%）：</label>
		      <div class="layui-input-inline">
				  <label name="taxRate"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label" style="width:140px!important;">税后合同金额（元）：</label>
		       <div class="layui-input-inline">
				   <label name="afterTaxContractAmount"  class="layui-form-label"></label>
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
				   <label name="sellDeptName"  class="layui-form-label"></label>
		       </div>
		     </div>
		       
		      <div class="layui-inline">
			   <label class="layui-form-label" style="width:80px!important;">客户经理：</label>
			   <div class="layui-input-inline">
				   <label name="custManagerName"  class="layui-form-label"></label>
			   </div>
		   </div>
		    <div class="layui-inline" style="margin-right:0px;">
		       <label class="layui-form-label" style="width:80px!important;">客户名称：</label>
		       <div class="layui-input-inline">
				   <label name="custName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline" style="margin-right: 0px;">
		      <label class="layui-form-label" style="width:110px!important;">OA流程编号：</label>
		       <div class="layui-input-inline">
				   <label name="oaFlowCode"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline" style="">
		      <label class="layui-form-label" style="width:80px!important;">公司代码：</label>
		       <div class="layui-input-inline">
				   <label name="companyCode"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		  </div>
	     <div class="layui-form-item" style="margin-bottom:0px;">
	     	<div class="layui-inline">
				  <label class="layui-form-label">合同开始日期：</label>
				  <div class="layui-input-inline">
					  <label name="contractStartTime"  class="layui-form-label"></label>
				  </div>
			  </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">合同结束日期：</label>
		       <div class="layui-input-inline">
				   <label name="contractEndTime"  class="layui-form-label"></label>
		      </div>
		    </div>
			  <div class="layui-inline">
				  <label class="layui-form-label">签订日期：</label>
				  <div class="layui-input-inline">
					  <label name="signContractDate"  class="layui-form-label"></label>
				  </div>
			  </div>
			 <div class="layui-inline">
				 <label class="layui-form-label">备注：</label>
				 <div class="layui-input-inline" style="width:323px;">
					 <label name="remark"  class="layui-form-label"></label>
				 </div>
			 </div>
	     </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1" id="contract-close-hook">关闭</a>
    </div>
</div>
<script>
$(function(){
	layui.use(['layer', 'form'], function(){
		var layer = layui.layer ,
	  	  form = layui.form;

        var pmContract = JSON.parse('${pmContract}');
        for (var property in pmContract) {
            $("#contract-addForm-hook label[name='"+property+"']").text(pmContract[property]);

        }

	 	  form.render();
        $("#contract-addForm-hook #contract-close-hook").click(function(){
            $(this).getWindow().close();
            return false;
        })
	})
});	
</script>