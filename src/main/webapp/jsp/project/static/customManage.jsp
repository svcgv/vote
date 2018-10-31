<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<body>
<div class="custom-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>客户信息</legend>
	</fieldset>
	<form class="layui-form" action="">
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">客户名称：</label>
	      <div class="layui-input-inline">
	        <select name="modules" lay-verify="required" lay-search="" class="form-control">
	          <option value="">--请选择客户--</option>
	          <option value="TM-16283">工银瑞信基金管理有限公司</option>
	          <option value="TM-18025">华夏基金管理有限公司</option>
	          <option value="TM-18185">交通银行股份有限公司</option>
	        </select>
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">WBS编号：</label>
	      <div class="layui-input-inline">
	         <input type="text" name="WBSID"  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">项目类型：</label>
	      <div class="layui-input-inline">
	        <select name="modules" lay-verify="required" lay-search="" class="form-control">
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
	        <input type="text" name="startDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input datepicker">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">基本结束日期:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="endDateTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input datepicker">
	      </div>
	    </div>
	    <div class="layui-inline">
		   <div class="layui-btn-container" style="margin-left:15px;margin-top:5px;">
		    <button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon layui-icon-search"></i>客户查询</button>
		    <button type="button" class="layui-btn layui-btn-sm" id="customAdd" ><i class="layui-icon"></i>客户新增</button>
		    <button type="button" class="layui-btn layui-btn-sm" id="customImport"><i class="layui-icon"></i>客户表数据导入</button>
		  </div>
	    </div>
	    
	  </div>
	</form>
	<table class="layui-hide" id="customTable" lay-filter="custom"></table>
	<!-- 弹出框模板 -->
	<div id="custom-form-wrapper" style="display:none">
		<custom-from></custom-from>
	</div>
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="deleteData">批量删除</button>
  </div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>




<script type="text/javascript">

//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  //日期
  var datepickers=document.getElementsByClassName("datepicker");
  for(var i=0;i<datepickers.length;i++){
	  laydate.render({
		    elem: datepickers[i],
		    theme: 'molv'
		  });
  }

//指定允许上传的文件类型
	upload.render({
	    elem: '#customImport',
	    url: '/upload/',
	    accept: 'file', // 普通文件
	    exts: 'xlsx|xls', //只允许上传压缩文件
	    auto: true , // 是否自动上传 
	    multiple: true,
	    done: function(res){
	    	layer.msg('上传成功', {icon: 1});
	    },
	    error: function(index, upload){
	    	layer.msg('上传失败', {icon: 5});
	    }
	  });
  // table render
  table.render({
	    elem: '#customTable',
	    //url:'custom.json',
	    toolbar: '#toolbarDemo',
	    height:'full-200',
	    title: '客户数据表',
	    cols: [[
	      {type: 'checkbox'},
	      {field:'id', title:'ID', width:80, unresize: true, sort: true},
	      {field:'customerName', title:'客户名称', width:150},
	      {field:'WBS', title:'WBS', width:100},
	      {field:'projectType', title:'项目类型', width:80},
	      {field:'isOpenTicket', title:'是否开票', width:80},
	      {field:'gainCenter', title:'利润中心', sort: true},
	      {field:'companyCode', title:'公司代码', width:80, sort: true},
	      {field:'startTime', title:'基本开始时间', width:120},
	      {field:'endTime', title:'基本结束时间', width:100, sort: true},
	      {field:'managerNo', title:'项目经理编号', width:120},
	      {field:'costCenter', title:'成本中心', width:120},
	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
	    ]],
	    data:testData,
	    page: true
	  });
	//监听头工具栏事件 
	table.on('toolbar(custom)', function(obj){
	  var checkStatus = table.checkStatus(obj.config.id)
	  ,data = checkStatus.data; //获取选中的数据
	  switch(obj.event){
	   
	    case 'deleteData': //删除操作
	      if(data.length === 0){
	        layer.msg('请选择一行');
	      } else {
	    	  console.log(checkStatus.data)
	        layer.alert('删除：'+ checkStatus.data);
	      }
	    break;
	  };
	});
	//监听行工具事件
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	      layer.confirm('真的删除行么', function(index){
	        obj.del();
	        layer.close(index);
	      });
	    } else if(obj.event === 'edit'){
	      layer.prompt({
	        formType: 2
	        ,value: data.customerName
	      }, function(value, index){
	        obj.update({
	        	customerName: value
	        });
	        layer.close(index);
	      });
	    }
	  });
	// 新增客户
	$("#customAdd").click(function(){
		layer.open({
			  type: 2, // iframe层
			  title: "新增客户信息",
			  closeBtn: 0, //不显示关闭按钮
			  shade: 0.3,
			  area: ["800px","500px"],
			  anim: 2,
			  maxmin: true,
			  shadeClose: true, //点击遮罩关闭
			  closeBtn:1,
			  content: ['customForm'], //iframe的url，no代表不显示滚动条
			  btn: ['保存', '关闭'],
			  btnAlign:'c',
			  //zIndex: layer.zIndex,
			  yes: function(index, layero){
				  // 保存按钮
				  console.log(index)
		      },
		      btn2: function(index, layero){
		    	  // 关闭按钮回调
		      }
			  
			});
		
	})
	
});
var testData=[
	  		{
				"id":1,
				"customerName":"扬州农行邗江支行",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":2,
				"customerName":"华夏基金管理有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":3,
				"customerName":"天弘基金管理有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":4,
				"customerName":"扬州农行邗江支行",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":5,
				"customerName":"扬州农行邗江支行",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":6,
				"customerName":"江苏昆山农村商业银行股份有限公司",
				"WBS":"TM-18050",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":7,
				"customerName":"交通银行股份有限公司广东省分行",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":8,
				"customerName":"上海浦东发展银行股份有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":9,
				"customerName":"扬州农行邗江支行",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":10,
				"customerName":"兴业数字金融服务(上海)股份有限公司 ",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"史定波",
				"costCenter":"1005020060"
			},
			{
				"id":11,
				"customerName":"华润元大基金管理有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"华伟",
				"costCenter":"1005020060"
			},
			{
				"id":12,
				"customerName":"博彦科技（深圳）有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"祝献仁",
				"costCenter":"1005020060"
			},
			{
				"id":13,
				"customerName":"博彦科技（深圳）有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"祝献仁",
				"costCenter":"1005020060"
			},
			{
				"id":14,
				"customerName":"博彦科技（深圳）有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"祝献仁",
				"costCenter":"1005020060"
			},
			{
				"id":15,
				"customerName":"博彦科技（深圳）有限公司",
				"WBS":"PB-18348",
				"projectType":"PB-Projcet base",
				"isOpenTicket":"是",
				"gainCenter":"1701040001",
				"companyCode":"1005",
				"startTime":"2018/10/15",
				"endTime":"2021/3/31",
				"managerNo":"祝献仁",
				"costCenter":"1005020060"
			}
		]
</script>
</body>
</html>