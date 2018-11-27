<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
	function save() {
		if(checkFlg ==1){
			alert("登录名重复！");
			return ;
		}
		formValidate();
	}

	
	function backindex(){
		$.indi.submit({url:"${ctx }/usr/qryUsrList"});
	}
	//关闭当前弹出框
	function close_01() {
		history.back();
	}

	//打开机构选择的页面
	function openOrgInfo() {
		var orgType = $("#orgType").val();
		if(orgType.length==0){
			alert("请先选择机构类型！");
			return ;
		}else{
		$.indi.openPopup({
			title : '机构信息选择',
			area : [ '600px', '400px' ],
			url : '${ctx}/usr/openUserOrgInfo'
		});
		}
		 
	}
	// 验证身份证 
	function isCardNo(card) { 
		 var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
		 return pattern.test(card); 
		} 
	// 验证函数
	function formValidate() {
	 var str = '';
	 
	// 验证身份证
// 	 if(($.trim($('#certId').val()).length == 0) || ($.trim($('#certId').val()).length>18)||($.trim($('#certId').val()).length<15)) { 
// 	  str += '身份证号为15~18位!\n';
// 	  $('#certId').focus();
// 	 } else {
// 	  if(isCardNo($.trim($('#certId').val())) == false) {
// 	   str += '身份证号码输入错误!\n';
// 	   $('#certId').focus();
// 	  }
// 	   
     if(($.trim($('#orgNo').val()).length == 0)){
    	 
    	 str += '请选择机构';
     }
    	 
     
	 // 如果没有错误则提交
	 if(str != '') {
	  alert(str);
	  return false;
	 } else {
		 $('#i-form').validate({
				rules:{
					email:{
						required: true,
						email: true 
					}
				}
			});
		 $.indi.ajaxSubmit({
				url : "${ctx}/usr/addSave",
				closeMode : true,
				success : function() {
					layer.alert('用户信息保存成功！',{icon: 1}, function(index){
						layer.close(index);
						backindex();
						
					});  
				}
			});
	 }
	}
	
	function openAdd() {
		var type ="user";
		$.indi.openPopup({title: '    ',area : ['520px' , '500px'],isDate:false,url: '${ctx }/usr/queryAddPara?type='+type});
	}
	
	var checkFlg = 0;
	function check(){
		var loginName = $("#loginName").val();
		
		//console.log(loginName)
		if(loginName != "" || loginName !=null ){
			$.indi.ajaxSubmit({url: "${ctx }/usr/checkUsrInfo.do",isCheck:false,success:function(data){
				var loginName1="";
				if(data.usrInfo != null){
					loginName1=data.usrInfo.loginName
				}
				var leth = $.trim(loginName1).length;
				console.log(leth)
				if(leth >0){
					$("#check").show();
					checkFlg = 1;
				}else{
					$("#check").hide();
					checkFlg = 0;
				}
			}});
		
		}
		
	} 
	
