<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<%@page import="com.indihx.comm.InitSysConstants"%>
<%@page import="com.indihx.system.entity.UsrInfo"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@page import="com.indihx.util.ActionUtil"%>
<%@page import="com.indihx.credit.commons.CreditConstants"%>
<%
UsrInfo user = ActionUtil.getUser(request);

String ishpbid="";
if(user.getOrgType().equals(InitSysConstants.ORGTYPE_QUJU)){ 
	ishpbid=user.getHpbBaseId();
}
%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#hidden1").hide();
		
		$('ul li').click(function() {
			$(this).siblings('li').removeClass('active'); //删除其他兄弟元素的样式
			$(this).addClass('active'); // 添加当前元素的样式
			var data = $(this).attr('data');
			switchView(data); //切换视图展示
		});
	}); 
	
	function switchView(data) {
		if (data == "0") {
			$('#_baseInfo').removeClass('collapse');
			$('#_file_info').addClass('collapse');
			$('#_audit_info').addClass('collapse');
		} else if (data == "1") {
			$('#_baseInfo').addClass('collapse');
			$('#_file_info').removeClass('collapse');
			$('#_audit_info').addClass('collapse');
		} else if (data == "2") {
			$('#_baseInfo').addClass('collapse');
			$('#_file_info').addClass('collapse');
			$('#_audit_info').removeClass('collapse');
		}  
	}
	
	//审核通过
	function send(){
		switchView("2");
		var check=false;
		$("textarea[name='score']").each(function(){
			if(this.value==''){
				check=true;
				return;
			}
		});
		if(check){
			layer.alert("请输入最终指标扣分值。");
			return;
		}
		var flag=false;
		$("textarea[name='clyj']").each(function(){
			if(this.value==''){
				flag=true;
				return;
			}
		});
		if(flag){
			layer.alert("请输入最终指标扣分处理意见。");
			return;
		}
		$.indi.ajaxSubmit({url: "${ctx}/credit/appeal/sendToNextStep.do",success:function(data){
			$("#_appId").val(data.appId);
			$("#_nextOrgId").val(data.nextOrgId);
			$.indi.submit({url:'${ctx }/activiti/queryNextNodeByView.do'});
		}});
	}
	
	//退回
	function backward(){
		$.indi.submit({url:'${ctx }/activiti/openRejectMemo.do'});
	}
	
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
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active" data="0"><a href="javascript:void('0')">不良档案</a></li>
					<li data="1"><a href="javascript:void('0')">异议申诉</a></li>
					<li data="2"><a href="javascript:void('0')">评分核正</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<form method="post" id="i-form" class="col-md-12  form-horizontal" role="form">
			<input type="hidden" id="credit_code" name="credit_code" value="${credit_code}"/>
			<input type="hidden" id="appeal_code" name="appeal_code" value="${appeal_code}"/>
			<input type="hidden" id="app_id" name="app_id" value="${appeal.app_id}"/>
			<input type="hidden" id="_appId" name="appId" value="${app.appId}"/>
			<input type="hidden" id="_nextOrgId" name="nextOrgId" value="${app.nextOrgId}"/>
			<input type="hidden" name="relaTable" id="_relaTable"> 
			<input type="hidden" name="relaTabId" id="_relaTabId"> 
			<input type="hidden" name="relaTypeId" id="_relaTypeId"> 
			<input type="hidden" id="zblx" name="zblx" value="<%=InitSysConstants.CreditQuotaKind_BL%>"/>
				<fieldset class="content">
				<div class=" col-md-12" id="_baseInfo">
					<div class="col-md-12">
						<span style="color: #72afd2">基础信息</span>
						<hr>
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
									<th width="20%" >记分说明</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${listInfo}" var="quota" varStatus="quotaSta">
								<tr>
									<td align="center" >${quotaSta.count }</td>
									<td align="center" >${cm:getCodeVal('CreditQuotaObject',quota.syzt) }</td>
									<td align="left" >${quota.jfyj}</td>
									<td align="center" >${quota.jlf}</td>
									<td align="center" >${quota.jfsm}</td>
								</tr>
							</c:forEach>
								<tr class="warning">
									<td colspan="5" align="right" style="padding-right: 55px;color: red;">本次扣分总值：${record.jlf}分</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="collapse col-md-12" id="_file_info">
					<div class="form-group">
						<label class="col-xs-1 control-label text-right">申诉时间</label>
						<div class=" col-xs-11">
							<input type="text" class="form-control col-xs-10"
								placeholder="申诉时间" id="ss" name="ss"  maxlength="80" value="${appeal.appeal_date} ${appeal.appeal_time}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-1 control-label text-right">申诉理由</label>
						<div class=" col-xs-11">
							<textarea type="text" class="form-control col-xs-11" 
								placeholder="异议申诉理由" id="slnr" name="appeal_reasion" rows="5" maxlength="512">${appeal.appeal_reasion}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-1 control-label text-right">其他说明</label>
						<div class=" col-xs-11">
							<textarea type="text" class="form-control col-xs-11" 
								placeholder="其他说明" id="appeal_remark" name="appeal_remark" rows="5" maxlength="512">${appeal.appeal_remark}</textarea>
						</div>
					</div>
					<div class="col-md-12 paddtop10">
						<span style="color: #72afd2">对应要件</span> <span style="color: red">&nbsp;&nbsp;*保存前请选中的附件资料</span>
						<hr>
					</div>
					<div class="col-md-12 paddtop10">
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
									<td align="left">${file.fileType}</td>
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
				</div>			
				<div class="collapse col-md-12" id="_audit_info">
				<div class="col-md-12">
					<span style="color: #72afd2">记分调整</span>
					<hr>
				</div>
				<div class="col-md-12">
						<table class="table table-bordered table-striped with-check" id="tableId">
							<thead>
								<tr>
									<th width="5%" target_data="count">序号</th>
									<th width="10%" target_data="syzt" type="code" codeNo="CreditQuotaObject">适用主体</th>
									<th width="15%" target_data="jfyj">指标依据</th>
									<th width="8%" target_data="jlf">记分值</th>
									<th width="15%" target_data="jfsm">记分说明</th>
									<th width="12%" >最终分值</th>
									<th width="20%" >处理意见</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${listInfo}" var="quota" varStatus="quotaSta">
								<tr>
									<td align="center" valign="middle">${quotaSta.count }</td>
									<td align="center" valign="middle">${cm:getCodeVal('CreditQuotaObject',quota.syzt) }</td>
									<td align="left" valign="middle">${quota.jfyj}</td>
									<td align="center" valign="middle">${quota.jlf}</td>
									<td align="center" valign="middle">${quota.jfsm}</td>
									<td align="center" valign="middle">
										<input type="hidden" name="credit_seq" value="${quota.credit_seq}"/>
										<textarea type="number" class="form-control col-xs-11" placeholder="最终记分值" 
										id="score" name="score" rows="3" maxlength="512" required></textarea>
									</td>
									<td align="center" >
										<textarea type="text" class="form-control col-xs-11" placeholder="处理意见" 
										id="clyj" name="clyj" rows="3" maxlength="512" required></textarea>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-md-12">
						<span style="color: #72afd2">处理结果</span>
						<hr>
					</div>
					<div class="col-md-12">
						<textarea type="text" class="form-control col-xs-11" placeholder="申诉结果" id="appeal_desc" name="appeal_desc" rows="5" maxlength="512" required></textarea>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="send()">
						<i class="icon-save"></i> 审核通过
					</button>
					<button type="button" class="btn btn-primary" onclick="backward()">
						<i class="icon-save"></i> 审核退回
					</button>
					<button type="button" class="btn btn-primary" onclick="close_01()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>