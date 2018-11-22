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
 	if(act == "costForm"){
 		
 		//实施部门
 		// index
		$("#profit-form-hook input[name='orgName']").val(getCheckedOrg.name);
		 console.log(getCheckedOrg.name)
		$("#profit-form-hook input[name='orgId']").val(getCheckedOrg.orgId);
		 console.log(getCheckedOrg.orgId)
 	}
 	if(act == "index"){
 		// index
		$("#profit-index-form input[name='orgName']").val(getCheckedOrg.name);
		$("#profit-index-form input[name='orgId']").val(getCheckedOrg.orgId);
 	}
 	if(act == "edit"){

 		$("#profit-edit-hook input[name='orgName']").val(getCheckedOrg.name);
		$("#profit-edit-hook input[name='orgId']").val(getCheckedOrg.orgId);
 	}
 
 	
		win.close();
};


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
					 		$("#project-index-form input[name='buildManagerName']").val(res.page[0].usrName);
							$("#project-index-form input[name='buildManagerId']").val(res.page[0].usrId);
					  }else if(act =="sellDept"){ //销售负责人
					 		$("#project-index-form  input[name='sellManagerName']").val(res.page[0].usrName);
							$("#project-index-form  input[name='sellManagerId']").val(res.page[0].usrId);
			      	 }else if(act == "buildDeptForm"){
			      		$("#project-form-hook input[name='buildManagerName']").val(res.page[0].usrName);
						$("#project-form-hook input[name='buildManagerId']").val(res.page[0].usrId);
			      	 }else if(act == "sellDeptForm"){
			      		$("#project-form-hook input[name='sellManagerName']").val(res.page[0].usrName);
						$("#project-form-hook input[name='sellManagerId']").val(res.page[0].usrId);
			      	 }else if(act == "buildDeptEdit"){
			      	 	// edit
			      		$("#project-edit-hook  input[name='buildManagerName']").val(res.page[0].usrName);
			     		$("#project-edit-hook  input[name='buildManagerId']").val(res.page[0].usrId);
			      	}else if(act == "sellDeptEdit"){
			      		$("#project-edit-hook  input[name='sellManagerName']").val(res.page[0].usrName);
			     		$("#project-edit-hook  input[name='sellManagerId']").val(res.page[0].usrId);
			      	}
		      }
			      else{
			    	  if(act =="buildDept"){ // 实施负责人
					 		$("#project-index-form  input[name='buildManagerName']").val('');
							$("#project-index-form  input[name='buildManagerId']").val('');
					 	}else if(act =="sellDept"){ //销售负责人
					 		$("#project-index-form  input[name='sellManagerName']").val('');
							$("#project-index-form  input[name='sellManagerId']").val('');
			      		}else if(act == "buildDeptForm"){
				      		$("#project-form-hook input[name='buildManagerName']").val('');
							$("#project-form-hook input[name='buildManagerId']").val('');
				      	 }else if(act == "sellDeptForm"){
				      		$("#project-form-hook input[name='sellManagerName']").val('');
							$("#project-form-hook input[name='sellManagerId']").val('');
				      	 }else if(act == "buildDeptEdit"){
					      	 	// edit
					      		$("#project-edit-hook  input[name='buildManagerName']").val('');
					     		$("#project-edit-hook  input[name='buildManagerId']").val('');
					      	}else if(act == "sellDeptEdit"){
					      		$("#project-edit-hook  input[name='sellManagerName']").val('');
					     		$("#project-edit-hook  input[name='sellManagerId']").val('');
					      	}
			      }},
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