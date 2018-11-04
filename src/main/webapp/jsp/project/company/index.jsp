<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
</style>
<div class="custom-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>公司管理</legend>
	</fieldset>
	<form class="layui-form" id="customer-query-form" method="POST" action="/vote/bmcustomerinfo/list">
	   <div class="layui-form-item">
	   
		  	<div class="layui-inline">
		      <label class="layui-form-label">公司名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="companyName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">公司地址：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="companyAddress"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">是否有效：</label>
		      <div class="layui-input-inline">
		        <select name="isDelete" lay-verify="required" lay-filter="" class="form-control">
		        	 ${isUseful.ewTypeHtml }
		        </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">创建时间(开始)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="startTime" id="startTime" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">创建时间(结束)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="endTime" id="endTime"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		  
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="customAdd"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			     
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			  </div>
		   </div>
	   </div>
	</form>
	<table class="layui-hide" id="customTable" lay-filter="custom"></table>
	
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
		    elem: "#startTime",
		    theme: 'molv',
		    type: 'datetime'
	 });
	 laydate.render({
		    elem: "#endTime",
		    theme: 'molv',
		    type: 'datetime'
	 });

	 var queryParams=$("#customer-query-form").serializeObject();
  // table render
  table.render({
	  	id:"customer-table",
	    elem: '#customTable',
	    method:'post',
		where:{
			queryStr:JSON.stringify(queryParams)
		},
	    url: '/vote/pmcompanyinfo/list',
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '公司数据表',
	    cols: [[
	    	 //{type: 'checkbox', fixed: 'left'},
	  	      {field:'companyCode', title:'公司编号',fixed: 'left', width:110, sort: true},
	  	      {field:'companyName', title:'公司名称', width:230},
	  	      {field:'companyAddress', title:'公司地址'},
	  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
	    ]],
	    response: {
	    	dataName: 'page'
	    },
	    //contentType:false,
	    contentType:'application/json',
	    cellMinWidth:'90',
	    //data:testData,
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
	    	 
	    	  $.ajax({
				  type:"POST",
				  url:"/vote/pmcompanyinfo/update",
				  data:JSON.stringify({'companyCode':data.companyCode,'isDelete':'01'}),
				  contentType:'application/json',
				  success:function(data){
					 
					  table.reload('customer-table',{
						  
					  });
					 
				  }
			  });
	    	  
	        obj.del();
	        layer.close(index);
	        table.reload('customer-table',{
	        	page: {
	        	    curr: 1 //重新从第 1 页开始
	        	  }
	        });
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.companyCode);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.companyCode);
	    }
	  });
	/*
	* 公司查询按钮
	*/
	 $("#customQuery").click(function(){
		 var queryParams=$("#customer-query-form").serializeObject();
		 /*$.ajax({
			  type: 'POST',
			  url: '/vote/pmcompanyinfo/list',
			  data: JSON.stringify(queryParams),
			  contentType:'application/json',
			  success: function(res){
			      console.log(res)
			      testData=res.page
			      table.render({
			  	  	id:"customer-table",
			  	    elem: '#customTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-250',
			  	    title: '公司数据表',
			  	    cols: [[
			  	    	 {type: 'checkbox', fixed: 'left'},
			  	  	      {field:'companyCode', title:'公司编号',fixed: 'left', width:110, sort: true},
			  	  	      {field:'companyName', title:'公司名称', width:230},
			  	  	      {field:'companyAddress', title:'公司地址'},
			  	  	  	  {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
			  	    ]],
			  	    cellMinWidth:'90',
			  	    data:testData,
			  	    page: true
			  	  });},
			  dataType: "json"
			});*/
		//var queryParams=$("#customer-query-form").serialize();
		 table.reload('customer-table',{
			url:'/vote/pmcompanyinfo/list',
			page:{
				curr:1 //从第一页开始
			},
		    method:'post',
			where:{
				queryStr:JSON.stringify(queryParams)
			},
			contentType: 'application/json',
		    response: {
		    	dataName: 'page'
		    },
			done:function(res){
				console.log(res)
			}
			
		}) 
		//table.reload('customer-table');
		
	}); 
	
	/*
	* 新增公司
	*/
	$("#customAdd").click(function(){
		showFromTable("add");
	});
	
	/*
	* 新增和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var url='form?act=add&id=';
		var title="新增公司信息";
		var btn=['保存', '关闭'];
		var ajaxurl="/vote/pmcompanyinfo/save"; 
		if(isEdit == "edit"){
			url='form?act=edit&id='+id;
			title="修改公司信息";
			btn=['保存', '关闭'];
			ajaxurl="/vote/pmcompanyinfo/update";
		}else if(isEdit == "view"){
			url='form?act=view&id='+id;
			title="查看公司信息";
			btn=[];
		}
		
		layer.open({
			  skin: 'form-iframe-class',
			  type: 2, // iframe层
			  title: title,
			  closeBtn: 0, //显示关闭按钮
			  shade: 0.3,
			  area: ["800px","450px"],
			  anim: 2,
			  maxmin: true,
			  shadeClose: true, //点击遮罩关闭
			  closeBtn:1,
			  content: url, //iframe的url，no代表不显示滚动条
			  btn: btn,
			  btnAlign:'c',
			  //zIndex: layer.zIndex,
			  yes: function(index, layero){
				  // 保存按钮
				  var formData = layer.getChildFrame('body', index).find("form").serializeObject();
				  console.log(formData,'save')
				  $.ajax({
					  type:"POST",
					  url:ajaxurl,
					  data:JSON.stringify(formData),
					  contentType:'application/json',
					  success:function(data){
						  layer.close(index);
						  table.reload('customer-table');
						 
					  }
				  })
				 
				  
		      },
		      btn2: function(index, layero){
		    	  // 关闭按钮回调
		      }
			  
			});
	}
	
});
var testData=[
              {"companyCode":1,"companyName":"sdfs","companyAddress":"sff"},
              {"companyCode":2,"companyName":"sdfs","companyAddress":"sff"},
              {"companyCode":3,"companyName":"sdfs","companyAddress":"sff"},
              {"companyCode":4,"companyName":"sdfs","companyAddress":"sff"}
       ]
</script>
</body>
</html>