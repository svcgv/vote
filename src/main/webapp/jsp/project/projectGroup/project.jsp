<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.projectGroup-project-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="projectGroup-project-wrapper">
	<form class="layui-form" id="project-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">项目编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="projectId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >项目名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="projectName"  autocomplete="off" class="layui-input form-control" >
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
	<table class="layui-hide" id="projectTable-all" lay-filter="projectTableFilter" style="overflow:hidden;"></table>
 	<div id="project-laypage"></div>
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">
$(function(){
	var testData=[
			  		{"projectId":"1000","projectName":"雪松-软件研发外包战略供应商招标"},
					{"projectId":"1001","projectName":"深圳数位传媒科技有限公司IT人力外包"},
					{"projectId":"10102","projectName":"信贷及相关系统技术服务项目"},
					{"projectId":"10023","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10as03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10AS03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10ASDA03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10ASA03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10SD03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10ASDSA03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10ASD03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"100ASAS3","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10AAASA03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"1ZXC00ZX3","projectName":"新华保险人力外包服务项目"},
					{"projectId":"100AWQWEQ3","projectName":"新华保险人力外包服务项目"},
					{"projectId":"100AA3","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10ASDASD03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"100ASDASD3","projectName":"新华保险人力外包服务项目"},
					{"projectId":"100ADQEQW3","projectName":"新华保险人力外包服务项目"},
					{"projectId":"100QEQE3","projectName":"新华保险人力外包服务项目"},
					{"projectId":"10ASADAAD03","projectName":"新华保险人力外包服务项目"},
					{"projectId":"AQQQQ","projectName":"新华保险人力外包服务项目"},
					{"projectId":"WEQWEQSDSDDS","projectName":"新华保险人力外包服务项目"},
					
				]
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','laypage'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
  var laypage = layui.laypage;
	 
  var queryParams=$("#project-query-form").serializeObject();
  // table render
  table.render({
	    elem: '#projectTable-all',
	   // url:'/vote/pmcustomerinfo/list',
	    height:'260',
	    width:"690",
	    title: '项目表信息',
	    response: {
	    	dataName: 'page'
	    },
	    where:{
			queryStr:JSON.stringify(queryParams)
		},
	    method:'POST',
	    contentType: 'application/json',
	    cols: [[
	      {type: 'checkbox' },
	      {field:'projectId', title:'项目编号', templet:function(d){
	    	  var jsonStr = JSON.stringify(d);
	    	  return '<div class="jsonData" dataStr='+jsonStr+'>'+d.projectId+'</div>'
	      } },
	      {field:'projectName', title:'项目名称'}
	    ]],
	    data:testData,
	    page: true,
	    done:function(res,curr,count){
	    }
	  });
  	
       //复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
       var ids=[];
        table.on('checkbox(projectTableFilter)', function (obj) {
            var tempArray=[];
            var count=0;
            var tempId;
            $(".projectGroup-project-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
                var dataStr=$(this).children("td").eq(1).find(".jsonData").attr("dataStr");
                var chk=$(this).find(".laytable-cell-checkbox");
                var obj=JSON.parse(dataStr);
                var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
                if(!isChecked){
                    count=count+1;
                    tempId=obj.projectId;
				}
                tempArray.push(obj)
            })
        	console.log(obj,'click');
        	console.log(obj.checked,'是否选中');
           if(obj.checked==true){
               if(obj.type=='one' && count!=0){// 选中一个
                   ids.push(obj.data.projectId);
                   console.log(ids);
              }else{ // 全选
                   for(var i=0;i<tempArray.length;i++){
                       if($.inArray(tempArray[i].projectId, ids) == -1) {
                           ids.push(tempArray[i].projectId);
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
                           if(ids[i]==obj.data.projectId){
                               ids.remove(i);
                           }
                       }
				   }
                   console.log(ids);
               }else{// 取消全选
				   //这方法也能返回数组下标
                   //i = $.inArray(tempArray[j].projectId, ids);
                   for(var i=0;i<ids.length;i++){
                       for(var j=0;j<tempArray.length;j++){
                           if(ids[i]==tempArray[j].projectId){
                              ids.remove(i);
                          }
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
  	
  
  
  	var act="${act}";
	var win=$(".projectGroup-project-wrapper").getWindow();
	$(".projectGroup-project-wrapper").on("click","#save-hook",function(){
		// 遍历选中的CheckBox
		$(".projectGroup-project-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
            console.log(ids);
			var chk=$(this).find(".laytable-cell-checkbox");
			var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
			if(isChecked){
				var dataStr=$(this).children("td").eq(1).find(".jsonData").attr("dataStr");
				var obj=JSON.parse(dataStr);
				// 去重 已选的项目
				var flag= true;
				for(var i in chosedProject){
					var proId=chosedProject[i].projectId;
					if(obj.projectId == proId){
						flag= false;
						continue;
					}
				}
				if(flag){
					chosedProject.push({
						"projectId":obj.projectId,
						"projectName":obj.projectName
					});
				}
			}
		});
		console.log(chosedProject)
		chosedLayTable.reload('table-chosedProject',{
					data:chosedProject
			})
		win.close();
	});
	
	// 关闭按钮
	var win=$(".projectGroup-project-wrapper").getWindow();
	$(".projectGroup-project-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#project-query-form #userQuery").click(function(){
		
		var queryParams=$("#project-query-form").serialize();
		console.log(queryParams)
		table.reload('customerGroup-table',{
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
			
		})
	});
	
});
	
});



</script>



