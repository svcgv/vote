<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	
function save() {
	$('#i-form').validate({
		rules:{
			 wygsmc:{
				required: true
			},shxydm:{
				required: true
			},wygsdz:{
				required: true
			},hpbid:{
				required: true
			}
		}
	});
	$.indi.ajaxSubmit({url: "${ctx}/cspinfomng/saveCspInfo",success:function(data){
		$("#_appId").val(data);
		layer.alert('物业企业信息保存成功！',{icon: 1});
	}});
}


function send() {
	$('#i-form').validate({
		rules:{
			localflag:{
				required: true
			},wygsmc:{
				required: true
			},wygsdz:{
				required: true
			},certflag:{
				required: true
			},shxydm:{
				required: true
			},gslx:{
				required: true
			},bgdz:{
				required: true
			},zcdz:{
				required: true
			},qydh:{
				required: true
			},frdb:{
				required: true
			},qylxr:{
				required: true
			},hpbid:{
				required: true
			}
		}
	});
	
	$.indi.ajaxSubmit({url: "${ctx}/cspinfomng/sendToNextStep.do",success:function(data){
		$("#_appId").val(data);
		$.indi.submit({url:'${ctx }/activiti/queryNextNodeByView.do'});
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
			<input type="hidden" id="_appId" name="appId" value=""/>
			<input type="hidden" id="_taskId" name="taskId" value=""/>
			<input type="hidden" id="app_id" name="app_id" value=""/>
			 <input type="hidden" name="wygsid" value="${csp.wygsid}" >
			 <input type="hidden" id="sjzt" name="sjzt" value="${sjzt}"/>
			 	<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业公司名称</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="物业公司名称" id="wygsmc" name="wygsmc" value="${csp.wygsmc}" minlength="2" maxlength="40" />
					</div> 
				</div> 
				
				<div class="form-group">
				<label class="col-xs-2 control-label text-right">物业公司地址</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="物业公司地址" id="wygsdz" name="wygsdz" value="${csp.wygsdz}" minlength="2" maxlength="200" />
					</div>
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">是否是本地注册</label>
					<div class=" col-xs-3" >
						<select  class="form-control col-xs-11 " id="localflag" name="localflag" >
							${LOCAL_FLAG}
						</select>
					</div>   
					<label class="col-xs-2 control-label text-right">是否三证合一</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="certflag" name="certflag" >
							${CERT_FLAG}
						</select>
					</div>
				</div> 
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">注册区</label>
					<div class=" col-xs-3">
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid">
							${cm:createRegionHtml(hpbList,csp.hpbid) }
						</select>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">公司类型</label>
					<div class=" col-xs-3">
							<select  class="form-control col-xs-11 " id="gslx" name="gslx" >
							${ORG_NATURE}
						   </select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">社会信用代码</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="社会信用代码" id="shxydm" name="shxydm" value="${csp.shxydm}" minlength="10" maxlength="18" />
					</div> 
					<label class="col-xs-2 control-label text-right">营业执照号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="营业执照注册号" id="yyzzbh" name="yyzzbh" value="${csp.yyzzbh}" minlength="10" maxlength="18" />
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">资质等级</label>
					<div class=" col-xs-3">
							<select  class="form-control col-xs-11 " id="zzdj" name="zzdj" >
							${CSP_LEVEL}
						   </select>
					</div>  
					<label class="col-xs-2 control-label text-right">资质编号</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="资质编号" id="zzbh" name="zzbh" value="${csp.zzbh}" minlength="10" maxlength="18" />
					</div> 
				</div>	
				
					
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">注册证照有效期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="注册证照有效期" id="zczzyxq" name="zczzyxq" value="${csp.zczzyxq}" onClick="WdatePicker()" minlength="10" maxlength="10" />
					</div> 
					<label class="col-xs-2 control-label text-right">Email邮箱</label>
					<div class=" col-xs-3"> 
						   <input type="text" class="form-control col-xs-11"
							placeholder="Email邮箱" id="qyyx" name="qyyx"  value="${csp.qyyx}"  minlength="5" maxlength="80"  email="true" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">注册资本(万)</label>
					<div class=" col-xs-3">
							<input type="number" class="form-control col-xs-11"
							placeholder="注册资本" id="zczb" name="zczb"  value="${csp.zczb}" minlength="1" maxlength="4" />
					</div> 
					<label class="col-xs-2 control-label text-right">成立日期</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="成立日期" id="clrq" name="clrq"  value="${csp.clrq}" onClick="WdatePicker()"  minlength="10" maxlength="10" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">从事物业管理<br>活动时间</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="从物业管理活动时间" id="wyglsj" name="wyglsj"  value="${csp.wyglsj}" onClick="WdatePicker()" minlength="10" maxlength="10" />
					</div> 
					
					<label class="col-xs-2 control-label text-right">企业电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="企业电话" id="qydh" name="qydh" value="${csp.qydh}" minlength="8" maxlength="12" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">注册地址</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="注册地址" id="zcdz" name="zcdz" value="${csp.zcdz}" minlength="2" maxlength="200" />
					</div> 
					<label class="col-xs-2 control-label text-right">办公地址</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="办公地址" id="bgdz" name="bgdz" value="${csp.bgdz}" minlength="2" maxlength="200" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">企业传真</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="企业传真" id="qycz" name="qycz" value="${csp.qycz}" minlength="6" maxlength="6" />
					</div> 
					<label class="col-xs-2 control-label text-right">企业邮编</label>
					<div class=" col-xs-3">
							<input type="number" class="form-control col-xs-11"
							placeholder="企业邮编" id="qyyb" name="qyyb" value="${csp.qyyb}" minlength="6" maxlength="6" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">法人代表</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="法人代表" id="frdb" name="frdb" value="${csp.frdb}" minlength="1" maxlength="20" />
					</div> 
					<label class="col-xs-2 control-label text-right">法人手机</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="法人手机" id="frsj" name="frsj" value="${csp.frsj}" minlength="11" maxlength="11" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">企业联系人</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="企业联系人" id="qylxr" name="qylxr" value="${csp.qylxr}" minlength="1" maxlength="20" />
					</div> 
					<label class="col-xs-2 control-label text-right">联系人电话</label>
					<div class=" col-xs-3">
							<input type="text" class="form-control col-xs-11"
							placeholder="联系人电话" id="lxrdh" name="lxrdh"  value="${csp.lxrdh}" minlength="11" maxlength="11" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">企业概况</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="企业概况" id="qygk" name="qygk" value="${csp.qygk}" minlength="1" maxlength="200" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">经营范围</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="经营范围" id="zyfw" name="zyfw" value="${csp.zyfw}" minlength="1" maxlength="200" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-8">
							<input type="text" class="form-control col-xs-11"
							placeholder="备注" id="bz" name="bz" value="${csp.bz}" minlength="1" maxlength="200" />
					</div>
				</div>
			  
				<div class=" form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="save()">
						<i class="icon-save"></i> 保存
					</button>
					<button type="button" class="btn btn-primary" onclick="send()">
						<i class="icon-save"></i> 提交
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