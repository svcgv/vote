<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<link href="${ctx }/resources/admincp/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ctx }/resources/admincp/zTree/jquery.ztree.core.js"></script>
<div class="org-wrapper">
	<div class="tree-wrapper" style="height:300px;">
		<ul id="treeOrg" class="ztree"></ul>
	</div>
	<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="close_01()">
						<i class="icon-remove"></i> 取消
					</button>
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

function save() {

	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes();
	if(getCheckedOrg.length == 0){
		layui.use(['layer'], function(){
			layer.msg("请选择机构");
		})
	}else{
		zTreeOnSaveEvent();
		return false;
	}
	
	
	
};
var act="${type}";// 区分是index页 form页 赋值问题
function zTreeOnSaveEvent(event, treeId, treeNode) {
	
	var getCheckedOrg =$.fn.zTree.getZTreeObj("treeOrg").getSelectedNodes()[0];
 // 保存到已选机构中

 	layer.msg(getCheckedOrg.name);
 
      var  parentName = getCheckedOrg.name;
      var parentId =getCheckedOrg.orgId;
 		// index
 		if(act == "add"){
		 		$('#parentOrgName', window.parent.document).val(parentName);
		 		$('#parentOrgNo', window.parent.document).val(parentId);
 		}
 		if(act == "user"){
	 	 		$('#orgName', window.parent.document).val(parentName);
	 	 		$('#orgNo', window.parent.document).val(parentId);
 	 		}
 	 		
		$.indi.closePopup();
};


//关闭
function close_01(){
		 $.indi.closePopup();
	}



</script>