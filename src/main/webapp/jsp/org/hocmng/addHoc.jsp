<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">

function save() {
	 $('#i-form').validate({
		rules:{
			ywhdm:{
				required: true
			},ywhmc:{
				required: true
			},hpbid:{
				required: true
			},lxr:{
				required: true
			},lxrdh:{
				required: true
			},rq:{
				required: true
			},term:{
				required: true
			},bjksrq:{
				required: true
			},bjjzrq:{
				required: true
			},zrryxm:{
				required: true
			},zrlxdh:{
				required: true
			},zrzjlx:{
				required: true
			},zrzjhm:{
				required: true
			},ylryxm:{
				required: true
			},yllxdh:{
				required: true
			},ylzjlx:{
				required: true
			},ylzjhm:{
				required: true
			},fzrryxm:{
				required: true
			},fzrlxdh:{
				required: true
			},fzrzjlx:{
				required: true
			},fzrzjhm:{
				required: true
			}            
		}
	}); 	
	 $.indi.ajaxSubmit({url: "${ctx}/hoc/saveHocInfo",success:function(data){
		if(data.status == true){
			layer.alert('业委会新增成功！',{icon: 1}, function(index){
				window.parent.qryList();
				$.indi.closePopup();
			});  
		}else{
			layer.alert('业委会新增失败！',{icon: 2});
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
			
			  	<div class="form-group" style="text-align: center;font-weight: bold;color: blue;" > 业委会信息</div> 
			
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会代码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会代码" id="ywhdm" name="ywhdm" minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">业主大会名称</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会名称" id="ywhmc" name="ywhmc" minlength="2" maxlength="250" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">归属区</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid">
							${cm:createRegionHtml(hpbList,"") }
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
							placeholder="业主大会地址" id="ywhdz" name="ywhdz" minlength="2" maxlength="250" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会联系人</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会联系人" id="lxr" name="lxr" minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">业委会联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会联系电话" id="lxrdh" name="lxrdh" minlength="8" maxlength="40" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">任期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="任期" id="rq" name="rq" minlength="1" maxlength="5" />
					</div> 
					<label class="col-xs-2 control-label text-right">第几届</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="第几届" id="term" name="term" minlength="1" maxlength="5" />
					</div> 
				</div> 
				
				 <div class="form-group">
					<label class="col-xs-2 control-label text-right">成立日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="成立日期" id="clrq" name="clrq"  onClick="WdatePicker()" minlength="8"maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">业委会备案日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会备案日期" id="barq" name="barq"   onClick="WdatePicker()" minlength="8" maxlength="40" />
					</div> 
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">本届任期开始日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="本届任期开始日期" id="bjksrq" name="bjksrq" onClick="WdatePicker()"  minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">本届任期结束日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会备案日期" id="bjjzrq" name="bjjzrq" onClick="WdatePicker()" minlength="8" maxlength="40" />
					</div> 
				</div>   
				
				<div class="form-group" style="text-align: center;font-weight: bold;height: 30px; color: blue;" > 业委会主任信息</div> 
				  
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会主任姓名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="业委会主任姓名" id="zrryxm" name="zrryxm"  minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="zrlxdh" name="zrlxdh" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">出生年月</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="出生年月" id="zrcsrq" name="zrcsrq"  onClick="WdatePicker()"  minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">主任性别</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="zrryxb" name="zrryxb" >
							${SEXTYPE}
						</select>
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="zrzjlx" name="zrzjlx" >
							${CERTTYPE}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="zrzjhm" name="zrzjhm" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">文化程度</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="zrwhcd" name="zrwhcd" >
							${EDUCATION}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">邮箱Email</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮箱Email" id="zrryyx" name="zrryyx" minlength="8" maxlength="40" email="true"  />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">上任日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="上任日期" id="zrrzrq" name="zrrzrq"  onClick="WdatePicker()" minlengngth="8" maxlength="40" />
					</div> 
				</div>   
			
			    <div class="form-group" style="text-align: center;font-weight: bold;height: 30px; color: blue;" > 预留印玺副主任</div> 
				  
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">预留印玺副主任</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="预留印玺副主任" id="ylryxm" name="ylryxm"  minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="yllxdh" name="yllxdh" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">出生年月</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="出生年月" id="ylcsrq" name="ylcsrq" onClick="WdatePicker()"  minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">主任性别</label>
					<div class=" col-xs-3"> 
						 <select  class="form-control col-xs-11 " id="ylryxb" name="ylryxb" >
							${SEXTYPE}
						</select>
						
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3"> 
						 <select  class="form-control col-xs-11 " id="ylzjlx" name="ylzjlx" >
							${CERTTYPE}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="ylzjhm" name="ylzjhm" minlength="18" maxlength="18" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">文化程度</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="ylwhcd" name="ylwhcd" >
							${EDUCATION}
						</select>
					</div> 
					<label class="col-xs-2 control-label text-right">邮箱Email</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮箱Email" id="ylryyx" name="ylryyx" minlength="8" maxlength="40" email="true"  />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">上任日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="上任日期" id="ylrzrq" name="ylrzrq" onClick="WdatePicker()"   minlengngth="8" maxlength="40" />
					</div> 
				</div>   
				
				
				<div class="form-group" style="text-align: center;font-weight: bold;height: 30px; color: blue;" > 副主任信息</div> 
				
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">副主任姓名</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="副主任姓名" id="fzrryxm" name="fzrryxm"  minlength="2" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">联系电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系电话" id="fzrlxdh" name="fzrlxdh" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">出生年月</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="出生年月" id="fzrcsrq" name="fzrcsrq"  onClick="WdatePicker()" minlength="8" maxlength="40" />
					</div> 
					<label class="col-xs-2 control-label text-right">主任性别</label>
					<div class=" col-xs-3"> 
					<select  class="form-control col-xs-11 " id="fzrryxb" name="fzrryxb" >
							${SEXTYPE}
					</select>
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">证件类型</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="fzrzjlx" name="fzrzjlx" >
							${CERTTYPE}
						</select>
					
					</div> 
					<label class="col-xs-2 control-label text-right">证件号码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="证件号码" id="fzrzjhm" name="fzrzjhm" minlength="8" maxlength="40" />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">文化程度</label>
					<div class=" col-xs-3"> 
						<select  class="form-control col-xs-11 " id="fzrwhcd" name="fzrwhcd" >
							${EDUCATION}
						</select>
						
					</div> 
					<label class="col-xs-2 control-label text-right">邮箱Email</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="邮箱Email" id="fzrryyx" name="fzrryyx" minlength="8" maxlength="40" email="true"   />
					</div> 
				</div>   
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">上任日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="上任日期" id="fzrrzrq" name="fzrrzrq"  onClick="WdatePicker()" minlengngth="8" maxlength="40" />
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