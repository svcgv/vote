<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	function openAdd() {
		/* $.indi.submit({url: '${ctx }/jsp/usr/addUsrInfo.jsp'}); */
		window.location.href="${ctx }/usr/addUsrInfo";
	}

	function shuxing(){
		var obj = selectRow("usrIdArray");
		var obj = selectRow("usrSta");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行设置！",{icon:0});
			return;
		}if ( obj.val=="已注销") {
			layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
			return;
		}if ( obj.val=="禁用") {
			layer.alert("该机构已经被关闭！",{icon:0});
			return;
		}
		$("#usrId").val(obj.val);
		$.indi.openPopup({title: '设置菜单权限',area : ['600px' , '500px'],url: '${ctx }/jsp/usr/setUsrMeau.jsp'});
	}

	//查询
	function qryList_01() {
		$.indi.loadTableByQry({
			url : "${ctx }/usr/ajaxQryUsrInfo"
		});
	}

	function updUsr() {
		var obj1 = selectRow("usrIdArray");
		var obj = selectRow("usrSta");
		if (obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}if ( obj.val=="已注销") {
			layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
			return;
		}if ( obj.val=="禁用") {
			layer.alert("该机构已经被关闭,无法进行修改！",{icon:0});
			return;
		}
		$("#usrId").val(obj1.val);
		$.indi.submit({url: '${ctx }/usr/openEditUsrInfo'});
	}
	//删除
	function deleUsr() {
		var obj1 = selectRow("usrIdArray");
		 var obj = selectRow("usrSta");
		 if ( obj.val=="已注销") {
				layer.alert("该机构已经被注销！",{icon:0});
				return;
			}
		 if ( obj1.size != 1) {
				layer.alert("请选择一个机构注销！",{icon:0});
				return;
			}
		 $("#usrId").val(obj1.val);
		layer.confirm("确定要注销么？",{icon: 3, title:'提示'},function(index){
			$("#usrId").val(selectRow("usrIdArray").val);
			 $.indi.ajaxSubmit({url:'${ctx }/usr/delUsrInfo',success:function(data){
				layer.alert('注销成功！',{icon: 1}, function(index){
					layer.close(index);
					qryList_01();
				});  
				
			}});
			layer.close(index);
		});	
	}

	//设置
	function setRole(){
		var obj1 = selectRow("usrIdArray");
		var obj = selectRow("usrSta");
		if ( obj1.size != 1) {
			layer.alert("请选择一条记录进行设置！",{icon:0});
			return;
		}if ( obj.val=="已注销") {
			layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
			return;
		}if ( obj.val=="禁用") {
			layer.alert("该机构已经被关闭！",{icon:0});
			return;
		}
		$("#usrId").val(obj1.val);
		$.indi.openPopup({title: '设置用户权限',area : ['700px' , '500px'],url: '${ctx }/usr/setUsrRole'});
	}
	//启用
	function openSta(){
		var obj = selectRow("usrSta");
		var obj1 = selectRow("usrIdArray");
		 if ( obj.size != 1) {
				layer.alert("请选择一条记录启用！",{icon:0});
				return;
			}
		 if ( obj.val=="正常使用") {
				layer.alert("该机构已经启用！",{icon:0});
				return;
			}
		 if ( obj.val=="已注销") {
				layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
				return;
			}
		 $("#usrId").val(obj1.val);
		 $.indi.ajaxSubmit({url:'${ctx }/usr/openStaUsrInfo',success:function(data){
				if(data.status == true){
					layer.alert('启用成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList_01();
					});  
				}else{
					layer.alert('启用失败！',{icon: 2});
				}
			}});
	}
	//关闭
	function closeSta(){
		var obj = selectRow("usrSta");
		var obj1 = selectRow("usrIdArray");
		 if ( obj.size != 1) {
				layer.alert("请选择一条记录关闭！",{icon:0});
				return;
			}
		 if ( obj.val=="禁用") {
				layer.alert("该机构已经被关闭！",{icon:0});
				return;
			}
		 if ( obj.val=="已注销") {
				layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
				return;
			}
		 $("#usrId").val(obj1.val);
		 $.indi.ajaxSubmit({url:'${ctx }/usr/closeStaUsrInfo',success:function(data){
				if(data.status == true){
					layer.alert('关闭成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList_01();
					});  
				}else{
					layer.alert('关闭失败！',{icon: 2});
				}
			}});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				系统管理 <small><i class="icon-double-angle-right"></i>用户管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="usrId" name="usrId" /> <input
						type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2  control-label">用户名</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="exampleInputEmail1" name="userId" placeholder="用户名">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">用户姓名</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="i-usrName"
								name="usrName" placeholder="用户姓名">
						</div>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1" class=" col-md-2 control-label">机构类型</label>
						<div class="col-md-3">
							<select class="form-control" id="orgType"
								name="orgType"> ${cm:createHtmlByCodem('ORG_TYPE','') }
							</select>
						</div>
						<div class="col-md-2"></div>
						<div class="col-md-3">
							<button type="button" class="btn btn-primary "
								onclick="qryList_01()">
								<i class="icon-search"></i> 查询
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="updUsr()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="deleUsr()">
						<i class="icon ion-close-circled"></i> 注销
					</button>
					<button type="button" class="btn btn-primary" onclick="openSta()">
						<i class="icon-unlock"></i> 启用
					</button>
					<button type="button" class="btn btn-primary" onclick="closeSta()">
						<i class="icon-lock"></i> 关闭
					</button>
					<button type="button" class="btn btn-primary" onclick="shuxing()">
						设置菜单
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
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="usrIdArray" target_data="usrId">
							<input type="hidden" id="usrSta" target_data="usrSta"></th>
							<th target_data="usrId">用户名</th>
							<th target_data="usrName">操作员姓名</th>
							<th target_data="orgType">机构类型</th>
							<th target_data="orgName">机构名称</th>
							<th target_data="roleName">角色</th>
							<th target_data="usrSta">状态</th>
						</tr> 
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="usrinfo" varStatus="usrSta">
							<tr>
								<td ><input type="checkbox" /> <input
									type="hidden" id="usrIdArray" target_data="usrId"
									value="${usrinfo.usrId }"><input
									type="hidden" id="usrSta" target_data="usrSta"
									value="${usrinfo.usrSta }"></td>
								<td class="center">${usrinfo.usrId }</td>
								<td class="center">${usrinfo.usrName}</td>
								<td class="center">${usrinfo.orgType}</td>
								<td class="center">${usrinfo.orgName}</td>
								<td class="center">${usrinfo.roleName}</td>
								<td class="center">${usrinfo.usrSta}</td>
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
						url : "${ctx }/usr/ajaxQryUsrInfo"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
