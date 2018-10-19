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
    <title>我的维修资金</title>
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/common.css">
    <link rel="stylesheet" href="${ctx }/resources/WeChat/css/address1.css?1=1">
</head>
<body>
<div class="sel_addr">
        <span class="left manage_goback" onclick="javascript:history.go(-1)"><i></i></span>
        <h1 class="addr_title2">
            我的维修资金</h1>
    </div>
    <ul class="addr_add" style="margin-top: 50px;">
    
       <li class="input"><span class="tit">房屋地址</span> <span class="valtxt">姚港路26号101室</span>
            </li>
        <li class="input"><span class="tit">房屋面积</span> <span class="valtxt">100</span>
            </li>
              <li class="input"><span class="tit">账户余额</span> <span class="valtxt">21231.11元</span>
            </li>
       
       <li class="select1">资金记录 </li>
       
    </ul>
    <div class="myaddr_list select"  >
       

        <ul>
        
            <li>
               <div>交款</div>
                <div class="tel">100元</div>
                <div class="addr_txt3">2018-2-1</div>
            </li>
        <li>
               <div>支取</div>
                <div class="tel">100元</div>
                <div class="addr_txt3">2018-2-1</div>
            </li>
            
        </ul>


    </div>
</body>
</html>