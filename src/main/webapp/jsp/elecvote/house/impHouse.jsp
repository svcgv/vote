<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
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
$(document).ready(function(){
	$('#_file').change(handFile);
});

//处理文件上传
function handFile() {
	var files = this.files;
	var tbodyHtml = $('.table tbody').html();
	$('.table tbody').html(tbodyHtml);
	uploadFile();
}

//导入
function uploadFile() {
	$("#i-form").ajaxSubmit({
		type : "POST",
		url : "${ctx}/expert/loadExcelData.do",
		dataType : "json",
		success : function(data) {
			if(data.listInfo.length>0){
				//alert(data.listInfo[0].expert_name);
				var tbodyHtml = $('.table tbody').html();
				var expert = null;
				for (var i = 0; i < data.listInfo.length; i++) {
					expert = data.listInfo[i];
					tbodyHtml += '<tr><td>'+ (i+1)+'</td>';
					tbodyHtml += '<td>'+ expert.expert_name+'</td>';
					tbodyHtml += '<td>'+ expert.hpbmc+'</td>';
					tbodyHtml += '<td>'+ expert.company_name+'</td>';
					tbodyHtml += '<td>'+ expert.cert_code+'</td>';
					tbodyHtml += '<td>'+ expert.expert_type+'</td>';
					tbodyHtml += '<td>'+ expert.contact_tel+'</td></tr>';
				}
				$('.table tbody').html(tbodyHtml);
			}else{
				layer.alert('加载文件数据为空！',{icon: 2});
			}
		}
	});			
}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" enctype="multipart/form-data" id="i-form" class="form-horizontal">
				<div class="col-md-12">
		          	<!-- File Upload -->
		          	<div class="controls">
						<button class="btn btn-primary">
							<i class="icon-file"></i>&nbsp;&nbsp;选择Excel文件
						</button>
						<input type="file" id="_file" name="file" class="upload-file" multiple="multiple"> 
						<input type="hidden" name="fileTypeId" style="border: 1px solid #ccc;height: 34px;" id="_fileTypeId" value="">
		          	</div>
		        </div>
			</form>
			<div class="col-md-12 paddtop10">
				<table class="table table-bordered table-striped table-paging text-center">
					<thead>
						<tr>
							<th>序号</th>
							<th>专家姓名</th>
							<th>所在区</th>
							<th>所在公司</th>
							<th>证件号码</th>
							<th>专家类别</th>
							<th>联系电话</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>