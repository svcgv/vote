<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
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
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">产品代码：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="productCode"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">指导销售价：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="productSuggestPrice"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">产品类型：</label>
		      <div class="layui-input-inline">
		        <select name="productType" lay-verify="required" lay-filter="" class="form-control">
		        	 ${productType.ewTypeHtml }
		        </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">开始销售日期：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="startSaleDate" id="startSaleDate" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">研发部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="developmentDeptName" readonly="readonly"  autocomplete="off" class="layui-input form-control">
		          <input type="hidden" name="developmentDeptId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">研发负责人：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="developmentManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control">
		          <input type="hidden" name="developmentManagerId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:25px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			     
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
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript">

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
	  	$.openWindow({
	  		url:'user?act=index',
	  		title:"选择研发人员",
	  		width:"700"
	 	 });
	});
  
  

  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#productTable',
	    //url:'custom.json',
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '产品数据表',
	    cols: [[
	    	  //{type: 'checkbox', fixed: 'left'},
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
	    data:[
              {"productCode":1,"productName":"产品1","productSuggestPrice":"200","developmentDeptName":"开发一部","developmentManagerName":"asdas","startSaleDate":"2018-11-01","productType":"01"},
              {"productCode":2,"productName":"产品2","productSuggestPrice":"200","developmentDeptName":"开发二部","developmentManagerName":"asdas","startSaleDate":"2018-11-01","productType":"01"},
              {"productCode":3,"productName":"产品3","productSuggestPrice":"200","developmentDeptName":"开发三部","developmentManagerName":"asdas","startSaleDate":"2018-11-01","productType":"01"},
              {"productCode":4,"productName":"产品4","productSuggestPrice":"200","developmentDeptName":"开发四部","developmentManagerName":"asdas","startSaleDate":"2018-11-01","productType":"01"},
       		],
	    page: true
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
	        obj.del();
	        layer.close(index);
	        table.reload('customer-table',{
	        	
	        });
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.groupCode);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.groupCode);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#customQuery").click(function(){
		 var queryParams=$("#product-index-form").serialize();
		 $.ajax({
			  type: 'POST',
			  url: '/vote/pmcompanyinfo/list',
			  data: queryParams,
			  contentType:'application/json',
			  success: function(res){
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
						  //{type: 'checkbox', fixed: 'left'},
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
			  	    data:[
						{"groupCode":44,"groupName":"销售team1","ownerOrgId":"销售一部"},
						{"groupCode":55,"groupName":"销售team1","ownerOrgId":"销售一部"},
						{"groupCode":66,"groupName":"销售team1","ownerOrgId":"销售一部"}
			       	],
			  	    page: true
			  	  	});},
			  dataType: "json"
			});
		//var queryParams=$("#product-index-form").serialize();
		/* table.reload('customer-table',{
			url:'/vote/bmcustomerinfo/list',
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
			
		}) */
		
	}); 
	
	/*
	* 新增
	*/
	$(".product-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增产品信息",
	  		width:"720"
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
	  		width:"700"
	  	})
		
	}
	
});
var testData=null;
</script>
</body>
</html>