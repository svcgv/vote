<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.contract-company-wrapper .layui-form-label{width:100px!important;}
</style>
<div style="margin-top:10px;" class="contract-company-wrapper">
	<form class="layui-form" id="company-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">公司实体编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="companyCode"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >公司实体名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="companyName"  autocomplete="off" class="layui-input form-control" >
			   <input type="text" style="display: none;" name="isDelete" value="00" autocomplete="off" class="layui-input form-control" >
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
	<table class="layui-hide" id="companyTable" lay-filter="company" style="overflow:hidden;"></table>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">
$(function(){
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;

    function getParam(){
        var queryParams=$("#company-query-form").serializeObject();
        var newParam = {}
        for(var i in queryParams){
            if(queryParams[i]){
                newParam[i] = queryParams[i]
            }
        }
        return newParam
    }
  // table render
  table.render({
	    elem: '#companyTable',
	    id:'customerGroup-table',
	    url:'/vote/pmcompanyinfo/list',
	    method:'post',
		where:{
			queryStr:JSON.stringify(getParam())
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    height:'260',
	    width:"690",
	    title: '公司数据表',
	    cols: [[
	      {type: 'radio' },
	      {field:'companyCode', title:'公司实体编号', sort: true},
	      {field:'companyName', title:'公司实体名称'}
	    ]],
	    page: true
	  });
	
	
	// 保存 事件
	var act="${act}";// 区分是index页 form页 赋值问题
	var win=$(".contract-company-wrapper").getWindow();
    $(".contract-company-wrapper").on("click","#save-hook",function(){
        // 遍历选中的radio
        $(".contract-company-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
            var chk=$(this).find(".laytable-cell-radio");
            var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
            if(isChecked){
                var companyCode=$(this).children("td").eq(1).text();
//                var userName=$(this).children("td").eq(2).text();
                if(act == "index"){
                    $("#custom-form input[name='companyCode']").val(companyCode);
//                    $("#contract-index-form input[name='custManagerId']").val(userId);

                }else if(act =="form"){ //编辑 修改 页面
                    $("#custom-form input[name='companyCode']").val(companyCode);
//                    $("#contract-addForm-hook input[name='custManagerId']").val(userId);
                }
            }
        });
        win.close();
    });

	// 关闭按钮
	var win=$(".contract-company-wrapper").getWindow();
	$(".contract-company-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#company-query-form #userQuery").click(function(){

		table.reload('customerGroup-table',{
			url:'/vote/pmcompanyinfo/list',
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
			
		}) 
	});
	
});
	
});



</script>



