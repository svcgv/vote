<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#budget-addForm-hook .layui-form-label {
    width: 130px!important;
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
.layui-table-iptMoney{
	padding:0 ;
	text-align:center;
	border-radius:0;
	box-shadow:1px 1px 20px rgba(0,0,0,.15);
	border-color:#5FB878;
	height:28px;
}
#budget-addForm-hook .layui-table-body.layui-table-main{
	min-height: 80px;
}
#budget-addForm-hook .layui-table-body .layui-table{
	min-height: 50px;
}
.budget-wrapper{overflow:auto;margin:0 10px;}
.layui-table td, .layui-table th{
	white-space: nowrap;
    word-spacing: normal;
    word-break: break-word;
    text-align:center;
}
.layui-input-inline{
	min-width: 190px;
    margin-right: 10px;
}
.project-list .item{
	text-align:left;
	height:32px;
	line-height:32px;
	display:block;
}
.project-list .productItem{
	text-align:left;
	height:32px;
	line-height:32px;
	margin:5px 0;
	display:block;
	width:auto;
	float:none;
}
.project-list .item .layui-badge-dot{
	margin-right:5px;
}
.project-list .copyAddItem{
	display:none;
}
.layui-table tbody tr:hover{
	background:#fff;
}
.layui-btn-sm{
	font-size:14px;
}
</style>
<div id="budget-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
	 	<div class="layui-inline" style="margin-left:10px;">
	      <button type="button"  class="layui-btn layui-btn-sm" id="customerQuery-form" ><i class="layui-icon"></i>添加老客户</button>
	      <button type="button"  class="layui-btn layui-btn-sm" id="addCustomer-form" ><i class="layui-icon"></i>新增新客户</button>
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
			      <td rowspan="2">产品列表</td>
			      <td rowspan="2">收入来源(Revenue source)</td>
			      <td rowspan="2">公司实体(Entity)</td>
			      <td rowspan="2">合同编码(Contract)</td>
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
			      <td rowspan="2">税后(Total Rev)</td>
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
			    <tr>
			      <td >
			      	<div class="layui-inline" style="min-width:250px;">
			      		<div class="layui-input-inline">
				      		<label>上海农商</label>
			      		</div>
			      	</div>
			      	<div>
			      		<button type="button"  class="layui-btn layui-btn-sm addProjectQuery-hook" style="vertical-align: top;;margin-top:10px;"><i class="layui-icon"></i>新增项目</button>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
				      	<div class="layui-input-inline item" style="margin:5px 0;">
				      		<span>WBS123223Y4872Y34</span>
				      	</div>
				      	<div class="layui-input-inline item" style="margin:5px 0;">
				      		<span>WBS123223Y4872Y34</span>
				      	</div>
				      	<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
				      		<div class="layui-input-inline">
					      		<input type="text" name="wbsCode" readonly="readonly"  class="layui-input form-control disabledColor" />
			      			</div>
			      			<button type="button"  class="layui-btn layui-btn-sm WBSQuery-hook"  style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
				      	</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span class="layui-badge-dot layui-bg-green"></span><span>项目名称一</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span class="layui-badge-dot layui-bg-green"></span><span>项目名称一</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			<div class="layui-input-inline">
			      				<input type="text" name="projectName" readonly="readonly" class="layui-input form-control disabledColor"/>
			      			</div>
			      			<button type="button"  class="layui-btn layui-btn-sm" id="projectNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>项目</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>产品</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>项目</option>
						        	<option value="02">产品</option>
						        	<option value="03" >人力</option>
								  </select>
					      	</div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline productItem" style="">
			      			<span class="layui-badge layui-bg-gray" productid="0">列表1</span>
			      			<span class="layui-badge layui-bg-gray" productid="01">列表1</span>
			      			<span class="layui-badge layui-bg-gray" productid="02">列表1</span>
			      			
			      		</div>
			      		<div class="layui-input-inline productItem" style="">
			      			<span class="layui-badge layui-bg-gray" productid="03">列表2</span>
			      			<span class="layui-badge layui-bg-gray" productid="03">列表2</span>
			      			<span class="layui-badge layui-bg-gray" productid="03">列表2</span>
			      			<span class="layui-badge layui-bg-gray" productid="03">列表2</span>
			      		</div>
			      		<div class="layui-input-inline productItem copyAddItem" style="">
			      			<div class="layui-input-inline">
				      			<input name="productList" type="text" class="layui-input form-control">
			      			</div>
			      			<button type="button"  class="layui-btn layui-btn-sm productQuery-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-search "></i></button>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>收入来源1</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>收入来源2</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="revenueSource" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>收入来源1</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>收入来源2</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="revenueSource" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>公司实体1</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>公司实体2</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="entity" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>sow</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>sow</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="poSow" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>合同编号KSASASQW123123JKSD</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>合同编号123123</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="contract" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>A</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>B</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="taxes" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>A</option>
						        	<option value="02">B</option>
						        	<option value="03" >C</option>
						        	<option value="04" >D</option>
						        	<option value="05" >E</option>
						        	<option value="06" >F</option>
						        	<option value="07" >G</option>
						        	<option value="08" >H</option>
						        	<option value="09" >I</option>
							  </select>
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>T&M</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>FA</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="revRecognitionMethod" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>T&M</option>
						        	<option value="02">FA</option>
						        	<option value="03" >Others</option>
							   </select>
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>中国</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>香港</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="region" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>美元</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>人民币</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="currency" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>人民币</option>
						        	<option value="02">美元</option>
						        	<option value="03">欧元</option>
						        	<option value="04">英镑</option>
						        	<option value="05">日元</option>
							   </select>
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12%</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>13%</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="taxRate" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12%</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>13%</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="grossRate" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list contractMoney">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>123456</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>46548151</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list manyYearRev">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list curYearRev">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list lastRev">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			<span>65651616</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="afterTax" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <!-- 12 revenue -->
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="jan" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="feb" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="mar" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="apr" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="may" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="jun" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="jul" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="aug" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="sep" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="oct" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="nov" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>798865651</span>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<span>12</span>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="dec" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      
			      <td>
			      	<div class="project-list" style="width:150px;">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			 <div class="layui-input-inline"></div>
			      		</div>
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			 <div class="layui-input-inline"></div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px;">
						      <button type="button"  class="layui-btn layui-btn-sm newProjectSave-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-ok"></i>保存</button>
						      <button type="button"  class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-close"></i>删除</button>
			      		</div>
			      	</div>
			      </td>
			    </tr>
			   
			   <!-- 新增客户  -->
			    <tr style="display:none;" class="trTmpl">
			      <td>
			      	<div class="layui-inline" style="min-width:250px;">
			      		<div class="layui-input-inline">
				      		<input type="text" name="newCustomer" placeholder="请输入新客户" class="layui-input form-control" />
			      		</div>
			      	</div>
			      	<div>
			      		<button type="button"  class="layui-btn layui-btn-sm addProjectQuery-hook" style="vertical-align: top;;margin-top:10px;"><i class="layui-icon"></i>新增项目</button>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
				      	<div class="layui-input-inline item" style="margin:5px 0;">
				      		<div class="layui-input-inline">
			      			</div>
				      	</div>
				      	<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
				      		<div class="layui-input-inline">
			      			</div>
				      	</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<div class="layui-input-inline">
			      				<input type="text" name="projectName"  class="layui-input form-control" />
			      			</div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			<div class="layui-input-inline">
			      				<input type="text" name="projectName" class="layui-input form-control" />
			      			</div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>项目</option>
						        	<option value="02">产品</option>
						        	<option value="03" >人力</option>
								  </select>
					      	</div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>项目</option>
						        	<option value="02">产品</option>
						        	<option value="03" >人力</option>
								  </select>
					      	</div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline productItem" style="">
			      			<div class="layui-input-inline">
				      			<input name="productList" type="text" class="layui-input form-control">
			      			</div>
			      			<button type="button"  class="layui-btn layui-btn-sm productQuery-hook"  style="vertical-align: top;"><i class="layui-icon layui-icon-search "></i></button>
			      		</div>
			      		<div class="layui-input-inline productItem copyAddItem" style="">
			      			<div class="layui-input-inline">
				      			<input name="productList" type="text" class="layui-input form-control">
			      			</div>
			      			<button type="button"  class="layui-btn layui-btn-sm productQuery-hook"  style="vertical-align: top;"><i class="layui-icon layui-icon-search "></i></button>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="revenueSource" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="revenueSource" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="revenueSource" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="revenueSource" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="entity" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="entity" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="poSow" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="poSow" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="contract" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="contract" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="taxes" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>A</option>
						        	<option value="02">B</option>
						        	<option value="03" >C</option>
						        	<option value="04" >D</option>
						        	<option value="05" >E</option>
						        	<option value="06" >F</option>
						        	<option value="07" >G</option>
						        	<option value="08" >H</option>
						        	<option value="09" >I</option>
							    </select>
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="taxes" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>A</option>
						        	<option value="02">B</option>
						        	<option value="03" >C</option>
						        	<option value="04" >D</option>
						        	<option value="05" >E</option>
						        	<option value="06" >F</option>
						        	<option value="07" >G</option>
						        	<option value="08" >H</option>
						        	<option value="09" >I</option>
							    </select>
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="revRecognitionMethod" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>T&M</option>
						        	<option value="02">FA</option>
						        	<option value="03" >Others</option>
							   </select>
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="revRecognitionMethod" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>T&M</option>
						        	<option value="02">FA</option>
						        	<option value="03" >Others</option>
							   </select>
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="region" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="region" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="currency" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>人民币</option>
						        	<option value="02">美元</option>
						        	<option value="03">欧元</option>
						        	<option value="04">英镑</option>
						        	<option value="05">日元</option>
							   </select>
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<select name="currency" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="01" selected>人民币</option>
						        	<option value="02">美元</option>
						        	<option value="03">欧元</option>
						        	<option value="04">英镑</option>
						        	<option value="05">日元</option>
							   </select>
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="taxRate" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="taxRate" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="grossRate" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="grossRate" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list contractMoney">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list manyYearRev">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list curYearRev">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list lastRev">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="afterTax" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="afterTax" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			       <!-- 12 revenue -->
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<div class="layui-input-inline">
						     	<input type="text" name="jan" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="jan" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="feb" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="feb" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="mar" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="mar" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="apr" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="apr" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<div class="layui-input-inline">
						     	<input type="text" name="may" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="may" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="jun" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="jun" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			<div class="layui-input-inline">
						     	<input type="text" name="jul" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="jul" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="aug" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="aug" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="sep" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="sep" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="oct" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="oct" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="nov" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="nov" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list">
			      		<div class="layui-input-inline item" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="dec" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
			      			 <div class="layui-input-inline">
						     	<input type="text" name="dec" class="layui-input form-control" />
					      	 </div>
			      		</div>
			      	</div>
			      </td>
			      <td>
			      	<div class="project-list" style="width:150px;">
			      		<div class="layui-input-inline item" style="margin:5px;">
			      			 <button type="button"  class="layui-btn layui-btn-sm newProjectSave-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-ok"></i>保存</button>
					      	 <button type="button"  class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-close"></i>删除</button>
			      		</div>
			      		<div class="layui-input-inline item copyAddItem" style="margin:5px;">
						      <button type="button"  class="layui-btn layui-btn-sm newProjectSave-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-ok"></i>保存</button>
						      <button type="button"  class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-close"></i>删除</button>
			      		</div>
			      	</div>
			      </td>
			    </tr>
			    <!--   end -->
			    
			  </tbody>
			</table>
		</div>
	</form>
	 
	 
	 
	 
	 
	<table id="custRevSummary2" lay-filter="calcRev"></table>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="customGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
    
    <table id="budgetTableForm" lay-filter="calcRev" lay-data="{id: 'idTest'}"></table>
    <a class="layui-layer-btn0" id="getData" style="background:#009688;border-color:#009688;">获取数据</a>
