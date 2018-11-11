<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    .customGroup-form-wrapper .layui-form-label {
        width: 90px;
    }
</style>
<div style="margin-top:10px;" class="customGroup-form-wrapper">
    <form class="layui-form" id="formGroup-query-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户SAP编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="sapCode" autocomplete="off" class="layui-input form-control">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">客户名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="custCnName" autocomplete="off" class="layui-input form-control">
                </div>
            </div>

            <div class="layui-inline" style="vertical-align: top;">
                <div class="layui-btn-container" style="margin-left:15px;">
                    <button type="button" class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i
                            class="layui-icon layui-icon-search"></i>查询
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i
                            class="layui-icon layui-icon-refresh"></i>重置
                    </button>
                </div>
            </div>

        </div>
    </form>
    <table class="layui-hide" id="customTable" lay-filter="custom" style="overflow:hidden;"></table>
    <div class="layui-layer-btn layui-layer-btn-c">
        <a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
        <a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
</div>

<script type="text/javascript">
    $(function () {

        var testData = []
//一般直接写在一个js文件中
        layui.use(['layer', 'form', 'laydate', 'table'], function () {
            var layer = layui.layer,
                form = layui.form,
                tableGroup = layui.table;
            var queryParams = $("#formGroup-query-form").serializeObject();
            // table render
            tableGroup.render({
                elem: '#customTable',
                id: 'customerGroup-table',
                url: '/vote/pmcustomerinfo/list',
                method: 'post',
                where: {
                    queryStr: JSON.stringify(queryParams)
                },
                contentType: 'application/json',
                response: {
                    dataName: 'page'
                },
                height: '250',
                width: "690",
                title: '客户数据表',
                cols: [[
                    {type: 'checkbox'},
                    {field: 'sapCode', title: '客户SAP编号', sort: true},
                    {field: 'custCnName', title: '客户名称'}

                ]],
                page: true
            });

            /**
             * chechbox 点击事件
             */
            $(".customGroup-form-wrapper").on("click", ".layui-table-body table.layui-table tbody .laytable-cell-checkbox i", function () {
                var isChecked = $(this).parent(".layui-form-checkbox").hasClass("layui-form-checked");
                console.log($(this), isChecked)
            })

            // 保存 事件
            var act = "${act}";
            var win = $(".customGroup-form-wrapper").getWindow();
            var getExitCustomer = $("#form-customer-hook #chosed-customer-hook");
            var getExittable = $("#form-customer-hook #customInnerTable");
            $(".customGroup-form-wrapper").on("click", "#save-hook", function () {
                var ret = [];
                var data2 = [];
                var data = [];
                getExitCustomer.children(".customer-list").each(function () {
                    var sapCode2 = $(this).children(".customerItem").val();
                    var name = $(this).children(".customerItem2").val();
                    var object = {"sapCode": sapCode2, "custCnName": name};
                    data.push(object);
                    ret.push(sapCode2)
                });
                //遍历之前新增的数据
//                $(".formDetail-wrapper .layui-table-body table.layui-table tbody tr").each(function () {
//                    var sapCode = $(this).children("td").eq(0).text();
//                    var name = $(this).children("td").eq(1).text();
//                    if (name != "") {
//                        var object = {"sapCode": sapCode, "custCnName": name};
//                        data.push(object);
//                        data2.push(sapCode);
//                    }
//                });


                // 遍历选中的CheckBox
                $(".customGroup-form-wrapper .layui-table-body table.layui-table tbody tr").each(function () {
                    var chk = $(this).find(".laytable-cell-checkbox");
                    var isChecked = chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
                    if (isChecked) {
                        var sapCode = $(this).children("td").eq(1).text();
                        var name = $(this).children("td").eq(2).text();
                        var object = {"sapCode": sapCode, "custCnName": name};
                        // 遍历不存在的插入
                        if ($.inArray(sapCode, ret) == -1) {
                            data.push(object);
                            var _html = '<div class="customer-list">'
                                +'<input type="hidden" class="customerItem" value="' + sapCode + '"/>'
                            +'<input type="hidden" class="customerItem2" value="' + name + '"/>' +
                                '</div>';
                            getExitCustomer.append(_html);
                        }


                    }
                });
                tableGroup.render({
                    elem: '#customInnerTable',
                    id: 'customerInner-table',
                    height: '250',
                    width: "690",
                    title: '客户数据表',
                    cols: [[
                        {field: 'sapCode', title: 'sap编号', sort: true},
                        {field: 'custCnName', title: '客户名称'},
                        {fixed: 'right', title: '操作', toolbar: '#addBarDemo', width: 80}
                    ]],
                    data: data,
                    page: true
                });
                //location.reload();
                //tableGroup.reload('customerInner-table');
                win.close();
            });

            // 关闭按钮
            var win = $(".customGroup-form-wrapper").getWindow();
            $(".customGroup-form-wrapper").on("click", "#close-hook", function () {
                win.close();
            });

            /*
             * 客户查询按钮
             */
            $("#customQuery").click(function () {

                var queryParams = $("#formGroup-query-form").serializeObject();
                console.log(queryParams)

                var newparam = {}
                for (var o in queryParams) {
                    if (queryParams[o]) {
                        newparam[o] = queryParams[o]
                    }
                }

                tableGroup.reload('customerGroup-table', {
                    url: '/vote/pmcustomerinfo/list',
                    page: {
                        curr: 1 //从第一页开始
                    },
                    method: 'post',
                    where: {
                        queryStr: JSON.stringify(newparam)
                    },
                    contentType: 'application/json',
                    response: {
                        dataName: 'page'
                    },
                    done: function (res) {
                    }

                })

                /*$.ajax({
                 type: 'POST',
                 url: '/vote/pmcustomerinfo/list',
                 data: JSON.stringify(newparam),
                 contentType:'application/json',
                 success: function(res){
                 console.log(res)
                 testData=res.page
                 tableGroup.render({
                 elem: '#customTable',
                 height:'250',
                 cols: [[
                 {type: 'checkbox' },
                 {field:'sapCode', title:'sap编号', sort: true},
                 {field:'custCnName', title:'客户名称'},
                 ]],
                 cellMinWidth:'90',

                 data:testData,
                 page: true
                 });},
                 dataType: "json"
                 });*/
            });

        });

    });


</script>



