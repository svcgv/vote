<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$(":checkbox").click(function() {
			if ($(this).attr("checked") != undefined) {
				$(":checkbox").removeAttr('checked');
				$(this).attr("checked", true);
			}
		});
	});

	function really() {
		var orgId = selectRow('orgIdArray').val;
		var orgType = selectRow('orgTypeArray').val;
		var orgName = selectRow('orgNameArray').val;
		$('#_orgNo', window.parent.document).val(orgId);
		$('#orgType', window.parent.document).val(orgType);
		$('#_orgName', window.parent.document).val(orgName);
		$.indi.closePopup(); //关闭当前页面
	}

	//查询
	function qryList01(){
		var orgType = $("#orgType").val();
		var url="${ctx }/usr/ajaxQryopenUserOrgInfo?orgType="+orgType;
		$.indi.loadTableByQry({
			url : url
		});
	}
</script>
</head>
<body class="body-modle">
	<div class=content>
		<div class="row">
				<form method="post" id="i-form" class="form-horizontal">
					<input type="hidden" name="orgType" id="orgType" value="${orgType }">
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<label class="col-xs-2 control-label text-right">机构名称</label>
						<div class=" col-xs-5">
							<input type="text" class="form-control"  name="orgName" />
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-primary"
								onclick="qryList01()">
								<i class="icon-search"></i> 查询
							</button>
						</div>
					</div>
				</form>
			<div class="col-xs-12">
				<button type="button" class="btn btn-primary" onclick="really()">
					<i class="icon-ok"></i> 确定
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th  target_data="checkbox">
							<i class="icon-resize-vertical"></i>
							<input type="hidden"  id="orgIdArray" target_data="orgNo">
							<input type="hidden" id="orgTypeArray" target_data="orgType">
							<input type="hidden" id="orgNameArray" target_data="orgName">
							</th>
							<th  target_data="count">序号</th>
							<th  target_data="orgName">机构名称</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="orgInfo" varStatus="status">
							<tr>
								<td ><input type="checkbox" /> 
								<input type="hidden" id="orgIdArray" value="${orgInfo.orgNo }">
								<input type="hidden" id="orgTypeArray" value="${orgInfo.orgType }">
								<input type="hidden" id="orgNameArray" value="${orgInfo.orgName }">
								</td>
								<td class="center">${status.count }</td>
								<td class="center">${orgInfo.orgName }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
				var orgType = $("#orgType").val();
				var url="${ctx }/usr/ajaxQryopenUserOrgInfo?orgType="+orgType;
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
</body>
</html>