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
			  <div class="layui-inline" style="margin-right: 0px;">
				  <label class="layui-form-label" style="width:110px!important;">OA流程编号：</label>
				  <div class="layui-input-inline">
					  <label name="oaFlowCode"  class="layui-form-label"></label>
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
		<div class="layui-form-item" style="margin-bottom:0px;margin-left:10px;">
			<div style="float:left;width: 140px;">
				<p class="layui-form-label">项目引用列表：</p>
			</div>
			<div style="float:left;width:900px;">
				<table class="layui-hide" id="projectTable-chosed" lay-filter="tableFilter" style="overflow:hidden;"></table>
			</div>
		</div>
		<div class="layui-form-item" style="margin-bottom:0px;">
			<div class="layui-inline" style="width:98%;">
				<label class="layui-form-label">收款点信息：</label>
			</div>
		</div>
		<table class="layui-table palyListTable" style="width:95%;margin-left:10px;;" >
			<thead>
			<tr>
				<th style="white-space: nowrap;">序号</th>
				<th>收款编号</th>
				<th>收款日期</th>
				<th>收款金额</th>
				<th>收款比例（%）</th>
				<th>收款要求</th>
			</tr>
			</thead>
			<tbody class="payList">
			<c:forEach items="${pmPaymentPoints}" var="app" varStatus="status" >
			<tr>
				<th class="paySortNum">${status.count}</th>
				<th>
					<div class="layui-input-inline">
						<label name="payWbsCode"  class="layui-form-label">${app.paymentCode}</label>
					</div>
				</th>
				<th>
					<div class="layui-input-inline">
						<label name="paymentDate"  class="layui-form-label">${app.paymentDate}</label>
					</div>
				</th>
				<th>
					<div class="layui-input-inline">
						<label name="paymentAmount"  class="layui-form-label">${app.paymentAmount}</label>
					</div>
				</th>
				<th>
					<div class="layui-input-inline">
						<label name="paymentRate"  class="layui-form-label">${app.paymentRate}</label>
					</div>
				</th>
				<th>
					<div class="layui-input-inline">
						<label name="payRequirement"  class="layui-form-label">${app.remark}</label>
					</div>
				</th>
			</tr>
			</c:forEach>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1" id="contract-close-hook">关闭</a>
    </div>
</div>
<script>
$(function(){
	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form;
        table = layui.table;
        var pmContract = JSON.parse('${pmContract}');
        for (var property in pmContract) {
            $("#contract-addForm-hook label[name='"+property+"']").text(pmContract[property]);

        }

        var contractId = ${id};
        console.log(contractId);
        var param = {"contractId": contractId};
        $.ajax({
            type: 'POST',
            url: '/vote/pmcontractprojectrelation/listProject',
            data: JSON.stringify(param),
            contentType: 'application/json',
            success: function (res) {
                table.render({
                    id:"table-chosedProject",
                    elem: '#projectTable-chosed',
                    height:'350',
                    width:"700",
                    title: '项目群数据信息',
                    cols: [[
                        {field:'wbs', title:'项目编号', templet:function(d){
                            var jsonStr = JSON.stringify({"projectId":d.projectId,"wbs":d.wbs,"projectName":d.projectName});
                            return '<div class="jsonData" dataStr='+jsonStr+'>'+d.wbs+'</div>'
                        } },
                        {field:'projectName', title:'项目名称'}
                    ]],
                    cellMinWidth:'90',
                    data:res.page,
                    page: true
                });
            },
            dataType: "json"
        });
	 	  form.render();
        $("#contract-addForm-hook #contract-close-hook").click(function(){
            $(this).getWindow().close();
            return false;
        })
	})
});	
</script>