<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#budget-addForm-hook .layui-form-label {
	width: 130px !important;
}
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

#budget-addForm-hook .layui-table-iptMoney {
	padding: 0;
	text-align: center;
	border-radius: 0;
	box-shadow: 1px 1px 20px rgba(0, 0, 0, .15);
	border-color: #5FB878;
	height: 28px;
}

#budget-addForm-hook .layui-table-body.layui-table-main {
	min-height: 80px;
}

#budget-addForm-hook .layui-table-body .layui-table {
	min-height: 50px;
}

#budget-addForm-hook .budget-wrapper {
	overflow: auto;
}

#budget-addForm-hook .layui-table td, .layui-table th {
	white-space: nowrap;
	word-spacing: normal;
	word-break: break-word;
	text-align: center;
}

#budget-addForm-hook .layui-input-inline {
	min-width: 150px;
	margin-right: 10px;
}

.project-list .item {
	text-align: left;
	height: 24px;
	line-height: 24px;
	display: block;
	min-width: 90px;
}

.project-list2 .item {
	text-align: left;
	height: 24px;
	line-height: 24px;
	display: block;
	min-width: 90px;
}

.project-list .productItem {
	text-align: left;
	height: 24px;
	line-height: 24px;
	display: block;
	width: auto;
	float: none;
}

#budget-addForm-hook .project-list .item .layui-badge-dot {
	margin-right: 5px;
}

#budget-addForm-hook .project-list .copyAddItem {
	display: none;
}

#budget-addForm-hook .project-list2 .copyAddItem {
	display: none;
}

#budget-addForm-hook .layui-table tbody tr:hover {
	background: #fff;
}

