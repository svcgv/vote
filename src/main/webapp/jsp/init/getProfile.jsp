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

	//关闭当前弹出框
	function close_01() {
		 window.close();
	}
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				用户资料信息 <small><i class="icon-double-angle-right"></i>查看用户资料</small>
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
						<label class=" col-md-4 control-label text-right">用户角色</label>
						<div class="col-md-5">
							<input type="text" class="form-control" placeholder="用户姓名"
								id="roleName" name="roleName" required value="${usrInfo.roleName}" />
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">机构类型</label>
						<div class="col-md-5">
							<input type="text" class="form-control" placeholder="用户姓名"
								id="orgType" name="orgType" required value="${cm:getCodeVal('ORG_TYPE',usrInfo.orgType) }" />
						</div>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">所属机构</label>
						<div class=" col-md-5">
							<input type="text" class="form-control col-md-11 "
								placeholder="所属机构" id="_orgName" name="orgName" value="${usrInfo.orgName}"
								/> 
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
						<label class=" col-md-4 control-label text-right"> About Me</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="原密码" id="About Me" name="About Me" required value="${usrInfo.usrId}"/>
						</div>
					</div>
					<div class=" form-group" align="center">
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