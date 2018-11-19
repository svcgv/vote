<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>
<style>
#tender-addForm-hook .layui-form-label {
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
</style>
<div id="tender-addForm-hook" class="formDetail-wrapper" style="margin-top:10px;">
	<form class="layui-form" action="" lay-filter="form-detail">
		  <div class="layui-form-item">
			  <div class="layui-inline">
			      <label class="layui-form-label">投标编号：</label>
			       <div class="layui-input-inline">
			         <label type='text' readonly='true' name="bidCode"  class="layui-form-label"></label>
			      </div>
			    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		         <label type='text' readonly='true' name="bidName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">首次报价（元）：</label>
		       <div class="layui-input-inline">
		         <label type='text' readonly='true' name="firstBidAmount"  class="layui-form-label"></label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		          <label name="custCnName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预估合同金额：</label>
		       <div class="layui-input-inline">
		         <label name="predictAmount"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估成本（元）：</label>
		      <div class="layui-input-inline">
		     	 <label name="predictCost"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估工作量：</label>
		      <div class="layui-input-inline">
		     	 <label name="predictWorkLoad"  class="layui-form-label"></label>
		      </div>
		    </div> 
		    <div class="layui-inline">
		      <label class="layui-form-label">预估利润（元）：</label>
		      <div class="layui-input-inline">
		     	 <label name="predictProfit"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		    
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估利润率（%）：</label>
		       <div class="layui-input-inline">
		         <label name="predictProfitRate"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">项目开始时间：</label>
		       <div class="layui-input-inline">
	         	<label name="predictPeriodStart"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">项目结束时间：</label>
		       <div class="layui-input-inline">
	         	<label name="predictPeriodEnd"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">交付部门：</label>
		       <div class="layui-input-inline">
		         <label name="constructionDeptName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">交付部门负责人：</label>
		       <div class="layui-input-inline">
		         <label name="constructionDeptManagerName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		         <label name="sellDeptName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">销售部门负责人：</label>
		       <div class="layui-input-inline">
		         <label name="sellDeptManagerName"  class="layui-form-label"></label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		           <label name="custManagerName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">技术总监：</label>
		       <div class="layui-input-inline">
		           <label name="technicalDirectorName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">项目经理：</label>
		       <div class="layui-input-inline">
		           <label name="projectManagerName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">付款点：</label>
		       <div class="layui-input-inline">
		           <label name="paymentPoint"  class="layui-form-label"></label>
		      </div>
		    </div>
	     <!--  <div class="layui-inline">
	       		 <label class="layui-form-label" style="width:170px!important;">工作任务及范围是否清晰：</label>
	       		 <div class="layui-input-inline">
	       		 	open 是开启 close 是关闭 (关闭时  设置 file-hook style="display:none;")    对应 isWorkAreaExplicit 数据字典
			       <label class="layui-form-label"></label>
			    </div>
	       </div> -->
	       <div class="file-hook" style="width:95%;margin:0 auto;">
	          <!-- 反写已上传的数据 -->
		      <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr><th>文件名</th>
				        <th>大小</th>
				        <th>文件类型</th>
				       
				        <th>状态</th>
				        <th> 上传人</th>
				        <th>上传时间</th>
				        <th>操作</th>
				      </tr></thead>
				      <tbody id="wosFileList">
				      <c:forEach items="${file}" var="fileInfo" >
				      	<tr class="edit-wosUploaded">
					      	<td>${fileInfo.fileUploadName}</td>
					      	<td>${fileInfo.fileSize}</td>
					      	<td>
					      		<c:if test="${fileInfo.fileBusinessType=='00' }">
									招标文件
								</c:if>
								<c:if test="${fileInfo.fileBusinessType=='01' }">
									客户需求文件
								</c:if>
								<c:if test="${fileInfo.fileBusinessType=='02' }">
									内部评审文件
								</c:if>
							</td>
					      	<td>已上传</td>
					      	 <td>${fileInfo.usrName}</td>
						      <td>${fileInfo.createTime}</td>
					      	<td><a href="/vote/pmfile/download?id=${fileInfo.fileId}">下载</a></td>
					      	
				      	</tr>
				      	</c:forEach>
				      </tbody>
				    </table>
				  </div>
		  	 </div>
	       <div class="layui-inline">
	       		 <label class="layui-form-label">备注：</label>
	       		 <div class="layui-input-inline" style="width:323px;">
			      <label name="remark"  class="layui-form-label"></label>
			    </div>
	       </div>
	       <!-- 未评审时不显示评审记录 -->
	        <div class="layui-inline" style='margin:40px 30px'>
	       		 <label class="layui-form-label">评审记录：</label>
	       		  <table class="layui-table" id='reviewViewHisTable'></table>
	       </div>
		     
		     
		  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script>
var reviewHis = ${reviewHis}
$(function(){
	layui.use(['layer', 'form','laydate','table'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate,
	  	 table=layui.table;
		 //日期
	  laydate.render({
		    elem: "#predictPeriodDate2",
		    theme: 'molv',
		    type: 'datetime'
	 });
		 var pmConfirmBid = JSON.parse('${pmConfirmBid}');
		 console.log(pmConfirmBid);
		 for (var property in pmConfirmBid) {
		 	$("#tender-addForm-hook label[name='"+property+"']").text(pmConfirmBid[property]);

		 }
	// form 表单手动渲染
	  form.render();
		// 关闭
		$("#tender-addForm-hook #customerGroup-close-hook").click(function(){
			$(this).getWindow().close();
			return false;
		})
		
		table.render({
	  	  	id:"reviewViewHisTable",
	  	    elem: '#reviewViewHisTable',
	  	    //url:'custom.json',
	  	    height:'120',
	  	    width:'100%',
	  	    title: '评审历史纪录',
	  	    cols: [[
		  	      {field:'reviewUserName', title:'评审人', width:130},
		  	     
		  	    {field:'result', title: '评审结果', width: 120
		      	      ,templet: function(d){
		      	    	if(d.result=='00'){
		      	        	return '同意'
		      	        }
		      	    	if(d.result=='01'){
		      	        	return '退回'
		      	        }
		      	    	else{
		      	    		return '数据待完善'
		      	    	}
		      	      },rowspan: 2
		      	    },
		  	      {field:'commentDetail', title:'评审意见', width:200},
		  	      {field:'modifyTime', title:'评审时间', width:150,sort:true}
	  	    ]],
	  	    cellMinWidth:'100',
	  	    data:reviewHis,
	  	    page: false
	  	  	})
	
	})
});

</script>