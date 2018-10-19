<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function save() {
		$('#i-form').validate({
			rules:{
				expert_name:{
					required: true
				},expert_six:{
					required: true
				},cert_type:{
					required: true
				},cert_code:{
					required: true
				},company_name:{
					required: true
				},company_addr:{
					required: true
				},hpbid:{
					required: true
				},contact_tel:{
					required: true
				},contact_addr:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/expert/addExInfo",success:function(data){
			if(data.status == true){
				layer.alert('人员信息新增成功！',{icon: 1}, function(index){
					window.parent.qryList();//调用父页面查询方法
					$.indi.closePopup();//关闭页面
				});
			}else{
				layer.alert('人员信息新增失败！',{icon: 2});
			}
			}});
	}
	function close_0(){
		$.indi.closePopup();
	}
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">专家姓名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="张三" id="expert_name" name="expert_name" />
					</div>
					<label class="col-xs-2 control-label text-right">性别</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="expert_six" name="expert_six" >
							${SEXTYPE}
						</select>
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="cert_type" name="cert_type" >
							${CERTTYPE}
						</select>
					</div>
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="cert_code" name="cert_code" />
					</div>
					
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">公司名称</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="公司名称" id="company_name" name="company_name" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">公司地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="公司地址" id="company_addr" name="company_addr" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">所属区</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid">
							${cm:createRegionHtml(hpbList,"") }
						</select>
					</div>
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="contact_tel" name="contact_tel" maxlength="18" minlength="6" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">联系地址</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11"
							placeholder="联系地址" id="contact_addr" name="contact_addr" />
					</div>
					<label class="col-xs-2 control-label text-right">电子邮箱</label>
					<div class=" col-xs-3">
						<input type="text" class="form-control col-xs-11"
							placeholder="电子邮箱" id="email" name="email" />
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">所属类别</label>
					<div class=" col-xs-8">
						<select  class="form-control col-xs-11 " id="expert_type" name="expert_type">
							${EXPERT_TYPE}
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">搜索关键字</label>
					<div class=" col-xs-8">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="关键字" id="search_key" name="search_key" rows="5" maxlength="512"></textarea>
					</div> 
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
							<textarea type="text" class="form-control col-xs-11" 
							placeholder="备注" id="expert_remark" name="expert_remark" rows="5" maxlength="512"></textarea>
					</div>
				</div>
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="close_0()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>