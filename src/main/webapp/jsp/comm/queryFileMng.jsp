<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function() {
		window.parent.setPopUpInfo(800, 300, '');
		$('#_file').change(handFile);
	})

	//处理文件上传
	function handFile() {
		var files = this.files;
		var tbodyHtml = $('.table tbody').html();
		for (var i = 0; i < files.length; i++) {
			var file = files[i];
			tbodyHtml += '<tr><td class="collapse"></td><td>' + file.name
					+ '</td>';
			tbodyHtml += '<td>'
					+ (parseFloat(parseFloat(file.size) / 1024)).toFixed(2)
					+ 'KB</td>';
			tbodyHtml+='<td>-</td>';  //上传人
			tbodyHtml+='<td>'+$.basic.formatDateTime(new Date())+'</td>';  //上传时间
			tbodyHtml+='<td>'+$('#_fileType').val()+'</td>';  //类型
			tbodyHtml += '<td><div class="progress"><div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="0"aria-valuemin="90" aria-valuemax="100" style="width: 50%">50%</div></div></td>';
			tbodyHtml += '<td><a href="javascipt:void(0)" title="删除文件"  onclick="del(this,null)"><i class="icon-trash" ></i></a></td></tr>';
		}
		$('.table tbody').html(tbodyHtml);
		uploadFile();
	}

	function uploadFile() {
		$("#_form")
				.ajaxSubmit(
						{
							type : "POST",
							url : "${ctx}/file/fileUploadMulti.upload",
							dataType : "json",
							//contentType:"multipart/form-data",
							success : function(data) {
								if (data.length > 0) {
									var trs = $('.table tbody').children();
									for (var i = 0; i < data.length; i++) {
										if (data[i].status == 'success') {
											var statusVal = '<div class="progress"><div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="0"aria-valuemin="90" aria-valuemax="100" style="width: 100%">100%</div></div>';
											var delVal = '<a href="javascipt:void(0)" title="删除文件1" onclick="del(this,'+data[i].fileId+')"><i class="icon-trash" ></i></a>';
											var fileNameVal='<a href="'+data[i].relaPath+'" target="_blank">'+data[i].fileName+'</a>';
										} else {
											var statusVal = '上传失败';
											var delVal = '<a href="javascipt:void(0)" title="删除文件" onclick="del(this,'+data[i].fileId+')"><i class="icon-trash" ></i></a>'
										}
										for (var j = 0; j < trs.length; j++) {
											if (data[i].fileName == trs[j].children[1].innerText) {
												trs[j].children[1].innerHTML = fileNameVal;
												trs[j].children[3].innerText = data[i].uploadUser;
												trs[j].children[6].innerHTML = statusVal;
												trs[j].children[7].innerHTML = delVal;
												trs[j].children[0].innerText = data[i].fileId;
											}

										}
									}
								} else {
									layer.alert('上传情况未知，请刷新页面查看！', {
										icon : 0
									});
								}
							}
						})

	}

	//删除文件
	function del(obj,fileId) {
		$('#_fileId').val(fileId);
		$.indi.ajaxSubmit({
			url : '${ctx}/file/delFiles.do',
			dataType : "json",
			success : function(data) {
				if(parseInt(data) == 1){
					var index = $(obj).parents("tr").index();
					$('.table').delRow(index);
					parent.layer.alert('删除成功！', {
						icon : 1
					});
				}else{
					parent.layer.alert('删除失败！', {
						icon : 0
					});
				}
			}
		})
	}
</script>

</head>
<body class="body-modle">
	<div class=content>
		
		<div class="row">
			<div class="col-md-12">
				<form id="_form" enctype="multipart/form-data" method="post">
					<button class="btn btn-primary">
						<i class="icon-file"></i>&nbsp;&nbsp;选择文件
					</button>
					<input type="file" id="_file" name="file" class="upload-file"
						multiple="multiple"> <input type="hidden" name="relaTable"
						value="${relaTable}"> <input type="hidden"
						name="relaTabId" value="${relaTabId}"> <input
						type="hidden" name="fileId" id="_fileId">
						
					&nbsp;&nbsp;&nbsp;&nbsp;<span>文件类型:&nbsp;</span>
					<input type="text" name="fileType" style="border: 1px solid #ccc;height: 34px;" id="_fileType" value="">
					<input type="hidden" name="fileTypeId" style="border: 1px solid #ccc;height: 34px;" id="_fileTypeId" value="${relaTypeId}">
				</form>
			</div>
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
								<td><a href="javascipt:void(0)" title="删除文件1"
									onclick="del(this,'${fileUpload.fileId }')"><i
										class="icon-trash"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>