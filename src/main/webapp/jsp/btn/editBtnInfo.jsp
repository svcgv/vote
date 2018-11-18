<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	
	
	function save() {
		$.indi.ajaxSubmit({url:"${ctx }/btn/editSave.do",success:function(data){
			if(data.success == true){
				layer.alert('按钮信息修改成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					window.parent.qryList();
				});  
			}else{
				layer.alert('按钮信息修改失败！',{icon: 2});
			}
		}});
	}

	 function openMenu() {
			
			$.indi.openPopup({
				title : '菜单',
				area : [ '550px', '400px' ],
				isDate:false,
				url : '${ctx }/menu/qryMenu'
			});
			
//			$.indi.openPopup({title: '    ',
//				area : ['520px' , '500px'],isDate:false,url: '${ctx }/org/queryAddPara?type='+type});

		}
	
</script>
</head>
<body class="body-modle">
	<div  class="content" >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">按钮主键</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="按钮主键"  name="btnId"  value="${btn.btnId }" readonly="readonly"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">按钮名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="按钮名称"  name="btnName"  value="${btn.btnName }" required/>
					</div>
				</div>
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">按钮说明</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="按钮说明"  name="btnRmk"  value="${btn.btnRmk }" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class=" col-xs-4 control-label text-right">按钮所属页面</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11 "
							placeholder="按钮所属页面" id="btnPage" name="btnPage" required/>
						<input type="hidden" name="menuId" id = "menuId"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openMenu()">
		     	<i class="icon-plus-sign"></i> 选择</button>
				</div>
				
<!-- 				<div class="form-group"> -->
<!-- 					<label class=" col-xs-4 control-label text-right">按钮所属页面</label> -->
<!-- 					<div class=" col-xs-6"> -->
<!-- 						<input type="text" class="form-control col-xs-11 " -->
<%-- 							placeholder="按钮所属页面"  name="btnPage" required value="${btn.btnPage }"/> --%>
<!-- 					</div> -->
<!-- 				</div> -->
				
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="javascript:$.indi.closePopup();">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>