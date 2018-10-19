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
				<input type="hidden" id="infoId" name="infoId" value="${house.infoId}" >
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" readonly="readonly"
							placeholder="张三" id="sectName" name="sectName" value="${house.sectName}"/>
							
					</div>
					<label class="col-xs-2 control-label text-right">业主姓名</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="ownerName" name="ownerName" 
							value="${house.ownerName}"/>
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">楼栋号</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="buildCode" name="buildCode" 
							value="${house.buildCode}"/>
					</div>
					<label class="col-xs-2 control-label text-right">单元号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							id="unitCode" name="unitCode" value="${house.unitCode}"/>
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">楼层号</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="floorCode" name="floorCode"
							value="${house.floorCode}"/>
					</div>
					<label class="col-xs-2 control-label text-right">房屋号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="房屋号" id="roomCode" name="roomCode"  value="${house.roomCode}" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="certType" name="certType" 
							value="身份证"/>
					</div>
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="certCode" name="certCode" value="${house.certCode}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">房屋面积</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="infoArea" name="infoArea" 
							value="${house.infoArea}"/>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="close_0()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>