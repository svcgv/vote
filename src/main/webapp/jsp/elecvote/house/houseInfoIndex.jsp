<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indihx.comm.util.DateUtil"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$('#_file').change(handFile);
	});
	
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
	
	function handFile(){
		var filePath = $(this).val();
		if (filePath){
			var arr=filePath.split('\\');
			var fileName=arr[arr.length-1];
			$("#fileind").html(fileName);
		}else{
			$("#fileind").html("未选择文件");
		}
		//$("#fileind").html(filepath);
	}
	
	function uploadExcel(){
		var files = this.files;
		//alert(files);
		uploadFile();
	}
	
	function uploadFile() {
		$("#ui-form").ajaxSubmit({
			type : "POST",
			url : "${ctx}/house/uploadHouseInfo",
			dataType : "json",
			success : function(data) {
				alert("提交成功, 共上传了"+data.count+"条房屋信息");
			},
			error: function(data){
				alert("提交失败");
			}
			
		});
	}
	
	function qryList(){
		//var sectName = $("#sect_name").val();
		//var ipage = $("#i-pages").val()
		//alert(sectName);
		//checkBuildCode();
		$.indi.loadTableByQry({
			url : "${ctx }/house/getHouseInfo"
			//data: '{"sectName":"' + sectName + '","currPage":"' + ipage + '"}'
		});
	}
	
	//新增
	function openAdd() {
		$.indi.openPopup({title: '房屋信息新增',area : ['850px' , '650px'],url: '${ctx }/house/addHouse'});
	}
	
	//编辑
	function editExInfo(){
		var obj = selectRow("_tab_info_id");
		if(obj.size!=1){
			layer.alert("请选择一条记录进行修改！",{icon:0});
			return;
		}else{
			$("#info_id").val(obj.val);
			$.indi.openPopup({title: '评标房屋信息',area : ['850px' , '650px'],url: '${ctx }/house/editHouse'});
		}
	}

	//删除
	function deleteCommittee() {
		var obj = selectRow("_tab_info_id");
		if ( obj.size < 1) {
			layer.alert("请至少选择一条记录进行删除！",{icon:0});
			return;
		}
		$("#info_id").val(obj.val);
		var obj = selectRow("sectId");
		$("#sectId").val(obj.val);
		layer.confirm("确定要删除么？",{icon: 3, title:'提示'},function(index){
			 $.indi.ajaxSubmit({url:'${ctx }/house/delHouseInfo',success:function(data){
				if(data.status == true){
					layer.alert('房屋信息删除成功！',{icon: 1}, function(index){
						layer.close(index);
						$("#info_id").val("");
						qryList();
					});  
				}else{
					layer.alert('房屋信息删除失败！',{icon: 2});
				}
			}});
			layer.close(index);
		});	
	
	}
	function checkBuildCode(){
		var val = $("#build_code").val();
		var patt = new RegExp("[a-z]");
		if (patt.test(val) == true){
			alert("请填写大写字母");
		}
		throw("请填写大写字母");
	}
	
	//导入界面
	function openExcel(){
		$.indi.openPopup({title: '房屋信息导入',area : ['850px' , '550px'],url: '${ctx }/house/impHouse'});
	}

	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1 style="color: #3c8dbc">
				4图片 <small><i class="icon-double-angle-right"></i>房屋信息</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<div class="col-md-12">
				<form class="form-horizontal" id="i-form" name="from-name" method="post">
					<input type="hidden" id="info_id" name="infoId" value="">
					<input type="hidden" id="sectId" name="sectId" value="" >
					<input type="hidden" id="i-pages" name="currPage" value="1" /> <!-- 如果有分页，必须定义该隐藏域 -->
					<div class="form-group">
						<label for="_ruleName" class="col-md-1  control-label">小区名</label>
						<div class="col-md-3">
							<!--<input class="col-md-2 form-control" type="text" id="sect_name" name="sectName" placeholder="小区名">-->
							<select id="sectName" class="col-md-2 form-control" name="sectName" onclick="qrySectList()">
								<option value="" selected="selected" >请选择</option>
							</select>
							
						</div>
						<label for="_ruleName" class="col-md-1  control-label">楼栋号</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text" id="build_code" name="buildCode" placeholder="楼栋号">
						</div>
					</div>
					<div class="form-group">
						<label for="_ruleName" class="col-md-1  control-label">单元</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text" id="unit_code" name="unitCode" placeholder="单元">
						</div>
						<label for="_ruleName" class="col-md-1  control-label">楼层</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text" id="floor_code" name="floorCode" placeholder="楼层">
						</div>
					</div>
					<div class="form-group">
						<label for="_ruleName" class="col-md-1  control-label">户号</label>
						<div class="col-md-3">
							<input class="col-md-2 form-control" type="text" id="room_code" name="roomCode" placeholder="户号">
						</div>
					</div>
				</form>
					<!-- <div class="form-group">
						<div class="col-md-1">
							<button type="button" class="btn btn-primary"
								onclick="qryList()">
								<i class="icon-search"></i>查询
							</button>
						</div>
					</div> -->
				</div>
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="qryList()"><i class="icon-search"></i>查询</button>
					<button type="button" class="btn btn-primary" onclick="openAdd()"><i class="icon-plus-sign"></i> 新增</button>
					<button type="button" class="btn btn-primary" onclick="editExInfo()"><i class="icon-edit" ></i> 修改</button>
					<button type="button" class="btn btn-primary" onclick="deleteCommittee()"><i class="icon-trash"></i> 删除</button>
					<button type="button" class="btn btn-primary" onclick="uploadExcel()"><i class="icon-file col-md-4"></i>&nbsp;&nbsp;导入</button>
					<form method="post" enctype="multipart/form-data" id="ui-form" >
						<input type="file" id="_file" name="file"> 
						<input type="hidden" name="fileTypeId" style="border: 1px solid #ccc;height: 34px;" id="_fileTypeId" value="">
					</form>
				</div>
				
				<div class="col-md-12">
				
				
				<!-- 
				<form action="/example/html5/demo_form.asp" method="post" enctype="multipart/form-data">
						<input type="file" name="file" size="50" />
				</form>	
				<form method="post" enctype="multipart/form-data" id="ui-form" class="form-horizontal">
					<label for="_ruleName" class="col-md-2  control-label">房屋信息上传</label>
						<div class="col-md-4">
							<button class="btn btn-primary">
								<i class="icon-file col-md-4"></i>&nbsp;&nbsp;选择Excel文件
							</button>
							<input type="file" id="_file" name="file" class="upload-file" multiple="multiple"> 
							<input type="hidden" name="fileTypeId" style="border: 1px solid #ccc;height: 34px;" id="_fileTypeId" value="">
						</div>
						<div class="col-md-2">
							<label id="fileind" for="_ruleName" class="control-label">未选择文件</label>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary" onclick="uploadExcel()"><i class="glyphicon glyphicon-import"></i>导入</button>
						</div>
				</form> -->
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
								<input type="hidden" id="_tab_info_id" target_data="infoId" >
								<input type="hidden" id="sectId" target_data="sectId" >
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
		</div>
</body>
</html>
