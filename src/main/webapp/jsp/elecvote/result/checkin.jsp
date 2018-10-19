<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
<script type="text/javascript">
	function save() {
		$("#i-form").ajaxSubmit({
			type : "POST",
			url : "${ctx}/result/checkinSubmit",
			dataType : "json",
			success : function(data) {
				alert("提交成功");
				$.indi.closePopup();//关闭页面
			},
			error: function(data){
				alert("提交失败");
				$.indi.closePopup();//关闭页面
			}
			
		});
	}
	
	function close_0(){
		$.indi.closePopup();
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" enctype="multipart/form-data" class="form-horizontal">
				<input type="hidden" id="topic_id" name="topicId" value="${topic.topicId}">
				<input type="hidden" id="infoId" name="infoId" value="${house.infoId}" >
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">议题名称</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" 
							 readonly="readonly" id="topicName" name="topicName" value="${topic.topicName }"/>
					</div>
					<label class="col-xs-2 control-label text-right">小区名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" readonly="readonly"
							placeholder="小区名" id="sectName" name="sectName" value="${house.sectName}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">楼栋号</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="buildCode" name="buildCode" 
							readonly="readonly" placeholder="楼栋号" value="${house.buildCode }"/>
					</div>
					<label class="col-xs-2 control-label text-right">单元号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" readonly="readonly"
							id="unitCode" name="unitCode" placeholder="单元号" value="${house.unitCode }"/>
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">楼层号</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="floorCode" name="floorCode"
						readonly="readonly"	placeholder="楼层号" value="${house.floorCode }"/>
					</div>
					<label class="col-xs-2 control-label text-right">房屋号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" readonly="readonly"
							placeholder="房屋号" id="roomCode" name="roomCode"  value="${house.roomCode }" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件上传</label>
					<div class=" col-xs-6 " >
						<input type="file" id="_file" name="file"> 
						<input type="hidden" name="fileTypeId" style="border: 1px solid #ccc;height: 34px;" id="_fileTypeId" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">手工表决:</label>
					<label class="col-xs-1 control-label text-right">同意</label>
					<div class=" col-xs-1">
						<input type="radio" class="form-control col-xs-11" name="result" id="radio1" value="01">
					</div>
					<label class="col-xs-1 control-label text-right">不同意</label>
					<div class=" col-xs-1">
						<input type="radio" class="form-control col-xs-11" name="result" id="radio2" value="02">
					</div>
				</div>
			</form>	
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 提交
					</button>
					<button type="button" class="btn btn-primary" onclick="close_0()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
		</div>
	</div>
</body>
</html>