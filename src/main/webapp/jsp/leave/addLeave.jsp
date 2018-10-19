<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function save() {
		$.indi.ajaxSubmit({url: "${ctx}/activiti/saveLeaveInfo",closeMode:true,success:function(data){
				 close_01();
				 window.parent.qryList_01();
				
			}});
	}

	//关闭当前弹出框
	function close_01(){
		$.indi.closePopup();
	}

	function commit(){
		$.indi.ajaxSubmit({url: "${ctx}/activiti/saveLeaveInfo",closeMode:true,success:function(data){
			$("#volId_").val(data.volCopy.voaId);
			$.indi.ajaxSubmit({url: "${ctx}/activiti/startProcess",closeMode:true,success:function(data){
				$("#_appId").val(data.app.appId);
				$.indi.submit({url:'${ctx }/activiti/queryNextNodeByView.do'});
			}});
		}});
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" id="volId_" name="volId" /> 
				<input type="hidden" id="_appId" name="appId" />
				<div class="form-group">
					<label class="col-xs-4 control-label text-right" >请假原因</label>
					<div class=" col-xs-6">
						<textarea  class="form-control col-xs-11"  name="voaDesc"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">请假时间</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="姓名"  name="voaDate"  onClick="WdatePicker()" />
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 暂存
					</button>
					<button type="button" class="btn btn-primary" onclick="commit()">
						<i class="icon-save"></i> 提交
					</button>
					<button type="button" class="btn btn-primary" onclick="close_01()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>