<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		qrySectList();
	});

	function toggle(){
		var val = $("input[type='checkbox']").attr("checked"); 
		//alert(val);
		if (val == "checked"){
			$("#hidden").removeClass("hidden");
		}else{
			$("#hidden").addClass("hidden");
		}
	}
	
	function qrySectList(){
    	console.log(":"+ $("#sectId option:selected").val()+":");
    	$.indi.ajaxSubmit({url:'${ctx }/sect/getallsectinfo',success:function(data){
			if(data.status == true){
				var list = data.listInfo;
				
				for(var i=0; i<list.length; i++){
					$("#sectId").append('<option value="' + list[i].sectId +'">'+ list[i].sectName +'</option>');
				}
			}else{
				layer.alert('小区信息异常，请重试！',{icon: 2});
			}
		}});
	}

	function save() {
		$('#i-form').validate({
			rules:{
				topicName:{
					required: true
				},voteStartDate:{
					required: true
				},voteEndDate:{
					required: true
				},topicContent:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/apply/addApplyTopicInfo",success:function(data){
			if(data.status == true){
				layer.alert('发布议题成功！',{icon: 1}, function(index){
					window.parent.qryList();//调用父页面查询方法
					$.indi.closePopup();//关闭页面
				});
			}
			else{
				layer.alert('发布议题失败！',{icon: 2});
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
				<input type="hidden" id="topicId" name="topicId" value="" >
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">投票主题</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11" 
							placeholder="投票主题" id="topicName" name="topicName" value=""/>
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">投票开始时间</label>
					<div class="col-xs-3">
						<input class="form-control col-xs-11" type="text" id="voteStartDate" 
							name="voteStartDate" placeholder="<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %>" value="" onClick="WdatePicker()">
					</div>
					<label class="col-xs-2 control-label text-right">投票结束时间</label>
					<div class="col-xs-3 ">
							<input class="form-control col-xs-11" type="text" id="voteEndDate" 
							name="voteEndDate" placeholder="<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %>" value="" onClick="WdatePicker()">
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">议题内容</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-11" id="topicContent" name="topicContent" 
							 placeholder="议题内容"  value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">是否关联维修工程</label>
					<div class=" col-xs-1">
						<input type="checkbox" class="form-control col-xs-11" id="linkFlag" name="linkFlag" 
							 value="01" checked="checked" />
					</div>
				</div>
				<!--默认关联维修工程  <div id="hidden" class="hidden">-->
				<div id="hidden">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区名称</label>
					<div class=" col-xs-3">
						  <!--<input type="text" class="form-control col-xs-11" id="sectName" name="sectName" 
							placeholder="小区名称" value=""/>-->
						<select class="form-control col-xs-3 select" id="sectId" name="sectId">
							<option value="" selected="selected" >请选择</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">工程名称</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" 
							placeholder="工程名称" id="wsName" name="wsName" value=""/>
					</div>
					<label class="col-xs-2 control-label text-right">工程总价</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="totalAmt" name="totalAmt" 
							placeholder="工程总价" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">维修内容</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							id="repairContent" name="repairContent" placeholder="维修内容" value=""/>
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">维修原因</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="repairReason" name="repairReason"
							placeholder="维修原因" value=""/>
					</div>
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