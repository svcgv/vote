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
		 	<div class="form-group" style="text-align: center;font-weight: bold;color: blue;" > 业委会信息</div> 
			
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会代码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会代码" id="ywhdm" name="ywhdm" value="${hoc.ywhdm}" minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">业主大会名称</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会名称" id="ywhmc" name="ywhmc" value="${hoc.ywhmc}" minlength="2" maxlength="250" />
					</div> 
				</div> 
				
				<div class="form-group">
					 <label class="col-xs-2 control-label text-right">归属区</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid">
							${cm:createRegionHtml(hpbList,hoc.hpbid) }
						</select>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">是否刻有公章</label>
					<div class=" col-xs-3">
							<select  class="form-control col-xs-11 " id="sfkz" name="sfkz" >
							${IS_FLAG}
						</select>
					</div>  
				</div> 
				
				 <div class="form-group">
					<label class="col-xs-2 control-label text-right">业主大会地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="业主大会地址" id="ywhdz" name="ywhdz" value="${hoc.ywhdz}" minlength="2" maxlength="250" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会联系人</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会联系人" id="lxr" name="lxr"  value="${hoc.lxr}" minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">业委会联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会联系电话" id="lxrdh" name="lxrdh"  value="${hoc.lxrdh}"  minlength="8" maxlength="40" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">任期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="任期" id="rq" name="rq" value="${hoc.rq}" minlength="1" maxlength="5" />
					</div> 
					<label class="col-xs-2 control-label text-right">第几届</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="第几届" id="term" name="term" value="${hoc.term}"  minlength="1" maxlength="5" />
					</div> 
				</div> 
				
				 <div class="form-group">
					<label class="col-xs-2 control-label text-right">成立日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="成立日期" id="clrq" name="clrq" value="${hoc.clrq}"  onClick="WdatePicker()" minlength="8"maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">业委会备案日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会备案日期" id="barq" name="barq" value="${hoc.barq}"   onClick="WdatePicker()" minlength="8" maxlength="40" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">本届任期开始日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="本届任期开始日期" id="bjksrq" name="bjksrq" value="${hoc.bjksrq}" onClick="WdatePicker()"  minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">本届任期结束日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会备案日期" id="bjjzrq" name="bjjzrq" value="${hoc.bjjzrq}" onClick="WdatePicker()" minlength="8" maxlength="40" />
					</div> 
				</div>   
				
				<div class="form-group" style="text-align: center;font-weight: bold;height: 30px; color: blue;" > 业委会主任信息</div> 
				  
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会主任姓名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会主任姓名" id="zrryxm" name="zrryxm" value="${zr.ryxm}" minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="zrlxdh" name="zrlxdh" value="${zr.lxdh}" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">出生年月</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="出生年月" id="zrcsrq" name="zrcsrq"  value="${zr.csrq}"  onClick="WdatePicker()"  minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">主任性别</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="zrryxb" name="zrryxb" >
							${ZRSEXTYPE}
						</select>
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="zrzjlx"  name="zrzjlx" >
							${ZRCERTTYPE}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="zrzjhm" name="zrzjhm" value="${zr.zjhm}" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">文化程度</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="zrwhcd"  name="zrwhcd" >
							${ZREDUCATION}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">邮箱Email</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮箱Email" id="zrryyx" name="zrryyx" value="${zr.ryyx}" minlength="8" maxlength="40" required email="false" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">上任日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="上任日期" id="zrrzrq" name="zrrzrq" value="${zr.rzrq}"  onClick="WdatePicker()" minlengngth="8" maxlength="40" />
					</div> 
				</div>   
			
			    <div class="form-group" style="text-align: center;font-weight: bold;height: 30px; color: blue;" > 预留印玺副主任</div> 
				  
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">预留印玺副主任</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="预留印玺副主任" id="ylryxm" name="ylryxm" value="${yl.ryxm}"  minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="yllxdh" name="yllxdh"  value="${yl.lxdh}" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">出生年月</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="出生年月" id="ylcsrq" name="ylcsrq" value="${yl.csrq}" onClick="WdatePicker()"  minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">主任性别</label>
					<div class=" col-xs-3"> 
						 <select  class="form-control col-xs-11 " id="ylryxb" name="ylryxb" >
							${YLSEXTYPE}
						</select>
						
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3"> 
						 <select  class="form-control col-xs-11 " id="ylzjlx" name="ylzjlx" >
							${YLCERTTYPE}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="ylzjhm" name="ylzjhm" value="${yl.zjhm}" minlength="18" maxlength="18" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">文化程度</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="ylwhcd" name="ylwhcd" >
							${YLEDUCATION}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">邮箱Email</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮箱Email" id="ylryyx" name="ylryyx" value="${yl.ryyx}" minlength="8" maxlength="40" required email="false" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">上任日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="上任日期" id="ylrzrq" name="ylrzrq" value="${yl.rzrq}" onClick="WdatePicker()"   minlengngth="8" maxlength="40" />
					</div> 
				</div>   
				
				
				<div class="form-group" style="text-align: center;font-weight: bold;height: 30px; color: blue;" > 副主任信息</div> 
				
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">副主任姓名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="副主任姓名" id="fzrryxm" name="fzrryxm" value="${fzr.ryxm}"  minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="fzrlxdh" name="fzrlxdh" value="${fzr.lxdh}" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">出生年月</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="出生年月" id="fzrcsrq" name="fzrcsrq" value="${fzr.csrq}"  onClick="WdatePicker()" minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">主任性别</label>
					<div class=" col-xs-3"> 
					<select  class="form-control col-xs-11 " id="fzrryxb" name="fzrryxb" >
							${FZRSEXTYPE}
					</select>
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="fzrzjlx" name="fzrzjlx" >
							${FZRCERTTYPE}
						</select>
					
					</div> 
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="fzrzjhm" name="fzrzjhm" value="${fzr.zjhm}" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">文化程度</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="fzrwhcd" name="fzrwhcd" >
							${FZREDUCATION}
						</select>
						
					</div> 
					<label class="col-xs-2 control-label text-right">邮箱Email</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮箱Email" id="fzrryyx" name="fzrryyx" value="${fzr.ryyx}" minlength="8" maxlength="40" required email="false"  />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">上任日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="上任日期" id="fzrrzrq" name="fzrrzrq"  value="${fzr.rzrq}" onClick="WdatePicker()" minlengngth="8" maxlength="40" />
					</div> 
				</div> 
			  
			</form>
		</div>
	</div>
</body>
</html>