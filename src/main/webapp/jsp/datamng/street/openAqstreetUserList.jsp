<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	function openAddUser() {
		var streetId = $("#jdid").val();
		window.location.href="${ctx }/street/addUsrInfo?jdid="+streetId;
	}

	//修改
	function saveEdit() {
		var obj = selectRow("_tab_admId");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#usrId").val(obj.val);
		var usrId = $("#usrId").val();
		window.location.href="${ctx }/street/openEditUsrInfo?usrId="+usrId;
	}
	//删除
	function deleUsr() {
		var obj = selectRow("_tab_admId");
		 if ( obj.size == 0) {
				layer.alert("请至少选择一条记录进行删除！",{icon:0});
				return;
			}
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $("#usrId").val(obj.val);
			 $.indi.ajaxSubmit({url:'${ctx }/usr/delUsrInfo',success:function(data){
				layer.alert('用户删除成功！',{icon: 1}, function(index){
					layer.close(index);
					history.go(0);
				});  
				
			}});
			layer.close(index);
		});	
	}
	function close_01() {
		window.location.href="${ctx }/street/qryStreet";
	}	
	//设置
	function setRole(){
		var obj = selectRow("_tab_admId");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行设置！",{icon:0});
			return;
		}
		$("#usrId").val(obj.val);
		$.indi.openPopup({title: '设置用户权限',area : ['700px' , '500px'],url: '${ctx }/street/setUsrRole'});
	}
	//刷新
	function qryList_01() {
		window.location.reload(); 
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1>
				基础数据管理 <small>街道管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
			<input type="hidden" id="jdid" name="jdid" value="${info.jdid }" >
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" id="usrId" name="usrId" /> <input
						type="hidden" id="i-pages" name="pages" value="1" />
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">区县名称</label>
					<div class=" col-xs-6">
					<label class="col-md-8  control-label">${info.hpbmc }</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">街道编号</label>
					<div class=" col-xs-6">
						<label class="col-md-8  control-label">${info.jdbm }</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">街道名称</label>
					<div class=" col-xs-6">
						<label class="col-md-8  control-label">${info.jdmc }</label>
					</div>
				</div>
			</form>
			<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAddUser()">
						<i class="icon-plus-sign"></i> 添加
					</button>
					<button type="button" class="btn btn-primary" onclick="saveEdit()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="deleUsr()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="setRole()">
						 设置角色
					</button>
				</div>
		</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="_tab_admId" target_data="usrId" >
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="15%" target_data="roleName">岗位</th>
							<th width="15%" target_data="usrName">姓名</th>
							<th width="15%" target_data="email">邮箱</th>
							<th width="15%" target_data="mblNo">手机</th>
							<th width="15%" target_data="usrId">登录名</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="listInfo" varStatus="listInfoSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_admId" target_data="id" value="${listInfo.usrId }">								</td>
								<td class="center" >${listInfoSta.count }</td>
								<td class="center" >${listInfo.roleName}</td>
								<td class="center" >${listInfo.usrName}</td>
								<td class="center" >${listInfo.email}</td>
								<td class="center" >${listInfo.mblNo}</td>
								<td class="center" >${listInfo.usrId}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
				var streetId = $("#streetId").val();
				var url = "${ctx }/street/ajaxQryopenAqStreetUserListInfo?streetId="+streetId;
					var pages = {
						total : "${pageInfo.total}",
						pageNum : "${pageInfo.pageNum}",
						pageSize : "${pageInfo.pageSize}",
						pages : "${pageInfo.pages}",
						url : url
					}
					$.indi.loadPages(pages);
				</script>
				</div>
				<div class=" form-group" align="center">
						<button type="button" class="btn btn-primary" onclick="close_01()">
							<i class="icon-remove"></i> 返回
						</button>
					</div>
			</div>
		</div>
</body>
</html>
