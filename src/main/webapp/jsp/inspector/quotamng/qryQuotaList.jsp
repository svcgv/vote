<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>

<html>
<head>
<script type="text/javascript">
//查询
function qryList() {
	$("#check_seq").val("");
	$.indi.loadTableByQry({url :"${ctx}/quotamng/ajaxqryQuotaList"});
}
//新增
function openAdd() {
	$.indi.openPopup({title: '新增指标',area : ['850px' , '630px'],url: '${ctx}/quotamng/openAddQuota'});
}

//修改
function updQuota() {
	var obj = selectRow("checkSeqArr");
	if ( obj.size != 1) {
		layer.alert("请选择一条记录进行修改！",{icon:0});
		return;
	}
	$("#check_seq").val(obj.val);
	$.indi.openPopup({title: '修改检查指标',area : ['850px' , '600px'],url: '${ctx }/quotamng/openUpdateQuota'});
}

//查看
function getQuota() {
	var obj = selectRow("checkSeqArr");
	if ( obj.size != 1) {
		layer.alert("请选择一条记录进行查看！",{icon:0});
		return;
	}
	$("#check_seq").val(obj.val);
	$.indi.openPopup({title: '查看检查指标',area : ['850px' , '600px'],url: '${ctx }/quotamng/openGetQuota'});
}

//删除
function delQuota() {
	var obj = selectRow("checkSeqArr");
	if ( obj.size < 1) {
		layer.alert("请至少选择一条记录进行删除！",{icon:0});
		return;
	}
	$("#check_seq").val(obj.val);
	layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
		 $.indi.ajaxSubmit({url:'${ctx }/quotamng/delQuota',success:function(data){
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
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				日常事务督察<small><i class="icon-double-angle-right"></i>指标管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="check_seq" name="check_seq" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2 control-label">指标编码</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="zbbm"
								name="zbbm" placeholder="指标编码">
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">指标名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="zbmc"
								name="zbmc" placeholder="指标名称">
						</div>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1" class="col-md-2 control-label">指标类别</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="zblb" name="zblb">
							${USER_TYPE}
							</select>
						</div>
						<label for="exampleInputEmail1" class="col-md-2 control-label">记分标志</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="jflx" name="jfbz">
							${USER_TYPE}
							</select>
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
					<button type="button" class="btn btn-primary" onclick="updQuota()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="delQuota()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="getQuota()">
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
							<input type="hidden" id="checkSeqArr" target_data="check_seq">
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="15%"  target_data="zbbm">指标编码</th>
							<th width="20%"  target_data="zbmc">指标名称</th>
							<th width="40%" target_data="jfyj">考核指标细则</th>
							<th width="7%" target_data="ckfz">分值</th>
							<th width="8%" target_data="jfbz" type="code" codeNo="NORM_RECORD">记分类型</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="quotaInfo" varStatus="quotaSts">
							<tr>
								<td ><input type="checkbox" />
								<input type="hidden" id="checkSeqArr" target_data="check_seq" value="${quotaInfo.check_seq }"></td>
								<td align="center" >${quotaSts.count }</td>
								<td align="center" >${quotaInfo.zbbm }</td>
								<td class="center" >${quotaInfo.zbmc }</td>
								<td class="center" >${quotaInfo.jfyj }</td>
								<td align="center" >${quotaInfo.ckfz }</td>
								<td align="center" > <c:if test='${quotaInfo.jfbz !=""}'>${cm:getCodeVal('NORM_RECORD',quotaInfo.jfbz) }</c:if></td>
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
						url : "${ctx }/quotamng/ajaxQuotaList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>