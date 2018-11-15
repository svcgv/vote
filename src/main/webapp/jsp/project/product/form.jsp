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
		         <input type="text" name="productCode" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">产品名称：</label>
		      <div class="layui-input-inline">
		         <input type="text" name="productName" autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">指导销售价(元)：</label>
		       <div class="layui-input-inline">
		         <input type="text" id="productSuggestPrice" name="productSuggestPrice"  autocomplete="off"  class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">开始销售日期：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="startSaleDate" id="startSaleDate2" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">产品类型：</label>
		      <div class="layui-input-inline">
		        <select name="productType" lay-verify="required" lay-filter="" class="form-control">
		        	 ${productType.ewTypeHtml }
		        </select>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">研发部门：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="developmentDeptName" readonly="readonly"  autocomplete="off" class="layui-input form-control">
		          <input type="text" style='display:none' name="developmentDeptId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">研发负责人：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="developmentManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control">
		          <input type="text" style='display:none' name="developmentManagerId">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">项目引用列表：</label>
				 <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
					 <button type="button"  class="layui-btn layui-btn-sm" id="projectQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
				 </div>
	           <%--<div class="layui-input-inline" id="chosed-project-hook" style="border:#e6e6e6 solid 1px;height:60px;overflow-y:auto;width:320px;">--%>
			 <%--</div>--%>
			 </div>
			  <table class="layui-hide" id="projectTable-chosed" lay-filter="tableFilter" style="overflow:hidden;"></table>

			  <div class="layui-inline">
	       		 <label class="layui-form-label">备注：</label>
	       		 <div class="layui-input-block" style="margin-left:130px;width:323px;">
			      <textarea name="remark"  class="layui-textarea form-control"></textarea>
			    </div>
	       </div>
		     
		     
		  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="customGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script type="text/html" id="barFormDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    var chosedProject=[];
    console.log(chosedProject);
    var chosedLayTable=null;
$(function(){
	layui.use(['layer', 'form','laydate','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;

        chosedLayTable=layui.table;
		 //日期
	  laydate.render({
		    elem: "#startSaleDate2",
		    theme: 'molv',
		    type: 'datetime'
	 });
		
	// form 表单手动渲染
	  form.render();

        //table render
        chosedLayTable.render({
            id:"table-chosedProject",
            elem: '#projectTable-chosed',
            height:'350',
            title: '项目群数据信息',
            cols: [[
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

	  //自动填充小数点
        $(document).on('change', '#productSuggestPrice', function(data) {
            var value=(parseInt($(this).val()*100)/100).toFixed(2);
            console.log(value);
            $(this).val(value);
        });

	 // 选择机构
  $("#product-addForm-hook #orgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=add',
	  		title:"选择研发机构",
	  		width:"400"
	 });
	  
  });
  
  // 选择人员
  $("#product-addForm-hook #userQuery-hook").off("click").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=add',
	  		title:"选择研发人员",
	  		width:"700"
	 	 });
	}); 
  
  // 选择项目
  $("#product-addForm-hook #projectQuery-hook").on("click",function(){
	  	$.openWindow({
	  		url:'project',
	  		title:"选择项目",
	  		width:"700"
	 	 });
	}); 
	  
		var win=$("#product-addForm-hook").getWindow();
		// 保存
		$("#product-addForm-hook #customGroup-add-hook").click(function(){
			var customerGroupName=$("#product-addForm-hook input[name='productName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入产品名称");
				return false;
			}
			
			var ret=[];
            for(var i=0;i<chosedProject.length;i++ ){
                ret.push(chosedProject[i].projectId)
            }

			var formDatas=$("#product-addForm-hook form").serializeObject();
            formDatas=$.extend({},true,formDatas,{projectIds:ret});
            var newparam = {}
            for(var o in formDatas){
                 if(formDatas[o]){
                     newparam[o] = formDatas[o]
                 }
             }


			$.ajax({
				type:'POST',
				 contentType:'application/json',
				 dataType: "json",
				url:'/vote/pmproductinfo/save',
				data: JSON.stringify(newparam),
				success:function(res){
					layer.msg("新增成功",{icon:1});
					location.reload();
					win.close();
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					win.close();
				}
			})
			return false;
		})
		
		// 关闭
		$("#product-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});

</script>