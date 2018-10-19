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

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
			  
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目管理处编码</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="项目管理处编码" id="glcid" name="glcid" value="${glc.glcid}" minlength="2" maxlength="40" />
					</div> 
				</div> 
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
							<input type="text" class="form-control col-xs-11"
							placeholder="物业公司名称" id="wygsmc" name="wygsmc" value="${wygs.wygsmc}" minlength="2" maxlength="200" />
					</div>
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
					<button type="button" class="btn btn-primary" onclick="close_01()">
						<i class="icon-remove"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>