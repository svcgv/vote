<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#hidden1").hide();
	}); 
	function show(){
		var menulevel=$("#hpblx").val();
		$("#hidden1").show();
		
	}
	function save() {
		$('#i-form').validate({
			rules:{
				hpbmc:{
					required: true
				},
				hpbbm:{
					required: true
				},
				hpblx:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/hpb/saveHpbInfo",success:function(data){
			if(data.status == true){
				layer.alert('区县新增成功！',{icon: 1}, function(index){
					window.parent.qryList();
					$.indi.closePopup();
				});  
			}else{
				layer.alert(data.responseText,{icon: 2});
			}
			}});
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
			<form method="post" id="i-form" class="form-horizontal" role="form">
				<fieldset class="content">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">区县编码</label>
					<div class=" col-xs-6">
							<input type="number" class="form-control col-xs-11"
							placeholder="区县编码" id="hpbbm" name="hpbbm"  minlength="4" maxlength="4" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">县区级别</label>
					<div class=" col-xs-6">
						<select  class="form-control col-xs-11 " id="hpblx" name="hpblx" onchange="show();">
							${regionType }
						</select>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">区县名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="区县名称" id="hpbmc" name="hpbmc" maxlength="80"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">备注</label>
					<div class=" col-xs-6">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="备注" id="bz" name="bz" rows="5" maxlength="512"></textarea>
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
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>