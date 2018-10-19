<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<style type="text/css">
body {
	font-size: 14px;
}

input {
	vertical-align: middle;
	margin: 0;
	padding: 0
}

.txt {
	height: 34px;
	border: 1px solid #cdcdcd;
	width: 180px;
}

.file {
	position: absolute;
	top: 0;
	height: 34px;
	filter: alpha(opacity : 0);
	opacity: 0;
	width: 100%;
}
</style>

<script type="text/javascript">
	function upload() {
		$("#i-form").ajaxSubmit({
			type : "POST",
			url : "${ctx}/file/fileUpload.upload",
			dataType : "json",
			success : function(data) {
				if (data.status == 'success') {
					//如果文件上传成功需要后续操作,则需要在父页面定义fileUoloadSucc方法
					if (window.parent.fileUoloadSucc) {
						$.indi.closePopup();
						window.parent.fileUoloadSucc(data);
					}else{
						layer.alert('文件上传成功！', {
							icon : 1
						}, function(index) {
							if (window.parent.refreshParent) { //关闭上传框之前需要刷新父页面，则定义refreshParent方法
								window.parent.refreshParent();
							}
							$.indi.closePopup();
						});
					}
					
				} else {
					layer.alert('文件上传失败，请稍候再试！', {
						icon : 2
					});
				}
			}
		});
	}
</script>
</head>
<body class="body-modle">
	<div class=content>
		<div class="row">
			<form class="col-xs-9" id="i-form" name="from-name"
				enctype="multipart/form-data" method="post">
				<input type='text' name='textfield' id='textfield' class='txt' /> <input
					type='button' class='btn btn-primary' value='浏览...' /> <input
					type="file" name="file" class="file" id="fileField" size="28"
					onchange="document.getElementById('textfield').value=this.value" />
			</form>
		</div>
		<div class="row">
			<div class="col-xs-9">
				<input type="button" name="upload"
					class=" col-xs-12 btn btn-primary" value="上传" onclick="upload()" />
			</div>
		</div>
	</div>
</body>
</html>