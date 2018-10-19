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
     <title>我的房屋</title>
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/common.css">
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/address.css">
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	getSignature();
    	var listString = '${list}';
    	var sectInfos = '{"list":'+listString+'}';
    	var openId = '${openId}';
    	console.log(sectInfos);
    	var jsonObj = JSON.parse(sectInfos);
		var data = jsonObj.list; 
		//console.log("hello");
		if (data.length == 0){
			alert("您还没有绑定房屋信息，请绑定！");
			return;
		}
		for (var i=0; i < data.length; i++)
		{
			var sectInfo = data[i];
			//$("ul").append('<option value="' + data[i].sectName +'">'+ data[i].sectName +'</option>');
			$("ul").append('<li><div>'+data[i].SECT_NAME+'</div><div class="default_addr fixclear">'
					+'<input type="hidden" name="infoId" value="'+data[i].INFO_ID+'">'
					+'<input type="hidden" name="openId" value="'+openId+'">'
					+'<div class="default f-l"><span class="red">'+data[i].BUILD_CODE+'栋'+data[i].UNIT_CODE
					+'单元'+data[i].FLOOR_CODE+'层'+data[i].ROOM_CODE+'室'+'</span></div><div class="operate f-r fixclear">'
					+'<div class="del f-r"><i class="icon_del">&nbsp;</i><span onclick="unbindHouse(\''
					+data[i].INFO_ID+'\',\''+openId+'\')">删除</span></div></div>');
		}
		console.log($("ul").html());
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
    
    function unbindHouse(infoId, openId){
    	console.log("unbindHouse");
    	console.log(infoId);
    	console.log(openId);
    	/*
    	var infoId = $(this).parents(".default_addr").children("input").val();
    	console.log($(this).parent().html());
    	console.log($(this).parents(".default_addr fixclear").html());
    	console.log(infoId);*/
    	$.indi.ajaxSubmit({url: "${ctx}/WeChat/House/unbindHouseInfo",
    		data:'{"infoId":"'+infoId+'","openId":"' +openId+'"}',
    		success:function(data){
			if(data.status == true){
				//layer.alert('房屋信息解绑成功！',{icon: 1});
				alert('房屋信息解绑成功 ！');
				wx.closeWindow();//关闭页面
			}else{
				layer.alert('房屋信息解绑失败！',{icon: 2});
			}
			}});
    }
    </script>
</head>
<body>
<div id="ct">
		<form id="i-form" name="from-name" method="post">
			<input type="hidden" id="openId" name="openId" value="${openId}">
		</form>
        <div class="sel_addr">
            <span class="left manage_goback" onclick="javascript:history.go(-1)"><i ></i></span>
            <h1 class="addr_title2">我的房屋</h1>
        </div>
                 <div class="myaddr_list select"   style="margin-top:50px;">          
      	<ul>
      	  	<!-- 
            <li>
                <div>${listInfo}</div>
                <div class="default_addr fixclear">
                	<input type="hidden"  name="infoId" value="">
                    <div class="default f-l">
                            <span class="red">姚港路58号201室</span>
                    </div>
                    <div class="operate f-r fixclear">
                        <div class="del f-r"><i class="icon_del">&nbsp;</i><span onclick="unbindHouse()">删除</span></div>
                    </div>
                </div>
            </li>
            
            <li>
                <div>万科城市花苑</div>
                <div class="default_addr fixclear">
                    <div class="default f-l">
                            <span class="red">姚港路58号201室</span>
                    </div>
                    <div class="operate f-r fixclear">
                        <div class="del f-r"><i class="icon_del">&nbsp;</i><span>删除</span></div>
                    </div>
                </div>
            </li>
             -->
        </ul>
    </div>
 <!-- 
 <div class="btn_area">
        <span class="btn_type1" id="btnSubmit">绑定房屋</span>
    </div>
 -->
</div>
</body>
</html>