<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//页面加载时初始化页面数据
	$(document).ready(function() {
	
	});
	

	function openAdd() {
		$.indi.openPopup({title: '参数新增',area : ['550px' , '600px'],url: '${ctx }/para/openAddPara'});
	
	}
	//查询
	function qryList_01() {
		$.indi.loadTableByQry({
			url :"${ctx}/para/ajaxQryParaInfo"
		});
	}
	//重置
	function reset() {
		 
		 $("#paramsName").val("");
		 $("#paramsType").val("");
	}
	
	function updPara() {
		var obj = selectRow("ParaIdArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#paramsNo").val(obj.val);
		$.indi.openPopup({title: '参数修改',area : ['550px','600px'],url: '${ctx }/para/qryParaInfoById'});
	}
	//删除
	function deleUsr() {
	
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 var obj = selectRow("ParaIdArray");
			 $("#paramsNo").val(obj.val);
			 $.indi.ajaxSubmit({url:'${ctx }/para/delParaInfo'	,success:function(data){
				if(data.success == true){
					layer.alert('参数删除成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList_01();
					});  
				}else{
					layer.alert('参数删除失败！',{icon: 2});
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
				系统管理 <small><i class="icon-double-angle-right"></i>参数管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post"><input type="hidden" id="paramsNo" name="paramsNo" />
				 <input
						type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<!-- <label for="exampleInputEmail1" class="col-md-2  control-label">参数编码</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="paramsVal"
								name="paramsVal" placeholder="参数编码">
						</div> -->
						<label for="exampleInputEmail1" class="col-md-2 control-label">参数名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="paramsName"
								name="paramsName" placeholder="参数名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2  control-label">参数类型</label>
						<div class="col-md-3">
							<select class="col-md-8 form-control" name="paramsType" id="paramsType">
							${codeData.ewTypeHtml }
							</select>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary "
								onclick="qryList_01()">
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
					<button type="button" class="btn btn-primary" power="10000301" onclick="openAdd()">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" power="10000302" onclick="updPara()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" power="10000303" onclick="deleUsr()">
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
							 <input  type="hidden" id="ParaIdArray" target_data="paramsNo">
							</th>
							<th target_data="count">序号</th>
							<th target_data="paramsNo">参数代码</th>
							<th target_data="paramsName">参数名称</th>
							<th target_data="paramsType">参数类别</th>
							<th target_data="paramsVal">参数值</th>
							<!-- <th target_data="tmSmp" type="date">创建时间</th> -->
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="usrinfo" varStatus="usrSta">
							<tr>
								<td ><input type="checkbox" /> <input
									type="hidden" id="ParaIdArray" target_data="paramsNo"
									value="${usrinfo.paramsNo }"></td>
								<td class="center" >${usrSta.count }</td>
								<td class="center" >${usrinfo.paramsNo }</td>
								<td class="center" >${usrinfo.paramsName }</td>
								<td class="center" >${usrinfo.paramsType }</td>
								<td class="center" >${usrinfo.paramsVal}</td>
								<%-- <td class="center" >${cm:formatFromDB(usrinfo.tmSmp)}</td> --%>
								
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
						url : "${ctx }/para/ajaxQryParaInfo"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
