<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
  var orgIdList = ""; //选择的用户
	//页面加载时初始化页面数据
	function openAdd() {
		var obj = selectRow("_tab_gsryid");
		var objn = selectRow("_tab_ryxm");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行确认！",{icon:0});
			return;
		}
		window.parent.$("#gsryid").val(obj.val);
		window.parent.$("#gsryname").val(objn.val);
		$.indi.closePopup();
  }
	//查询
	function qryList_01() {
		var url = "${ctx }/cspsect/ajaxcspPersonList";
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
			<input type="hidden" id="gsryid" name="gsryid" value="">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-2  control-label">人员姓名</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="ryxm" name="ryxm" placeholder="人员姓名">
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
								<input type="hidden"  id="_tab_gsryid" target_data="gsryid" >
								<input type="hidden"  id="_tab_ryxm" target_data="ryxm" >
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="20%" target_data="ryxm">人员姓名</th>
							<th width="15%" target_data="rylx" type="code" codeNo="USER_TYPE">人员类型</th>
							<th width="15%" target_data="zjlx" type="code" codeNo="CERTTYPE">证件类型</th>
							<th width="15%" target_data="zjhm">证件号码</th>
							<th width="15%" target_data="lxsj">手机</th>
							<th width="15%" target_data="drzw">职务</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="listcs" varStatus="listcsSta">
							<tr>
								<td target_data="checkbox"><input type="checkbox" /> 
									<input type="hidden" id="_tab_gsryid" target_data="gsryid" value="${listcs.gsryid }">
									<input type="hidden" id="_tab_ryxm" target_data="ryxm" value="${listcs.ryxm}">
								</td>
								<td class="center" >${listcsSta.count}</td>
								<td class="center" >${listcs.ryxm}</td>
								<td class="center" >${cm:getCodeVal('USER_TYPE',listcs.rylx)}</td>
								<td class="center" >${cm:getCodeVal('CERTTYPE',listcs.zjlx)}</td>
								<td class="center" >${listcs.zjhm}</td>
								<td class="center" >${listcs.lxsj}</td>
								<td class="center" >${listcs.drzw}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
				var regionType = $("#hpblx").val();
				var url = "${ctx }/cspsect/ajaxcspPersonList";
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
