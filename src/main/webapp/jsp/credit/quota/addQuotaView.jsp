<%@page import="com.indihx.credit.commons.CreditConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#hidden1").hide();
	}); 
	
	//保存按钮
	function save() {
		$('#i-form').validate({
			rules:{
				zbbm:{
					required: true
				},
				jfyj:{
					required: true
				},
				syzt:{
					required: true
				},
				ckfz:{
					required: true
				},
				jfbz:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/credit/quota/saveQuotaInfo.do",success:function(data){
			layer.alert("指标信息保存成功!",{icon: 1},function(index){
				$.indi.closePopup();
				//window.parent.location.reload();
				window.parent.frames.qryList();
			});
		}});
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
			<input type="hidden" id="credit_seq" name="credit_seq" value=""/>
			<input type="hidden" id="status" name="status" value="${status}"/>
				<fieldset class="content">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">编码</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-10"
							placeholder="指标编码" id="zbbm" name="zbbm"   maxlength="80" value="${zbbm} "/>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">指标内容</label>
					<div class="col-xs-8">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="请填写指标依据内容..." id="jfyj" name="jfyj" rows="5" maxlength="512"></textarea>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">条</label>
					<div class="col-xs-8">
						<input type="text" class="form-control"
							placeholder="第几条,如第一条，则填写大写'一'即可..." id="tseq" name="tseq"  maxlength="80" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">款</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-10"
							placeholder="第几款,如第五款，则填写大写'五'即可..." id="kseq" name="kseq"  maxlength="80" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">适用主体</label>
					<div class=" col-xs-8">
						<label class="radio">
						  <label for="">
						   <input type="radio" id="syzt1" name="syzt" value="<%=CreditConstants.QuotaObject_WYGS %>" checked />物业公司
						  </label>
						 </label>
						 <label class="radio">
						  <label for="">
						   <input type="radio" id="syzt2" name="syzt" value="<%=CreditConstants.QuotaObject_XMJL %>"/>项目经理
						  </label>
						 </label>
						  <label class="radio">
						  <label for="">
						   <input type="radio" id="syzt3" name="syzt" value="<%=CreditConstants.QuotaObject_WYGSXMJL %>"/>物业公司和项目经理
						  </label>
						 </label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">分值</label>
					<div class=" col-xs-8">
						<input type="number" class="form-control col-xs-10"
							placeholder="参考分值" id="ckfz" name="ckfz"   maxlength="80" value=""/>
						<p class="help-block"></p>	
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">记分标志</label>
					<div class=" col-xs-8">
						<label class="radio">
						  <label for="">
						   <input type="radio" id="jfbz1" name="jfbz" value="加分"  checked/>加分
						  </label>
						 </label>
						  <label class="radio">
						  <label for="">
						   <input type="radio" id="jfbz2" name="jfbz" value="减分"/>减分
						  </label>
						 </label>	
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="指标依据描述" id="bz" name="bz" rows="5" maxlength="512"></textarea>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
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