<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#hidden1").hide();
		//加载物业类型字典
		loadWylx(0);
		//加载业委会是否输入
		loadYwh();
		
		//加载tabs
		$('ul li').click(function() {
			$(this).siblings('li').removeClass('active'); //删除其他兄弟元素的样式
			$(this).addClass('active'); // 添加当前元素的样式
			var data = $(this).attr('data');
			switchView(data); //切换视图展示
		});
	}); 
	
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
	
	//onChange根据区县ID加载街道选项
	function createStreetOption(val){
		$("#jdid").empty();
		$("#sqid").empty();
		 $.indi.ajaxSubmit({url:'${ctx }/sect/ajaxGetJdList?hpbid='+val,isCheck:false,success:function(data){
			if(data.jdlist  == "" || data.jdlist  == undefined || data.jdlist  == null){
				layer.alert('街道信息加载失败！',{icon: 2});
			}else{
				$("#jdid").html(data.jdlist);
			}
		}});
	}
	
	//onChange根据街道ID加载社区选项
	function createCommOption(val){
		$("#sqid").empty();
		 $.indi.ajaxSubmit({url:'${ctx }/sect/ajaxGetSqList?jdid='+val,isCheck:false,success:function(data){
			if(data.sqlist  == "" || data.sqlist  == undefined || data.sqlist  == null){
				layer.alert('社区信息加载失败！',{icon: 2});
			}else{
				$("#sqid").html(data.sqlist);
			}
		}});
	}
	
	//onChange选择物业类型
	function loadWylx(val){
		if(val==01){
			$("#wylx").empty().html("${SECTTYPESUB1}");
		}else if(val==02){
			$("#wylx").empty().html("${SECTTYPESUB2}");
		}else{
			$("#wylx").empty();
		}
	}
	
	//onClick选择是否成立业委会
	function loadYwh(){
		$("#ywhflag1").click(function(){
			if(this.value=='是'){
				$("#ywh").attr("class","form-group");
			}else{
				$("#ywh").attr("class","hidden");
			}
		});
		$("#ywhflag2").click(function(){
			if(this.value=='否'){
				$("#ywh").attr("class","hidden");
			}else{
				$("#ywh").attr("class","form-group");
			}
		});
	}
	
	//选择物业公司
	function openWygs(){
		$.indi.openPopup({title:'选择物业公司',area:['700px','500px'],isDate:false,url:'${ctx}/sect/openCspList.do'});
	}
	
	//选择管理处
	function openGlc(){
		var xmid =$("#xmid").val();
		var wygsid =$("#wygsid").val();
		if(wygsid==''){
			layer.alert('请先选择物业公司！',{icon: 2});
			return;
		}
		$.indi.openPopup({title:'选择管理处',area:['700px','500px'],isDate:false,url:'${ctx}/sect/openGlcList.do?xmid='+xmid+'&wygsid='+wygsid});
	}
	
	//选择项目经理
	function openXmjl(){
		var xmid =$("#xmid").val();
		var wygsid =$("#wygsid").val();
		if(wygsid==''){
			layer.alert('请先选择物业公司！',{icon: 2});
			return;
		}
		$.indi.openPopup({title:'选择项目经理',area:['700px','500px'],isDate:false,url:'${ctx}/sect/openXmjlList.do?xmid='+xmid+'&wygsid='+wygsid});
	}
	
	//保存按钮
	function save() {
		$('#i-form').validate({
			rules:{
				xmmc:{
					required: true
				},
				xmdz:{
					required: true
				},
				hpbid:{
					required: true
				}
			}
		});
		$.indi.ajaxSubmit({url: "${ctx}/sect/saveSectInfo.do",success:function(data){
			$("#_appId").val(data);
			layer.alert('小区信息保存成功！',{icon: 1});
		}});
	}
	
	//提交按钮
	function send(){
		var base=$('#i-form').validate({
			rules:{
				xmmc:{
					required: true
				},
				xmdz:{
					required: true
				},
				hpbid:{
					required: true
				},
				jdid:{
					required: true
				},
				sqid:{
					required: true
				},
				szdz:{
					required: true
				},
				szxz:{
					required: true
				},
				sznz:{
					required: true
				},
				szbz:{
					required: true
				},
				wyxz:{
					required: true
				},
				wylx:{
					required: true
				},
				jzmj:{
					required: true
				},
				zdmj:{
					required: true
				},
				zds:{
					required: true
				},
				zhs:{
					required: true
				},
				zzjzmj:{
					required: true
				},
				syjzmj:{
					required: true
				},
				qtjzmj:{
					required: true
				},
				jzmd:{
					required: true
				},
				rjl:{
					required: true
				},
				lhl:{
					required: true
				},
				lhmj:{
					required: true
				},
				jzlhl:{
					required: true
				},
				jzlhmj:{
					required: true
				},
				dscws:{
					required: true
				},
				dxcws:{
					required: true
				},
				kgsj:{
					required: true
				},
				terms:{
					required: true
				},
				jhjfsj:{
					required: true
				},
				bqzbjzmj:{
					required: true
				},
				jfsysj:{
					required: true
				},
				wyglyfmj:{
					required: true
				},
				ywhyfmj:{
					required: true
				}
			}
		});
		if(base.form()==true){
			switchView(1);
		}else{
			return;
		}
		$.indi.ajaxSubmit({url: "${ctx}/sect/sendToNextStep.do",success:function(data){
			$("#_appId").val(data.appId);
			$("#_nextOrgId").val(data.nextOrgId);
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
	<div  class="content" >
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
			<form method="post" id="i-form" class="col-md-12 form-horizontal" role="form">
			<input type="hidden" id="_appId" name="appId" value=""/>
			<input type="hidden" id="_nextOrgId" name="nextOrgId" value=""/>
			<input type="hidden" id="xmid" name="xmid" value=""/>
			<input type="hidden" id="sjzt" name="sjzt" value="${sjzt}"/>
			<div class="col-md-12" id="_baseInfo">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目名称</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目名称" id="xmmc" name="xmmc"   maxlength="80" />
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">区县名称</label>
					<div class=" col-xs-10">
						<select  class="form-control col-xs-11 " id="hpbid" name="hpbid" onchange="createStreetOption(this.value)">
							${cm:createRegionHtml(hpbList,"") }
						</select>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">街道名称</label>
					<div class=" col-xs-4">
						<select  class="form-control col-xs-11 " id="jdid" name="jdid" onchange="createCommOption(this.value)">
							
						</select>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">社区名称</label>
					<div class=" col-xs-4">
						<select  class="form-control col-xs-11 " id="sqid" name="sqid" >
							
						</select>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">项目地址</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目地址" id="xmdz" name="xmdz"   maxlength="80" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">东至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-东至" id="szdz" name="szdz"   maxlength="80" />
						<p class="help-block"></p>
					</div>
					<label class="col-xs-2 control-label text-right">西至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-西至" id="szxz" name="szxz"   maxlength="80" />
						<p class="help-block"></p>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">南至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-南至" id="sznz" name="sznz"   maxlength="80" />
						<p class="help-block"></p>
					</div>
					<label class="col-xs-2 control-label text-right">北至</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-10"
							placeholder="项目四至范围-北至" id="szbz" name="szbz"   maxlength="80" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业性质</label>
					<div class=" col-xs-4">
						<select  class="form-control col-xs-11 " id="wyxz" name="wyxz" onchange="loadWylx(this.value)">
							${SECTTYPE}
						</select>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label">物业类型</label>
					<div class=" col-xs-4">
						<select  class="form-control col-xs-11 " id="wylx" name="wylx">
							${SECTTYPESUB1}
						</select>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">备注</label>
					<div class=" col-xs-10">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="项目描述" id="xmms" name="xmms" rows="5" maxlength="512"></textarea>
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
							placeholder="物业管理用房建筑面积" id="wyglyfmj" name="wyglyfmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">业委会用房面积</label>
					<div class=" col-xs-10">
						<div class="input-group">
						<input type="number" class="form-control col-xs-10" 
							placeholder="业委会用房面积" id="ywhyfmj" name="ywhyfmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">共有部分描述</label>
					<div class=" col-xs-10">
						<textarea type="text" class="form-control col-xs-11" 
							placeholder="共有部分描述" id="gybfqtms" name="gybfqtms" rows="5" maxlength="512"></textarea>
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
							placeholder="建筑面积" id="jzmj" name="jzmj" maxlength="80"/>
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
							placeholder="住宅建筑面积" id="zzjzmj" name="zzjzmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">商业建筑面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="商业建筑面积" id="syjzmj" name="syjzmj" maxlength="80"/>
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
							placeholder="其它建筑面积" id="qtjzmj" name="qtjzmj" maxlength="80"/>
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
							placeholder="用地面积" id="zdmj" name="zdmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">建筑物总幢数</label>
					<div class=" col-xs-4">
						<input type="number" class="form-control col-xs-11"
							placeholder="建筑物总幢数" id="zds" name="zds" maxlength="80"/>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">住宅套数</label>
					<div class=" col-xs-4">
						<input type="number" class="form-control col-xs-11"
							placeholder="住宅套数" id="zhs" name="zhs" maxlength="80"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">建筑密度</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="建筑密度" id="jzmd" name="jzmd" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(%)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">容积率</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="容积率" id="rjl" name="rjl" maxlength="80"/>
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
							placeholder="绿化率" id="lhl" name="lhl" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(%)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">绿化面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="绿化面积" id="lhmj" name="lhmj" maxlength="80"/>
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
							placeholder="集中绿化率" id="jzlhl" name="jzlhl" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(%)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">集中绿化面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="集中绿化面积" id="jzlhmj" name="jzlhmj" maxlength="80"/>
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
							placeholder="地上车位数" id="dscws" name="dscws" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(个)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">地下车位数</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="地下车位数" id="dxcws" name="dxcws" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(个)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">开工时间</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-11" 
							placeholder="开工时间" id="kgsj" name="kgsj" maxlength="80" onClick="WdatePicker()"/>
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">分几期建设</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="分几期建设" id="terms" name="terms" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(期)</font></span>
						</div>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">整个项目计划交付时间</label>
					<div class=" col-xs-10">
						<input type="text" class="form-control col-xs-11"
							placeholder="整个项目计划交付时间" id="jhjfsj" name="jhjfsj" maxlength="80" onClick="WdatePicker()"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">本期招标建筑面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="本期招标建筑面积" id="bqjzmj" name="bqjzmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">交付使用时间</label>
					<div class=" col-xs-4">
						<input type="text" class="form-control col-xs-11" 
							placeholder="交付使用时间" id="jfsysj" name="jfsysj" maxlength="80" onClick="WdatePicker()"/>
						<p class="help-block"></p>	
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">门卫房面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="门卫房面积" id="mwfmj" name="mwfmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">电话间面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="电话间面积" id="dhjmj" name="dhjmj" maxlength="80"/>
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
							placeholder="监控室面积" id="jksmj" name="jksmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">非机动车车库面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="非机动车车库面积" id="fjdcckmj" name="fjdcckmj" maxlength="80"/>
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
							placeholder="避难层面积" id="bncmj" name="bncmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
					<label class="col-xs-2 control-label text-right">设备层面积</label>
					<div class=" col-xs-4">
						<div class="input-group">
						<input type="number" class="form-control col-xs-11" 
							placeholder="设备层面积" id="sbcmj" name="sbcmj" maxlength="80"/>
							<span class="input-group-addon"><font class="text-warning">(平方米)</font></span>
						</div>	
						<p class="help-block"></p>	
					</div>
				</div>
			</div>
			<div class="collapse col-md-12" id="_mng_info">
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">物业公司名称</label>
					<div class=" col-xs-9">
						<input type="text" class="form-control col-xs-10" 
							placeholder="请选择在管物业公司名称" id="wygsmc" name="wygsmc" maxlength="80" readonly="readonly"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openWygs()">
					<i class="icon-plus-sign"></i><span>选择</span></button>
					<input type="hidden" value="" name="wygsid" id="wygsid"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区管理处名称</label>
					<div class=" col-xs-9">
						<input type="text" class="form-control col-xs-10"
							placeholder="请选择在管小区管理处名称" id="glcmc" name="glcmc"   maxlength="80" readonly="readonly"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openGlc()">
					<i class="icon-plus-sign"></i><span>选择</span></button>
					<input type="hidden" value="" name="glcid" id="glcid"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">小区经理姓名</label>
					<div class=" col-xs-9">
						<input type="text" class="form-control col-xs-10"
							placeholder="请选择在管小区经理姓名" id="xmjlxm" name="xmjlxm"   maxlength="80" readonly="readonly"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openXmjl()">
					<i class="icon-plus-sign"></i><span>选择</span></button>
					<input type="hidden" value="" name="gsryid" id="gsryid"/>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label text-right">是否成立业主大会</label>
					<div class=" col-xs-9">
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
					<div class=" col-xs-9">
						<input type="text" class="form-control col-xs-10"
							placeholder="在管业委会名称" id="ywhmc" name="ywhmc"   maxlength="80" />
					</div>
					<button type="button" class="btn btn-primary" onclick="openYwh()">
					<i class="icon-plus-sign"></i><span>选择</span></button>
					<input type="hidden" value="" name="ywhid" id="ywhid"/>
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