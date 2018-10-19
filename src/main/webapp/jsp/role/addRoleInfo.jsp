<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function save() {
		$('#i-form').validate({
			rules:{
				roleName:{
					required: true
				},roleRmk:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/role/addRoleInfo",success:function(data){
			if(data.status == true){
				layer.alert('角色新增成功！',{icon: 1}, function(index){
					window.parent.qryList_01();
					$.indi.closePopup();
				});  
			}else{
				layer.alert('角色新增失败！',{icon: 2});
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
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">角色名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="角色名称" id="roleName" name="roleName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">角色描述</label>
					<div class=" col-xs-6">
							<textarea rows="5" cols="40" name="roleRmk" id="roleRmk"></textarea>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="close_01()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>