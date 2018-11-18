<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cm" uri="http://www.custom.com/01" %>
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
<div id="form-customer-hook" class="formDetail-wrapper"
     style="margin-top: 10px;">
    <form class="layui-form" action="" lay-filter="form-detail">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户群名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="custGroupName"
                           value="${ctnGroup.custGroupName}" autocomplete="off"
                           class="layui-input form-control">
                    <input type="hidden" name="custGroupId" readonly=“readonly”
                           value="${ctnGroup.custGroupId}" id='custGroupId' autocomplete="off" enabled='false'
                           class="layui-input form-control">
                </div>

            </div>

            <div class="layui-inline" style="vertical-align: top;">
                <div class="layui-btn-container" style="margin-left: 15px;">
                    <button type="button" class="layui-btn layui-btn-sm"
                            id="addCustomer-hook" style="margin-right: 15px;">
                        <i class="layui-icon"></i>添加客户
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm" id="removeCustomer-hook"
                            style="margin-right:15px;"><i class="layui-icon"></i>清空客户
                    </button>
                    <input type="hidden" id="saveCustomerIds" name="customerIds"/> <input
                        type="hidden" id="saveCustomerNames" name="customerNames"/>
                </div>
            </div>
            <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">客户列表：</label>--%>
            <%--<div class="layui-input-inline" id="chosed-customer-hook"--%>
            <%--style="border: #e6e6e6 solid 1px; height: 100px; overflow-y: auto; width: 320px;">--%>

            <%--<c:forEach items="${customerList }" var="app">--%>
            <%--<span class="customer-list"> <span class="customerItem"--%>
            <%--sapCode="${app.sapCode}">${app.custCnName}</span> <span--%>
            <%--onclick="$(this).parent().remove()"><i--%>
            <%--class="layui-icon layui-icon-close-fill"></i></span>--%>
            <%--</span>--%>
            <%--</c:forEach>--%>
            <%--</div>--%>
            <%--</div>--%>
           <div style="width:670px;margin-left:10px;">
	            <table class="layui-hide" id="customInnerTable" lay-filter="custom" style="overflow:hidden;"></table>
           </div>
        </div>
    </form>
    <div class="layui-layer-btn layui-layer-btn-c">
        <a class="layui-layer-btn0" id="customGroup-add-hook"
           style="background: #009688; border-color: #009688;">保存</a> <a
            class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script type="text/html" id="addBarDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    var custGroupId = '';
    var chosedProject=[];
    console.log(chosedProject);
    var chosedLayTable=null;
    $(function () {
        custGroupId = $('#custGroupId')[0].value;
        console.log(custGroupId);
        layui
            .use(
                ['layer', 'form', 'table'],
                function () {
                    var layer = layui.layer,
                        form = layui.form;
                    chosedLayTable = layui.table;
                    // 选择客户
                    $("#addCustomer-hook").on("click", function () {
                        $.openWindow({
                            url: 'formGroup?act=edit',
                            title: "选择客户",
                            width: "700"
                        });
                    });
                    var win = $("#form-customer-hook").getWindow();
                    var param = {"custGroupId": custGroupId};
                    $.ajax({
                        type: 'POST',
                        url: '/vote/pmcustomergrouprelation/list',
                        data: JSON.stringify(param),
                        contentType: 'application/json',
                        success: function (res) {
                            chosedProject=res.page;
                            chosedLayTable.render({
                                elem: '#customInnerTable',
                                id: 'table-chosedProject',
                                height: '250',
                                title: '客户数据表',
                                cols: [[
                                    {field: 'sapCode', title: 'sap编号',templet:function(d){
                                        var jsonStr = JSON.stringify({"sapCode":d.sapCode,"custCnName":d.custCnName});
                                        return '<div class="jsonData" dataStr='+jsonStr+'>'+d.sapCode+'</div>'
                                    } },
                                    {field: 'custCnName', title: '客户名称'},
                                    {fixed: 'right', title: '操作', toolbar: '#addBarDemo', width: 80}
                                ]],
                                data: chosedProject,
                                page: true
                            });
                        },
                        dataType: "json"
                    });

                    /*
                     *监听每行编辑删除事件
                     */
                    chosedLayTable.on('tool(custom)', function (obj) {
                        var data = obj.data;
                        if(obj.event === 'del'){
                            layer.confirm('确认删除行么', function(index){
                                obj.del();
                                console.log(data,chosedProject)
                                // 删除
                                for(var k in chosedProject){
                                    if(data.sapCode == chosedProject[k].sapCode ){
                                        chosedProject.splice(k,1)
                                    }
                                }
                                console.log(chosedProject,'exit');
                                chosedLayTable.reload('table-chosedProject',{
                                    data:chosedProject
                                });
                                layer.close(index);

                            });
                        }
                    });

                    // 清空客户
                    $("#removeCustomer-hook").on("click", function () {
                        chosedProject=[];
                        chosedLayTable.render({
                            elem: '#customInnerTable',
                            id: 'table-chosedProject',
                            height: '250',
                            width: "690",
                            title: '客户数据表',
                            cols: [[
                                {field: 'sapCode', title: 'sap编号', sort: true},
                                {field: 'custCnName', title: '客户名称'},
                                {fixed: 'right', title: '操作', toolbar: '#addBarDemo', width: 80}
                            ]],
                            data: [],
                            page: true
                        });
                    });

                    // 保存
                    $("#form-customer-hook #customGroup-add-hook")
                        .click(
                            function () {
                                var customerGroupName = $("#form-customer-hook input[name='custGroupName']").val();
                                console.log(customerGroupName)
                                if ($.trim(customerGroupName) == '') {
                                    layer.msg("请输入客户群名称");
                                    return false;
                                }

//                                var getChosedCustomer = $("#form-customer-hook #chosed-customer-hook");
                                var ret = [];
                                for(var i=0;i<chosedProject.length;i++ ){
                                    ret.push(chosedProject[i].sapCode)
                                }
//                                getChosedCustomer
//                                    .children(
//                                        ".customer-list")
//                                    .each(
//                                        function () {
//                                            var sapCode2 = $(
//                                                this)
//                                                .children(
//                                                    ".customerItem")
//                                                .attr(
//                                                    "sapCode");
//                                            ret
//                                                .push(sapCode2)
//                                        });
                                var dataparam = {
                                    custGroupId: custGroupId,
                                    name: customerGroupName,
                                    ctnCodes: ret
                                }

                                $.ajax({
                                    type: 'POST',
                                    url: '/vote/pmcustomergroup/changeRelation',
                                    data: JSON.stringify(dataparam),
                                    contentType: 'application/json',
                                    success: function (res) {
                                        layer.msg("修改成功",{icon:1,shade:0.3,time:1000,shadeClose:true},function(){
                                            win.close();
                                            location.reload();
                                        });
                                    },
                                    error: function () {
                                        layer
                                            .msg(
                                                "修改失败",
                                                {
                                                    icon: 5
                                                });
                                        win.close();
                                    },
                                    dataType: "json"
                                })

                                return false;
                            })

                    // 关闭
                    $("#form-customer-hook #customerGroup-close-hook")
                        .click(function () {
                            $(this).getWindow().close();
                            return false;
                        })

                })
    });
</script>