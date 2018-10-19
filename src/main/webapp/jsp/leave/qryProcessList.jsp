<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//打开流程新增页面
	function openAdd(){
		$.indi.openPopup({title: '角色新增',area : ['550px' , '400px'],url: '${ctx }/jsp/leave/addLeave.jsp'});
	}

	function commit(){
		var obj = selectRow("voaId_h");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行设置！",{icon:0});
			return;
		}
		$("#volId_").val(obj.val);
		var currstatus = selectRow("currStatus_h").val;
		if(currstatus != "00"){
			layer.alert("当前请假单已发起流程，请勿重复发起！",{icon:0});
			return;
		}
		$.indi.ajaxSubmit({
			url : "${ctx }/activiti/startProcess",
			success : function(data){
				if(data == true || data == "true"){
					layer.alert("流程发起成功！",{icon:1});
				}
			}
		});
	}

</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				系统管理 <small><i class="icon-double-angle-right"></i>请假管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="volId_" name="volId" /> <input
						type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2  control-label">用户编码</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="exampleInputEmail1" name="userId" placeholder="用户编码">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">用户姓名</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="i-usrName"
								name="usrName" placeholder="用户姓名">
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary "
								onclick="qryList_01()">
								<i class="icon-search"></i> 查询
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()">
						<i class="icon-plus-sign"> 新增</i> 
					</button>
					<button type="button" class="btn btn-primary" onclick="commit()">
						<i class="icon-edit"></i> 提交
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th><i class="icon-resize-vertical"></i></th>
							<th>序号</th>
							<th>请假时间</th>
							<th>请假原因</th>
							<th>当前状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listVol}" var="vol" varStatus="sta">
							<tr>
								<td target_data="checkbox"><input type="checkbox" /> 
								<input type="hidden" id="voaId_h" target_data="voaId" value="${vol.voaId }">
								<input type="hidden" id="currStatus_h" target_data="currStatus" value="${vol.currStatus }">
								</td>
								<td class="center" target_data="count">${sta.count }</td>
								<td class="center" target_data="voaDate">${vol.voaDate }</td>
								<td class="center" target_data="voaDesc">${vol.voaDesc }</td>
								<td class="center" target_data="currStatus">${vol.currStatus }</td>
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
