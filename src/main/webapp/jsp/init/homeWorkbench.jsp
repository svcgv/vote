<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script src="${ctx }/resources/map/echarts.min.js"></script>
<%-- <script src="${ctx }/resources/map/bmap.js"></script> --%>
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
			<form action="psot"></form>
			<div class="row">
				<div class="col-md-6 col-xs-12">
					<div class="box box-primary">
						<div class="box-header">
							<div class="pull-right box-tools"></div>
							<h3 class="box-title" style="color: #3c8dbc">
								<i class="icon-th-list"></i> 待处理预警信息
							</h3>
						</div>
						<div class="box-body no-padding">
							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>项目编号</th>
										<th>报警级别</th>
										<th>规则描述</th>
										<th width="10%">操作</th>
									</tr>
								</thead>
								<tbody>
									<tr style="background-color: red">
										<td>CR15005</td>
										<td>红色报警</td>
										<td>延迟开工</td>
										<td align="center"><a href="JavaScript:void(0);"
											title="处理报警"><i class="icon-edit"></i></a></td>
									</tr>
									<tr>
										<td>C15004</td>
										<td>橙色报警</td>
										<td>没有开工许可证编号</td>
										<td align="center"><a href="JavaScript:void(0);"
											title="处理报警"><i class="icon-edit"></i></a></td>
									</tr>
									<tr>
										<td>C15004</td>
										<td>黄色报警</td>
										<td>测试情况看下看下</td>
										<td align="center"><a href="JavaScript:void(0);"
											title="处理报警"><i class="icon-edit"></i></a></td>
									</tr>
									<tr>
										<td>C15004</td>
										<td>黄色报警</td>
										<td>测试情况看下看下</td>
										<td align="center"><a href="JavaScript:void(0);"
											title="处理报警"><i class="icon-edit"></i></a></td>
									</tr>
									<tr>
										<td>C15004</td>
										<td>橙色报警</td>
										<td>测试情况看下看下</td>
										<td align="center"><a href="JavaScript:void(0);"
											title="处理报警"><i class="icon-edit"></i></a></td>
									</tr>
									<tr>
										<td>C15004</td>
										<td>橙色报警</td>
										<td>测试情况看下看下</td>
										<td align="center"><a href="JavaScript:void(0);"
											title="处理报警"><i class="icon-edit"></i></a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="box box-success">
						<div class="box-header">
							<div class="pull-right box-tools">
								<ul class="nav nav-tabs">
									<li role="presentation" data="m" class="active"><a
										href="JavaScript:void(0);">月份</a></li>
									<li role="presentation" data="q"><a
										href="JavaScript:void(0);">季度</a></li>
									<li role="presentation" data="y"><a
										href="JavaScript:void(0);">年份</a></li>
								</ul>
							</div>
							<h3 class="box-title" style="color: #00a65a">
								<i class="icon-picture"></i> 折线图
							</h3>
						</div>
						<div class="box-body no-padding">
							<div id="mapMainzx" style="width: 100%; height: 400px;"></div>
							<script type="text/javascript">
							var myChart2 = echarts.init(document
									.getElementById('mapMainzx'));
							
								$(".nav-tabs li").click(
										function() {
											$(this).siblings('li').removeClass(
													'active'); // 删除其他兄弟元素的样式
											$(this).addClass('active'); // 添加当前元素的样式
											var val = $(this).attr('data');
											if (val == "m") {
												myChart2.setOption(getM());  //初始化数据
											} else if (val == "q") {
												myChart2.setOption(getP());  //初始化数据
											} else if (val == "y") {
												myChart2.setOption(getY());  //初始化数据
											}
								});

								function getM(){
									var data =[ '1月份', '2月份', '3月份','4月份', '5月份', '6月份', '7月份' ];
									var series = [
										{
											name : '红色报警',
											type : 'line',
											stack : '总量',
											data : [ 120, 132, 101,
													134, 90, 230, 210 ]
										},
										{
											name : '橙色报警',
											type : 'line',
											stack : '总量',
											data : [ 220, 182, 191,
													234, 290, 330, 310 ]
										},
										{
											name : '黄色报警',
											type : 'line',
											stack : '总量',
											data : [ 150, 232, 201,
													154, 190, 330, 410 ]
										} ];
									return getTemp(data,series);
								}

								function getP(){
									var data =[ '1季度', '2季度', '3季度','4季度' ];
									var series = [
										{
											name : '红色报警',
											type : 'line',
											stack : '总量',
											data : [ 15, 16, 15, 13]
										},
										{
											name : '橙色报警',
											type : 'line',
											stack : '总量',
											data : [ 24, 21, 20, 18]
										},
										{
											name : '黄色报警',
											type : 'line',
											stack : '总量',
											data : [ 34, 23, 14, 15]
										} ];
									return getTemp(data,series);
								}

								function getY(){
									var data =[ '2013', '2014', '2015','2016','2017' ];
									var series = [
										{
											name : '红色报警',
											type : 'line',
											stack : '总量',
											data : [ 150, 160, 150, 130,220]
										},
										{
											name : '橙色报警',
											type : 'line',
											stack : '总量',
											data : [ 240, 210, 200, 180,230]
										},
										{
											name : '黄色报警',
											type : 'line',
											stack : '总量',
											data : [ 340, 230, 140, 150,250]
										} ];
									return getTemp(data,series);
								}

								function getTemp(data,series){
									var option = {
											title : {
												text : '预警折线图'
											},
											tooltip : {
												trigger : 'axis'
											},
											legend : {
												data : [ '红色报警', '橙色报警', '黄色报警' ]
											},
											grid : {
												left : '3%',
												right : '4%',
												bottom : '3%',
												containLabel : true
											},
											toolbox : {
												feature : {
													saveAsImage : {}
												}
											},
											xAxis : {
												type : 'category',
												boundaryGap : false,
												data : data
											},
											yAxis : {
												type : 'value'
											},
											series : series,
											color : [ '#FF3030', '#ffb134',
													'#FFFF00' ]
										};
										return option;
								}
								myChart2.setOption(getM());  //初始化数据
							</script>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-xs-12">
					<div class="box box-success">
						<div class="box-header">
							<!-- tools box -->
							<div class="pull-right box-tools"></div>

							<h3 class="box-title" style="color: #00a65a">
								<i class="icon-picture"></i> 预警图表
							</h3>
						</div>
						<div class="box-body no-padding">
							<div id="mapMain" style="width: 100%; height: 400px;"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document
										.getElementById('mapMain'));
								var option = {
									title : {
										text : '预警类型比例',
										subtext : '当月数量',
										x : 'center'
									},
									tooltip : {
										trigger : 'item',
										formatter : "{a} <br/>{b} : {c} ({d}%)"
									},
									legend : {
										orient : 'vertical',
										left : 'left',
										data : [ '红色预警', '橙色预警', '黄色预警' ]
									},
									series : [ {
										name : '预警详情',
										type : 'pie',
										radius : '55%',
										center : [ '50%', '60%' ],
										data : [ {
											value : 1,
											name : '红色预警'
										}, {
											value : 2,
											name : '橙色预警'
										}, {
											value : 3,
											name : '黄色预警'
										}, ],
										itemStyle : {
											emphasis : {
												shadowBlur : 10,
												shadowOffsetX : 0,
												shadowColor : 'rgba(0, 0, 0, 0.5)'
											}
										}
									} ],
									color : [ '#FF3030', '#ffb134', '#FFFF00' ]
								};
								myChart.setOption(option);
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
