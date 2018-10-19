<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		var topicId = $("#topic_id").val();
		//var ipage = $("#i-pages").val()
		$.indi.loadTableByQry({
			url : "${ctx }/result/getUnvoteListInfo",
			data: '{"topicId":"' + topicId + '","currPage":"1"}'
		});
	});
	
	function add() {
		var obj = selectRow("_tab_info_id");
		if(obj.size!=1){
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		console.log(obj.val);
		$("#infoId").val(obj.val);
		$.indi.openPopup({title: '投票补录',area : ['850px' , '650px'],url: '${ctx }/result/checkin'});
	}
	
	
	function close_0(){
		$.indi.closePopup();
	}
	
	function qryList(){
		//var topicId = $("#topic_id").val();
		$.indi.loadTableByQry({
			url : "${ctx }/result/getUnvoteListInfo"
			//data: '{"topicId":"' + topicId + '","currPage":"1"}'
		});
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" id="topic_id" name="topicId" value="${topicId}">
				<input type="hidden" id="infoId" name="infoId" value="" >
				<input type="hidden" id="i-pages" name="currPage" value="1" />
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" 
							placeholder="小区名" id="sectName" name="sectName" value=""/>
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">楼栋号</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="buildCode" name="buildCode" 
							placeholder="楼栋号" value=""/>
					</div>
					<label class="col-xs-2 control-label text-right">单元号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							id="unitCode" name="unitCode" placeholder="楼栋号" value=""/>
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">楼层号</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11" id="floorCode" name="floorCode"
							placeholder="楼层号" value=""/>
					</div>
					<label class="col-xs-2 control-label text-right">房屋号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="房屋号" id="roomCode" name="roomCode"  value="" />
					</div> 
				</div>
			</form>	
				<div class="col-xs-12">
					<button type="button" class="btn btn-primary " onclick="qryList()">
						<i class="icon-search"></i> 查询
					</button>
					<button type="button" class="btn btn-primary " onclick="add()">
						<i class="icon-plus-sign"></i>投票补录
					</button>
					<!--  
					<form method="post" enctype="multipart/form-data" id="ui-form" >
						<input type="hidden" id="topic_id" name="topicId" value="${topicId}">
						<input type="hidden" id="infoId" name="infoId" value="" >
						<input type="file" id="_file" name="file"> 
						<input type="hidden" name="fileTypeId" style="border: 1px solid #ccc;height: 34px;" id="_fileTypeId" value="">
					</form>-->
				</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox">
								<input type="checkbox" id="allSelect" onchange="allSelect()">
								<input type="hidden" id="infoArea" target_data="infoArea">
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
		</div>
	</div>
</body>
</html>