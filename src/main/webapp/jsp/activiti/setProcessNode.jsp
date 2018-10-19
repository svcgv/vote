<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		window.parent.setTitleWh('600','420','选择下一步');
	});


	//提交
	function commit() {
		$.indi.ajaxSubmit({
			url : "${ctx }/activiti/commitProcessByRole.do",
			success : function(data) {
				parent.parent.layer.msg("流程提交成功！", {
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
				<button type="button" class="btn btn-primary" onclick="commit()"
					id="btnSave">
					<i class="icon-save"></i> 提交
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
				<div class="col-xs-12">
					<input type="hidden" name="appId" value="${app.appId}">
					<input type="hidden" name="taskId" value="${app.taskId}">
					<!-- 节点列表 -->
					<span class="col-xs-9" style="color: #468ebc">↓↓请选择节点</span> <select
						id="_nodeList" name="nodeId" class="col-xs-6 form-control"
						size="8">
						<c:forEach items="${listNode}" var="node" varStatus="sta">
							<option value="${node.nodeId}">${node.nodeName }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-xs-12 paddtop5">
					<label class="col-xs-2  text-right">审核意见</label>
					<div class="col-xs-9">
						<textarea class="form-control" rows="5" name="memo"></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>