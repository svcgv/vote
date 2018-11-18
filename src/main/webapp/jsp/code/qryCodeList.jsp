<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	
	function openAdd() {
		$.indi.openPopup({title: '数据字典新增',area : ['700px' , '500px'],url: '${ctx }/jsp/code/addCodeData.jsp'});
	}

	//查询
	function qryList() {
		 $("#codeNo").val("");
		 $("#codeKey").val("");//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/code/ajaxQryCodeData"
		});
	}
	//重置
	function reset() {
		 
		 $("#i-codeKey").val("");
		 $("#exampleInputEmail1").val("");
	}

	function updCodeData() {
		 var obj = selectRow("codeNoArray");
		 var obj1=selectRow("codeKeyArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		 $("#codeNo").val(obj.val);
		$("#codeKey").val(obj1.val);
		$.indi.openPopup({title: '数据字典信息修改',area : ['700px','500px'],url: '${ctx }/code/openUpdateCode'});  
	}
		
	
	//删除
	function delCodeData() {
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 var obj = selectRow("codeNoArray");
			 var obj1=selectRow("codeKeyArray");
			 $("#codeNo").val(obj.val);
			$("#codeKey").val(obj1.val);
			 $.indi.ajaxSubmit({url:"${ctx }/code/delCodeData"	,success:function(data){
					if(data.success == true){
						layer.alert('数据字典删除成功！',{icon: 1}, function(index){
							layer.close(index);
							qryList();
						});  
					}else{
						layer.alert('数据字典删除失败！',{icon: 2});
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
					<!-- 联合主键 -->
					<input type="hidden" id="codeNo" name="codeNo" /> 
					<input type="hidden" id="codeKey" name="codeKeyBy" /> 
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2 control-label">字典类型</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="i-codeKey"
								name="codeName" placeholder="类型名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2  control-label">字典名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text"
								id="exampleInputEmail1" name="codeVal" placeholder="字典名称">
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
					<button type="button" class="btn btn-primary" power="2018111601" onclick="openAdd()"> 
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" power="2018111602" onclick="updCodeData()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" power="2018111603" onclick="delCodeData()">
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
							<input type="hidden" id="codeNoArray" target_data="codeNo" >
							<input type="hidden" id="codeKeyArray" target_data="codeKey">
							</th>
							<th target_data="count">序号</th>
							<th target_data="codeNo">类型编码</th>
							<th target_data="codeName">类型说明</th>
							<th target_data="codeKey">字典代码</th>
							<th target_data="codeVal">字典名称</th>
							<th target_data="tmSmp">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="codedata" varStatus="codeSta">
							<tr>
									<!-- 联合主键 -->
								<td ><input type="checkbox" /> 
								    <input type="hidden" id="codeNoArray" target_data="codeNo" value="${codedata.codeNo }">
									<input type="hidden" id="codeKeyArray" target_data="codeKey" value="${codedata.codeKey }">
								</td>	
									
								<td class="center" >${codeSta.count }</td>
								<td class="center" >${codedata.codeNo }</td>
								<td class="center" >${codedata.codeName }</td>
								<td class="center">${codedata.codeKey}</td>
								<td class="center">${codedata.codeVal}</td>
								<td class="center">${codedata.tmSmp}</td>
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
						url : "${ctx }/code/ajaxQryCodeData"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
