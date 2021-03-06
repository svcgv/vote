<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('#_roleId').setParentVal();//给当前标签赋值，值为父页面相同name的标签值
		 $.indi.ajaxSubmit({url: '${ctx }/role/qryMenuList',success:function(data){
			var json = JSON.stringify(data.mean);
			$("#treeview").initTreeView(json);
		}});
	});

	//保存角色菜单信息
	function saveMean(){
		var menuId = $("#treeview").getNodesByFieldStr('menuId'); //取选中的菜单ID
		$('#_menuId').val(menuId);
		
		$.indi.ajaxSubmit({url: "${ctx }/role/saveRoleInfo",success:function(data){
			if(data.status == true){
				layer.alert('角色分配成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					 window.parent.qryList_01();
				});  
			}else{
				layer.alert('角色分配失败！',{icon: 2});
			}
		}});
	}
	
	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}

</script>
</head>
<body class="body-modle">
	<div class=content>
		<div class="row" >
			<div class="col-xs-10">
				<button type="button" class="btn btn-primary" onclick="saveMean()"
					id="btnSave">
					<i class="icon-save"></i> 保存
				</button>
				<button type="button" class="btn btn-primary" onclick="close_01()">
					<i class="icon-remove"></i> 取消
				</button>
			</div>
		</div>
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" name="menuId" id="_menuId" />
				<input type="hidden" id="_roleId" name="roleId" value="${param.roleId} ">
			</form>
		</div>
		<!--  -->
		<div class="row">
			<div class="col-xs-10">
				<div id="treeview" class=""></div>
			</div>
		</div>
	</div>
</body>
</html>