<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	
	function save(){
		$('#i-form').validate({
			code:{
				fieldName:{
					required: true
				},
		           codeNo:{
					required: true
				},		
		          codeName:{
					required: true
				},
		          codeKey:{
					required: true
				},
		         codeVal:{
				  required: true
			    }
			}
		});
		$.indi.ajaxSubmit({url:"${ctx}/code/addSave",closeMode:true,success:function(data){
			if(data.status == true){
				layer.alert('数据字典信息新增成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					window.parent.qryList();
				});  
			}else{
				layer.alert('数据字典信息新增失败！',{icon: 2});
			}
		}});
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">字段名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="字段名称" id="fieldName" name="fieldName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">字典类型编码</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="字典类型编码" id="codeNo" name="codeNo" />
					</div>
				</div>
				
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">字典类型说明</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="字典类型说明" id="codeName" name="codeName" />
					</div>
				</div>
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">字典代码</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="字典代码" id="codeKey" name="codeKey" maxlength="20"/>
					</div>
				</div>
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">字典名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="字典名称" id="codeVal" name="codeVal" />
					</div>
				</div>
				
				
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="javascript:$.indi.closePopup();">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>