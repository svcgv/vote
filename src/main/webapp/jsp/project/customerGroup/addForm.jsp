<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>

    .formDetail-wrapper .customer-list {
        word-wrap: normal;
        word-break: keep-all;
        padding: 5px;
        display: inline-block;
    }

    .formDetail-wrapper .layui-icon-close-fill {
        position: relative;
        top: 1px;
    }
</style>
<div id="form-customer-hook" class="formDetail-wrapper" style="margin-top:10px;">
    <form class="layui-form" action="" lay-filter="form-detail">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户群名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="custGroupName" autocomplete="off" class="layui-input form-control">
                </div>
            </div>
            <div class="layui-inline" style="vertical-align:top;">
                <div class="layui-btn-container" style="margin-left:15px;">
                    <button type="button" class="layui-btn layui-btn-sm" id="addCustomer-hook"
                            style="margin-right:15px;"><i class="layui-icon"></i>添加客户
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm" id="removeCustomer-hook"
                            style="margin-right:15px;"><i class="layui-icon"></i>清空客户
                    </button>
                    <input type="hidden" id="saveCustomerIds" name="customerIds"/>
                    <input type="hidden" id="saveCustomerNames" name="customerNames"/>
                </div>
            </div>
            <div class="layui-inline">
            <%--<label class="layui-form-label">客户列表：</label>--%>
            <div class="layui-input-inline" id="chosed-customer-hook" style="opacity: 0;height: 0;">
                <%--<input type="hidden" id="chosed-customer-hook" />--%>
            </div>
            </div>

            <table class="layui-hide" id="customInnerTable" lay-filter="custom" style="overflow:hidden;"></table>

        </div>
    </form>
    <div class="layui-layer-btn layui-layer-btn-c">
        <a class="layui-layer-btn0" id="customGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
        <a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script type="text/html" id="toolAddBarDemo">
    <div class="layui-btn-container"></div>
</script>

<script type="text/html" id="addBarDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    $(function () {
        layui.use(['layer', 'form', 'table'], function () {
            var layer = layui.layer,
                form = layui.form,
                table = layui.table;

            var getChosedCustomer = $("#form-customer-hook #chosed-customer-hook");
            var data = [];
            getChosedCustomer.children(".customer-list").each(function () {
                var sapCode2 = $(this).children(".customerItem").attr("sapCode");
                data.push(sapCode2)
            });

            //table render
            table.render({
                elem: '#customInnerTable',
                id: 'customerInner-table',
                height: '250',
                width: "690",
                title: '客户数据表',
                toolbar: '#toolAddBarDemo',
                cols: [[
                    {field: 'sapCode', title: 'sap编号', sort: true},
                    {field: 'custCnName', title: '客户名称'},
                    {fixed: 'right', title: '操作', toolbar: '#addBarDemo', width: 80}
                ]],
                data: [],
                page: true
            });

            /*
             *监听每行编辑删除事件
             */
            table.on('tool(custom)', function (obj) {
                var data = obj.data;
                if (obj.event === 'del') {
                    layer.confirm('确认删除行么', function (index) {
                        obj.del();
                        layer.close(index);
                    });
                }
            });

            // 选择客户
            $("#addCustomer-hook").on("click", function () {
                $.openWindow({
                    url: 'formGroup?act=add',
                    title: "选择客户",
                    width: "700"
                });
            });
            // 清空客户
            $("#removeCustomer-hook").on("click", function () {
                console.log(layui.table.cache.customerInner-table);
                table.render({
                    elem: '#customInnerTable',
                    id: 'customerInner-table',
                    height: '250',
                    width: "690",
                    title: '客户数据表',
                    toolbar: '#toolAddBarDemo',
                    cols: [[
                        {field: 'sapCode', title: 'sap编号', sort: true},
                        {field: 'custCnName', title: '客户名称'},
                        {fixed: 'right', title: '操作', toolbar: '#addBarDemo', width: 80}
                    ]],
                    data: [],
                    page: true
                });
            });
            var win = $("#form-customer-hook").getWindow();
            // 保存
            $("#form-customer-hook #customGroup-add-hook").click(function () {
                var customerGroupName = $("#form-customer-hook input[name='custGroupName']").val();
//			console.log(customerGroupName)
                if ($.trim(customerGroupName) == '') {
                    layer.msg("请输入客户群名称");
                    return false;
                }

                var getChosedCustomer = $("#form-customer-hook #chosed-customer-hook");
                var ret = [];
			getChosedCustomer.children(".customer-list").each(function(){
				var sapCode2=$(this).children(".customerItem").val();
				ret.push(sapCode2)
			});

//                $(".formDetail-wrapper .layui-table-body table.layui-table tbody tr").each(function () {
//                    var sapCode = $(this).children("td").eq(0).text();
//                    var name = $(this).children("td").eq(1).text();
//                    if (name != "") {
//                        ret.push(sapCode);
//                        console.log(sapCode);
//                    }
//                });

                var data = {custGroupName: customerGroupName, sapCode: ret};
                $.ajax({
                    type: 'POST',
                    url: '/vote/pmcustomergroup/save',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    success: function (res) {
                        // table.reload('customer-table');
                        location.reload();
                        layer.msg("新增成功", {icon: 1});
                        win.close();
                    },
                    error: function () {
                        layer.msg("新增失败", {icon: 5});
                        win.close();
                    },
                    dataType: "json"
                })

                return false;
            })

            // 关闭
            $("#form-customer-hook #customGroup-close-hook").click(function () {
                win.close();
                return false;
            })

            $("#form-customer-hook #customerGroup-close-hook").click(function () {
                $(this).getWindow().close();
                return false;
            })

        })
    });

</script>