<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$("input").attr("readonly",true);
		$("select").attr("disabled","disabled");
	})
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">专家姓名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="张三" id="expert_name" name="expert_name" value="${expert.expert_name}"/>
					</div>
					<label class="col-xs-2 control-label text-right">性别</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="expert_six" name="expert_six" >
							${SEXTYPE}
						</select>
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="cert_type" name="cert_type" >
							${CERTTYPE}
						</select>
					</div>
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="cert_code" name="cert_code" value="${expert.cert_code}"/>
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">公司名称</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="公司名称" id="company_name" name="company_name" value="${expert.company_name}"/>
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">公司地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="公司地址" id="company_addr" name="company_addr" value="${expert.company_addr}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">所属区</label>
					<div class=" col-xs-3">
						<%-- <input type="text" class="form-control col-xs-11" id="hpbmc" name="hpbmc" value="${expert.hpbmc}" /> --%>
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid">
							${cm:createRegionHtml(hpbList,expert.hpbid)}
						</select>
					</div>
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="contact_tel" name="contact_tel" maxlength="18" minlength="6" value="${expert.contact_tel}" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">联系地址</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系地址" id="contact_addr" name="contact_addr" value="${expert.contact_addr}" />
					</div>
					<label class="col-xs-2 control-label text-right">电子邮箱</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11"
							placeholder="电子邮箱" id="email" name="email" value="${expert.email}" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">所属类别</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="expert_type" name="expert_type" value="${cm:getCodeVal('EXPERT_TYPE',expert.expert_type)}" />
					</div>
					<label class="col-xs-2 control-label text-right">搜索关键字</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11"
							placeholder="关键字" id="search_key" name="search_key" value="${expert.search_key}" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="备注" id="expert_remark" name="expert_remark" maxlength="200" value="${expert.expert_remark}" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>