<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$("#hidden1").hide();
	$("#hidden2").hide();
	$.indi.ajaxSubmit({url: "${ctx }/menu/qryParentId",success:setVal});
	$.indi.ajaxSubmit({url: "${ctx }/menu/qryMenuRage",success:setMenu});
}); 
	function setVal(obj){
	  $("#parent_id").append(obj.options);
   }
	function setMenu(obj){
	 $("#menu_level").append(obj.options);
	}

	function show(){
		var menulevel=$("#menu_level").val();
		if(menulevel=="2"){
			$("#hidden1").show();
			$("#hidden2").show();
		}if(menulevel=="1"){
			$("#hidden1").hide();
			$("#hidden2").hide();
		}
		
	}
	
	function save(){
		$('#i-form').validate({
			rules:{
				menuName:{
					required: true
				},
				cssIcon:{
					required: true
				},
				sortNum:{
					required: true
				},
				menuLevel:{
					required: true
				},		
				menuUrl:{
					required: true
				},
				parentId:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url:"${ctx}/menu/addSave",closeMode:true,success:function(data){
			if(data.status == true){
				layer.alert('菜单信息新增成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					window.parent.qryList();
				});  
			}else{
				layer.alert('菜单信息新增失败！',{icon: 2});
			}
		}});
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">菜单名称</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="菜单名称" id="menu_name" name="menuName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">标签样式</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="标签样式" id="menu_name" name="cssIcon" value="icon-list"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">顺序编号</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="顺序编号" id="sortNum" name="sortNum" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-4 control-label text-right">菜单级别</label>
					<div class=" col-xs-6">
						<select  class="form-control col-xs-11 " id="menu_level" name="menuLevel" onchange="show();" >
						
						</select>
					</div>
				</div>
				<div class="form-group" id="hidden1">
					<label class="col-xs-4 control-label text-right">父菜单</label>
					<div class=" col-xs-6">
						<select  class="form-control col-xs-11 " id="parent_id" name="parentId" >
							
						</select>
					</div>
				</div>
				
				<div class="form-group" id="hidden2">
					<label class="col-xs-4 control-label text-right">菜单地址</label>
					<div class=" col-xs-6">
						<input type="text" class="form-control col-xs-11"
							placeholder="菜单地址" id="menu_url" name="menuUrl" />
					</div>
				</div>
			

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