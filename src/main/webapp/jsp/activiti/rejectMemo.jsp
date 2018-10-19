<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//提交
	function reject(){
		$.indi.ajaxSubmit({
			url : "${ctx }/activiti/rejectProcess.do",
			success : function(data) {
				parent.parent.layer.msg("流程驳回成功！", {
					icon : 6
				});
				$.indi.closePopup();
				if(typeof(window.parent.qryList_01) != "undefined"){
					window.parent.qryList_01();
				}
			}
		});
	}
	
</script>
</head>
<body class="body-modle">
	<div class=content>
		<div class="row">
			<div class="col-xs-10">
				<button type="button" class="btn btn-primary" onclick="reject()"
					id="btnSave">
					<i class="icon-save"></i> 驳回
				</button>
				<button type="button" class="btn btn-primary"
					onclick="javascript:history.back();">
					<i class="icon-remove"></i> 取消
				</button>
			</div>
		</div>
		<!--  -->
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" name="appId" value="${obj.appId}"/>
				<input type="hidden" name="taskId" value="${obj.taskId}"/>
				<div class="col-xs-12 paddtop5">
					<label class="col-xs-2  text-right">驳回意见</label>
					<div class="col-xs-9">
						<textarea class="form-control" rows="5" name="memo"></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>