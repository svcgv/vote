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
    <title>表决查看</title>
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/common.css">
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/address.css?1=1">
 <style>
    .demo--label{margin:0;display:inline-block; margin-right:8px}
.demo--radio{display:none}
.demo--radioInput{background-color:#fff;border:1px solid rgba(0,0,0,0.15);border-radius:100%;display:inline-block;height:16px;margin-right:10px;margin-top:-1px;vertical-align:middle;width:16px;line-height:1}
.demo--radio:checked + .demo--radioInput:after{background-color:#57ad68;border-radius:100%;content:"";display:inline-block;height:12px;margin:2px;width:12px}
.demo--checkbox.demo--radioInput,.demo--radio:checked + .demo--checkbox.demo--radioInput:after{border-radius:0}
    </style>
    
    <script type="text/javascript">
    $(document).ready(function(){
    	var map = '${map}';
    	var openId = '${openId}';
    	var jsonObj = JSON.parse(map);
    	var topicInfo = jsonObj.topic;
    	var result = jsonObj.result;
    	//console.log(map);
    	console.log(openId);
    	console.log(topicInfo);
    	console.log(result);
    	
    	$(".select1").text(topicInfo.topicName);
    	$(".valtxt").text(topicInfo.voteStartDate+"~"+topicInfo.voteEndDate);
    	$(".valtxtbz").text(topicInfo.topicContent);
    	$("#topicId").val(topicInfo.topicId);
    	
    	
    	if(result == "01"){
    		console.log("hello");
    		$("input[type='radio'][value='01']").attr("checked",true); 
    		$("#btnSubmit").text("已投票:同意");
    	}else if(result == "02"){
    		console.log("world");
    		$("input[type='radio'][value='02']").attr("checked",true); 
    		$("#btnSubmit").text("已投票:不同意");
    	}
    	
    	//console.log($("ul").html());
    });
    
    function check(value){
    	$('#result').val(value);
    }
    </script>
</head>
<body>
  <div class="sel_addr">
        <span class="left manage_goback" onclick="javascript:history.go(-1)"><i></i></span>
        <h1 class="addr_title2">
            表决查看</h1>
    </div>
    <ul class="addr_add" style="margin-top: 50px;">
     <li class="select1">关于城市家园小区的维修方案 </li>
       <li class="input"><span class="tit">表决日期</span> <span class="valtxt">2018-1-1 ~ 2018-4-1</span>
            </li>
        <li class="input"><span class="tit">方案详情</span> <span class="valtxtbz">
            详情详情详情详情详情详情详情详情详情</span> </li>
        <li class="select"><span class="tit">业主意见</span> <span class="val">
           <div style="margin:8px;">
           		<label class="demo--label"><input class="demo--radio" type="radio" name="t_xuanx1" value="01" readonly="readonly"><span class="demo--checkbox demo--radioInput"></span>同意</label>
                <label class="demo--label"><input class="demo--radio" type="radio" name="t_xuanx1" value="02" readonly="readonly"><span class="demo--checkbox demo--radioInput"></span>不同意</label>
           </div>
      </span> </li>
    </ul>
    <div class="btn_area">
        <a id="loginSubmit"><span class="btn_type1" id="btnSubmit">已投票:不同意</span></a>
    </div>
</body>
</html>
