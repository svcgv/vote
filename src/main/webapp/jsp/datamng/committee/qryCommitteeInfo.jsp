<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function openAdd() {
		$.indi.openPopup({title: '社区新增',area : ['560px' , '450px'],url: '${ctx }/cmt/openAddCommittee'});
	}

	//查询
	function qryList() {
		$('#sqid').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/cmt/ajaxQryCommitteeInfo"
		});
	}

	//修改
	function saveEdit() {
		var obj = selectRow("_tab_hpbId");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#sqid").val(obj.val);
		$.indi.openPopup({title: '社区信息修改',area : ['550px','450px'],url: '${ctx }/cmt/openUpdateCommittee'});
	}
	
	//查看
	function getCommittee(){
		var obj = selectRow("_tab_hpbId");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$("#sqid").val(obj.val);
		$.indi.openPopup({title: '社区信息',area : ['550px','500px'],url: '${ctx }/cmt/getCommittee'});
	}
	
	//删除
	function deleteCommittee() {
		 var obj = selectRow("_tab_hpbId");
		 $("#sqid").val(obj.val);
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/cmt/delCommitteeInfo',success:function(data){
				if(data.status == true){
					layer.alert('社区删除成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList();
					});  
				}else{
					layer.alert('社区删除失败！',{icon: 2});
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
				基础数据管理 <small><i class="icon-double-angle-right"></i>社区管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="sqid" name="sqid" />
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="hpbmc" class="col-md-2  control-label">县区名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="hpbmc" name="hpbmc" placeholder="县区名称">
						</div>
						<label for="jdmc" class="col-md-2  control-label">街道名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="jdmc" name="jdmc" placeholder="街道名称">
						</div>
					</div>
					<div class="form-group">
						<label for="sqbm" class="col-md-2  control-label">社区编号</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="sqbm" name="sqbm" placeholder="社区编号">
						</div>
						<label for="sqmc" class="col-md-2  control-label">社区名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="sqmc" name="sqmc" placeholder="社区名称">
						</div>
						<div class="col-md-2" >
							<button type="button" class="btn btn-primary"
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
					<button type="button" class="btn btn-primary" onclick="deleteCommittee()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="saveEdit()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="getCommittee()">
						<i class="icon-info-sign"></i> 查看
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="_tab_hpbId" target_data="sqid" >
							
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="15%" target_data="sqbm">社区编号</th>
							<th width="15%" target_data="sqmc">社区名称</th>
							<th width="15%" target_data="hpbmc">县区名称</th>
							<th width="15%" target_data="jdmc">街道名称</th>
							<th width="15%" target_data="cjrq">创建日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="listInfo" varStatus="listInfoSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_hpbId" target_data="id" value="${listInfo.sqid}">								</td>
								<td class="center" >${listInfoSta.count }</td>
								<td class="center" >${listInfo.sqbm}</td>
								<td class="center" >${listInfo.sqmc}</td>
								<td class="center" >${listInfo.hpbmc}</td>
								<td class="center" >${listInfo.jdmc}</td>
								<td class="center" >${listInfo.cjrq}</td>
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
						url : "${ctx }/cmt/ajaxQryCommitteeInfo"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
