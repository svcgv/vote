<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	//查询
	function qryList() {
		$("#gsryid").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/cspstaff/ajaxStaffList"
		});
	}

	//新增
	function openAdd() {
		$.indi.openPopup({title: '企业人员信息新增',area : ['850px' , '630px'],url: '${ctx }/cspstaff/openAddStaff'});
	}

	//修改
	function saveEdit() {
		var obj = selectRow("_tab_gsryid");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#gsryid").val(obj.val);
		$.indi.openPopup({title: '企业人员信息修改',area : ['850px' , '630px'],url: '${ctx }/cspstaff/openUpdateStaff'});
	}
	
	//删除
	function deleteInfo() {
		var obj = selectRow("_tab_gsryid");
		if ( obj.size < 1) {
			layer.alert("请至少选择一条记录进行删除！",{icon:0});
			return;
		}
		$("#gsryid").val(obj.val);
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/cspstaff/delStaffInfo',success:function(data){
				if(data.status == true){
					layer.alert('企业人员信息删除成功！',{icon: 1}, function(index){
						layer.close(index);
						$("#gsryid").val("");
						qryList();
					});  
				}else{
					layer.alert('企业人员信息删除失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	
	}

	//查看
	function qryInfo() {
		var obj = selectRow("_tab_gsryid");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$("#gsryid").val(obj.val);
		$.indi.openPopup({title: '企业人员信息查看',area : ['850px' , '600px'],url: '${ctx }/cspstaff/openGetStaff'});
	}
		
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				组织结构管理 <small><i class="icon-double-angle-right"></i>企业人员信息</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="gsryid" name="gsryid" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-2  control-label">人员姓名</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="ryxm" name="ryxm" placeholder="人员姓名">
						</div>
						<label for="_ruleName" class="col-md-2  control-label">人员类型</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="rylx" name="rylx">
							${USER_TYPE}
							</select>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary"
								onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()"><i class="icon-plus-sign"></i> 新增</button>
					<button type="button" class="btn btn-primary" onclick="saveEdit()"><i class="icon-edit"></i> 修改</button>
					<button type="button" class="btn btn-primary" onclick="deleteInfo()"><i class="icon-trash"></i> 删除</button>
					<button type="button" class="btn btn-primary" onclick="qryInfo()"><i class="icon-info-sign"></i> 查看</button>
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
								<input type="hidden" id="_tab_gsryid" target_data="gsryid" >
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
						<c:forEach items="${listInfo}" var="listInfo" varStatus="listInfoSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_gsryid" target_data="gsryid" value="${listInfo.gsryid }">
								</td>
								<td class="center" >${listInfoSta.count}</td>
								<td class="center" >${listInfo.ryxm}</td>
								<td class="center" >${cm:getCodeVal('USER_TYPE',listInfo.rylx)}</td>
								<td class="center" >${cm:getCodeVal('CERTTYPE',listInfo.zjlx)}</td>
								<td class="center" >${listInfo.zjhm}</td>
								<td class="center" >${listInfo.lxsj}</td>
								<td class="center" >${listInfo.drzw}</td>
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
						url : "${ctx }/cspstaff/ajaxStaffList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
