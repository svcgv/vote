<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>

.formDetail-wrapper .customer-list{
	word-wrap:normal;
	word-break:keep-all;
	padding:5px;
	display: inline-block;
}
.formDetail-wrapper .layui-icon-close-fill{
	position:relative;
	top:1px;
}
</style>
<div id="product-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		  <div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">产品代码：</label>
		      <div class="layui-input-inline">
		          <label class="layui-form-label">${product.productCode}</label>
				  <input type="text" style='display:none' id="productId" name="productId" autocomplete="off" value="${product.productId}" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">产品名称：</label>
		      <div class="layui-input-inline">
		          <label class="layui-form-label">${product.productName}</label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">指导销售价：</label>
		       <div class="layui-input-inline">
		          <label id="productSuggestPrice" class="layui-form-label">${product.productSuggestPrice}</label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">开始销售日期：</label>
		       <div class="layui-input-inline">
		          <label class="layui-form-label">${product.startSaleDate}</label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">产品类型：</label>
		      <div class="layui-input-inline">
		        <select name="productType" lay-verify="required"  disabled="true" lay-filter="" class="form-control">
		        	 ${productType.ewTypeHtml }
		        </select>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">研发部门：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">${product.developmentDeptName}</label>
		      </div>
		       
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">研发负责人：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">${product.developmentManagerName}</label>
		      </div>
		       
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">项目引用列表：</label>
	           <%--<div class="layui-input-inline" id="chosed-project-hook" style="border:#e6e6e6 solid 1px;height:60px;overflow-y:auto;width:320px;">--%>
		     	 <%--<span class="customer-list">--%>
		    		<%--<span class="customerItem" projectId="123">交行项目</span>--%>
		    	<%--</span>--%>
		       <%--</div>--%>
		    </div>
			  <table class="layui-hide" id="projectTable-chosed" lay-filter="tableFilter" style="overflow:hidden;"></table>
	       <div class="layui-inline">
	       		 <label class="layui-form-label" style="width:80px!important;">备注：</label>
	       		 <div class="layui-input-block" style="margin-left:90px;width:410px;">
			      <textarea name="remark" readonly="readonly" class="layui-textarea form-control">${product.remark}</textarea>
			    </div>
	       </div>
		     
		     
		  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>

<script>
$(function(){
    productId = $('#productId')[0].value;
	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
            table = layui.table;
        $('#productSuggestPrice').text((parseInt($('#productSuggestPrice').text()*100)/100).toFixed(2));
		  form.render();
        var param = {"productId": productId};
        $.ajax({
            type: 'POST',
            url: '/vote/pmproductprojectrelation/list',
            data: JSON.stringify(param),
            contentType: 'application/json',
            success: function (res) {
                table.render({
                    id:"table-chosedProject",
                    elem: '#projectTable-chosed',
                    height:'350',
                    title: '项目群数据信息',
                    cols: [[
                        {field:'wbs', title:'项目编号', templet:function(d){
                            var jsonStr = JSON.stringify({"projectId":d.projectId,"wbs":d.wbs,"projectName":d.projectName});
                            return '<div class="jsonData" dataStr='+jsonStr+'>'+d.wbs+'</div>'
                        } },
                        {field:'projectName', title:'项目名称'}
                    ]],
                    cellMinWidth:'90',
                    data:res.page,
                    page: true
                });
            },
            dataType: "json"
        });


		var win=$("#product-addForm-hook").getWindow();
		// 关闭
		$("#product-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	});
});

</script>