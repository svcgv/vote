<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.tender-review-wrapper .layui-form-label{width:150px!important;}
.layui-table tbody tr:hover{
background-color:#fff;
}
</style>
<div style="margin:10px;" class="tender-setMoney-wrapper">
	<form class="layui-form" id="setMoney-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-upload" style="min-height:300px;">
		  	<button type="button" class="layui-btn" id="addMoneyItem"><i class="layui-icon"></i>新增</button>
			  <div class="layui-setMoney-list" style="width:98%;">
			    <table class="layui-table" style="width:98%;">
			      <thead>
			        <tr>
			        	<th style="white-space: nowrap;">付款批次</th>
				        <th>付款条件</th>
				        <th>付款方式</th>
				        <th>付款比例/金额</th>
			      	</tr>
			     </thead>
			      <tbody id="moneyList">
			     	<tr>
			        	<th>第1批次</th>
				        <th>
				        	<div class="layui-input-inline">
				        		<input type="text" name="payCondition" value="项目立项" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
					      	</div>
					     </th>
				        <th>
				        	<div class="layui-input-inline">
						         <input type="text" name="payMode" value="比例" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
					      	</div>
					     </th>
					     <th>
				        	<div class="layui-input-inline">
						         <input type="text" name="payMode" value="2000" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
					      	</div>
					     </th>
			      	 </tr>
			     	<tr>
			        	<th>第2批次</th>
				        <th>
				        	<div class="layui-input-inline">
						        <select name="payCondition" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="1">项目立项</option>
						        	<option value="2">项目上线</option>
						        	<option value="3">运维结束</option>
						        </select>
					      	</div>
					     </th>
				        <th>
				        	<div class="layui-input-inline">
						        <select name="payMode" lay-verify="required" lay-filter="" class="form-control">
						        	<option value="">请选择</option>
						        	<option value="1">比例</option>
						        	<option value="2">金额(元)</option>
						        </select>
					      	</div>
					    </th>
				        <th>
				        	<div class="layui-input-inline">
						         <input type="text" name="money" id="startSaleDate2" autocomplete="off" class="layui-input form-control">
					      	</div>
					     </th>
			      	 </tr>
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
		
	 	 form.render();
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