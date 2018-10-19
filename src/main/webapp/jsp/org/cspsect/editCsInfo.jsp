<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	//关闭当前弹出框
	function close_01(){
		 $.indi.closePopup();
	}
	//查询物业公司名称
	function openWygs() {
		$.indi.openPopup({title:'选择物业公司',area:['750px' , '500px'],isDate:false,url:'${ctx}/cspsect/wygsList'});
	}
	//查询项目经理名称
	function openXmjl() {
		var wygsId=$("#wygsid").val();
		if(wygsId==null||wygsId==""){
			layer.alert("请先选择物业公司！",{icon:0});
			return;
		}
		$.indi.openPopup({title:'选择项目经理',area:['750px' , '500px'],isDate:false,url:'${ctx}/cspsect/cspPersonList?wygsid='+wygsId});
	}
	//保存编辑信息
	function save() {
		$('#i-form').validate({
			rules:{
				glcmc:{
					required: true
				},glcdz:{
					required: true
				},glclxr:{
					required: true
				},glclxdh:{
					required: true
				},zbdh:{
					required: true
				},bxdh:{
					required: true
				},wygsid:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/cspsect/saveCsInfo",success:function(data){
			if(data.status == true){
				layer.alert('管理处信息编辑成功！',{icon: 1}, function(index){
					$.indi.closePopup();
					window.parent.qryList();
				});  
			}else{
				layer.alert('管理处信息编辑失败！',{icon: 2});
			}
			}});
	}

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
			<input type="hidden" id="glcid" name="glcid" value="${glc.glcid}" >
			  
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目管理处名称</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="项目管理处名称" id="glcmc" name="glcmc" value="${glc.glcmc}" minlength="2" maxlength="40" />
					</div> 
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目管理处地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="项目管理处地址" id="glcdz" name="glcdz" value="${glc.glcdz}" minlength="2" maxlength="200" />
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业公司名称</label>
					<div class=" col-xs-8">
							<input type="text" id="wygsmc" name="wygsmc" class="form-control col-xs-11" value="${wygs.wygsmc}" disabled="disabled">
					</div>
					<button type="button" class="btn btn-primary" onclick="openWygs()">
						<i class="icon-plus-sign"></i>选择</button>	
					<input type="hidden" value="${glc.wygsid}" name="wygsid" id="wygsid"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目经理名称</label>
					<div class=" col-xs-8">
							<input type="text" id="gsryname" name="gsryname" class="form-control col-xs-11" value="${cs.ryxm}" disabled="disabled">
					</div>
					<button type="button" class="btn btn-primary" onclick="openXmjl()">
						<i class="icon-plus-sign"></i>选择</button>	
					<input type="hidden" value="${glc.gsryid}" name="gsryid" id="gsryid"/>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">管理处联系人</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="管理处联系人" id="glclxr" name="glclxr" value="${glc.glclxr}" minlength="1" maxlength="18" />
					</div> 
					<label class="col-xs-2 control-label text-right">管理处联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="管理处联系电话" id="glclxdh" name="glclxdh" value="${glc.glclxdh}" minlength="11" maxlength="11" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">值班电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="值班电话" id="zbdh" name="zbdh" value="${glc.zbdh}" minlength="11" maxlength="11" />
					</div> 
					<label class="col-xs-2 control-label text-right">保修电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="保修电话" id="bxdh" name="bxdh" value="${glc.bxdh}" minlength="11" maxlength="11" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">管理处状态</label>
					<div class=" col-xs-8">
						<select  class="form-control col-xs-11 " id="status" name="status" >
							${ORG_STATUS}
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="备注" id="bz" name="bz" value="${glc.bz}" maxlength="200" />
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