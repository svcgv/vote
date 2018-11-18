<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	$('#_roleId').setParentVal();//给当前标签赋值，值为父页面相同name的标签值
	//初始化角色列表
});
	
	//查询
	function qryBtnList() {
		 $("#btnId").val("");
		$.indi.loadTableByQry({
			url : "${ctx }/btnRole/ajaxQryBtnInfo.do"
		});
	}

	//保存角色
	function saveBtnRole() {
		var objRow = checkedRet;
		console.log(checkedRet)
		$("#_btnId").val(objRow.val);
		$.indi.ajaxSubmit({
			url : "${ctx}/btnRole/addSave.do",
			closeMode : true,
			success : function(data) {
				layer.alert('角色按钮信息保存成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					window.parent.qryList_01();
				});  
			}
		});
	}
	
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1>
				系统管理 <small><i class="icon-double-angle-right"></i>按钮列表</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="i-pages" name="pages" value="1" />
					<!--角色主键 -->
					<input type="hidden" name="roleId" id="_roleId">
					<input type="hidden" name="btnId" id="_btnId">
					<div class="form-group">
					<label class="col-xs-2 control-label text-right">按钮名称</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-11" id="_btnName"
							name="btnName" placeholder="按钮名称" />
					</div>
					<div class="col-xs-2">
						<button type="button" class="btn btn-primary "
							onclick="qryBtnList()" ><i class="icon-search"></i> 查询</button>
					</div>
				</div>
			</form>
				</form>
			</div>
			<div class="row">
				<div class="col-md-12">
				<table
					class="table table-bordered table-striped table-hover with-check table-paging table-role-hook">
					<thead>
						<tr>
							<th target_data="checkbox"><i class="icon-resize-vertical"></i>
							<input type="hidden" id="btnId" target_data="btnId" >
							</th>
							<th target_data="count">序号</th>
							<th target_data="btnId">按钮编号</th>
							<th target_data="btnName">按钮名称</th>
							<th target_data="btnRmk">按钮说明</th>
							<th target_data="btnPage">所属页面</th>
							<th target_data="tmSmp" type="date">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="btninfo" varStatus="btnSta">
							<tr>
									<!-- 主键 -->
								<td><input type="checkbox" class="j-checkbox"
								<c:if test="${btninfo.btnId eq btninfo.roleBtnId}" >
								checked="checked"
								</c:if>
								/> 
								    <input type="hidden" id="btnIdArray" target_data="btnId" value="${btninfo.btnId }">
								</td>	
									
								<td class="center" >${btnSta.count }</td>
								<td class="center" >${btninfo.btnId }</td>
								<td class="center" >${btninfo.btnName }</td>
								<td class="center" >${btninfo.btnRmk }</td>
								<td class="center">${btninfo.btnPage}</td>
								<td class="center">${cm:formatFromDB(btninfo.tmSmp)}</td>
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
						url : "${ctx }/btn/ajaxQryBtnInfo.do"
						
					}
					$.indi.loadPages(pages);
					
					var checkedRet=[];
					
					$(".table-role-hook tbody").on("click",".j-checkbox",function(){
						var roleId=$(this).next("input").val();
						if($(this).is(":checked")){
							console.log(checkedRet)
							checkedRet.push(roleId);
							$.unique(checkedRet);
						}else{
							console.log(checkedRet)
							var index=$.inArray(roleId,checkedRet);
							if(index > -1){
								checkedRet.splice(index,1)
							}
						}
					})
				</script>
				</div>
				<div class="row" align="center">
				<button type="button" class="btn btn-primary" onclick="saveBtnRole()"
					id="btnSave">
					<i class="icon-save"></i> 保存
				</button>
			</div>
			</div>
		</div>
</body>
</html>
