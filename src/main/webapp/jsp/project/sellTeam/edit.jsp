<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
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
		         <input type="text" name="groupName" autocomplete="off" value="${group.groupName}" class="layui-input form-control">
		          <input type="hidden" name="groupName" autocomplete="off" value="${group.groupId}" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline" style="vertical-align:top;">
		       <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button" class="layui-btn layui-btn-sm" id="addOrg-hook"  style="margin-right:15px;"><i class="layui-icon"></i>选择机构</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="addUser-hook"  style="margin-right:15px;"><i class="layui-icon"></i>选择团队成员</button>
			  </div>
		     </div>
		     <div class="layui-inline">
	     	   <label class="layui-form-label">已选机构：</label>
			       <div class="layui-input-inline" id="chosed-customer-hook" style="border:#e6e6e6 solid 1px;height:32px;overflow-y:auto;width:460px;">
			     	 <span class="customer-list">
			    		<span class="customerItem" orgId="${group.ownerOrgId}">${group.ownerOrgName}</span>
			    		<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>
			    	</span>
			       </div>
		     </div>
		     <div class="layui-inline">
		     	   <label class="layui-form-label">已选用户：</label>
			      <div class="layui-input-inline" id="chosed-user-hook" style="border:#e6e6e6 solid 1px;height:50px;overflow-y:auto;width:460px;">
			      	
			    	<c:forEach items="${users}" var="app">
						<span class="customer-list">
				    		<span class="customerItem" userId="${app.usrId}">${app.usrName}</span>
				    		<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>
			    		</span>
					</c:forEach>
			    	
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
				var sapCode2=$(this).children(".customerItem").attr("orgId");
				ret.push(sapCode2)
			});
			
			var getChosedUser=$("#form-customer-hook #chosed-user-hook");
			var ret2=[];
			getChosedUser.children(".customer-list").each(function(){
				var sapCode2=$(this).children(".customerItem").attr("userId");
				ret.push(sapCode2)
			});
			
			
			$.ajax({
				type:'POST',
				url:'save',
				data:{
					name:customerGroupName,
					orgCodes:ret.join(","),
					userCodes:ret2.join(",")
				},
				success:function(res){
					layer.msg("新增成功",{icon:1});
					win.close();
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					win.close();
				}
				
				
			})
				
			
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