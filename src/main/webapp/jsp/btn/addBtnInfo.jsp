<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	
	function save(){
		/* if(checkPrimary()){ */
			$.indi.ajaxSubmit({url:"${ctx}/btn/addSave.do",closeMode:true,success:function(data){
				if(data.status == true){
					layer.alert('按钮信息新增成功！',{icon: 1}, function(index){
						$.indi.closePopup();
						window.parent.qryList();
					});  
				}else{
					layer.alert('按钮信息新增失败！',{icon: 2});
				}
			}});
		/* } */
	}
	
	
	
	 function checkPrimary(){
		var flag=true;
		$.indi.ajaxSubmit({url: "${ctx }/btn/qryPrimary.do",isCheck:false,success:function(data){
			if(data.primary=="1"){
				 $("#myerror").show();
				 flag= false;
			}else{
				 $("#myerror").hide();
				 flag= true;
			}
		}});
		return flag;
	} 
	
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">按钮主键</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="按钮主键" id="btnId" name="btnId" required  maxlength="16"  onblur="checkPrimary();"/>
							<label style="display: none;color: red;" id="myerror" >主键重复，请重新录入！</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">按钮名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="按钮名称" id="btnName" name="btnName" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">按钮说明</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="按钮说明" id="btnRmk" name="btnRmk" />
					</div>
				</div>
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">按钮所属页面</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="按钮所属页面" id="btnPage" name="btnPage" required/>
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