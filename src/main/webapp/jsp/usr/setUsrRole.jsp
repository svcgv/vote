<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('#_usrId').setParentVal();//给当前标签赋值，值为父页面相同name的标签值
		//初始化角色列表
	});

	//查询角色集合
	function qryRoleList() {
		var v =$("#_usrId").val();
		var url = "${ctx}/usr/ajaxQryRoleInfo?usrId="+v;
		$.indi.loadTableByQry({
			url : url
		});
	}

	//关闭当前弹出框
	function close_01() {
		$.indi.colseModle();
	}

	//保存角色
	function saveRole() {
		var objRow = selectRow("td_roleId");
		$("#_roleId").val(objRow.val);
		$.indi.ajaxSubmit({
			url : "${ctx}/usr/addRoleUser",
			closeMode : true,
			success : function(data) {
				layer.alert('角色信息保存成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					window.parent.qryList_01();
				});  
			}
		});
	}
</script>
</head>
<body class="body-modle">
<div class=content>
	<div class="row">
		<form method="post" id="i-form" class="form-horizontal">
			<input type="hidden" name="usrId" id="_usrId" value="${usrId }"> <input
				type="hidden" name="roleId" id="_roleId"> <input
				type="hidden" id="i-pages" name="pages" value="1" />
				<input type="text" style="display:none;">
			<div class="form-group">
				<label class="col-xs-2 control-label text-right">角色名称</label>
				<div class=" col-xs-4">
					<input type="text" class="form-control col-xs-11" id="_roleName"
						name="roleName" placeholder="角色名称" />
				</div>
				<div class="col-xs-2">
					<button type="button" class="btn btn-primary "
						onclick="qryRoleList()"> <i class="icon-search"></i> 查询</button>
				</div>
			</div>
		</form>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<table
				class="table table-bordered table-striped table-hover with-check table-paging">
				<thead>
					<tr>
						<th><i class="icon-resize-vertical"></i></th>
						<th>序号</th>
						<th>角色代码</th>
						<th>角色名称</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listInfo}" var="roleInfo" varStatus="roleSta">
						<tr>
							<td target_data="checkbox">
								<input type="checkbox" <c:if test="${roleInfo.isSelect eq 'Y' }">checked="checked" </c:if>/> 
								<input type="hidden" id="td_roleId" target_data="roleId" value="${roleInfo.roleId }">
							</td>
							<td class="center" target_data="count">${roleSta.count }</td>
							<td class="center" target_data="roleId">${roleInfo.roleId}</td>
							<td class="center" target_data="roleName">${roleInfo.roleName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		<div class="row" align="center">
			<button type="button" class="btn btn-primary" onclick="saveRole()"
				id="btnSave">
				<i class="icon-save"></i> 保存
			</button>
		</div>
	</div>
</body>
</html>