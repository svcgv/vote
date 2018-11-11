<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<style>
.custom-form-wrapper .layui-form-label{width:130px;}
.custom-form-view input{
	border:none;
	  -webkit-box-shadow: none!important;
          box-shadow: none!important;
  -webkit-transition: none!important;
       -o-transition: none!important;
          transition: none!important;
	
}
.custom-form-view .layui-disabled{
	color:#00004F!important;
}
</style>

<div class="custom-form-wrapper" style="margin-top:10px;">
	
<c:if test="${act =='add'}">
	<form class="layui-form form-add" action="" lay-filter="customer-form">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">公司实体编号：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="companyCode" autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
 	 	<div class="layui-inline">
	       <label class="layui-form-label">公司实体名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="companyName" autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
 	 	<%--<div class="layui-inline">--%>
	       <%--<label class="layui-form-label">公司实体地址：</label>--%>
	       <%--<div class="layui-input-inline">--%>
	         <%--<input type="text" name="companyAddress" autocomplete="off" class="layui-input form-control">--%>
	       <%--</div>--%>
 	 	<%--</div>--%>
 	 	<div class="layui-inline">
	       <label class="layui-form-label">备注：</label>
			<div class="layui-input-inline" style="width:323px;">
				<textarea name="remark"  class="layui-textarea form-control"></textarea>
 	 	</div>
 	 	
 	 			    
 	 	
	  </div>
	</form>
</c:if>
<c:if test="${act =='edit' ||  act =='view'}">
	<form class="layui-form form-" action="" lay-filter="customer-form">
	  <div class="layui-form-item">
	  	<div class="layui-inline">
	       <label class="layui-form-label">公司实体编号：</label>

	<c:if test="${act =='edit'}">
	       <div class="layui-input-inline">
	         <input type="text" name="companyCode" value="${Company.companyCode}" autocomplete="off" class="layui-input form-control">
	       </div>
	</c:if>
			<c:if test="${act =='view'}">
				<label class="layui-form-label">${Company.companyCode}</label>
			</c:if>
 	 	</div>
 	 	<div class="layui-inline">
	       <label class="layui-form-label">公司实体名称：</label>
	<c:if test="${act =='edit'}">
	       <div class="layui-input-inline">
	         <input type="text" name="companyName" value="${Company.companyName}" autocomplete="off" class="layui-input form-control">
	       </div>
	</c:if>
			<c:if test="${act =='view'}">
				<label class="layui-form-label">${Company.companyName}</label>
			</c:if>
 	 	</div>
 	 	<%--<div class="layui-inline">--%>
	       <%--<label class="layui-form-label">公司实体地址：</label>--%>
	       <%--<div class="layui-input-inline">--%>
	        <%--<c:if test="${act =='edit'}">--%>
	         <%--<input type="text" name="companyAddress" autocomplete="off" value="${Company.companyAddress}"  class="layui-input form-control">--%>
	         <%--</c:if>--%>
	         <%--<c:if test="${act =='view'}">--%>
	          <%--<label class="layui-form-label">${Company.companyAddress}</label>--%>
	         <%--</c:if>--%>
	       <%--</div>--%>
 	 	<%--</div>--%>
 	 	<div class="layui-inline">
	       <label class="layui-form-label">备注：</label>
			<div class="layui-input-inline" style="width:323px;">
	      	 <c:if test="${act =='edit'}">
				 <div class="layui-input-inline" style="width:323px;">
					 <textarea name="remark"  class="layui-textarea form-control">${Company.remark}</textarea>
				 </div>
	         </c:if>
	         <c:if test="${act =='view'}">
	          <label class="layui-form-label">${Company.remark}</label>
	         </c:if>
	       </div>
 	 	</div>
 	 	<input type="hidden" value="${id}" name="oldCode" />
	  </div>
	</form>
</c:if>

</div>
</body>
</html>

<script type="text/javascript">
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  	  laydate.render({
	    elem: '#createTime',
	    theme: 'molv',
	    type: 'datetime'
	  });
  	 
  	 
  	
});
</script>
 <c:if test="${act !='add'}">
 <script type="text/javascript">
 layui.use(['layer', 'form','laydate','table','upload'], function(){
	  var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate,
	  	  table=layui.table,
	  	  upload=layui.upload;
 });
 </script>
 </c:if>



