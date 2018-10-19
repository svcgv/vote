<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<style>
#main-nav {
	margin-left: 1px;
}  
 .navbar-static-top {
     margin-bottom: 5px;
 }
 .color-000{ color:#000; background:#666} 
 .navbar-brand {
     background: url('') no-repeat 10px 8px;
     display: inline-block;
     vertical-align: middle;
     padding-left: 30px;
     color: #fff;
 } 
.nav-tabs li a{
    line-height:2
}
.nav-tabs .active a{
    border-top: solid 2px #3498db !important;
}     
</style>
<body class="skin-blue">
	<header id="header" class="navbar navbar-default  navbar-static-top ">
	<div class="container-fluid">
		<div class="navbar-header">
        	<a class="navbar-brand" data-i18n="title" href="${ctx }/login/index" id="logo">物业管理信息系统</a>
            <a class="navbar-brand"  class="btn btn-default" onclick="divhide()">
  				<span class="glyphicon glyphicon-align-justify"></span> 
			</a>
            <span class="navbar-brand" data-i18n="title"></span>
		</div>
        <nav id="menu" class="navbar-collapse collapse" role="navigation">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="glyphicon"><b>退出</b></i>
						<span>
							${usrInfo.usrNo }
							<i class="caret"></i>
						</span>
					</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header bg-light-blue">
							<img src="${ctx }/resources/vol/img/avatar3.png" class="img-circle" alt="User Image" />
							<p>${usrInfo.usrNo } <small>${usrInfo.roleName }</small></p>
						</li>
						<li class="user-footer">
							<div class="pull-left">
								<script language=Javascript>
									var now = new Date()
									document.write(1900 + now.getYear()
											+ "-" + (now.getMonth() + 1)
											+ "-" + now.getDate() + " "
											+ now.getHours() + ":"
											+ now.getMinutes() + ":"
											+ now.getSeconds())
								</script>
							</div>
							<div class="pull-right">
								<a href="${ctx }/login/outLogin" class="btn btn-default btn-flat">退出</a>
							</div>
						</li>
					</ul>
				</li>
            </ul>
        </nav>
	</div>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left" >
		<aside class="left-side sidebar-offcanvas" id="hiddendiv">
			<section class="sidebar">
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${ctx }/resources/vol/img/avatar3.png" class="img-circle" alt="User Image" />
					</div>
					<div class="pull-left info">
						<p>欢迎您&nbsp;${usrInfo.usrName }</p>
						<a href="#"> <!-- <i class="fa fa-circle text-success"></i> -->
							${usrInfo.orgName}
						</a>
					</div>
				</div>
				<ul class="sidebar-menu">
					<c:forEach items="${menuList}" var="menuInfo" varStatus="menuSta">
						<li class="treeview">
							<a href="">
								<i class="fa fa-th"></i>
								<span>${menuInfo.menuName }</span> 
								<c:if test="${fn:length(menuInfo.menuVo)  > 0}">
									<span class="label label-important"><i class="fa fa-angle-left pull-right"></i></span>
								</c:if> 
							</a> 
							<c:if test="${fn:length(menuInfo.menuVo)  > 0}">
								<ul class="treeview-menu">
									<c:forEach items="${menuInfo.menuVo}" var="menuInfo_2" varStatus="menuSta_2">
										<li>
											<a href="${pageContext.request.contextPath }${menuInfo_2.menuUrl}" target="main-content">
											<i class="fa fa-angle-double-right"></i>${menuInfo_2.menuName }</a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</section>
		</aside>
		<div style="margin-left: 220px;">
			<script type="text/javascript">
				function iFrameHeight() {
					var iframe = document.getElementById("main-content");
					try {
						var bHeight = iframe.contentWindow.document.body.scrollHeight;
						var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
						var height = Math.max(bHeight, dHeight);
						//if(height > 800) height=800;
						iframe.height = height;
					} catch (ex) {
					}
				}
			</script>
			<iframe name="main-content" id="main-content" frameBorder="0" scrolling="auto" width="100%" src="${ctx }/jsp/init/homeWorkbench.jsp" onload="iFrameHeight()"></iframe>
		</div>
	</div>
	<script src="${ctx }/resources/vol/js/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
	<script src="${ctx }/resources/vol/js/plugins/jqueryKnob/jquery.knob.js" type="text/javascript"></script>
	<script src="${ctx }/resources/vol/js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
	<script src="${ctx }/resources/vol/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
	<script src="${ctx }/resources/vol/js/AdminLTE/app.js" type="text/javascript"></script>
	<script type="text/javascript">
	function divhide(){
		$("#hiddendiv").fadeToggle(250);
	}
	</script>
</body>
</html>