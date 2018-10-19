<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.jqprint-0.3.js"></script>
<html>
<head>
</head>
<body>
<script language="javascript">CheckLodop();</script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#hidden1").hide();
	}); 
	
	function print(){
		$("#_baseInfo").jqprint();
	}
	
	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="form-horizontal">
			<form method="post" id="i-form" class="form-horizontal" role="form">
			<input type="hidden" id="app_id" name="app_id" value="${info.app_id}"/>
			<input type="hidden" id="credit_code" name="credit_code" value="${info.credit_code}"/>
			</form>
			<fieldset class="content">
			<div class="form-group paddtop10" align="right">
				<button type="button" class="btn btn-primary" onclick="print()">
					<i class="icon-print"></i>打印
				</button>
			</div>	
			<div class=" col-md-12" id="_baseInfo">
				<blockquote>
				<div class="col-md-12"  align="center">
					<span style="color: #72afd2"><br><h3><strong>物业服务企业信用信息记分告知单</strong></h3></span>
					<hr>
				</div>
				<div class="form-group">
					  <p class="text-left"><u title="物业公司">${info.wygsmc}</u>：</p>
					  <p class="text-left">
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	经查，你公司有<u >${info.slnr}</u>情形。根据《XX市物业服务企业和项目经理信用信息管理暂行办法》的有关规定和
					  	《XX市物业服务企业和项目经理警示信息试行记分标准》的有关规定，责令你公司在收到本告知单之日(<u >&nbsp;${info.gzrq}&nbsp;</u>)起的
					  	<u >&nbsp;${info.zgqx}&nbsp;</u>天内予以整改，同时对你公司给予记<u >&nbsp;${info.jlf}&nbsp;</u>分的处理。
					  </p>	
					  <p class="text-left">
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	若你公司对上述处理和记分有异议，可在收到本告知单之日起的<u>&nbsp;${info.ssqx}&nbsp;</u>日内向区房屋行政管理部门申请核查。
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
					  <small>本告知单一式三联。<cite title="Source Title"> 第一联为企业信用信息记载依据，由开具单位留存；第二联交物业服务企业留存；第三联交企业注册地区房屋主管部门留存。</cite></small>
				</div> 
				</blockquote>
			</div>
			</fieldset>
		</div>
	</div>
</body>
</html>