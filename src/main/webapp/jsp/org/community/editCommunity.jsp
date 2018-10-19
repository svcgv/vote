<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	function save() {
		$('#i-form').validate({
			rules:{
				jwhbm:{
					required: true
				},jwhmc:{
					required: true
				},hpbid:{
					required: true
				},jwzr:{
					required: true
				},jwsj:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/community/editCommunityInfo",success:function(data){
			if(data.status == true){
				layer.alert('居委会信息编辑成功！',{icon: 1}, function(index){
					window.parent.qryList();
					$.indi.closePopup();
				});  
			}else{
				layer.alert('居委会信息编辑失败！',{icon: 2});
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
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
			<input type="hidden" id="jwhid" name="jwhid" value="${hoc.jwhid}"/>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委会名称</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委会名称" id="jwhmc" name="jwhmc" value="${hoc.jwhmc}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委会编码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委会编码" id="jwhbm" name="jwhbm" value="${hoc.jwhbm}" />
					</div> 
					<label class="col-xs-2 control-label text-right">归属区</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid">
							${cm:createRegionHtml(hpbList,hoc.hpbid) }
						</select>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委会地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委会地址" id="jwhdz" name="jwhdz" value="${hoc.jwhdz}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委主任</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委主任" id="jwzr" name="jwzr" value="${hoc.jwzr}" />
					</div> 
					<label class="col-xs-2 control-label text-right">居委主任电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委主任电话" id="jwzrdh" name="jwzrdh" value="${hoc.jwzrdh}" maxlength="18" minlength="6"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委书记</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委书记" id="jwsj" name="jwsj" value="${hoc.jwsj}" />
					</div> 
					<label class="col-xs-2 control-label text-right">纪委书记电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="纪委书记电话" id="jwsjdh" name="jwsjdh" value="${hoc.jwsjdh}" maxlength="18" minlength="6"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="备注" id="bz" name="bz" maxlength="200" value="${hoc.bz}" />
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
			</form>
		</div>
	</div>
</body>
</html>