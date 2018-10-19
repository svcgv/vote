<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cm" uri="http://www.custom.com/01" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/errorpage.css">
</head>
<body>
	<div class="main">
		<div class="mod-page-main">
			<div class="mod-notfound">
				<img class=img-notfound height=320 src="${ctx }/resources/images/500.gif" width=520>
			</div>
			<P style="FONT-SIZE: 35px; LINE-HEIGHT: 70px;color: red;">啊~哦~ 您的用户登陆已失效！</P>
			<P style="MARGIN-BOTTOM: 30px">
				请检查您输入的网址是否正确，或者点击链接 <A href="#" onclick="reLogin();">重新登录</A>
			</P>
			<P style="FONT-SIZE: 14px; LINE-HEIGHT: 20px">
				如若不能解决您的问题，请 <A href="#">联系我们</A> 提出建议^_^
			</P>
		</div>
	</div>
	<div class="footer">
		<div class="mod-footer">
			<div class="inner-box">
				<A href="#">网站首页</A><SPAN>&nbsp;|&nbsp;</SPAN> <A href="#">联系我们</A>
			</div>
			<div class="copy-box">Copyright @2018 物业监管信息系统</div>

		</div>
	</div>
	<script type="text/javascript">
	function reLogin(){
		if(top.location!=self.location){  
		    top.location = "${ctx }/login/outLogin";  
		}else{  
		    window.location.href = "${ctx }/login/outLogin";  
		}
	}
	</script>
</body>
</html>