<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	function save() {
		$('#i-form').validate({
			rules:{
				ryxm:{
					required: true
				},rylx:{
					required: true
				},ryxb:{
					required: true
				},whcd:{
					required: true
				},zjlx:{
					required: true
				},zjhm:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/cspstaff/addStaffInfo",success:function(data){
			if(data.status == true){
				layer.alert('人员信息新增成功！',{icon: 1}, function(index){
					window.parent.qryList();
					$.indi.closePopup();
				});  
			}else{
				layer.alert('人员信息新增失败！',{icon: 2});
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
					<label class="col-xs-2 control-label text-right">人员姓名</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="人员姓名" id="ryxm" name="ryxm" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">人员类型</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="rylx" name="rylx" >
							${USER_TYPE}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">从事物管年限</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="从事物管年限" id="wgnx" name="wgnx" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">人员性别</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="ryxb" name="ryxb" >
							${SEXTYPE}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">文化程度</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="whcd" name="whcd" >
							${EDUCATION}
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">职务</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="职务" id="drzw" name="drzw" />
					</div> 
					<label class="col-xs-2 control-label text-right">手机</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="手机" id="lxsj" name="lxsj" maxlength="11" minlength="11" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">邮箱</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮箱" id="lxyx" name="lxyx" />
					</div> 
					<label class="col-xs-2 control-label text-right">电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="电话" id="lxdh" name="lxdh" maxlength="18" minlength="6"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">出生日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="出生日期" id="csrq" name="csrq" onClick="WdatePicker()"  minlength="10" maxlength="10" />
					</div> 
					<label class="col-xs-2 control-label text-right">邮政编码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮政编码" id="yzbm" name="yzbm" maxlength="6" minlength="6" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="zjlx" name="zjlx" >
							${CERTTYPE}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="zjhm" name="zjhm" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">劳务合同开始日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="劳务合同开始日期" id="htksrq" name="htksrq" onClick="WdatePicker()"  minlength="10" maxlength="10" />
					</div> 
					<label class="col-xs-2 control-label text-right">劳务合同截止日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="劳务合同截止日期" id="htjzrq" name="htjzrq" onClick="WdatePicker()"  minlength="10" maxlength="10" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">联系地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系地址" id="lxdz" name="lxdz" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="备注" id="bz" name="bz" maxlength="200" />
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