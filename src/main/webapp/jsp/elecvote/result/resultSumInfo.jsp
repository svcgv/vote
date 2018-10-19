<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function save() {
		$('#i-form').validate({
			rules:{
				sectName:{
					required: true
				},ownerName:{
					required: true
				},buildCode:{
					required: true
				},unitCode:{
					required: true
				},floorCode:{
					required: true
				},roomCode:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/house/editHouseInfo",success:function(data){
			if(data.status == true){
				layer.alert('房屋信息修改成功！',{icon: 1}, function(index){
					window.parent.qryList();//调用父页面查询方法
					$.indi.closePopup();//关闭页面
				});
			}else{
				layer.alert('房屋信息修改失败！',{icon: 2});
			}
			}});
	}
	function close_0(){
		$.indi.closePopup();
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" id="topicId" name="topicId" value="${result.topicId}" >
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">投票主题</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"  readonly="readonly"
							placeholder="投票主题" id="topicName" name="topicName" value="${topic.topicName}"/>
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">投票开始时间</label>
					<div class="col-xs-3">
						<input class="form-control col-xs-11" type="text" id="voteStartDate" 
						 readonly="readonly" name="voteStartDate" value="${topic.voteStartDate }">
					</div>
					<label class="col-xs-2 control-label text-right">投票结束时间</label>
					<div class="col-xs-3 ">
							<input class="form-control col-xs-11" type="text" id="voteEndDate" 
							readonly="readonly" name="voteEndDate" value="${topic.voteEndDate }">
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">总户数</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" readonly="readonly"
							placeholder="总户数 " id="sumCount" name="sumCount" value="${result.sumCount}"/>
							
					</div>
					<label class="col-xs-2 control-label text-right">总面积</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="sumArea" name="sumArea" 
							readonly="readonly"	value="${result.sumArea}"/>
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">同意户数</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="agreeHou" name="agreeHou" 
							readonly="readonly" value="${result.agreeHou}"/>
					</div>
					<label class="col-xs-2 control-label text-right">同意户数占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							readonly="readonly" id="agreeHouRate" name="agreeHouRate" value="${result.agreeHouRate}%"/>
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">同意面积</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="agreeArea" name="agreeArea"
							readonly="readonly" value="${result.agreeArea}"/>
					</div>
					<label class="col-xs-2 control-label text-right">同意面积占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							readonly="readonly" id="agreeAreaRate" name="agreeAreaRate"  value="${result.agreeAreaRate}%" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">不同意户数</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="unagreeHou" name="unagreeHou" 
							readonly="readonly" value="${result.unagreeHou}"/>
					</div>
					<label class="col-xs-2 control-label text-right">不同意户数占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							 readonly="readonly" id="unagreeHouRate" name="unagreeHouRate" value="${result.unagreeHouRate}%"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">不同意面积</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="unagreeArea" name="unagreeArea" 
							readonly="readonly" value="${result.unagreeArea}"/>
					</div>
					<label class="col-xs-2 control-label text-right">不同意面积占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							 readonly="readonly" id="unagreeAreaRate" name="unagreeAreaRate" value="${result.unagreeAreaRate}%"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">弃权户数</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="quitHou" name="quitHou" 
							readonly="readonly" value="${result.quitHou}"/>
					</div>
					<label class="col-xs-2 control-label text-right">弃权户数占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							 readonly="readonly" id="quitHouRate" name="quitHouRate" value="${result.quitHouRate}%"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">弃权面积</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="quitArea" name="quitArea" 
							readonly="readonly" value="${result.quitArea}"/>
					</div>
					<label class="col-xs-2 control-label text-right">弃权面积占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							 readonly="readonly" id="quitAreaRate" name="quitAreaRate" value="${result.quitAreaRate}%"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">未表决户数</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="nonvoteHou" name="nonvoteHou" 
							readonly="readonly" value="${result.nonvoteHou}"/>
					</div>
					<label class="col-xs-2 control-label text-right">未表决户数占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							 readonly="readonly" id="nonvoteHouRate" name="nonvoteHouRate" value="${result.nonvoteHouRate}%"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">未表决面积</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="nonvoteArea" name="nonvoteArea" 
							readonly="readonly" value="${result.nonvoteArea}"/>
					</div>
					<label class="col-xs-2 control-label text-right">未表决面积占比</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							 readonly="readonly" id="nonvoteAreaRate" name="nonvoteAreaRate" value="${result.nonvoteAreaRate}%"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>