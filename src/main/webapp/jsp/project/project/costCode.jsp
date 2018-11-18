<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.project-costOrg-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="project-costOrg-wrapper">

	<table class="layui-hide" id="costOrgTable" lay-filter="custom" style="overflow:hidden;"></table>
	
    <div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
    
</div>

<script type="text/javascript">
var act="${act}";// 区分是index页 form页 赋值问题
var orgId="${orgId}";
$(function(){

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  table=layui.table;
  var paramData = {}
  paramData.orgId = orgId
  console.log(paramData)
  // table render
  table.render({
	    elem: '#costOrgTable',
	    id:'costOrg-table',
	    url:'/vote/pmprojectinfo/queryCostInfo',
	    method:'post',
	    where:paramData,
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    height:'260',
	    width:"690",
	    title: '成本中心表',
	    cols: [[
	      {type: 'radio' },
	      {field:'orgId', title:'机构ID', sort: true},
	      {field:'orgName', title:'机构名'}
	    ]],
	    page: true
	  });
	
	
	// 保存 事件
	
	var win=$(".project-costOrg-wrapper").getWindow();
	$(".project-costOrg-wrapper").on("click","#save-hook",function(){
		// 遍历选中的radio
		$(".project-costOrg-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
			var chk=$(this).find(".laytable-cell-radio");
			var isChecked=chk.find(".layui-form-radio").hasClass("layui-form-radioed");
			if(isChecked){
				var orgId=$(this).children("td").eq(1).text();
				var orgName=$(this).children("td").eq(2).text();
			 	if(act =="costCodeForm"){
					$("#project-form-hook input[name='costCode']").val(orgId);
			 	}else if(act =="costCodeEdit"){ 
					$("#project-edit-hook input[name='costCode']").val(orgId);
			 	}
			}
		});
		
		win.close();
	});
	
	// 关闭按钮
	var win=$(".project-costOrg-wrapper").getWindow();
	$(".project-costOrg-wrapper").on("click","#close-hook",function(){
		win.close();
	});
	
	
});
	
});
</script>