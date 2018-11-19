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
	<form class="layui-form" action="">
		  <div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><span class="required">*</span>收入编号：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="revenueCode"  lay-verify="required"  autocomplete="off" class="layui-input form-control">
		      </div>
		       <span class="f-placeholder"></span>
		    </div>

		   <div class="layui-inline">
			   <label class="layui-form-label"><span class="required">*</span>项目编号：</label>
			   <div class="layui-input-inline">
				  <input type="text" name="projectId" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
				  <input type="hidden" name="projectName" >
			   </div>
			   <button type="button"  class="layui-btn layui-btn-sm" id="projectNameQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		   </div>

		    <div class="layui-inline">
		       <label class="layui-form-label">收款金额(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="collectionAmount" lay-verify="money"   autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">外购人力金额(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="externalManpowerAmount" lay-verify="money"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">外购服务金额(元)：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="outsourcingServiceAmount" lay-verify="money"   autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
	    </div>
	    <div class="layui-form-item" style="margin-bottom:0px;">
		    <div class="layui-inline">
		      <label class="layui-form-label">收款日期：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="collectionDate" id="collectionDate-form"  autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">应收款日期：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="shouldCollectionDate" id="shouldCollectionDate-form"  autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		  </div>
		<div class="layui-layer-btn layui-layer-btn-c">
	    	<button class="layui-btn" lay-submit lay-filter="formSubmit" style="height:30px;line-height:30px;margin: 5px 5px 0;">保存</button>
	    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
	    </div>
	</form>
</div>
<script>
$(function(){
	layui.use(['layer', 'form','laydate'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;

		 //日期
		  laydate.render({
			    elem: "#revenue-addForm-hook #shouldCollectionDate-form",
			    theme: 'molv'
		 });
		  laydate.render({
			    elem: "#revenue-addForm-hook #collectionDate-form",
			    theme: 'molv'
		 });
		
	
    	//选择项目
   	  $("#revenue-addForm-hook #projectNameQuery-hook").click(function(){
   		  $.openWindow({
   		  		url:'project?act=form',
   		  		title:"选择项目编号",
   		  		width:"700"
   		 });
   		  
   	  });
		var win=$("#revenue-addForm-hook").getWindow();
	  form.render();
	 //保存和验证
	  form.on('submit(formSubmit)', function(data){
		  var data=JSON.stringify(data.field);
		  console.log(data,'form data')
		  $.ajax({
				type:'POST',
			    contentType:'application/json',
			    dataType: "json",
				url:'/vote/pmproductinfo/save',
				data: data,
				success:function(res){
					layer.msg("新增成功",{icon:1,shade:0.3,time:1000,shadeClose:true},function(){
						location.reload();
						win.close();
					});
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					win.close();
				}
			})
	    return false;
	  });
	// 关闭
	$("#revenue-addForm-hook #customerGroup-close-hook").click(function(){
		win.close();
		return false;
	})
	
	})
});

</script>