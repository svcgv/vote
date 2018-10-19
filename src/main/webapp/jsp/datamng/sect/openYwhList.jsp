<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//查询
	function qryList() {
		$.indi.loadTableByQry({
			url : "${ctx }/sect/ajaxcspList"
		});
	}
	
	//选择
	function confirmRow() {
		var obj = selectRow("orgIdArray");
		var obj1 = selectRow("orgNameArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行确认！",{icon:0});
			return;
		}
		window.parent.$("#wygsid").val(obj.val);
		window.parent.$("#wygsmc").val(obj1.val);
		$.indi.closePopup();
  	}
	
	//取消
	function close_01() {
		 $.indi.closePopup();
	}
</script>
</head>
<body>
	<div class="right-side">
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-3 control-label">企业名称</label>
						<div class="col-md-4">
							<input class="col-md-5 form-control" type="text" id="wygsmc"
								name="wygsmc" placeholder="企业名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">企业地址</label>
						<div class="col-md-4">
							<input class="col-md-5 form-control" type="text" id="wygsdz"
								name="wygsdz" placeholder="企业地址">
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary "
								onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="confirmRow()">
				    	<i class="icon-plus-sign"></i> 选择
			        </button>
			        <button type="button" class="btn btn-primary" onclick="close_01()">
				    	<i class="icon-remove"></i> 关闭
			        </button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="orgIdArray" target_data="wygsid" value="${orgInfo.wygsid }">
							<input type="hidden" id="orgNameArray" target_data="wygsid" value="${orgInfo.wygsmc }">
							</th>
							<th width="8%" target_data="count">序号</th>
							<th width=""  target_data="wygsmc">企业名称</th>
							<th width="20%"  target_data="wygsdz">企业地址</th>
							<th width="10%" target_data="frdb">法人代表</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="orgInfo" varStatus="orgInfoSta">
							<tr>
								<td align="center">
									<input type="checkbox" />
									<input type="hidden" id="orgIdArray" target_data="wygsid" value="${orgInfo.wygsid }">
									<input type="hidden" id="orgNameArray" target_data="wygsmc" value="${orgInfo.wygsmc }">
								</td>
								<td align="center" >${orgInfoSta.count }</td>
								<td >${orgInfo.wygsmc }</td>
								<td >${orgInfo.wygsdz }</td>
								<td >${orgInfo.frdb}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
					var pages = {
						total : "${pageInfo.total}",
						pageNum : "${pageInfo.pageNum}",
						pageSize : "${pageInfo.pageSize}",
						pages : "${pageInfo.pages}",
						url : "${ctx }/sect/ajaxcspList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>