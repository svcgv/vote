<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="zh">
	<head>
		<meta charset="GBK" />
		<title>项目实施管理系统</title>
		<meta http-equiv="Content-Type" content="text/html;charset=GBK">
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
		<meta name="keywords" content="项目实施管理系统" />
		<meta name="description" content="项目实施管理系统-Main页面-Author:zhengwei" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="${ctx }/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${ctx }/resources/assets/css/font-awesome.min.css" rel="stylesheet" />
		
		<link rel="stylesheet" href="${ctx }/resources/assets/css/jquery-ui-1.10.3.full.min.css" />
		
		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx }/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx }/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx }/resources/assets/css/ace-skins.min.css" />
		<!-- ace script -->
		<script src="${ctx }/resources/assets/js/ace-extra.min.js"></script>
		<style type="text/css">
			body {
				overflow: hidden;
			}
			.tab-content {
				border: none;
				padding: 10px 0px;
			}
			#menu_li_0 {
				display: none;
			}
		</style>
	</head>
	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
			<!-- header -->
			<div class="navbar-container" id="navbar-container">
				<!-- header左边 -->
				<div class="navbar-header pull-left">
					<a href="${ctx }/login/index" class="navbar-brand">
						<small>
							<i class="icon-cogs"></i>
							项目实施管理系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->
				<!-- header右边 -->
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- 
						<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-tasks"></i>
								<span class="badge badge-grey">4</span>
							</a>
							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-ok"></i>
									还有4个任务完成
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">软件更新</span>
											<span class="pull-right">65%</span>
										</div>
										<div class="progress progress-mini ">
											<div style="width:65%" class="progress-bar "></div>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">硬件更新</span>
											<span class="pull-right">35%</span>
										</div>
										<div class="progress progress-mini ">
											<div style="width:35%" class="progress-bar progress-bar-danger"></div>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">单元测试</span>
											<span class="pull-right">15%</span>
										</div>
										<div class="progress progress-mini ">
											<div style="width:15%" class="progress-bar progress-bar-warning"></div>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">错误修复</span>
											<span class="pull-right">90%</span>
										</div>
										<div class="progress progress-mini progress-striped active">
											<div style="width:90%" class="progress-bar progress-bar-success"></div>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										查看任务详情
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a>
							<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-warning-sign"></i>
									8条通知
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												招标备案受理
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs btn-primary icon-warning-sign"></i>
												不良信用建档
											</span>
											<span class="pull-right badge badge-error">+5</span>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success icon-flag"></i>
												物业企业注册
											</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
												项目信息建档
											</span>
											<span class="pull-right badge badge-info">+11</span>
										</div>
									</a>
								</li>
								<li>
									<a href="#">
										查看所有通知
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">5</span>
							</a>
							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-envelope-alt"></i>
									5条消息
								</li>
								<li>
									<a href="#">
										<img src="${ctx }/resources/assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Alex:</span>
												不知道写啥 ...
											</span>
											<span class="msg-time">
												<i class="icon-time"></i>
												<span>1分钟以前</span>
											</span>
										</span>
									</a>
								</li>
								<li>
									<a href="#">
										<img src="${ctx }/resources/assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Susan:</span>
												不知道翻译...
											</span>
											<span class="msg-time">
												<i class="icon-time"></i>
												<span>20分钟以前</span>
											</span>
										</span>
									</a>
								</li>
								<li>
									<a href="#">
										<img src="${ctx }/resources/assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Bob:</span>
												到底是不是英文 ...
											</span>
											<span class="msg-time">
												<i class="icon-time"></i>
												<span>下午3:15</span>
											</span>
										</span>
									</a>
								</li>
								<li>
									<a href="inbox.html">
										查看所有消息
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						 -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctx }/resources/vol/img/avatar3.png" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临</small>
									${usrInfo.usrName }
								</span>
								<i class="icon-caret-down"></i>
							</a>
							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#" id="setUser">
										<i class="icon-cog"></i>
										设置
									</a>
								</li>
								<li>
									<a href="#"  id="profile">
										<i class="icon-user" ></i>
										个人资料
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="${ctx }/login/outLogin" >
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text" ></span>
				</a>

				<div class="sidebar" id="sidebar" >
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>
					
					<!-- 功能菜单加载 -->
					<ul class="nav nav-list" id="menu" style="font-family: Microsoft YaHei;">
					
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<ul class="nav nav-tabs" role="tablist" style="font-family: Microsoft YaHei;font-size: 15px;">
			       					<li class="active"><a href="#Index" role="tab" data-toggle="tab" id="firstpage"><i class="green icon-home bigger-110"></i>首页</a></li>
			      				</ul>
			      				<div class="tab-content" >
			       					<div role="tabpanel" class="tab-pane active" id="Index">
			       						<iframe src="${ctx }/jsp/init/homePage.jsp" width="100%" height="600" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
			       					</div>
			      				</div>
							</div>
						</div>
					</div>
				</div>

				<!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		<!-- 
		<script src='${ctx }/resources/assets/js/jquery-2.0.3.min.js'></script>-->
		<script src="${ctx }/resources/js/jquery.min.js"></script>
		<!-- 自定义js -->
		<script src="${ctx }/resources/assets/js/sidebar-menu.js"></script>
		<script src="${ctx }/resources/assets/js/bootstrap-tab.js"></script>

		<script src="${ctx }/resources/assets/js/bootstrap.min.js"></script>
		<script src="${ctx }/resources/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->


		<script src="${ctx }/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${ctx }/resources/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${ctx }/resources/assets/js/jquery.slimscroll.min.js"></script>
		<script src="${ctx }/resources/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${ctx }/resources/assets/js/jquery.sparkline.min.js"></script>
		<script src="${ctx }/resources/assets/js/flot/jquery.flot.min.js"></script>
		<script src="${ctx }/resources/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="${ctx }/resources/assets/js/flot/jquery.flot.resize.min.js"></script>

		<!-- ace scripts -->
		<script src="${ctx }/resources/assets/js/ace-elements.min.js"></script>
		<script src="${ctx }/resources/assets/js/ace.min.js"></script>
		
		<script src="${ctx }/resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			//根据窗口大小自适应
			$(window).resize(function() {
			  	$("#sidebar").height($(window).height()-$("#navbar").height());
			  	$("div .tab-pane iframe").height($("#sidebar").height()-60);
			});

			$(function () {
				  //控制打开的菜单tab高度	
				  $("#sidebar").height($(window).height()-$("#navbar").height()-40);	
				  
				  //菜单延迟加载
				  $('#menu').sidebarMenu({
					  url:"${ctx }/login/getMenuByUser.do",
					  param:{usrId:"${usrInfo.usrId}"}
				   });
				   
				  //控制首页直接显示
				  //debugger;
				  //eval($("#menu_li_00 a").attr("href"));
			  });
		</script>
		<script type="text/javascript">
			//个人资料
			jQuery(function($) {
				$( "#profile" ).on('click', function(e) {
					e.preventDefault();
					window.open("${ctx }/login/getProfile","查看用户资料",'height=600, width=600, top=150, left=450, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=yes');
				});
				
				$( "#setUser" ).on('click', function(e) {
					e.preventDefault();
					window.open("${ctx }/login/setUserPassword","设置用户资料",'height=600, width=600, top=150, left=450, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=yes');
				});
			});
		</script>
</body>
</html>