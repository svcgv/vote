<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admincp/layouts/main.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body>
<script src="${ctx }/common/queryCode?area=AREA&isDelete=IS_USEFUL&custType=CUST_TYPE&tradeCode=TRADE_CODE
&regionalMarket=REGIONAL_MARKET&custTrade=CUST_TRADE&payCycle=PAY_CYCLE&bgVisiable=BG_VISIABLE&country=COUNTRY" type="text/javascript"></script>
<div class="custom-info-wrapper">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>客户管理</legend>
	</fieldset>
	<form class="layui-form" id="customer-query-form" method="POST" action="/vote/bmcustomerinfo/list">
	  <div class="layui-form-item">
	  

	  	<div class="layui-inline">
	       <label class="layui-form-label">SAP编码：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="sapCode"  autocomplete="off" class="layui-input form-control">
	       </div>
 	 	</div>
	  	
	  	<div class="layui-inline">
	      <label class="layui-form-label">客户名称：</label>
	       <div class="layui-input-inline">
	         <input type="text" name="custCnName"  autocomplete="off" class="layui-input form-control">
	      </div>
	    </div>
	  
	  <div class="layui-inline">
	      <label class="layui-form-label">国家/地区：</label>
	      <div class="layui-input-inline">
	          <select name="country" lay-verify="required" lay-filter="" class="form-control">
	        	${country.ewTypeHtml }
	        </select>
	      </div>
	    </div>
		  <div class="layui-inline">
			  <label class="layui-form-label">创建日期(开始)：</label>
			  <div class="layui-input-inline">
				  <input type="text" name="createTimeStart" id="beginTime"  autocomplete="off" class="layui-input form-control">
			  </div>
		  </div>

		  <div class="layui-inline">
			  <label class="layui-form-label">创建日期(结束)：</label>
			  <div class="layui-input-inline">
				  <input type="text" name="createTimeEnd" id="endTime" autocomplete="off" class="layui-input form-control">
			  </div>
		  </div>
		  <div class="layui-inline">
			  <label class="layui-form-label">客户群编号：</label>
			  <div class="layui-input-inline">
				  <input type="text" name="custGroupId"  autocomplete="off" class="layui-input form-control">
			  </div>
		  </div>
		  <div class="layui-inline">
			  <label class="layui-form-label">客户群名称：</label>
			  <div class="layui-input-inline">
				  <input type="text" name="custGroupName"  autocomplete="off" class="layui-input form-control">
			  </div>
		  </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">客户类型：</label>
	      <div class="layui-input-inline">
	        <select name="custType" lay-verify="required" lay-filter="" class="form-control">
	        	 ${custType.ewTypeHtml}
	        </select>
	      </div>
	    </div>
	    
	    
		<div class="layui-inline">
	      <label class="layui-form-label">客户行业：</label>
	      <div class="layui-input-inline">
	        <select name="custTrade" lay-verify="required" lay-filter="" class="form-control">
	        	${custTrade.ewTypeHtml }
	        </select>
	      </div>
	    </div>
	    
	     <div class="layui-inline">
	      <label class="layui-form-label">是否有效：</label>
	      <div class="layui-input-inline">
			  <select name="isDelete" lay-verify="required" lay-filter="" class="form-control">
				  ${isUseful.ewTypeHtml }
			  </select>
	      </div>
	    </div>
	    
	  <div class="layui-inline">
	      <label class="layui-form-label">BG隐藏：</label>
	      <div class="layui-input-inline">
	        <select name="bgVisiable" lay-verify="required" lay-filter="" class="form-control">
	          ${bgVisiable.ewTypeHtml }
	        </select>
	      </div>
	    </div>
	    

	    
 	   <div class="layui-inline" style="vertical-align: top;">
		   <div class="layui-btn-container" style="margin-left:15px;">
		    <button type="button"  class="layui-btn layui-btn-sm" id="customQuery" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i>查询</button>
		    <button type="button" class="layui-btn layui-btn-sm" id="customAdd" power="104401"  style="margin-right:15px;"><i class="layui-icon"></i>新增</button>
		     
		    <button type="reset" class="layui-btn layui-btn-sm" style="margin-right:15px;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
		    <button type="button" class="layui-btn layui-btn-normal"  power="104405" id="test8">导入</button>
		     <button type="button" class="layui-btn layui-btn-normal" id="test9" style="opacity:0">保存导入数据</button>
		  </div>
	   </div>
	    
	  </div>
	</form>
	<table class="layui-hide" id="customTable" lay-filter="custom"></table>
	
 </div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container"></div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" power="104402" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-xs layui-btn-xs" power="104403" lay-event="view">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" power="104404" lay-event="del">删除</a>
