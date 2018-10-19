<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//初始化请求后台加载数据
	$(document).ready(function(){
		$("#hidden1").hide();
		$("#hidden2").hide();
		var menulevel=$("#menu_level").val();
		if(menulevel=="2"){
			$("#hidden1").show();
	     	$("#hidden2").show();
		}
	}); 
	
	function save() {
		$('#i-form').validate({
			menu:{
				menuName:{
					required: true
				},
				menuLevel:{
					required: true
				},		
				menuUrl:{
					required: true
				},
				parentId:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url:"${ctx }/menu/editSave"/* ,closeMode:true */,success:function(data){
			if(data.success == true){
				layer.alert('菜单信息修改成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					window.parent.qryList();
				});  
			}else{
				layer.alert('菜单信息修改失败！',{icon: 2});
			}
		}});
	}
	 
	
	
</script>
</head>
<body class="body-modle">
	<div  class="content" >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" id="menuId" name="menuId"   value="${menu.menuId} "  >
				<input type="hidden" id="parentIdBy" name="parentIdBy" value="${menu.parentId}"  >
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">菜单名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="菜单名称" id="menu_name" name="menuName"   value="${menu.menuName}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">标签样式</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="标签样式" id="menu_name" name="cssIcon" value="${menu.cssIcon}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">顺序编号</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="顺序编号" id="sortNum" name="sortNum" value="${menu.sortNum}"/>
					</div>
				</div>
				<div class="form-group" >
					<label class="col-xs-4 control-label text-right">菜单级别</label>
					<div class=" col-xs-6" >
						<select  class="form-control col-xs-11 " id="menu_level" name="menuLevel" onchange="show();"  disabled   value="${menu.menuLevel}" >
						${menu.levelHtml}
						</select>
					</div>
				</div>
				<div class="form-group" id="hidden1">
					<label class="col-xs-4 control-label text-right">父菜单</label>
					<div class=" col-xs-6">
						 <select  class="form-control col-xs-11 " id="parent_id" name="parentId"  <%--  value="${menu.parentId}"  --%>  >
							  ${menu.parentHtml }
						</select> 
						 
					</div>
				</div>
				
				<div class="form-group" id="hidden2">
					<label class="col-xs-4 control-label text-right">菜单地址</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="菜单地址" id="menu_url" name="menuUrl"   value="${menu.menuUrl}"/>
					</div>
				</div>
				
				
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="javascript:$.indi.closePopup();">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>