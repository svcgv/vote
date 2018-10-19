<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#hidden1").hide();
	}); 
	
	//查看附件
	function uploadFile(signId, fileType,fileTypeId) {
		var title = '[' + fileType + ']文件上传';
		$('#_relaTabId').val(signId);//用户操作的档案类型ID
		$('#_relaTypeId').val(fileTypeId);//系统配置的档案类型ID
		$('#_relaTable').val('CREDIT_FILE_SIGN');//关联表名
		$.indi.openPopup({
			url : '${ctx}/file/seeFileView.do',
			parentOpen : true,
			title : title
		});
	}
	
	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal" role="form">
			<input type="hidden" id="_appId" name="appId" value="${record.app_id}"/>
			<input type="hidden" id="_taskId" name="taskId" value="${app.taskId }"/>
			<input type="hidden" id="_nextOrgId" name="nextOrgId" value="${app.nextOrgId}"/>
			<input type="hidden" id="app_id" name="app_id" value="${record.app_id}"/>
			<input type="hidden" id="credit_code" name="credit_code" value="${credit_code}"/>
			<input type="hidden" id="credit_status" name="credit_status" value="${record.credit_status}"/>
			<input type="hidden" name="relaTable" id="_relaTable"> 
			<input type="hidden" name="relaTabId" id="_relaTabId"> 
			<input type="hidden" name="relaTypeId" id="_relaTypeId"> 
			<div class=" col-md-12" id="_baseInfo">
				<div class="col-md-12">
					<span style="color: #72afd2">基础信息</span>
					<hr>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">受理日期</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control"
							placeholder="受理日期" id="jlrq" name="jlrq"   maxlength="80" value="${record.jlrq}" />
						<p class="help-block"></p>	
						<input type="hidden" id="jlsj" name="jlsj" value="<%=DateUtil.getSysTime()%>"/>	
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">受理区局</label>
					<div class="col-xs-4">
						<input type="text" class="form-control"
							placeholder="受理	区局" id="hpbmc" name="hpbmc"   maxlength="80" value="${record.hpbmc}" />
					</div>
					<label class="col-xs-2 control-label text-right">被记分主体</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control"
							placeholder="被记分主体" id="bjlx" name="bjlx"   maxlength="80" value="${cm:getCodeVal('CreditQuotaObject',record.bjlx) }" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">信息类型</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control"
							placeholder="信息类型" id="bllx" name="bllx"   maxlength="80" value="${cm:getCodeVal('CreditBadRecordKind',record.bllx) }" />
					</div>
					<label class="col-xs-2 control-label text-right">信息来源</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control"
							placeholder="信息来源" id="info_from" name="info_from"   maxlength="80" value="${cm:getCodeVal('CreditInfoFrom',record.info_from) }" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">告知日期</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control"
							placeholder="告知日期" id="gzrq" name="gzrq"   maxlength="80" value="${record.gzrq}" />
					</div>
					<label class="col-xs-2 control-label text-right">申诉截止日期</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control"
							placeholder="申诉截止日期" id="ssjzrq" name="ssjzrq"   maxlength="80" value="${record.ssjzrq}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">涉及小区</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"  id="xmmc" name="xmmc"  maxlength="80" value="${record.xmmc}" />
						<input type="hidden" id="xmid" name="xmid" value="${record.xmid}"/>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业公司</label>
					<div class="col-xs-10">
						<input type="text" class="form-control col-xs-10" id="wygsmc" name="wygsmc" maxlength="80" value="${record.wygsmc}"/>
					</div>
					<input type="hidden" name="wygsid" id="wygsid" value="${record.wygsid}" />
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目经理</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目经理姓名" id="ryxm" name="ryxm"  maxlength="80" value="${record.ryxm}" />
						<input type="hidden" id="gsryid" name="gsryid" value="${record.gsryid}"/>		
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">受理内容</label>
					<div class=" col-xs-10">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="不良信息内容描述" id="slnr" name="slnr" rows="5" maxlength="512">${record.slnr}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-10">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="描述" id="remark" name="remark" rows="5" maxlength="512">${record.remark}</textarea>
					</div>
				</div>
			</div>		
			<div class="col-md-12">
				<span style="color: #72afd2">记分指标依据</span>
				<hr>
			</div>
			<div class="col-md-12">
				<table class="table table-bordered table-striped with-check" id="tableId">
					<thead>
						<tr>
							<th width="7%" target_data="count">序号</th>
							<th width="10%" target_data="syzt" type="code" codeNo="CreditQuotaObject">适用主体</th>
							<th width="45%" target_data="jfyj">指标依据</th>
							<th width="8%" >分值</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${listInfo}" var="quota" varStatus="quotaSta">
						<tr>
							<td align="center" >${quotaSta.count }</td>
							<td align="center" >${cm:getCodeVal('CreditQuotaObject',quota.syzt) }</td>
							<td align="left" >${quota.jfyj}</td>
							<td align="center" >${quota.score}</td>
						</tr>
					</c:forEach>
						<tr class="warning">
							<td colspan="4" align="right" style="padding-right: 55px;color: red;">本次扣分总值：${record.jlf}分</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-12 paddtop10">
			<span style="color: #72afd2">对应要件</span>
			<hr>
		</div>
		<div class="col-md-8  paddtop10">
			<table class="table table-bordered table-striped table-paging">
				<thead>
					<tr>
						<th width="10%">序号</th>
						<th  align="left">要件类型</th>
						<th width="8%">操作</th>
					</tr>
				</thead>
				<tbody>
				<!-- 隐藏 -->
				<c:forEach items="${fileSigns}" var="file" varStatus="fileSta">
					<tr>
						<td align="center">
							${fileSta.count }
						</td>
						<td align="left">${file.fileType }</td>
						<td align="center">
							<a href="javascript:void('0')" style="font-size: 20px;" title="上传附件" onclick="uploadFile('${file.signId}','${file.fileType }','${file.fileTypeId }')">
								<i class="icon-upload"></i>
							</a>&nbsp;&nbsp;
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</form>
	</div>
</div>
</body>
</html>