<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$('#i-form').validate({
			rules:{
				zblb:{
					required: true
				},zbmc:{
					required: true
				},zbcj:{
					required: true
				}
			},
			messages:{
				zblb: "请选择指标类别",
				zbmc: "请输入指标名称",
				zbcj: "请选择指标层级"
			}
		});
		chgZbcj(${khzb.zbcj});
	});
	
	function save() { 
		$.indi.ajaxSubmit({url: "${ctx}/quotamng/savaEditQuota",success:function(data){
			if(data.status == true){
				layer.alert('检查指标信息更新成功！',{icon: 1}, function(index){
					window.parent.qryList();
					$.indi.closePopup();
				});  
			}else{
				layer.alert('检查指标信息更新失败！',{icon: 2});
			}
		}});
	}
	//关闭当前弹出框
	function closeWindow(){
		 $.indi.closePopup();
	}
	
	//选择指标层级
	function chgZbcj(val){
		//如果是选择的二级指标，则显示下面部分，并设置验证
		if("2"==val){
			//显示
			$("#d1").show();
			$("#d2").show();
			//添加验证
			$("#jfyj").rules("add",{required:true,messages:{required:"请输入考核指标细则"}});
			$("#tseq").rules("add",{required:true,messages:{required:"请输入条"}});
			$("#kseq").rules("add",{required:true,messages:{required:"请输入款"}});
			$("#jfbz").rules("add",{required:true,messages:{required:"请选择记分类型"}});
			$("#ckfz").rules("add",{required:true,range:[1,20],messages:{required:"请输入参考分值",range:"参考分值请输入{0}和{1}之间的值"}});
		}else{
			//隐藏并清空值
			$("#d1").hide();
			$("#d2").hide();
			$(".form-control","#d1").val("");
			//去掉验证
			$(".form-control","#d1").rules("remove");
		}
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
			<input type="hidden" id="check_seq" name="check_seq" value="${khzb.check_seq}">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">指标编码</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-11" value="${khzb.zbbm}" id="zbbm" name="zbbm" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">指标类别</label>
					<div class=" col-xs-8">
						<select  class="form-control col-xs-11 " id="zblb" name="zblb" >
							${NORM_TYPE}
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">指标名称</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-11" placeholder="指标名称" id="zbmc" name="zbmc" value="${khzb.zbmc}" />
					</div>
				</div>
				
				<div class="form-group" id="d2" style="display: none">
					<label class="col-xs-2 control-label text-right">上级指标</label>
					<div class=" col-xs-8">
						<select class="col-md-8 form-control" name="super_check_seq" required >
							<option>--请选择--</option>
							<c:forEach items="${list}" var="list" varStatus="listSta">
							<option name="jdid" value="${list.check_seq }" <c:if test='${list.check_seq == khzb.super_check_seq}'>selected="selected"</c:if>>${list.zbmc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">指标层级</label>
					<div class=" col-xs-8">
						<select  class="form-control col-xs-11 " id="zbcj" name="zbcj" onchange="chgZbcj(this.value)">
							${NORM_LEVEL}
						</select>
					</div>
				</div>
				<div id="d1" style="display: none">
					<div class="form-group">
						<label class="col-xs-2 control-label text-right">考核指标细则</label>
						<div class=" col-xs-8">
							<textarea type="text" class="form-control col-xs-11" placeholder="考核指标细则" id="jfyj" name="jfyj" rows="10">${khzb.jfyj}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label text-right">条</label>
						<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" placeholder="条" id="tseq" name="tseq" value="${khzb.tseq}"/>
						</div> 
						<label class="col-xs-2 control-label text-right">款</label>
						<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" placeholder="款" id="kseq" name="kseq" value="${khzb.kseq}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label text-right">记分类型</label>
						<div class=" col-xs-3">
							<select  class="form-control col-xs-11 " id="jfbz" name="jfbz" >
								${NORM_RECORD}
							</select>
						</div> 
						<label class="col-xs-2 control-label text-right">分值</label>
						<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11" placeholder="分值，范围1至20" id="ckfz" name="ckfz" value="${khzb.ckfz}"/>
						</div>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" onclick="closeWindow()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>