#budget-addForm-hook .layui-btn-sm {
	font-size: 12px;
	padding:0px 4px;
	line-height:22px;
	height:22px;
	margin-right:5px;
}
#budget-addForm-hook .layui-btn-sm i{
	font-size: 12px!important;
}
#budget-addForm-hook .layui-table td, .layui-table th{
	font-size: 12px;
	padding:2px;
}
#budget-addForm-hook .layui-input, .layui-select, .layui-textarea{
	line-height:22px;
	height:22px;
	margin:1px 0;
}
</style>
<div id="budget-addForm-hook" class="formDetail-wrapper"
	style="margin-top: 10px;">
	<form class="layui-form" action="">
		<div class="layui-inline">
			<button type="button" class="layui-btn layui-btn-sm" id="customerQuery-form">
				<i class="layui-icon"></i>添加老客户
			</button>
			<button type="button" class="layui-btn layui-btn-sm" id="addCustomer-form">
				<i class="layui-icon"></i>新增新客户
			</button>
		</div>
		<div class="budget-wrapper">
			<table class="layui-table">
				<colgroup>
					<col width="150">
					<col width="200">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">客户</th>
						<th rowspan="2">WBS</th>
						<th rowspan="2">项目名称</th>
						<th rowspan="2">项目类型</th>
						<th rowspan="2">是否选择产品</th>
						<td rowspan="2">产品列表</td>
						<td rowspan="2">年度</td>
						<td rowspan="2">收入来源(Revenue source)</td>
						<td rowspan="2">公司实体(Entity)</td>
						<td rowspan="2">合同编码(contractCode)</td>
						<td rowspan="2">PO#/SOW#</td>
						<td rowspan="2">客户经理(Owner)</td>
						<td rowspan="2">税种</td>
						<td rowspan="2">Rev Recognition Method</td>
						<td rowspan="2">区域(Region)</td>
						<td rowspan="2">结算币种(Currency)</td>
						<td rowspan="2">税率(%)</td>
						<td rowspan="2">毛利率(%)</td>
						<td rowspan="2">合同金额</td>
						<td rowspan="2">历年已上报收入合计</td>
						<td rowspan="2">当年已上报收入合计</td>
						<td rowspan="2">剩余收入</td>
						<td rowspan="2">税后合计</td>
						<td rowspan="2">Total Rev</td>
						<td colspan="12">Revenue</td>
						<td rowspan="2">操作</td>
					</tr>
					<tr>
						<td>Jan</td>
						<td>Feb</td>
						<td>Mar</td>
						<td>Apr</td>
						<td>May</td>
						<td>Jun</td>
						<td>Jul</td>
						<td>Aug</td>
						<td>Sep</td>
						<td>Oct</td>
						<td>Nov</td>
						<td>Dec</td>
					</tr>
				</thead>
				<tbody>
					<!-- 
			  copyAddItem DOM 结构不能删除
			   -->
					<c:forEach items="${list}" var="app" varStatus="status">
						<tr>
							<td>
								<div class="layui-inline" style="min-width: 250px;">
									<div class="layui-input-inline item">
										<label>${app.custName}</label> 
										<input type="hidden" name="custName" value="${app.custName}" /> 
										<input type="hidden" name="custId" value="${app.sapCode}" />
									</div>
								</div>
								<div>
									<button type="button" class="layui-btn layui-btn-sm addProjectQuery-hook" style="vertical-align: top;">
										<i class="layui-icon"></i>新增项目
									</button>
									<button type="button" custId="longshangCode" class="layui-btn layui-btn-sm deleteCustomer-hook" style="vertical-align: top; background-color: #FF5722;">
										<i class="layui-icon layui-icon-close"></i>删除客户
									</button>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.wbs}</span> <input type="hidden" name="wbs"
												value="${app2.wbs}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem"
										>
										<div class="layui-input-inline">
											<input type="text" name="wbs" readonly="readonly"
												class="layui-input form-control disabledColor" />
										</div>
										<button type="button" class="layui-btn layui-btn-sm WBSQuery-hook">
											<i class="layui-icon layui-icon-search "></i>
										</button>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span class="layui-badge-dot layui-bg-green"></span><span>${app2.projectName}</span>
											<input type="hidden" name="projectName" value="${app2.projectName}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="text" name="projectName" readonly="readonly" class="layui-input form-control disabledColor" />
										</div>
										<button type="button" class="layui-btn layui-btn-sm projectNameQuery-hook" >
											<i class="layui-icon layui-icon-search "></i>
										</button>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.projectType}</span> 
											<input type="hidden" name="projectType" value="${app2.projectType}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem"
										>
										<div class="layui-input-inline">
											<select name="projectType" class="form-control">
												${projectType2.ewTypeHtml}
											</select>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>是</span> 
											<input type="hidden" name="isChooseProduct" value="01" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<select name="isChooseProduct"  lay-filter="isChooseProduct-filter" class="form-control">
												<option value="">请选择</option>
												<option value="01" selected>是</option>
												<option value="02">否</option>
											</select>
										</div>
									</div>
								</div>
							</td>

							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline productItem" >
											<c:forEach items="${app2.productNameArr}" var="app3">
												<span class="layui-badge layui-bg-gray">${app3}</span>
											</c:forEach>
											<c:forEach items="${app2.productIdArr}" var="app3">
												<input type="hidden" productCode="app3" />
											</c:forEach>
										</div>
									</c:forEach>

									<div class="layui-input-inline productItem copyAddItem">
										<button type="button"
											class="layui-btn layui-btn-sm productQuery-hook" style="vertical-align: top;">
											<i class="layui-icon layui-icon-search "></i>
										</button>
										<c:if test="${projectType == '02' }">
											<div class="layui-input-inline">
												<input name="productList" type="text"
													class="layui-input form-control">
											</div>
										</c:if>

										<c:if test="${projectType != '02' }">
											<div class="layui-input-inline" style="display: none;">
												<input name="productList" type="text"
													class="layui-input form-control">
											</div>
										</c:if>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetYear}年</span>
											<input type="hidden" name="budgetYear" value="${app2.budgetYear}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem" >
										<div class="layui-input-inline">
											<select name="budgetYear" class="j-budgetYear"></select>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.revenueSource}</span> <input type="hidden"
												name="revenueSource" value="${app2.revenueSource}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem"
										>
										<div class="layui-input-inline">
											<input type="text" name="revenueSource"
												class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.companyEntityName}</span> <input type="hidden"
												name="companyEntityName" value="${app2.companyEntityName}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem"
										>
										<div class="layui-input-inline">
											<input type="text" name="companyEntityName"
												class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.contractCode}</span> <input type="hidden"
												name="contractCode" value="${app2.contractCode}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem"
										>
										<div class="layui-input-inline">
											<input type="text" name="contractCode"
												class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<%--<span>${app2.poSow}</span>--%>
											<span>${app2.poSow}</span> <input type="hidden" name="poSow"
												value="" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem"
										>
										<div class="layui-input-inline">
											<input type="text" name="poSow"
												class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.custManagerName}</span> 
											<input type="hidden" name="custManagerId" value="${app2.custManagerId}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem"
										>
										<div class="layui-input-inline">
											<input type="text" name="custManagerName"
												class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.taxType}</span>
											<input type="hidden" value="${app2.taxType}" name="taxType"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<select name="taxType" lay-verify="required" class="form-control">
												${taxType.ewTypeHtml}
											</select>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.revRecognitionMethod}</span>
											<input type="hidden" value="${app2.revRecognitionMethod}" name="revRecognitionMethod"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<select name="revRecognitionMethod" class="form-control">
												${revRecognitionMethod.ewTypeHtml}
											</select>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.region}</span>
											<input type="hidden" value="${app2.region}" name="region"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="text" name="region" class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.currency}</span>
											<input type="hidden" value="${app2.currency}" name="currency"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<select name="currency" class="form-control">
											${currency.ewTypeHtml}
											</select>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">

									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.taxRate}</span>
											<input type="hidden" value="${app2.taxRate}" name="taxRate"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="taxRate" class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.grossProfitRate}</span>
											<input type="hidden" value="${app2.grossProfitRate}" name="grossProfitRate"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="grossProfitRate" class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list contractMoney">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.contractMoney}</span>
											<input type="hidden" value="${app2.contractMoney}" name="contractMoney"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="contractMoney" class="layui-input form-control" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list manyYearRev">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.manyYearRev}</span>
											<input type="hidden" value="${app2.manyYearRev}" name="manyYearRev"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list curYearRev">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.curYearRev}</span>
											<input type="hidden" value="${app2.curYearRev}" name="curYearRev"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list lastRev">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.lastRev}</span>
											<input type="hidden" value="${app2.lastRev}" name="lastRev"/>
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="lastRev" class="layui-input form-control"  />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetSum}</span>
											<input type="hidden" name="budgetSum" value="${app2.budgetSum}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem" >
										<div class="layui-input-inline">
											<input type="number" readonly="readonly" name="budgetSum" class="layui-input form-control disabledColor" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.afterTax}</span>
											<input type="hidden" name="afterTax" value="${app2.afterTax}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" readonly="readonly" name="afterTax" class="layui-input form-control disabledColor" />
										</div>
									</div>
								</div>
							</td>
							<!-- 12 revenue -->
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetJan}</span>
											<input type="hidden" name="budgetJan" value="${app2.budgetJan}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetJan" class="layui-input form-control j-monthCalc"/>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetFeb}</span>
											<input type="hidden" name="budgetFeb" value="${app2.budgetFeb}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetFeb" class="layui-input form-control j-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetMar}</span>
											<input type="hidden" name="budgetMar" value="${app2.budgetMar}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetMar" class="layui-input form-control j-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetApr}</span>
											<input type="hidden" name="budgetApr" value="${app2.budgetApr}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetApr" class="layui-input form-control j-monthCalc"/>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetMay}</span>
											<input type="hidden" name="budgetMay" value="${app2.budgetMay}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetMay" class="layui-input form-controlj-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetJun}</span>
											<input type="hidden" name="budgetJun" value="${app2.budgetJun}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetJun" class="layui-input form-control j-monthCalc"/>
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetJul}</span>
											<input type="hidden" name="budgetJul" value="${app2.budgetJul}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetJul" class="layui-input form-control j-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetAug}</span>
											<input type="hidden" name="budgetAug" value="${app2.budgetAug}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetAug" class="layui-input form-control j-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetSep}</span>
											<input type="hidden" name="budgetSep" value="${app2.budgetSep}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetSep" class="layui-input form-control j-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetOct}</span>
											<input type="hidden" name="budgetOct" value="${app2.budgetOct}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetOct" class="layui-input form-control j-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetNov}</span>
											<input type="hidden" name="budgetNov" value="${app2.budgetNov} j-monthCalc" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetNov" class="layui-input form-control j-monthCalcj-monthCalc" />
										</div>
									</div>
								</div>
							</td>
							<td>
								<div class="project-list">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item" >
											<span>${app2.budgetDec}</span>
											<input type="hidden" name="budgetDec" value="${app2.budgetDec}" />
										</div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<div class="layui-input-inline">
											<input type="number" name="budgetDec" class="layui-input form-control j-monthCalc"/>
										</div>
									</div>
								</div>
							</td>

							<td>
								<div class="project-list2" style="width: 80px;">
									<c:forEach items="${app.pmYearBudgetEntity}" var="app2">
										<div class="layui-input-inline item"></div>
									</c:forEach>
									<div class="layui-input-inline item copyAddItem">
										<button type="button" class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top; background-color: #FF5722;">
											<i class="layui-icon layui-icon-close"></i>删除
										</button>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>

					<!-- 新增客户  -->

					<!--   end -->

				</tbody>
			</table>
		</div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
		<a class="layui-layer-btn0" id="customGroup-add-hook" style="background: #009688; border-color: #009688;">保存</a> 
		<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
	</div>

