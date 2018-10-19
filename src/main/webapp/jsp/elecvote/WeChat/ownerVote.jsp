<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/jsp/elecvote/WeChat/mate.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <title>业主表决</title>
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/common.css">
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/address.css?1=1">
 <style> 
    .demo--label{margin:0;display:inline-block; margin-right:8px}
.demo--radio{display:none}
.demo--radioInput{background-color:#fff;border:1px solid rgba(0,0,0,0.15);border-radius:100%;display:inline-block;height:16px;margin-right:10px;margin-top:-1px;vertical-align:middle;width:16px;line-height:1}
.demo--radio:checked + .demo--radioInput:after{background-color:#57ad68;border-radius:100%;content:"";display:inline-block;height:12px;margin:2px;width:12px}
.demo--checkbox.demo--radioInput,.demo--radio:checked + .demo--checkbox.demo--radioInput:after{border-radius:0}
    </style>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function(){
    	var map = '${map}';
    	var openId = '${openId}';
    	var jsonObj = JSON.parse(map);
    	var topicInfo = jsonObj.topic;
    	//console.log(map);
    	console.log(openId);
    	console.log(topicInfo);
    	
    	$(".select1").text(topicInfo.topicName);
    	$(".valtxt").text(topicInfo.voteStartDate+"~"+topicInfo.voteEndDate);
    	$(".valtxtbz").text(topicInfo.topicContent);
    	$("#topicId").val(topicInfo.topicId);
    	
    	getSignature();
    	//checkapi();
    	
    	//console.log($("ul").html());
    	$('#uploadBtn').click(function () {  
    		
    	    wx.chooseImage({  
    	        count: 1,  
    	        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有  
    	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有  
    	        success: function (res) {  
    	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片  
    	            uploadImg(localIds[0]);  
    	        }  
    	    });  
    	});  
    });
    
    function check(value){
    	$('#result').val(value);
    }
    
    /*不再使用formdata上传文件*/
    /*
    function voteSubmit(){
    	var formData = new FormData();
    	formData.append('openId',$('#openId').val());
    	formData.append('topicId',$('#topicId').val());
    	formData.append('result',$('#result').val());
    	//formData.append('file', $('#picFile')[0].files[0]);//不用这个上传文件了
    	//console.log($('#picFile')[0].files[0]);
    	//console.log($('#picFile').val());
    	console.log($('#result').val());
    	//alert("hello");
    	var str = JSON.stringify(formData);
    	//alert(str);
    	
    	$.indi.ajaxSubmit({url: "${ctx}/WeChat/vote/voteSubmit",data: formData,contentType: false,success:function(data){
			if(data.status == true){
				layer.alert('投票成功！',{icon: 1});
			}else{
				layer.alert('投票失败！',{icon: 2});
			}
			}});
    }*/
    function voteSubmit(){
    	$.indi.ajaxSubmit({url: "${ctx}/WeChat/vote/voteSubmit",success:function(data){
			if(data.status == true){
				//layer.alert('投票成功！',{icon: 1});
				alert("投票成功");
				wx.closeWindow();//关闭页面
			}else{
				layer.alert('投票失败！',{icon: 2});
			}
			}});
    }
    
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
    	                    "chooseImage",
    	                    "uploadImage",
    	                    "closeWindow"
    	                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    	            });
    	            wx.error(function (res) {
    	                alert(res);
    	            });
    	        },
    	        error:function (error){
    	            alert(error)
    	        }
    	    });
    }
    
  //具体上传图片  
	function uploadImg(e) {  
	    wx.uploadImage({  
	        localId: e, // 需要上传的图片的本地ID，由chooseImage接口获得  
	        isShowProgressTips: 1, // 默认为1，显示进度提示  
	        success: function (res) {  
	            serverId = res.serverId;
	            console.log(serverId);
	            $("#serverId").val(serverId);
	            /*
	            $.ajax({  
	                url: "/uploadImg",  
	                dataType: "json",  
	                async: false,  
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
	                data: {"mediaId": serverId},  
	                type: "POST",  
	                timeout: 30000,  
	                success: function (data, textStatus) {  
	                    $('#imgUrl').val(data);  
	                    $.toast('上传成功', 'text');  
	                },  
	                error: function (XMLHttpRequest, textStatus, errorThrown) {  
	                    $.toast('上传错误,请稍候重试!', 'text');  
	                }  
	            });  */
	        },  
	        fail: function (error) {  
	            $.toast('上传错误,请稍候重试!', 'text');  
	        }  
	    });  
	}  
    
  function checkapi(){
	  wx.checkJsApi({
		    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
		    success: function(res) {
		    // 以键值对的形式返回，可用的api值true，不可用为false
		    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
		    	alert(res);
		    }
		});
  }
	
  	/*
    wx.ready(function () {
    	wx.chooseImage({
    		count: 1, // 默认9
    		sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
    		sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
    		success: function (res) {
    		var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
    		uploadImg(localIds[0]); 
    		}
    		});	
    }*/
    </script>
</head>
<body>
	<form id="i-form" name="from-name" enctype="multipart/form-data" method="post">
		<input type="hidden" id="openId" name="openId" value="${openId}">
		<input type="hidden" id="topicId" name="topicId" value="">
		<input type="hidden" id="result" name="result" value="">
		<input type="hidden" id="serverId" name="serverId" value="">
	</form>
    <div class="sel_addr">
        <span class="left manage_goback" onclick="javascript:history.go(-1)"><i></i></span>
        <h1 class="addr_title2">
            业主表决</h1>
    </div>
    <ul class="addr_add" style="margin-top: 50px;">
     <li class="select1">关于城市家园小区的维修方案 </li>
       <li class="input"><span class="tit">表决日期</span> <span class="valtxt">2018-1-1 ~ 2018-4-1</span>
            </li>
        <li class="input"><span class="tit">方案详情</span> <span class="valtxtbz">
            详情详情详情详情详情详情详情详情详情</span> </li>
        <li class="select"><span class="tit">附件上传</span> <span class="valan" id="uploadBtn"><a href="javascript:;" class="file">证件上传
		</a>
           </span> </li>
        	<li class="select"><span class="tit">业主意见</span> 
        	<span class="val">
           	<div style="margin:8px;">
           		<label class="demo--label"><input class="demo--radio" value="01" type="radio" name="result" onclick="check(this.value)"><span class="demo--checkbox demo--radioInput"></span>同意</label>
                <label class="demo--label"><input class="demo--radio" value="02" type="radio" name="result" onclick="check(this.value)"><span class="demo--checkbox demo--radioInput"></span>不同意</label>
         	</div>
    		</span>
     		</li>
    </ul>
    <div style="font-weight:bold; color:Red; margin-left:20px; margin-top:10px">注：请在规定时间内表决</div>
    <div class="btn_area">
        <a id="loginSubmit"><span class="btn_type1" id="btnSubmit" onclick="voteSubmit()">确定</span></a>
    </div>
</body>
</html>