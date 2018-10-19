<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		//$('#theme_id').setParentVal(); 
		 $.indi.ajaxSubmit({url: '${ctx}/checktheme/qryQuotaList',success:function(data){
			var json = JSON.stringify(data.mean);
			$("#treeview").initTreeView(json);
		}});
	}); 

	 
	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
			  <input type="hidden" id="_appId" name="appId" value=""/>
			   <input type="hidden" id="theme_id" name="theme_id" value="${theme.theme_id}"/>
			  	<fieldset class="content">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">检查主题</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="检查主题" id="theme_name" name="theme_name" value="${theme.theme_name}" minlength="2" maxlength="40" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">检查开始日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="检查开始日期" id="start_date"   name="start_date" value="${theme.start_date}" minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">检查结束日期</label>
					<div class=" col-xs-3"> 
							<input type="text" class="form-control col-xs-11"
							placeholder="检查结束日期" id="end_date"   name="end_date" value="${theme.end_date}" minlength="2" maxlength="40" />
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
					<label class="col-xs-2 control-label text-right">项目</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-11"
							placeholder="项目" id="sect_name_arr" name="sect_name_arr" value="${sect_name_arr} "  maxlength="80" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
						<textarea type="text" class="form-control col-xs-11" 
							 id="remark" name="remark" rows="5" maxlength="512">${theme.remark}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">选择指标
							<input type="hidden" name="quota_seq" id="quota_seq" />
							
					</label>
				    <div class=" col-xs-7" style="width:550px;height:300px;overflow:auto;overflow-x:hidden" >
						<div id="treeview" class=""></div>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="close_01()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>