</div>

<script type="text/html" id="toolbarForm">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
  </div>
</script>

<script type="text/html" id="iptMoneyTpl">
  <input class="layui-input layui-table-iptMoney"/>
</script>
<script>

var cols=[    {width:'180px',title:'是否新客户', edit:'text', field:'isNewCust'},
	{width:'180px',title:'客户名称', edit:'text', field:'custName'},
	{width:'180px',title:'客户SAPCode', edit:'text', field:'sapCode'},
	{width:'180px',title:'项目名称', edit:'text', field:'projectName'},
	{width:'180px',title:'项目WBS编号', edit:'text', field:'wbs'},
	{width:'180px',title:'项目类型', edit:'text', field:'projectType'},
	{width:'180px',title:'收入来源', edit:'text', field:'revebueSource'},
	{width:'180px',title:'公司实体名称', edit:'text', field:'companyEntityName'},
	{width:'180px',title:'公司实体编号', edit:'text', field:'companyCode'},
	{width:'180px',title:'合同ID', edit:'text', field:'contractId'},
	{width:'180px',title:'合同编号', edit:'text', field:'contractCode'},
	{width:'180px',title:'合同名称', edit:'text', field:'contractName'},
	{width:'180px',title:'采购单', edit:'text', field:'revenueSource'},
	{width:'180px',title:'客户经理编号', edit:'text', field:'custManagerId'},
	{width:'180px',title:'客户经理名称', edit:'text', field:'custManagerName'},
	{width:'180px',title:'税种', edit:'text', field:'taxType'},
	{width:'180px',title:'收入类型', edit:'text', field:'incommingType'},
	{width:'180px',title:'国家', edit:'text', field:'region'},
	{width:'180px',title:'币种', edit:'text', field:'curency'},
	{width:'180px',title:'毛利率', edit:'text', field:'grossProfitRate'},
	{width:'180px',title:'预计一月收入', edit:'text', field:'budgetJan'},
	{width:'180px',title:'预计二月收入', edit:'text', field:'budgetFeb'},
	{width:'180px',title:'预计三月收入', edit:'text', field:'budgetMar'},
	{width:'180px',title:'预计四月收入', edit:'text', field:'budgetApr'},
	{width:'180px',title:'预计五月收入', edit:'text', field:'budgetMay'},
	{width:'180px',title:'预计六月收入', edit:'text', field:'budgetJun'},
	{width:'180px',title:'预计七月收入', edit:'text', field:'budgetJul'},
	{width:'180px',title:'预计八月收入', edit:'text', field:'budgetAug'},
	{width:'180px',title:'预计九月收入', edit:'text', field:'budgetSep'},
	{width:'180px',title:'预计十月收入', edit:'text', field:'budgetOct'},
	{width:'180px',title:'预计十一月收入', edit:'text', field:'budgetNov'},
	{width:'180px',title:'预计十二月收入', edit:'text', field:'budgetDec'},
	{width:'180px',title:'收入合计', edit:'text', field:'budgetSum'},
	{width:'180px',title:'添加人编号', edit:'text', field:'creatorId'},
	{width:'180px',title:'添加时间', edit:'text', field:'createTime'},
	{width:'180px',title:'修改人', edit:'text', field:'modifier'},
	{width:'180px',title:'修改时间', edit:'text', field:'modifyTime'},
	{width:'180px',title:'是否删除', edit:'text', field:'isDelete'},
	{width:'180px',title:'备注', edit:'text', field:'remark'},
	{width:'180px',title:'预算评审状态', edit:'text', field:'reviewStatus'},
	{width:'180px',title:'预算年份', edit:'text', field:'budgetYear'}]
	
	
