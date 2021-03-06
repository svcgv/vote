<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body>
<script src="${ctx }/common/queryCode?productType=PRODUCT_TYPE" type="text/javascript"></script>
<style>
.layui-form-label{width:110px!important;padding:8px 5px;}
</style>
<div class="product-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>产品管理</legend>
	</fieldset>
	<form class="layui-form" id="product-index-form" method="POST" action="">
	   <div class="layui-form-item">
	   
		  	<div class="layui-inline">
		      <label class="layui-form-label">产品名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="productName"  autocomplete="off" class="layui-input form-control">
		      </div>
		       <span class="f-placeholder"></span>
		    </div>

		   <div class="layui-inline">
			   <label class="layui-form-label">产品类型：</label>
			   <div class="layui-input-inline">
				   <select name="productType" lay-verify="required" lay-filter="" class="form-control">
					   ${productType.ewTypeHtml }
				   </select>
			   </div>
			   <span class="f-placeholder"></span>
		   </div>

		    <div class="layui-inline">
		      <label class="layui-form-label">产品代码：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="productCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    <%--<div class="layui-inline">--%>
		      <%--<label class="layui-form-label">指导销售价(元)：</label>--%>
		       <%--<div class="layui-input-inline">--%>
		         <%--<input type="text" name="productSuggestPrice"  autocomplete="off" class="layui-input form-control">--%>
		      <%--</div>--%>
		      <%--<span class="f-placeholder"></span>--%>
		    <%--</div>--%>
		   
		     <%--<div class="layui-inline">--%>
		      <%--<label class="layui-form-label">开始销售日期：</label>--%>
		       <%--<div class="layui-input-inline">--%>
		         <%--<input type="text" name="startSaleDate" id="startSaleDate" autocomplete="off" class="layui-input form-control hasDatepicker">--%>
		      <%--</div>--%>
		    <%--</div>--%>
		    
		     <div class="layui-inline">
		      	<label class="layui-form-label">研发部门：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="developmentDeptName" readonly="readonly"  autocomplete="off" class="layui-input form-control">
			          <input type="text" style='display:none' name="developmentDeptId">
			       </div>
	      	 	<button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
	        </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">研发负责人：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="developmentManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control">
		          <input type="text" style='display:none' name="developmentManagerId">
		      </div>
				 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">是否有效：</label>
		      <div class="layui-input-inline">
		        <select name="isDelete" lay-verify="required" lay-filter="" class="form-control">
		        	 ${isUseful.ewTypeHtml }
		        </select>
		      </div>
		       <span class="f-placeholder"></span>
		    </div>
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  power = "108701" style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			  </div>
		   </div>
	   </div>
	</form>
	<table class="layui-hide" id="productTable" lay-filter="custom"></table>
	
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" power = "108702" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" power = "108703" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" power = "108704" lay-event="del">删除</a>
</script>

<script type="text/javascript">

function getParam(){
	var queryParams=$("#product-index-form").serializeObject();
	 var newParam = {}
	  for(var i in queryParams){
		  if(queryParams[i]){
			  newParam[i] = queryParams[i]
		  }
	  }
	  return newParam
}

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  
  //日期
	  laydate.render({
		    elem: "#startSaleDate",
		    theme: 'molv',
		    type: 'datetime'
	 });
  // 选择机构
  $(".product-info-wrapper #orgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=index',
	  		title:"选择研发机构",
	  		width:"400"
	 });
	  
  });
  
  // 选择人员
  $(".product-info-wrapper #userQuery-hook").on("click",function(){
      var par = getParam()
      if(!par.developmentDeptId){
          layer.msg("请输入请选择研发部门");
          return
      }
	  	$.openWindow({
	  		url:'user?act=index&orgId='+par.developmentDeptId,
	  		title:"选择研发人员",
	  		width:"700"
	 	 });
	});
  
  

  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#productTable',
	    url:'/vote/pmproductinfo/list',
	    method:'post',
		where:{
			queryStr:JSON.stringify(getParam())
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '产品数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'productCode', title:'产品代码',fixed: 'left', width:110, sort: true},
	  	      {field:'productName', title:'产品名称', width:230},
	  	      {field:'productSuggestPrice', title:'指导销售价（元）', width:230,templet:function (d) {
                  var value=typeof d.productSuggestPrice =="undefined" ? '':d.productSuggestPrice;
                  value=(parseInt(value*100)/100).toFixed(2);
				  return value
              }},
	  	      {field:'developmentDeptName', title:'研发部门名称', width:230},
	  	      {field:'developmentManagerName', title:'研发负责人名称'},
	  	      {field:'startSaleDate', title:'开始销售日期'},
	  	      {field:'productType', title:'产品类型',templet:function(d){
                  return getCodeValue(d.productType,productType);
              }},
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
	    ]],
	    cellMinWidth:'90',
	    page: true    ,
        done:function(){
            $.buttonAuthority();
        }
	  });
	/*
	* 监听头工具栏事件 
	*/
	table.on('toolbar(custom)', function(obj){
	  var checkStatus = table.checkStatus(obj.config.companyCode)
	  ,data = checkStatus.data; //获取选中的数据
	  switch(obj.event){
	   
	    case 'deleteData': //删除操作
	      if(data.length === 0){
	        layer.msg('请选择一行');
	      } else {
	    	  console.log(checkStatus.data)
	        layer.alert('删除：'+ checkStatus.data);
	      }
	    break;
	  };
	});
	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	      layer.confirm('确认删除行么', function(index){
	    	  $.ajax({
				  type:"POST",
				  url:"/vote/pmproductinfo/update",
				  data:JSON.stringify({'productId':data.productId,'isDelete':'01'}),
				  contentType:'application/json',
				  success:function(data){
					 
					  table.reload('customer-table');
					 
				  }
			  }); 
	        obj.del();
	        layer.close(index);
	        table.reload('customer-table',{
	        	
	        });
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.productId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.productId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#customQuery").click(function(){
		 
		 table.reload('customer-table',{
				url:'/vote/pmproductinfo/list',
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

		 /*$.ajax({
		 			  type: 'POST',
		 			  url: '/vote/pmproductinfo/list',
		 			  data: JSON.stringify(newparam),
		 			  contentType:'application/json',
		 			  success: function(res){
		 			      console.log(res)
		 			      testData=res.page
		 			     console.log(res)
					      testData=res.page
					      table.render({
					  	  	id:"customer-table",
					  	    elem: '#productTable',
					  	    //url:'custom.json',
					  	    toolbar: '#toolbarDemo',
					  	    height:'full-250',
					  	    title: '销售数据表',
					  	    cols: [[
								  {type: 'checkbox', fixed: 'left'},
								  {field:'productCode', title:'产品代码',fixed: 'left', width:110, sort: true},
								  {field:'productName', title:'产品名称', width:230},
								  {field:'productSuggestPrice', title:'指导销售价', width:230},
								  {field:'developmentDeptName', title:'研发部门名称', width:230},
								  {field:'developmentManagerName', title:'研发负责人名称'},
								  {field:'startSaleDate', title:'开始销售日期'},
								  {field:'productType', title:'产品类型'},
						  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
					  	    ]],
					  	    cellMinWidth:'90',
					  	    data:testData,
					  	    page: true
					  	  	});},
		 			  dataType: "json"
		 			})*/

		
	}); 
	
	/*
	* 新增
	*/
	$(".product-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增产品信息",
	  		width:"90%"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改产品信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看产品信息";
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:"90%"
	  	})
		
	}
	
});
var testData=[];
</script>
</body>
</html>