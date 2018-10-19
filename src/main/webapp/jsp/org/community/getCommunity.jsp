<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

	//
</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委会名称</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委会名称" id="jwhmc" name="jwhmc" value="${jwh.jwhmc}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委会编码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委会编码" id="jwhbm" name="jwhbm" value="${jwh.jwhbm}" />
					</div> 
					<label class="col-xs-2 control-label text-right">归属区</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid">
							${cm:createRegionHtml(hpbList,jwh.hpbid) }
						</select>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委会地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委会地址" id="jwhdz" name="jwhdz" value="${jwh.jwhdz}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委主任</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委主任" id="jwzr" name="jwzr" value="${jwh.jwzr}" />
					</div> 
					<label class="col-xs-2 control-label text-right">居委主任电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委主任电话" id="jwzrdh" name="jwzrdh" value="${jwh.jwzrdh}" maxlength="18" minlength="6"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">居委书记</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="居委书记" id="jwsj" name="jwsj" value="${jwh.jwsj}" />
					</div> 
					<label class="col-xs-2 control-label text-right">纪委书记电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="纪委书记电话" id="jwsjdh" name="jwsjdh" value="${jwh.jwsjdh}" maxlength="18" minlength="6"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="备注" id="bz" name="bz" maxlength="200" value="${jwh.bz}" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>