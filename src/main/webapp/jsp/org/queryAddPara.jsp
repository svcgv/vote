<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
  var orgIdList = ""; //选择的用户
	//页面加载时初始化页面数据
	function openAdd() {
	    var obj1=selectRow("orgNameArray");
		var obj = selectRow("orgIdArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行确认！",{icon:0});
			return;
		}
		 window.parent.$("#parentOrgName").val(obj1.val)
		 window.parent.$("#parentOrgNo").val(obj.val)
		 $.indi.closePopup();
  }
	//查询
	function qryList_01() {
		var orgType = $("#orgType").val();
		var url="${ctx }/org/ajaxQryqueryAddPara?orgType="+orgType;
		$.indi.loadTableByQry({
			url : url
		});
	}
	function close_01() {
		 $.indi.closePopup();
	}
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<input type="hidden" id="orgType" name="orgType" value="${orgType }"/>
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="orgNo" name="orgNo" /> <input
						type="hidden" id="i-pages" name="pages" value="1" />
	
					<div class="form-group">
					<label class="col-xs-3 control-label text-right">机构名称</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-11" id="orgName"
							name="orgName" placeholder="机构名称" />
					</div>
					<div class="col-xs-2">
						<button type="button" class="btn btn-primary "
							onclick="qryList_01()"> <i class="icon-search"></i> 查询</button>
					</div>
				</div>
				</form>
					<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()">
						    <i class="icon-plus-sign"></i> 选择
					        </button>
					        <button type="button" class="btn btn-primary" onclick="close_01()">
						    <i class="icon-remove"></i> 关闭
					        </button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr><th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="orgIdArray" target_data="orgNo">
							<input type="hidden" id="orgNameArray" target_data="orgName">
							</th>
							<th target_data="count">序号</th>
							<th target_data="orgName">机构名称</th>
							<th target_data="addres">地址</th>
							<th target_data="operUsr">创建人</th>
							<th target_data="tmSmp">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="orginfo" varStatus="orgSta">
							<tr>
								<td target_data="checkbox"><input type="checkbox" /> <input
									type="hidden" id="orgIdArray" target_data="orgNo"
									value="${orginfo.orgNo }"><input
									type="hidden" id="orgNameArray" target_data="orgName"
									value="${orginfo.orgName }"></td>
								<td class="center" target_data="count">${orgSta.count }</td>
								<td class="center" target_data="orgName">${orginfo.orgName }</td>
								<td class="center" target_data="addres">${orginfo.addres}</td>
								<td class="center" target_data="operUsr">${orginfo.operUsr}</td>
								<td class="center" target_data="tmSmp">${orginfo.tmSmp}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
				var orgType = $("#orgType").val();
				var url="${ctx }/org/ajaxQryqueryAddPara?orgType="+orgType;
					var pages = {
						total : "${pageInfo.total}",
						pageNum : "${pageInfo.pageNum}",
						pageSize : "${pageInfo.pageSize}",
						pages : "${pageInfo.pages}",
						url : "${ctx }/org/ajaxQryqueryAddPara"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
