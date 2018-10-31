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
	         <input type="text" name="sapCode" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
 	 	
	    <div class="layui-inline">
	      <label class="layui-form-label">客户名称：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="custCnName" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    
	    <div class="layui-inline">
	      <label class="layui-form-label">国家/地区：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="country" <c:if test="${act =='view'}">disabled=true</c:if> autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    
		<div class="layui-inline">
	      <label class="layui-form-label">英文名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="enName" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">客户纳税识别码：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custPatTaxesCode" <c:if test="${act =='view'}">disabled=true</c:if> autocomplete="off" class="layui-input form-control">
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
	         <input type="text" name="location" <c:if test="${act =='view'}">disabled=true</c:if> autocomplete="off" class="layui-input form-control">
	      </div>
       </div>
 	   <div class="layui-inline">
		  <label class="layui-form-label">现金管理组：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="cashManagementGroup" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">付款条件：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="payCondition" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   
	   <div class="layui-inline">
		  <label class="layui-form-label">行业代码：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="tradeCode" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">地区市场：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="regionalMarket" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   
	   <div class="layui-inline">
		  <label class="layui-form-label">主营业务：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="mainBusiness" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">地区：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="area" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
	      <label class="layui-form-label">客户行业：</label>
	      <div class="layui-input-inline">
	        <select name="custTrade" lay-verify="required" <c:if test="${act =='view'}">disabled=true</c:if> lay-filter="" class="form-control">
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
	        <select name="isUseful" lay-verify="required" <c:if test="${act =='view'}">disabled=true</c:if> lay-filter="" class="form-control">
	        	 ${isUseful.ewTypeHtml }
	        </select>
	      </div>
	    </div>
	  <div class="layui-inline">
	       <label class="layui-form-label">集团公司：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="groupCompany" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
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
	         <input type="text" name="companyCode" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   <div class="layui-inline">
		  <label class="layui-form-label">公司代码（职能）：</label>
	     <div class="layui-input-inline">
	         <input type="text" name="companyFuncCode" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	  <c:if test="${act =='view'}">
	   <div class="layui-inline">
		  <label class="layui-form-label">创建日期：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="createTime" id="createTime" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	   </div>
	   </c:if>
	   
	    <div class="layui-inline">
		    <label class="layui-form-label">选择客户群：</label>
	       <div class="layui-input-inline">
	          <select name="choseGroup" lay-verify="required" lay-search="">
		          <option value="">选择客户群</option>
		          <option value="1">group1</option>
		          <option value="2">group2</option>
		          <option value="3">group3</option>
	          </select>
	       </div>
	   </div>
	   
	   
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
	  	 
	  	 // 根据sapCode 获取json 数据
	  	form.val("customer-form", {
			"sapCode":"1000000210",
			"custCnName":"上海浦东发展银行股份有限公司",
			"country":"中国",
			"enName":'',
			"custPatTaxesCode":"91320600838300330D",
			"custType":"01",
			"location":"江苏省南通市崇川区姚港路8号",
			"cashManagementGroup":"主营业务客户",
			"payCondition":"180天之内 到期净值",
			"tradeCode":"互联网和电商",
			"regionalMarket":"中国",
			"mainBusiness":"计算机专业领域内的四技服务，计算机软硬件、电子通讯系统集成，计算机软硬件及配件、电子产品及通信设备销售及售后服务",
			"area":"上海",
			"custTrade":"01",
			"payCycle":"01",
			"isUseful":"01",
			"groupCompany":"扬州市邗江区住房保障和房产管理局",
			"bgVisiable":"01",
			"companyCode":"1005",
			"companyFuncCode":"",
			"createTime":"2018-10-12 13:29:43",
			"custGroupId":"1000000210",
			"custGroupName":"交通银行"
		});
 });
 </script>
 </c:if>



