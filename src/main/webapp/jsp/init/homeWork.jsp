<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="GBK"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/assets/js/bootstrap-tab.js"></script>
<script src="${ctx }/resources/assets/js/bootstrap.min.js"></script>


<html>
<head>
<%--<script src="${ctx }/resources/map/echarts.min.js"></script>
 <script src="${ctx }/resources/map/bmap.js"></script> --%>
<script
	src="http://api.map.baidu.com/api?v=2.0&ak=ZpeI3M1Em9GDKoYW17YoswATr98VSIdR"></script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				<i class="icon-home"></i> 首页
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<form action="psot">
			<div class="row">
				<!--  
				<div class="form-group" style="margin-left:0px;margin-right:0px">
					<label class="col-sm-2 blue text-center">投票系统</label>
					<div class="col-sm-8">
						<img src="${ctx}/resources/images/fgx.jpg">
						</div>
				</div>	-->
				<div class="form-group" style="margin-left:0px;margin-right:0px">
					<div class="col-sm-2"></div>
					<div class="col-sm-2">
						<ul class="nav align-center">
							<li class="li-a"><a href="${ctx }/house/index" style="text-align: center;"><img src="/vote/resources/images/wyyfpz.png"></a></li>
							<li style="text-align: center;">1</li>
						</ul>
					</div>
					<div class="col-sm-2">
						<ul class="nav align-center">
							<li class="li-a"><a href="${ctx }/sect/index" style="text-align: center;"><img src="/vote/resources/images/sect.png"></a></li>
							<li style="text-align: center;">3</li>
						</ul>
					</div>
					<div class="col-sm-2">
						<ul class="nav align-center">
							<li class="li-a"><a href="${ctx }/apply/index" style="text-align: center;"><img src="/vote/resources/images/pbzxm.png"></a></li>
							<li style="text-align: center;">5</li>
						</ul>
					</div>
					<div class="col-sm-2">
						<ul class="nav align-center">
							<li class="li-a"><a href="${ctx }/result/index" style="text-align: center;"><img src="/vote/resources/images/qrzbhxr.png"></a></li>
							<li style="text-align: center;">投票管理2</li>
						</ul>
					</div>
					 
					<!--  
					<div class="col-sm-2"></div>
						<div class="col-sm-2">
						<ul class="nav align-center">
							<li class="li-a"><a href="" style="text-align: center;"><img src="/vote/resources/images/wyyfpz.png"></a></li>
							<li style="text-align: center;">房屋信息</li>
						</ul>
					</div>
					<div class="col-sm-2"></div>
						<div class="col-sm-2">
						<ul class="nav align-center">
							<li class="li-a"><a href="" style="text-align: center;"><img src="/vote/resources/images/wyyfpz.png"></a></li>
							<li style="text-align: center;">房屋信息</li>
						</ul>
					</div>
					<div class="col-sm-2"></div>
						<div class="col-sm-2">
						<ul class="nav align-center">
							<li class="li-a"><a href="" style="text-align: center;"><img src="/vote/resources/images/wyyfpz.png"></a></li>
							<li style="text-align: center;">房屋信息</li>
						</ul>
					</div>
					-->
				</div>		
			</div>
			</form>
		</div>
	</div>
</body>
</html>
