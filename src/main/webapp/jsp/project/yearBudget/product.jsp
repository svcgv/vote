<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
.budget-cust-wrapper .layui-form-label{width:80px!important;}
</style>
<div style="margin-top:10px;" class="budget-cust-wrapper">
<c:if test="${act !='viewProduct'}">
	<form class="layui-form" id="customer-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">产品编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="productCode"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label" >产品名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="productName"  autocomplete="off" class="layui-input form-control" >
	      </div>
	    </div>
	    
 	   <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="wbsQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		  </div>
	   </div>
	    
	  </div>
	</form>
</c:if>
	<table class="layui-hide" id="productTable" lay-filter="product" style="overflow:hidden;"></table>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>
<c:if test="${act =='viewProduct'}">
	<script type="text/html" id="bar-product">
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
</c:if>
<script type="text/javascript">
$(function(){
//一般直接写在一个js文件中
var act="${act}";// 区分 选择 和查看
var cols= [[
	  {type: 'checkbox'},
	  {field:'productCode', title:'产品代码', width:110, sort: true},
	  {field:'productName', title:'产品名称', width:190},
	  {field:'productType', title:'产品类型', width:150},
  ]]
  
  function query(){
	var queryParams=$("#customer-query-form").serializeObject();
	$.ajax({
		  type: 'POST',
		  url: '/vote/pmproductinfo/list',
		  data: JSON.stringify(queryParams),
		  contentType:'application/json',
		  success: function(res){
		      console.log(res)
		      getData=res.page
		     
		      a.table.render({
		    	elem: '#productTable',
		  	    id:"productCode",
		  	    height:'full-250',
		  	    width:"520",
		  	    title: '',
		  	    cols: cols,
		  	    cellMinWidth:'90',
		  	    data:getData,
		  	    page: false
		  	  	});},
		  dataType: "json"
		})
}
var getData=[];

console.log(getData,'初始化数据')
var a = layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
	  
  // table render
  table.render({
	    elem: '#productTable',
	    id:"productCode",
	    //url:'custom.json',
	    height:'260',
	    width:"500",
	    title: '产品数据表',
	    cols: cols,
	    data:getData,
	    page: false
	  });
	// 保存 事件
	var index ="${index}";
	var YIndex ="${YIndex}";
	var win=$(".budget-cust-wrapper").getWindow();
	// 获取 form页 当前所有已选的产品
		var target=$(".budget-wrapper").find("tbody tr").eq(YIndex).find(".project-list").eq(4).children(".productItem").eq(index);
		var oldProducts= target.find("button.productQuery-hook").nextAll(".layui-badge");
		var oldRet=[];
		for(var i=0;i<oldProducts.length;i++){
			var pid=oldProducts.eq(i).attr("productCode");
			oldRet.push(pid)
		}
	$(".budget-cust-wrapper").on("click","#save-hook",function(){
		if(act == "formProduct"){
				var _html="";
				$(".budget-cust-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
					var chk=$(this).find(".laytable-cell-checkbox");
					var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
					if(isChecked){
						var productCode=$(this).children("td").eq(1).text();
						var productName=$(this).children("td").eq(2).text();
						
							console.log('productCode',productCode)
								console.log('productName',$(this).children("td").eq(0).text())
							if($.inArray(productCode,oldRet) == -1){
								_html += '<span class="layui-badge layui-bg-gray" productCode="'+productCode+'" style="margin-right:5px;">'+productName+'</span>';
							}
					}
				});
				//第4列 产品列表
				console.log(YIndex,index,'index')
				
				target.append(_html);
			}
		win.close();
	});
	
	// 关闭按钮
	var win=$(".budget-cust-wrapper").getWindow();
	$(".budget-cust-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	/*
	* 客户查询按钮
	*/
	$("#customer-query-form #wbsQuery").click(function(){
		
		query()
	});
	query()
});
	
});



</script>



