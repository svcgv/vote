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
			没有权限访问！
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
</body>
</html>