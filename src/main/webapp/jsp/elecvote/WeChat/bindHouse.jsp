<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/jsp/elecvote/WeChat/mate.jsp"%>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <title>绑定房屋</title>
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/common.css">
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/address.css?1=1">
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	getSignature();
		qrySectList();
		
		$('#Select1').change(function(){
			qryBuildList();
			//$("#Select2").empty();
			//$("#Select2").append('<option value="">请选择</option>');
		});
		$('#Select2').change(function(){
			qryUnitList();
			//$("#Select3").empty();
			//$("#Select3").append('<option value="">请选择</option>');
		});
		$('#Select3').change(function(){
			qryFloorList();
			//$("#Select4").empty();
			//$("#Select4").append('<option value="">请选择</option>');
		});
		$('#Select4').change(function(){
			qryRoomList();
			//$("#Select5").empty();
			//$("#Select5").append('<option value="">请选择</option>');
		});
	});
    
    function getSignature(){
    	var link = location.href;
    	$.ajax({
    	        url:"${ctx}/WeChat/getconfig",//后台给你提供的接口
    	        type:"GET",
    	        data:{"url":link},
    	        async:true,
    	        dataType:"json",
    	        success:function (data){
    	            wx.config({
    	                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来
    	                appId: data.configMap.appId, // 必填，公众号的唯一标识
    	                timestamp: data.configMap.timestamp, // 必填，生成签名的时间戳
    	                nonceStr: data.configMap.nonceStr, // 必填，生成签名的随机串
    	                signature: data.configMap.signature,// 必填，签名，见附录1
    	                jsApiList: [
    	                    "closeWindow"
    	                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    	            });
    	            wx.error(function (res) {
    	                alert(res);
    	            });
    	        },
    	        error:function (error){
    	            alert(error);
    	        }
    	    });
    }
    
    function save() {
		$('#i-form').validate({
			rules:{
				sectName:{
					required: true
				},ownerName:{
					required: true
				},buildCode:{
					required: true
				},unitCode:{
					required: true
				},floorCode:{
					required: true
				},roomCode:{
					required: true
				},linkPhone:{
					required: true
				},certCode:{
					required: true
				}
			}
		});
		//alert("hello");
		$.indi.ajaxSubmit({url: "${ctx}/WeChat/House/bindHouseInfo",success:function(data){
			if(data.status == true){
				//layer.alert('房屋信息绑定成功！', {icon: 1});
				alert('房屋信息绑定成功 !');
				wx.closeWindow();//关闭页面
			}else{
				layer.alert('房屋信息绑定失败！',{icon: 2});
			}
			}});
    }
    
    //查询小区名
    function qrySectList(){
    	console.log(":"+ $("#Select1 option:selected").val()+":");
    	if ($("#Select1 option:selected").val() == ""){
    		console.log("ajaxsubmit");
	    	$.indi.ajaxSubmit({url:'${ctx }/WeChat/House/getSectInfo',success:function(data){
				if(data.status == true){
					var list = data.listInfo;
					$("#Select1").empty();
					$("#Select1").append('<option value="">请选择</option>');
					
					for(var i=0; i<list.length; i++){
						$("#Select1").append('<option value="' + list[i].sectName +'">'+ list[i].sectName +'</option>');
					}
				}else{
					layer.alert('小区信息异常，请重试！',{icon: 2});
				}
			}});
    	}
	}
    //查询楼栋信息
    function qryBuildList(){
    	/*
    	$('#i-form').validate({
			rules:{
				sectName:{
					required: true
				}
			}
		});*/
    	if ($("#Select2 option:selected").val() == ""){
	    	$.indi.ajaxSubmit({url:'${ctx }/WeChat/House/getBuildInfo',success:function(data){
				if(data.status == true){
					var list = data.listInfo;
					$("#Select2").empty();
					$("#Select2").append('<option value="">请选择</option>');
					
					for(var i=0; i<list.length; i++){
						$("#Select2").append('<option value="' + list[i].BUILD_CODE +'">'+ list[i].BUILD_CODE +'</option>');
					}
				}else{
					layer.alert('楼栋信息异常，请重试！',{icon: 2});
				}
			}});
    	}
	}
  //查询单元信息
    function qryUnitList(){
    	/*$('#i-form').validate({
			rules:{
				sectName:{
					required: true
				},buildCode:{
					required: true
				}
			}
		});*/
    	if ($("#Select3 option:selected").val() == ""){
	    	$.indi.ajaxSubmit({url:'${ctx }/WeChat/House/getUnitInfo',success:function(data){
				if(data.status == true){
					var list = data.listInfo;
					$("#Select3").empty();
					$("#Select3").append('<option value="">请选择</option>');
					
					for(var i=0; i<list.length; i++){
						$("#Select3").append('<option value="' + list[i].UNIT_CODE +'">'+ list[i].UNIT_CODE +'</option>');
					}
				}else{
					layer.alert('单元信息异常，请重试！',{icon: 2});
				}
			}});
    	}
	}
 	 //查询单元信息
    function qryFloorList(){
    	/*$('#i-form').validate({
			rules:{
				sectName:{
					required: true
				},buildCode:{
					required: true
				},unitCode:{
					required: true
				}
			}
		});*/
    	if ($("#Select4 option:selected").val() == ""){
	    	$.indi.ajaxSubmit({url:'${ctx }/WeChat/House/getFloorInfo',success:function(data){
				if(data.status == true){
					var list = data.listInfo;
					$("#Select4").empty();
					$("#Select4").append('<option value="">请选择</option>');
					
					for(var i=0; i<list.length; i++){
						$("#Select4").append('<option value="' + list[i].FLOOR_CODE +'">'+ list[i].FLOOR_CODE +'</option>');
					}
				}else{
					layer.alert('楼层信息异常，请重试！',{icon: 2});
				}
			}});
    	}
	}
  //查询单元信息
    function qryRoomList(){
    	/*$('#i-form').validate({
			rules:{
				sectName:{
					required: true
				},buildCode:{
					required: true
				},unitCode:{
					required: true
				},floorCode:{
					required: true
				}
			}
		});*/
    	if ($("#Select5 option:selected").val() == ""){
	    	$.indi.ajaxSubmit({url:'${ctx }/WeChat/House/getRoomInfo',success:function(data){
				if(data.status == true){
					var list = data.listInfo;
					$("#Select5").empty();
					$("#Select5").append('<option value="">请选择</option>');
					
					for(var i=0; i<list.length; i++){
						$("#Select5").append('<option value="' + list[i].ROOM_CODE +'">'+ list[i].ROOM_CODE +'</option>');
					}
				}else{
					layer.alert('房间信息异常，请重试！',{icon: 2});
				}
			}});
    	}
	}
    </script>
</head>
<body>
    <div class="sel_addr">
        <span class="left manage_goback" onclick="javascript:history.go(-1)"><i></i></span>
        <h1 class="addr_title2">
            绑定房屋</h1>
    </div>
    <form id="i-form" name="from-name" method="post">
    <input type="hidden" id="openId" name="openId" value="${openId}">
    <ul class="addr_add" style="margin-top: 50px;">
        <li class="input"><span class="tit">手机号</span> <span class="val">
            <input type="text" name="linkPhone" value="" placeholder="请填写手机号"></span>
        </li>
        <li class="select"><span class="tit">选择小区</span> <span class="val">
            <select id="Select1" name="sectName">
                <option value="" selected="selected">请选择</option>
            </select></span> </li>
         <li class="select" ><span class="tit">选择楼幢</span> <span class="val">
            <select id="Select2" name="buildCode" >
                <option value="">请选择</option>
            </select></span> </li>

         <li class="select"><span class="tit">选择单元</span> <span class="val">
            <select id="Select3" name="unitCode" >
                <option value="">请选择</option>
            </select></span> </li>

         <li class="select"><span class="tit">选择楼层</span> <span class="val">
            <select id="Select4" name="floorCode" >
                <option value="">请选择</option>
            </select></span> </li>

         <li class="select"><span class="tit">选择户号</span> <span class="val">
            <select id="Select5" name="roomCode" >
                <option value="">请选择</option>
            </select></span> </li>

        <li class="input"><span class="tit">业主姓名</span> <span class="val">
            <input type="text" name="ownerName" value="" placeholder="请填写业主姓名"></span>
        </li>
        <li class="input"><span class="tit">身份证号</span> <span class="val">
            <input type="text" name="certCode" value="" placeholder="请填写身份证号"></span>
        </li>
    </ul>
    </form>
    <div class="btn_area">
    	<button type="button"  id="btnSubmit" onclick="save()"><span class="btn_type1" id="btnSubmit">确定</span></button>
        <!--  <a id="loginSubmit" href="index.aspx"><span class="btn_type1" id="btnSubmit">确定</span></a>-->
    </div>
</body>
</html>