<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
  var orgIdList = ""; //选择的用户
	//页面加载时初始化页面数据
	function openAdd() {
		$.indi.openPopup({title: '机构新增',area : ['850px' , '580px'],url: '${ctx }/org/openAddOrg'});
		//关闭模拟框
	}
	//查询
	function qryList_01() {
		$.indi.loadTableByQry({
			url : "${ctx }/org/qryOrginfoList"
		});
	}
	//重置
	function reset() {
		 
		 $("#i-usrName").val("");
		 $("#orgType").val("");
	}
	//修改
	function updUsr() {
		var obj1 = selectRow("orgIdArray");
		var obj = selectRow("orgStatus");
		var obj2 = selectRow("orgType");
		 if ( obj.val=="已注销") {
				layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
				return;
			}
		 if ( obj.val=="禁用") {
				layer.alert("该机构已经被关闭,无法进行修改！",{icon:0});
				return;
			}
		if ( obj1.size != 1) {
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}
		$("#orgNo").val(obj1.val);
		var orgType = obj2.val;
		if(orgType=='市局'){
			orgType='01';
		}else if(orgType=='区(市/县)'){
			orgType='02';
		}else if(orgType=='街道'){
			orgType='03';
		}else{
			orgType='99';
		}
		var url = '${ctx }/org/qryRoleInfoById?orgType='+orgType+'&&orgNo='+obj1.val;
		$.indi.openPopup({title: '机构修改',isDate:false,area : ['550px','600px'],url: url});
	}
	//删除
	function deleUsr() {
		var obj1 = selectRow("orgIdArray");
		 var obj = selectRow("orgStatus");
		 if ( obj1.size != 1) {
				layer.alert("请选择一个机构注销！",{icon:0});
				return;
			}if ( obj.val=="已注销") {
				layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
				return;
			}
		$("#orgNo").val(obj1.val);
		$.indi.ajaxSubmit({url: "${ctx }/org/qryFindUsrInfo",success:setVal});
		layer.confirm("确定要注销么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/org/delOrgInfo'	,success:function(data){
				if(data.status == true){
					layer.alert('注销成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList_01();
					});  
				}else{
					layer.alert('注销失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	}function setVal(obj){
		 if ( obj.userNum>=1) {
				layer.alert("该机构下存在用户,无法进行注销！",{icon:0});
				return;
			}
	   }
	//启用
	function openSta(){
		var obj = selectRow("orgStatus");
		var obj1 = selectRow("orgIdArray");
		 if ( obj.size != 1) {
				layer.alert("请选择一条记录启用！",{icon:0});
				return;
			}
		 if ( obj.val=="正常使用") {
				layer.alert("该机构已经启用！",{icon:0});
				return;
			}
		 if ( obj.val=="已注销") {
				layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
				return;
			}
		 $("#orgNo").val(obj1.val);
		 $.indi.ajaxSubmit({url:'${ctx }/org/openStaOrgInfo',success:function(data){
				if(data.status == true){
					layer.alert('启用成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList_01();
					});  
				}else{
					layer.alert('启用失败！',{icon: 2});
				}
			}});
	}
	//关闭
	function closeSta(){
		var obj = selectRow("orgStatus");
		var obj1 = selectRow("orgIdArray");
		 if ( obj.size != 1) {
				layer.alert("请选择一条记录关闭！",{icon:0});
				return;
			}
		 if ( obj.val=="禁用") {
				layer.alert("该机构已经被关闭！",{icon:0});
				return;
			}
		 if ( obj.val=="已注销") {
				layer.alert("该机构已经被注销,无法进行此操作！",{icon:0});
				return;
			}
		 $("#orgNo").val(obj1.val);
		 $.indi.ajaxSubmit({url:'${ctx }/org/closeStaOrgInfo',success:function(data){
				if(data.status == true){
					layer.alert('关闭成功！',{icon: 1}, function(index){
						layer.close(index);
						qryList_01();
					});  
				}else{
					layer.alert('关闭失败！',{icon: 2});
				}
			}});
	}
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				系统管理 <small><i class="icon-double-angle-right"></i>机构管理</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form class="form-horizontal col-md-12" id="i-form" name="from-name"
					method="post">
					<input type="hidden" id="orgNo" name="orgNo" /> <input
						type="hidden" id="i-pages" name="pages" value="1" />
					<div class="form-group">
					
						<label for="exampleInputEmail1" class="col-md-2 control-label">机构名称</label>
						<div class="col-md-3">
							<input class="col-md-8 form-control" type="text" id="i-usrName"
								name="orgName" placeholder="机构名称">
						</div>
						<label for="exampleInputEmail1" class="col-md-2  control-label">机构类型</label>
						<div class="col-md-3">
							<select class="col-md-8 form-control" name="orgType" id="orgType">
							${codeData.ewTypeHtml }
							</select>
						</div>
						
						<div class="col-md-2">
							<button type="button" class="btn btn-primary "
								onclick="qryList_01()">
								<i class="icon-search"></i>查询
							</button>
							<button type="button" class="btn btn-primary "
								onclick="reset()">
								<i class="icon-remove"></i>重置
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="openAdd()">
						<i class="icon-plus-sign"></i> 新增
					</button>
					<button type="button" class="btn btn-primary" onclick="updUsr()">
						<i class="icon-edit"></i> 修改
					</button>
					<button type="button" class="btn btn-primary" onclick="deleUsr()">
						<i class="icon ion-close-circled"></i> 注销
					</button>
					<button type="button" class="btn btn-primary" onclick="openSta()">
						<i class="icon-unlock"></i> 启用
					</button>
					<button type="button" class="btn btn-primary" onclick="closeSta()">
						<i class="icon-lock"></i> 关闭
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
							<input type="hidden" id="orgIdArray" target_data="orgNo">
							<input type="hidden" id="orgStatus" target_data="orgStatus">
							<input type="hidden" id="orgType" target_data="orgType">
							</th>
							<th target_data="count">序号</th>
							<th target_data="orgNo">机构编号</th>
							<th target_data="orgName">机构名称</th>
							<th target_data="orgType">机构类型</th>
							<th target_data="parentOrgName">上级机构名称</th>
							<th target_data="linkMan">联系人</th>
							<th target_data="telNo">联系电话</th>
							<th target_data="orgStatus">状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listInfo}" var="orginfo" varStatus="orgSta">
							<tr>
								<td ><input type="checkbox" /> <input
									type="hidden" id="orgIdArray" target_data="orgNo"
									value="${orginfo.orgNo }"><input
									type="hidden" id="orgStatus" target_data="orgStatus"
									value="${orginfo.orgStatus }"><input
									type="hidden" id="orgType" target_data="orgType"
									value="${orginfo.orgType }"></td>
								<td class="center" >${orgSta.count }</td>
								<td class="center" >${orginfo.orgNo }</td>
								<td class="center" >${orginfo.orgName }</td>
								<td class="center" >${orginfo.orgType}</td>
								<td class="center" >${orginfo.parentOrgName}</td>
								<td class="center" >${orginfo.linkMan}</td>
								<td class="center">${orginfo.telNo}</td>
								<td class="center">${orginfo.orgStatus}</td>
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
						url : "${ctx }/org/qryOrginfoList"
					}
					$.indi.loadPages(pages);
				</script>
				</div>
			</div>
		</div>
</body>
</html>
