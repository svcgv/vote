<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.customGroup-form-wrapper .layui-form-label{width:90px;}
</style>
<div style="margin-top:10px;" class="customGroup-form-wrapper">
	<form class="layui-form" id="formGroup-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">SAP编码：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="sapCode"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >客户名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custCnName"  autocomplete="off" class="layui-input form-control" >
	      </div>
	    </div>
	    
 	   <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		  </div>
	   </div>
	    
	  </div>
	</form>
	<table class="layui-hide" id="customTable" lay-filter="custom" style="overflow:hidden;"></table>
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
  	  tableGroup=layui.table;
	  
  // table render
  tableGroup.render({
	    elem: '#customTable',
	    //url:'custom.json',
	    height:'260',
	    width:"690",
	    title: '客户数据表',
	    cols: [[
	      {type: 'checkbox' },
	      {field:'sapCode', title:'sap编号', sort: true},
	      {field:'custCnName', title:'客户名称'},
	    ]],
	    data:[
		  		{
		  			"sapCode":"1",
					"custCnName":"上海浦东发展银行股份有限公司"
					
				},
				{
					"sapCode":"2",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"33",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"3",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"4",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"232",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"1232",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"123222",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"21231222",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"1111",
					"custCnName":"南京发展银行股份有限公司"
				},
				{
					"sapCode":"123121212",
					"custCnName":"南京发展银行股份有限公司"
				}
			],
	    page: true
	  });
	
  /**
  * chechbox 点击事件
  */
	$(".customGroup-form-wrapper").on("click",".layui-table-body table.layui-table tbody .laytable-cell-checkbox i",function(){
		var isChecked=$(this).parent(".layui-form-checkbox").hasClass("layui-form-checked");
		console.log($(this),isChecked)
	})
	
	// 保存 事件
	var win=$(".customGroup-form-wrapper").getWindow();
  	var getExitCustomer=$("#form-customer-hook #chosed-customer-hook");
	$(".customGroup-form-wrapper").on("click","#save-hook",function(){
		var ret=[];
		getExitCustomer.children(".customer-list").each(function(){
			var sapCode2=$(this).children(".customerItem").attr("sapCode");
			ret.push(sapCode2)
		});
		// 遍历选中的CheckBox
		$(".layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-checkbox");
			var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
			if(isChecked){
				var sapCode=$(this).children("td").eq(1).text();
				var name=$(this).children("td").eq(2).text();
				
				// 遍历不存在的插入
				
				if($.inArray(sapCode,ret) == -1 ){
					var _html = '<span class="customer-list">'
			         	      +'<span class="customerItem" sapCode="'+sapCode+'">'+name+'</span>'
			               	  +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
			         		  +'</span>';
					getExitCustomer.append(_html);	  
				}
				
				
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".customGroup-form-wrapper").getWindow();
	$(".customGroup-form-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#customQuery").click(function(){
		
		var queryParams=$("#customer-query-form").serialize();
		console.log(queryParams)
		tableGroup.reload('customerGroup-table',{
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


