<%@page import="com.indihx.comm.InitSysConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		//参数1：表示指定table的ID;参数2：true表示单选；false表示多选
		initTable("tableId",true);
	}); 

  var orgIdList = ""; //选择的用户
	//页面加载时初始化页面数据
	function openAdd() {
		$.indi.openPopup({title: '物业企业信息新增',area : ['850px' , '600px'],url: '${ctx }/cspinfomng/openAddCsp'});
		//关闭模拟框
	}
	//查询
	function qryList() {
		$('#orgNo').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/cspinfomng/ajaxcspList"
		});
	}
	function updCsp() {
		var obj = selectRow("orgIdArray");
		var obj1 = selectRow("sjztArray");
		$("#orgNo").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		} 
		if(obj1.val!='<%=InitSysConstants.DATA_ZhengChang%>'){
			layer.alert("数据正在修改或删除中，不能进行修改操作！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '物业企业信息修改',area : ['900px','600px'],url: '${ctx }/cspinfomng/openUpdateCsp'});
	}
	//删除
	function deleCsp() {
		var obj = selectRow("orgIdArray");
		var obj1 = selectRow("sjztArray");
		$("#orgNo").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行删除！",{icon:0});
			return;
		}
		if(obj1.val!='<%=InitSysConstants.DATA_ZhengChang%>'){
			layer.alert("数据正在修改或删除中，不能进行修改操作！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '企业信息删除',area : ['800px','500px'],url: '${ctx }/cspinfomng/delCsp'});
	}
	
	//查看
	function getCsp() {
		var obj = selectRow("orgIdArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#orgNo").val(obj.val);
		$.indi.openPopup({title: '物业企业信息查看',area : ['900px','600px'],url: '${ctx }/cspinfomng/openGetCsp'});
	}
	
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				组织机构管理 <small><i class="icon-double-angle-right"></i>物业公司信息管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="orgNo" name="wygsid" /> 
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
					
						<label for="exampleInputEmail1" class="col-md-2 control-label">企业名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="wygsmc"
								name="wygsmc" placeholder="企业名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">企业地址</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="wygsdz"
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
					<button type="button" class="btn btn-primary" onclick="openAdd()">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="updCsp()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="deleCsp()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="getCsp()">
						<i class="icon-info-sign"></i> 查看
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
					<thead>
						<tr>
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="orgIdArray" target_data="wygsid">
							<input type="hidden" id="sjztArray" target_data="sjzt">
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="15%"  target_data="wygsmc">企业名称</th>
							<th width="20%"  target_data="wygsdz">企业地址</th>
							<th target_data="gslx" type="code" codeNo="ORG_NATURE">公司类型</th>
							<th target_data="frdb">法人代表</th>
							<th target_data="qylxr">企业联系人</th>
							<th target_data="qydh">电话</th>
							<th width="10%" target_data="sjzt" type="code" codeNo="INFO_STATUS">数据状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="orgInfo" varStatus="orgInfoSta">
							<tr>
								<td ><input type="checkbox" />
								<input type="hidden" id="orgIdArray" target_data="wygsid" value="${orgInfo.wygsid }">
								<input type="hidden" id="sjztArray" target_data="sjzt" value="${orgInfo.sjzt }"/>
								</td>
								<td class="center" >${orgInfoSta.count }</td>
								<td class="center" >${orgInfo.wygsmc }</td>
								<td class="center" >${orgInfo.wygsdz }</td>
								<td class="center" >${cm:getCodeVal('ORG_NATURE',orgInfo.gslx) } </td>
								<td class="center" >${orgInfo.frdb}</td>
								<td class="center" >${orgInfo.qylxr}</td>
								<td class="center">${orgInfo.qydh}</td>
								<td align="center" >${cm:getCodeVal('INFO_STATUS',orgInfo.sjzt) }</td>
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
						url : "${ctx }/cspinfomng/ajaxcspList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
