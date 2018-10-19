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
				<img class=img-notfound height=320 src="${ctx }/resources/images/404.gif" width=520>
			</div>

			<P style="FONT-SIZE: 24px; LINE-HEIGHT: 70px">啊~哦~
				您要查看的页面不存在或已删除！</P>
			<P style="MARGIN-BOTTOM: 30px">请检查您输入的网址是否正确，或者点击链接继续浏览空间</P>
			<P style="FONT-SIZE: 14px; LINE-HEIGHT: 20px">
				您可以回到 <A href="#">网站首页</A> 或到 <A href="history.back();">返回上一页</A><BR>如若不能解决您的问题，请
				<A href="#">联系我们</A> 提出建议^_^
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
</body>
</html>