<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//初始化请求后台加载数据
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
		$.indi.ajaxSubmit({url: "${ctx }/para/editSave",closeMode:true,success:function(data){
			if(data.status == true){
				layer.alert('参数修改成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					 window.parent.qryList_01();
				});  
			}else{
				layer.alert('参数修改失败！',{icon: 2});
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
	<div  class="content" >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
			<input type="hidden" id="paramsNo" name="paramsNo" value="${paramsinfo.paramsNo } ">
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">参数名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="手参数名称" id="paramsName" name="paramsName" value="${paramsinfo.paramsName } "/>
					</div>
				</div>
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">参数类型</label>
					<div class=" col-xs-6">
						<select  class="form-control col-xs-11" name="paramsType">
							${paramsinfo.ewTypeHtml }
						</select>
					</div>
				 </div>
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">参数值</label>
		              	<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="参数值" id="paramsVal" name="paramsVal" value="${paramsinfo.paramsVal } "/>
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