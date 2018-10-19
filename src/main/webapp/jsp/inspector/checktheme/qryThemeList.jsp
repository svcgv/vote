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
	//查询
	function qryList() {
		$("#themeid").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/checktheme/ajaxqryThemeList"
		});
	}

	//新增
	function openAdd() {
		$.indi.openPopup({title: '制定检查主题',area : ['850px' , '600px'],url: '${ctx }/checktheme/openAddTheme'});
	}

	//修改
	function saveEdit() {
		var obj = selectRow("_tab_themeid");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#themeid").val(obj.val);
		$.indi.openPopup({title: '修改检查主题',area : ['850px' , '600px'],url: '${ctx }/checktheme/openUpdateTheme'});
	}
	
	//删除
	function deleteTheme() {
		var obj = selectRow("_tab_themeid");
		if ( obj.size < 1) {
			layer.alert("请至少选择一条记录进行删除！",{icon:0});
			return;
		}
		$("#themeid").val(obj.val);
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/checktheme/delThemeInfo',success:function(data){
				if(data.status == true){
					layer.alert('检查主题删除成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList();
					});  
				}else{
					layer.alert('检查主题删除失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	}

	//查看
	function qryInfo() {
		var obj = selectRow("_tab_themeid");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$("#themeid").val(obj.val);
		$.indi.openPopup({title: '查看检查主题',area : ['850px' , '600px'],url: '${ctx }/checktheme/openGetTheme'});
	}
	 
	//发布主题
	function pulishTheme() {
		var obj = selectRow("_tab_themeid");
		if ( obj.size < 1) {
			layer.alert("请至少选择一条记录进行删除！",{icon:0});
			return;
		}
		$("#themeid").val(obj.val);
		layer.confirm("确定要发布么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/checktheme/pulishThemeInfo',success:function(data){
				if(data.status == true){
					layer.alert('检查主题发布成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList();
					});  
				}else{
					layer.alert('检查主题发布失败！',{icon: 2});
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
				日常事务管理 <small><i class="icon-double-angle-right"></i>制定检查主题</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="themeid" name="themeid" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-2  control-label">主题名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="theme_name" name="theme_name" placeholder="主题名称">
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
					<button type="button" class="btn btn-primary" onclick="deleteTheme()"><i class="icon-trash"></i> 删除</button>
					<button type="button" class="btn btn-primary" onclick="qryInfo()"><i class="icon-info-sign"></i> 查看</button>
					<button type="button" class="btn btn-primary" onclick="pulishTheme()"><i class="icon-info-sign"></i> 发布</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox">
								<i class="icon-resize-vertical"></i>
								<input type="hidden" id="_tab_themeid" target_data="theme_id" >
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="40%" target_data="theme_name">检查主题名称</th>
							<th width="20%" target_data="start_date">检查开始日期</th>
							<th width="20%" target_data="end_date">检查结束日期</th>
							<th width="10%" target_data="status" type="code" codeNo="IS_FLAG">是否发布</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="listInfo" varStatus="listInfoSta">
							<tr>
								<td >
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_themeid" target_data="theme_id" value="${listInfo.theme_id }">
								</td>
								<td align="center" >${listInfoSta.count }</td>
								<td class="center" >${listInfo.theme_name}</td>
								<td align="center" >${listInfo.start_date}</td>
								<td align="center" >${listInfo.end_date}</td>
								<td align="center" >${cm:getCodeVal('IS_FLAG',listInfo.status)}</td>
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
						url : "${ctx }/checktheme/ajaxqryThemeList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
