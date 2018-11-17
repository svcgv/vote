<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<div id="view-project-hook" class="projectGroup-form-wrapper" style="margin-top:10px;">
<form class="layui-form" action="" lay-filter="form-detail">
		<div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label">项目群名称：</label>
			      <div class="layui-input-inline">
			         <input type="text" name="projectGroupName" value="${pmProjectGroupInfoEntity.projectGroupName}"  readonly='true' autocomplete="off" class="layui-input form-control">
			      </div>
			    </div>
			</div>
	</form>
<table class="layui-hide" id="projectTable-chosed" lay-filter="tableFilter"></table>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1 win-closeBtn-hook">关闭</a>
    </div>
    </div>
    
<script type="text/javascript">
var chosedProject=[];
console.log(chosedProject);
var chosedLayTable=null;
$(function(){
layui.use(['layer', 'form','table'], function(){
		 var layer = layui.layer ,
			  form = layui.form;
		  var paramData = {}
		  paramData.projectGroupId = ${pmProjectGroupInfoEntity.projectGroupId}
		  chosedLayTable=layui.table;
		   $.ajax({
              type: 'POST',
              url:'/vote/pmprojectgroupinfo/listChildren',
              data: JSON.stringify(paramData),
              contentType: 'application/json',
              success: function (res) {
                  chosedProject=res.page;
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
			      {field:'projectName', title:'项目名称'}
			    ]],
			    data:chosedProject,
			    page: true
			  });
		  
              },
              dataType: "json"
          });
          	var win=$(".projectGroup-form-wrapper").getWindow();
		$(".projectGroup-form-wrapper").on("click",".win-closeBtn-hook",function(){
			win.close();
		});
		
			});
})

</script>