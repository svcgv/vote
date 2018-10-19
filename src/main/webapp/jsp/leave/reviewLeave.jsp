<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function review(flg) {
		$("#reviewFlg_").val(flg);
		$.indi.ajaxSubmit({url: "${ctx}/activiti/reviewNext",closeMode:true,success:function(data){
				 alert("处理成功！");
			}});
	}

	//关闭当前弹出框
	function close_01(){
		$.indi.colseModle();
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" id="reviewFlg_" name="reviewFlg"/>
				<input type="hidden" id="leaveId_" name="leaveId" value="${param.leaveId} ">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">姓名</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="姓名"  name="usrAnme" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right" >请假原因</label>
					<div class=" col-xs-6">
						<textarea  class="form-control col-xs-11"  name="reason"></textarea>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="review('1')">
						<i class="icon-save"></i> 通过
					</button>
					<button type="button" class="btn btn-primary" onclick="review('0')">
						<i class="icon-save"></i> 驳回
					</button>
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
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