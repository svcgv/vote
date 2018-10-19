<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
<script type="text/javascript">
	function qryList(){
		var sectName = $("#sect_name").val();
		var ipage = $("#i-pages").val();
		$.indi.loadTableByQry({
			url : "${ctx }/sect/getSectInfo",
			data: '{"sectName":"' + sectName + '","currPage":"' + ipage + '"}'
		});
	}
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				3投票表决管理 <small><i class="icon-double-angle-right"></i>小区信息</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<div class="col-md-5">
				<form class="form-horizontal" id="i-form" name="from-name" method="post">
					<input type="hidden" id="info_id" name="infoId" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-3  control-label">小区名</label>
						<div class="col-md-6">
							<input class="col-md-2 form-control" type="text" id="sect_name" name="sect_name" placeholder="小区名">
						</div>
						<div class="col-md-1">
							<button type="button" class="btn btn-primary"
								onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
						</div>
					</div>
				</form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox">
								<i class="icon-resize-vertical"></i>
								<input type="hidden" id="_tab_sect_id" target_data="sectId" >
							</th>
							<th width="5%"  target_data="count">序号</th>
							<th width="15%"  target_data="sectName">项目</th>
							<th width="10%" target_data="houCount">房屋数</th>
							<th width="10%" target_data="buildCount">楼栋数</th>
							<th width="10%" target_data="infoArea">总面积</th>
							<th width="10%" target_data="createDate">创建日期</th>
							<th width="8%"  target_data="createTime">创建时间</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				</div>
			</div>
		</div>
</body>
</html>
