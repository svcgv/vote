<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
<script type="text/javascript">
	
	function qryList(){
		$.indi.loadTableByQry({
			url : "${ctx }/apply/getApplyInfo"
		});
	}
	
	//查看汇总信息
	function qrySumList(){
		var obj = selectRow("_tab_topic_id");
		if(obj.size!=1){
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}else{
			$("#topic_id").val(obj.val);
			$.indi.openPopup({title: '投票汇总信息',area : ['850px' , '650px'],url: '${ctx }/result/getTopicSumInfo'});
		}
	}
	
	//查看投票分户信息
	function qryVoteList(){
		var obj = selectRow("_tab_topic_id");
		if(obj.size!=1){
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}else{
			$("#topic_id").val(obj.val);
			$.indi.openPopup({title: '投票分户信息',area : ['850px' , '650px'],url: '${ctx }/result/getVoteList'});
		}
	}
	
	//查看没有投票的分户信息
	function qryUnvoteList(){
		var obj = selectRow("_tab_topic_id");
		if(obj.size!=1){
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}else{
			/*
			var objs = selectRow("voteStatus");
			if (objs.val == "已注销"){
				alert("已注销议题不能表决！");
				return;
			}*/
			var obj1 = selectRow("effectiveStatus");
			if (obj1.val == "无效"){
				layer.alert("无效议题不能补录！",{icon:0});
				return;
			}
			
			$("#topic_id").val(obj.val);
			$.indi.openPopup({title: '手工补录',area : ['850px' , '650px'],url: '${ctx }/result/getUnvoteList'});
		}
	}
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				2投票表决管理 <small><i class="icon-double-angle-right"></i>维修工程信息</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<div class="col-md-12">
				<form class="form-horizontal" id="i-form" name="from-name" method="post">
					<input type="hidden" id="topic_id" name="topicId" value="">
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-2  control-label">投票主题</label>
						<div class="col-md-3">
							<input class="col-md-3 form-control" type="text" id="topicName" name="topicName" placeholder="投票主题">
						</div>
						<label for="_ruleName" class="col-md-1  control-label">投票状态</label>
						<div class="col-md-2">
							<select class="col-md-3 form-control" id="voteStatus" name="voteStatus">
								<option value="" selected="selected">请选择</option>
								<option value="01">待确定表决范围</option>
								<option value="02">已关联分户</option>
								<option value="03">表决中</option>
								<option value="04">表决完结</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="_ruleName" class="col-md-2  control-label">发布时间</label>
						<div class="col-md-3">
							<input class="form-control" type="text" id="voteStartDate" 
							name="voteStartDate" placeholder="<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %>" value="" onClick="WdatePicker()">
						</div>
						<div class="col-md-3 ">
							<input class="form-control" type="text" id="voteEndDate" 
							name="voteEndDate" placeholder="<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %>" value="" onClick="WdatePicker()">
						</div>
					</div>
				</form>
				</div>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="qryList()"><i class="icon-info-sign"></i> 查询</button>
					<button type="button" class="btn btn-primary" onclick="qrySumList()"><i class="icon-info-sign"></i> 查看汇总结果</button>
					<button type="button" class="btn btn-primary" onclick="qryVoteList()"><i class="icon-info-sign"></i> 查看投票分户信息</button>
					<button type="button" class="btn btn-primary" onclick="qryUnvoteList()"><i class="icon-info-sign"></i> 手工补录</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox">
								<i class="icon-resize-vertical"></i>
								<input type="hidden" id="_tab_topic_id" target_data="topicId" >
								<input type="hidden" id="voteStatus" target_data="voteStatus" >
								<input type="hidden" id="effectiveStatus" target_data="effectiveStatus">
							</th>
							<th width="5%"  target_data="count">序号</th>
							<th width="15%"  target_data="topicName">投票主题</th>
							<th width="10%" target_data="voteStartDate">投票开始时间</th>
							<th width="10%" target_data="voteEndDate">投票结束时间</th>
							<th width="10%" target_data="createDate">发布时间</th>
							<th width="10%" target_data="sumHs">投票分户数</th>
							<th width="8%"  target_data="voteStatus">投票状态</th>
							<th width="8%"  target_data="effectiveStatus">有效性</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				</div>
			</div>
		</div>
</body>
</html>
