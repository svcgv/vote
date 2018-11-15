<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<div class="projectGroup-form-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">项目群名称：</label>
		      <div class="layui-input-inline">
		         <input type="text" name="projectGroupName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline">
		       <div class="layui-btn-container" style="margin-left:15px;">
			    <button type="button" class="layui-btn layui-btn-sm" id="addProject-hook"  style="margin-right:15px;"><i class="layui-icon"></i>添加项目</button>
			     <input type="hidden" id="saveCustomerIds" name="customerIds" />
			     <input type="hidden" id="saveCustomerNames" name="customerNames" />
			  </div>
		    </div>
	  	</div>
	</form>
	<table class="layui-hide" id="projectTable-chosed" lay-filter="tableFilter"></table>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="projectGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1 win-closeBtn-hook">关闭</a>
    </div>
</div>
<script type="text/html" id="barFormDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
// 全局变量 已选的项目  新增时为空  编辑时为后台传入的数据 "${chosedProject}"
var chosedProject=[];
console.log(chosedProject);
var chosedLayTable=null;
$(function(){
	layui.use(['layer', 'form','table'], function(){
		 var layer = layui.layer ,
			  form = layui.form;
		 
		  chosedLayTable=layui.table;
		  chosedLayTable.render({
			  	id:"table-chosedProject",
			    elem: '#projectTable-chosed',
			    height:'350',
			    title: '项目群数据信息',
			    cols: [[
			      {type: 'checkbox'},
			      {field:'projectId', title:'项目编号', templet:function(d){
			    	  var jsonStr = JSON.stringify(d);
			    	  return '<div class="jsonData" dataStr='+jsonStr+'>'+d.projectId+'</div>'
			      } },
			      {field:'projectName', title:'项目名称'},
			      {fixed: 'right', title:'操作', toolbar: '#barFormDemo', width:100}
			    ]],
			    cellMinWidth:'90',
			    data:chosedProject,
			    page: true
			  });
		  chosedLayTable.on('tool(tableFilter)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'del'){
			      layer.confirm('确认删除行么', function(index){
			        obj.del();
			        console.log(data,chosedProject)
			        // 删除
			        for(var k in chosedProject){
			        	if(data.projectId == chosedProject[k].projectId ){
			        		chosedProject.splice(k,1)
			        	}
			        }
			        console.log(chosedProject,'exit');
                      chosedLayTable.reload('table-chosedProject',{
                          data:chosedProject
                      })
			        layer.close(index);
			        
			      });
			    } 
			  });
		$(".projectGroup-form-wrapper #addProject-hook").click(function(){
			$.openWindow({
		  		url:'project?act=form2Project',
		  		title:"选择项目",
		  		width:"700"
		  	})
		})
		var win=$(".projectGroup-form-wrapper").getWindow();
		$(".projectGroup-form-wrapper").on("click",".win-closeBtn-hook",function(){
			win.close();
		});
		// 保存
		$(".projectGroup-form-wrapper").on("click","#projectGroup-add-hook",function(){
			// 遍历选中的CheckBox
			console.log(chosedProject,'form table中数据');
			win.close();
		});
	
	});
})
</script>


