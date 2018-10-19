<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function() {
		window.parent.setPopUpInfo(800, 100, '');
	})
</script>

</head>
<body class="body-modle">
	<div class=content>
		<div class="row">
			<div class="col-md-12 paddtop10">
				<table
					class="table table-bordered table-striped table-paging text-center">
					<thead>
						<tr>
							<th class="collapse">ID</th>
							<th>文件名</th>
							<th>大小</th>
							<th>上传人</th>
							<th>上传时间</th>
							<th>类型</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fileUploads }" var="fileUpload">
							<tr>
								<td class="collapse">${fileUpload.fileId }</td>
								<td><a href="${fileUpload.fileAddre }" target="_blank">${fileUpload.oldFileName }</a></td>
								<td>${fileUpload.fileSize }</td> <!-- 大小 -->
								<td>${fileUpload.uploadUser }</td> <!-- 上传人 -->
								<td>${cm:formatDateTime(fileUpload.uploadDate) }</td> <!-- 上传时间 -->
								<td>${fileUpload.fileType }</td> <!-- 上传类型 -->
								<td>
									<div class="progress">
										<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="0"
											aria-valuemin="90" aria-valuemax="100" style="width: 100%">
											100%</div>
									</div>
								</td>
								<td><a href="${fileUpload.fileAddre }" title="查看附件信息" target="_blank"><i
										class="icon-search"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>