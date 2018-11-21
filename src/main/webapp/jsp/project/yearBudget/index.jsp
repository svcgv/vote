<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
</style>
<div class="budget-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>年度销售预算</legend>
	</fieldset>
	<form class="layui-form" id="tender-index-form" method="POST" action="">
	   <div class="layui-form-item">
	   
		  	<div class="layui-inline" style="margin-right: 49px;">
		      <label class="layui-form-label">是否新客户：</label>
		       <div class="layui-input-inline">
		          <select name="isNewCustomer" lay-verify="required" lay-filter="isNewCustomer" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01">是</option>
			        	<option value="02" selected>否</option>
				  </select>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="custName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		         <input type="hidden" name="sapCode" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="customerNameQuery-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-search "></i></button>
		    </div>
	    	<div class="layui-inline" style="margin-right: 49px;">
		      <label class="layui-form-label">是否新项目：</label>
		       <div class="layui-input-inline">
		          <select name="isNewProject" lay-verify="required" id="isNewProject-hook" lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01">是</option>
			        	<option value="02" selected>否</option>
				  </select>
		      </div>
		    </div>
		    	
		     <div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		      <div class="layui-input-inline">
		       	<input type="text" name="projectName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
		       	 <input type="hidden" name="wbs" />
		      </div>
		      <button type="button"  class="layui-btn layui-btn-sm" id="projectNameQuery-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-search "></i></button>
		    </div>
		    
		     <div class="layui-inline" style="margin-right: 49px;">
		      <label class="layui-form-label">项目类型：</label>
		       <div class="layui-input-inline">
		          <select name="projectType" lay-verify="required" lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>项目</option>
			        	<option value="02">产品</option>
			        	<option value="03" >人力</option>
				  </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline" style="margin-right: 49px;">
		      <label class="layui-form-label">税种：</label>
		       <div class="layui-input-inline">
		          <select name="isNewProject" lay-verify="required" lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>A</option>
			        	<option value="02">B</option>
			        	<option value="03" >C</option>
			        	<option value="04" >D</option>
			        	<option value="05" >E</option>
			        	<option value="06" >F</option>
			        	<option value="07" >G</option>
			        	<option value="08" >H</option>
			        	<option value="09" >I</option>
				  </select>
		      </div>
		    </div>
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:25px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>收入上报</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="exportYearBudger"  style="margin-right:15px;"><i class="layui-icon"></i>导出</button>
			    
			  </div>
		   </div>
		   
	   </div>
	</form>
	<table id="budgetTable" lay-filter="budgetIndex"></table>
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript">
var testData=[{
	'isNewCustomer':'是','custName':'交通银行','isNewProject':'是','projectName':'','projectType':'123','productList':'sss','revenueSource':'a','entity':'','contract':'ss','poSow':'','owner':'aaa','taxType':'12%','jan':'一月收入1千万','feb':'一月收入3千万'
		
	}]
