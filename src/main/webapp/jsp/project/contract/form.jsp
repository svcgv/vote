<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
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
.layui-table tbody tr:hover{
	background:#fff;
}
#contract-addForm-hook .layui-form-label{
	width:140px!important;
	padding:9px 5px;
}
#contract-addForm-hook  .layui-btn{
	padding:0 9px;
	height:32px;
	line-height:32px;
}
</style>
<div id="contract-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		  <div class="layui-form-item" style="margin-bottom:0px;">
		     <%--<div class="layui-inline">--%>
		      <%--<label class="layui-form-label">合同编号：</label>--%>
		       <%--<div class="layui-input-inline">--%>
		         <%--<input type="text" name="contractCode"  autocomplete="off" class="layui-input form-control">--%>
		      <%--</div>--%>
		       <%--<span class="f-placeholder"></span>--%>
		    <%--</div>--%>
			  <div class="layui-inline">
				  <label class="layui-form-label">OA流程编号：</label>
				  <div class="layui-input-inline">
					  <input type="text" name="oaFlowCode"  autocomplete="off" class="layui-input form-control">
				  </div>
				  <span class="f-placeholder"></span>
			  </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">合同名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="contractName"  autocomplete="off" class="layui-input form-control">
		      </div>
		       <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">合同金额（元）：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="contractAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">税率（%）：</label>
		      <div class="layui-input-inline">
		      	 <input type="number" name="taxRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		       <span class="f-placeholder"></span>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">税后合同金额（元）：</label>
			       <div class="layui-input-inline">
			         <input type="number" name="afterTaxContractAmount" readonly autocomplete="off" class="layui-input form-control">
			      </div>
		       	  <span class="f-placeholder"></span>
		    </div>
			  
		    <div class="layui-inline">
		      <label class="layui-form-label">是否科委认定：</label>
		       <div class="layui-input-inline">
		          <select name="isAgree">
		          	<option value="">请选择</option>
		          	<option value="01" selected>是</option>
		          	<option value="02">否</option>
		          </select>
		      </div>
		       <span class="f-placeholder"></span>
		    </div>
		    		    
		    <div  class="layui-inline">
		   	   <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="sellDeptId">
		       </div>
		       <button type="button"  class="layui-btn layui-btn-sm" id="payDeptNameQuery-form" ><i class="layui-icon layui-icon-search"></i></button>
		     </div> 
		       
		      <div class="layui-inline">
			   <label class="layui-form-label">客户经理：</label>
			   <div class="layui-input-inline">
				   <input type="text" name="custManagerName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
				   <input type="text" style='display:none' name="custManagerId">
			   </div>
			   <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-form"><i class="layui-icon layui-icon-search"></i></button>
		   </div>
		    <div class="layui-inline">
		       <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="custId">
				   <input type="text" style='display:none' name="custSapCode">
		      </div>
	      	  <button type="button"  class="layui-btn layui-btn-sm" id="customerQuery-form" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>

		    <div class="layui-inline">
		      <label class="layui-form-label">公司代码：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="companyCode" readonly="readonly" autocomplete="off" class="layui-input form-control">
		      </div>
				<button type="button"  class="layui-btn layui-btn-sm" id="companyQuery-form" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>
				 <div class="layui-inline">
					 <label class="layui-form-label">项目名称：</label>
					 <div class="layui-input-inline">
						 <input type="text" name="projectName" readonly="readonly" autocomplete="off" class="layui-input form-control">
						 <input type="text" style='display:none' name="projectId">
						 <input type="text" style='display:none' name="wbs">
					 </div>
					 <button type="button"  class="layui-btn layui-btn-sm" id="projectQuery-hook" ><i class="layui-icon layui-icon-search"></i></button>
				 </div>
				 <div class="layui-inline">
					 <label class="layui-form-label">项目类型：</label>
					 <div class="layui-input-inline">
						 <input type="text" name="projectType" readonly="readonly" autocomplete="off" class="layui-input form-control">
					 </div>
				 </div>
		  </div>
	     <div class="layui-form-item" style="margin-bottom:0px;">
	     	<div class="layui-inline">
				  <label class="layui-form-label">合同开始日期：</label>
				  <div class="layui-input-inline">
					  <input type="text" name="contractStartTime" id="contractStartTime-form"  autocomplete="off" class="layui-input form-control hasDatepicker">
				  </div>
				   <span class="f-placeholder"></span>
			  </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">合同结束日期：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="contractEndTime" id="contractEndTime-form" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		       <span class="f-placeholder"></span>
		    </div>
			  <div class="layui-inline">
				  <label class="layui-form-label">签订日期：</label>
				  <div class="layui-input-inline">
					  <input type="text" name="signContractDate" id="signContractDate-form" autocomplete="off" class="layui-input form-control hasDatepicker">
				  </div>
				   <span class="f-placeholder"></span>
			  </div>
			 <div class="layui-inline">
				 <label class="layui-form-label">备注：</label>
				 <div class="layui-input-inline" style="width:600px;">
					 <textarea name="remark"  class="layui-textarea form-control"></textarea>
				 </div>
				 <span class="f-placeholder"></span>
			 </div>
	     </div>
		
      <div class="clearfix" style="margin-bottom:0px;;margin-left:10px;">
	     	   <div style="float:left;width: 140px;">
					<p class="layui-form-label">收款点信息：</p>
					<div>
						<button type="button"  class="layui-btn" id="addPayList-form" ><i class="layui-icon"></i>新增收款点</button>
					</div>
				</div>
				<div style="float:left;width:900px;">
			      <table class="layui-table palyListTable" style="width:900px" >
				      <thead>
				        <tr>
				        	<th style="white-space: nowrap;">序号</th>
							<th>收款编号</th>
					        <th>收款日期</th>
					        <th>收款金额</th>
					        <th>收款比例（%）</th>
					        <th>收款要求</th>
					        <th>操作</th>
				      	</tr>
				      </thead>
					      <tbody class="payList">
					     	  <tr class="listTmpl" style="display:none;">
					     	      <th class="paySortNum">1</th>
								  <th>
									  <div class="layui-input-inline">
										  <input type="text" name="payWbsCode" autocomplete="off" class="layui-input form-control">
									  </div>
								  </th>
						         <th>
						        	<div class="layui-input-inline">
						        		<input type="text" name="paymentDate" style="width:120px;" autocomplete="off" class="layui-input form-control paymentDate-hook hasDatepicker">
							      	</div>
							     </th>
						        <th>
						        	<div class="layui-input-inline">
								         <input type="number" name="paymentAmount"  autocomplete="off" class="layui-input form-control">
							      	</div>
							     </th>
							     <th>
						        	<div class="layui-input-inline">
								         <input type="number" name="paymentRate" style="width:90px;" autocomplete="off" class="layui-input form-control">
							      	</div>
							     </th>
							     <th>
						        	<div class="layui-input-inline">
								         <input type="text" name="payRequirement" style="min-width:150px;" autocomplete="off" class="layui-input form-control">
							      	</div>
							     </th>
							     <th>
						        	<div class="layui-input-inline" style="width:70px;">
								         <a class="layui-btn layui-btn-danger layui-btn-xs delete-row-hook">删除</a>
							      	</div>
							     </th>
					      	 </tr>
					      </tbody>
					</table>
				</div>
      </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="contract-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="contract-close-hook">关闭</a>
    </div>
