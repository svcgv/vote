<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.formDetail-wrapper .layui-form-label{width:130px;}
.form-view input{
	border:none;
	  -webkit-box-shadow: none!important;
          box-shadow: none!important;
  -webkit-transition: none!important;
       -o-transition: none!important;
          transition: none!important;
	
}
.form-view .layui-disabled{
	color:#00004F!important;
}
</style>

<div id="form-customer-hook" class="formDetail-wrapper  <c:if test="${act =='view'}">form-view</c:if>" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">客户群名称：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="custGroupName" <c:if test="${act =='view'}">disabled=true</c:if>  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	     <div class="layui-inline">
	       <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="addCustomer-hook"  style="margin-right:15px;"><i class="layui-icon"></i>添加客户</button>
		    <input type="text" style='display:none' id="saveCustomerIds" name="customerIds" />
		     <input type="text" style='display:none' id="saveCustomerNames" name="customerNames" />
		  </div>
	    </div>
	  </div>
	</form>
	
</div>
</body>
</html>

<script type="text/javascript">
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate;
  	
  $("#addCustomer-hook").on("click",function(){
		$.ajax({
			type:"GET",
			url:"formGroup",
			success: function(html){
			     $("#customerContent").html(html);
			     $("#form-customer-hook").hide();
			 }

		})
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
	  	form.val("form-detail", {
	  		"custGroupId":"1000000210",
			"custGroupName":"上海浦东发展银行股份有限公司",
			"creator":"史定波",
			"creatorId":'101',
			"createTime":"2017-10-12",
			"modifier":"",
			"modifyTime":"",
			"isDelete":"否"
		})
 });
 </script>
 </c:if>



