<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		//$('#_file').change(handFile);
		qryList();
	});
	

	
	function qryList(){
		//var sectName = $("#sect_name").val();
		var sectName = "${sectName}";
		var ipage = $("#i-pages").val();
		//alert(sectName);
		$.indi.loadTableByQry({
			url : "${ctx }/house/getHouseInfo",
			data: '{"sectName":"' + sectName + '","currPage":"' + ipage + '"}'
		});
	}
	
	function save() {
		/*$('#i-form').validate({
			rules:{
				wsName:{
					required: true
				},totalAmt:{
					required: true
				},repairContent:{
					required: true
				},repairReason:{
					required: true
				}
			}
		});*/
		
		$.indi.ajaxSubmit({url: "${ctx}/apply/addapplyProjectInfo",success:function(data){
			if(data.status == true){
				layer.alert('维修工程发布成功！',{icon: 1}, function(index){
					window.parent.qryList();//调用父页面查询方法
					$.indi.closePopup();//关闭页面
				});
			}
			else{
				layer.alert('维修工程发布失败！',{icon: 2});
			}
			}});
		
	}
	function close_0(){
		$.indi.closePopup();
	}
	
</script>
</head>
<body class="body-modle">
	<div class="content">
			<div class="row">
				<div class="col-md-5">
				<form class="form-horizontal" id="i-form" name="from-name" method="post">
					<input type="hidden" id="info_id" name="infoId" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<label  class="col-xs-2 control-label text-left"><strong>${sectName}</strong></label>
				
					<div class="col-md-12">
					<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead class="bg-primary">
						<tr>
							<th width="5%" target_data="checkbox">
								<i class="icon-resize-vertical"></i>
								<input type="hidden" id="_tab_info_id" target_data="infoId" >
							</th>
							<th width="5%"  target_data="count">序号</th>
							<th width="15%"  target_data="sectName">项目</th>
							<th width="10%" target_data="buildCode">楼幢</th>
							<th width="10%" target_data="unitCode">单元</th>
							<th width="10%" target_data="floorCode">楼层</th>
							<th width="10%" target_data="roomCode">户号</th>
							<th width="8%"  target_data="infoArea">分户面积</th>
							<th width="10%" target_data="ownerName">业主姓名</th>
							<th width="20%" target_data="certCode">身份证号</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					</table>
					</div>
					<div class="col-md-12" id="pageId"></div>
					<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 提交
					</button>
					<button type="button" class="btn btn-primary" onclick="close_0()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
				</form>
				</div>
			</div>
		</div>
	</body>
</html>
