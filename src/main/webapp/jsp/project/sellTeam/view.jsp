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
		      <label class="layui-form-label">销售团队名称：</label>
		      <div class="layui-input-inline">
		       	<label class="layui-form-label">反写名称</label>
		      </div>
		    </div>
		     <div class="layui-inline">
	     	   <label class="layui-form-label">已选机构：</label>
		       <div class="layui-input-inline">
		       	<label class="layui-form-label">反写机构</label>
		      </div>
		     </div>
		     <div class="layui-inline">
		     	   <label class="layui-form-label">已选用户：</label>
			      <div class="layui-input-inline" id="chosed-user-hook" style="border:#e6e6e6 solid 1px;height:50px;overflow-y:auto;width:460px;">
			      	<span class="customer-list">
			    		<span class="customerItem" userId="1000">系统管理员</span>
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
	var win=$("#form-customer-hook").getWindow();
	// 关闭
	$("#form-customer-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
});

</script>