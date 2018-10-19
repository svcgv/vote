<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('#themeId').setParentVal(); 
		 $.indi.ajaxSubmit({url: '${ctx}/checktheme/qryQuotaList',success:function(data){
			var json = JSON.stringify(data.mean);
			$("#treeview").initTreeView(json);
		}});
	});

	//选择项目
	function openSect(){
		$.indi.openPopup({title:'选择项目',area:['700px','500px'],isDate:false,url:'${ctx}/checktheme/openSectList.do'});
	}
	function save() {
		$('#i-form').validate({
			rules:{
				theme_name:{
					required: true
				},start_date:{
					required: true
				},end_date:{
					required: true
				},design_flag:{
					required: true
				}
			}
		});
		
		var quota_seq = $("#treeview").getNodesByFieldStr('quota_seq'); //取选中的指标ID
		$('#quota_seq').val(quota_seq);
		
		
		$.indi.ajaxSubmit({url: "${ctx}/checktheme/saveAddThemeInfo",success:function(data){
			if(data.status == true){
				layer.alert('检查主题信息新增成功！',{icon: 1}, function(index){
					window.parent.qryList();
					$.indi.closePopup();
				});  
			}else{
				layer.alert('检查主题信息新增失败！',{icon: 2});
			}
			}});
	}
	 
	
	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}

</script>
</head>
<body class="body-modle">
<form method="post" id="i-form" class="form-horizontal">
	<div  class=content >
		<div class="row">
			  <input type="hidden" id="_appId" name="appId" value=""/>
			  	<fieldset class="content">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">检查主题</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="检查主题" id="theme_name" name="theme_name" minlength="2" maxlength="40" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">检查开始日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="检查开始日期" id="start_date"  onClick="WdatePicker()" name="start_date" minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">检查结束日期</label>
					<div class=" col-xs-3"> 
							<input type="text" class="form-control col-xs-11"
							placeholder="检查结束日期" id="end_date"  onClick="WdatePicker()" name="end_date" minlength="2" maxlength="40" />
					</div>  
				</div> 
	 	
	 			<div class="form-group">
					<label class="col-xs-2 control-label text-right">是否制定检查</label>
					<div class=" col-xs-8">
						 <select  class="form-control col-xs-11 " id="design_flag" name="design_flag" >
							${DESIGN_FLAG}
						</select>
					</div> 
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">选择项目</label>
					<div class=" col-xs-7">
						<input type="text" class="form-control col-xs-10"
							placeholder="清选择检查的项目" id="sect_name_arr" name="sect_name_arr"  maxlength="80" readonly="readonly"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openSect()">
					<i class="icon-plus-sign"></i><span>选择</span></button>
					<input type="hidden" value="" name="sect_id" id="sect_id_arr"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="备注" id="remark" name="remark" rows="3" maxlength="512"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">选择指标
							<input type="hidden" name="quota_seq" id="quota_seq" />
							<input type="hidden" name="theme_id" id="themeId" />
					</label>
				    <div class=" col-xs-7" style="width:550px;height:300px;overflow:auto;overflow-x:hidden" >
						<div id="treeview" class=""></div>
					</div>
				</div>
	
			
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button> 
					<button type="button" class="btn btn-primary" onclick="close_01()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
				</fieldset>
			
		</div>
		
	</form>
</body>
</html>