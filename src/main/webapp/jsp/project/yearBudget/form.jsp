<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#budget-addForm-hook .layui-form-label {
    width: 130px!important;
}
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
.layui-table-iptMoney{
	padding:0 ;
	text-align:center;
	border-radius:0;
	box-shadow:1px 1px 20px rgba(0,0,0,.15);
	border-color:#5FB878;
	height:28px;
}
#budget-addForm-hook .layui-table-body.layui-table-main{
	min-height: 80px;
}
#budget-addForm-hook .layui-table-body .layui-table{
	min-height: 50px;
}
</style>
<div id="budget-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
	  <div class="layui-form-item">
		  	<div class="layui-inline">
		      <label class="layui-form-label">是否新客户：</label>
		       <div class="layui-input-inline">
		          <select name="isNewCustomer" lay-verify="required"  lay-filter="isNewCustomer-form" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01">是</option>
			        	<option value="02" selected>否</option>
				  </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="custName"  autocomplete="off" readonly="readonly" class="layui-input form-control disabledColor">
		         <input  type="text" style='display:none' name="custId"/>
		      </div>
		      <div class="layui-input-inline layui-btn-container showCustomer-hook" style="margin-left:15px;width: 50px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
		      </div>
		    </div>
		    
	    	<div class="layui-inline">
		      <label class="layui-form-label">是否新项目：</label>
		       <div class="layui-input-inline">
		          <select name="isNewProject" lay-verify="required" id="isNewProject-hook" lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01">是</option>
			        	<option value="02" selected>否</option>
				  </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">WBS编号：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="wbsCode"  autocomplete="off" readonly="readonly" class="layui-input form-control disabledColor">
		      </div>
		      <div class="layui-input-inline layui-btn-container showWBS-hook" style="margin-left:15px;width: 50px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="WBSQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="projectName" readonly="readonly" autocomplete="off"  class="layui-input form-control disabledColor">
		      </div>
		    </div>
		    
		    
		     <div class="layui-inline">
			       <label class="layui-form-label">项目类型：</label>
			       <div class="layui-input-inline">
			          <select name="projectType" lay-verify="required" lay-filter="projectType-filter" class="form-control">
				        	<option value="">请选择</option>
				        	<option value="01" selected>项目</option>
				        	<option value="02">产品</option>
				        	<option value="03" >人力</option>
					  </select>
			      </div>
			      
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">产品列表：</label>
		      <div class="layui-input-inline" style="width:565px;">
		       <textarea type="text" name="productList"  style="height:80px;"  autocomplete="off" class="layui-input form-control"></textarea>
		       <div id="chosedProduct-hook" class="form-control disabledColor" style="height:80px;display:none;"></div>
		      </div>
		      <div class="layui-input-inline layui-btn-container product-hook" style="display:none;margin-left:15px;width: 50px;">
			      	 <button type="button"  class="layui-btn layui-btn-sm" id="productQuery-hook" title="选择" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
			      	 <button type="button"  class="layui-btn layui-btn-sm" id="productView-hook" title="点我查看" style="margin-right:15px;margin-top:5px;"><i class="layui-icon layui-icon-edit" ></i></button>
			   </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">收入来源(Revenue source)：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="revenueSource"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">实体(Entity)：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="entity"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">合同编码(Contract)：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="contract"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">PO#/SOW#：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="poSow"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户经理(Owner)：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="owner"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline">
		      	<label class="layui-form-label">税种：</label>
		        <div class="layui-input-inline">
		          <select name="taxes" lay-verify="required" lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>A</option>
			        	<option value="02">B</option>
			        	<option value="03" >C</option>
			        	<option value="04" >D</option>
			        	<option value="05" >E</option>
			        	<option value="06" >F</option>
			        	<option value="07" >G</option>
			        	<option value="08" >H</option>
			        	<option value="09" >I</option>
				  </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		       <label class="layui-form-label">Rev Recognition Method：</label>
		       <div class="layui-input-inline">
		          <select name="revRecognitionMethod" lay-verify="required" lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>T&M</option>
			        	<option value="02">FA</option>
			        	<option value="03" >Others</option>
				  </select>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		       <label class="layui-form-label">区域(Region)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="region"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		      <div class="layui-inline">
		       <label class="layui-form-label">结算币种(Currency)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="currency"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">税率（%）：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="taxRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">毛利率（%）：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="grossRate"  autocomplete="off" class="layui-input form-control">
			      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">总收入(Total Rev)：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">税后合计：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="afterTax"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     
		     
		     
		</div>
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>客户收入汇总（Clients Summary）</legend>
	</fieldset>
	 <table id="custRevSummary" lay-filter="calcRev"></table>
	 
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn0" id="customGroup-add-hook" style="background:#009688;border-color:#009688;">保存</a>
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>

