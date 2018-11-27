<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.tender-info-wrapper .layui-form-label{width:130px!important;}
</style>
<div class="tender-info-wrapper">
	<table class="layui-hide" id="productTable" lay-filter="custom"></table>
</div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs layui-btn-xs" power ="112401"  lay-event="view">评审</a>
</script>

<script type="text/javascript">

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
    
    function getParam(){
		var queryParams=$("#tender-index-form").serializeObject();
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
	  	id:"customer-table",
	    elem: '#productTable',
	    url:'/vote/pmreviewinfo/selectBidReview',
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
	    title: '投标数据表',
	    cols: cols,
	    cellMinWidth:'90',
	    
	    page: true,
	    done:function(){
	    	$.buttonAuthority();
	    }
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	   
	    if(obj.event === "view"){
	    	// 评审
	    	showFromTable('view',data.bidId);
	    }
	  });
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){

	  if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="投标评审";
		}
	  
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:800
	  	})
		
	}
	
});
var testData=[];
var cols=[[
	  {type: 'checkbox', fixed: 'left'},
	      {field:'bidCode', title:'投标编号',fixed: 'left', sort: true, width:130},
	      {field:'bidName', title:'项目名称', width:130},
  	    {field:'status', title: '评审状态', width: 200
      	      ,templet: function(d){
      	    	if(d.status=='00'){
      	        	return '已录入'
      	        }
      	    	if(d.status=='01'){
      	        	return '交付部门负责人评审'
      	        }
      	    	if(d.status=='02'){
      	        	return '销售部门负责人评审'
      	        }
      	    	if(d.status=='03'){
      	        	return '技术总监评审'
      	        }
      	    	if(d.status=='04'){
      	        	return '评审完成'
      	        }
      	    	else{
      	    		return '数据待完善'
      	    	}
      	      },rowspan: 2
      	    },
	      {field:'firstBidAmount', title:'首次报价（元）', width:150},
	      {field:'custCnName', title:'客户', width:130},
	      {field:'predictAmount', title:'预估合同金额（元）', width:150},
	      {field:'predictCost', title:'预估成本（元）', width:120},
	      {field:'predictProfitRate', title:'预估利润率（%）'},
    {field:'predictPeriodStart', title:'项目开始时间'},
    {field:'predictPeriodEnd', title:'项目结束时间'},
	      {field:'constructionDeptName', title:'交付部门'},
	      {field:'sellDeptName', title:'销售部门'},
	      {field:'projectManagerName', title:'项目经理'},
	      {field:'custManagerName', title:'客户经理'},
	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
]]
</script>
</body>
</html>