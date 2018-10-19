<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
<body>
	<div class="right-side">
		<div class="content">
		<div class="row">
			<div class="col-md-12 table-responsive">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox">
								<i class="icon-resize-vertical"></i>
								<input type="hidden" id="_tab_info_id" target_data="infoId" >
							</th>
							<th width="5%"  target_data="count">序号</th>
							<th width="15%"  target_data="sectName">项目</th>
							<th width="10%" target_data="buildCode">楼幢</th>
							<th width="10%" target_data="unitCode">单元</th>
							<th width="10%" target_data="floorCode">楼层</th>
							<th width="10%" target_data="roomCode">户号</th>
							<th width="8%"  target_data="infoArea">分户面积</th>
							<th width="10%" target_data="ownerName">业主姓名</th>
							<th width="20%" target_data="certCode">身份证号</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		</div>
	</div>
</body>
</html>