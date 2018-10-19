<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function save() {
		$('#i-form').validate({
			rules:{
				sqbm:{
					required: true
				},sqmc:{
					required: true
				},jdid:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/cmt/addCommitteeInfo",success:function(data){
			if(data.status == true){
				layer.alert('社区新增成功！',{icon: 1}, function(index){
					window.parent.qryList();
					$.indi.closePopup();
				});  
			}else{
				layer.alert('社区新增失败！',{icon: 2});
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
					<label class="col-xs-4 control-label text-right">社区编号</label>
					<div class=" col-xs-6">
						<input type="number" class="form-control col-xs-11"
							placeholder="社区编号" id="sqbm" name="sqbm" minlength="4" maxlength="4"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">社区名称</label>
					<div class=" col-xs-6">
							<input type="text" class="form-control col-xs-11"
							placeholder="社区名称" id="sqmc" name="sqmc" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">选择街道</label>
					<div class=" col-xs-6">
						<select class="col-md-8 form-control" name="jdid" required >
							<option>--请选择--</option>
							<c:forEach items="${list}" var="list" varStatus="listSta">
							<option name="jdid" value="${list.jdid }">${list.jdmc}</option>
							</c:forEach>
						</select>
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
			</form>
		</div>
	</div>
</body>
</html>