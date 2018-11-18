<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${ctx }/vote/resources/admincp/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ctx }/vote/resources/admincp/zTree/jquery.ztree.core.js"></script>
<div class="org-wrapper">
	<div class="tree-wrapper" style="height:300px;">
		<ul id="treeOrg" class="ztree"></ul>
	</div>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="org-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="org-close-hook">关闭</a>
	 </div>
</div>
<script>
var setting={
	// 增加双击选中事件
	callback:{
		onDblClick: zTreeOnSaveEvent
	}
};
$(document).ready(function(){
	$.ajax({
		  type: 'POST',
		  url: '/vote/queryorginfo/getOrgTree',
		  data: JSON.stringify({}),
		  contentType:'application/json',
		  success: function(res){
			  console.log(res)
		      zNodes=[res.Tree];
		      if(zNodes.length){
		    	  zNodes[0].open=true;
		      }
		      $.fn.zTree.init($("#treeOrg"), setting, zNodes);
	      },
		  dataType: "json"
		})
});
//保存
var win=$(".org-wrapper").getWindow();
$(".org-wrapper #org-add-hook").click(function(){
	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes();
	if(getCheckedOrg.length == 0){
		layui.use(['layer'], function(){
			layer.msg("请选择机构");
		})
	}else{
		zTreeOnSaveEvent();
		return false;
	}
	
	
	
});
var act="${act}";// 区分是index页 form页 赋值问题

function zTreeOnSaveEvent(event, treeId, treeNode) {
	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
 // 保存到已选机构中
 	if(act == "buildDept"){
 		//实施部门
		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"BUILD_DEPT_NAME")
 		// index
		$("#projectApprove-index-form  input[name='buildDeptName']").val(getCheckedOrg.name);
		$("#projectApprove-index-form  input[name='buildDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "sellDept"){
 		
 		//销售部门
		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"SELL_DEPT_MANAGER")
 		// index
 		$("#projectApprove-index-form input[name='sellDeptName']").val(getCheckedOrg.name);
		$("#projectApprove-index-form input[name='sellDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "buildDeptForm"){
 		
 		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"BUILD_DEPT_NAME")
 		queryProfitInfo(getCheckedOrg.orgId)
 		// form
 		$("#project-form-hook  input[name='buildDeptName']").val(getCheckedOrg.name);
		$("#project-form-hook  input[name='buildDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "sellDeptForm"){
 		
 		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"SELL_DEPT_MANAGER")
 		// form
 		$("#project-form-hook  input[name='sellDeptName']").val(getCheckedOrg.name);
		$("#project-form-hook  input[name='sellDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "buildDeptEdit"){
 	// edit
 			queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"BUILD_DEPT_NAME")
 			queryProfitInfo(getCheckedOrg.orgId)
 		$("#project-edit-hook  input[name='buildDeptName']").val(getCheckedOrg.name);
		$("#project-edit-hook  input[name='buildDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "sellDeptEdit"){
 		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"SELL_DEPT_MANAGER")
 		$("#project-edit-hook  input[name='sellDeptName']").val(getCheckedOrg.name);
		$("#project-edit-hook  input[name='sellDeptId']").val(getCheckedOrg.orgId);
 	}
 
 	
		win.close();
};

function queryProfitInfo(orgId){
	
	var param = {}
	param.orgId=orgId
	$.ajax({
		  type: 'POST',
		url: '/vote/pmprojectinfo/queryProfitInfo',
		 data: JSON.stringify(param),
		 contentType:'application/json',
		success:function(res){
		      if(res.profitInfo.length>0){
		    	  if(act == "buildDeptForm"){
			      	$("#project-form-hook input[name='profitCode']").val(res.profitInfo.profitId);
			      }else if(act == "buildDeptEdit"){
			      	$("#project-edit-hook  input[name='profitCode']").val(res.profitInfo.profitId);
			      }
		      }
		},
		 dataType: "json"
		
	})
}

function queryUserByRoleCodeOrgNo(orgNo,roleCode){
	console.log(orgNo,roleCode)
	//若roleCode存在，则向后台查一把，根据act返现再页面上
	if(roleCode){
		var param = {}
		param.orgNo=orgNo
		param.roleCode=roleCode
		console.log('param',param)
		$.ajax({
			  type: 'POST',
			  url: '/vote/queryusrinfo/queryUserByRoleCodeAndOrgNo',
			  data: JSON.stringify(param),
			  contentType:'application/json',
			  success: function(res){
				  console.log('asdas',act)
			      console.log(res)
			      if(res.page.length>0){
			    	  if(act =="buildDept"){ // 实施负责人
					 		$("#projectApprove-index-form input[name='buildManagerName']").val(res.page[0].usrName);
							$("#projectApprove-index-form input[name='buildManagerId']").val(res.page[0].usrId);
					  }else if(act =="sellDept"){ //销售负责人
					 		$("#projectApprove-index-form  input[name='sellManagerName']").val(res.page[0].usrName);
							$("#projectApprove-index-form  input[name='sellManagerId']").val(res.page[0].usrId);
			      	 }
		    	  }
			      else{
			    	  if(act =="buildDept"){ // 实施负责人
					 		$("#projectApprove-index-form  input[name='buildManagerName']").val('');
							$("#projectApprove-index-form  input[name='buildManagerId']").val('');
					 	}else if(act =="sellDept"){ //销售负责人
					 		$("#projectApprove-index-form  input[name='sellManagerName']").val('');
							$("#projectApprove-index-form  input[name='sellManagerId']").val('');
			      		}
			      }
			  },
			  dataType: "json"
			})
	}
}

//关闭
$(".org-wrapper #org-close-hook").click(function(){
	win.close();
	return false;
})



</script>