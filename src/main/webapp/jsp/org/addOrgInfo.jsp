<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$("#hidden5").hide();
});
function show1(){
	var orglevel=$("#orgType").val();
	var parOrgName=$("#parOrgName").val();
	var parOrgNo=$("#parOrgNo").val();
	if(orglevel=="01"){
		$("#parentOrgNo").val("");
		$("#parentOrgName").val("");
		$("#jdmc").val("");
		$("#hidden5").hide();
	}else if(orglevel=="02"){
		$("#parentOrgNo").val("");
		$("#parentOrgName").val("");
		$("#jdmc").val("");
		$("#hidden5").show();
		$("#hidden1").show();
		$("#hidden2").hide();
		$("#hidden3").hide();
		$("#hidden4").show();
	}else if(orglevel=="03"){
		$("#parentOrgNo").val("");
		$("#parentOrgName").val("");
		$("#jdmc").val("");
		$("#hidden5").show();
		$("#hidden1").hide();
		$("#hidden2").show();
		$("#hidden3").hide();
		$("#hidden4").show();
	}else{
		$("#parentOrgNo").val(parOrgNo);
		$("#parentOrgName").val(parOrgName);
		$("#jdmc").val(parOrgName);
		$("#hidden5").show();
		$("#hidden1").hide();
		$("#hidden2").hide();
		$("#hidden3").show();
		$("#hidden4").hide();
	}
	
}
function openAdd() {
	var type ="add";
	$.indi.openPopup({title: '    ',area : ['520px' , '500px'],isDate:false,url: '${ctx }/org/queryAddPara?type='+type});
}
function save() {
	$('#i-form').validate({
		rules:{
			orgName:{
				required: true
			},parentOrgName:{
				required: true
			},parentOrgNo:{
				required: true
			},remark:{
				required: true
			}
		}
	});
	
	
	if(($.trim($('#parentOrgNo').val()).length == 0)){
   	 
    	
   	 
   	 alert('请选择上级机构');
  	  	 return false;
    }
	
		var streetId = $("#jdid").val();
		var hpbId = $("#hpbid").val();
		var url = "${ctx}/org/addSave?jdid="+streetId+"&&hpbid="+hpbId;
   $.indi.ajaxSubmit({url: url,closeMode:true,success:function(data){
	   if(data.status == true){
			layer.alert('机构新增成功！',{icon: 1}, function(index){
				$.indi.closePopup();
				 window.parent.qryList_01();
			});  
		}else{
			layer.alert('机构新增失败！',{icon: 2});
		}
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
		<input type="hidden" id="parOrgName" value="${parOrgName}"/>
		<input type="hidden" id="parOrgNo" value="${parOrgNo }"/>
		<input type="hidden" id="jdid" name="jdid"/>
		<input type="hidden" id="hpbid" name="hpbid"/>
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">机构名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="机构名称" id="orgName" name="orgName" />
					</div>
				</div>
				
				
			<div class="form-group">
					<label class=" col-xs-4 control-label text-right">机构类型</label>
					<div class=" col-xs-6">
						<select class="col-md-8 form-control" name="orgType" id="orgType"  >
							${codeData.ewTypeHtml }
							</select>
					</div>
				</div>
		    
		    <div class="form-group" >
					<label class=" col-xs-4 control-label text-right" >上级机构</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 " readonly="readonly"
							placeholder="上级机构" id="parentOrgName" name="parentOrgName" />
							   <input type="hidden" name="parentOrgNo" id = "parentOrgNo"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openAdd()">
		     	<i class="icon-plus-sign"></i> 选择</button>
				</div>
		    
				
<!-- 				<div class="form-group" id="hidden5"> -->
<!-- 					<label class=" col-xs-4 control-label text-right" id="hidden1">所属区（市/县）</label> -->
<!-- 					<label class=" col-xs-4 control-label text-right" id="hidden2">所属街道</label> -->
<!-- 					<label class=" col-xs-4 control-label text-right" id="hidden3">上级机构名称</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11 " id="jdmc" disabled="disabled"/> -->
<!-- 					</div> -->
<!-- 					<span id="hidden4"> -->
<!-- 					<button type="button" class="btn btn-primary" onclick="openAdd()"> -->
<!-- 						<i class="icon-plus-sign"></i> 选择</button></span> -->
<!-- 					<input type="hidden" class="form-control col-xs-11 " -->
<!-- 							placeholder="上级机构名称" id="parentOrgNo" name="parentOrgNo"/> -->
<!-- 				<input type="hidden" id="parentOrgName" name="parentOrgName"/> -->
<!-- 				</div> -->


<!-- 				<div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >联系人</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11 " -->
<!-- 							placeholder="联系人" id="linkMan" name="linkMan" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >地址</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11 " -->
<!-- 							placeholder="地址" id="addres" name="addres" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >邮箱地址</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11 " -->
<!-- 							placeholder="邮箱地址" id="email" name="email" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >邮政编码</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11 " -->
<!-- 							placeholder="邮政编码" id="postCode" name="postCode" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
<!-- 				<div class="form-group" > -->
<!-- 					<label class=" col-xs-4 control-label text-right" >电话号码</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11 " -->
<!-- 							placeholder="电话号码" id="telNo" name="telNo" maxlength="18" minlength="6" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="form-group" >
					<label class=" col-xs-4 control-label text-right" >备注</label>
					<div class=" col-xs-6">
						<textarea class="form-control col-xs-11 "
							placeholder="备注" id="remark" name="remark" ></textarea>
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