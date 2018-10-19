<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh">
<head>
<title>物业管理信息系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script type="text/javascript">
	var _ctx = '${ctx}';
</script>
<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/resources/css/bootstrap-treeview.min.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/resources/vol/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/resources/vol/css/ionicons.min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/resources/vol/css/morris/morris.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx }/resources/vol/css/jvectormap/jquery-jvectormap-1.2.2.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/resources/vol/css/fullcalendar/fullcalendar.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx }/resources/vol/css/daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx }/resources/vol/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/resources/vol/css/AdminLTE.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/resources/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link href="${ctx }/resources/css/validformstyle.css" rel="stylesheet" />
<script src="${ctx }/resources/js/jquery.min.js"></script>
<script src="${ctx }/resources/js/jquery.json-2.4.js"></script>
<script src="${ctx }/resources/js/form-json.js"></script>
<script src="${ctx }/resources/js/talbe-pages.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>
<script src="${ctx }/resources/date/WdatePicker.js"></script>
<script src="${ctx }/resources/js/jquery.validate-1.13.1.js"></script>
<script src="${ctx }/resources/js/jquery.validate.extend.js"></script>
<script src="${ctx }/resources/layer/lay/dest/layui.all.js"></script>
<script src="${ctx }/resources/js/init.js"></script>
<script src="${ctx }/resources/js/bootstrap-treeview.js"></script>
<script src="${ctx }/resources/js/treeview-indi.js"></script>
<script src="${ctx }/resources/js/web-ui-01.js"></script>
<script src="${ctx }/resources/js/btn-power.js"></script>
<script type="text/javascript">
	//给子页面调用，设置弹出框的标题和页面大小
	function setTitleWh(widht, height, title) {
		$('.layui-layer-title').html(title);
		$('.layui-layer-iframe').css('height', height);
		$('.layui-layer-iframe').css('width', widht);
		$('iframe').css('height', height);
	}
</script>
</head>