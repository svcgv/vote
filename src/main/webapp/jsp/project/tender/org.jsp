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
var zNodes =[
	

];

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
	if(getCheckedOrg == "undefined"){
		layui.use(['layer'], function(){
			layer.msg("请选择机构")
		})
	}else{
		queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,roleCode)
		var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
		 // 保存到已选机构中
		 	if(act == "index"){
				$("#tender-index-form input[name='sellDeptName']").val(getCheckedOrg.name);
				$("#tender-index-form input[name='sellDeptId']").val(getCheckedOrg.orgId);
		 	}else if(act == "pay"){
		 		$("#tender-index-form input[name='payDeptName']").val(getCheckedOrg.name);
				$("#tender-index-form input[name='payDeptId']").val(getCheckedOrg.orgId);
		 	}else if(act =="add"){
		 		//销售部门
		 			queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"SELL_DEPT_MANAGER")
		 		$("#tender-addForm-hook input[name='sellDeptName']").val(getCheckedOrg.name);
				$("#tender-addForm-hook input[name='sellDeptId']").val(getCheckedOrg.orgId);
		 	}else if(act == "addPay"){
		 		//选择交付部门
		 			queryUserByRoleCodeOrgNo(getCheckedOrg.orgId,"MAIN_MANAGER")
		 		$("#tender-addForm-hook input[name='constructionDeptName']").val(getCheckedOrg.name);
				$("#tender-addForm-hook input[name='constructionDeptId']").val(getCheckedOrg.orgId);
		 	}
		 	win.close();
		return false;
	}
	
	
	
});



var act="${act}";// 区分是index页 form页 赋值问题
var roleCode="${roleCode}"
console.log("roleCode:"+roleCode);
console.log("${roleCode}");

	//通过orgId和roleCode查人，并反显
function zTreeOnSaveEvent () {
	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
	//若roleCode存在，则向后台查一把，根据act返现再页面上
	

	 // 保存到已选机构中
 	if(act == "index"){
		$("#tender-index-form input[name='sellDeptName']").val(getCheckedOrg.name);
		$("#tender-index-form input[name='sellDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "pay"){
 		$("#tender-index-form input[name='payDeptName']").val(getCheckedOrg.name);
		$("#tender-index-form input[name='payDeptId']").val(getCheckedOrg.orgId);
 	}else if(act =="add"){
 		$("#tender-addForm-hook input[name='sellDeptName']").val(getCheckedOrg.name);
		$("#tender-addForm-hook input[name='sellDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "addPay"){
 		$("#tender-addForm-hook input[name='payDeptName']").val(getCheckedOrg.name);
		$("#tender-addForm-hook input[name='payDeptId']").val(getCheckedOrg.orgId);
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
			      if(res.page){
			    	  if(act =="addPay"){ // 交付部门负责人页面
					 		$("#tender-addForm-hook input[name='constructionDeptManagerName']").val(res.page[0].usrName);
							$("#tender-addForm-hook input[name='constructionDeptManagerId']").val(res.page[0].usrId);
					 	}else if(act =="add"){ //销售部门负责人 页面
					 		$("#tender-addForm-hook input[name='sellDeptManagerName']").val(res.page[0].usrName);
							$("#tender-addForm-hook input[name='sellDeptManagerId']").val(res.page[0].usrId);
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