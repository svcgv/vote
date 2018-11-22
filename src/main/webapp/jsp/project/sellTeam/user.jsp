<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.sellTeam-form-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="sellTeam-form-wrapper">
	<form class="layui-form" id="user-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">用户编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="usrNo"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >用户名：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="usrName"  autocomplete="off" class="layui-input form-control" >
	      </div>
	    </div>
	    
 	   <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="userQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		  </div>
	   </div>
	    
	  </div>
	</form>
	<table class="layui-hide" id="userTable" lay-filter="custom" style="overflow:hidden;"></table>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">
	var orgNo="${orgId}";
$(function(){
    var testData = [];
	function getParam(){
		var queryParams=$("#user-query-form").serializeObject();
        queryParams=$.extend({},true,queryParams,{orgNo:orgNo});
		 var newParam = {}
		  for(var i in queryParams){
			  if(queryParams[i]){
				  newParam[i] = queryParams[i]
			  }
		  }
        newParam ={"queryStr":JSON.stringify(newParam)};
		  return newParam
	}
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;

  // table render
    $.ajax({
        type: 'POST',
        url: '/vote/queryusrinfo/list',
        data: JSON.stringify(getParam()),
        contentType:'application/json',
        success: function(res){
            testData=res.page;
            table.render({
                elem: '#userTable',
                id:'user-table',
                height:'260',
                width:"690",
                title: '用户数据表',
                cols: [[
                    {type: 'checkbox' },
                    {field:'usrId', title:'用户账号', sort: true,templet:function(d){
                        var jsonStr = JSON.stringify({"usrId":d.usrId,"usrName":d.usrName});
                        return '<div class="jsonData" dataStr='+jsonStr+'>'+d.usrId+'</div>'
                    } },
                    {field:'usrNo', title:'用户编号(博彦)'},
                    {field:'usrName', title:'用户名'}
                ]],
                data:testData,
                page: true
            });
        },
        dataType: "json"
    });


//复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
    var ids=[];
    table.on('checkbox(custom)', function (obj) {
        var tempArray=[];
        var count=0;
        var tempId;
        $(".sellTeam-form-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
            var dataStr=$(this).children("td").eq(1).find(".jsonData").attr("dataStr");
            var chk=$(this).find(".laytable-cell-checkbox");
            var obj=JSON.parse(dataStr);
            var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
            if(!isChecked){
                count=count+1;
                tempId=obj.usrId;
            }
            tempArray.push(obj)
        })
        console.log(obj,'click');
        console.log(obj.checked,'是否选中');
        if(obj.checked==true){
            if(obj.type=='one' && count!=0){// 选中一个
                ids.push(obj.data.usrId);
                console.log(ids);
            }else{ // 全选
                for(var i=0;i<tempArray.length;i++){
                    if($.inArray(tempArray[i].usrId, ids) == -1) {
                        ids.push(tempArray[i].usrId);
                    }
                }
                console.log(ids);
            }
        }else{
            if(obj.type=='one'){ // 取消选中的
                if(count==1){
                    for(var i=0;i<ids.length;i++){
                        if(ids[i]==tempId){
                            ids.remove(i);
                        }
                    }
                }else{
                    for(var i=0;i<ids.length;i++){
                        if(ids[i]==obj.data.usrId){
                            ids.remove(i);
                        }
                    }
                }
                console.log(ids);
            }else{// 取消全选
                //这方法也能返回数组下标
                //i = $.inArray(tempArray[j].projectId, ids);
                for(var j=0;j<tempArray.length;j++){
                    if($.inArray(tempArray[j].usrId, ids) != -1){
                        ids.remove($.inArray(tempArray[j].usrId, ids));
                    }
                }
                console.log(ids);
            }
        }
    });
    Array.prototype.remove=function(dx)
    {
        if(isNaN(dx)||dx>this.length){return false;}
        for(var i=0,n=0;i<this.length;i++)
        {
            if(this[i]!=this[dx])
            {
                this[n++]=this[i]
            }
        }
        this.length-=1
    }
	
	
	// 保存 事件
	var win=$(".sellTeam-form-wrapper").getWindow();
  	var getExitUser=$("#chosed-user-hook");
	$(".sellTeam-form-wrapper").on("click","#save-hook",function(){
		var ret=[];
		getExitUser.children(".customer-list").each(function(){
			var usrId2=$(this).children(".customerItem").attr("userId");
            var usrName2=$(this).children(".customerItem").attr("userName");
			ret.push({"usrId":usrId2,"usrName":usrName2})
		});
		// 遍历选中的CheckBox
        var tempArray=[];
        for(var i=0;i<ids.length;i++){
            for(var j=0;j<testData.length;j++){
                if(ids[i]==testData[j].usrId){
                    tempArray.push(testData[j]);
                }
            }
        }


        for(var k=0;k<tempArray.length;k++){
            var flag= true;
            for(var j in ret){
                var proId=ret[j].usrId;
                if(tempArray[k].usrId == proId){
                    flag= false;
                }
            }
            if(flag){
                var _html = '<span class="customer-list">'
                    +'<span class="customerItem" userId="'+tempArray[k].usrId+'" userName="'+tempArray[k].usrName+'">'+tempArray[k].usrName+'</span>'
                    +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
                    +'</span>';
                getExitUser.append(_html);
            }
        }
//        for(var k=0;k<tempArray.length;k++){
//            if($.inArray(tempArray[k].usrId,ret) == -1 ){
//                var _html = '<span class="customer-list">'
//                    +'<span class="customerItem" userId="'+tempArray[k].usrId+'" userName="'+tempArray[k].userName+'">'+tempArray[k].userName+'</span>'
//                    +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
//                    +'</span>';
//                getExitUser.append(_html);
//            }
//        }
		// 遍历选中的CheckBox
//		$(".sellTeam-form-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
//			var chk=$(this).find(".laytable-cell-checkbox");
//			var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
//			if(isChecked){
//				var userId=$(this).children("td").eq(1).text();
//				var usrName=$(this).children("td").eq(3).text();
//				// 遍历不存在的插入
//
//				if($.inArray(userId,ret) == -1 ){
//					var _html = '<span class="customer-list">'
//			         	      +'<span class="customerItem" userId="'+userId+'" userName="'+usrName+'">'+usrName+'</span>'
//			               	  +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
//			         		  +'</span>';
//			         		 getExitUser.append(_html);
//				}
//
//
//			}
//		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".sellTeam-form-wrapper").getWindow();
	$(".sellTeam-form-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#user-query-form #userQuery").click(function(){
        $.ajax({
            type: 'POST',
            url: '/vote/queryusrinfo/list',
            data: JSON.stringify(getParam()),
            contentType:'application/json',
            success: function(res){
                testData=res.page;
                table.render({
                    elem: '#userTable',
                    //url:'custom.json',
                    height:'260',
                    width:"690",
                    title: '用户数据表',
                    cols: [[
                        {type: 'checkbox' },
                        {field:'usrId', title:'用户账号', sort: true},
                        {field:'usrNo', title:'用户编号(博彦)'},
                        {field:'usrName', title:'用户名'}
                    ]],
                    data:testData,
                    page: true
                });
                ;},
            dataType: "json"
        })


        /*table.reload('customerGroup-table',{
            url:'form',
            page:{
                curr:1 //从第一页开始
            },
            method:'post',
            where:{
                queryStr:queryParams
            },
            done:function(res){
                console.log(res)
            }

        })*/
		 /*table.reload('user-table',{
				url:'/vote/queryusrinfo/list',
				page:{
					curr:1 //从第一页开始
				},
			    method:'post',
				where:{
					queryStr:JSON.stringify(getParam())
				},
				contentType: 'application/json',
			    response: {
			    	dataName: 'page'
			    },
				done:function(res){
					console.log(res)
				}

			})*/
	});
	
});
	
});

var testData = []

</script>



