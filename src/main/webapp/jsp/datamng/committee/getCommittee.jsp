<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" name="sqid" value="${info.sqid }" >
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">社区编号</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"  value="${info.sqbm}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">社区名称</label>
					<div class=" col-xs-6">
							<input type="text" class="form-control col-xs-11" id="sqmc" name="sqmc" value="${info.sqmc}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">所属区县</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11" id="hpbmc" name="hpbmc" value="${info.hpbmc}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">所属街道</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11" id="jdmc" name="jdmc" value="${info.jdmc}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">备注</label>
					<div class=" col-xs-6">
						<textarea type="text" class="form-control col-xs-11" placeholder="备注" id="bz" name="bz" rows="5" maxlength="512">${info.bz}</textarea>
					</div>
				</div> 
			</form>
		</div>
	</div>
</body>
</html>