//客户收入汇总 初始化值 全局变量
var customerData=[{'custCode':'','custName':'','totalRev':'0'}];
var table2=null;
	layui.use(['layer', 'form','laydate','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;
		// 设置全局变量
	  	  table2=layui.table;
		 //日期
		  laydate.render({
			    elem: "#predictPeriodDate-edit",
			    theme: 'molv',
			    type: 'datetime'
		 });
		console.log(table2)
	// form 表单手动渲染
	  form.render();
	/*
	*   业务功能：
	*	选择新客户 ，则自动选择新项目
	*   提供已有客户可选择，若系新客户，则不选，手工输入客户名称
	*	
	*/
	// 新增老客户
    $("#budget-addForm-hook #customerQuery-form").click(function(){
		  $.openWindow({
		  		url:'customer?act=choseOldCustomer',
		  		title:"选择客户名称",
		  		width:"750"
		 });
    });
	// 手书时去掉 custId
	$("#budget-addForm-hook input[name=custName]").keyup(function(){
		$("#budget-addForm-hook input[name=custId]").val("")
	});
	// 新增新客户
	$("#budget-addForm-hook #addCustomer-form").click(function(){
		$.ajax({
			type:"GET",
			url:"trTempl",
			data:{
				custName:"",
				custId:""
			},
			success:function(html){
				$(".budget-wrapper tbody").append(html);
				win.resize();
			}
		})
		
		
	});
	
	
	$("#budget-addForm-hook").on("click",".addProjectQuery-hook",function(){
		$(this).attr("disabled","disabled");
		var num=$(this).parents("td").next("td").find(".project-list .item").length;
		var thisTr=$(this).parents("tr");
		var _this=this;
		var win=$(this).getWindow();
		thisTr.children("td").each(function(){
			var copyItem = $(this).find(".copyAddItem");
			if(copyItem.length){
				var _copyHtml = copyItem.clone(true).prop("outerHTML");;
				_copyHtml =$(_copyHtml).removeClass("copyAddItem");
				copyItem.before(_copyHtml);
			}
		});
		win.resize();
		form.render();
		$(this).removeAttr("disabled");
	});
  	// 项目名称
	$("#budget-addForm-hook").on("click","#projectNameQuery-hook",function(){
		// 当前按钮坐标
			var _YIndex=$(this).parents("tr").index();
			var _index=$(this).parents(".item").index();
		 $.openWindow({
		  		url:'wbs?act=addProjectName&index='+_index+'&YIndex='+_YIndex,
		  		title:"选择项目名称",
		  		width:"700"
		 });
	});
  // wbs
	$("#budget-addForm-hook").on("click",".WBSQuery-hook",function(){
		// 当前按钮坐标
		var _YIndex=$(this).parents("tr").index();
		var _index=$(this).parents(".item").index();
		 console.log(_YIndex,_index)
		 $.openWindow({
		  		url:'wbs?act=addWBS&index='+_index+'&YIndex='+_YIndex,
		  		title:"选择WBS编号",
		  		width:"700"
		 });
	});
  // 删除当前行
  $("#budget-addForm-hook").on("click",".newProjectDelete-hook",function(){
		// 当前按钮坐标
		var _YIndex=$(this).parents("tr").index();
		var _index=$(this).parents(".item").index();
		console.log(_YIndex,_index)
		var thisTr=$(this).parents("tr");
		var _this=this;
		var win=$(this).getWindow();
		thisTr.children("td").each(function(){
			$(this).find(".item").eq(_index).remove();
			$(this).find(".productItem").eq(_index).remove();
		});
		win.resize();
		
  });
  // 保存当前行
  $("#budget-addForm-hook").on("click",".newProjectSave-hook",function(){
	  
  });
	// 选择产品
  $("#budget-addForm-hook").on("click",".productQuery-hook",function(){
	// 当前按钮坐标
		var _YIndex=$(this).parents("tr").index();
		var _index=$(this).parents(".productItem").index();
		 console.log(_YIndex,_index);
		  $.openWindow({
		  		url:'product?act=formProduct&index='+_index+'&YIndex='+_YIndex,
		  		title:"选择产品",
		  		width:"700"
		 });
  });
  

	  table2.render({
		  elem: '#custRevSummary',
		  id:"custRevSummaryTableID",
		  cols: [
		         [ //标题栏
		            {field: 'custName', title: '客户（Clients）', width: 180, rowspan: 2,templet: function(d){
		            	return '<div><span custCode='+ d.custCode +'>'+d.custName+'</span></div>'
		            }} 
		           ,{field: 'totalRev', title: 'Total Rev', width: 100, rowspan: 2}
		           ,{align: 'center', title: 'Revenue', colspan: 12} //colspan即横跨的单元格数，这种情况下不用设置field和width
		         ], [
		            	{field: 'jan', title: 'Jan',templet:function(d){ var num=typeof d.jan =="undefined" ? '':d.jan;  return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'feb', title: 'Feb', templet:function(d){ var num=typeof d.feb =="undefined" ? '':d.feb; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'mar', title: 'Mar', templet:function(d){var num=typeof d.mar =="undefined" ? '':d.mar; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'apr', title: 'Apr', templet:function(d){var num=typeof d.apr =="undefined" ? '':d.apr; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'may', title: 'May',templet:function(d){var num=typeof d.may =="undefined" ? '':d.may; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'jun', title: 'Jun',templet:function(d){var num=typeof d.jun =="undefined" ? '':d.jun; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'jul', title: 'Jul',templet:function(d){var num=typeof d.jul =="undefined" ? '':d.jul; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'aug', title: 'Aug',templet:function(d){var num=typeof d.aug =="undefined" ? '':d.aug; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'sep', title: 'Sep', templet:function(d){var num=typeof d.sep =="undefined" ? '':d.sep; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'oct', title: 'Oct', templet:function(d){var num=typeof d.oct =="undefined" ? '':d.oct; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'Nov', title: 'Nov',templet: function(d){var num=typeof d.nov =="undefined" ? '':d.nov; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'dec', title: 'Dec', templet:function(d){var num=typeof d.dec =="undefined" ? '':d.dec; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			         ]
		  ],
		  cellMinWidth:100,
		  data:customerData
	  });
	
	  var result=[];// 保存数据
	  
	 $("#budget-addForm-hook .layui-table-iptMoney").blur(function(){
		 var _val=$.trim($(this).val());
		 if( _val !="" && !/^\d+(\.\d{1,3})?$/.test(_val)){
				$(this).val(" ")
			  layer.msg("请输入非负数字，最多可保留3位小数");
			  return false;
		  }
		 var _iptMoneys=$(this).parents("tr").find(".layui-table-iptMoney");
		 var _total=0;
		 _iptMoneys.each(function(){
			 var _val=$.trim($(this).val()) == "" ? 0 :parseFloat($(this).val());
			 _total+=_val;
		 });
		 $(this).parents("tr").find("td[data-field='totalRev']").children("div").text(_total);
		 // save data
		 var field=$(this).parents("td").attr("data-field");
		 var obj={}
		 obj[field] = _val;
		 result.push(obj);
	 })
  
	  
		var win=$("#budget-addForm-hook").getWindow();
		// 保存
		$("#budget-addForm-hook #customGroup-add-hook").click(function(){
			console.log(result,'revenue 表格数据');
			
			var customerGroupName=$("#budget-addForm-hook input[name='custName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入客户名称");
				return false;
			}
			
			
			var formDatas=$("#budget-addForm-hook form").serializeObject();
			// 保存产品列表数据
			var productList=$("#budget-addForm-hook #chosedProduct-hook").children(".customer-list");
			var productListIds=[];
			var productListNames=[];
			if(productList.length > 0){
				productList.each(function(){
					var id=$(this).children(".customerItem").attr("productId");
					var name=$(this).children(".customerItem").text();
					productListIds.push(id);
					productListNames.push(name);
				})
				// 存到data中
				formDatas.productListIds = productListIds.join(",");
				formDatas.productListNames = productListNames.join(",");
			}
			
			
			// 保存数据 revenue 月账单
			for(var i in result){
				var obj=result[i];
				formDatas=$.extend({},true,formDatas,obj);
			}
			
			console.log(formDatas,'save data');
			return;
			$.ajax({
				type:'POST',
				url:'save',
				data:{
					queryParams:formDatas
				},
				success:function(res){
					layer.msg("新增成功",{icon:1});
					win.close();
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					win.close();
				}
			})
			return false;
		})
		
		// 关闭
		$("#budget-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
		
		
		
		
		
	
	})

	
</script>