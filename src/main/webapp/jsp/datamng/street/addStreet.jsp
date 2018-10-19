<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
function openAdd() {
	var menulevel=$("#hpblx").val();
	$.indi.openPopup({title:'选择区县',area:['500px','400px'],isDate:false,url:'${ctx}/street/queryAddPara?hpblx='+menulevel});
}
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
				jdmc:{
					required: true
				},jdbm:{
					required: true
				},hpbmc:{
					required: true
				},hpblx:{
					required: true
				}
				
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/street/addStreetInfo",success:function(data){
			if(data.status == true){
				layer.alert('街道新增成功！',{icon: 1}, function(index){
					window.parent.qryList();
					$.indi.closePopup();
				});  
			}else{
				layer.alert('街道新增失败！',{icon: 2});
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
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">街道名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="街道名称" id="jdmc" name="jdmc" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">街道编号</label>
					<div class=" col-xs-6">
							<input type="number" class="form-control col-xs-11"
							placeholder="街道编号" id="jdbm" name="jdbm"  minlength="4" maxlength="4" />
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
				 <div class="form-group" id="hidden1">
					<label class="col-xs-4 control-label text-right">选择县区</label>
					<div class=" col-xs-6">
							<input type="text" class="form-control col-xs-11"
							placeholder="选择县区" id="sjid" required="required" name="sjid" disabled="disabled"/>
					</div>
						<button type="button" class="btn btn-primary" onclick="openAdd()">
						<i class="icon-plus-sign"></i><span>选择</span></button>
						<input type="hidden" value="" name="hpbid" id="hpbid"/>
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
			</form>
		</div>
	</div>
</body>
</html>