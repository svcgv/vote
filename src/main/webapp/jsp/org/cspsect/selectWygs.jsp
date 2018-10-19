<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
  var orgIdList = ""; //选择的用户
	//页面加载时初始化页面数据
	function openAdd() {
		var obj = selectRow("wygsid");
		var obj1 = selectRow("wygsmc");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行确认！",{icon:0});
			return;
		}
		window.parent.$("#wygsid").val(obj.val);
		window.parent.$("#wygsmc").val(obj1.val);
		$.indi.closePopup();
  }
	//查询
	function qryList_01() {
		var url = "${ctx }/cspsect/ajaxWygsList";
		$.indi.loadTableByQry({
			url : url
		});
	}
	function close_01() {
		 $.indi.closePopup();
	}
	
</script>
</head>
<body>
	<div class="right-side">
		<div class="content">
			<div class="row">
			<input type="hidden" name="hpblx" id="hpblx" value="${region }">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label class="col-xs-3 control-label text-right">企业名称</label>
						<div class=" col-xs-4">
							<input type="text" class="form-control col-xs-11" id="wygsmc"name="wygsmc" placeholder="企业名称" />
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-primary" onclick="qryList_01()">
									<i class="icon-search"></i>查询
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()">
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
								<input type="hidden" id="wygsid" target_data="wygsid">
								<input type="hidden" id="wygsmc" target_data="wygsmc">
							</th>
							<th width="10%" target_data="count">序号</th>
							<th width="20%" target_data="wygsmc">企业名称</th>
							<th width="30%" target_data="wygsdz">企业地址</th>
							<th width="20%" target_data="gslx" type="code" codeNo="ORG_NATURE">公司类型</th>
							<th width="20%" target_data="frdb">法人代表</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="orgInfo" varStatus="orgInfoSta">
							<tr>
								<td target_data="checkbox"><input type="checkbox" /> 
									<input type="hidden" id="wygsid" target_data="wygsid" value="${orgInfo.wygsid}">
									<input type="hidden" id="wygsmc" target_data="wygsmc" value="${orgInfo.wygsmc}">
								</td>
								<td class="center"  target_data="count">${orgInfoSta.count }</td>
								<td class="center"  target_data="wygsmc">${orgInfo.wygsmc }</td>
								<td class="center"  target_data="wygsdz">${orgInfo.wygsdz }</td>
								<td class="center"  target_data="gslx">${cm:getCodeVal('ORG_NATURE',orgInfo.gslx) } </td>
								<td class="center"  target_data="frdb">${orgInfo.frdb}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
				var regionType = $("#hpblx").val();
				var url = "${ctx }/cspsect/wygsList";
					var pages = {
						total : "${pageInfo.total}",
						pageNum : "${pageInfo.pageNum}",
						pageSize : "${pageInfo.pageSize}",
						pages : "${pageInfo.pages}",
						url : url
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