</div>

<script>
	var queryAllListForUser = '/vote/pmyearbudget/queryAllListForUser'

	function queryList() {
		var queryParams = getParam()
		console.log(queryParams)

		$.ajax({
			type : 'POST',
			url : queryAllListForUser,
			data : JSON.stringify({}),
			contentType : 'application/json',
			success : function(res) {
				var testData = res.page
				console.log(res)
				testData = res.page
			},
			dataType : "json"
		});
	}
	queryList()
	//年份
	$.fn.insertYearOption = function() {
		var curYear = "${budgetYear}";
		var _optionsHtml = "<option value=''>请选择</option>";
		for (var i = curYear; i >= parseInt(curYear)-10; i--) {
			if (i == curYear) {
				_optionsHtml += "<option selected='true' value='"+i+"'>" + i
						+ ("年") + "</option>";
			} else {
				_optionsHtml += "<option value='"+i+"'>" + i + ("年")
						+ "</option>";
			}
		}
		this.html(_optionsHtml);
	}

	var budgetForm = null;
layui.use([ 'layer', 'form', 'laydate' ],function() {
	var layer = layui.layer, laydate = layui.laydate;
	budgetForm = layui.form;
	//日期
	laydate.render({
		elem : "#predictPeriodDate-edit",
		theme : 'molv',
		type : 'datetime'
	});
	budgetForm.render();
	/*
	 *   业务功能：
	 *	选择新客户 ，则自动选择新项目
	 *   提供已有客户可选择，若系新客户，则不选，手工输入客户名称
	 *	
	 */
	// 新增老客户
	$("#budget-addForm-hook #customerQuery-form").click(
			function() {
				$.openWindow({
					url : 'customer?act=choseOldCustomer',
					title : "选择客户名称",
					width : "750"
				});
			});
	//若项目类型选择产品，则产品名称列提供产品信息清单供选择
	budgetForm.on('select(isChooseProduct-filter)',function(data) {
		console.log(111)
		var index = $(this).parents(".item").index();
		var target = $(this).parents("td").next("td").find(".project-list").children(".productItem").eq(index);
		console.log(data,index,target)
		if (data.value == "01") { // 是
			var select = 'dd[lay-value=01]';
			// 显示产品选择按钮
			target.find("div").hide();
			target.find("button").show();
		} else {
			target.find("div").show();
			target.find("button").hide();
		}
		// 切换时 已选的删除
		target.find("span").remove();
	});

	// 新增新客户
	$("#budget-addForm-hook #addCustomer-form").click(function() {
		$.ajax({
			type : "GET",
			url : "trTempl",
			data : {
				custName : "",
				custId : ""
			},
			success : function(html) {
				$(".budget-wrapper tbody").append(html);
				$(".j-budgetYear").insertYearOption();
				budgetForm.render();
				win.resize();
			}
		});
	});
	// 删除客户
	$("#budget-addForm-hook").on("click",".deleteCustomer-hook", function() {
		var _this = this;
		layer.confirm('确认删除行么', function(index) {
			//后台删除
			var custId = $(_this).attr("custId");
			//$.post(url,{custId:custId},function(res){})
			$(_this).parents("tr").remove();
			layer.close(index);
		});

	});

// 新增项目
$("#budget-addForm-hook").on("click",".addProjectQuery-hook",function(){
	$(this).attr("disabled","disabled");
	var num = $(this).parents("td").next("td").find(".project-list .item").length;
	var thisTr = $(this).parents("tr");
	var _this = this;
	var win = $(this).getWindow();
	thisTr.children("td").each(function() {
			var copyItem = $(this).find(".copyAddItem");
			if (copyItem.length) {
				var _copyHtml = copyItem.clone(true).prop("outerHTML");
				_copyHtml = $(_copyHtml).removeClass("copyAddItem");
				copyItem.before(_copyHtml);
				
			}
		});
		$(".j-budgetYear").insertYearOption();
		budgetForm.render();
		win.resize();
		$(this).removeAttr("disabled");
	});
// 项目名称
$("#budget-addForm-hook").on("click",".projectNameQuery-hook",function() {
	// 当前按钮坐标
	var _YIndex = $(this).parents("tr").index();
	var _index = $(this).parents(".item").index();
	$.openWindow({
		url : 'wbs?act=addProjectName&index='+ _index+ '&YIndex='+ _YIndex,
		title : "选择项目名称",
		width : "700"
	});
});
// wbs
$("#budget-addForm-hook").on("click",".WBSQuery-hook",function() {
	// 当前按钮坐标
	var _YIndex = $(this).parents("tr").index();
	var _index = $(this).parents(".item").index();
	console.log(_YIndex, _index)
	$.openWindow({
		url : 'wbs?act=addWBS&index='+ _index + '&YIndex='+ _YIndex,
		title : "选择WBS编号",
		width : "700"
	});
});
// 删除当前行
$("#budget-addForm-hook").on("click",".newProjectDelete-hook",function() {
	// 当前按钮坐标
	var _YIndex = $(this).parents("tr").index();
	var _index = $(this).parents(".item").index();
	console.log(_YIndex, _index)
	var thisTr = $(this).parents("tr");
	var _this = this;
	var win = $(this).getWindow();
	thisTr.children("td").each(function() {
		$(this).find(".item").eq(_index).remove();
		$(this).find(".productItem").eq(_index).remove();
	});
	win.resize();
});

//$.projectTypeFn(budgetForm);
// 选择产品
$("#budget-addForm-hook").on("click",".productQuery-hook",function() {
		// 当前按钮坐标
		var _YIndex = $(this).parents("tr").index();
		var _index = $(this).parents(".productItem").index();
		$.openWindow({
			url : 'product?act=formProduct&index='+ _index+ '&YIndex='+ _YIndex,
			title : "选择产品",
			width : "700"
		});
	});
// 月份预算的合计
$("#budget-addForm-hook").on("blur",".j-monthCalc",function() {
	var _val = $.trim($(this).val());
	if (_val != ""&& !/^\d+(\.\d{1,3})?$/.test(_val)) {
		$(this).val(" ")
		layer.msg("请输入非负数字且最多可保留3位小数");
		return false;
	}
	
	var index=$(this).parents(".item").index();
	var _YIndex = $(this).parents("tr").index();
	console.log(index,'index  ss');
	var totalSummary=0;
	$(this).parents("tr").find(".j-monthCalc").each(function(){
		if($(this).parents(".item").index() == index){
			var _val=$(this).val();
			console.log(_val,index,$(this).val())
			if(_val != ""){
				totalSummary += parseFloat(_val);
			}
		}
	});
	$(this).parents("tr").find("input[name='afterTax']").each(function(){
		if($(this).parents(".item").index() == index){
			$(this).val(totalSummary)
		}
	});
	var taxRate=0;// 当前行税率
	$(this).parents("tr").find("input[name='taxRate']").each(function(){
		if($(this).parents(".item").index() == index){
			taxRate=$(this).val();
		}
	});
	
	$(this).parents("tr").find("input[name='budgetSum']").each(function(){
		if($(this).parents(".item").index() == index){
			if(taxRate!=''){
				$(this).val(totalSummary *( 1- parseFloat(taxRate)))
			}
		}
	});
});

function getArr(x) {
	var a = []
	for (var i = 0; i < x; i++) {
		a[i] = {}
	}
	return a
}

	var win = $("#budget-addForm-hook").getWindow();
	// 保存
$("#budget-addForm-hook #customGroup-add-hook").click(function() {
		var res = [];//结果集
		var res3 = [];
		var res2 = [];

	var trs = $(".budget-wrapper tbody tr").each(function(i) {
		var tds = $(this).children("td");
		var res2 = [];
		var custId = $(this).children("td").eq(0).find("input[name='custId']").val();
		var custName = $(this).children("td").eq(0).find("input[name='custName']").val();
	
		var rows = $(this).children("td").eq(2).find(".item:not('.copyAddItem')").length;
	
		var arr1 = getArr(rows)
		console.log(rows)
		tds.each(function(j) {
		//获取每个td中有多少个值
		var projectList = $(this).children(".project-list");
		// 产品列表
		if (j == 5) {
			projectList.children(".productItem:not('.copyAddItem')").each(function(k) {
				var spans = $(this).find("span");
				if(spans.length){
					var names = [];
					var ids = [];
					spans.each(function() {
						names.push($(this).text());
						ids.push($(this).attr("productCode"));
					});
					var key1 = "productNames";
					var key2 = "productIds";
					if (!arr1[k])
						arr1[k] = {}
					arr1[k][key1] = names.join(",");
					arr1[k][key2] = ids.join(",");
				}else{
					var val = $(this).find("input").val();
					var key = $(this).find("input,select").attr("name");
					if (!arr1[k])
						arr1[k] = {}
					arr1[k][key] = val;
				}
			});

		} else {
			// 剔除 copy项
			projectList.children(".item:not('.copyAddItem')").each(function(k) {
			var val = $(this).find("input").val();
			var key = $(this).find("input,select").attr("name");
				
				if (!arr1[k])
					arr1[k] = {}
				arr1[k][key] = val;
				arr1[k]["custId"] = custId;
				arr1[k]["custName"] = custName;

			});
		}
	});
	res.push(arr1)
});
console.log(res, 'res')
var fullList = []
for (var i = 0; i < res.length; i++) {
	res[i].map(function(data) {
		fullList.push(data)
	})
}
						
	var budgetList = {}
	budgetList.budgetList = fullList

	$.ajax({
			type : 'POST',
			url : '/vote/pmyearbudget/saveList',
			data : JSON
					.stringify(budgetList),
			contentType : 'application/json',
			success : function(res) {
				win.close();
			},
			dataType : "json"
	})
	return false;
})

// 关闭
$("#budget-addForm-hook #customerGroup-close-hook").click(function() {
		win.close();
		return false;
	})
});
</script>