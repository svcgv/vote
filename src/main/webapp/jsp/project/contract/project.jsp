<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.projectGroup-project-wrapper .layui-form-label{width:70px!important;}
</style>
<div style="margin-top:10px;" class="projectGroup-project-wrapper">
	<form class="layui-form" id="project-query-form" action="">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">项目编号：</label>
				<div class="layui-input-inline">
					<input type="text" name="wbs"  autocomplete="off" class="layui-input form-control">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label" >项目名称：</label>
				<div class="layui-input-inline">
					<input type="text" name="projectName"  autocomplete="off" class="layui-input form-control" >
				</div>
			</div>

			<div class="layui-inline" style="vertical-align: top;">
				<div class="layui-btn-container" style="margin-left:15px;">
					<button type="button" class="layui-btn layui-btn-sm" id="userQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
					<button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
				</div>
			</div>

		</div>
	</form>
	<table class="layui-hide" id="projectTable-all" lay-filter="projectTableFilter" style="overflow:hidden;"></table>
	<div id="project-laypage"></div>
	<div class="layui-layer-btn layui-layer-btn-c">
		<a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
		<a class="layui-layer-btn1" id="close-hook">关闭</a>
	</div>

</div>

<script type="text/javascript">
    $(function(){
        var testData=[];
//一般直接写在一个js文件中
        layui.use(['layer', 'form','laydate','table','laypage'], function(){
            var layer = layui.layer ,
                form = layui.form,
                table=layui.table;
            var laypage = layui.laypage;

            var queryParams=$("#project-query-form").serializeObject();
            var newParams={"queryStr":JSON.stringify(queryParams)};
            $.ajax({
                type: 'POST',
                url: '/vote/pmprojectinfo/list',
                data: JSON.stringify(newParams),
                contentType:'application/json',
                success: function(res){
                    testData=res.page;
                    table.render({
                        elem: '#projectTable-all',
                        height:'260',
                        width:"690",
                        title: '项目表信息',
                        cols: [[
                            {type: 'radio' },
                            {field:'wbs', title:'项目编号', templet:function(d){
                                var jsonStr = JSON.stringify({"projectId":d.projectId,"wbs":d.wbs,"projectName":d.projectName,"projectType":d.projectType});
                                return '<div class="jsonData" dataStr='+jsonStr+'>'+d.wbs+'</div>'
                            } },
                            {field:'projectName', title:'项目名称'}
                        ]],
                        cellMinWidth:'90',
                        data:testData,
                        page: true
                    });},
                dataType: "json"
            });
            // table render


            var act="${act}";
            var win=$(".projectGroup-project-wrapper").getWindow();
            $(".projectGroup-project-wrapper").on("click","#save-hook",function() {

                console.log("121");
                $(".projectGroup-project-wrapper .layui-table-body table.layui-table tbody tr").each(function () {
                    var chk = $(this).find(".laytable-cell-radio");
                    var isChecked = chk.find(".layui-form-radio").hasClass("layui-form-radioed");
                    if (isChecked) {
                        var dataStr = $(this).children("td").eq(1).find(".jsonData").attr("dataStr");
                        var obj = JSON.parse(dataStr);
//                        if (act == "index") {
                            $("#contract-addForm-hook input[name='wbs']").val(obj.wbs);
                            $("#contract-addForm-hook input[name='projectId']").val(obj.projectId);
                            $("#contract-addForm-hook input[name='projectName']").val(obj.projectName);
                            $("#contract-addForm-hook input[name='projectType']").val(getCodeValue(obj.projectType,projectType));
//                    $("#contract-index-form input[name='custManagerId']").val(userId);

//                        } else if (act == "form") { //编辑 修改 页面
//                    $("#contract-addForm-hook input[name='custManagerId']").val(userId);
//                        }
                    }
                });
                win.close();
            });

            // 关闭按钮
            var win=$(".projectGroup-project-wrapper").getWindow();
            $(".projectGroup-project-wrapper").on("click","#close-hook",function(){
                win.close();
            });

			/*
			 * 客户查询按钮
			 */
            $("#project-query-form #userQuery").click(function(){

                var queryParams=$("#project-query-form").serializeObject();
                var newParams={"queryStr":JSON.stringify(queryParams)};
                $.ajax({
                    type: 'POST',
                    url: '/vote/pmprojectinfo/list',
                    data: JSON.stringify(newParams),
                    contentType:'application/json',
                    success: function(res){
                        testData=res.page;
                        table.render({
                            elem: '#projectTable-all',
                            height:'260',
                            width:"690",
                            title: '项目表信息',
                            cols: [[
                                {type: 'checkbox' },
                                {field:'wbs', title:'项目编号', templet:function(d){
                                    var jsonStr = JSON.stringify({"projectId":d.projectId,"wbs":d.wbs,"projectName":d.projectName});
                                    return '<div class="jsonData" dataStr='+jsonStr+'>'+d.wbs+'</div>'
                                } },
                                {field:'projectName', title:'项目名称'}
                            ]],
                            cellMinWidth:'90',
                            data:testData,
                            page: true
                        });},
                    dataType: "json"
                });
            });

        });

    });



</script>



