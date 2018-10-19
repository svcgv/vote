<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){ 
	var ra = ${usrInfo.sex };
	if(ra==1){
		$("#sex1").click();
	}else{
		$("#sex2").click();
	}

});
	function save() {
		formValidate();
	}
	
	
	function backindex(){
		//$.indi.submit({url:"${ctx }/login/index"});
	}

	//关闭当前弹出框
	function close_01() {
		 window.close();
	}
	
	// 验证身份证 
	function isCardNo(card) { 
		 var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
		 return pattern.test(card); 
		} 
	// 验证函数
	function formValidate() {
	 	var str = '';
	 	// 验证身份证
	 	if(($.trim($('#certId').val()).length == 0) || ($.trim($('#certId').val()).length>18)||($.trim($('#certId').val()).length<15)) { 
	  		str += '身份证号为15~18位!\n';
	  		$('#certId').focus();
	 	} else {
	  		if(isCardNo($.trim($('#certId').val())) == false) {
	   			str += '身份证号码输入错误!\n';
	  	 		$('#certId').focus();
	  		}
	 	}
		// 如果没有错误则提交
		if(str != '') {
			alert(str);
		  	return false;
		} else {
			var pwd1 = $("input[name='pass_word_new']").val();
			var pwd2 = $("input[name='pass_word_c']").val();
			if(pwd1!=pwd2){
				layer.alert('新密码输入有误，请确认确认新密码输入一致！',{icon: 0});
				return;
			}
		 $.indi.ajaxSubmit({
				url : "${ctx}/usr/setUserInfo",
				success : function(data) {
					layer.alert('设置用户资料成功！',{icon: 1}, function(index){
						layer.close(index);
						backindex();
					});  
				}
			});
	 }
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				设置用户信息 <small><i class="icon-double-angle-right"></i>修改用户资料</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form method="post" id="i-form" class="form-horizontal col-md-10">
					<input type="hidden" name="usrId" value="${usrInfo.usrId}">
					<div class="form-group">
						<label class="col-md-4 control-label text-right">用户姓名</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="用户姓名"
								id="usrName" name="usrName" required value="${usrInfo.usrName }" />
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">机构类型</label>
						<div class="col-md-5">
							<select class="form-control" required id="orgType"
								disabled="disabled"> ${cm:createHtmlByCodem('ORG_TYPE',usrInfo.orgType) }
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">所属机构</label>
						<div class=" col-md-5">
							<input type="text" class="form-control col-md-11 "
								placeholder="所属机构" id="_orgName" name="orgName" value="${usrInfo.orgName}"
								disabled="disabled" /> 
								<input type="hidden" name="orgNo"id="_orgNo" value="${usrInfo.orgNo}"/>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">手机号码</label>
						<div class=" col-md-5">
							<input type="text" class="form-control col-md-11 "
								placeholder="手机号码" id="mblNo" name="mblNo" required value="${usrInfo.mblNo }"/>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">证书编号</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="证书编号" 
							id="licenceCode" name="licenceCode" value="${usrInfo.licenceCode }"/>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">身份证</label>
						<div class=" col-md-5">
							<input type="text" class="form-control col-md-11 " placeholder="省份证" 
								id="certId" name="certId" required value="${usrInfo.certId }"/>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">邮箱 </label>
						<div class=" col-md-5">
							<input type="text" class="form-control col-md-11 "
								placeholder="邮箱 " id="email" required name="email" value="${usrInfo.email }"/>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">原密码</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="原密码" id="passWord" name="passWord" required value=""/>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">新密码</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="新密码" id="pass_word_new" name="pass_word_new" required value=""/>
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">确认新密码</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="确认新密码" id="pass_word_c" name="pass_word_c"  required value=""/>
						</div>
					</div>
					<div class=" form-group" align="center">
						<button type="button" class="btn btn-primary" onclick="save()"
							id="btnSave">
							<i class="icon-save"></i> 保存
						</button>
						<button type="button" class="btn btn-primary" onclick="close_01()">
							<i class="icon-remove"></i> 关闭
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>