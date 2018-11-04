<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.tender-review-wrapper .layui-form-label{width:150px!important;}
</style>
<div style="margin-top:10px;" class="tender-setMoney-wrapper">
	<form class="layui-form" id="review-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-upload">
		  	<button type="button" class="layui-btn" id="addMoneyItem"><i class="layui-icon"></i>新增</button>
			  <div class="layui-setMoney-list">
			    <table class="layui-table">
			      <thead>
			        <tr><th>付款批次</th>
			        <th>付款条件</th>
			        <th>付款方式</th>
			        <th>付款比例/金额</th>
			      </tr></thead>
			      <tbody id="moneyList">
			      
			      </tbody>
			    </table>
			  </div>
		 </div> 
	    
	  </div>
	</form>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
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