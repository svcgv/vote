<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.custom-form-wrapper .layui-form-label{width:130px;}
.custom-form-view input{
	border:none;
	  -webkit-box-shadow: none!important;
          box-shadow: none!important;
  -webkit-transition: none!important;
       -o-transition: none!important;
          transition: none!important;
	
}
.custom-form-view .layui-disabled{
	color:#00004F!important;
}
</style>

<div class="custom-form-wrapper  <c:if test="${act =='view'}">custom-form-view</c:if>" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="customer-form">
	  <div class="layui-form-item">
	  	
	  	<div class="layui-inline">
	       <label class="layui-form-label">SAP编码：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="sapCode"  value="${Custom.sapCode}" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
 	 	
	    <div class="layui-inline">
	      <label class="layui-form-label">客户名称：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="custCnName"  value="${Custom.custCnName}" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    
	    <div class="layui-inline">
	      <label class="layui-form-label">国家/地区：</label>
	      <div class="layui-input-inline">
	          <select name="country" lay-verify="required"  <c:if test="${act =='view'}">disabled=true</c:if> lay-filter="" class="form-control">
	        	${country.ewTypeHtml }
	        </select>
	      </div>
	    </div>
	    
	    
		<div class="layui-inline">
	      <label class="layui-form-label">英文名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="enName"  value="${Custom.enName}" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">客户纳税识别码：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custPatTaxesCode"  value="${Custom.custPatTaxesCode}"  <c:if test="${act =='view'}">disabled=true</c:if> autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	   <div class="layui-inline">
	      <label class="layui-form-label">客户类型：</label>
	      <div class="layui-input-inline">
	        <select name="custType" lay-verify="required" <c:if test="${act =='view'}">disabled=true</c:if> lay-filter="" class="form-control">
	        	 ${custType.ewTypeHtml}
	        </select>
	      </div>
	    </div>
	    
	  <div class="layui-inline">
	  	 <label class="layui-form-label">地址：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="address"  value="${Custom.address}"  <c:if test="${act =='view'}">disabled=true</c:if> autocomplete="off" class="layui-input form-control">
	      </div>
       </div>
 	   <div class="layui-inline">
		  <label class="layui-form-label">现金管理组：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="cashManagementGroup"  value="${Custom.cashManagementGroup}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">付款条件：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="payCondition"  value="${Custom.payCondition}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   
	   <div class="layui-inline">
		  <label class="layui-form-label">行业代码：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="tradeCode"  value="${Custom.tradeCode}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">地区市场：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="regionalMarket"  value="${Custom.regionalMarket}" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   
	   <div class="layui-inline">
		  <label class="layui-form-label">主营业务：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="mainBusiness"  value="${Custom.mainBusiness}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">地区：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="area"  value="${Custom.area}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
	      <label class="layui-form-label">客户行业：</label>
	      <div class="layui-input-inline">
	        <select name="custTrade" lay-verify="required"   <c:if test="${act =='view'}">disabled=true</c:if> lay-filter="" class="form-control">
	        	${custTrade.ewTypeHtml }
	        </select>
	      </div>
	   </div>
	   
	   <div class="layui-inline">
	      <label class="layui-form-label">结算周期：</label>
	      <div class="layui-input-inline">
	        <select name="payCycle" lay-verify="required" <c:if test="${act =='view'}">disabled=true</c:if> lay-filter="" class="form-control">
	        	${payCycle.ewTypeHtml }
	        </select>
	      </div>
	    </div>
	    
	     <div class="layui-inline">
	      <label class="layui-form-label">是否有效：</label>
	      <div class="layui-input-inline">
	        <select name="isUseful" lay-verify="required"  disabled=true  lay-filter="" class="form-control">
	        	 ${isUseful.ewTypeHtml }
	        </select>
	      </div>
	    </div>
	  <div class="layui-inline">
	       <label class="layui-form-label">集团公司：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="groupCompany"  value="${Custom.groupCompany}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	  </div>
	  <div class="layui-inline">
	      <label class="layui-form-label">BG隐藏：</label>
	      <div class="layui-input-inline">
	        <select name="bgVisiable" lay-verify="required" <c:if test="${act =='view'}">disabled=true</c:if> lay-filter="" class="form-control">
	          ${bgVisiable.ewTypeHtml }
	        </select>
	      </div>
	  </div>
	  <div class="layui-inline">
		  <label class="layui-form-label">公司代码：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="companyCode"  value="${Custom.companyCode}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">公司代码（职能）：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="companyFuncCode"   value="${Custom.companyFuncCode}"  <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	  <c:if test="${act =='view'}">
	   <div class="layui-inline">
		  <label class="layui-form-label">创建日期：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="createTime" id="createTime"  value="${Custom.createTime}"   <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   </c:if>
	   
	    <div class="layui-inline">
		    <label class="layui-form-label">选择客户群：</label>
	       <div class="layui-input-inline">
	          <select name="custGroupId" lay-verify="required"  <c:if test="${act =='view'}">disabled=true</c:if> lay-search="">
	          <option value="">选择客户群</option>
	          <c:forEach items="${customerGroup}" var="app">
		          <option value="${app.custGroupId}">${app.custGroupName}</option>
		          </c:forEach>
	          </select>
	       </div>
	   </div>
	   
	   <input type="hidden" value="${custId}" name="custId" />
	  </div>
	</form>
</div>
</body>
</html>

<script type="text/javascript">
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  	  laydate.render({
	    elem: '#createTime',
	    theme: 'molv',
	    type: 'datetime'
	  });
  	 
  	 
  	
});
</script>
 <c:if test="${act !='add'}">
 <script type="text/javascript">
 layui.use(['layer', 'form','laydate','table','upload'], function(){
	  var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate,
	  	  table=layui.table,
	  	  upload=layui.upload;
	  	 
 });
 </script>
 </c:if>