</div>
<script type="text/html" id="barFormDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
$(function(){
	layui.use(['layer', 'form','laydate','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;

		 //日期
		  laydate.render({
			    elem: "#contractStartTime-form",
			    theme: 'molv'
		 });
        laydate.render({
            elem: "#contractEndTime-form",
            theme: 'molv'
        });
        laydate.render({
            elem: "#signContractDate-form",
            theme: 'molv'
        });
        // 绑定日历组件
        $(".palyListTable").on("mousedown",".paymentDate-hook",function(){
        	var _this=this;
        	 laydate.render({
                 elem: _this,
                 theme: 'molv',
                 type: 'month'
             });
        })


		// 新增付款点
        $("#contract-addForm-hook #addPayList-form").click(function(){
        	var tmpl=$(".palyListTable .listTmpl").clone(true);
        	var num =$(".payList tr:not('.listTmpl')").length;
        	$(tmpl).removeClass("listTmpl").show().find(".paySortNum").text(num+1);
        	$(".palyListTable .payList").append(tmpl);
        	win.resize();
        	
        });
         // 删除付款点
        $("#contract-addForm-hook .palyListTable").on("click",".delete-row-hook",function(){
        	var _this=this;
        	var thisTr=$(this).parents("tr");
        	layer.confirm('确认删除？', {
        		  yes: function(index, layero){
        			  thisTr.remove();
        			  layer.close(index);
        			  // 序号 重新写
        			  $("#contract-addForm-hook .palyListTable .payList").children("tr").each(function(i){
        				 $(this).find(".paySortNum").text(i);
        			  });
        		  }
        		});
        });
        // 比例金额互换
         $("#contract-addForm-hook .palyListTable").on("keyup","input[name='paymentAmount']",function(){
             var max=$("#contract-addForm-hook input[name='contractAmount']").val();
             if($.trim(max) ==''){
                 layer.msg("请输入合同金额");
                 return false;
             }
             var _account=$(this).val();
             console.log(_account)
             if(_account !=''){
                 var rate=_account/max*100;
       	  			$(this).parents("th").next("th").find("input[name='paymentRate']").val(rate.toFixed(2));
       	  		}
         });
         $("#contract-addForm-hook .palyListTable").on("keyup","input[name='paymentRate']",function(){
             var max=$("#contract-addForm-hook input[name='contractAmount']").val();
             if($.trim(max) ==''){
                 layer.msg("请输入合同金额");
                 return false;
             }
       	  		var rate=$(this).val();
       	  		if(rate !=''){
                    var account=rate /100 * max;
       	  			$(this).parents("th").prev("th").find("input[name='paymentAmount']").val(account.toFixed(2));
       	  		}
         });
		 
	// form 表单手动渲染
	  form.render();
        //输入值变更
        $(document).on('change', '#contract-addForm-hook input[name="contractAmount"]', function(data) {
            var amount = $("#contract-addForm-hook input[name='contractAmount']").val();
            var tax = $("#contract-addForm-hook input[name='taxRate']").val();
            var afterAmount = amount*(1-tax/100);
            $("#contract-addForm-hook input[name='afterTaxContractAmount']").val(afterAmount);
        });
        //输入值变更
        $(document).on('change', '#contract-addForm-hook input[name="taxRate"]', function(data) {
            var amount = $("#contract-addForm-hook input[name='contractAmount']").val();
            var tax = $("#contract-addForm-hook input[name='taxRate']").val();
            var afterAmount = amount*(1-tax/100);
            $("#contract-addForm-hook input[name='afterTaxContractAmount']").val(afterAmount);
        });


	  $("#contract-addForm-hook #payDeptNameQuery-form").click(function(){
		  $.openWindow({
		  		url:'org?act=form',
		  		title:"选择销售部门",
		  		width:"400"
		 });
	  });
	  $("#contract-addForm-hook #customerQuery-form").click(function(){
		  $.openWindow({
		  		url:'customer?act=form',
		  		title:"选择客户名称",
		  		width:"750"
		 });
		  
	  });
        $("#contract-addForm-hook #companyQuery-form").click(function(){
            $.openWindow({
                url:'company?act=form',
                title:"选择公司代码",
                width:"750"
            });

        });

        // 选择项目
        $("#contract-addForm-hook #projectQuery-hook").on("click",function(){
            $.openWindow({
                url:'project',
                title:"选择项目",
                width:"700"
            });
        });

        $("#contract-addForm-hook #custNameQuery-form").on("click",function(){
		  	$.openWindow({
		  		url:'user?act=form',
		  		title:"选择客户经理",
		  		width:"700"
		 	 });
		});
	  function getParam(){
			var queryParams=$("#contract-addForm-hook form").serializeObject();
          if(queryParams.payWbsCode){
              var rets=[];
              if(!$.isArray(queryParams.payWbsCode)){
            	  var ret={}
            	  ret.paymentCode=queryParams.payWbsCode;
            	  ret.paymentAmount=queryParams.paymentAmount;
            	  ret.paymentDate=queryParams.paymentDate;
            	  ret.remark=queryParams.payRequirement;
            	  ret.paymentRate=queryParams.paymentRate;
                  rets.push(ret);
              }else{
                  for(var j=0;j<queryParams.payWbsCode.length;j++){
                	  var ret={}
                	  ret.paymentCode=queryParams.payWbsCode[j];
                	  ret.paymentAmount=queryParams.paymentAmount[j];
                	  ret.paymentDate=queryParams.paymentDate[j];
                	  ret.remark=queryParams.payRequirement[j];
                	  ret.paymentRate=queryParams.paymentRate[j];
                      rets.push(ret);
                  }
              }

              delete queryParams.payWbsCode
              delete queryParams.paymentAmount
              delete queryParams.paymentDate
              delete queryParams.payRequirement
              delete queryParams.paymentRate
              queryParams=$.extend({},true,queryParams,{paymentPoint:rets});
          }
          var projects=[];
          if(queryParams.projectId){
              projects.push(queryParams.projectId);
              queryParams=$.extend({},true,queryParams,{projectIds:projects});
          }
          var newParam = {}
			  for(var i in queryParams){
				  if(queryParams[i]){
					  newParam[i] = queryParams[i]
				  }
			  }
			  return newParam
		}
		var win=$("#contract-addForm-hook").getWindow();
		// 保存
		$("#contract-addForm-hook #contract-add-hook").click(function(){
			
			var customerGroupName=$("#contract-addForm-hook input[name='contractName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入合同名称");
				return false;
			}
			
			
			$.ajax({
				type:'POST',
				url:'/vote/pmcontractinfo/save',
				contentType:'application/json',
				data: JSON.stringify(getParam()),
				success:function(res){
                    layer.msg("新增成功",{icon:1,shade:0.3,time:1000,shadeClose:true},function(){
                        win.close();
                        location.reload();
                    });
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					win.close();
				}
			})
			return false;
		});
		
		// 关闭
		$("#contract-addForm-hook #contract-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});
</script>