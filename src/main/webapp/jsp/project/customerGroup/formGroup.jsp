<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    .customGroup-form-wrapper .layui-form-label {
        width: 100px!important;
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
    <div style="width:650px;margin-left:10px;"><table class="layui-hide" id="customTable" lay-filter="custom" style="overflow:hidden;"></table></div>
    <div class="layui-layer-btn layui-layer-btn-c">
        <a class="layui-layer-btn0" id="save-hook" style="background:#009688;border-color:#009688;">保存</a>
        <a class="layui-layer-btn1" id="close-hook">关闭</a>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        var testData = [];
//一般直接写在一个js文件中
        layui.use(['layer', 'form', 'laydate', 'table'], function () {
            var layer = layui.layer,
                form = layui.form,
                tableGroup = layui.table;
            var queryParams = $("#formGroup-query-form").serializeObject();
            var newParams={"queryStr":JSON.stringify(queryParams)};
            $.ajax({
             type: 'POST',
             url: '/vote/pmcustomerinfo/list',
             data: JSON.stringify(newParams),
             contentType:'application/json',
             success: function(res){
             testData=res.page;
             tableGroup.render({
             elem: '#customTable',
             height:'250',
             cols: [[
             {type: 'checkbox' },
                 {field: 'sapCode', title: 'sap编号',sort:true,templet:function(d){
                     var jsonStr = JSON.stringify({"sapCode":d.sapCode,"custCnName":d.custCnName});
                     return '<div class="jsonData" dataStr='+jsonStr+'>'+d.sapCode+'</div>'
                 } },
             {field:'custCnName', title:'客户名称'},
             ]],
             cellMinWidth:'90',
             data:testData,
             page: true
             });},
             dataType: "json"
             });
            // table render
//            tableGroup.render({
//                elem: '#customTable',
//                id: 'customerGroup-table',
//                url: '/vote/pmcustomerinfo/list',
//                method: 'post',
//                where: {
//                    queryStr: JSON.stringify(queryParams)
//                },
//                contentType: 'application/json',
//                response: {
//                    dataName: 'page'
//                },
//                height: '250',
//                width: "690",
//                title: '客户数据表',
//                cols: [[
//                    {type: 'checkbox'},
//                    {field: 'sapCode', title: '客户SAP编号', sort: true},
//                    {field: 'custCnName', title: '客户名称'}
//
//                ]],
//                page: true
//            });

            //复选框选中监听,将选中的id 设置到缓存数组,或者删除缓存数组
            var ids=[];
            tableGroup.on('checkbox(custom)', function (obj) {
                var tempArray=[];
                var count=0;
                var tempId;
                $(".customGroup-form-wrapper .layui-table-body table.layui-table tbody tr").each(function(){
                    var dataStr=$(this).children("td").eq(1).find(".jsonData").attr("dataStr");
                    var chk=$(this).find(".laytable-cell-checkbox");
                    var obj=JSON.parse(dataStr);
                    var isChecked=chk.find(".layui-form-checkbox").hasClass("layui-form-checked");
                    if(!isChecked){
                        count=count+1;
                        tempId=obj.sapCode;
                    }
                    tempArray.push(obj)
                })
                console.log(obj,'click');
                console.log(obj.checked,'是否选中');
                if(obj.checked==true){
                    if(obj.type=='one' && count!=0){// 选中一个
                        ids.push(obj.data.sapCode);
                        console.log(ids);
                    }else{ // 全选
                        for(var i=0;i<tempArray.length;i++){
                            if($.inArray(tempArray[i].sapCode, ids) == -1) {
                                ids.push(tempArray[i].sapCode);
                            }
                        }
                        console.log(ids);
                    }
                }else{
                    if(obj.type=='one'){ // 取消选中的
                        if(count==1){
                            for(var i=0;i<ids.length;i++){
                                if(ids[i]==tempId){
                                    ids.remove(i);
                                }
                            }
                        }else{
                            for(var i=0;i<ids.length;i++){
                                if(ids[i]==obj.data.sapCode){
                                    ids.remove(i);
                                }
                            }
                        }
                        console.log(ids);
                    }else{// 取消全选
                        //这方法也能返回数组下标
                        //i = $.inArray(tempArray[j].projectId, ids);
                            for(var j=0;j<tempArray.length;j++){
                                if($.inArray(tempArray[j].sapCode, ids) != -1){
                                    ids.remove($.inArray(tempArray[j].sapCode, ids));
                                }
                            }
                        console.log(ids);
                    }
                }
            });
            Array.prototype.remove=function(dx)
            {
                if(isNaN(dx)||dx>this.length){return false;}
                for(var i=0,n=0;i<this.length;i++)
                {
                    if(this[i]!=this[dx])
                    {
                        this[n++]=this[i]
                    }
                }
                this.length-=1
            }

            // 保存 事件
            var act = "${act}";
            var win = $(".customGroup-form-wrapper").getWindow();
            var getExitCustomer = $("#form-customer-hook #chosed-customer-hook");
            var getExittable = $("#form-customer-hook #customInnerTable");
            $(".customGroup-form-wrapper").on("click", "#save-hook", function () {
                // 遍历选中的CheckBox
                var tempArray=[];
                for(var i=0;i<ids.length;i++){
                    for(var j=0;j<testData.length;j++){
                        if(ids[i]==testData[j].sapCode){
                            tempArray.push(testData[j]);
                        }
                    }
                }
                for(var k=0;k<tempArray.length;k++){
                    var flag= true;
                    for(var j in chosedProject){
                        var proId=chosedProject[j].sapCode;
                        if(tempArray[k].sapCode == proId){
                            flag= false;
                            continue;
                        }
                    }
                    if(flag){
                        chosedProject.push(tempArray[k]);
                    }

                }
                console.log(chosedProject);
                chosedLayTable.reload('table-chosedProject',{
                    data:chosedProject
                });
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
                var newParams={"queryStr":JSON.stringify(queryParams)};
//                var newparam = {}
//                for (var o in queryParams) {
//                    if (queryParams[o]) {
//                        newparam[o] = queryParams[o]
//                    }
//                }
                $.ajax({
                    type: 'POST',
                    url: '/vote/pmcustomerinfo/list',
                    data: JSON.stringify(newParams),
                    contentType:'application/json',
                    success: function(res){
                        testData=res.page;
                        tableGroup.render({
                            elem: '#customTable',
                            height:'250',
                            cols: [[
                                {type: 'checkbox' },
                                {field: 'sapCode', title: 'sap编号',sort:true,templet:function(d){
                                    var jsonStr = JSON.stringify({"sapCode":d.sapCode,"custCnName":d.custCnName});
                                    return '<div class="jsonData" dataStr='+jsonStr+'>'+d.sapCode+'</div>'
                                } },
                                {field:'custCnName', title:'客户名称'},
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



