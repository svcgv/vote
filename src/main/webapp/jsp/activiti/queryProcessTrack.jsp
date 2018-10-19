<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="content">
		<div class="row">
			<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover table-paging">
					<thead>
						<tr>
							<th>序号</th>
							<th>处理节点</th>
							<th>处理类型</th>
							<th>处理人</th>
							<th>处理时间</th>
							<th>意见说明</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="appIdea">
							<tr>
								<td class="center">${appIdea.ordNum }</td>
								<td class="center">${appIdea.nodeName }</td>
								<td class="center">${cm:getCodeVal('TASK_TYPE',appIdea.taskType) }</td>
								<td class="center">${appIdea.usrName}</td>
								<td class="center">${appIdea.endTime}</td>
								<td class="center">${appIdea.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
