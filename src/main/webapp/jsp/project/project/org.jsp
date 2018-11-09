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
		$("#project-index-form input[name='buildDeptName']").val(getCheckedOrg.name);
		$("#project-index-form input[name='buildDeptId']").val(getCheckedOrg.orgId);
 	}else if(act == "sellDept"){
 		$("#project-index-form input[name='sellDeptName']").val(getCheckedOrg.name);
		$("#project-index-form input[name='sellDeptId']").val(getCheckedOrg.orgId);
 	}
		win.close();
};


//关闭
$(".org-wrapper #org-close-hook").click(function(){
	win.close();
	return false;
})



</script>