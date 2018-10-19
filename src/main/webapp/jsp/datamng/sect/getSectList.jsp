<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.InitSysConstants"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		//参数1：表示指定table的ID;参数2：true表示单选；false表示多选
		initTable("tableId",true);
	});

	//查询按钮
	function qryList() {
		$('#_admId').val('');//清空隐藏域
		$.indi.loadTableByQry({
			url : "${ctx }/sect/ajaxQrySectList"
		});
	}
	
	//onChange根据区县ID加载街道选项
	function createStreetOption(val){
		$("#jdid").empty();
		 $.indi.ajaxSubmit({url:'${ctx }/sect/ajaxGetJdList?hpbid='+val,isCheck:false,success:function(data){
			if(data.jdlist  == "" || data.jdlist  == undefined || data.jdlist  == null){
				layer.alert('街道信息加载失败！',{icon: 2});
			}else{
				$("#jdid").html(data.jdlist);
			}
		}});
	}

	//新增按钮
	function openAdd() {
		$.indi.openPopup({title: '小区信息新增',area : ['950px' , '600px'],url: '${ctx }/sect/addSectView'});
	}

	//查看按钮
	function openDetail(){
		var obj = selectRow("_tab_admId");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行查看！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '小区信息详情',area : ['950px','600px'],url: '${ctx }/sect/getSectView'});
	}
	
	//修改按钮
	function openEdit() {
		var obj = selectRow("_tab_admId");
		var obj1 = selectRow("sjztArray");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		if(obj1.val!='<%=InitSysConstants.DATA_ZhengChang%>'){
			layer.alert("数据正在修改或删除中，不能进行修改操作！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '小区信息修改',area : ['950px','600px'],url: '${ctx }/sect/editSectView'});
	}
	
	//删除按钮
	function deleteOper() {
		var obj = selectRow("_tab_admId");
		var obj1 = selectRow("sjztArray");
		$("#_admId").val(obj.val);
		if ( obj.size != 1) {
			layer.alert("请选择一条记录进行删除！",{icon:0});
			return;
		}
		if(obj1.val!='<%=InitSysConstants.DATA_ZhengChang%>'){
			layer.alert("数据正在修改或删除中，不能进行修改操作！",{icon:0});
			return;
		}
		$.indi.openPopup({title: '小区信息删除',area : ['950px','600px'],url: '${ctx }/sect/deleteSectView'});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				基础数据管理 <small><i class="icon-double-angle-right"></i>项目信息管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="_admId" name="xmid" />
					<input type="hidden" id="i-pages" name="pages" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="hpbmc" class="col-md-2  control-label">所属区县</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="hpbid" name="hpbid" onchange="createStreetOption(this.value)">
							${cm:createRegionHtml(hpbList,"") }
							</select>
						</div>
						<label for="jdmc" class="col-md-2  control-label">所属街道</label>
						<div class="col-md-3">
							<select  class="form-control col-xs-11 " id="jdid" name="jdid" >
							
							</select>
						</div>
					</div>
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
					</div>
					<div class="form-group">
						<label for="wygsmc" class="col-md-2  control-label">物业公司</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="wygsmc" name="wygsmc" placeholder="物业公司名称">
						</div>
						<label for="xmjlxm" class="col-md-2  control-label">项目经理</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text"
								id="xmjlxm" name="xmjlxm" placeholder="项目经理姓名">
						</div>
						<div class="col-md-2" >
							<button type="button" class="btn btn-sm btn-primary"
								onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()" power="20180301001">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="deleteOper()">
						<i class="icon-trash"></i> 删除
					</button>
					<button type="button" class="btn btn-primary" onclick="openEdit()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="openDetail()">
						<i class="icon-info-sign"></i> 查看
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
							<input type="hidden" id="sjztArray" target_data="sjzt">
							</th>
							<th width="5%" target_data="count">序号</th>
							<th width="10%" target_data="xmbm">项目编码</th>
							<th width="15%" target_data="xmmc">项目名称</th>
							<th width="8%" target_data="hpbmc">所属区县</th>
							<th width="8%" target_data="wyxz" type="code" codeNo="SECTTYPE">物业性质</th>
							<th width="8%" target_data="jzmj">建筑面积</th>
							<th width="15%" target_data="wygsmc">在管物业公司</th>
							<th width="10%" target_data="sjzt" type="code" codeNo="INFO_STATUS">数据状态</th>
							<th width="10%" target_data="cjsj">创建日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="sectInfo" varStatus="sectInfoSta">
							<tr>
								<td align="center">
									<input type="checkbox" /> 
									<input type="hidden" id="_tab_admId" target_data="xmid" value="${sectInfo.xmid }"/>
									<input type="hidden" id="sjztArray" target_data="sjzt" value="${sectInfo.sjzt }"/>
								</td>
								<td align="center" >${sectInfoSta.count }</td>
								<td align="center" >${sectInfo.xmbm }</td>
								<td align="left" >${sectInfo.xmmc}</td>
								<td align="center" >${sectInfo.hpbmc}</td>
								<td align="center" >${cm:getCodeVal('SECTTYPE',sectInfo.wyxz) }</td>
								<td align="right" >${sectInfo.jzmj}</td>
								<td align="left" >${sectInfo.wygsmc}</td>
								<td align="center" >${cm:getCodeVal('INFO_STATUS',sectInfo.sjzt) }</td>
								<td align="center" >${sectInfo.cjsj}</td>
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
						url : "${ctx }/sect/ajaxQrySectList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
