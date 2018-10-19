<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//部署模版
	function deploy(){
		$.indi.ajaxSubmit({
			url : "${ctx }/process/deployProcess",
			success : function(data){
				if(data != null){
					layer.alert("["+data.processName+"]"+data.msg,{icon:1});
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
				系统管理 <small><i class="icon-double-angle-right"></i>流程部署</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<div class="form-group">
						<label for="fileName" class="col-md-2  control-label">流程文件名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="_fileName" name="fileName" placeholder="流程文件名">
						</div>
						<label for="fileName" class="col-md-2  control-label">流程名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="_processName" name="processName" placeholder="流程名称">
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary" onclick="deploy()">
								<i class="icon-plus-sign"> 流程部署</i> 
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
