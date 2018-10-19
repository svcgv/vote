<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#hidden1").hide();
		//加载业委会是否输入
		initYwh();
		loadWylx();
		
		//加载tabs
		$('ul li').click(function() {
			$(this).siblings('li').removeClass('active'); //删除其他兄弟元素的样式
			$(this).addClass('active'); // 添加当前元素的样式
			var data = $(this).attr('data');
			switchView(data); //切换视图展示
		});
	}); 
	//初始化加载业委会表单默认选项
	function initYwh(){
		if('${sect.ywhflag}'=='是'){
			$("#ywh").attr("class","form-group");
			$("#ywhflag1").attr("checked","checked");
		}else{
			$("#ywh").attr("class","hidden");
			$("#ywhflag2").attr("checked","checked");
		}
	}
	//初始化加载物业类型单默认选项
	function loadWylx(){
		var val="${sect.wyxz}";
		if(val=='01'){
			$("#wylx").empty().val("${cm:getCodeVal('SECTTYPESUB1',sect.wylx) }");
		}else if(val=='02'){
			$("#wylx").empty().val("${cm:getCodeVal('SECTTYPESUB2',sect.wylx) }");
		}else{
			$("#wylx").empty();
		}
	}
	
	//tabs切换
	function switchView(data) {
		if (data == "0") {
			$('#_baseInfo').removeClass('collapse');
			$('#_pt_info').addClass('collapse');
			$('#_mng_info').addClass('collapse');
		} else if (data == "1") {
			$('#_baseInfo').addClass('collapse');
			$('#_pt_info').removeClass('collapse');
			$('#_mng_info').addClass('collapse');
		} else if (data == "2") {
			$('#_baseInfo').addClass('collapse');
			$('#_pt_info').addClass('collapse');
			$('#_mng_info').removeClass('collapse');
		}
	}
	
	//审核通过按钮
	function pass(){
		$.indi.submit({url:'${ctx }/activiti/queryNextNodeByView.do'});
	}
	
	//审核退回按钮
	function back(){
		$.indi.submit({url:'${ctx }/activiti/openRejectMemo.do'});
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
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active" data="0"><a href="javascript:void('0')">基本信息</a></li>
					<li data="1"><a href="javascript:void('0')">配套信息</a></li>
					<li data="2"><a href="javascript:void('0')">在管单位</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<form method="post" id="i-form" class="form-horizontal" role="form">
			<input type="hidden" id="_appId" name="appId" value="${sect.app_id}"/>
			<input type="hidden" id="_taskId" name="taskId" value="${app.taskId }"/>
			<input type="hidden" id="xmid" name="xmid" value="${xmid}"/>
			<input type="hidden" id="app_id" name="app_id" value="${sect.app_id}"/>
			<div class="col-md-12" id="_baseInfo">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目编码</label>
					<div class=" col-xs-10">
							<input type="text" class="form-control col-xs-10"
							placeholder="自动生成" id="xmbm" name="xmbm"  value="${sect.xmbm}" readonly="readonly"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目名称</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目名称" id="xmmc" name="xmmc"  maxlength="80" value="${sect.xmmc}"/>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">区县名称</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"
							placeholder="区县名称" id="hpbmc" name="hbbmc"  maxlength="80" value="${sect.hpbmc}"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">街道名称</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="街道名称" id="jdmc" name="jdmc"  maxlength="80" value="${sect.jdmc}"/>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">社区名称</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="社区名称" id="sqmc" name="sqmc"  maxlength="80" value="${sect.sqmc}"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目地址</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目地址" id="xmdz" name="xmdz"  maxlength="80" value="${sect.xmdz}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">东至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-东至" id="szdz" name="szdz"  maxlength="80" value="${sect.szdz}"/>
						<p class="help-block"></p>
					</div>
					<label class="col-xs-2 control-label text-right">西至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-西至" id="szxz" name="szxz"  maxlength="80" value="${sect.szxz}"/>
						<p class="help-block"></p>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">南至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-南至" id="sznz" name="sznz"  maxlength="80" value="${sect.sznz}"/>
						<p class="help-block"></p>
					</div>
					<label class="col-xs-2 control-label text-right">北至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-北至" id="szbz" name="szbz"  maxlength="80" value="${sect.szbz}"/>
						<p class="help-block"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业性质</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="物业性质" id="wyxz" name="wyxz"  maxlength="80" value="${cm:getCodeVal('SECTTYPE',sect.wyxz) }"/>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label">物业类型</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="物业类型" id="wylx" name="wylx"  maxlength="80" value="${cm:getCodeVal('SECTTYPE',sect.wylx) }"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-10">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="项目描述" id="xmms" name="xmms" rows="5" maxlength="512">${sect.xmms}</textarea>
					</div>
				</div>
			</div>
			<div class="collapse col-md-12" id="_pt_info">
				<div class="col-md-12">
					<span style="color: #72afd2">物业管理用房配置</span>
					<hr>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业管理用房面积</label>
					<div class=" col-xs-10">
						<div class="input-group">
						<input type="number" class="form-control col-xs-5" 
							placeholder="物业管理用房建筑面积" id="wyglyfmj" name="wyglyfmj" maxlength="80" value="${sect.wyglyfmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会用房面积</label>
					<div class=" col-xs-10">
						<div class="input-group">
						<input type="number" class="form-control col-xs-10" 
							placeholder="业委会用房面积" id="ywhyfmj" name="ywhyfmj" maxlength="80" value="${sect.ywhyfmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">共有部分描述</label>
					<div class=" col-xs-10">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="共有部分描述" id="gybfqtms" name="gybfqtms" rows="5" maxlength="512">${sect.gybfqtms}</textarea>
					</div>
				</div>
				<div class="col-md-12">
					<span style="color: #72afd2">其它配套信息</span>
					<hr>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">总建筑面积</label>
					<div class=" col-xs-10">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="建筑面积" id="jzmj" name="jzmj" maxlength="80" value="${sect.jzmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">住宅建筑面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="住宅建筑面积" id="zzjzmj" name="zzjzmj" maxlength="80" value="${sect.zzjzmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">商业建筑面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="商业建筑面积" id="syjzmj" name="syjzmj" maxlength="80" value="${sect.syjzmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">其它建筑面积</label>
					<div class=" col-xs-10">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="其它建筑面积" id="qtjzmj" name="qtjzmj" maxlength="80" value="${sect.qtjzmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">总用地面积</label>
					<div class=" col-xs-10">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="用地面积" id="zdmj" name="zdmj" maxlength="80" value="${sect.zdmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">建筑物总幢数</label>
					<div class=" col-xs-4">
						<input type="number" class="form-control col-xs-11"
							placeholder="建筑物总幢数" id="zds" name="zds" maxlength="80" value="${sect.zds}"/>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">住宅套数</label>
					<div class=" col-xs-4">
						<input type="number" class="form-control col-xs-11"
							placeholder="住宅套数" id="zhs" name="zhs" maxlength="80" value="${sect.zhs}"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">建筑密度</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="建筑密度" id="jzmd" name="jzmd" maxlength="80" value="${sect.jzmd}"/>
							<span class="input-group-addon"><font class="text-warning">(%)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">容积率</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="容积率" id="rjl" name="rjl" maxlength="80" value="${sect.rjl}"/>
							<span class="input-group-addon"><font class="text-warning">(%)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">绿化率</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="绿化率" id="lhl" name="lhl" maxlength="80" value="${sect.lhl}"/>
							<span class="input-group-addon"><font class="text-warning">(%)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">绿化面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="绿化面积" id="lhmj" name="lhmj" maxlength="80" value="${sect.lhmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">集中绿化率</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="集中绿化率" id="jzlhl" name="jzlhl" maxlength="80" value="${sect.jzlhl}"/>
							<span class="input-group-addon"><font class="text-warning">(%)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">集中绿化面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="集中绿化面积" id="jzlhmj" name="jzlhmj" maxlength="80" value="${sect.jzlhmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">地上车位数</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="地上车位数" id="dscws" name="dscws" maxlength="80" value="${sect.dscws}"/>
							<span class="input-group-addon"><font class="text-warning">(个)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">地下车位数</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="地下车位数" id="dxcws" name="dxcws" maxlength="80" value="${sect.dxcws}"/>
							<span class="input-group-addon"><font class="text-warning">(个)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">开工时间</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-11" 
							placeholder="开工时间" id="kgsj" name="kgsj" maxlength="80" onClick="WdatePicker()" value="${sect.kgsj}"/>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">分几期建设</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="分几期建设" id="terms" name="terms" maxlength="80" value="${sect.terms}"/>
							<span class="input-group-addon"><font class="text-warning">(期)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">整个项目计划交付时间</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-11"
							placeholder="整个项目计划交付时间" id="jhjfsj" name="jhjfsj" maxlength="80" onClick="WdatePicker()" value="${sect.jhjfsj}"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">本期招标建筑面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="本期招标建筑面积" id="bqjzmj" name="bqjzmj" maxlength="80" value="${sect.bqjzmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">交付使用时间</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-11" 
							placeholder="交付使用时间" id="jfsysj" name="jfsysj" maxlength="80" onClick="WdatePicker()" value="${sect.jfsysj}"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">门卫房面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="门卫房面积" id="mwfmj" name="mwfmj" maxlength="80" value="${sect.mwfmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">电话间面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="电话间面积" id="dhjmj" name="dhjmj" maxlength="80" value="${sect.dhjmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">监控室面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="监控室面积" id="jksmj" name="jksmj" maxlength="80" value="${sect.jksmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">非机动车车库面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="非机动车车库面积" id="fjdcckmj" name="fjdcckmj" maxlength="80" value="${sect.fjdcckmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">避难层面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="避难层面积" id="bncmj" name="bncmj" maxlength="80" value="${sect.bncmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">设备层面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="设备层面积" id="sbcmj" name="sbcmj" maxlength="80" value="${sect.sbcmj}"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
				</div>
			</div>
			<div class="collapse col-md-12" id="_mng_info">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业公司名称</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-10"
							placeholder="在管物业公司名称" id="wygsmc" name="wygsmc"  maxlength="80" value="${sect.wygsmc}"/>
					</div>
					<input type="hidden" value="" name="wygsid" id="wygsid" value="${sect.wygsid}"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区管理处名称</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-10"
							placeholder="在管小区管理处名称" id="glcmc" name="glcmc"  maxlength="80" value="${sect.glcmc}"/>
					</div>
					<input type="hidden" value="" name="glcid" id="glcid" value="${sect.glcid}"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区经理姓名</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-10"
							placeholder="在管小区经理姓名" id="xmjlxm" name="xmjlxm"  maxlength="80" value="${sect.xmjlxm}"/>
					</div>
					<input type="hidden" value="" name="gsryid" id="gsryid" value="${sect.gsryid}"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">是否成立业主大会</label>
					<div class=" col-xs-8">
						<label class="radio">
						   <input type="radio" id="ywhflag1" name="ywhflag" value="是" checked />已成立
						 </label>
						 <label class="radio">
						   <input type="radio" id="ywhflag2" name="ywhflag" value="否"/>未成立
						 </label>
					</div>
				</div>
				<div class="form-group" id="ywh">
					<label class="col-xs-2 control-label text-right">业委会名称</label>
					<div class=" col-xs-8">
						<input type="text" class="form-control col-xs-10"
							placeholder="在管业委会名称" id="ywhmc" name="ywhmc"  maxlength="80" value="${sect.ywhmc}"/>
					</div>
					<input type="hidden" value="" name="ywhid" id="ywhid" value="${sect.ywhid}"/>
				</div>
			</div>
			<div class=" form-group" align="center">
				<button type="button" class="btn btn-primary" onclick="pass()">
					<i class="icon-check"></i> 审核通过
				</button>
				<button type="button" class="btn btn-primary" onclick="back()">
					<i class="icon-share"></i> 审核驳回
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