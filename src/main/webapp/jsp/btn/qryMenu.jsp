<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	
	//查询
	function qryMenuList() {
		 $("#btnId").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/btnRole/ajaxQryBtnInfo.do"
		});
	}

	//保存角色
	function selectMenu() {
		
		
		var menuId = selectRow("menuIdArray");
		var menuName = selectRow("menuNameArray");
		
		 
	 		// index
// 	 		$('#btnPage', window.parent.document).val(menuIdArray.val);
// 	 		$('#btnPageId', window.parent.document).val(menuIdArray.val);
	 	
	 		 window.parent.$("#btnPage").val(menuName.val)
			 window.parent.$("#menuId").val(menuId.val)
	 		
			$.indi.closePopup();
		
		
	}
	
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1>
				系统管理 <small><i class="icon-double-angle-right"></i>菜单列表</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<!--角色主键 -->
					<input type="hidden" name="roleId" id="_roleId">
					<input type="hidden" name="btnId" id="_btnId">
					<div class="form-group">
					<label class="col-xs-2 control-label text-right">菜单名称</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-11" id="_menuName"
							name="menuName" placeholder="菜单名称" />
					</div>
					<div class="col-xs-2">
						<button type="button" class="btn btn-primary "
							onclick="qryMenuList()" ><i class="icon-search"></i> 查询</button>
					</div>
				</div>
				<div class="row" align="center">
				<button type="button" class="btn btn-primary" onclick="selectMenu()"
					id="menuSave">
					<i class="icon-save"></i> 选择
				</button>
				
				<button type="button" class="btn btn-primary" onclick="javascript:$.indi.closePopup();">
						<i class="icon-remove"></i> 取消
					</button>
			</div>
			</form>
				</form>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="menuId" target_data="menuId" >
							</th>
							<th target_data="menuId">菜单ID</th>
							<th target_data="menuName">菜单名称</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="menuinfo" varStatus="menuSta">
							<tr>
									<!-- 主键 -->
								<td ><input type="checkbox" 
								
								/> 
								    <input type="hidden" id="menuIdArray" target_data="menuId" value="${menuinfo.menuId }">
								    <input type="hidden" id="menuNameArray" target_data="menuId" value="${menuinfo.menuName }">
								</td>	
									
								<td class="center" >${menuinfo.menuId }</td>
								<td class="center" >${menuinfo.menuName }</td>
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
						url : "${ctx }/menu/ajaxQryMenuInfo.do"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
				
			</div>
		</div>
</body>
</html>
