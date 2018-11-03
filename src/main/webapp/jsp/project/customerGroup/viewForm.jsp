<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	         <lable class="layui-form-label">123阿丹上看</lable>
	      </div>
	    </div>
	     <div class="layui-inline">
	     	   <label class="layui-form-label">已选客户：</label>
		      <div class="layui-input-inline" id="chosed-customer-hook" style="border:#e6e6e6 solid 1px;height:100px;overflow-y:auto;width:320px;">
		         <span class="customer-list">
		         	<span class="customerItem" sapCode="1">南京发展银行股份有限公司</span>
		         </span>
		         <span class="customer-list">
		         	<span class="customerItem" sapCode="2">北京发展银行股份有限公司</span>
		         </span>
		         <span class="customer-list">
		         	<span class="customerItem" sapCode="3">天津发展银行股份有限公司</span>
		         </span>
		         <span class="customer-list">
		         	<span class="customerItem" sapCode="4">天津发展银行股份有限公司</span>
		         </span>

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