</script>
</head>
<body>
	<div class="right-side">
		<!-- 头部信息start -->
		<section class="content-header">
			<h1>
				用户信息管理<small>&nbsp;用户信息新增</small>
			</h1>
		</section>
		<!-- 头部信息end -->
		<div class="content">
			<div class="row">
				<form method="post" id="i-form" class="form-horizontal col-md-12">
				<div class="form-group">
						<label class="col-md-4 control-label text-right">操作员姓名</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="用户姓名"
								id="usrName" name="usrName" required />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label text-right">登录名</label>
						<div class=" col-md-5">
							<input type="text" class="form-control" placeholder="登录名" onblur="check();"
								id="loginName" name="loginName" required />
						</div>
						<span id="check" style="display: none;"><font color='red'>登录名重复</font></span>
					</div>
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">性别</label>
						<div class=" col-md-6">
							<div class="row">
							<label class=" col-md-1">男</label>
							<div class=" col-md-1"><input type="radio" checked="checked" class="radio" placeholder="男" id="sex1" name="sex" value="1"/>
							</div>
							<label class=" col-md-1">女</label>
							<div class=" col-md-1"><input type="radio" class="radio" placeholder="女" id="sex2" name="sex" value="0" />
							</div>
							</div>
					      </div>
					</div>
					 <%-- <div class="form-group">
						<label class=" col-md-4 control-label text-right">角色</label>
						<div class=" col-md-6">
							<c:forEach items="${listInfo}" var="roleInfo" varStatus="roleSta">
							<label class="checkbox font-size col-md-3">
							<input type="checkbox" id="rolecheck" name="rolecheck" class="col-md-1"
							value="${roleInfo.roleId}">${roleInfo.roleName}
							</label>
							</c:forEach>
							<input type="hidden" id="roleId" name="roleId" value="">
					</div> --%>
					
					
					<div class="form-group" >
					<label class=" col-xs-4 control-label text-right" >所属机构</label>
					<div class=" col-xs-5">
						<input type="text" class="form-control col-xs-11 "
							placeholder="所属机构" id="orgName" name="orgName" readonly="readonly" required/>
							   <input type="hidden" name="orgNo" id = "orgNo"/>
					</div>
					<button type="button" class="btn btn-primary" onclick="openAdd()">
		     	<i class="icon-plus-sign"></i> 选择</button>
		     	</div>
				
<!-- 					<div class="form-group"> -->
<!-- 						<label class=" col-md-4 control-label text-right">所属机构</label> -->
<!-- 						<div class=" col-md-5"> -->
<!-- 							<input type="text" class="form-control col-md-11 " -->
<!-- 								placeholder="所属机构" id="_orgName" name="orgName" -->
<!-- 								disabled="disabled" />  -->
<!-- 								<input type="hidden" name="orgNo"id="_orgNo" /> -->
<!-- 								 <input type="hidden" name="orgType"id="orgType" /> -->
<!-- 						</div> -->
<!-- 						<div class=" col-md-1 paddtop5"> -->
<!-- 							<a href="javascript:void(0)" onclick="openOrgInfo()">选择</a> -->
<!-- 						</div>  -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class=" col-md-4 control-label text-right">出生日期</label> -->
<!-- 						<div class=" col-md-5"> -->
<!-- 							<input type="text" class="form-control col-md-11 " -->
<!-- 								placeholder="出生日期" id="calendar" onClick="WdatePicker()" name="birthDate"  birthDate="true"/> -->
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<!-- 						<label class=" col-md-4 control-label text-right">手机号码</label> -->
<!-- 						<div class=" col-md-5"> -->
<!-- 							<input type="text" class="form-control col-md-11 "  -->
<!-- 								placeholder="手机号码" id="mblNo" name="mblNo" required mobile="true"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class=" col-md-4 control-label text-right">证书编号</label> -->
<!-- 						<div class=" col-md-5"> -->
<!-- 							<input type="text" class="form-control col-md-11 "  -->
<!-- 								placeholder="证书编号" id="licenceCode" name="licenceCode"  licenceCode="true"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class=" col-md-4 control-label text-right">身份证</label> -->
<!-- 						<div class=" col-md-5"> -->
<!-- 							<input type="text" class="form-control col-md-11 "  -->
<!-- 								placeholder="省份证" id="certId" name="certId" required certId="true"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class=" col-md-4 control-label text-right">邮箱</label> -->
<!-- 						<div class=" col-md-5"> -->
<!-- 							<input type="text" class="form-control col-md-11 "  -->
<!-- 								placeholder="邮箱" id="email" name="email"  email="true"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="form-group">
						<label class=" col-md-4 control-label text-right">备注</label>
						<div class=" col-md-5">
						
						<textarea class="form-control col-xs-11 "
							placeholder="备注" id="strRemark" name="strRemark" strRemark="true" ></textarea>
							
						</div>
					</div>
					<div class=" form-group" align="center">
						<button type="button" class="btn btn-primary" onclick="save()"
							id="btnSave">
							<i class="icon-save"></i> 保存
						</button>
						<button type="button" class="btn btn-primary" onclick="close_01()">
							<i class="icon-remove"></i> 返回
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>