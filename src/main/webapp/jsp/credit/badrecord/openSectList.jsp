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
			url : "${ctx }/sect/ajaxSectList"
		});
	}
	
	//选择
	function confirmRow() {
		var obj1 = selectRow("_tab_admId");
		var obj2 = selectRow("_tab_admmc");
		var obj3 = selectRow("_tab_adgsid");
		var obj4 = selectRow("_tab_adgsmc");
		var obj5 = selectRow("_tab_adryid");
		var obj6 = selectRow("_tab_adrymc");
		if ( obj1.size != 1) {
			layer.alert("请选择一条记录进行确认！",{icon:0});
			return;
		}

		window.parent.$("#xmid").val();//置空
		window.parent.$("#xmid").val(obj1.val);//赋新值
		window.parent.$("#xmmc").val();
		window.parent.$("#xmmc").val(obj2.val);
		window.parent.$("#wygsid").val();
		window.parent.$("#wygsid").val(obj3.val);
		window.parent.$("#wygsmc").val();
		window.parent.$("#wygsmc").val(obj4.val);
		window.parent.$("#gsryid").val();
		window.parent.$("#gsryid").val(obj5.val);
		window.parent.$("#ryxm").val();
		window.parent.$("#ryxm").val(obj6.val);
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
					<div class="form-group col-md-15">
						<label for="glcmc" class="col-md-2 control-label">项目名称</label>
						<div class="col-md-3">
							<input class="col-md-11 form-control" type="text" id="xmmc"
								name="xmmc" placeholder="项目名称">
						</div>
						<label for="glcmc" class="col-md-2 control-label">项目地址</label>
						<div class="col-md-3">
							<input class="col-md-11 form-control" type="text" id="xmdz"
								name="xmdz" placeholder="项目名称">
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
				<table class="table table-bordered table-striped table-hover with-check table-paging" id="tableId">
					<thead>
						<tr>
							<th width="5%" target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="_tab_admId" target_data="xmid">
							<input type="hidden" id="_tab_admmc" target_data="xmmc">
							<input type="hidden" id="_tab_adgsid" target_data="wygsid">
							<input type="hidden" id="_tab_adgsmc" target_data="wygsmc">
							<input type="hidden" id="_tab_adryid" target_data="gsryid">
							<input type="hidden" id="_tab_adrymc" target_data="xmjlxm">
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="10%" target_data="xmbm">项目编码</th>
							<th width="15%" target_data="xmmc">项目名称</th>
							<th width="8%" target_data="hpbmc">所属区县</th>
							<th width="8%" target_data="wylx1" type="code" codeNo="SECTTYPE">物业类型</th>
							<th width="8%" target_data="jzmj">建筑面积</th>
							<th width="15%" target_data="wygsmc">在管物业公司</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="sectInfo" varStatus="sectInfoSta">
							<tr>
								<td align="center">
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_admId" target_data="xmid" value="${sectInfo.xmid }"/>
									<input type="hidden" id="_tab_admmc" target_data="xmmc" value="${sectInfo.xmmc }"/>
									<input type="hidden" id="_tab_adgsid" target_data="wygsid" value="${sectInfo.wygsid }"/>
									<input type="hidden" id="_tab_adgsmc" target_data="wygsmc" value="${sectInfo.wygsmc }"/>
									<input type="hidden" id="_tab_adryid" target_data="gsryid" value="${sectInfo.gsryid }"/>
									<input type="hidden" id="_tab_adrymc" target_data="xmjlxm" value="${sectInfo.xmjlxm }"/>
								</td>
								<td align="center" >${sectInfoSta.count }</td>
								<td align="center" >${sectInfo.xmbm }</td>
								<td align="left" >${sectInfo.xmmc}</td>
								<td align="center" >${sectInfo.hpbmc}</td>
								<td align="center" >${cm:getCodeVal('SECTTYPE',sectInfo.wylx1) }</td>
								<td align="right" >${sectInfo.jzmj}</td>
								<td align="left" >${sectInfo.wygsmc}</td>
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
						url : "${ctx }/sect/ajaxcspSectList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>