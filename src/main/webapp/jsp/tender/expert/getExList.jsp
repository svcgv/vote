<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	//查询
	function qryList() {
		$("#expert_id").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/expert/ajaxExList"
		});
	}

	//新增
	function openAdd() {
		$.indi.openPopup({title: '评标专家信息新增',area : ['850px' , '650px'],url: '${ctx }/expert/addExpert'});
	}
	
	//编辑
	function editExInfo(){
		var obj = selectRow("_tab_expert_id");
		if(obj.size!=1){
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}else{
			$("#expert_id").val(obj.val);
			$.indi.openPopup({title: '评标专家信息',area : ['850px' , '650px'],url: '${ctx }/expert/editExpert'});
		}
	}

	//删除
	function deleteCommittee() {
		var obj = selectRow("_tab_expert_id");
		if ( obj.size < 1) {
			layer.alert("请至少选择一条记录进行删除！",{icon:0});
			return;
		}
		$("#expert_id").val(obj.val);
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/expert/delExInfo',success:function(data){
				if(data.status == true){
					layer.alert('专家信息删除成功！',{icon: 1}, function(index){
						layer.close(index);
						$("#expert_id").val("");
						qryList();
					});  
				}else{
					layer.alert('专家信息删除失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	
	}
	
	//查看专家详情
	function qryExInfo(){
		var obj = selectRow("_tab_expert_id");
		if(obj.size!=1){
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}else{
			$("#expert_id").val(obj.val);
			$.indi.openPopup({title: '评标专家信息',area : ['850px' , '650px'],url: '${ctx }/expert/qryExInfo'});
		}
	}
	
	//导入界面
	function openExcel(){
		$.indi.openPopup({title: '专家信息导入',area : ['850px' , '550px'],url: '${ctx }/expert/impExpert'});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				评标管理 <small><i class="icon-double-angle-right"></i>评标专家库管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="expert_id" name="expert_id" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-2  control-label">专家姓名</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="expert_name" name="expert_name" placeholder="姓名">
						</div>
						<label for="_ruleName" class="col-md-2  control-label">创建时间</label>
						<div class="col-md-3">
							<input class="form-control" type="text" id="create_date" 
							name="create_date" placeholder="<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %>" value="" onClick="WdatePicker()">
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
					<button type="button" class="btn btn-primary" onclick="openExcel()"><i class="glyphicon glyphicon-import"></i>导入</button>
					<button type="button" class="btn btn-primary" onclick="editExInfo()"><i class="icon-edit" ></i> 修改</button>
					<button type="button" class="btn btn-primary" onclick="deleteCommittee()"><i class="icon-trash"></i> 删除</button>
					<button type="button" class="btn btn-primary" onclick="qryExInfo()"><i class="icon-info-sign"></i> 查看</button>
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
								<input type="hidden" id="_tab_expert_id" target_data="expert_id" >
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="10%" target_data="hpbmc">所在区</th>
							<th width="10%" target_data="expert_name">专家姓名</th>
							<th width="20%" target_data="company_name">在职公司</th>
							<th width="15%" target_data="cert_code">证件号码</th>
							<th width="15%" target_data="expert_type" type="code" codeNo="EXPERT_TYPE">专家类别</th>
							<th width="10%" target_data="create_date">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="listInfo" varStatus="listInfoSta">
							<tr>
								<td align="center">
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_expert_id" target_data="expert_id" value="${listInfo.expert_id }">
								</td>
								<td align="center" class="center" >${listInfoSta.count}</td>
								<td align="center" class="center" >${listInfo.hpbmc}</td>
								<td align="center" class="center" >${listInfo.expert_name}</td>
								<td align="center" class="center" >${listInfo.company_name}</td>
								<td align="center" class="center" >${listInfo.cert_code}</td>
								<td align="center" class="center" >${cm:getCodeVal('EXPERT_TYPE',listInfo.expert_type)}</td>
								<td align="center" class="center" >${listInfo.create_date}</td>
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
