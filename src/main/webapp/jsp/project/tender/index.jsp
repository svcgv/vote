<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
</style>
<div class="tender-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>投标管理</legend>
	</fieldset>
	<form class="layui-form" id="tender-index-form" method="POST" action="">
	   <div class="layui-form-item">
	   
		  	<div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="bidName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		   <div class="layui-inline">
			   <label class="layui-form-label">投标编号：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="bidCode"  autocomplete="off" class="layui-input form-control">
			   </div>
		   </div>
		   <div class="layui-inline">
			   <label class="layui-form-label">客户名称：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="custCnName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
				   <input type="text" style='display:none' name="custId">
				   <input type="text" style='display:none' name="custSapCode">
			   </div>
			   <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
				   <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
			   </div>
		   </div>
		    <!-- 
		    <div class="layui-inline">
		      <label class="layui-form-label">预估合同金额：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估成本：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="predictCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估利润率：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictProfitRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">预估期限：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="predictPeriod" id="predictPeriodDate" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		     -->
		    <div class="layui-inline">
		      <label class="layui-form-label">交付部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="payDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="payDeptId">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="payOrgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="sellDeptId">
		      </div>
	      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="custManagerId">
		      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>

		   <div class="layui-inline">
			   <label class="layui-form-label">评审状态：</label>
			   <div class="layui-input-inline">
				   <select name="status" lay-verify="required" lay-filter="" class="form-control">
					   ${status.ewTypeHtml }
				   </select>
			   </div>
		   </div>
		   <%--<div class="layui-inline">--%>
			   <%--<label class="layui-form-label">预估开始期限：</label>--%>
			   <%--<div class="layui-input-inline">--%>
				   <%--<input type="text" name="predictPeriodStart" id="predictPeriodStartDate-edit" autocomplete="off" class="layui-input form-control hasDatepicker">--%>
			   <%--</div>--%>
		   <%--</div>--%>
		   <%--<div class="layui-inline">--%>
			   <%--<label class="layui-form-label">预估结束期限：</label>--%>
			   <%--<div class="layui-input-inline">--%>
				   <%--<input type="text" name="predictPeriodEnd" id="predictPeriodEndDate-edit" autocomplete="off" class="layui-input form-control hasDatepicker">--%>
			   <%--</div>--%>
		   <%--</div>--%>
		    
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
  <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="tenderReview">提交评审</a>
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
		    elem: "#predictPeriodDate",
		    theme: 'molv',
		    type: 'datetime'
	 });
  
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
  // 选择机构
  $(".tender-info-wrapper #orgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=index',
	  		title:"选择销售部门",
	  		width:"400"
	 });
	  
  });
  $(".tender-info-wrapper #payOrgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=pay',
	  		title:"选择交付部门",
	  		width:"400"
	 });
	  
  });
  
  
  // 选择人员
  $(".tender-info-wrapper #userQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=index',
	  		title:"选择客户经理",
	  		width:"700"
	 	 });
	});
  
  

  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#productTable',
	    url:'/vote/pmconfirmbid/list',
	    method:'post',
		where:{
			queryStr:JSON.stringify(getParam())
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-200',
	    title: '投标数据表',
	    cols: [[
	    	  {type: 'checkbox', fixed: 'left'},
	  	      {field:'bidCode', title:'投标编号',fixed: 'left', sort: true, width:130},
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
            {field:'bidName', title:'项目名称', width:130},
            {field:'custCnName', title:'客户名称', width:130},
            {field:'constructionDeptName', title:'交付部门'},
            {field:'sellDeptName', title:'销售部门'},
            {field:'custManagerName', title:'客户经理'},
            {field:'firstBidAmount', title:'首次报价（元）', width:150},
            {field:'predictAmount', title:'预估收入（元）', width:150},
            {field:'predictCost', title:'预估成本（元）', width:120},
            {field:'predictProfitRate', title:'预估利润率（%）'},
            {field:'taxRate', title:'税率（%）'},
            {field:'predictPeriodStart', title:'项目开始时间'},
            {field:'predictPeriodEnd', title:'项目结束时间'},
            {field:'paymentPoint', title:'付款点'},
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:250}
	    ]],
	    cellMinWidth:'90',
	    page: true
	  });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	    	if(data.status != '00'){
	    		layer.msg("当前已发起过评审，不可删除",{icon:5});
	    		return
	    	}
	      layer.confirm('确认删除行么', function(index){
	    	  $.ajax({
				  type:"POST",
				  url:"/vote/pmconfirmbid/update",
				  data:JSON.stringify({'bidId':data.bidId,'isDelete':'01'}),
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
	    	if(data.status != '00'){
	    		layer.msg("投标正在评审中，请勿修改",{icon:5});
	    		return
	    	}
	    	showFromTable('edit',data.bidId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.bidId);
	    }else if(obj.event == "tenderReview"){
	    	// 评审
	    	if(data.status!=='00'){
	    		layer.msg("当前已发起过评审，请勿重复发起",{icon:5});
	    		return;
	    	}
			console.log(data);
            $.ajax({
                type:'POST',
                url:'/vote/pmconfirmbid/submit',
                contentType:'application/json',
                data: JSON.stringify(data),
                success:function(res){
                    layer.msg("发起评审成功",{icon:1});
                },
                error:function(){
                    layer.msg("发起评审失败",{icon:5});
                },
                dataType: "json"
            });
            
	    }else if(obj.event == "setMoney"){
	    	// 评审
	    	showFromTable('setMoney',data.bidId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#customQuery").click(function(){
		 console.log("test");
		 table.reload('customer-table',{
				url:'/vote/pmconfirmbid/list',
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
			    }

			})
			
	}); 
	
	/*
	* 新增
	*/
	$(".tender-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增投标",
	  		width:"1000"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var _width=800;
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改投标信息";
		}else if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看投标信息";
		}else if(isEdit == "review"){
			var url='review?act=review&id='+id;
	    	var	title="投标评审";
	    	_width=650;
		}
		
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:_width
	  	})
		
	}
	
});
var testData=[];
</script>
</body>
</html>