var cols = [
    [/* 
  	  {type: 'checkbox', fixed: 'left',rowspan: 2}, */
	      {field:'isNewCustomer', title: '是否新客户', width: 200
      	      ,templet: function(d){
      	    	if(d.sapCode){
      	        	return "否"
      	        }
      	    	else{
      	    		return '是'
      	    	}
      	    	
      	      },rowspan: 2
      	    },
	      {field:'custName', title:'客户名称', width:130,rowspan: 2},
	      {field:'isNewProject', title: '是否新项目', width: 120
      	      ,templet: function(d){
      	    	if(d.wbs){
      	        	return "否"
      	        }
      	    	else{
      	    		return '是'
      	    	}
      	    	
      	      },rowspan: 2
      	    },
	      {field:'wbs', title:'WBS编号', width:150,rowspan: 2},
	      {field:'projectName', title:'项目名称', width:130,rowspan: 2},
	      {field:'projectType', title:'项目类型', width:150,rowspan: 2},
	      {field:'productList', title:'产品列表', width:120,rowspan: 2},
	      {field:'revenueSource', title:'收入来源(Revenue source)',rowspan: 2},
	      {field:'entity', title:'实体(Entity)',rowspan: 2},
	      {field:'contract', title:'合同编码(Contract)',rowspan: 2},
	      {field:'poSow', title:'PO#/SOW#',rowspan: 2},
	      {field:'owner', title:'客户经理(Owner)',rowspan: 2},
	      {field:'taxType', title:'税种',rowspan: 2},
	      {field:'revRecognitionMethod', title:'Rev Recognition Method',rowspan: 2},
	      {field:'region', title:'区域(Region)',rowspan: 2},
	      {field:'currency', title:'结算币种(Currency)',rowspan: 2},
	      {field:'taxRate', title:'税率(%)',rowspan: 2},
	      {field:'grossProfitRate', title:'毛利率(%)',rowspan: 2},
	      {field:'totalRev', title:'Total Rev',rowspan: 2},
	      {field:'afterTax',title:"税后合计",rowspan: 2},
	      {align: 'center', title: 'Revenue', colspan: 12},
     	  {align: 'center',fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
	 ],[
		 {field: 'budgetJan', title: 'Jan'}
		,{field: 'budgetFeb', title: 'Feb'}
		,{field: 'budgetMar', title: 'Mar'}
		,{field: 'budgetApr', title: 'Apr'}
		,{field: 'budgetMay', title: 'May'}
		,{field: 'budgetJun', title: 'Jun'}
		,{field: 'budgetJul', title: 'Jul'}
		,{field: 'budgetAug', title: 'Aug'}
		,{field: 'budgetSep', title: 'Sep'}
		,{field: 'budgetOct', title: 'Oct'}
		,{field: 'budgetNov', title: 'Nov'}
		,{field: 'budgetDec', title: 'Dec'}
]
]
function getParam(){
	var par = $("#tender-index-form").serializeObject();
	var newParam = {}
	for(var key in par){
		if(par[key]){
			newParam[key] = par[key]
		}
	}
	return newParam
}
var exportUrl = '/vote/pmyearbudget/exportExcel'

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  
  //日期
	  laydate.render({
		    elem: "#predictPeriodDate",
		    theme: 'molv',
		    type: 'datetime'
	 });
  
  /*选择新客户 ，则自动选择新项目**/
  form.on('select(isNewCustomer)', function(data){
	  if(data.value == "01"){
		  var select= 'dd[lay-value=01]';
		  $('.budget-info-wrapper #isNewProject-hook').siblings("div.layui-form-select").find('dl').find(select).click();
	  }
  });
  
  
  function queryList(){
		 var queryParams=getParam()
		 console.log(queryParams)
		 
		 $.ajax({
			  type: 'POST',
			  url: '/vote/pmyearbudget/list',
			  data: JSON.stringify(queryParams),
			  contentType:'application/json',
			  success: function(res){
				  testData = res.page
			      console.log(res)
			      testData=res.page
			      table.render({
			  	  	id:"budget-tableID",
			  	    elem: '#budgetTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-200',
			  	    title: '年度预算表',
			  	    cols: cols,
			  	    cellMinWidth:'100',
			  	    data:testData,
			  	    page: true
			  	  	});},
			  dataType: "json"
			});
	}
  
  

  // table render  other
  table.render({
	  	id:"budget-tableID",
	    elem: '#budgetTable',
	    //url:'custom.json',
	    toolbar: '#toolbarDemo',
	    height:'full-220',
	    title: '年度预算数据表',
	    cols: cols,
	    cellMinWidth:'90',
	    data:[
			{'isNewCustomer':'是','custName':'交通银行','isNewProject':'是','projectName':'','projectType':'123','productList':'sss','revenueSource':'a','entity':'','contract':'ss','poSow':'','owner':'aaa','taxType':'12%','jan':'1111','feb':'2222'},
			{'isNewCustomer':'是','custName':'交通银行','isNewProject':'是','projectName':'','projectType':'123','productList':'sss','revenueSource':'a','entity':'','contract':'ss','poSow':'','owner':'aaa','taxType':'12%','jan':'111','feb':'222'}
	    ],
	    page: true
	  });
	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(budgetIndex)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	      layer.confirm('确认删除行么', function(index){
	    	  console.log(obj,index,'index')
	         $.ajax({
			  type: 'POST',
			  url: '/vote/pmyearbudget/update',
			  data: JSON.stringify({isDelete:'01',yearBudgetCode:data.yearBudgetCode}),
			  contentType:'application/json',
			  success: function(res){queryList()},
			  dataType: "json"
			});
	    	  
	        layer.close(index);
	        /* table.reload('budget-tableID',{
	        	
	        }); */
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.bidId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.bidId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#customQuery").click(queryList); 
	
	$(".budget-info-wrapper #projectNameQuery-hook").click(function(){
		// 共用 wbs controller
		var _YIndex=0;
		var _index=0;
		 $.openWindow({
		  		url:'wbs?act=indexSearch&index='+_index+'&YIndex='+_YIndex,
		  		title:"选择项目名称",
		  		width:"700"
		 });
	})
	$(".budget-info-wrapper #customerNameQuery-hook").click(function(){
		 $.openWindow({
		  		url:'customer?act=indexSearch',
		  		title:"选择项目名称",
		  		width:"700"
		 });
	})
	/*
	* 新增
	*/
	$(".budget-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form2?act=add&id=',
	  		title:"新增预算",
	  		width:"95%"
	  	})
	});
	
	
	$("#exportYearBudger").click(function(){
		var query = getParam()
		
		var xhr = new XMLHttpRequest();
		
		xhr.open('POST', exportUrl, true); // 也可以使用POST方式，根据接口
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.responseType = "blob"; // 返回类型blob
		
		xhr.onload = function () {

			   // 请求完成

			   if (this.status === 200) {

			       // 返回200

			       var blob = this.response;

			       var reader = new FileReader();

			       reader.readAsDataURL(blob);    // 转换为base64，可以直接放入a表情href

			       reader.onload = function (e) {

			           // 转换完成，创建一个a标签用于下载

			           var a = document.createElement('a');

			           a.download = 'data.xlsx';

			           a.href = e.target.result;

			           $("body").append(a);    // 修复firefox中无法触发click

			           a.click();

			           $(a).remove();

			       }

			   }
			};
			xhr.send(JSON.stringify(query));

	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改预算信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看预算信息";
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:"95%"
	  	})
		
	}
	queryList()
	
});
</script>
</body>
</html>