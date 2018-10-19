<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.InitSysConstants"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
   $(document).ready(function(){
		//参数1：表示指定table的ID;参数2：true表示单选；false表示多选
		initTable("tableId",true);
	});

	//查询按钮
	function qryList() {
		$('#_admId').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/credit/quota/ajaxQryQuotaList"
		});
	}

	//新增按钮
	function openAdd() {
		$.indi.openPopup({title: '诚信指标新增',area : ['950px' , '600px'],url: '${ctx }/credit/quota/addQuotaView'});
	}

	//查看按钮
	function openDetail(){
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '诚信指标详情',area : ['950px','600px'],url: '${ctx }/credit/quota/getQuotaView'});
	}
	
	//修改按钮
	function openEdit() {
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '诚信指标修改',area : ['950px','600px'],url: '${ctx }/credit/quota/editQuotaView'});
	}
	
	//删除按钮
	function deleteOper() {
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行删除！",{icon:0});
			return;
		}
		layer.confirm("确定要删除么？",{icon: 3, title:'温馨提示'},function(index){
			layer.close(index);
			$.indi.ajaxSubmit({url: "${ctx}/credit/quota/deleteQuotaInfo.do",success:function(data){
				layer.alert("删除指标信息成功!",{icon: 1},function(index){
					qryList();
					layer.close(index);
				});
			}});
		});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				诚信档案管理 <small><i class="icon-double-angle-right"></i>诚信指标管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="_admId" name="credit_seq" />
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="xmmc" class="col-md-2  control-label">指标类型</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="zblx" name="zblx" >
							${quotaKind}
							</select>
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
					<button type="button" class="btn btn-primary" onclick="openAdd()" power="20180312001">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="deleteOper()" power="20180312002">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="openEdit()" power="20180312003">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="openDetail()">
						<i class="icon-info-sign"></i> 查看
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
					<thead>
						<tr>
							<th width="4%" target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="_tab_admId" target_data="credit_seq">
							</th>
							<th width="4%" target_data="count">序号</th>
							<th width="8%" target_data="zbbm">编码</th>
							<th width="8%" target_data="zblx" type="code" codeNo="CreditQuotaKind">指标类型</th>
							<th width="" target_data="jfyj">指标内容</th>
							<th width="8%" target_data="ckfz">记分值</th>
							<th width="8%" target_data="jfbz">记分标志</th>
							<th width="8%" target_data="syzt" type="code" codeNo="CreditQuotaObject">适用主体</th>
							<!-- 
							<th width="10%" target_data="cjrq">创建日期</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="quota" varStatus="quotaSta">
							<tr>
								<td align="center">
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_admId" target_data="credit_seq" value="${quota.credit_seq }"/>
								</td>
								<td align="center" >${quotaSta.count }</td>
								<td align="center" >${quota.zbbm }</td>
								<td align="center" >${cm:getCodeVal('CreditQuotaKind',quota.zblx) }</td>
								<td align="left" >${quota.jfyj}</td>
								<td align="center" >${quota.ckfz}</td>
								<td align="center" >${quota.jfbz}</td>
								<td align="center" >${cm:getCodeVal('CreditQuotaObject',quota.syzt) }</td>
								<!-- 
								<td align="center" >${quota.cjrq}</td> -->
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
						url : "${ctx }/credit/quota/ajaxQryQuotaList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
