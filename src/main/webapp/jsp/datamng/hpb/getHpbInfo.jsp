<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal" role="form">
				<fieldset class="content">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">区县编码</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"  value="${hpb.hpbbm}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">县区级别</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"  value="${cm:getCodeVal('REGION_TYPE',hpb.hpblx) }"/>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">区县名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"  value="${hpb.hpbbm}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">备注</label>
					<div class=" col-xs-6">
						<textarea type="text" class="form-control col-xs-11" rows="5">${hpb.bz}</textarea>
					</div>
				</div> 
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>