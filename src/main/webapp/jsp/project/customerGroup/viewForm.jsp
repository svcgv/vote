<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>

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
<div id="form-customer-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">客户群名称：</label>
	      <div class="layui-input-inline">
	         <lable class="layui-form-label">${ctnGroup.custGroupName}</lable>
	      </div>
	    </div>
	     <div class="layui-inline">
	     	   <label class="layui-form-label">客户列表：</label>
		      <div class="layui-input-inline" id="chosed-customer-hook" style="border:#e6e6e6 solid 1px;height:100px;overflow-y:auto;width:320px;">
		         <c:forEach items="${customerList }" var="app">					
						<span class="customer-list">
				         	<span class="customerItem" sapCode="${app.sapCode}">${app.custCnName}</span>
				         </span>
				</c:forEach>
		         

		      </div>
	     </div>
	    
	  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script>
$(function(){
		// 关闭
		$("#form-customer-hook #customerGroup-close-hook").click(function(){
			$(this).getWindow().close();
			return false;
		})
	
});

</script>