<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<html>
<head>
<script type="text/javascript">

	function allSelect(){
		var val = $('#allSelect').attr("checked");
		if (val == "checked"){
			$("input[type='checkbox']").attr("checked", true);
		}else{
			$("input[type='checkbox']").attr("checked", false);
		}
		//alert(val);
	}
	
	function save() {
		/*
		var arr = [];
		var i = 0;
		$("tr td :checkbox:checked").each(function() {
			if (this.checked) {
				var obj = new Object();
				obj.infoId = $(this).siblings("#_tab_info_id").val();
				obj.infoArea = $(this).siblings("#infoArea").val();
				arr[i] = obj;
				i++;
			}
		});
		var len = $("tr td :checkbox:checked").length; // 记录条数
		if ( len < 1) {
			layer.alert("请至少选择一条记录进行关联！",{icon:0});
			return;
		}*/
		//alert(arr[0]);
		//alert(JSON.stringify(arr));
		var obj = selectRow("_tab_info_id");
		var infoId = $("#infoId").val()+","+obj.val;
		if (infoId == ""){
			layer.alert("请至少选择一条记录进行关联！",{icon:0});
		}
		console.log(infoId);
		//jsonStr = '{"infoId":"'+infoId+'"}';
		var jsonStr = new Object();
		jsonStr.infoId = infoId;
		$.indi.ajaxSubmit({url: "${ctx}/apply/unlinkTopicAndHouseInfo",success:function(data){
			if(data.status == true){
				layer.alert('解除关联房屋信息成功！',{icon: 1}, function(index){
					window.parent.qryList();//调用父页面查询方法
					$.indi.closePopup();//关闭页面
				});
			}
			else{
				layer.alert('解除关联房屋信息失败！',{icon: 2});
			}
			}, data: JSON.stringify(jsonStr)});
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
			url : "${ctx }/house/getLinkHouseInfoAndSelect"
			//data: '{"sectName":"' + sectName + '","currPage":"' + ipage + '"}'
		});
	}
	
	//查询小区信息
	function qrySectList(){
		if ($("#sectName option:selected").val() == ""){
	    	console.log(":"+ $("#sectName option:selected").val()+":");
	    	$.indi.ajaxSubmit({url:'${ctx }/sect/getallsectinfo',success:function(data){
				if(data.status == true){
					var list = data.listInfo;
					$("#sectName").empty();
					$("#sectName").append('<option value="">请选择</option>');
					for(var i=0; i<list.length; i++){
						$("#sectName").append('<option value="' + list[i].sectName +'">'+ list[i].sectName +'</option>');
					}
				}else{
					layer.alert('小区信息异常，请重试！',{icon: 2});
				}
			}});
		}
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<input type="hidden" id="infoId" name="infoId" value="" >
				<input type="hidden" id="i-pages" name="currPage" value="1" />
				<input type="hidden" id="topicId" name="topicId" value="${topic.topicId}" >
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
						<input class="form-control col-xs-11" type="text" id="voteStartDate" readonly="readonly"
							name="voteStartDate" placeholder="<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %>" value="" >
					</div>
					<label class="col-xs-2 control-label text-right">投票结束时间</label>
					<div class="col-xs-3 ">
							<input class="form-control col-xs-11" type="text" id="voteEndDate" readonly="readonly"
							name="voteEndDate" placeholder="<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %>" value="" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区名</label>
					<div class=" col-xs-3">
							<!--  <input type="text" class="form-control col-xs-11" 
							placeholder="小区名" id="sectName" name="sectName" value=""/>-->
							<select id="sectName" class="col-md-2 form-control" name="sectName" onclick="qrySectList()">
								<option value="" selected="selected" >请选择</option>
							</select>
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
				
					<div class="col-xs-12">
						<button type="button" class="btn btn-primary " onclick="qryList()">
							<i class="icon-search"></i> 查询
						</button>
						<button type="button" class="btn btn-primary " onclick="save()">
							<i class="icon-plus-sign"></i> 解除关联
						</button>
					</div>
				
			</form>
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