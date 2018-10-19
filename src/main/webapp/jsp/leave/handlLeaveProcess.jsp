<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(function(){
		window.parent.setTitleWh('600','300','请假流程处理');
	})
	
	function save() {
		$.indi.ajaxSubmit({
			url : "${ctx}/activiti/saveLeaveInfo",
			closeMode : true,
			success : function(data) {
				window.parent.qryList_01();
			}
		});
	}

	function commit() {
		$.indi.submit({
			url : "${ctx }/activiti/queryNextNodeByView.do"
		});
	}

	function reject(){
		$.indi.submit({url:'${ctx }/activiti/openRejectMemo.do'});
	}

	//关闭当前弹出框
	function close_01() {
		$.indi.closePopup();
	}
</script>
</head>
<body >
	<div class=content>
		<div class="row">
			<div class="col-xs-12">
				<button type="button" class="btn btn-primary" onclick="commit()">
					<i class="icon-save"></i> 审核通过
				</button>
				<button type="button" class="btn btn-primary" onclick="reject()">
					<i class="icon-remove"></i> 驳回
				</button>
				<button type="button" class="btn btn-primary" onclick="close_01()">
					<i class="icon-remove"></i> 暂存
				</button>
				<button type="button" class="btn btn-primary" onclick="close_01()">
					<i class="icon-remove"></i> 取消
				</button>
			</div>
		</div>
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden"  name="volId" value="${vol.voaId}"/> 
				<!-- 所有流程提交的时候必须提交两个参数  appId和taskId -->
				<input type="hidden"  name="appId" value="${app.appId}" />
				<input type="hidden"  name="taskId" value="${app.taskId}" />
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">请假原因</label>
					<div class=" col-xs-6">
						<textarea class="form-control col-xs-11" name="voaDesc" >${vol.voaDesc }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">请假时间</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11" placeholder="姓名"
							name="voaDate" value="${vol.voaDate} "/>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>