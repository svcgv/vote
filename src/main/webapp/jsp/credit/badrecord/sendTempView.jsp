<%@page import="com.indihx.comm.InitSysConstants"%>
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
		} else if (data == "1") {
			$('#_baseInfo').addClass('collapse');
			$('#_quota_info').removeClass('collapse');
		}
	}
	
	//保存按钮
	function save() {
		$('#i-form').validate({
			rules:{
				jlrq:{
					required: true
				},
				hpbid:{
					required: true
				},
				bjlx:{
					required: true
				},
				info_from:{
					required: true
				},
				bllx:{
					required: true
				},
				wygsmc:{
					required: true
				},
				xmmc:{
					required: true
				},
				ryxm:{
					required: true
				},
				slnr:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/credit/badrecord/saveRecordInfo.do",success:function(data){
			$("#_appId").val(data.appId);
			$("#_nextOrgId").val(data.nextOrgId);
			layer.alert("不良信息档案保存成功!",{icon: 1});
		}});
	}
	
	//提交按钮
	function send(){
		var res =$('#i-form').validate({
			rules:{
				jlrq:{
					required: true
				},
				hpbid:{
					required: true
				},
				bjlx:{
					required: true
				},
				info_from:{
					required: true
				},
				bllx:{
					required: true
				},
				wygsmc:{
					required: true
				},
				xmmc:{
					required: true
				},
				ryxm:{
					required: true
				},
				slnr:{
					required: true
				}
			}
		});
		//验证tab1表单验证是否通过,false表示未通过，true表示通过
		if(!res.form()){
			switchView("0");
			return;
		}else{
			if($("#credit_seq:checked").length==0){
				switchView("1"); 
				layer.alert("请设置不良信用记录扣分依据！", {
					icon : 0
				});
				return;
			}
			//获取选中指标的输入框-扣分值
			var isEmpty=false;
			var vali=$("#credit_seq:checked").parents("tr").find("input[name='jlfs']");  
			vali.each(function(index){
				if(vali.val()==''){
					isEmpty = true;
					return;
				}
			});
			if(isEmpty){
				switchView("1"); 
				layer.alert("请输入选中记分依据对应的扣分值", {
					icon : 0
				});
				return;
			}
		}
		$.indi.ajaxSubmit({url: "${ctx}/credit/badrecord/sendToNextStep.do",success:function(data){
			$("#_appId").val(data.appId);
			$("#_nextOrgId").val(data.nextOrgId);
			$.indi.submit({url:'${ctx }/activiti/queryNextNodeByView.do'});
		}});
	}
	
	//选择物业公司
	function openWygs(){
		$.indi.openPopup({title:'选择物业公司',area:['700px','500px'],isDate:false,url:'${ctx}/credit/badrecord/openCspList.do'});
	}
	//选择涉及项目
	function openSect(){
		$.indi.openPopup({title:'选择项目',area:['700px','500px'],isDate:false,url:'${ctx}/credit/badrecord/openSectList.do'});
	}
	//选择项目经理
	function openXmjl(){
		$.indi.openPopup({title:'选择项目经理',area:['700px','500px'],isDate:false,url:'${ctx}/credit/badrecord/openXmjlList.do'});
	}
	
	//上传附件
	function uploadFile(signId, fileType,fileTypeId) {
		var title = '[' + fileType + ']文件上传';
		$('#_relaTabId').val(signId);//用户操作的档案类型ID
		$('#_relaTypeId').val(fileTypeId);//系统配置的档案类型ID
		$('#_relaTable').val('CREDIT_FILE_SIGN');//关联表名
		$.indi.openPopup({
			url : '${ctx}/file/uploadFileView.do',
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
					<li class="active" data="0"><a href="javascript:void('0')">基本信息</a></li>
					<li data="1"><a href="javascript:void('0')">指标依据</a></li>
				</ul>
			</div>
		</div>
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
			<input type="hidden" id="zblx" name="zblx" value="<%=InitSysConstants.CreditQuotaKind_BL%>"/>
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
							placeholder="受理日期" id="jlrq" name="jlrq"   maxlength="80" value="${record.jlrq}" onClick="WdatePicker()"/>
						<p class="help-block"></p>	
						<input type="hidden" id="jlsj" name="jlsj" value="<%=DateUtil.getSysTime()%>"/>	
					</div>
					<div class="col-xs-4 paddtop7">
						<span style="color: red">*受理日期默认为当前日期</span>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">受理区局</label>
					<div class="col-xs-4">
						<select  class="form-control col-xs-7 " id="hpbid" name="hpbid" >
							${cm:createRegionHtml(hpbList,record.hpbid) }
						</select>
					</div>
					<label class="col-xs-2 control-label text-right">被记分主体</label>
					<div class=" col-xs-4">
						<select  class="form-control col-xs-11 " id="bjlx" name="bjlx">
							${bjlx}
						</select>
						<p class="help-block"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">信息类型</label>
					<div class=" col-xs-4">
						<select  class="form-control col-xs-11 " id="bllx" name="bllx">
							${bllx}
						</select>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">信息来源</label>
					<div class=" col-xs-4">
						<select  class="form-control col-xs-11 " id="info_from" name="info_from">
							${info_from}
						</select>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">涉及小区</label>
					<div class=" col-xs-9">
						<input type="text" class="form-control col-xs-10" placeholder="请选择涉及的小区" id="xmmc" name="xmmc"  maxlength="80" value="${record.xmmc}" />
						<input type="hidden" id="xmid" name="xmid" value="${record.xmid}"/>	
					</div>
					<button type="button" class="btn btn-primary" onclick="openSect()">
					<i class="icon-plus-sign"></i><span>选择</span></button>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业公司</label>
					<div class="col-xs-9">
						<input type="text" class="form-control col-xs-10" placeholder="请选择物业服务企业" id="wygsmc" name="wygsmc" maxlength="80" value="${record.wygsmc}"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openWygs()">
					<i class="icon-plus-sign"></i><span>选择</span></button>
					<input type="hidden" name="wygsid" id="wygsid" value="${record.wygsid}" />
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目经理</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目经理姓名" id="ryxm" name="ryxm"  maxlength="80" value="${record.ryxm}" />
						<p class="help-block"></p>		
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
				<div class="col-md-12 paddtop10">
					<span style="color: #72afd2">对应要件</span> <span style="color: red">&nbsp;&nbsp;*上传附件资料</span>
					<hr>
				</div>
				<div class="col-md-12 paddtop10">
					<table class="table table-bordered table-striped table-paging">
						<thead>
							<tr>
								<th width="10%">签收标签</th>
								<th  align="left">要件类型</th>
								<th width="8%">操作</th>
							</tr>
						</thead>
						<tbody>
						<!-- 隐藏 -->
						<c:forEach items="${fileSigns}" var="file" varStatus="fileSta">
							<tr>
								<td align="center">
									<input type="checkbox" value="${file.signId}" name="signIds" <c:if test="${file.isSign eq 1}">checked="checked"</c:if>/>
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
			<div class="col-md-12 paddtop10">
				<table class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
					<thead>
						<tr>
							<th width="4%" target_data="checkbox"><i class="icon-resize-vertical"></i></th>
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
							<td align="center">
								<input type="checkbox" id="credit_seq" name="credit_seq" value="${quota.credit_seq }"  
								<c:if test="${not empty quota.jlf}">
									checked="checked"
								</c:if>/> 
							</td>
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
			<div class=" form-group" align="center">
				<button type="button" class="btn btn-primary" onclick="save()">
					<i class="icon-save"></i> 暂存
				</button>
				<button type="button" class="btn btn-primary" onclick="send()">
					<i class="icon-save"></i> 提交
				</button>
				<button type="button" class="btn btn-primary" onclick="close_01()">
					<i class="icon-remove"></i> 取消
				</button>
			</div>	
			</fieldset>
			</form>
		</div>
	</div>
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
</body>
</html>