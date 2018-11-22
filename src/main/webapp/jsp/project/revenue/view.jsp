<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
#revenue-addForm-hook {
	margin:10px;
}
#revenue-addForm-hook .layui-form-label {
    width: 130px!important;
}
</style>
<div id="revenue-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		  <div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">收入编号：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		       <span class="f-placeholder"></span>
		    </div>

		   <div class="layui-inline">
			   <label class="layui-form-label">项目编号：</label>
			   <div class="layui-input-inline">
				 <label class="layui-form-label">反写</label>
			   </div>
			   <span class="f-placeholder"></span>
		   </div>

		    <div class="layui-inline">
		      <label class="layui-form-label">收款金额(元)：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">外购人力金额(元)：</label>
		       <div class="layui-input-inline">
		        <label class="layui-form-label">反写</label>
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">外购服务金额(元)：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
	    </div>
	    <div class="layui-form-item" style="margin-bottom:0px;">
		    <div class="layui-inline">
		      <label class="layui-form-label">收款日期：</label>
		       <div class="layui-input-inline">
		        <label class="layui-form-label">反写</label>
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">应收款日期：</label>
		       <div class="layui-input-inline">
		        <label class="layui-form-label">反写</label>
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script>
$(function(){
	layui.use(['layer', 'form','laydate','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;

        table=layui.table;
	  form.render();
		var win=$("#revenue-addForm-hook").getWindow();
		// 关闭
		$("#revenue-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});

</script>