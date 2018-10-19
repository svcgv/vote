<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.indihx.comm.InitSysConstants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<%
session = request.getSession(false);
if(session!=null && session.getAttribute(InitSysConstants.USER_SESSION)!=null){
	String sUrl = request.getContextPath()+"/login/index.do";
	response.sendRedirect(sUrl);
}
%>
<html>
<head>
<title>电子投票表决系统2</title>
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
		background:url(${ctx }/resources/images/backgrounds/loginbg.jpg) no-repeat center;
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
<div class="main-container" style="margin-top:160px">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="login-container">
					<div class="center">
						<h1>
							<span class="red"></span>
							<span class="blue">电子投票表决系统22222</span>
						</h1>
					</div>
					<div class="space-6"></div>
					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border" style="background-color: transparent">
							<div>
								<div class="widget-main" style="border-radius: 5px; border: 2px solid #3AA6E4">
									<h4 class="header blue" style="text-align:center">
										<span style="font-family:Microsoft YaHei">用户登录</span><br/>
										<font style="color:red;font-size:14px;font-family:Microsoft YaHei"></font>
									</h4>
									<div class="space-6"></div>
									<form name="form1" id="form1" name="form" method="post">
										<input type="hidden" name="token" value="496e97bf-c39d-4d78-9bdc-9e9d236bec4e">
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control" v_max="32" maxlength="32" v_empty="0"
														name="i_userid" id="i_userid" placeholder="用户名">
													<i class="icon-user"></i>
													<input type="hidden" name="usrId" id="_usrId" value="2323" />
												</span>
											</label>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="password" class="form-control" v_max="20" maxlength="32" v_empty="0"
															name="userpwd" id="userpwd" placeholder="密码"/>
													<i class="icon-lock"></i>
													<input type="hidden" name="passWord" id="_passWord">
												</span>
											</label>
											<!-- 
											<p class="forgot">
												 <a id="iforget" href="javascript:void(0);">忘记密码?</a>
											</p>
											 -->
											<div class="space-4">
											</div>
											<div class="clearfix center" >
												<label class="inline pull-left">
													<input type="checkbox" class="ace" id="remember"/>
													<span class="lbl"> 记住密码</span>
												</label>
												<button type="button" class="width-35 pull-right btn btn-sm btn-primary" id="to-login" url="${ctx }/login/loginInit" i_href="${ctx }/login/index">
												<i class="icon-key"></i>
													登录
												</button>
											</div>
											<div class="space-4"></div>
											<!-- 
											<div class="toolbar clearfix">
												<div>
													<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
														<i class="icon-arrow-left"></i>
														开发商注册
													</a>
												</div>
	
												<div>
													<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
														物业公司注册
														<i class="icon-arrow-right"></i>
													</a>
												</div>
											</div>
											 -->
											<div class="clearfix center" >
												<!-- 
												<button type="button" class=" btn btn-sm btn-primary" onclick="register()">
													&nbsp;&nbsp;开发商注册&nbsp;
												</button>
												&nbsp;
												<button type="button" class=" btn btn-sm btn-primary" onclick="addHoc()">
													业主大会注册
												</button>
												 -->
												<div></div>
											</div>
											<div class="space-4"></div>
										</fieldset>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</div>
	<div class="tooltip top in" style="display: none;">
		<div class="tooltip-inner"></div>
	</div>
	<!--  
	<footer class="footer navbar-fixed-bottom ">
		<div class="container clearfix center text-info">
			<small>本系统支持Google Chrome、IE9及以上版本浏览器，建议使用最新版本的浏览器</small>
		</div>
	</footer>-->
</div>
</body>
</html>
