<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		var topicId = $("#topic_id").val();
		//var ipage = $("#i-pages").val()
		$.indi.loadTableByQry({
			url : "${ctx }/result/getVoteListInfo",
			data: '{"topicId":"' + topicId + '","currPage":"1"}'
		});
	});

	function allSelect(){
		var val = $('#allSelect').attr("checked");
		if (val == "checked"){
			$("input[type='checkbox']").attr("checked", true);
		}else{
			$("input[type='checkbox']").attr("checked", false);
		}
		//alert(val);
	}
	
	
	function close_0(){
		$.indi.closePopup();
	}
	
	function qryList(){
		//var sectName = $("#sect_name").val();
		//var ipage = $("#i-pages").val()
		//alert(sectName);
		//var obj = selectRow("_tab_info_id");
		//$("#infoId").val(obj.val);
		$.indi.loadTableByQry({
			url : "${ctx }/result/getVoteListInfo"
			//data: '{"sectName":"' + sectName + '","currPage":"' + ipage + '"}'
		});
	}
</script>
</head>
<body class="body-modle">
	<form class="form-horizontal" id="i-form" name="from-name" method="post">
		<input type="hidden" id="topic_id" name="topicId" value="${topicId}">
		<input type="hidden" id="i-pages" name="currPage" value="1" />
	</form>
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">投票主题</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"  readonly="readonly"
							placeholder="投票主题" id="topicName" name="topicName" value="${topic.topicName}"/>
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">投票开始时间</label>
					<div class="col-xs-3">
						<input class="form-control col-xs-11" type="text" id="voteStartDate" 
						 readonly="readonly" name="voteStartDate" value="${topic.voteStartDate }">
					</div>
					<label class="col-xs-2 control-label text-right">投票结束时间</label>
					<div class="col-xs-3 ">
							<input class="form-control col-xs-11" type="text" id="voteEndDate" 
							readonly="readonly" name="voteEndDate" value="${topic.voteEndDate }">
					</div>
				</div>
			</form>
			<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox">
								<input type="checkbox" id="allSelect" onchange="allSelect()">
								<input type="hidden" id="infoArea" target_data="infoArea">
								<input type="hidden" id="_tab_topic_id" target_data="topicId" value="" >
							</th>
							<th width="5%"  target_data="count">序号</th>
							<th width="20%"  target_data="detailedAddress">分户信息</th>
							<th width="10%" target_data="area">建筑面积</th>
							<th width="10%" target_data="voteDate">投票时间</th>
							<th width="10%" target_data="attachment">附件</th>
							<th width="10%" target_data="voteWays">来源</th>
							<th width="8%"  target_data="voteResult">表决结果</th>
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