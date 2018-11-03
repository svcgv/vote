<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<div class="index-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>客户群信息</legend>
	</fieldset>
	<form class="layui-form" id="index-query-form" action="">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">客户群编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custGroupId"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label">客户群名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custGroupName"  autocomplete="off" class="layui-input form-control">
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
	      <label class="layui-form-label">开始时间：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="startTime" id="startTime" autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">结束时间：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="endTime" id="endTime"  autocomplete="off" class="layui-input form-control">
		      </div>
		  </div>
	    
	     <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button" class="layui-btn layui-btn-sm" id="query-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
		    <button type="button" class="layui-btn layui-btn-sm" id="import-hook"><i class="layui-icon"></i>导入</button>
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		  </div>
	   	</div>
	    
	   </div>
	  </form>
	  
	  <table class="layui-hide" id="mainTable" lay-filter="tableFilter"></table>
</div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="view">查看</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>
<script type="text/javascript">
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

	//指定允许上传的文件类型
		upload.render({
		    elem: '#import-hook',
		    url: '/upload/',
		    accept: 'file', // 普通文件
		    exts: 'xlsx|xls', //只允许上传压缩文件
		    auto: true , // 是否自动上传 
		    multiple: true,
		    done: function(res){
		    	layer.msg('上传成功', {icon: 1});
		    },
		    error: function(index, upload){
		    	layer.msg('上传失败', {icon: 5});
		    }
		  });
		
		  
	  // table render
	  table.render({
		  	id:"tableID",
		    elem: '#mainTable',
		    //url:'custom.json',
		    height:'full-200',
		    toolbar: '#toolbarDemo',
		    title: '客户群数据信息',
		    cols: [[
		      {type: 'checkbox', fixed: 'left'},
		      {field:'custGroupId', title:'客户群编号',fixed: 'left', width:110, sort: true},
		      {field:'custGroupName', title:'客户群名称', width:230},
		      {field:'creator', title:'添加人', width:90},
		      {field:'creatorId', title:'添加人编号',width:100},
		      {field:'createTime', title:'添加时间'},
		      {field:'modifier', title:'修改人'},
		      {field:'modifyTime', title:'修改时间'},
		      {field:'isDelete', title:'是否删除'},
		      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
		    ]],
		    cellMinWidth:'90',
		    data:testData,
		    page: true
		  });
		/*
		* 监听头工具栏事件 
		*/
		table.on('toolbar(custom)', function(obj){
		  var checkStatus = table.checkStatus(obj.config.sapCode)
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
		  table.on('tool(tableFilter)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'del'){
		      layer.confirm('确认删除行么', function(index){
		        obj.del();
		        layer.close(index);
		        table.reload('tableID',{
		        	
		        });
		      });
		    } else if(obj.event === 'edit'){
		    	// 编辑
		    	$.openWindow({
			  		url:'editForm?id='+(data.custGroupId),
			  		title:"修改客户群",
			  		width:"700"
			  	})
		    	
		    	
		    }else if(obj.event === "view"){
		    	// 查看
		    	$.openWindow({
			  		url:'viewForm?id='+(data.custGroupId),
			  		title:"查看客户群",
			  		width:"700"
			  	})
		    }
		  });
		/*
		* 查询按钮
		*/
		$("#query-hook").click(function(){
			var queryParams=$("#customer-query-form").serializeObject();
			 $.ajax({
				  type: 'POST',
				  url: '/vote/pmcustomergroup/list',
				  data: JSON.stringify(queryParams),
				  contentType:'application/json',
				  success: function(res){
				      console.log(res)
				      testData=res.page
				      table.render({
						  	id:"tableID",
						    elem: '#mainTable',
						    //url:'custom.json',
						    height:'full-200',
				  	    //url:'custom.json',
				  	    toolbar: '#toolbarDemo',
				  	  title: '客户群数据信息',
					    cols: [[
					      {type: 'checkbox', fixed: 'left'},
					      {field:'custGroupId', title:'客户群编号',fixed: 'left', width:110, sort: true},
					      {field:'custGroupName', title:'客户群名称', width:230},
					      {field:'creator', title:'添加人', width:90},
					      {field:'creatorId', title:'添加人编号',width:100},
					      {field:'createTime', title:'添加时间'},
					      {field:'modifier', title:'修改人'},
					      {field:'modifyTime', title:'修改时间'},
					      {field:'isDelete', title:'是否删除'},
					      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
					    ]],
				  	    cellMinWidth:'90',
				  	    data:testData,
				  	    page: true
				  	  });},
				  dataType: "json"
				});
		});
			
			
			
			/*var queryParams=$("#index-query-form").serialize();
			table.reload('tableID',{
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
		});*/
		
		/*
		* 新增
		*/
		$("#add-hook").click(function(){
			$.openWindow({
		  		url:'addForm',
		  		title:"新增客户群",
		  		width:"700"
		  	})
		});
		
		
		/*
		* 新增和修改 form 表单 GET 请求
		*/
		function showFromTable(isEdit,id){
			var url='form?act=add&id=';
			var title="新增客户信息";
			var btn=['保存', '关闭'];
			if(isEdit == "edit"){
				url='form?act=edit&id='+id;
				title="修改客户信息";
				btn=['保存', '关闭'];
			}else if(isEdit == "view"){
				url='form?act=view&id='+id;
				title="查看客户信息";
				btn=[];
			}
			
			layer.open({
					id:"iframe-form",
				  skin: 'form-iframe-class',
				  type: 2, // iframe层
				  title: title,
				  closeBtn: 0, //显示关闭按钮
				  shade: 0.3,
				  area: ["800px","500px"],
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
					  var formData = layer.getChildFrame('body', index).find("form").serialize();
					  $.ajax({
						  type:"POST",
						  url:'form',
						  data:formData,
						  success:function(data){
							 
							  table.reload('tableID');
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
		  		{
					"custGroupId":"1000000210",
					"custGroupName":"上海浦东发展银行股份有限公司",
					"creator":"史定波",
					"creatorId":'101',
					"createTime":"2017-10-12",
					"modifier":"",
					"modifyTime":"",
					"isDelete":"否"
				},
				{
					"custGroupId":"1000000210",
					"custGroupName":"上海浦东发展银行股份有限公司",
					"creator":"史定波",
					"creatorId":'101',
					"createTime":"2017-10-12",
					"modifier":"",
					"modifyTime":"",
					"isDelete":"否"
				},
				{
					"custGroupId":"1000000210",
					"custGroupName":"上海浦东发展银行股份有限公司",
					"creator":"史定波",
					"creatorId":'101',
					"createTime":"2017-10-12",
					"modifier":"",
					"modifyTime":"",
					"isDelete":"否"
				},
				{
					"custGroupId":"1000000210",
					"custGroupName":"上海浦东发展银行股份有限公司",
					"creator":"史定波",
					"creatorId":'101',
					"createTime":"2017-10-12",
					"modifier":"",
					"modifyTime":"",
					"isDelete":"否"
				}
			]


</script>




