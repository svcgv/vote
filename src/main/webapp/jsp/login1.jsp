<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<html>
<head>
<title>物业监管信息系统</title>
<link href="${ctx }/resources/css/style_log.css" rel="stylesheet" />
<!-- basic styles -->
<link href="${ctx }/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="${ctx }/resources/assets/css/font-awesome.min.css" rel="stylesheet" />

<link rel="stylesheet" href="${ctx }/resources/assets/css/jquery-ui-1.10.3.full.min.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${ctx }/resources/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctx }/resources/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx }/resources/assets/css/ace-skins.min.css" />

<script type="text/javascript">var _ctx = '${ctx}';</script>
<script src="${ctx }/resources/js/jquery.min.js"></script>
<script src="${ctx }/resources/js/jquery.json-2.4.js"></script>
<script src="${ctx }/resources/js/jquery.validate-1.13.1.js"></script>
<script src="${ctx }/resources/js/jquery.validate.extend.js"></script>
<script src="${ctx }/resources/js/form-json.js"></script>
<script src="${ctx }/resources/js/jsencrypt.min.js"></script>
<script src="${ctx }/resources/layer/layui.js"></script>
<script src="${ctx }/resources/js/web-ui-01.js"></script>
<script src="${ctx }/resources/js/matrix.login.js"></script>
</head>
<style type="text/css">
	.bg-image {
		background:url(/hmfmstest/ztbplat/images/bg.jpg) no-repeat center;
		background-size:100% 100%;
	}
	.title_class{font-family:"Microsoft YaHei",微软雅黑,"Microsoft JhengHei",华文细黑,STHeiti,MingLiu }
</style>
<script type="text/javascript">
//一般直接写在一个js文件中
layui.use(['layer'], function(){
  var layer = layui.layer
});
</script>
<body  class="bg-image login-layout">
	<div class="login_m">
		<div class="login_logo">
			<h1>
				<span style="color: #3c8dbc; font-family:">物业监管信息系统</span>
			</h1>
		</div>
		<div class="login_boder">
			<form id="i-form" method="post" >
			<div class="login_padding" id="login_model">
				<h2>账&nbsp;号</h2>
				<div> 
					<span class="block input-icon input-icon-right">
						<input type="text" id="i_userid" class="txt_input txt_input2 " placeholder="用户名" required>
						<i class="icon-user"></i>
					</span>
					<input type="hidden" name="usrId" id="_usrId" value="2323">
				</div>
				<h2>密&nbsp;码</h2>
				<div> 
					<input type="password" id="userpwd" class="txt_input required" placeholder="密码" required >
					<input type="hidden" name="passWord" id="_passWord">
				</div>
				<p class="forgot">
					<!-- <a id="iforget" href="javascript:void(0);">忘记密码?</a> -->
				</p>
				<div class="rem_sub">
					<div class="rem_sub_l">
						<label class="inline">
							<input type="checkbox" class="ace" />
							<span class="lbl"> Remember Me</span>
						</label>
					</div>
					<label> 
						<input type="button"  class="sub_button" id="to-login" url="${ctx }/login/loginInit" i_href="${ctx }/login/index" name="button"  value="登录" style="opacity: 0.7;">
					</label>
				</div>
			</div>
			</form>
		</div>
	</div>
	<br>
	<br>
</body>
</html>
