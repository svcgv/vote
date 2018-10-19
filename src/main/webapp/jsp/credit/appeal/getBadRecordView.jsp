<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
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
			$('#_quota_info').addClass('collapse');
			$('#_file_info').addClass('collapse');
			$('#_notify_info').addClass('collapse');
		} else if (data == "1") {
			$('#_baseInfo').addClass('collapse');
			$('#_file_info').removeClass('collapse');
			$('#_quota_info').addClass('collapse');
			$('#_notify_info').addClass('collapse');
		} else if (data == "2") {
			$('#_baseInfo').addClass('collapse');
			$('#_file_info').addClass('collapse');
			$('#_quota_info').removeClass('collapse');
			$('#_notify_info').addClass('collapse');
		} else if (data == "3") {
			$('#_baseInfo').addClass('collapse');
			$('#_file_info').addClass('collapse');
			$('#_quota_info').addClass('collapse');
			$('#_notify_info').removeClass('collapse').siblings('li').removeClass('active');
		}
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
			title : title,
			area: ['950px' , '600px']
		});
	}
	
	//查看告知单
	function print(){
		$.indi.openPopup({title:'打印不良信用告知单',area:['700px','500px'],url:'${ctx }/credit/badrecord/printBadRecordView'});
	}
	
	//异议申请
	function sendAppeal(){
		$.indi.submit({url:'${ctx }/activiti/queryNextNodeByView.do'});
	}
	
	//退回
	function backward(){
		$.indi.submit({url:'${ctx }/activiti/openRejectMemo.do'});
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
					<li class="active" data="0"><a href="javascript:void('0')">基本信息</a></li>
					<li data="1"><a href="javascript:void('0')">电子资料</a></li>
					<li data="2"><a href="javascript:void('0')">指标依据</a></li>
					<li data="3"><a href="javascript:void('0')">告知单信息</a></li>
				</ul>
			</div>
		</div>
		<div class="form-horizontal">
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
			<fieldset class="content">
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
			<div class="collapse col-md-12" id="_file_info">
				<div class="col-md-12">
					<span style="color: #72afd2">电子要件</span>
					<hr>
				</div>
				<div class="col-md-8">
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
			</div>	
			<div class="collapse col-md-12" id="_quota_info">
				<div class="col-md-12">
					<span style="color: #72afd2">记分指标依据</span>
					<hr>
				</div>
				<div class="col-md-12">
					<table class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
						<thead>
							<tr>
								<th width="4%" target_data="count">序号</th>
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
								<td align="center" >${quotaSta.count }</td>
								<td align="center" >${cm:getCodeVal('CreditQuotaObject',quota.syzt) }</td>
								<td align="left" >${quota.jfyj}</td>
								<td align="center" >${quota.ckf}</td>
								<td align="center" ><input type="text" id="jlfs" name="jlfs" value="${quota.jlf}" size="6"/></td>
								<td align="center" ><input type="text" id="jfsm" name="jfsm" value="${quota.jfsm}"/></td>
								<td align="center" ><input type="text" id="clyj" name="clyj" value="${quota.clyj}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
			</div>
			<div class="collapse col-md-12" id="_notify_info">
				<blockquote>
				<div class="col-md-12"  align="center">
					<span style="color: #72afd2"><br><h3><strong>物业服务企业信用信息记分告知单</strong></h3></span>
					<hr>
				</div>
				<div class="form-group">
					  <p class="text-left"><u title="物业公司">${record.wygsmc}</u>：</p>
					  <p class="text-left">
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	经查，你公司有&nbsp;&nbsp;<u >${record.slnr}</u>&nbsp;&nbsp;情形。根据《XX市物业服务企业和项目经理信用信息管理暂行办法》的有关规定和
					  	《XX市物业服务企业和项目经理警示信息试行记分标准》的有关规定，责令你公司在收到本告知单之日(&nbsp;<u >&nbsp;${record.gzrq}&nbsp;</u>&nbsp;)起的
					  	&nbsp;<u >&nbsp;${record.zgqx}&nbsp;</u>&nbsp;天内予以整改，同时对你公司给予记&nbsp;<u >&nbsp;${record.jlf}&nbsp;</u>&nbsp;分的处理。
					  </p>	
					  <p class="text-left">
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	若你公司对上述处理和记分有异议，可在收到本告知单之日起的&nbsp;<u>&nbsp;${record.ssqx}&nbsp;</u>&nbsp;日内向区房屋行政管理部门申请核查。
					  </p>
					  <p class="text-right">
					  	单&nbsp;&nbsp;位（盖章）:
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </p>
					  <p class="text-right">
					  	经办人（签名）:
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </p>
				</div> 
				</blockquote>
			</div>	
			</form>
			</fieldset>
		</div>
	</div>
	<script type="text/javascript">
		var pages = {
			total : "${pageInfo.total}",
			pageNum : "${pageInfo.pageNum}",
			pageSize : "${pageInfo.pageSize}",
			pages : "${pageInfo.pages}",
			url : "${ctx }/credit/badrecord/ajaxQryQuotaList"
		}
		$.indi.loadPages(pages);
	</script>
</body>
</html>