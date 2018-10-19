<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		//参数1：表示指定table的ID;参数2：true表示单选；false表示多选
		initTable("tableId",true);
	});
	
	//查询
	function qryList() {
		$.indi.loadTableByQry({
			url : "${ctx }/sect/ajaxXmjlList"
		});
	}
	
	//选择
	function confirmRow() {
		var obj = selectRow("xmjlIdArray");
		var obj1 = selectRow("xmjlNameArray");
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行确认！",{icon:0});
			return;
		}
		window.parent.$("#gsryid").val(obj.val);
		window.parent.$("#xmjlxm").val(obj1.val);
		$.indi.closePopup();
  	}
	
	//取消
	function close_01() {
		 $.indi.closePopup();
	}
</script>
</head>
<body>
	<div class="right-side">
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name" method="post">
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<input type="hidden" id="wygsid" name="wygsid" value="${wygsid}" />
					<div class="form-group">
						<label for="glcmc" class="col-md-2 control-label">项目经理姓名</label>
						<div class="col-md-3">
							<input class="col-md-11 form-control" type="text" id="ryxm"
								name="ryxm" placeholder="项目经理姓名">
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary "
								onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="confirmRow()">
				    	<i class="icon-plus-sign"></i> 选择
			        </button>
			        <button type="button" class="btn btn-primary" onclick="close_01()">
				    	<i class="icon-remove"></i> 关闭
			        </button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table class="table table-bordered table-striped table-hover with-check table-paging" id ="tableId">
					<thead>
						<tr>
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="xmjlIdArray" target_data="gsryid" value="">
							<input type="hidden" id="xmjlNameArray" target_data="ryxm" value="">
							</th>
							<th width="8%" target_data="count">序号</th>
							<th width=""  target_data="ryxm">项目经理姓名</th>
							<th width="35%"  target_data="lxdh">联系电话</th>
							<!-- 
							<th width="15%" target_data="glclxr">管理处联系人</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="xmjlInfo" varStatus="xmjlInfoSta">
							<tr>
								<td align="center">
									<input type="checkbox" />
									<input type="hidden" id="xmjlIdArray" target_data="gsryid" value="${xmjlInfo.gsryid }">
									<input type="hidden" id="xmjlNameArray" target_data="ryxm" value="${xmjlInfo.ryxm }">
								</td>
								<td align="center" >${xmjlInfoSta.count }</td>
								<td >${xmjlInfo.ryxm }</td>
								<td >${xmjlInfo.lxdh }</td>
								<!-- 
								<td >${csInfo.glclxr}</td>
								 -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="col-md-12" id="pageId"></div>
				<script type="text/javascript">
					var pages = {
						total : "${pageInfo.total}",
						pageNum : "${pageInfo.pageNum}",
						pageSize : "${pageInfo.pageSize}",
						pages : "${pageInfo.pages}",
						url : "${ctx }/sect/ajaxXmjlList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>