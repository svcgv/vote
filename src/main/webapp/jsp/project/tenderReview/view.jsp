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
	
		  <div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		         <label name="bidName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">首次报价（元）：</label>
		       <div class="layui-input-inline">
		         <label name="firstBidAmount"  class="layui-form-label"></label>
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">客户：</label>
		       <div class="layui-input-inline">
		          <label name="custCnName"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预估收入（元）：</label>
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
		      <label class="layui-form-label">预估利润率（%）：</label>
		       <div class="layui-input-inline">
		         <label name="predictProfitRate"  class="layui-form-label"></label>
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预付开始期限：</label>
		       <div class="layui-input-inline">
	         	<label name="predictPeriodStart"  class="layui-form-label"></label>
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预付结束期限：</label>
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
		      <label class="layui-form-label">付款点：</label>
		       <div class="layui-input-inline">
		           <label name="paymentPoint"  class="layui-form-label"></label>
		      </div>
		    </div>
	      <div class="layui-inline">
	       		 <label class="layui-form-label" style="width:170px!important;">工作任务及范围是否清晰：</label>
	       		 <div class="layui-input-inline">
	       		 	<!-- open 是开启 close 是关闭 (关闭时  设置 file-hook style="display:none;")    对应 isWorkAreaExplicit 数据字典 -->
			       <label name='isWorkAreaExplicit' class="layui-form-label"></label>
			    </div>
	       </div>
	       <div class="file-hook" style="width:95%;margin:0 auto;">
	          <!-- 反写已上传的数据 -->
		      <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr><th>文件名</th>
				        <th>大小</th>
				        <th>上传人</th>
				        <th>上传时间</th>
				        
				        <th></th>
				      </tr></thead>
				      <tbody id="wosFileList">
				      	
				      	
				      	
				      	
				      	<c:forEach items="${fileList}" var="app">
							<tr class="edit-wosUploaded">
						      	<td>${app.fileUploadName }</td>
						      	<td>${app.fileSize}</td>
						      <td>${app.usrName}</td>
						      <td>${app.createTime}</td>
						      
						      	<td><a href="${app.filePath}" download='${app.fileUploadName }'>下载</a></td>
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
	        <div class="layui-inline">
	       		 <label class="layui-form-label">评审记录：</label>
	       		 
	       		 <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr><th>评审人</th>
				        <th>评审结果</th>
				        <th>评审意见</th>
				        <th>评审时间</th>
				        
				      </tr></thead>
				      <tbody id="wosFileList">
				      	
				      	
				      	
				      	
				      	<c:forEach items="${reviewHis}" var="app">
							<tr class="edit-wosUploaded">
						      	<td>${app.reviewUserName }</td>
						      	<td>${app.result}</td>
						      <td>${app.commentDetail}</td>
						      <td>${app.modifyTime}</td>
						      
					      	</tr>
						</c:forEach>
				      </tbody>
				    </table>
				  </div>
				  
				  
	       </div>
		     
		     
		  </div>
		  <div class='tender-review-wrapper'>
		 <form class="layui-form" action="" lay-filter="form-detail"> 
		 <div class="layui-inline">
	      <label class="layui-form-label" >评审理由：</label>
	       <div class="layui-input-inline">
	        	<textarea name="commentDetail" placeholder="请输入评审理由" class="layui-textarea"></textarea>
	      </div>
	    </div>
		  
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	      <div class="layui-input-inline">
	         <input type="text" style='display:none' name="reviewId" name="selfName" readonly="readonly" value="${userName }"  autocomplete="off" class="layui-input form-control disabledColor">
	         <input type="text" style='display:none' name="reviewId" value="${reviewId }">
	         <input type="text" style='display:none' name="reviewType" value="00">
	       </div>
 	 	</div>
	  	
	  <!-- 	<div class="layui-inline">
	      <label class="layui-form-label" >评审意见：</label>
	       <div class="layui-input-inline">
	        <select name="result" lay-verify="required"> 数据字典
		        <option value="">请选择</option>
		        <option value="00" selected>同意</option>
		        <option value="01">不同意</option>
		      </select>
	      </div>
	    </div> -->
	    
	    
	    
	  </div>
	</form></div>
	<div class="layui-layer-btn layui-layer-btn-c">
		<a class="layui-layer-btn1" id="tender-accessReview">通过</a>
		<a class="layui-layer-btn1" id="tender_returnReview">退回</a>
    	<a class="layui-layer-btn1" id="customerGroup-close-hook">关闭</a>
    </div>
</div>
<script>
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
		 
		  
			// 保存 事件
			var act="${act}";// 区分是index页 form页 赋值问题
			var win=$(".tender-review-wrapper").getWindow();
			$(".formDetail-wrapper").on("click","#tender-accessReview",function(){
				
				submit('00')
			})
			$(".formDetail-wrapper").on("click","#tender_returnReview",function(){
				submit('01')
			})
			
		function submit(result){
				
				var formDatas=$(".tender-review-wrapper form").serializeObject();
				formDatas.result=result
				$.ajax({
					type:'POST',
					url:'/vote/pmreviewinfo/submit',
					 data: JSON.stringify(formDatas), 
					 contentType:'application/json',
					 dataType: "json",
					success:function(res){
						layer.msg("评审成功",{icon:1});
						win.close();
					},
					error:function(){
						layer.msg("评审失败",{icon:5});
						win.close();
					}
				})
			}
		
	// form 表单手动渲染
	  form.render();
		// 关闭
		$("#tender-addForm-hook #customerGroup-close-hook").click(function(){
			$(this).getWindow().close();
			return false;
		})
	
	})
});

</script>