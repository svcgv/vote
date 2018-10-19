<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function save() {
		$('#i-form').validate({
			rules:{
				paramsNo:{
					required: true
				},paramsName:{
					required: true
				},paramsType:{
					required: true
				},paramsVal:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/para/addParaInfo",success:function(data){
			if(data.status == true){
				layer.alert('参数新增成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					 window.parent.qryList_01();
				});  
			}else{
				layer.alert('参数新增失败！',{icon: 2});
			}
		
			}});
	}
	function check(){
		 var paramsNo=$("#paramsNo")
		$.indi.ajaxSubmit({url: "${ctx}/para/checkParaInfo?paramsNo"+paramsNo,success:setVal});
	}
	function setVal(obj){
		$("#check").hide();
		var paramsno=obj.paraInfo.paramsNo;
		if(!(paramsno=="") ||  !(paramsno==null)){
			$("#check").show();
		}
	}
	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">参数代码</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="参数代码" id="paramsNo" name="paramsNo"  onblur="check();"/>
					</div>
					<span id="check" style="display: none;"><font color='red'>已重复</font></span>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">参数名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="参数名称" id="paramsName" name="paramsName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">参数类型</label>
					<div class=" col-xs-6">
						<select class="col-md-8 form-control" name="paramsType" id="paramsType">
							${codeData.ewTypeHtml }
							</select>
					</div>
				</div>
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">参数值</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="参数值" id="paramsVal" name="paramsVal" />
					</div>
				</div>
				<div class=" form-group" align="center">
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