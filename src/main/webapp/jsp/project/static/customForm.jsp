<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<div class="custom-form-wrapper">
	<form class="layui-form" action="" style="margin-top:10px;">
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">客户名称：</label>
	      <div class="layui-input-inline">
	       <input type="text" name="customerName" v-model="customerName" autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">WBS编号：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="WBSID" v-model="WBSID"  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">项目类型：</label>
	      <div class="layui-input-inline">
	        <select name="projectType"  lay-verify="required" lay-search="" class="form-control">
	          <option value="">--请项目类型--</option>
	          <option value="1">PB-Projcet base</option>
	          <option value="2">PB-Projcet base2</option>
	        </select>
	      </div>
	  </div>
	  <div class="layui-form-item">
		<div class="layui-inline">
	      <label class="layui-form-label">基本开始日期:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="startDate" id="startDate"  lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">基本结束日期:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="endDate" id="endDate"   lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	      </div>
	    </div>

	  </div>
	</form>
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
	    elem: '#startDate',
	    theme: 'molv'
	  });
  	 laydate.render({
 	    elem: '#endDate',
 	    theme: 'molv'
 	  });
  
});


</script>




