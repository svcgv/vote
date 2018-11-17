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
.layui-table tbody tr:hover{
	background:#fff;
}
</style>
<div id="contract-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		  <div class="layui-form-item" style="margin-bottom:0px;">
		    <div class="layui-inline">
		      <label class="layui-form-label">合同编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="contractCode"  autocomplete="off" class="layui-input form-control">
				   <input type="text" style='display:none' name="contractId" autocomplete="off" value="1" class="layui-input form-control">
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
		         <input type="number" name="afterTaxContractAmount" readonly autocomplete="off" class="layui-input form-control">
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
      <div class="layui-form-item" style="margin-bottom:0px;">
      	<div class="layui-inline" style="width:98%;">
		      <label class="layui-form-label">收款点信息：</label>
		       <div class="layui-input-inline">
	         	<button type="button"  class="layui-btn layui-btn-sm" id="addPayList-form" ><i class="layui-icon"></i>新增收款点</button>
		      </div>
	    </div>
      </div>
      <table class="layui-table palyListTable" style="width:95%;margin-left:10px;;" >
	      <thead>
	        <tr>
	        	<th style="white-space: nowrap;">序号</th>
		        <th>收款日期</th>
		        <th>收款金额</th>
		        <th>收款比例（%）</th>
		        <th>收款要求</th>
		        <th>收款编号</th>
	      	</tr>
	      </thead>
		      <tbody class="payList">
		     	 <tr>
		        	 <th class="paySortNum">1</th>
			         <th>
			        	<div class="layui-input-inline">
			        		<input type="text" name="paymentDate" value="2018-12" autocomplete="off" class="layui-input form-control paymentDate-hook hasDatepicker">
				      	</div>
				     </th>
			        <th>
			        	<div class="layui-input-inline">
					         <input type="number" name="paymentAmount"  autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
				     <th>
			        	<div class="layui-input-inline">
					         <input type="number" name="paymentRate" style="width:90px;" autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
				     <th>
			        	<div class="layui-input-inline">
					         <input type="text" name="payRequirement" style="min-width:250px;" autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
				     <th>
			        	<div class="layui-input-inline">
					         <input type="text" name="payWbsCode" autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
		      	 </tr>
		     	  <tr class="listTmpl" style="display:none;">
		        	 <th class="paySortNum">1</th>
			         <th>
			        	<div class="layui-input-inline">
			        		<input type="text" name="paymentDate" autocomplete="off" class="layui-input form-control paymentDate-hook hasDatepicker">
				      	</div>
				     </th>
			        <th>
			        	<div class="layui-input-inline">
					         <input type="number" name="paymentAmount"  autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
				     <th>
			        	<div class="layui-input-inline">
					         <input type="number" name="paymentRate" style="width:90px;" autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
				     <th>
			        	<div class="layui-input-inline">
					         <input type="text" name="payRequirement" style="min-width:250px;" autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
				     <th>
			        	<div class="layui-input-inline">
					         <input type="text" name="payWbsCode" autocomplete="off" class="layui-input form-control">
				      	</div>
				     </th>
		      	 </tr>

		      </tbody>
		</table>
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

        var pmContract = JSON.parse('${pmContract}');
        console.log(pmContract);
        for (var property in pmContract) {
            $("#contract-addForm-hook input[name='"+property+"']").val(pmContract[property]);
            if(property=='remark'){
                $("#contract-addForm-hook textarea[name='"+property+"']").val(pmContract[property]);
            }
        }

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
        // 绑定日历组件
        $(".palyListTable").on("mousedown",".paymentDate-hook",function(){
        	var _this=this;
        	 laydate.render({
                 elem: _this,
                 theme: 'molv',
                 type: 'month'
             });
        })
		// 新增付款点
        $("#contract-addForm-hook #addPayList-form").click(function(){
        	var tmpl=$(".palyListTable .listTmpl").clone(true);
        	var num =$(".payList tr:not('.listTmpl')").length;
        		$(tmpl).removeClass("listTmpl").show().find(".paySortNum").text(num+1);
        	$(".palyListTable .payList").append(tmpl);
        	win.resize();

        })
        // 比例金额互换
         $("#contract-addForm-hook .palyListTable").on("keyup","input[name='paymentAmount']",function(){
       	  		var _account=$(this).val();
       	  		console.log(_account)
       	  		if(_account !=''){
       	  			 var max=100000;
       	  			 var rate=_account/max;
       	  			$(this).parents("th").next("th").find("input[name='paymentRate']").val(rate.toFixed(2));
       	  		}
         });
         $("#contract-addForm-hook .palyListTable").on("keyup","input[name='paymentRate']",function(){
       	  		var rate=$(this).val();
       	  		if(rate !=''){
       	  			 var max=100000;
       	  			 var account=rate * max;
       	  			$(this).parents("th").prev("th").find("input[name='paymentAmount']").val(account.toFixed(2));
       	  		}
         });

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
				url:'/vote/pmcontractinfo/update',
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