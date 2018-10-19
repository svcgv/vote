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
    <title>已表决清单</title>
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/common.css">
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/addressQC.css">
    
    <script type="text/javascript">
    $(document).ready(function(){
    	var map = '${map}';
    	var openId = '${openId}';
    	var jsonObj = JSON.parse(map);
    	var data = jsonObj.listInfo;
    	//console.log(map);
    	console.log(openId);
    	console.log(data);
    	
    	for(var i=0; i<data.length; i++){
    		//var topicInfo = data[i];
    		$("ul").append('<li onclick="getVotedInfoDetails(\''+openId+'\',\''+data[i].topicId+'\')"><div>'
    				+data[i].topicName+'</div></li>');
    	}
    	$("#unvote").attr("href", "${ctx }/WeChat/vote/changeUnvotedInfo?openId="+openId);
    	console.log($("ul").html());
    });
    
    function getVotedInfoDetails(openId, topicId){
    	console.log(openId);
    	console.log(topicId);
    	/*
    	$.indi.ajaxSubmit({url: "${ctx}/WeChat/vote/getUnvotedInfoDetails",
    		data:'{"topicId":"'+topicId+'","openId":"' +openId+'"}'
			});*/
		$("#topicId").attr("value", topicId);
		$("#i-form").submit();
    }
    </script>
</head>
<body> 
<div id="ct">
	<form id="i-form" name="from-name" method="get" action="${ctx }/WeChat/vote/getVotedInfoDetails">
		<input type="hidden" id="openId" name="openId" value="${openId}">
		<input type="hidden" id="topicId" name="topicId" value="">
	</form>
 	<div class="myaddr_list select">
 	<div class="sel_addr">
       <span class="left1"><a id="unvote" href="3.1.htm">待表决</a></span>
       <span class="right"><a id="vote" href="3.2.htm">已表决</a></span>
       <span class="left manage_goback" onclick="javascript:history.go(-1)"><i></i></span>
     </div>
     </div>
     <div class="myaddr_list select"   style="margin-top:50px;">
        <ul>
            <li>
            </li>
        </ul>
    </div>
</div>
</body>
</html>