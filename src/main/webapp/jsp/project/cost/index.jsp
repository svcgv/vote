<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.layui-form-label{width:100px!important;}
</style>
<div class="cost-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>成本中心</legend>
	</fieldset>
	<form class="layui-form" id="cost-index-form" method="POST" action="">
	   <div class="layui-form-item">
	   
		  	
		    
		    <div class="layui-inline">
			      <label class="layui-form-label">成本中心编号：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="costId" id="costId" autocomplete="off" class="layui-input form-control">
			        
			      </div>
			   
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">所属机构：</label>
			      <div class="layui-input-inline">
				       <input type="text" name="orgName" id ="orgName" autocomplete="off" class="layui-input form-control">
				       <input type="text" style='display:none'  id="orgId" name="orgId" />
			      </div>
			      <button type="button"  class="layui-btn layui-btn-sm" id="sellDeptNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		   
		    
	 	   <div class="layui-inline" style="vertical-align: top;">
			   <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button"  class="layui-btn layui-btn-sm" id="costQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
			    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
			    <button type="button" class="layui-btn layui-btn-sm" id="add-hook"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
			  </div>
		   </div>
	   </div>
	</form>
	
	<table class="layui-hide" id="projectIndexTable" lay-filter="custom"></table>
	
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


function getParam(){
	var queryParams=$("#cost-index-form").serializeObject();
	 var newParam = {}
	  for(var i in queryParams){
		  if(queryParams[i]){
			  newParam[i] = queryParams[i]
		  }
	  }
	  return newParam
}

var testData=[];
var col=[
	[
		 {type: 'checkbox', fixed: 'left'},
 	      {field:'orgId', title:'机构编号'},
 	      {field:'orgName', title:'机构名称', width:150},
   	  {field:'costId', title:'成本中心编号'},
	      
	      {align: 'center',fixed: 'right', title:'操作', toolbar: '#barDemo', width:230}
	      
 		]
  ]
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  
 

	
  
  
  
  // 选择销售部门
  $(".cost-info-wrapper #sellDeptNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=index',
	  		title:"选择销售部门",
	  		width:"400"
	 });
  });
  
  
 
  
  

  
  table.render({
	  	id:"customer-table",
	    elem: '#projectIndexTable',
	    url:'/vote/costInfo/list',
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
	    cols: col,
	    cellMinWidth:'90',
	    page: true
	  });
  // table render
//   table.render({
// 	  	id:"customer-table",
// 	  elem: '#projectIndexTable',
// 	    toolbar: '#toolbarDemo',
// 	    height:'full-200',
// 	    title: '投标数据表',
// 	  cols: col,
// 	   cellMinWidth:'120',
// 	   data:testData,
// 	   page: true
// 	 });

	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	   // console.log(data)
	    if(obj.event === 'del'){
	      layer.confirm('确认删除行么', function(index){
	        obj.del();
	        var param = {}
           param.isDelete = '1';
            param.costId = data.costId;
            console.log(param)
            
            
            $.ajax({
                type: 'POST',
                url: '/vote/costInfo/update',
                data: JSON.stringify(param),
                contentType: 'application/json',
                success: function (res) {
                	layer.msg("删除成功",{icon:1});
                    console.log(res)
                },
                dataType: "json"
            })

            layer.close(index);
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.costId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.costId);
	    }
	  });
	/*
	* 查询按钮
	*/
	 $("#costQuery").click(function(){
		 var queryParams=$("#cost-index-form").serializeObject();
		 var newparam = {}
 		 for(var o in queryParams){
 			 if(queryParams[o]){
 				 newparam[o] = queryParams[o]
 			 }
 		 }
		 
			table.reload('customer-table',{
				 url: '/vote/costInfo/list',
				page:{
					curr:1 //从第一页开始
				},
				 method:'post',
					where:{
						queryStr:JSON.stringify(newparam)
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
	$(".cost-info-wrapper #add-hook").click(function(){
		$.openWindow({
	  		url:'form?act=add&id=',
	  		title:"新增成本中心",
	  		width:"90%"
	  	})
	});
	
	/*
	* 查看和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var _width='90%';
		if(isEdit == "edit"){
			var url='edit?act=edit&id='+id;
			var title="修改项目信息";
		}
		 if(isEdit == "view"){
			var url='view?act=view&id='+id;
	    	var	title="查看项目信息";
		}
		$.openWindow({
	  		url:url,
	  		title:title,
	  		width:_width
	  	})
		
	}
	
});

</script>
</body>
</html>