<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	//查询
	function qryList() {
		$("#jwhid").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/community/ajaxcommunityList"
		});
	}

	//新增
	function openAdd() {
		$.indi.openPopup({title: '居委会信息新增',area : ['850px' , '455px'],url: '${ctx }/community/openAddCommunity'});
	}

	//修改
	function saveEdit() {
		var obj = selectRow("_tab_jwhid");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#jwhid").val(obj.val);
		$.indi.openPopup({title: '居委会信息修改',area : ['850px' , '455px'],url: '${ctx }/community/openUpdateCommunity'});
	}
	
	//删除
	function deleteInfo() {
		var obj = selectRow("_tab_jwhid");
		if ( obj.size < 1) {
			layer.alert("请至少选择一条记录进行删除！",{icon:0});
			return;
		}
		$("#jwhid").val(obj.val);
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/community/delCommunityInfo',success:function(data){
				if(data.status == true){
					layer.alert('居委会信息删除成功！',{icon: 1}, function(index){
						layer.close(index);
						$("#jwhid").val("");
						qryList();
					});  
				}else{
					layer.alert('居委会信息删除失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	
	}

	//查看
	function qryInfo() {
		var obj = selectRow("_tab_jwhid");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$("#jwhid").val(obj.val);
		$.indi.openPopup({title: '居委会信息查看',area : ['850px' , '405px'],url: '${ctx }/community/openGetCommunity'});
	}
		
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				组织结构管理 <small><i class="icon-double-angle-right"></i>居委会管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="jwhid" name="jwhid" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-2  control-label">居委会编码</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="jwhbm" name="jwhbm" placeholder="居委会编码">
						</div>
						<label for="_ruleName" class="col-md-2  control-label">居委会名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="jwhmc" name="jwhmc" placeholder="居委会名称">
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
								<input type="hidden" id="_tab_jwhid" target_data="jwhid" >
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="20%" target_data="jwhbm">居委会编码</th>
							<th width="20%" target_data="jwhmc">居委会名称</th>
							<th width="25%" target_data="jwhdz">居委会地址</th>
							<th width="15%" target_data="jwzr">居委主任</th>
							<th width="15%" target_data="jwsj">居委书记</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="listInfo" varStatus="listInfoSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_jwhid" target_data="jwhid" value="${listInfo.jwhid }">
								</td>
								<td class="center" >${listInfoSta.count}</td>
								<td class="center" >${listInfo.jwhbm}</td>
								<td class="center" >${listInfo.jwhmc}</td>
								<td class="center" >${listInfo.jwhdz}</td>
								<td class="center" >${listInfo.jwzr}</td>
								<td class="center" >${listInfo.jwsj}</td>
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
						url : "${ctx }/community/ajaxcommunityList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
