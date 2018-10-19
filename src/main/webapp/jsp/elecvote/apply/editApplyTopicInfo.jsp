<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$("checkbox").change(function(){
		if ($(this).attr("checked", true)){
			alert(true);
		}else{
			alert(false);
		}
	});
	
	$(document).ready(function(){
		
		var voteStartDate = "${topic.voteStartDate}";
		var voteEndDate = "${topic.voteEndDate}";
		var startdate = voteStartDate.substring(0,4)+"-"+voteStartDate.substring(4,6)+"-"+voteStartDate.substring(6,8);
		var endDate = voteEndDate.substring(0,4)+"-"+voteEndDate.substring(4,6)+"-"+voteEndDate.substring(6,8);
		$("#voteStartDate").val(startdate);
		$("#voteEndDate").val(endDate);
	});

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
		$.indi.ajaxSubmit({url: "${ctx}/apply/editApplyTopicInfo",success:function(data){
			if(data.status == true){
				layer.alert('修改议题成功！',{icon: 1}, function(index){
					window.parent.qryList();//调用父页面查询方法
					$.indi.closePopup();//关闭页面
				});
			}
			else{
				layer.alert('修改议题失败！',{icon: 2});
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
				<input type="hidden" id="topicId" name="topicId" value="${topic.topicId}" >
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">投票主题</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11" 
							placeholder="投票主题" id="topicName" name="topicName" value="${topic.topicName}"/>
							
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
				<!-- 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">楼层号</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="floorCode" name="floorCode"
							placeholder="楼层号" value=""/>
					</div>
					<label class="col-xs-2 control-label text-right">房屋号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="房屋号" id="roomCode" name="roomCode"  value="" />
					</div> 
				</div> -->
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">议题内容</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-11" id="topicContent" name="topicContent" 
							 placeholder="议题内容"  value="${topic.topicContent}"/>
					</div>
				</div>
				<!--  
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">注销议题</label>
					<div class=" col-xs-1">
						<input type="checkbox" class="form-control col-xs-11" id="voteStatus" name="voteStatus" 
							 value="05"/>
					</div>
				</div> -->
				<!--  
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">是否关联维修工程</label>
					<div class=" col-xs-1">
						<input type="checkbox" class="form-control col-xs-11" id="linkFlag" name="linkFlag" 
							 value="01"/>
					</div>
				</div> -->
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