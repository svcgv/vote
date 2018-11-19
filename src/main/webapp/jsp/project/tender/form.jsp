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
		      <label class="layui-form-label">项目名称：</label>
		       <div class="layui-input-inline">
		         <input type="text" name="bidName"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">首次报价（元）：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="firstBidAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">预估合同金额（元）：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictAmount"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估成本（元）：</label>
		      <div class="layui-input-inline">
		       <input type="number" name="predictCost"  autocomplete="off" class="layui-input form-control">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预估工作量：</label>
		      <div class="layui-input-inline">
		       <input type="number" name="predictWorkLoad"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">预估利润（元）：</label>
		      <div class="layui-input-inline">
		       <input type="number" name="predictProfit"  autocomplete="off" class="layui-input form-control">
		      </div>
		    </div>
		    
		     <div class="layui-inline">
		      <label class="layui-form-label">预估利润率（%）：</label>
		       <div class="layui-input-inline">
		         <input type="number" name="predictProfitRate"  autocomplete="off" class="layui-input form-control">
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
		      <label class="layui-form-label">项目计划开始日期：</label>
		       <div class="layui-input-inline">
	         		<input type="text" name="predictPeriodStart" id="predictPeriodStartDate-edit" autocomplete="off" class="layui-input form-control hasDatepicker">
		      </div>
		      <span class="f-placeholder"></span>
		    </div>
			  <div class="layui-inline">
				  <label class="layui-form-label">项目计划结束日期：</label>
				  <div class="layui-input-inline">
					  <input type="text" name="predictPeriodEnd" id="predictPeriodEndDate-edit" autocomplete="off" class="layui-input form-control hasDatepicker">
				  </div>
				  <span class="f-placeholder"></span>
			  </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">交付部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="constructionDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="constructionDeptId">
		      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="payOrgQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    		    
		    <div  class="layui-inline">
		    	<label class="layui-form-label">交付部门负责人：</label>
		         <div class="layui-input-inline">
			          <input type="text" name="constructionDeptManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
			          <input type="text" style='display:none' name="constructionDeptManagerId">
		          </div>
		          <span class="f-placeholder"></span>
		     </div> 
		       
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">销售部门：</label>
		       <div class="layui-input-inline">
		          <input type="text" name="sellDeptName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
		          <input type="text" style='display:none' name="sellDeptId">
		      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="orgQuery-hook" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		     
		    <div  class="layui-inline">
		    	<label class="layui-form-label">销售部门负责人：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="sellDeptManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
			          <input type="text" style='display:none' name="sellDeptManagerId">
			      </div>
			      <span class="f-placeholder"></span>
		     </div> 
		     
	       <div  class="layui-inline">
		         <label class="layui-form-label">项目经理：</label>
		         <div class="layui-input-inline">
			          <input type="text" name="projectManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
			          <input type="text" style='display:none' name="projectManagerId">
		        </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="techQuery-projectManager" ><i class="layui-icon layui-icon-search"></i></button>
		     </div> 
		     
		      <div class="layui-inline">
			      <label class="layui-form-label">技术总监：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="technicalDirectorName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
			          <input type="text" style='display:none' name="technicalDirectorId">
			      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="techQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline">
			      <label class="layui-form-label">客户名称：</label>
			       <div class="layui-input-inline">
			         <input type="text" name="custCnName" readonly="readonly"  autocomplete="off" class="layui-input form-control disabledColor">
			         <input type="text" style='display:none' name="custId">
			         <input type="text" style='display:none' name="custSapCode">
			      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="custNameQuery-hook" ><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		     <div class="layui-inline" style="vertical-align:top;">
		      	<label class="layui-form-label">客户经理：</label>
			       <div class="layui-input-inline">
			          <input type="text" name="custManagerName" readonly="readonly" autocomplete="off" class="layui-input form-control disabledColor">
			          <input type="text" style='display:none' name="custManagerId">
			      </div>
		      	 <button type="button"  class="layui-btn layui-btn-sm" id="userQuery-hook"><i class="layui-icon layui-icon-search"></i></button>
		    </div>
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">付款点：</label>
		       <div class="layui-input-inline" style="width:230px;">
				   <textarea name="paymentPoint" rows="2"  placeholder="3/3/3/1,月/季/年" class="layui-textarea form-control"></textarea>
		      </div>
		    </div>
		    
	       <div class="layui-inline">
	       		 <label class="layui-form-label">备注：</label>
	       		 <div class="layui-input-inline" style="width:230px;">
			      <textarea name="remark"  class="layui-textarea form-control"></textarea>
			    </div>
	       </div>
	       
		 </div>
		    
	       <div class="layui-form-item clearfix" style="width:95%;margin:10px 10px;">
		      <div class="layui-upload">
		      <div style="display:inline-block;width:100%;">
			  	<button type="button" class="layui-btn" id="wosUploads"><i class="layui-icon"></i>选择文件</button> 
			  	<button type="button" class="layui-btn" id="wosListAction">开始上传</button>
		  	  </div>
				  <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr><th>文件名</th>
				        <th>大小</th>
				        <th>文件类型</th>
				        <th>状态</th>
				         <th>操作</th>
				      </tr></thead>
				      <tbody id="wosFileList"></tbody>
				    </table>
				  </div>
			 </div> 
		   
		     
		     
		  </div>
	</form>
	<div class="layui-layer-btn layui-layer-btn-c">
		<a class="layui-layer-btn0" id="customGroup-save-hook" style="background:#009688;border-color:#009688;">保存并提交</a>
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
		    elem: "#predictPeriodStartDate-edit",
		    theme: 'molv',
		    
	 });
        laydate.render({
            elem: "#predictPeriodEndDate-edit",
            theme: 'molv',
            
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
			 if(queryParams.open=='on'){
				 newParam.isWorkAreaExplicit='00'
			 }
			 else{
				 newParam.isWorkAreaExplicit='01'
			 }
			  return newParam
		}
		 
	// form 表单手动渲染
	  form.render();
  function getFileTableParams(){
	  var files=[]
	  var tbody = document.getElementById('wosFileList')
	  if(tbody.children){
		  for(var i = 0;i<tbody.children.length;i++){
			  var item={}
			  item.fileName = tbody.children[i].children[0].innerText
			  item.fileType = tbody.children[i].children[2].children[0].children[0].value
			  files.push(item)
		  }
	  }
	  return files;
  }
  
//多文件上传
  var demoListView = $('#wosFileList')
  ,uploadListIns = upload.render({
	  before:function(obj){
	    	this.data={uploadType:'00',fileTypes:JSON.stringify(getFileTableParams())}
	    	console.log('before',obj)
	    },
    elem: '#wosUploads'
    ,url: '/vote/pmfile/upload'
    ,accept: 'file'
    ,multiple: true
    ,auto: false
    ,bindAction: '#wosListAction'
    ,choose: function(obj){   
    	console.log('choose',obj)
      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
      //读取本地文件
      obj.preview(function(index, file, result){
        var tr = $(['<tr id="upload-'+ index +'">'
          ,'<td>'+ file.name +'</td>'
          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
          ,'<td>'
          ,' <div class="layui-input-inline">'
         	 ,'<select name="projectType" lay-verify="required" lay-filter="" class="form-control">'
	          ,'<option value="">请选择</option>'
	        	,'<option value="00" selected>招标文件</option>'
	        	,'<option value="01">客户需求文件</option>'
	        	,'<option value="02" >内部评审文件</option>'
	        	,'</select>'
				,'</div>'
        ,'</td>'

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
        setTimeout(function(){
	        form.render();
        },400)
      });
    }
    ,done: function(res, index, upload){
      if(res.code == 0){ //上传成功
    	  fileIds.push(res.fileIds)
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
	  		url:'user?act=addtech&orgNo=&roleCode=',
	  		title:"选择技术总监",
	  		width:"700"
	 });
	  
});
  
  //选择项目经理
  $("#techQuery-projectManager").click(function(){
	  var par = getParam()
	  if(!par.constructionDeptId){
		  layer.msg("请输入请选择交付部门");
		  return 
	  }
	  $.openWindow({
	  		url:'user?act=addProManager&orgNo='+par.constructionDeptId+'&roleCode=PROJECT_MANGER',
	  		title:"选择项目经理",
	  		width:"700"
	 });
	  
});
  
 
  
  	//查询交付部门
  $("#tender-addForm-hook #payOrgQuery-hook").click(function(){
	  console.log('asdasd')
	  $.openWindow({
	  		url:'org?act=addPay',
	  		title:"选择交付部门",
	  		width:"400"
	 });
	  
  });
/* 	
  	//查询交付部门负责人
  $("#tender-addForm-hook #payOrgMangerQuery-hook").click(function(){
	  $.openWindow({
	  		url:'user?act=addDept',
	  		title:"交付部门负责人",
	  		width:"700"
	 });
	  
}); */
  
/*   	//查询销售部门负责人
  $("#tender-addForm-hook #userManagerQuery-hook").click(function(){
	  $.openWindow({
	  		url:'user?act=addSaleDept',
	  		title:"销售部门负责人",
	  		width:"700"
	 });
	  
}); */
  
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
	  		url:'user?act=add&orgNo=&roleCode=',
	  		title:"选择客户经理",
	  		width:"700"
	 	 });
	}); 
	  
		var win=$("#tender-addForm-hook").getWindow();

        // 保存并提交
        $("#tender-addForm-hook #customGroup-save-hook").click(function(){
            var customerGroupName=$("#tender-addForm-hook input[name='bidName']").val();
            if($.trim(customerGroupName) ==''){
                layer.msg("请输入项目名称");
                return false;
            }
            if(!fileIds){
				layer.msg("请上传文件");
				return false;
			}

            $.ajax({
                type:'POST',
                url:'/vote/pmconfirmbid/save2',
                contentType:'application/json',
                data: JSON.stringify(getParam()),
                success:function(res){
                    location.reload();
                    layer.msg("新增成功",{icon:1});
                    win.close();
                },
                error:function(){
                    layer.msg("新增失败",{icon:5});
                    win.close();
                }
            });
            return false;
        });
		// 保存
		$("#tender-addForm-hook #customGroup-add-hook").click(function(){
			if(!fileIds){
				layer.msg("请上传文件");
				return false;
			}
			var customerGroupName=$("#tender-addForm-hook input[name='bidName']").val();
			if($.trim(customerGroupName) ==''){
				layer.msg("请输入项目名称");
				return false;
			}
			
			
			$.ajax({
				type:'POST',
				url:'/vote/pmconfirmbid/save',
				contentType:'application/json',
				data: JSON.stringify(getParam()),
				success:function(res){
                    location.reload();
					layer.msg("新增成功",{icon:1});
					win.close();
				},
				error:function(){
					layer.msg("新增失败",{icon:5});
					win.close();
				}
			})
			return false;
		});
		
		// 关闭
		$("#tender-addForm-hook #customerGroup-close-hook").click(function(){
			win.close();
			return false;
		})
	
	})
});
var fileIds = []
</script>