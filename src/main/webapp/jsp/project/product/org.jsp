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
/*
 * 异步加载获取数据
 */
 /*
var setting = {
		async: {
			enable: true,
			url:"getOrg",
			autoParam:["id", "name=n", "level=lv"],
			otherParam:{"otherParam":"zTreeAsyncTest"},
			dataFilter: filter
		}
	};
function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}
$(document).ready(function(){
	$.fn.zTree.init($("#treeOrg"), setting);
});
*/
var setting={
	// 增加双击选中事件
	callback:{
		onDblClick: zTreeOnSaveEvent
	}
};
var zNodes =[];

$(document).ready(function(){
	$.ajax({
		  type: 'POST',
		  url: '/vote/queryorginfo/getOrgTree',
		  data: JSON.stringify({}),
		  contentType:'application/json',
		  success: function(res){
		      console.log(res)
		      zNodes=[res.Tree]
		      if(zNodes.length >0 ){
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
	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
	console.log("getCheckedOrg"+getCheckedOrg);
	console.log(getCheckedOrg == "undefined");
	if(getCheckedOrg == "undefined"){
		layui.use(['layer'], function(){
			layer.msg("请选择机构")
		})
	}else{	
		var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
		 // 保存到已选机构中
		 	if(act == "index"){
//		 		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"MAIN_MANAGER")
				$("#product-index-form input[name='developmentDeptName']").val(getCheckedOrg.name);
				$("#product-index-form input[name='developmentDeptId']").val(getCheckedOrg.orgId);
		 		
		 	}else if(act =="add"){
//		 		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"MAIN_MANAGER")
		 		$("#product-addForm-hook input[name='developmentDeptName']").val(getCheckedOrg.name);
				$("#product-addForm-hook input[name='developmentDeptId']").val(getCheckedOrg.orgId);
		 	}
		 	win.close();
		return false;
	}
});
var act="${act}";// 区分是index页 form页 赋值问题
function zTreeOnSaveEvent(event, treeId, treeNode) {
	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
 // 保存到已选机构中
 	if(act == "index"){
 		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"MAIN_MANAGER")
		$("#product-index-form input[name='developmentDeptName']").val(getCheckedOrg.name);
		$("#product-index-form input[name='developmentDeptId']").val(getCheckedOrg.orgId);
 		
 	}else if(act =="add"){
 		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"MAIN_MANAGER")
 		$("#product-addForm-hook input[name='developmentDeptName']").val(getCheckedOrg.name);
		$("#product-addForm-hook input[name='developmentDeptId']").val(getCheckedOrg.orgId);
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
			      if(res.page.length){
			    	  if(act =="index"){ // 交付部门负责人页面
					 		$("#product-index-form input[name='developmentManagerName']").val(res.page[0].usrName);
							$("#product-index-form input[name='developmentManagerId']").val(res.page[0].usrId);
					 	}else if(act =="add"){ //销售部门负责人 页面
					 		$("#product-addForm-hook input[name='developmentManagerName']").val(res.page[0].usrName);
							$("#product-addForm-hook input[name='developmentManagerId']").val(res.page[0].usrId);
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