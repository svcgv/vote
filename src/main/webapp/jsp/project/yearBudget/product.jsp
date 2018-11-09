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
	         <input type="text" name="productId"  autocomplete="off" class="layui-input form-control">
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
var getData=[
		  		{
		  			"productId":"1000",
					"productName":"交行产品"
					
				},
				{
		  			"productId":"1001",
					"productName":"内网产品"
					
				},
				{
		  			"productId":"1002",
					"productName":"方维产品"
					
				},
				{
		  			"productId":"1003",
					"productName":"兴业银行产品"
					
				}
				
			];
// 获取 form页 当前所有已选的产品
var getExitProducts=$("#budget-addForm-hook #chosedProduct-hook");
if(act == "viewProduct"){ // 查看按钮
	getData=[];
	getExitProducts.children(".customer-list").each(function(){
		var productId=$(this).children(".customerItem").attr("productId");
		var productName=$(this).children(".customerItem").text();
		getData.push({
			"productId":productId,
			"productName":productName
		});
	});
	
}
console.log(getData,'初始化数据')
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
	  
  // table render
  table.render({
	    elem: '#productTable',
	    id:"productID",
	    //url:'custom.json',
	    height:'260',
	    width:"680",
	    title: '产品数据表',
	    cols: [[
	      {type: 'checkbox' },
	      {field:'productId', title:'wbs编号', sort: true},
	      {field:'productName', title:'产品名称'},
	      {align: 'center', title:'操作', toolbar: '#bar-product',width:90}
	    ]],
	    data:getData,
	    page: true
	  });
  if(act == "viewProduct"){
	  // 删除事件
	  table.on('tool(product)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'del'){
		      layer.confirm('确认删除么', function(index){
		    	  console.log(obj,index,'product')
		          obj.del();
		          layer.close(index);
		      });
		    }
	  });
  }
	// 保存 事件
	
	var win=$(".budget-cust-wrapper").getWindow();
	$(".budget-cust-wrapper").on("click","#save-hook",function(){
		var ret=[];
		getExitProducts.children(".customer-list").each(function(){
			var sapCode2=$(this).children(".customerItem").attr("productId");
			ret.push(sapCode2)
		});
		// 遍历选中的CheckBox
		if($(".budget-cust-wrapper .layui-table-body table.layui-table tbody tr").length == 0){
			if(act == "viewProduct"){
				getExitProducts.html(""); 
			}
		}
		$(".budget-cust-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var productId=$(this).children("td").eq(1).text();
			var productName=$(this).children("td").eq(2).text();
			if(act == "viewProduct"){
				console.log(productId,productName)
				
					var _html = '<span class="customer-list">'
		         	      +'<span class="customerItem" productId="'+productId+'">'+productName+'</span>'
		               	  +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
		         		  +'</span>';
		         		  // 替换
		         		 getExitProducts.html(_html); 
				
			}else if(act == "addCust"){
				var chk=$(this).find(".laytable-cell-checkbox");
				var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
				if(isChecked){
					    //编辑 修改 页面
					if($.inArray(productId,ret) == -1 ){
						var _html = '<span class="customer-list">'
				         	      +'<span class="customerItem" productId="'+productId+'">'+productName+'</span>'
				               	  +'<span onclick="$(this).parent().remove()" style="line-height:16px;"><i class="layui-icon layui-icon-close-fill"></i></span>'
				         		  +'</span>';
				         		 getExitProducts.append(_html); 
					}
				}
				
			}
			
			
		});
		
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
		
		var queryParams=$("#customer-query-form").serialize();
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



