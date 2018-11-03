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
<div id="product-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		  <div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">产品代码：</label>
		      <div class="layui-input-inline">
		          <label class="layui-form-label">查看反写</label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">产品名称：</label>
		      <div class="layui-input-inline">
		          <label class="layui-form-label">查看反写</label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">指导销售价：</label>
		       <div class="layui-input-inline">
		          <label class="layui-form-label">查看反写</label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">开始销售日期：</label>
		       <div class="layui-input-inline">
		          <label class="layui-form-label">查看反写</label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">产品类型：</label>
		      <div class="layui-input-inline">
		        <label class="layui-form-label">2018-10-12</label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">研发部门：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">查看反写</label>
		      </div>
		       
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">研发负责人：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">查看反写</label>
		      </div>
		       
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">项目引用：</label>
	           <div class="layui-input-inline" id="chosed-project-hook" style="border:#e6e6e6 solid 1px;height:60px;overflow-y:auto;width:320px;">
		     	 <span class="customer-list">
		    		<span class="customerItem" projectId="123">交行项目</span>
		    	</span>
		       </div>
		       
		    </div>
		    
	       <div class="layui-inline">
	       		 <label class="layui-form-label">备注：</label>
	       		 <div class="layui-input-block" style="margin-left:130px;width:323px;">
			      <textarea name="remark" readonly="readonly" class="layui-textarea form-control">撒大声地</textarea>
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
		var win=$("#product-addForm-hook").getWindow();
		// 关闭
		$("#product-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
});

</script>