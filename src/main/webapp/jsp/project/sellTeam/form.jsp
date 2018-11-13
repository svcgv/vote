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
		      <label class="layui-form-label">团队名称：</label>
		      <div class="layui-input-inline">
		         <input type="text" name="groupName" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline" style="vertical-align:top;">
		       <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button" class="layui-btn layui-btn-sm" id="addOrg-hook"  style="margin-right:15px;"><i class="layui-icon"></i>选择机构</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="addUser-hook"  style="margin-right:15px;"><i class="layui-icon"></i>选择团队成员</button>
			  </div>
		    </div>
		     <div class="layui-inline">
		     	   <label class="layui-form-label">所属机构：</label>
			      <div class="layui-input-inline" id="chosed-customer-hook" style="border:#e6e6e6 solid 1px;height:32px;overflow-y:auto;width:460px;">
			      </div>
		     </div>
		     <div class="layui-inline">
		     	   <label class="layui-form-label">团队成员列表：</label>
			      <div class="layui-input-inline" id="chosed-user-hook" style="border:#e6e6e6 solid 1px;height:50px;overflow-y:auto;width:460px;">
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
	layui.use(['layer', 'form'], function(){
		// 选择
		$("#addOrg-hook").on("click",function(){
		  	$.openWindow({
		  		url:'org',
		  		title:"选择机构",
		  		width:"400"
		 	 });
		});
		
		$("#addUser-hook").on("click",function(){
		  	$.openWindow({
		  		url:'user',
		  		title:"选择团队成员",
		  		width:"700"
		 	 });
		});
		var win=$("#form-customer-hook").getWindow();
		// 保存
		$("#form-customer-hook #customGroup-add-hook").click(function(){
			var customerGroupName=$("#form-customer-hook input[name='groupName']").val();
			console.log(customerGroupName)
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入团队名称");
				return false;
			}
			
			var getChosedCustomer=$("#form-customer-hook #chosed-customer-hook");
			var ret=[];
			getChosedCustomer.children(".customer-list").each(function(){
				var sapCode2={};
				sapCode2.orgId=$(this).children(".customerItem").attr("orgId")
				sapCode2.orgName=$(this).children(".customerItem").attr("orgName")
				
				ret.push(sapCode2)
			});
			
			var getChosedUser=$("#form-customer-hook #chosed-user-hook");
			var ret2=[];
			getChosedUser.children(".customer-list").each(function(){
				var sapCode2={}
				sapCode2.userId = $(this).children(".customerItem").attr("userId");
				sapCode2.userName = $(this).children(".customerItem").attr("userName");
				ret2.push(sapCode2)
			});
			var param = {}
			param.groupName=customerGroupName
			param.ownerOrgId=ret[0]
			param.userCodes=ret2
			param.orgNo=ret[0].orgId
			param.orgName=ret[0].orgName
			
			
		
				console.log(param)
				
				
				
			$.ajax({
			  type: 'POST',
			  url: '/vote/pmsalegroupinfo/save',
			  data: JSON.stringify(param),
			  contentType:'application/json',
			  success:function(res){
					layer.msg("新增成功",{icon:1});
					location.reload();
					win.close();
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					//win.close();
				},
			  dataType: "json"
			})
			/* 	
			$.ajax({
				type:'POST',
				url:'/vote/pmsalegroupinfo/save',
				data:JSON.stringify(param),
				 contentType:'application/json',
				 dataType: "json",
				
			}) */
			
			return false;
		})
		
		// 关闭
		$("#form-customer-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});

</script>