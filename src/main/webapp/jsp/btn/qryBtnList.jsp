<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	
	function openAdd() {
		$.indi.openPopup({title: '按钮新增',area : ['700px' , '500px'],url: '${ctx }/jsp/btn/addBtnInfo.jsp'});
	}

	//查询
	function qryList() {
		 $("#btnId").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/btn/ajaxQryBtnInfo.do"
		});
	}
	//重置
	function reset() {
		 
		 $("#exampleInputEmail1").val("");
		 $("#i-btnPage").val("");
	}

	function updBtnInfo() {
		 var obj = selectRow("btnIdArray");
		if ( obj.size != 1) {	
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		 $("#btnId").val(obj.val);
		$.indi.openPopup({title: '按钮信息修改',area : ['700px','500px'],url: '${ctx }/btn/openUpdateBtn.do'});  
	}
		
	
	//删除
	function delBtnInfo() {
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 var obj = selectRow("btnIdArray");
			 $("#btnId").val(obj.val);
			 $.indi.ajaxSubmit({url:"${ctx }/btn/delBtnInfo.do"	,success:function(data){
					if(data.success == true){
						layer.alert('按钮信息删除成功！',{icon: 1}, function(index){
							layer.close(index);
							qryList();
						});  
					}else{
						layer.alert('按钮信息删除失败！',{icon: 2});
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
				系统管理 <small><i class="icon-double-angle-right"></i>数据字典</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<!--主键 -->
						<input type="hidden" id="btnId" name="btnId" /> 
					
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2  control-label">按钮名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="exampleInputEmail1" name="btnName" placeholder="按钮名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">所属页面</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="i-btnPage"
								name="btnPage" placeholder="所属页面">
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary " onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
							<button type="button" class="btn btn-primary "
								onclick="reset()">
								<i class="icon-remove"></i>重置
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary"  onclick="openAdd()">
					<!-- power="2018111621"  按钮菜单只分配给系统管理员   -->
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="updBtnInfo()">
					<!-- power="2018111622"  -->
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary"  onclick="delBtnInfo()">
					<!-- power="2018111623"  -->
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
							<input type="hidden" id="btnIdArray" target_data="btnId" >
							</th>
							<th target_data="count">序号</th>
							<th target_data="btnId">按钮编号</th>
							<th target_data="btnName">按钮名称</th>
							<th target_data="btnRmk">按钮说明</th>
							<th target_data="btnPage">所属页面</th>
							<th target_data="tmSmp" type="date">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="btninfo" varStatus="btnSta">
							<tr>
									<!-- 主键 -->
								<td ><input type="checkbox" /> 
								    <input type="hidden" id="btnIdArray" target_data="btnId" value="${btninfo.btnId }">
									
								</td>	
									
								<td class="center" >${btnSta.count }</td>
								<td class="center" >${btninfo.btnId }</td>
								<td class="center" >${btninfo.btnName }</td>
								<td class="center" >${btninfo.btnRmk }</td>
								<td class="center">${btninfo.btnPage}</td>
								<td class="center">${cm:formatFromDB(btninfo.tmSmp)}</td>
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
						url : "${ctx }/btn/ajaxQryBtnInfo.do"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
