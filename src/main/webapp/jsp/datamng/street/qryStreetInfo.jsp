<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	function openAdd() {
		$.indi.openPopup({title: '街道新增',area : ['800px' , '500px'],url: '${ctx }/street/openAddStreet'});
	}

	//查询
	function qryList() {
		$('#_admId').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/street/ajaxQryStreetInfo"
		});
	}

	//修改
	function saveEdit() {
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '街道信息修改',area : ['550px','500px'],url: '${ctx }/street/openUpdateStreet'});
	}
	//删除
	function deleteStreet() {
		 var obj = selectRow("_tab_admId");
		 $("#_admId").val(obj.val);
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/street/delStreetInfo',success:function(data){
				if(data.status == true){
					layer.alert('街道删除成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList();
					});  
				}else{
					layer.alert('街道删除失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	
	}
	//用户
	function openUser() {
		var obj = selectRow("_tab_admId");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录设置用户！",{icon:0});
			return;
		}
		$("#_admId").val(obj.val);
		var streetId = $("#_admId").val();
	    window.location.href="${ctx }/street/openAqstreetUserList?jdid="+streetId;
	}
		
	//查看
	function getStreetInfo(){
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '街道信息',area : ['550px','500px'],url: '${ctx }/street/getStreetInfo'});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				基础数据管理 <small><i class="icon-double-angle-right"></i>街道管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="_admId" name="jdid" />
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="hpbmc" class="col-md-2  control-label">区县名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="hpbmc" name="hpbmc" placeholder="区县名称">
						</div>
						<label for="jdmc" class="col-md-2  control-label">街道名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="jdmc" name="jdmc" placeholder="街道名称">
						</div>
					</div>
					<div class="form-group">
						<label for="jdbm" class="col-md-2  control-label">街道编码</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="jdbm" name="jdbm" placeholder="街道编码">
						</div>
						<div class="col-md-2" align="right">
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
					<button type="button" class="btn btn-primary" onclick="deleteStreet()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="saveEdit()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="getStreetInfo()">
						<i class="icon-info-sign"></i> 查看
					</button>
					<!-- 
					<button type="button" class="btn btn-primary" onclick="openUser()">
						<i class="icon-user"></i> 用户
					</button>
					 -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="_tab_admId" target_data="jdid" >
							
							</th>
							<th width="15%" target_data="count">序号</th>
							<th width="15%" target_data="hpbmc">所属区县</th>
							<th width="25%" target_data="jdmc">街道名称</th>
							<th width="25%" target_data="jdbm">街道编号</th>
							<th width="25%" target_data="cjrq">创建日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="streetInfo" varStatus="streetInfoSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_admId" target_data="jdid" value="${streetInfo.jdid }"></td>
								<td class="center" >${streetInfoSta.count }</td>
								<td class="center" >${streetInfo.hpbmc }</td>
								<td class="center" >${streetInfo.jdmc}</td>
								<td class="center" >${streetInfo.jdbm}</td>
								<td class="center" >${streetInfo.cjrq}</td>
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
						url : "${ctx }/street/ajaxQryStreetInfo"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
