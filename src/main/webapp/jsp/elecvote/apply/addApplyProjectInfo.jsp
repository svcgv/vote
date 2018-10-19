<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		//var sectInfo = '${map}';
		var listString = '${list}';
		//var sectInfos = listString.substring(1, listString.length-1);
		var sectInfos = '{"list":'+listString+'}';
		var jsonObj = JSON.parse(sectInfos);
		var data = jsonObj.list; 
		for (var i=0; i < data.length; i++)
		{
			var sectInfo = data[i];
			$(".select").append('<option value="' + data[i].sectName +'">'+ data[i].sectName +'</option>');
		}
	});
	
	function save() {
		$('#i-form').validate({
			rules:{
				wsName:{
					required: true
				},totalAmt:{
					required: true
				},repairContent:{
					required: true
				},repairReason:{
					required: true
				}
			}
		});
		$.indi.openPopup({title: '工程信息关联',area : ['850px' , '650px'],url: '${ctx }/apply/linkProject'});
		/*
		$.indi.ajaxSubmit({url: "${ctx}/apply/addapplyTopicInfo",success:function(data){
			if(data.status == true){
				layer.alert('维修工程发布成功！',{icon: 1}, function(index){
					window.parent.qryList();//调用父页面查询方法
					$.indi.closePopup();//关闭页面
				});
			}
			else{
				layer.alert('维修工程发布失败！',{icon: 2});
			}
			}});*/
		
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
				<input type="hidden" id="wsId" name="wsId" value="" >
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">工程名称</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" 
							placeholder="工程名称" id="wsName" name="wsName" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区名称</label>
					<div class=" col-xs-3">
						<!--  <input type="text" class="form-control col-xs-11" id="sectName" name="sectName" 
							placeholder="小区名称" value=""/>-->
						<select class="form-control col-xs-3 select" id="sectName" name="sectName">
							<option value="" selected="selected">请选择</option>
						</select>
					</div>
					<label class="col-xs-2 control-label text-right">工程总价</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="totalAmt" name="totalAmt" 
							placeholder="工程总价" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">维修内容</label>
					<div class=" col-xs-10">
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
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 下一步
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