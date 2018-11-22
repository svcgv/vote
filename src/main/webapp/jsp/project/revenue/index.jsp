<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:130px!important;padding:5px 0px;}
</style>
<div class="revenue-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>收入上报</legend>
	</fieldset>
	<form class="layui-form" id="revenue-info-wrapper" method="POST" action="">
	   <div class="layui-form-item" style="margin-bottom:0px;">
		   <div class="layui-inline">
			   <label class="layui-form-label">年份：</label>
			   <div class="layui-input-inline">
				   <select name="year" id="getYearRange"></select>
			   </div>
			   <span class="f-placeholder"></span>
		   </div>
		   <div class="layui-inline">
			   <label class="layui-form-label">项目编号：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="projectId" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
				   <input type="hidden" name="projectName" >
			   </div>
			   <button type="button"  class="layui-btn layui-btn-sm" id="projectNameQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		   </div>
		   <div class="layui-inline">
				<label class="layui-form-label">项目名称：</label>
				<div class="layui-input-inline">
				   <input type="text" name="revenueCode"  autocomplete="off" class="layui-input form-control">
			   </div>
				<span class="f-placeholder"></span>
			</div>

		   <div class="layui-inline">
			   <label class="layui-form-label">成本中心编号：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="projectId" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
				   <input type="hidden" name="projectName" >
			   </div>
			   <button type="button"  class="layui-btn layui-btn-sm" id="projectNameQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		   </div>
		   <div class="layui-inline">
			   <label class="layui-form-label">成本中心：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="revenueCode"  autocomplete="off" class="layui-input form-control">
			   </div>
			   <span class="f-placeholder"></span>
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
	    </div>
	    
	    <div class="layui-form-item" style="margin-bottom:0px;">
		    <div class="layui-inline">
		      <label class="layui-form-label">收入上报(开始月份)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="collectionStartDate" id="collectionStartDate-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
			<div class="layui-inline">
				<label class="layui-form-label">收入上报(结束月份)：</label>
				<div class="layui-input-inline">
					<input type="text" name="collectionEndDate" id="collectionEndDate-hook"  autocomplete="off" class="layui-input form-control hasDatepicker">
				</div>
				<span class="f-placeholder"></span>
			</div>
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			  </div>
		   </div>
	   </div>
	</form>
	<table class="layui-hide" id="productTable" lay-filter="revenue"></table>
	
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
    $(function(){
        //年份
        var currentYear=(new Date()).getFullYear();
        var _optionsHtml="<option value=''>请选择</option>";
        for(var i=currentYear;i>=2010;i--){
            if(i == currentYear){
                _optionsHtml+="<option selected='true' value='"+i+"'>"+i+("年")+"</option>";
            }else{
                _optionsHtml+="<option value='"+i+"'>"+i+("年")+"</option>";
            }
        }
        $(".revenue-info-wrapper #getYearRange").html(_optionsHtml);
    })
function getParam(){
	var queryParams=$("#revenue-info-wrapper").serializeObject();
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
	    elem: ".revenue-info-wrapper #collectionStartDate-hook",
	    theme: 'molv',
      type: 'month'
 });
  laydate.render({
	    elem: ".revenue-info-wrapper #collectionEndDate-hook",
	    theme: 'molv',
      type: 'month'
 });

  // 选择项目
  $(".revenue-info-wrapper #projectNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'project?act=index',
	  		title:"选择项目编号",
	  		width:"700"
	 });
	  
  });
  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#productTable',
	  //  url:'/vote/pmproductinfo/list',
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
	    title: '收入上报数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'year', title:'年份', width:130,rowspan: 2},
            {field:'isDelete', title:'是否有效', width:130,rowspan: 2},
	  	      {field:'contractAmount', title:'合同金额', width:130,rowspan: 2},
	  	      {field:'projectCode', title:'项目编号', width:130,rowspan: 2},
	  	      {field:'projectName', title:'项目名称', width:130,rowspan: 2},
            {field:'projectType', title:'项目类型', width:150,rowspan: 2},
	  	      {field:'contractCode', title:'合同编号', width:130,rowspan: 2},
	  	      {field:'contractName', title:'合同名称', width:130,rowspan: 2},
	  	      {field:'chenbenId', title:'成本中心编号', width:130,rowspan: 2},
	  	      {field:'chenbenName', title:'成本中心名称', width:130,rowspan: 2},
	  	      {field:'allYearAmout', title:'历年上报收入合计', width:130,rowspan: 2},
			//需要加校验，校验规则为:历年加当年的不能超过合同金额
	  	      {field:'currentAmount', title:'当年上报收入合计', width:130,rowspan: 2},
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
            ],
	    cellMinWidth:'90',
	    page: true
	    ,data:[{year:"2018",
          isDelete:"有效",
          contractAmount:"100000",
          projectCode:"项目编号",
          projectName:"项目名称",
          projectType:"项目类型",
          contractCode:"合同编号",
          contractName:"合同名字",
          chenbenId:"成本中心编号",
          chenbenName:"成本中心名字",
          allYearAmout:"60000",
          currentAmount:"30000",
          budgetJan:"1000",
          budgetFeb:"10000",
          budgetMar:"1000",
          budgetApr:"1000",
          budgetMay:"1000",
          budgetJun:"1000",
          budgetJul:"1000",
          budgetAug:"1000",
          budgetSep:"1000",
          budgetOct:"1000",
          budgetNov:"1000",
          budgetDec:"10000"
	  	}]
	  });
	/*
	* 监听头工具栏事件
	*/
	table.on('toolbar(revenue)', function(obj){
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
	  table.on('tool(revenue)', function(obj){
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
	    	showFromTable('edit',data.revenueCode);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.revenueCode);
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
		
	}); 
	
	/*
	* 新增
	*/
	$(".revenue-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增收入上报信息",
	  		width:"800px"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改收入上报信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看收入上报信息";
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:"800px"
	  	})
		
	}
	
});
var testData=[];
</script>
</body>
</html>