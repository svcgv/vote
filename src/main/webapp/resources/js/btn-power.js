/*******************************************************************************
 * *************btn-power.js************** *************按钮权限控制js**************
 * *************初始化页面，调用后台查询当前页面按钮当前用户是否有操作权限**************
 * *************有操作权限，可以点击************** 无操作权限，不可以点击或隐藏不可见*************
 ******************************************************************************/
$(function() {
	var _btnList = $(':button,a');
	var initButton = function(btnList) {
		for (var i = 0; i < _btnList.length; i++) {
			var power = $(_btnList[i]).attr('power');
			if (typeof (power) == "undefined")
				continue;
			else if (btnList == null || btnList == "")
				$(_btnList[i]).css('display', 'none');
			else if (btnList.indexOf(power) < 0)
				$(_btnList[i]).css('display', 'none');
			else
				continue;
		}
	}
	if (_btnList.length > 0) {
		// 请求后台查询当前用户当前页面的按钮权限
		$.indi.ajaxSubmit({
			url : _ctx + "/btnPower/qryBtnByUser.do",
			closeMode : true,
			isCheck : false,
			isFrom:false,
			success : function(data) {
				initButton(data.btnList);
			}
		});
	}

})