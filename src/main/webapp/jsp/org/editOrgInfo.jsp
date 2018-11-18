<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	var hpbName = $("#bbb").val();
	var strName = $("#aaa").val();
	var orglevel=$("#orgType").val();
	if(orglevel=="01"){
		$("#jdmc").val("");
		$("#hidden5").hide();
	}else if(orglevel=="02"){
		$("#jdmc").val(hpbName);
		$("#hidden5").show();
		$("#hidden1").show();
		$("#hidden2").hide();
		$("#hidden3").hide();
		$("#hidden6").hide();
		$("#hidden7").show();
	}else if(orglevel=="03"){
		$("#jdmc").val(strName);
		$("#hidden5").show();
		$("#hidden1").hide();
		$("#hidden2").show();
		$("#hidden3").hide();
		$("#hidden6").hide();
		$("#hidden7").show();
	}else{
		$("#jdmc").val("");
		$("#hidden5").hide();
		$("#hidden1").hide();
		$("#hidden2").hide();
		$("#hidden3").show();
		$("#hidden7").hide();
		$("#hidden6").show();
	}
});
	function save() {
		$('#i-form').validate({
			rules:{
				orgName:{
					required: true
				},remark:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/org/editSave",closeMode:true,success:function(data){
			 if(data.status == true){
					layer.alert('机构修改成功！',{icon: 1}, function(index){
						$.indi.closePopup();
						 window.parent.qryList_01();
					});  
				}else{
					layer.alert('机构修改失败！',{icon: 2});
				}
		}});
	}
	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}
	
	function openAdd() {
		var type ="add";
		$.indi.openPopup({title: '    ',area : ['520px' , '500px'],isDate:false,url: '${ctx }/org/queryAddPara?type='+type});
	}
</script>
</head>
<body class="body-modle">
	<div  class="content" >
		<div class="row">
		<input type="hidden" id="aaa" value="${strName.jdmc}"/>
		<input type="hidden" id="bbb" value="${hpbName.hpbmc}"/>
			<form method="post" id="i-form" class="form-horizontal">
			<input type="hidden" id="orgNo" name="orgNo" value="${orginfo.orgNo }">
			<input type="hidden" id="orgNo1" name="orgNo1" >
			
			<div class="form-group" >
					<label class=" col-xs-4 control-label text-right" >上级机构</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="上级机构" id="parentOrgName" name="parentOrgName" />
							   <input type="hidden" name="parentOrgNo" id = "parentOrgNo"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openAdd()">
		     	<i class="icon-plus-sign"></i> 选择</button>
				</div>
			
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">机构名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="机构名称" id="orgName" name="orgName" value="${orginfo.orgName }"/>
					</div>
				
				</div>
					<div class="form-group">
					<label class=" col-xs-4 control-label text-right">机构类型</label>
					<div class=" col-xs-6">
						<select  class="form-control col-xs-11" id="orgType" name="orgType" disabled="disabled">
							${orginfo.ewTypeHtml }
						</select>
					</div>
				 </div>
				 <div class="form-group" id="hidden5">
					<label class=" col-xs-4 control-label text-right" id="hidden1">所属区（市/县）</label>
					<label class=" col-xs-4 control-label text-right" id="hidden2">所属街道</label>
					<label class=" col-xs-4 control-label text-right" id="hidden3">上级机构名称</label>
					<div class=" col-xs-6" id="hidden7">
						<input type="text" class="form-control col-xs-11 " id="jdmc" disabled="disabled"/>
					</div>
					<div class=" col-xs-6" id="hidden6">
						<input type="text" class="form-control col-xs-11 " value="${orginfo.parentOrgName}" disabled="disabled"/>
					</div>
<%-- 				<input type="hidden" id="parentOrgNo" value="${orginfo.parentOrgNo}" name="parentOrgNo"/> --%>
<%-- 				<input type="hidden" id="parentOrgName" value="${orginfo.parentOrgName}" name="parentOrgName"/> --%>
				</div>
<!-- 				<div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >联系人</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 					<input type="text" class="form-control col-xs-11" -->
<%-- 							placeholder="联系人" id="linkMan" name="linkMan" value="${orginfo.linkMan }"/> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				 <div class="form-group"> -->
<!-- 					<label class=" col-xs-4 control-label text-right">地址</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11" -->
<%-- 							placeholder="地址" id="addres" name="addres" value="${orginfo.addres }"/> --%>
<!-- 					</div> -->
<!-- 				 </div> -->
<!-- 				 <div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >邮箱地址</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11" -->
<%-- 							placeholder="邮箱地址" id="email" name="email" value="${orginfo.email }"/> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >邮政编码</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11" -->
<%-- 							placeholder="邮政编码" id="postCode" name="postCode" value="${orginfo.postCode }" /> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				 	 <div class="form-group"> -->
<!-- 					<label class=" col-xs-4 control-label text-right">电话号码</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11" -->
<%-- 							placeholder="电话号码" id="telNo" name="telNo" value="${orginfo.telNo }" /> --%>
<!-- 					</div> -->
<!-- 				 </div> -->
				 <div class="form-group" >
					<label class=" col-xs-4 control-label text-right" >备注</label>
					<div class=" col-xs-6">
						<textarea class="form-control col-xs-11"
							placeholder="备注" id="remark" name="remark">${orginfo.remark}</textarea>
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
			</form>
		</div>
	</div>
</body>
</html>