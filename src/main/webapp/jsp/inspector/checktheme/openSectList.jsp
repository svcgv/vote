<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	//查询
	function qryList() {
		$.indi.loadTableByQry({
			url : "${ctx }/checktheme/ajaxSectList"
		});
	}
	
	//选择
	function confirmRow() {
		var obj = selectRow("infoIdArray");
		var obj1 = selectRow("infoNameArray");
		if ( obj.size <1) {
			layer.alert("请选择一条记录进行确认！",{icon:0});
			return;
		}
		window.parent.$("#sect_id_arr").val(obj.val);
		window.parent.$("#sect_name_arr").val(obj1.val);
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
					<div class="form-group">
						<label for="xmmc" class="col-md-2  control-label">项目名称</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="xmmc" name="xmmc" placeholder="项目名称">
						</div>
						<label for="xmdz" class="col-md-2  control-label">项目地址</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="xmdz" name="xmdz" placeholder="项目地址">
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
				<table
					class="table table-bordered table-striped table-hover with-check table-paging">
					<thead>
						<tr>
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="infoIdArray" target_data="xmid" value="${sectInfo.xmid }">
							<input type="hidden" id="infoNameArray" target_data="xmmc" value="${sectInfo.xmmc }">
							</th>
							<th width="8%" target_data="count">序号</th>
							<th width="10%" target_data="xmbm">项目编码</th>
							<th width="25%" target_data="xmmc">项目名称</th>
							<th width="8%" target_data="hpbmc">所属区县</th>
							<th width="8%" target_data="wylx1" type="code" codeNo="SECTTYPE">物业类型</th>
							<th width="8%" target_data="jzmj">建筑面积</th>
							<th width="15%" target_data="wygsmc">在管物业公司</th>
							<!-- 
							<th width="10%" target_data="frdb">法人代表</th>
							 -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="sectInfo" varStatus="sectInfoSta">
							<tr>
								<td align="center">
									<input type="checkbox" />
									<input type="hidden" id="infoIdArray" target_data="xmid" value="${sectInfo.xmid }">
									<input type="hidden" id="infoNameArray" target_data="xmmc" value="${sectInfo.xmmc }">
								</td>
								<td class="center" >${sectInfoSta.count }</td>
								<td class="center" >${sectInfo.xmbm }</td>
								<td class="center" >${sectInfo.xmmc}</td>
								<td class="center" >${sectInfo.hpbmc}</td>
								<td class="center" >${cm:getCodeVal('SECTTYPE',sectInfo.wylx1) }</td>
								<td class="center" >${sectInfo.jzmj}</td>
								<td class="center" >${sectInfo.wygsmc}</td>
								 
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
						url : "${ctx }/checktheme/ajaxSectList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>