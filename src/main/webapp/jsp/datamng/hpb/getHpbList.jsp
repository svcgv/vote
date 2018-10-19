<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	function openAdd() {
		$.indi.openPopup({title: '区县新增',area : ['800px' , '500px'],url: '${ctx }/hpb/addHpbView'});
	}

	//查询
	function qryList() {
		$('#_admId').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/hpb/ajaxgetHpbList"
		});
	}

	//查看
	function getHpbInfo(){
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '区县信息',area : ['550px','500px'],url: '${ctx }/hpb/getHpbInfo'});
	}
	
	//修改
	function saveEdit() {
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '区县信息修改',area : ['550px','500px'],url: '${ctx }/hpb/editHpbView'});
	}
	//删除
	function deleteStreet() {
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行删除！",{icon:0});
			return;
		}
		layer.confirm("确定要删除么？",{icon: 3, title:'温馨提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/hpb/deleteHpbInfo',success:function(data){
				if(data.status == true){
					layer.alert('区县删除成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList();
					});  
				}else{
					layer.alert('区县删除失败！',{icon: 2});
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
	    window.location.href="${ctx }/hpb/openAqstreetUserList?streetId="+streetId;
	}
		
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				基础数据管理 <small><i class="icon-double-angle-right"></i>区县信息管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="_admId" name="hpbid" />
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="hpbName" class="col-md-2  control-label">区县名称</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="hpbmc" name="hpbmc" placeholder="区县名称">
						</div>
						<label for="name" class="col-md-2  control-label">区县编码</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="hpbbm" name="hpbbm" placeholder="区县编码">
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
					<button type="button" class="btn btn-primary" onclick="deleteStreet()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="saveEdit()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="getHpbInfo()">
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
				<table class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="_tab_admId" target_data="hpbid" name="adm">
							</th>
							<th width="15%" target_data="count">序号</th>
							<th width="15%" target_data="hpbbm">区县编码</th>
							<th width="25%" target_data="hpbmc">区县名称</th>
							<th width="25%" target_data="hpblx" type="code" codeNo="REGION_TYPE">区县级别</th>
							<th width="25%" target_data="cjrq">创建日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="hpbInfo" varStatus="hpbInfoSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_admId" target_data="hpbid" value="${hpbInfo.hpbid }"></td>
								<td class="center" >${hpbInfoSta.count }</td>
								<td class="center" >${hpbInfo.hpbbm }</td>
								<td class="center" >${hpbInfo.hpbmc}</td>
								<td class="center" >${cm:getCodeVal('REGION_TYPE',hpbInfo.hpblx) }</td>
								<td class="center" >${hpbInfo.cjrq}</td>
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
						url : "${ctx }/hpb/ajaxgetHpbList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
