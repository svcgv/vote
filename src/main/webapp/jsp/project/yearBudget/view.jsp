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
		          <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		      
		    </div>
		    
	    	<div class="layui-inline">
		      <label class="layui-form-label">是否新项目：</label>
		       <div class="layui-input-inline">
		          <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">WBS编号：</label>
		      <div class="layui-input-inline">
		       <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		      <div class="layui-input-inline">
		       	<label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    
		     <div class="layui-inline">
			       <label class="layui-form-label">项目类型：</label>
			       <div class="layui-input-inline">
			          <label class="layui-form-label">反写</label>
			      </div>
			      
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">产品列表：</label>
		      <div class="layui-input-inline" style="width:565px;">
		      	<label class="layui-form-label">反写</label>
		      </div>
		      
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">收入来源(Revenue source)：</label>
		      <div class="layui-input-inline">
		       	<label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">实体(Entity)：</label>
		      <div class="layui-input-inline">
		       	<label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">合同编码(Contract)：</label>
		      <div class="layui-input-inline">
		       	<label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">PO#/SOW#：</label>
		      <div class="layui-input-inline">
		     	 <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">客户经理(Owner)：</label>
		      <div class="layui-input-inline">
		       	<label class="layui-form-label">反写</label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      	<label class="layui-form-label">税种：</label>
		        <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		       <label class="layui-form-label">Rev Recognition Method：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		       <label class="layui-form-label">区域(Region)：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		      <div class="layui-inline">
		       <label class="layui-form-label">结算币种(Currency)：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">税率（%）：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">毛利率（%）：</label>
			       <div class="layui-input-inline">
			         <label class="layui-form-label">反写</label>
			      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">总收入(Total Rev)：</label>
		       <div class="layui-input-inline">
		         <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">税后合计：</label>
		       <div class="layui-input-inline">
		          <label class="layui-form-label">反写</label>
		      </div>
		    </div>
		     
		     
		     
		</div>
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>客户收入汇总（Clients Summary）</legend>
	</fieldset>
	 <table id="custRevSummary" lay-filter="calcRev"></table>
	 
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>

<script>
//客户收入汇总 初始化值 全局变量
var customerData=[{'custCode':'111111','custName':'银行','totalRev':'12','jan':'12','feb':'23','mar':'45','nov':'66'}];
	layui.use(['layer', 'form','laydate','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate;
		// 设置全局变量
	  	 var  table2=layui.table;
		 
	// form 表单手动渲染
	  form.render();	
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
			            {field: 'jan', title: 'Jan',templet:function(d){ var num=typeof d.jan =="undefined" ? '':d.jan;  return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'feb', title: 'Feb', templet:function(d){ var num=typeof d.feb =="undefined" ? '':d.feb; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'mar', title: 'Mar', templet:function(d){var num=typeof d.mar =="undefined" ? '':d.mar; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'apr', title: 'Apr', templet:function(d){var num=typeof d.apr =="undefined" ? '':d.apr; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'may', title: 'May',templet:function(d){var num=typeof d.may =="undefined" ? '':d.may; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'jun', title: 'Jun',templet:function(d){var num=typeof d.jun =="undefined" ? '':d.jun; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'jul', title: 'Jul',templet:function(d){var num=typeof d.jul =="undefined" ? '':d.jul; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'aug', title: 'Aug',templet:function(d){var num=typeof d.aug =="undefined" ? '':d.aug; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'sep', title: 'Sep', templet:function(d){var num=typeof d.sep =="undefined" ? '':d.sep; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'oct', title: 'Oct', templet:function(d){var num=typeof d.oct =="undefined" ? '':d.oct; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'Nov', title: 'Nov',templet: function(d){var num=typeof d.nov =="undefined" ? '':d.nov; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
			           ,{field: 'dec', title: 'Dec', templet:function(d){var num=typeof d.dec =="undefined" ? '':d.dec; return '<input readonly="readonly" value="'+num+'" class="layui-input layui-table-iptMoney disabledColor"/>'}}
				    ]
		  ],
		  cellMinWidth:100,
		  data:customerData
	  });

		var win=$("#budget-addForm-hook").getWindow();
		// 关闭
		$("#budget-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})

</script>