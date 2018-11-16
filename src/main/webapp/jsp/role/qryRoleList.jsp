<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//页面加载时初始化页面数据
	$(document).ready(function() {

	});

	function openAdd() {
		$.indi.openPopup({
			title : '角色新增',
			area : [ '550px', '400px' ],
			url : '${ctx }/role/openAddRule'
		});

	}
	//查询
	function qryList_01() {
		$.indi.loadTableByQry({
			url : "${ctx}/role/ajaxQryRoleInfo"
		});
	}
	//重置
	function reset() {
		 
		 $("#roleName").val("");
		 $("#tmSmp").val("");
	}
	
	function updPara() {
		var obj = selectRow("RoleIdArray");
		if (obj.size != 1) {
			layer.alert("请选择一条记录进行修改！", {
				icon : 0
			});
			return;
		}
		$("#_roleId").val(obj.val);
		$.indi.openPopup({
			title : '角色修改',
			area : [ '550px', '400px' ],
			url : '${ctx }/role/qryRoleInfoById'
		});
	}
	//删除
	function deleUsr() {
		layer.confirm("确定要删除么？", {
			icon : 3,
			title : '提示'
		}, function(index) {
			var obj = selectRow("RoleIdArray");
			$("#_roleId").val(obj.val);
			$.indi.ajaxSubmit({
				url : '${ctx }/role/delRoleInfo',
				success : function(data) {
					if (data.status == true) {
						layer.alert('角色删除成功！', {
							icon : 1
						}, function(index) {
							layer.close(index);
							qryList_01();
						});
					} else {
						layer.alert('角色删除失败！', {
							icon : 2
						});
					}
				}
			});
			layer.close(index);
		});

	}
	function assignment() {
		var obj = selectRow("RoleIdArray");
		if (obj.size != 1) {
			layer.alert("请选择一条记录进行分配权限！", {
				icon : 0
			});
			return;
		}
		$("#_roleId").val(obj.val);
		$.indi.openPopup({
			title : '角色信息分配权限',
			area : [ '550px', '600px' ],
			url : '${ctx }/role/AddRule'
		});

	}

	function btnassign() {
		var obj = selectRow("RoleIdArray");
		if (obj.size != 1) {
			layer.alert("请选择一条记录进行设置！", {
				icon : 0
			});
			return;
		}
		$("#_roleId").val(obj.val);
		$.indi.openPopup({
			title : '设置角色按钮',
			area : [ '700px', '500px' ],
			url : '${ctx }/btnRole/qryBtnInfo.do'
		});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1>
				系统管理 <small><i class="icon-double-angle-right"></i>角色管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="_roleId" name="roleId" /> <input
						type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<!-- <label for="exampleInputEmail1" class="col-md-2 control-label">角色编码</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="tmSmp"
								name="roleId" placeholder="角色编码" onClick="WdatePicker()"/>
						</div> -->

						<label for="exampleInputEmail1" class="col-md-2  control-label">角色名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="roleName"
								name="roleName" placeholder="角色名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">创建时间</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="tmSmp"
								name="tmSmp" placeholder="创建时间" onClick="WdatePicker()" />
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary "
								onclick="qryList_01()">
								<i class="icon-search"></i>查询
							</button>
							<button type="button" class="btn btn-primary "
								onclick="reset()">
								<i class="icon-remove"></i>重置
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="updPara()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="deleUsr()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary"
						onclick="assignment()">
						<i class="icon-plus-sign"></i> 分配权限
					</button>
					<button type="button" class="btn btn-primary" onclick="btnassign()">
						<i class="icon-plus-sign"></i> 按钮分配
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table
						class="table table-bordered table-striped table-hover with-check table-paging">
						<thead>
							<tr>
								<th target_data="checkbox"><i class="icon-resize-vertical"></i>
									<input type="hidden" id="RoleIdArray" target_data="roleId">
								</th>
								<th target_data="count">序号</th>
								<th target_data="roleId">角色编码</th>
								<th target_data="roleName">角色名称</th>
								<th target_data="roleRmk">角色描述</th>
								<!-- <th target_data="tmSmp" type="date">创建时间</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listInfo}" var="roleinfo" varStatus="roleSta">
								<tr>
									<td target_data="checkbox"><input type="checkbox" /> <input
										type="hidden" id="RoleIdArray" target_data="roleId"
										value="${roleinfo.roleId }"></td>
									<td class="center">${roleSta.count }</td>
									<td class="center">${roleinfo.roleId }</td>
									<td class="center">${roleinfo.roleName }</td>
									<td class="center">${roleinfo.roleRmk }</td>
									<%-- <td class="center" >${cm:formatFromDB(roleinfo.tmSmp)}</td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
					var pages = {
						total : "${pageInfo.total}",
						pageNum : "${pageInfo.pageNum}",
						pageSize : "${pageInfo.pageSize}",
						pages : "${pageInfo.pages}",
						url : "${ctx }/role/ajaxQryRoleInfo"
					}
					$.indi.loadPages(pages);
				</script>
			</div>
		</div>
	</div>
</body>
</html>