</script>
<script type="text/javascript">
var test_button = document.getElementById('test9');
//一般直接写在一个js文件中
layui.use(['layer', 'form','laydate','table','upload'], function(){
  var layer = layui.layer ,
  	  form = layui.form,
  	  laydate=layui.laydate,
  	  table=layui.table,
  	  upload=layui.upload;
  
  //日期
	  laydate.render({
		    elem: "#beginTime",
		    theme: 'molv',
		    type: 'datetime'
	 });
	 laydate.render({
		    elem: "#endTime",
		    theme: 'molv',
		    type: 'datetime'
	 });

//指定允许上传的文件类型
	upload.render({
    elem: '#test8'
    ,url: '/vote/excel/upload/excelFileCode'
    ,auto: true
    ,accept: 'file' //普通文件
    ,exts: 'xls|xlsx'
    ,before:function(obj){
    	this.data={fileCode:'cust'}
    }
    ,done: function(res){
      testData=res.list
      table.render({
  	  	id:"customer-table",
  	    elem: '#customTable',
  	    //url:'custom.json',
  	    toolbar: '#toolbarDemo',
  	    height:'full-250',
  	    title: '客户数据表',
  	    cols: [[
  	     // {type: 'checkbox', fixed: 'left'},
  	      {field:'sapCode', title:'SAP编码',fixed: 'left', width:110, sort: true},
  	      {field:'custCnName', title:'客户名称', width:230},
  	      {field:'country', title:'国家/地区', width:90},
  	      {field:'enName', title:'英文名称',width:100},
  	      {field:'custPatTaxesCode', title:'客户纳税识别码'},
  	      {field:'custType', title:'客户类型'},
  	      {field:'address', title:'地址'},
  	      {field:'cashManagementGroup', title:'现金管理组'},
  	      {field:'payCondition', title:'付款条件'},
  	      {field:'tradeCode', title:'行业代码'},
  	      {field:'regionalMarket', title:'地区市场'},
  	      {field:'mainBusiness', title:'主营业务'},
  	      {field:'area', title:'地区'},
  	      {field:'custTrade', title:'客户行业'},
  	      {field:'payCycle', title:'结算周期'},
  	      {field:'isUseful', title:'是否有效'},
//  	      {field:'isDelete', title:'是否有效'},
  	      {field:'groupCompany', title:'集团公司'},
  	      {field:'bgVisiable', title:'BG隐藏'},
  	      {field:'companyCode', title:'公司代码'},
  	      {field:'companyFuncCode', title:'公司代码（职能）'},
  	      {field:'createTime', title:'创建日期'},
  	      {field:'custGroupId', title:'客户群编号'},
  	      {field:'custGroupName', title:'客户群名称'}
  	    ]],
  	    cellMinWidth:'90',
  	    data:testData,
  	    page: true
  	  });
      test_button.style.opacity=1
      layer.msg("excel解析成功，数据确认无误后，请点击按钮：保存导入数据以进行保存",{icon:1});
    }
  });
	
	//保存导入数据
	$("#test9").click(function(){
		 $.ajax({
			  type: 'POST',
			  url: '/vote/pmcustomerinfo/saveList',
			  data: JSON.stringify(testData),
			  contentType:'application/json',
			  success: function(res){
			      console.log(res)
			      testData=res.page
			      table.render({
			  	  	id:"customer-table",
			  	    elem: '#customTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-250',
			  	    title: '客户数据表',
			  	    cols: [[
			  	      //{type: 'checkbox', fixed: 'left'},
			  	      {field:'sapCode', title:'SAP编号',fixed: 'left', width:110, sort: true},
			  	      {field:'custCnName', title:'客户名称', width:230},
			  	      {field:'area', title:'地区'},
			  	      {field:'createTime', title:'创建日期'},
			  	      {field:'companyCode', title:'公司代码'},
			  	      {field:'custGroupId', title:'客户群编号'},
			  	      {field:'custGroupName', title:'客户群名称'},
			  	      {field:'custPatTaxesCode', title:'客户纳税识别码'},
			  	      {field:'custType', title:'客户类型'},
			  	      {field:'address', title:'地址'},
			  	      {field:'cashManagementGroup', title:'现金管理组'},
			  	      {field:'payCondition', title:'付款条件'},
			  	      {field:'tradeCode', title:'行业代码'},
			  	      {field:'regionalMarket', title:'地区市场'},
			  	      {field:'mainBusiness', title:'主营业务'},
			  	      {field:'custTrade', title:'客户行业'},
			  	      {field:'payCycle', title:'结算周期'},
			  	      {field:'isUseful', title:'是否有效'},
			  	      {field:'groupCompany', title:'集团公司'},
			  	      {field:'bgVisiable', title:'BG隐藏'},
			  	      {field:'companyFuncCode', title:'公司代码（职能）'},
			  	      {field:'country', title:'国家/地区', width:90},
			  	      {field:'enName', title:'英文名称',width:100},
			  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
			  	    ]],
			  	    cellMinWidth:'90',
			  	    data:testData,
			  	    page: true
			  	  });},
			  dataType: "json"
			});
		 test_button.style.opacity=0
		 layer.msg("保存成功",{icon:1});
	});

  // table render
  var queryParams=$("#customer-query-form").serializeObject();
  var newParam = {};
  for(var i in queryParams){
	  if(queryParams[i]){
		  newParam[i] = queryParams[i]
	  }
  }
  queryParams = newParam
  table.render({
	  	id:"customer-table",
	    elem: '#customTable',
	    url:'/vote/pmcustomerinfo/listAll',
	    method:'post',
		where:{
			queryStr:JSON.stringify(queryParams)
		},
		contentType: 'application/json',
	    response: {
	    	dataName: 'page'
	    },
	    toolbar: '#toolbarDemo',
	    height:'full-250',
	    title: '客户数据表',
	    cols: [[
	      //{type: 'checkbox', fixed: 'left'},
	      {field:'sapCode', title:'SAP编号',fixed: 'left', width:110, sort: true},
  	      {field:'custCnName', title:'客户名称', width:230},
            {field:'custGroupId', title:'客户群编号'},
            {field:'custGroupName', title:'客户群名称'},
//            {field:'isUseful', title:'是否有效'},
            {field:'isDelete', title:'是否有效',templet:function(d){
                return getCodeValue(d.isDelete,isDelete);
            }},
            {field:'area', title:'地区',templet:function(d){
                return getCodeValue(d.area,area);
            }},
            {field:'createTime', title:'创建日期'},
            {field:'companyCode', title:'公司代码'},
  	      {field:'custPatTaxesCode', title:'客户纳税识别码'},
            {field:'custType', title:'客户类型',templet:function(d){
                return getCodeValue(d.custType,custType);
            }},
  	      {field:'address', title:'地址'},
  	      {field:'cashManagementGroup', title:'现金管理组'},
  	      {field:'payCondition', title:'付款条件'},
  	      {field:'tradeCode', title:'行业代码',templet:function(d){
              return getCodeValue(d.tradeCode,tradeCode);
          }},
  	      {field:'regionalMarket', title:'地区市场',templet:function(d){
              return getCodeValue(d.regionalMarket,regionalMarket);
          }},
  	      {field:'mainBusiness', title:'主营业务'},
  	      {field:'custTrade', title:'客户行业',templet:function(d){
              return getCodeValue(d.custTrade,custTrade);
          }},
  	      {field:'payCycle', title:'结算周期',templet:function(d){
              return getCodeValue(d.payCycle,payCycle);
          }},
  	      {field:'groupCompany', title:'集团公司'},
  	      {field:'bgVisiable', title:'BG隐藏',templet:function(d){
              return getCodeValue(d.bgVisiable,bgVisiable);
          }},
  	      {field:'companyFuncCode', title:'公司代码（职能）'},
  	      {field:'country', title:'国家/地区', width:90,templet:function(d){
              return getCodeValue(d.country,country);
          }},
  	      {field:'enName', title:'英文名称',width:100},
  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
	    ]],
	    cellMinWidth:'90',
	    page: true    ,
        done:function(){
            $.buttonAuthority();
        }
	  });
	/*
	* 监听头工具栏事件 
	*/
	table.on('toolbar(custom)', function(obj){
	  var checkStatus = table.checkStatus(obj.config.sapCode)
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
	/*
	*监听每行编辑删除事件
	*/
	  table.on('tool(custom)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	      layer.confirm('确认删除行么', function(index){
	    	  $.ajax({
				  type:"POST",
				  url:"/vote/pmcustomerinfo/update",
				  data:JSON.stringify({'custId':data.custId,'isDelete':'01'}),
				  contentType:'application/json',
				  success:function(data){
					 
					  table.reload('customer-table');
					 
				  }
			  }); 
	    	 
	        obj.del();
	        layer.close(index);
	        table.reload('customer-table',{
	        	
	        });
	      });
	    } else if(obj.event === 'edit'){
	    	// 编辑
	    	showFromTable('edit',data.custId);
	    }else if(obj.event === "view"){
	    	// 查看
	    	showFromTable('view',data.custId);
	    }
	  });
	/*
	* 客户查询按钮
	*/
	 $("#customQuery").click(function(){
		 var queryParams=$("#customer-query-form").serializeObject();
		 var newParam = {}
		  for(var i in queryParams){
			  if(queryParams[i]){
				  newParam[i] = queryParams[i]
			  }
		  }
		  queryParams = newParam
		 table.reload('customer-table',{
				url:'/vote/pmcustomerinfo/listAll',
				page:{
					curr:1 //从第一页开始
				},
			    method:'post',
				where:{
					queryStr:JSON.stringify(queryParams)
				},
				contentType: 'application/json',
			    response: {
			    	dataName: 'page'
			    },
				done:function(res){
					console.log(res)
				}

			})
		 /*$.ajax({
			  type: 'POST',
			  url: '/vote/pmcustomerinfo/list',
			  data: JSON.stringify(queryParams),
			  contentType:'application/json',
			  success: function(res){
			      console.log(res)
			      testData=res.page
			      table.render({
			  	  	id:"customer-table",
			  	    elem: '#customTable',
			  	    //url:'custom.json',
			  	    toolbar: '#toolbarDemo',
			  	    height:'full-250',
			  	    title: '客户数据表',
			  	    cols: [[
			  	      {type: 'checkbox', fixed: 'left'},
			  	      {field:'sapCode', title:'SAP编号',fixed: 'left', width:110, sort: true},
			  	      {field:'custCnName', title:'客户名称', width:230},
			  	      {field:'country', title:'国家/地区', width:90},
			  	      {field:'enName', title:'英文名称',width:100},
			  	      {field:'custPatTaxesCode', title:'客户纳税识别码'},
			  	      {field:'custType', title:'客户类型'},
			  	      {field:'address', title:'地址'},
			  	      {field:'cashManagementGroup', title:'现金管理组'},
			  	      {field:'payCondition', title:'付款条件'},
			  	      {field:'tradeCode', title:'行业代码'},
			  	      {field:'regionalMarket', title:'地区市场'},
			  	      {field:'mainBusiness', title:'主营业务'},
			  	      {field:'area', title:'地区'},
			  	      {field:'custTrade', title:'客户行业'},
			  	      {field:'payCycle', title:'结算周期'},
			  	      {field:'isUseful', title:'是否有效'},
			  	      {field:'groupCompany', title:'集团公司'},
			  	      {field:'bgVisiable', title:'BG隐藏'},
			  	      {field:'companyCode', title:'公司代码'},
			  	      {field:'companyFuncCode', title:'公司代码（职能）'},
			  	      {field:'createTime', title:'创建日期'},
			  	      {field:'custGroupId', title:'客户群编号'},
			  	      {field:'custGroupName', title:'客户群名称'},
			  	      {fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
			  	    ]],
			  	    cellMinWidth:'90',
			  	    data:testData,
			  	    page: true
			  	  });},
			  dataType: "json"
			});*/
		//var queryParams=$("#customer-query-form").serialize();
		/* table.reload('customer-table',{
			url:'/vote/bmcustomerinfo/list',
			page:{
				curr:1 //从第一页开始
			},
			method:'post',
			where:{
				queryStr:queryParams
			},
			done:function(res){
				console.log(res)
			}
			
		}) */
		
	}); 
	
	/*
	* 新增客户
	*/
	$("#customAdd").click(function(){
		showFromTable("add");
	});
	
	/*
	* 新增和修改 form 表单
	*/
	function showFromTable(isEdit,id){
		var url='form?act=add&custId=1';
		var title="新增客户信息";
		var btn=['保存', '关闭'];
		var ajaxurl="/vote/pmcustomerinfo/save"; 
		if(isEdit == "edit"){
			url='form?act=edit&custId='+id;
			title="修改客户信息";
			btn=['保存', '关闭'];
			ajaxurl="/vote/pmcustomerinfo/update"; 
		}else if(isEdit == "view"){
			url='form?act=view&custId='+id;
			title="查看客户信息";
			btn=[];
		}
		
		layer.open({
			  skin: 'form-iframe-class',
			  type: 2, // iframe层
			  title: title,
			  closeBtn: 0, //显示关闭按钮
			  shade: 0.3,
			  area: ["800px","550px"],
			  anim: 2,
			  maxmin: true,
			  shadeClose: true, //点击遮罩关闭
			  closeBtn:1,
			  content: url, //iframe的url，no代表不显示滚动条
			  btn: btn,
			  btnAlign:'c',
			  //zIndex: layer.zIndex,
			  yes: function(index, layero){
				  // 保存按钮
				  var formData = layer.getChildFrame('body', index).find("form").serializeObject();
                  var newParam2 = {};
                  for(var i in formData){
                      if(formData[i]){
                          newParam2[i] = formData[i]
                      }
                  }
                  formData = newParam2
				  $.ajax({
					  type:"POST",
					  url:ajaxurl,
					  data:JSON.stringify(formData),
					  //data:formData,
					  contentType:'application/json',
					  success:function(data){
						 layer.close(index);
						  table.reload('customer-table',{page:
                          {
                              curr: 1 //重新从第 1 页开始
                          }});
						 
					  }
				  })
				 
				  
		      },
		      btn2: function(index, layero){
		    	  // 关闭按钮回调
		    	  layer.close(index)
		      }
			  
			});
	}
	
});
var testData=[]


</script>
</body>
</html>