<script type="text/html" id="iptMoneyTpl">
  <input class="layui-input layui-table-iptMoney"/>
</script>
<script>
//客户收入汇总 初始化值 全局变量
var customerData=[{'custCode':'','custName':'','totalRev':'0'}];
var table2=null;
	layui.use(['layer', 'form','laydate','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;
		// 设置全局变量
	  	  table2=layui.table;
		 //日期
		  laydate.render({
			    elem: "#predictPeriodDate-edit",
			    theme: 'molv',
			    type: 'datetime'
		 });
		console.log(table2)
	// form 表单手动渲染
	  form.render();
	/*
	*   业务功能：
	*	选择新客户 ，则自动选择新项目
	*   提供已有客户可选择，若系新客户，则不选，手工输入客户名称
	*	
	*/
	
  form.on('select(isNewCustomer-form)', function(data){
	  if(data.value == "01"){
		  var select= 'dd[lay-value=01]';
		  $('#budget-addForm-hook #isNewProject-hook').siblings("div.layui-form-select").find('dl').find(select).click();
		  // 隐藏客户选择按钮
		  $("#budget-addForm-hook .showCustomer-hook").hide();
		  // 客户输入框 不可写 清除已选
		  $("#budget-addForm-hook input[name='custName']").removeAttr("readonly",true).removeClass("disabledColor");
		  $("#budget-addForm-hook input[name='custName']").val("");
		  $("#budget-addForm-hook input[name='custId']").val("");
		  // 隐藏 WBS 选择按钮
		  $("#budget-addForm-hook .showWBS-hook").hide();
		  $("#budget-addForm-hook input[name='wbsCode']").removeAttr("readonly",true).removeClass("disabledColor");
		  $("#budget-addForm-hook input[name='wbsCode']").val("");
		  // 项目名称
		  $("#budget-addForm-hook input[name='projectName']").removeAttr("readonly",true).removeClass("disabledColor");
		  $("#budget-addForm-hook input[name='projectName']").val("");
		  
		 
		  
	  }else{
		  // customer
		  $("#budget-addForm-hook .showCustomer-hook").show();
		  $("#budget-addForm-hook input[name='custName']").val('').attr("readonly",true).addClass("disabledColor");
		  // wbs
		  $("#budget-addForm-hook .showWBS-hook").show();
		  $("#budget-addForm-hook input[name='wbsCode']").attr("readonly",true).addClass("disabledColor");
		  // 项目名称
		  $("#budget-addForm-hook input[name='projectName']").attr("readonly",true).addClass("disabledColor");
		  
		  
	  }
	  // 客户收入汇总（Clients Summary）table重绘
	  table2.reload('custRevSummaryTableID',{
					data:[{'custCode':'','custName':'','totalRev':'0'}]
			})
  
  });

   /*
   *  项目类型选择产品，则产品名称列提供产品信息清单供选择
   */
  form.on('select(projectType-filter)', function(data){
	  if(data.value == "02"){ // 产品字典
		  var select= 'dd[lay-value=02]';
			// 显示产品选择按钮
		  $("#budget-addForm-hook .product-hook").show();
		  $("#budget-addForm-hook textarea[name='productList']").hide();
		  $("#budget-addForm-hook #chosedProduct-hook").show();
		  
	  }else{
		  $("#budget-addForm-hook .product-hook").hide();
		  $("#budget-addForm-hook textarea[name='productList']").show();
		  $("#budget-addForm-hook #chosedProduct-hook").html("").hide();
	  }
  });
  
  //  手动填写客户名称时 对应 table2 reload
	$("#budget-addForm-hook input[name='custName']").blur(function(){
		var _custName=$.trim($(this).val())
		if(_custName != ""){
			table2.reload('custRevSummaryTableID',{
				data:[{'custCode':'','custName':_custName,'totalRev':'0'}]
			})
		}
	})
  
  	
	$("#budget-addForm-hook #custNameQuery-hook").click(function(){
		 $.openWindow({
		  		url:'customer?act=addCust',
		  		title:"选择客户",
		  		width:"700"
		 });
	});
	$("#budget-addForm-hook #WBSQuery-hook").click(function(){
		 $.openWindow({
		  		url:'wbs?act=addCust',
		  		title:"选择WBS编号",
		  		width:"700"
		 });
	});
	// 选择产品
	$("#budget-addForm-hook #productQuery-hook").click(function(){
		 $.openWindow({
		  		url:'product?act=addCust',
		  		title:"选择产品",
		  		width:"700"
		 });
	});
	// 查看产品
	$("#budget-addForm-hook #productView-hook").click(function(){
		 $.openWindow({
		  		url:'product?act=viewProduct',
		  		title:"查看产品",
		  		width:"700"
		 });
	});
	
		
	  table2.render({
		  elem: '#custRevSummary',
		  id:"custRevSummaryTableID",
		  cols: [
		         [ //标题栏
		            {field: 'custName', title: '客户（Clients）', width: 180, rowspan: 2,templet: function(d){
		            	return '<div><span custCode='+ d.custCode +'>'+d.custName+'</span></div>'
		            }} 
		           ,{field: 'totalRev', title: 'Total Rev', width: 100, rowspan: 2}
		           ,{align: 'center', title: 'Revenue', colspan: 12} //colspan即横跨的单元格数，这种情况下不用设置field和width
		         ], [
		            	{field: 'jan', title: 'Jan',templet:function(d){ var num=typeof d.jan =="undefined" ? '':d.jan;  return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'feb', title: 'Feb', templet:function(d){ var num=typeof d.feb =="undefined" ? '':d.feb; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'mar', title: 'Mar', templet:function(d){var num=typeof d.mar =="undefined" ? '':d.mar; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'apr', title: 'Apr', templet:function(d){var num=typeof d.apr =="undefined" ? '':d.apr; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'may', title: 'May',templet:function(d){var num=typeof d.may =="undefined" ? '':d.may; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'jun', title: 'Jun',templet:function(d){var num=typeof d.jun =="undefined" ? '':d.jun; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'jul', title: 'Jul',templet:function(d){var num=typeof d.jul =="undefined" ? '':d.jul; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'aug', title: 'Aug',templet:function(d){var num=typeof d.aug =="undefined" ? '':d.aug; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'sep', title: 'Sep', templet:function(d){var num=typeof d.sep =="undefined" ? '':d.sep; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'oct', title: 'Oct', templet:function(d){var num=typeof d.oct =="undefined" ? '':d.oct; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'Nov', title: 'Nov',templet: function(d){var num=typeof d.nov =="undefined" ? '':d.nov; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			           ,{field: 'dec', title: 'Dec', templet:function(d){var num=typeof d.dec =="undefined" ? '':d.dec; return '<input value="'+num+'" class="layui-input layui-table-iptMoney"/>'}}
			         ]
		  ],
		  cellMinWidth:100,
		  data:customerData
	  });
	
	  var result=[];// 保存数据
	  
	 $("#budget-addForm-hook .layui-table-iptMoney").blur(function(){
		 var _val=$.trim($(this).val());
		 if( _val !="" && !/^\d+(\.\d{1,3})?$/.test(_val)){
				$(this).val(" ")
			  layer.msg("请输入非负数字，最多可保留3位小数");
			  return false;
		  }
		 var _iptMoneys=$(this).parents("tr").find(".layui-table-iptMoney");
		 var _total=0;
		 _iptMoneys.each(function(){
			 var _val=$.trim($(this).val()) == "" ? 0 :parseFloat($(this).val());
			 _total+=_val;
		 });
		 $(this).parents("tr").find("td[data-field='totalRev']").children("div").text(_total);
		 // save data
		 var field=$(this).parents("td").attr("data-field");
		 var obj={}
		 obj[field] = _val;
		 result.push(obj);
	 })
  
	  
		var win=$("#budget-addForm-hook").getWindow();
		// 保存
		$("#budget-addForm-hook #customGroup-add-hook").click(function(){
			console.log(result,'revenue 表格数据');
			
			var customerGroupName=$("#budget-addForm-hook input[name='custName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入客户名称");
				return false;
			}
			
			
			var formDatas=$("#budget-addForm-hook form").serializeObject();
			// 保存产品列表数据
			var productList=$("#budget-addForm-hook #chosedProduct-hook").children(".customer-list");
			var productListIds=[];
			var productListNames=[];
			if(productList.length > 0){
				productList.each(function(){
					var id=$(this).children(".customerItem").attr("productId");
					var name=$(this).children(".customerItem").text();
					productListIds.push(id);
					productListNames.push(name);
				})
				// 存到data中
				formDatas.productListIds = productListIds.join(",");
				formDatas.productListNames = productListNames.join(",");
			}
			
			
			// 保存数据 revenue 月账单
			for(var i in result){
				var obj=result[i];
				formDatas=$.extend({},true,formDatas,obj);
			}
			
			console.log(formDatas,'save data');
			return;
			$.ajax({
				type:'POST',
				url:'save',
				data:{
					queryParams:formDatas
				},
				success:function(res){
					layer.msg("新增成功",{icon:1});
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
		$("#budget-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})

</script>