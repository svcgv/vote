<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
.formDetail-wrapper .customer-list {
	word-wrap: normal;
	word-break: keep-all;
	padding: 5px;
	display: inline-block;
}

.formDetail-wrapper .layui-icon-close-fill {
	position: relative;
	top: 1px;
}
</style>
<div id="form-customer-hook" class="formDetail-wrapper"
	style="margin-top: 10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">客户群名称：</label>
				<div class="layui-input-inline">
					<input type="text" name="custGroupName"
						value="${ctnGroup.custGroupName}" autocomplete="off"
						class="layui-input form-control">
					<input type="hidden" name="custGroupId" readonly=“readonly”
					value="${ctnGroup.custGroupId}" id='custGroupId' autocomplete="off" enabled= 'false'
					class="layui-input form-control"> 
				</div>
				
			</div>
			
			<div class="layui-inline" style="vertical-align: top;">
				<div class="layui-btn-container" style="margin-left: 15px;">
					<button type="button" class="layui-btn layui-btn-sm"
						id="addCustomer-hook" style="margin-right: 15px;">
						<i class="layui-icon"></i>添加客户
					</button>
					<input type="hidden" id="saveCustomerIds" name="customerIds" /> <input
						type="hidden" id="saveCustomerNames" name="customerNames" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">客户列表：</label>
				<div class="layui-input-inline" id="chosed-customer-hook"
					style="border: #e6e6e6 solid 1px; height: 100px; overflow-y: auto; width: 320px;">

					<c:forEach items="${customerList }" var="app">
						<span class="customer-list"> <span class="customerItem"
							sapCode="${app.sapCode}">${app.custCnName}</span> <span
							onclick="$(this).parent().remove()"><i
								class="layui-icon layui-icon-close-fill"></i></span>
						</span>
					</c:forEach>
				</div>
			</div>
		</div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
		<a class="layui-layer-btn0" id="customGroup-add-hook"
			style="background: #009688; border-color: #009688;">保存</a> <a
			class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
	</div>
</div>
<script>
	var custGroupId = ''
	$(function() {
		custGroupId = $('#custGroupId')[0].value
		console.log(custGroupId)
		layui
				.use(
						[ 'layer', 'form' ],
						function() {
							// 选择客户 
							$("#addCustomer-hook").on("click", function() {
								$.openWindow({
									url : 'formGroup?act=edit',
									title : "选择客户",
									width : "700"
								});
							});
							var win = $("#form-customer-hook").getWindow();
							// 保存
							$("#form-customer-hook #customGroup-add-hook")
									.click(
											function() {
												var customerGroupName = $("#form-customer-hook input[name='custGroupName']").val();
												console.log(customerGroupName)
												if ($.trim(customerGroupName) == '') {
													layer.msg("请输入客户群名称");
													return false;
												}

												var getChosedCustomer = $("#form-customer-hook #chosed-customer-hook");
												var ret = [];
												getChosedCustomer
														.children(
																".customer-list")
														.each(
																function() {
																	var sapCode2 = $(
																			this)
																			.children(
																					".customerItem")
																			.attr(
																					"sapCode");
																	ret
																			.push(sapCode2)
																});
												var dataparam = {
														custGroupId:custGroupId,
														name : customerGroupName,
														ctnCodes : ret
													}
												
												$.ajax({
													  type: 'POST',
													  url: '/vote/pmcustomergroup/changeRelation',
													  data: JSON.stringify(dataparam),
													  contentType:'application/json',
													  success: function(res){
													      console.log(res)
													      layer
															.msg(
																	"新增成功",
																	{
																		icon : 1
																	});
													win.close();
													      },
													      error : function() {
																layer
																		.msg(
																				"新增失败",
																				{
																					icon : 5
																				});
																win.close();
															},
													  dataType: "json"
													})

												return false;
											})

							// 关闭
							$("#form-customer-hook #customerGroup-close-hook")
									.click(function() {
										$(this).getWindow().close();
										return false;
									})

						})
	});
</script>