$(document).ready(function() {
    var oUser = document.getElementById('i_userid');
    var oPswd = document.getElementById('userpwd');
    var oRemember = document.getElementById('remember');
    //页面初始化时，如果帐号密码cookie存在则填充
    if(getCookie('user') && getCookie('pswd')){
      oUser.value = getCookie('user');
      oPswd.value = getCookie('pswd');
      oRemember.checked = true;
    }
    //复选框勾选状态发生改变时，如果未勾选则清除cookie
    oRemember.onchange = function(){
      if(!this.checked){
        delCookie('user');
        delCookie('pswd');
      }
    };
	
	// 验证登录
	var checklogin = function() {
		var usrId = $("#i_userid").val();
		var passWord = $("#userpwd").val();
		if (usrId == '' || usrId == null) {
			layer.alert("用户ID不能为空！", {
				icon : 0
			});
			return false;
		} else if (passWord == '' || passWord == null) {
			layer.alert("用户密码不能为空！", {
				icon : 0
			});
			return false;
		}
		return true;
	};
	$('#to-login').click(function() {
		if (checklogin()) {
			//表单提交事件触发时，如果复选框是勾选状态则保存cookie
			if(oRemember.checked){ 
		        setCookie('user',oUser.value,7); //保存帐号到cookie，有效期7天
		        setCookie('pswd',oPswd.value,7); //保存密码到cookie，有效期7天
		    }
			//表单提交
			toLogin();
		}
	});

	var toLogin = function() {
		// 初始化登录页面获取公共KEY
		$.indi.ajaxSubmit({
			url : _ctx + '/basic/getLoginPublicKey.do',
			success : function(data) {
				var encrypt = new JSEncrypt();
				encrypt.setPublicKey(data.publicKey);
				var userIdJm = encrypt.encrypt($("#i_userid").val());
				if (userIdJm == "false" || userIdJm == false) {
					layer.alert("登录加密失败，请联系管理员！", {
						icon : 0
					});
					return;
				} else
					$('#_usrId').val(userIdJm);
				var passwordjm = encrypt.encrypt($("#userpwd").val());
				if (userIdJm == "false" || userIdJm == false) {
					layer.alert("登录加密失败，请联系管理员！", {
						icon : 0
					});
					return;
				} else
					$('#_passWord').val(passwordjm);
				// 获取
				i_href = $("#to-login").attr('i_href');
				$.indi.ajaxSubmit({
					url : $("#to-login").attr('url'),
					success : login_sucess
				});
			}
		});
	};

	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			toLogin();
		}else if(code == 37){
			$("#i_userid").focus();
		}else if(code == 39){
			$("#userpwd").focus();
		}
	};

	var login_sucess = function(data) {
		var status = data.stuatus;
		if (status != '0')
			layer.alert(data.errInfo, {
				icon : 0
			});
		else
			window.location.href = i_href;
	};

	/*
	 * //通过模和公钥参数获取公钥 var key = new RSAUtils.getKeyPair("123423", "", "456789");
	 * //颠倒密码的顺序，要不然后解密后会发现密码顺序是反的 var reversedPwd =
	 * password.split("").reverse().join(""); //对密码进行加密传输 var encrypedPwd =
	 * RSAUtils.encryptedString(key,"我是谁"); console.log(encrypedPwd);
	 */

  //设置cookie
  function setCookie(name,value,day){
    var date = new Date();
    date.setDate(date.getDate() + day);
    document.cookie = name + '=' + value + ';expires='+ date;
  };
  //获取cookie
  function getCookie(name){
    var reg = RegExp(name+'=([^;]+)');
    var arr = document.cookie.match(reg);
    if(arr){
      return arr[1];
    }else{
      return '';
    }
  };
  //删除cookie
  function delCookie(name){
    setCookie(name,null,-1);
  };
});