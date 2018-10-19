<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	function openAdd() {
		$.indi.openPopup({title: '菜单新增',area : ['700px' , '500px'],url: '${ctx }/menu/openMenuInfo'});
	}

	//查询
	function qryList() {
		 $("#menuId").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/menu/ajaxQryMenuInfo"
		});
	}

	function updMenu() {
		 var obj = selectRow("menuIdArray");
		 var obj1 = selectRow("parentIdArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		 $("#menuId").val(obj.val);
		$.indi.openPopup({title: '菜单信息修改',area : ['700px','500px'],url: '${ctx }/menu/openUpdateMenu'});  
	}
	//删除
	function deleMenu() {
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 var obj = selectRow("menuIdArray");
			$("#menuId").val(obj.val);
			 $.indi.ajaxSubmit({url:"${ctx }/menu/delMenuInfo"	,success:function(data){
					if(data.success == true){
						layer.alert('菜单删除成功！',{icon: 1}, function(index){
							layer.close(index);
							qryList();
						});  
					}else{
						layer.alert('菜单删除失败！',{icon: 2});
					}
				}});
				layer.close(index);
			});	
		
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				系统管理 <small><i class="icon-double-angle-right"></i>菜单管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="text" style="display:none"/> <!-- 防止按enter键提交form -->
					<input type="hidden" id="menuId" name="menuId" /> <input
						type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2  control-label">菜单名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="exampleInputEmail1" name="menuName" placeholder="菜单名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">菜单级别</label>
						<div class="col-md-3">
							<!-- <input class="col-md-8 form-control" type="text" id="i-menuLevel"
								name="menuLevel" placeholder="菜单级别">
						 -->
						<select  name="menuLevel"   class="col-md-8 form-control">
							 ${codeData.levelHtml } 
						</select>
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
					<button type="button" class="btn btn-primary" onclick="openAdd()">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="updMenu()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="deleMenu()">
						<i class="icon-trash"></i> 删除
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
							<input type="hidden" id="menuIdArray" target_data="menuId" >
							<input type="hidden" id="parentIdArray"  target_data="parentId" >
							</th>
							<th target_data="count">序号</th>
							<th target_data="menuId">菜单ID</th>
							<th target_data="menuName">菜单名称</th>
							<th target_data="parentId">父菜单</th>
							<th target_data="menuUrl">菜单地址</th>
							<th target_data="sortNum">顺序编号</th>
							<th target_data="codeVal">菜单级别</th>
							<th target_data="tmSmp" type="date">时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="menuinfo" varStatus="menuSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="menuIdArray" target_data="menuId" value="${menuinfo.menuId }">
									<input type="hidden" id="parentIdArray"  target_data="parentId"  value="${menuinfo.parentId}">
								</td>
								 <td align="center">${menuSta.count }</td>
								<td class="center" >${menuinfo.menuId }</td>
								<td class="center" >${menuinfo.menuName }</td>
								<td class="center" >${menuinfo.parentId}</td>
								<td class="center" >${menuinfo.menuUrl}</td>
								<td align="center" >${menuinfo.sortNum}</td>
								<td align="center" >${menuinfo.codeVal}</td>
								<td align="center">${cm:formatFromDB(menuinfo.tmSmp)}</td>
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
						url : "${ctx }/menu/ajaxQryMenuInfo"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
