<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
  var orgIdList = ""; //选择的用户
	//页面加载时初始化页面数据
	function openAdd() {
		$.indi.openPopup({title: '业委会信息新增',area : ['850px' , '600px'],url: '${ctx }/hoc/openAddHoc'});
		//关闭模拟框
	}
	//查询
	function qryList() {
		$('#hocNo').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/hoc/ajaxhocList"
		});
	}
	 
	function updHoc() {
		var obj = selectRow("hocIdArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#hocNo").val(obj.val);
		$.indi.openPopup({title: '业委会信息修改',area : ['900px','600px'],url: '${ctx }/hoc/openUpdateHoc'});
	}
	//删除
	function deleHoc() {
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 var obj = selectRow("hocIdArray");
			 $("#hocNo").val(obj.val);
			 $.indi.ajaxSubmit({url:'${ctx }/hoc/delHocInfo'	,success:function(data){
				if(data.status == true){
					layer.alert('业委会删除成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList();
					});  
				}else{
					layer.alert('业委会删除失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	}
	
	function getHoc() {
		var obj = selectRow("hocIdArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#hocNo").val(obj.val);
		$.indi.openPopup({title: '业委会信息查看',area : ['900px','600px'],url: '${ctx }/hoc/openGetHoc'});
	}
	 
	function getHocStaff() {
		$('#hocNo').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/hoc/hocStaffList"
		});
	}
	 
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				组织机构管理 <small><i class="icon-double-angle-right"></i>业委会信息管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="hocNo" name="ywhid" /> 
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
					
						<label for="exampleInputEmail1" class="col-md-2 control-label">业委会代码</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="ywhdm"
								name="ywhdm" placeholder="业委会代码">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">业委会名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="ywhmc"
								name="ywhmc" placeholder="业委会名称">
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
					<button type="button" class="btn btn-primary" onclick="updHoc()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="deleHoc()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="getHoc()">
						<i class="icon-info-sign"></i> 查看
					</button> 
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="hocIdArray" target_data="ywhid">
							</th>
							<th width="5%" target_data="count">序号</th>
							<th align="center"  width="15%"  target_data="ywhdm">业主大会代码</th>
							<th class="center"  width="20%"  target_data="ywhmc">业主大会名称</th>
							<th class="center" target_data="ywhdz"  >办公地址</th>
							<th align="center"  target_data="rq">任期</th>
							<th align="center"  target_data="term">第几届</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="hocInfo" varStatus="hocInfoSta">
							<tr>
								<td ><input type="checkbox" />
								<input type="hidden" id="hocIdArray" target_data="ywhid" value="${hocInfo.ywhid }"></td>
								<td class="center" >${hocInfoSta.count }</td>
								<td align="center" >${hocInfo.ywhdm }</td>
								<td class="center" >${hocInfo.ywhmc }</td>
								<td class="center" >${hocInfo.ywhdz}</td>
								<td align="center" >${hocInfo.rq}</td>
								<td align="center">${hocInfo.term}</td>
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
						url : "${ctx }/hoc/ajaxhocList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
