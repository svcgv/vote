<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		//参数1：表示指定table的ID;参数2：true表示单选；false表示多选
		initTable("tableId",true);
	});

	function qryList_01(){
		$.indi.loadTableByQry({
			url :"${ctx }/activiti/ajaxQeryCompToDo.do"
		});
	}

	//流程跟踪
	function track(){
		var obj = selectRow("_appId_array");
		if (obj.size != 1) {
			layer.alert("请选择一条记录进行办理！", {
				icon : 0
			});
			return;
		}
		$("#_appId").val(obj.val);
		$.indi.openPopup({
			title : '流程跟踪',
			area : [ '700px', '500px' ],
			url : '${ctx }/activiti/processTrack.do'
		});
	}

	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				任务中心 <small><i class="icon-double-angle-right"></i>已办任务</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="_appId" name="appId" /> 
					<input type="hidden" id="_taskId" name="taskId" /> 
					<input
						type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2  control-label">流程类型</label>
						<div class="col-md-3">
							<select name="appType" class="form-control">
								${cm:createHtmlByCode('APP_TYPE')}
							</select>
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
					<!-- <button type="button" class="btn btn-primary" onclick="handle()">
						<i class="icon-plus-sign"></i> 办理
					</button> -->
					&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" onclick="track()">
						<i class="icon-plus-sign"></i> 流程跟踪
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table
						class="table table-bordered table-striped table-hover with-check table-paging" array="appList" page="pageInfo" id="tableId">
						<thead>
							<tr>
								<th target_data="checkbox"><i class="icon-resize-vertical"></i>
									<input type="hidden" id="_appId_array" target_data="appId">
								</th>
								<th target_data="appId">流程ID</th>
								<th target_data="appName">流程名称</th>
								<th target_data="createId">提交人</th>
								<th target_data="startTime">开始时间</th>
								<th target_data="endTime">结束时间</th>
								<th target_data="currNodeName">当前节点</th>
								<th target_data="currStatus" type="code" codeNo="CURR_STATUS">流程状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${appList}" var="app" varStatus="sta">
								<tr>
									<td>
										<input type="checkbox" />
										<input type="hidden" id="_appId_array" target_data="appId" value="${app.appId }">
									</td>
									<td class="center">${app.appId }</td>
									<td class="center">${app.appName }</td>
									<td class="center">${app.createId}</td>
									<td class="center">${app.startTime} </td>
									<td class="center">${app.endTime}</td>
									<td class="center">${app.currNodeName}</td>
									<td class="center">${cm:getCodeVal('CURR_STATUS',app.currStatus) }</td>
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
						url : "${ctx }/activiti/ajaxQeryCompToDo.do"
					}
					$.indi.loadPages(pages);
				</script>
			</div>
		</div>
	</div>
</body>
</html>
