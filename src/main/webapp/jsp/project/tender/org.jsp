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
	{ name:"第一事业部",orgId:"100", open:true,
		children: [
			{ name:"销售一部",orgId:"1",
				children: [
					{ orgId:"11",name:"上海分部"},
					{ orgId:"12",name:"北京分部"},
					{ orgId:"13",name:"深圳分部"},
					{ orgId:"14",name:"南京分部"}
				]},
			{ name:"销售二部",orgId:"2",isParent:false},
			{ name:"销售三部",orgId:"3",isParent:false}
		]},
	{ name:"第二事业部",orgId:"3",
		children: [
			{ orgId:"31",name:"销售六部", open:true,isParent:true},
			{ orgId:"32",name:"销售二部",isParent:true},
			{ orgId:"33",name:"销售五部",isParent:true}
		]},
	{ name:"第三事业部", orgId:"4",isParent:true}

];

$(document).ready(function(){
	$.fn.zTree.init($("#treeOrg"), setting, zNodes);
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
		
		return false;
	}
	
	
	
});
var act="${act}";// 区分是index页 form页 赋值问题
function zTreeOnSaveEvent(event, treeId, treeNode) {
	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
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


//关闭
$(".org-wrapper #org-close-hook").click(function(){
	win.close();
	return false;
})



</script>