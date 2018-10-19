<%@page import="com.indihx.credit.commons.CreditConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.InitSysConstants"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
   $(document).ready(function(){

   });

	//查询按钮
	function qryList() {
		$('#_admId').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/credit/appeal/ajaxQryAppealList"
		});
	}

	function openRecordView(val1,val2) {
		$("#_admId1").val(val1);
		$("#_admId2").val(val2);
		$.indi.openPopup({title: '不良信用建档详情',area : ['950px' , '600px'],url: '${ctx }/credit/appeal/getBadTempRecordView'});
	}

	function openAppealView(val1,val2,val3){
		$("#_admId1").val(val1);
		$("#_admId2").val(val2);
		if(val3!='<%=CreditConstants.AppealFlag_YiShenSU%>'){
			layer.alert("正在异议申诉或已申诉，不能进行申诉操作！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '异议申诉申请',area : ['950px' , '600px'],url: '${ctx }/credit/appeal/addAppealView'});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				诚信档案管理 <small><i class="icon-double-angle-right"></i>异议申诉申请</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="_admId1" name="credit_code_sel" />
					<input type="hidden" id="_admId2" name="app_id_sel" />
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="credit_code" class="col-md-2  control-label">诚信档案编号</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="credit_code" name="credit_code" placeholder="诚信档案编号">
						</div>
						<label for="bjlx" class="col-md-2  control-label">记分主体</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="bjlx" name="bjlx" >
							${CreditQuotaObject}
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="hpbmc" class="col-md-2  control-label">所属区县</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="hpbid" name="hpbid" onchange="createStreetOption(this.value)">
							${cm:createRegionHtml(hpbList,"") }
							</select>
						</div>
						<label for="xmmc" class="col-md-2  control-label">项目名称</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="xmmc" name="xmmc" placeholder="项目名称">
						</div>
					</div>
					<div class="form-group">
						<label for="wygsmc" class="col-md-2  control-label">物业公司</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="wygsmc" name="wygsmc" placeholder="物业公司名称">
						</div>
						<label for="ryxm" class="col-md-2  control-label">项目经理</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="ryxm" name="ryxm" placeholder="项目经理姓名">
						</div>
						<div class="col-md-2" >
							<button type="button" class="btn btn-primary"
								onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
					<thead>
						<tr>
							<th width="3%" target_data="count">序号</th>
							<th width="8%" target_data="credit_code">诚信档案编号</th>
							<th width="15%" target_data="xmmc">涉及项目名称</th>
							<th width="10%" target_data="bjlx" type="code" codeNo="CreditQuotaObject">被记分主体</th>
							<th width="8%" target_data="hpbmc">受理单位</th>
							<th width="5%" target_data="jlf" >记分值</th>
							<th width="6%" target_data="ssbz">申诉标志</th>
							<th width="8%" target_data="ssjzrq">申诉截止日期</th>
							<th width="15%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="record" varStatus="recordSta">
							<tr>
								<td align="center" >${recordSta.count }</td>
								<td align="center" >${record.credit_code }</td>
								<td align="left" >${record.xmmc}</td>
								<td align="center" >${cm:getCodeVal('CreditQuotaObject',record.bjlx) }</td>
								<td align="center" >${record.hpbmc}</td>
								<td align="center" >${record.jlf}</td>
								<td align="center" >${record.ssbz}</td>
								<td align="center" >${record.ssjzrq}</td>
								<td align="center" > 
									<button class="btn btn-small btn-primary" type="button" onclick="openRecordView(${record.credit_code },${record.app_id})"><i class="icon-check"></i>查看</i></button>
									<button class="btn btn-small btn-primary" type="button" onclick="openAppealView(${record.credit_code },${record.app_id},${record.ssbz})"><i class="icon-edit"></i>申诉申请</button>
								</td>
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
						url : "${ctx }/credit/appeal/ajaxQryAppealList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
