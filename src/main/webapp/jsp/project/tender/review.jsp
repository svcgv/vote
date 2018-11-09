<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.tender-review-wrapper .layui-form-label{width:150px!important;}
</style>
<div style="margin-top:10px;" class="tender-review-wrapper">
	<form class="layui-form" id="review-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">交付部门负责人：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="constructionDeptManagerName" readonly="readonly"  autocomplete="off" class="layui-input form-control">
	         <input type="hidden" name="constructionDeptManagerId" >
	       </div>
	       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="payManagerQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >销售部门负责人：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="sellDeptName" readonly="readonly"  autocomplete="off" class="layui-input form-control" >
	          <input type="hidden" name="sellDeptId" >
	      </div>
	      <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="sellManagerQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
	       </div>
	    </div>
	    
	  </div>
	</form>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">提交评审</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">
$(function(){
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate','table'], function(){
	  var layer = layui.layer ,
	  	  form = layui.form,
	  	  table=layui.table;
		
	  // 人员选择
	  	  $(".tender-review-wrapper #payManagerQuery-hook").on("click",function(){
		  	$.openWindow({
		  		url:'user?act=reviewPay',
		  		title:"选择交付部门负责人",
		  		width:"700"
		 	 });
			});
	  	 	$(".tender-review-wrapper #sellManagerQuery-hook").on("click",function(){
			  	$.openWindow({
			  		url:'user?act=reviewSell',
			  		title:"选择销售部门负责人",
			  		width:"700"
			 	 });
			});
	  
		// 保存 事件
		var act="${act}";// 区分是index页 form页 赋值问题
		var win=$(".tender-review-wrapper").getWindow();
		$(".tender-review-wrapper").on("click","#save-hook",function(){
			
			var formDatas=$(".tender-review-wrapper form").serializeObject();
			$.ajax({
				type:'POST',
				url:'save',
				data:{
					queryParams:formDatas
				},
				success:function(res){
					layer.msg("成功",{icon:1});
					win.close();
				},
				error:function(){
					layer.msg("失败",{icon:5});
					win.close();
				}
			})
		});
		
		// 关闭按钮
		var win=$(".tender-review-wrapper").getWindow();
		$(".tender-review-wrapper").on("click","#close-hook",function(){
			win.close();
		});
		
	});
	
});

</script>