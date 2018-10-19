<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		window.parent.setTitleWh('700','420','选择下一步');
		$("#_nodeList").change(nodeChange); //节点改变事件
		//指定默认选择第一项，当只有一个节点或用户时，用户无需选择---@zhengwei
		//$("#_nodeList option:first").prop("selected", 'selected');
	});

	//当前节点改变
	function nodeChange(){
		var nodeId = $('#_nodeList').val();
		if(nodeId == 'FINISH' || nodeId == 'FINISH' || nodeId.toLocaleUpperCase().indexOf('FINISH') >= 0){
			$('#_userList').attr('disabled','disabled');
			return;
		}
		$.indi.ajaxSubmit({
			url : "${ctx }/activiti/queryUserByNodeId.do",
			success : function(data) {
				var usrList = data.usrList;
				var usrHtml='';
				for (var i = 0; i < usrList.length; i++) {
					usrHtml += '<option value="'+usrList[i].usrId+'">'+usrList[i].usrName+'</option>';
				}
				$('#_userList').html(usrHtml);
			}
		});
	}

	//提交
	function commit(){
		if($('#_nodeList').val() == ""){
			layer.alert("请选择您要提交的节点！", {
				icon : 0
			});
			return;
		}
		if($('#_nodeList').val() != "" && $('#_nodeList').val() != 'FINISH'){
			if(isEmpty($('#_userList').val())){
				layer.alert("请选择您要提交的用户！", {
					icon : 0
				});
				return;
			}	
		}
		$.indi.ajaxSubmit({
			url : "${ctx }/activiti/commitProcess.do",
			success : function(data) {
				parent.layer.msg("流程提交成功！", {
					icon : 6
				});
				$.indi.closePopup();
				if(typeof(window.parent.qryList_01) != "undefined"){
					window.parent.qryList_01();
				}
			}
		});
	}
	
</script>
</head>
<body class="body-modle">
	<div class=content>
		<div class="row">
			<div class="col-xs-10">
				<button type="button" class="btn btn-primary" onclick="commit()"
					id="btnSave">
					<i class="icon-save"></i> 提交
				</button>
				<button type="button" class="btn btn-primary"
					onclick="javascript:$.indi.closePopup();">
					<i class="icon-remove"></i> 取消
				</button>
			</div>
		</div>
		<!--  -->
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="col-xs-12">
					 <input type="hidden" name="appId" value="${app.appId}">
					 <input type="hidden" name="taskId" value="${app.taskId}">
					 <input type="hidden" name="nextOrgId" value="${app.nextOrgId}">
					<!-- 节点列表 -->
					<div class="col-xs-6">
						<span class="col-xs-6" style="color: #468ebc">↓↓请选择节点</span> 
						<select id="_nodeList" name="nodeId" class="col-xs-6 form-control" size="8" required>
							<c:forEach items="${listNode}" var="node" varStatus="sta">
								<option value="${node.nodeId}">${node.nodeName }</option>
							</c:forEach>
						</select>
					</div>
					<!-- 根据节点得到列表 -->
					<div class="col-xs-6">
						<span class="col-xs-6" style="color: #468ebc">↓↓请选择用户</span> 
						<select id="_userList" name="userId" multiple="multiple" class="col-xs-6 form-control" size="8" >

						</select>
					</div>
				</div>
				<div class="col-xs-12 paddtop5">
					<label class="col-xs-2  text-right">审核意见</label>
					<div class="col-xs-9">
						<textarea class="form-control" rows="5" name="memo"></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>