<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.InitSysConstants"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
   $(document).ready(function(){
		//参数1：表示指定table的ID;参数2：true表示单选；false表示多选
		initTable("tableId",false);
	});

	//查询按钮
	function qryList() {
		$('#_admId').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/credit/quota/ajaxQryQuotaList"
		});
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
	
	function openBadDetail(){
		$.indi.openPopup({title: '不良信息详情',area : ['950px','600px'],url: '${ctx }/credit/badrecord/getBadRecordView.do?credit_code='+${credit_code}});
	}
	
	//确定
	function saveBadRecordQuota(){
		$.indi.ajaxSubmit({url: "${ctx}/credit/badrecord/sendToNextStep.do",success:function(data){
			$("#_appId").val(data.appId);
			$("#_nextOrgId").val(data.nextOrgId);
			$.indi.submit({url:'${ctx }/activiti/queryNextNodeByView.do'});
		}});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1>
				诚信档案管理 <small>选择记分依据</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="saveBadRecordQuota()" >
						<i class="icon-plus-sign"></i> 确定
					</button>
					<button type="button" class="btn btn-primary" onclick="openDetail()">
						<i class="icon-info-sign"></i> 查看
					</button>
					<button type="button" class="btn btn-primary" onclick="openBadDetail()">
						<i class="icon-info-sign"></i> 不良信息
					</button>
				</div>
			</div>
			<div class="row">
				<form method="post" id="i-form" class="form-horizontal" role="form">
				<input type="hidden" id="_appId" name="appId" value="${app.appId}"/>
				<input type="hidden" id="_taskId" name="taskId" value="${app.taskId }"/>
				<input type="hidden" id="_nextOrgId" name="nextOrgId" value="${app.nextOrgId}"/>
				<input type="hidden" id="app_id" name="app_id" value="${record.app_id}"/>
				<input type="hidden" id="credit_code" name="credit_code" value="${credit_code}"/>
				<input type="hidden" id="_admId" name="credit_seq" value=""/>
				<input type="hidden" id="zblx" name="zblx" value="<%=InitSysConstants.CreditQuotaKind_BL%>"/>
				<div class="col-md-12">
				<table class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
					<thead>
						<tr>
							<th width="4%" target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="_tab_admId" target_data="credit_seq">
							</th>
							<th width="4%" target_data="count">序号</th>
							<th width="8%" target_data="zbbm">编码</th>
							<th width="8%" target_data="syzt" type="code" codeNo="CreditQuotaObject">适用主体</th>
							<th width="" target_data="jfyj">指标依据</th>
							<th width="8%" target_data="ckfz">参考分值</th>
							<th width="8%" >分值</th>
							<th width="8%" >记分说明</th>
							<th width="8%" >处理意见</th>
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
								<td align="center" >${cm:getCodeVal('CreditQuotaObject',quota.syzt) }</td>
								<td align="left" >${quota.jfyj}</td>
								<td align="center" >${quota.ckfz}</td>
								<td align="center" ><input type="text" id="jlfs" name="jlfs" value="" size="6"/></td>
								<td align="center" ><input type="text" id="jfsm" name="jfsm" value=""/></td>
								<td align="center" ><input type="text" id="clyj" name="clyj" value=""/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</form>
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
</body>
</html>
