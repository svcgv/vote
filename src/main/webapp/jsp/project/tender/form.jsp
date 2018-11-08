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
		      <label class="layui-form-label">投标名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="bidName"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">投标首次报价金额：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="firstBidAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">客户名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="custCnName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
		         <input type="hidden" name="custId">
		         <input type="hidden" name="custSapCode">
		      </div>
		      <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预估收入金额：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估成本：</label>
		      <div class="layui-input-inline">
		       <input type="text" name="predictCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估利润率：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="predictProfitRate"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预付期限：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="predictPeriod" id="predictPeriodDate-edit" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">交付部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="constructionDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="constructionDeptId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="payOrgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		    
		    		    
		    <div  class="layui-inline">
		    <label class="layui-form-label">交付部门负责人：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="constructionDeptManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="constructionDeptManagerId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="payOrgMangerQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		     </div> 
		       
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="sellDeptId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		     
		    <div  class="layui-inline">
		    <label class="layui-form-label">销售部门负责人：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="sellDeptManagerId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userManagerQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		     </div> 
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">客户经理：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="custManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="custManagerId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">技术总监：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="technicalDirectorName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="hidden" name="technicalDirectorId">
		      </div>
		       <div class="layui-input-inline layui-btn-container" style="margin-left:15px;">
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="techQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search"></i></button>
		       </div>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">设置付款点：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="paymentPoint"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
	      <div class="layui-inline">
	       		 <label class="layui-form-label" style="width:170px!important;">工作任务及范围是否清晰：</label>
	       		 <div class="layui-input-inline">
	       		 	<!-- open 是开启 close 是关闭   对应 isWorkAreaExplicit 数据字典 -->
			        <input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="是|否">
			    </div>
	       </div>
	       <div class="file-hook" style="width:95%;margin:0 auto;">
		      <div class="layui-upload">
			  	<button type="button" class="layui-btn" id="wosUploads"><i class="layui-icon"></i>选择文件</button> 
				  <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr><th>文件名</th>
				        <th>大小</th>
				        <th>状态</th>
				        <th>操作</th>
				      </tr></thead>
				      <tbody id="wosFileList"></tbody>
				    </table>
				  </div>
			  	<button type="button" class="layui-btn" id="wosListAction">开始上传</button>
			 </div> 
		  </div>
		   
	       <div class="layui-inline">
	       		 <label class="layui-form-label">备注：</label>
	       		 <div class="layui-input-inline" style="width:323px;">
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
<script>
$(function(){
	layui.use(['layer', 'form','laydate','upload'], function(){
		var layer = layui.layer ,
	  	  form = layui.form,
	  	  laydate=layui.laydate,
	  	upload=layui.upload;
		 //日期
	  laydate.render({
		    elem: "#predictPeriodDate-edit",
		    theme: 'molv',
		    type: 'datetime'
	 });
		
		function getParam(){
			var queryParams=$("#tender-addForm-hook form").serializeObject();
			 var newParam = {}
			  for(var i in queryParams){
				  if(queryParams[i]){
					  newParam[i] = queryParams[i]
				  }
			  }
			 if(fileIds){
				 newParam.fileIds=fileIds.join(',')
			 }
			  return newParam
		}
		 
	// form 表单手动渲染
	  form.render();
  //监听指定开关
  form.on('switch(switchTest)', function(data){
   	 var check= this.checked ? true : false;
   	 if(check){
   		 $("#tender-addForm-hook .file-hook").show();
   	 }else{
   		 $("#tender-addForm-hook .file-hook").hide();
   	 }
  });
  
//多文件上传
  var demoListView = $('#wosFileList')
  ,uploadListIns = upload.render({
	  before:function(obj){
	    	this.data={uploadType:'00'}
	    },
    elem: '#wosUploads'
    ,url: '/vote/pmfile/upload'
    ,accept: 'file'
    ,multiple: true
    ,auto: false
    ,bindAction: '#wosListAction'
    ,choose: function(obj){   
      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
      //读取本地文件
      obj.preview(function(index, file, result){
        var tr = $(['<tr id="upload-'+ index +'">'
          ,'<td>'+ file.name +'</td>'
          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
          ,'<td>等待上传</td>'
          ,'<td>'
            ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
            ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
          ,'</td>'
        ,'</tr>'].join(''));
        
        //单个重传
        tr.find('.demo-reload').on('click', function(){
          obj.upload(index, file);
        });
        
        //删除
        tr.find('.demo-delete').on('click', function(){
          delete files[index]; //删除对应的文件
          tr.remove();
          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
        });
        
        demoListView.append(tr);
      });
    }
    ,done: function(res, index, upload){
      if(res.code == 0){ //上传成功
    	  fileIds = res.fileIds
        var tr = demoListView.find('tr#upload-'+ index)
        ,tds = tr.children();
        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
        tds.eq(3).html(''); //清空操作
        return delete this.files[index]; //删除文件队列已经上传成功的文件
      }
      this.error(index, upload);
    }
    ,error: function(index, upload){
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
    }
  })
	
  //客户查询
  $("#tender-addForm-hook #custNameQuery-hook").click(function(){
	  $.openWindow({
	  		url:'customer?act=addCust',
	  		title:"选择客户",
	  		width:"700"
	 });
	  
});
  
  //查询技术总监
  $("#tender-addForm-hook #techQuery-hook").click(function(){
	  $.openWindow({
	  		url:'user?act=addtech',
	  		title:"选择技术总监",
	  		width:"700"
	 });
	  
});
  
  	//查询交付部门
  $("#tender-addForm-hook #payOrgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=addPay',
	  		title:"选择交付部门",
	  		width:"400"
	 });
	  
  });
	
  	//查询交付部门负责人
  $("#tender-addForm-hook #payOrgMangerQuery-hook").click(function(){
	  $.openWindow({
	  		url:'user?act=addDept',
	  		title:"交付部门负责人",
	  		width:"700"
	 });
	  
});
  
  	//查询销售部门负责人
  $("#tender-addForm-hook #userManagerQuery-hook").click(function(){
	  $.openWindow({
	  		url:'user?act=addSaleDept',
	  		title:"销售部门负责人",
	  		width:"700"
	 });
	  
});
  
	 // 选择机构
  $("#tender-addForm-hook #orgQuery-hook").click(function(){
	  $.openWindow({
	  		url:'org?act=add',
	  		title:"选择销售部门",
	  		width:"400"
	 });
	  
  });
  
  // 选择人员
  $("#tender-addForm-hook #userQuery-hook").off("click").on("click",function(){
	  	$.openWindow({
	  		url:'user?act=add',
	  		title:"选择客户经理",
	  		width:"700"
	 	 });
	}); 
	  
		var win=$("#tender-addForm-hook").getWindow();
		// 保存
		$("#tender-addForm-hook #customGroup-add-hook").click(function(){
			var customerGroupName=$("#tender-addForm-hook input[name='bidName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入投标名称");
				return false;
			}
			
			
			$.ajax({
				type:'POST',
				url:'/vote/pmconfirmbid/save',
				contentType:'application/json',
				data: JSON.stringify(getParam()),
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
		$("#tender-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});
var fileIds = []
</script>