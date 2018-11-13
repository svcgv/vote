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
		         <input type="text" name="productCode" value="${product.productCode}" autocomplete="off" class="layui-input form-control">
		         <input type="text" style='display:none' name="productId" autocomplete="off" value="${product.productId}" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">产品名称：</label>
		      <div class="layui-input-inline">
		         <input type="text" name="productName" value="${product.productName}" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">指导销售价：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="productSuggestPrice"  value="${product.productSuggestPrice}"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">开始销售日期：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="startSaleDate" id="startSaleDate2"  value="${product.startSaleDate}"  autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">产品类型：</label>
		      <div class="layui-input-inline">
		        <select name="productType" lay-verify="required" lay-filter="" class="form-control">
		        	 ${productType.ewTypeHtml }
		        </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">研发部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="developmentDeptName" readonly="readonly"  value="${product.developmentDeptName}"  autocomplete="off" class="layui-input form-control">
		          <input type="text" style='display:none' name="developmentDeptId" value="${product.developmentDeptId}"  >
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>部门</button>
		       </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">研发负责人：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="developmentManagerName"  value="${product.developmentManagerName}"   readonly="readonly" autocomplete="off" class="layui-input form-control">
		          <input type="text" style='display:none' name="developmentManagerId" value="${product.developmentManagerId}"  >
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>人员</button>
		       </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">项目引用：</label>
	           <div class="layui-input-inline" id="chosed-project-hook" style="border:#e6e6e6 solid 1px;height:60px;overflow-y:auto;width:320px;">
		     	 <span class="customer-list">
		    		<span class="customerItem" projectId="123">交行项目</span>
		    		<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>
		    	</span>
		       </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="projectQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>项目</button>
		       </div>
		    </div>
		    
	       <div class="layui-inline">
	       		 <label class="layui-form-label">备注：</label>
	       		 <div class="layui-input-block" style="margin-left:130px;width:323px;">
			      <textarea name="remark"   class="layui-textarea form-control">${product.remark}</textarea>
			    </div>
	       </div>
		     
		     
		  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="customGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script>
$(function(){
	layui.use(['layer', 'form','laydate'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate
		
		 //日期
	  laydate.render({
		    elem: "#startSaleDate2",
		    theme: 'molv',
		    type: 'datetime'
	 });
		
	// form 表单手动渲染
	  form.render()		 
	 // 选择机构
  $("#product-addForm-hook #orgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=add',
	  		title:"选择研发机构",
	  		width:"400"
	 });
	  
  });
  
  // 选择人员
  $("#product-addForm-hook #userQuery-hook").off("click").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=add',
	  		title:"选择研发人员",
	  		width:"700"
	 	 });
	}); 
  
  // 选择项目
  $("#product-addForm-hook #projectQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'project',
	  		title:"选择项目",
	  		width:"700"
	 	 });
	}); 
	  
		var win=$("#product-addForm-hook").getWindow();
		// 保存
		$("#product-addForm-hook #customGroup-add-hook").click(function(){
			var customerGroupName=$("#product-addForm-hook input[name='productName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入产品名称");
				return false;
			}
			
			var getChosedCustomer=$("#product-addForm-hook #chosed-project-hook");
			var ret=[];
			getChosedCustomer.children(".customer-list").each(function(){
				var sapCode2=$(this).children(".customerItem").attr("projectId");
				ret.push(sapCode2)
			});
			
			var formDatas=$("#product-addForm-hook form").serializeObject();
			formDatas=$.extend({},true,formDatas,{projectIds:ret.join(",")});
			$.ajax({
				type:'POST',
				url:'/vote/pmproductinfo/update',
				data:JSON.stringify(formDatas),
				contentType:'application/json',
				success:function(res){
					location.reload();
					layer.msg("修改成功",{icon:1});
					win.close();
				},
				error:function(){
					layer.msg("修改失败",{icon:5});
					win.close();
				}
			})
			return false;
		})
		
		// 关闭
		$("#product-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});

</script>