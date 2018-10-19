(function($) {
	$.fn.serializeJson = function() {
		var obj = {};
		var count = 0;
		$.each(this.serializeArray(), function(i, o) {
			var n = o.name, v = o.value;
			count++;
			obj[n] = obj[n] === undefined ? v : $.isArray(obj[n]) ? obj[n]
					.concat(v) : [ obj[n], v ];
		});
		//obj.nameCounts = count + "";//表单name个数  
		return $.toJSON(obj);
	};
	$.fn.serializeGet=function(){
		var obj = '';
		$.each(this.serializeArray(), function(i, o) {
			var n = o.name, v = o.value;
			obj=obj+n+'='+v+'&';
		});
		return obj.substr(0,obj.length-1);
	}
	$.fn.setParentVal=function(){  //把当前控件的值设置成父页面的值
		var objName = $(this).attr("name");
		var objParent = $(window.parent.document).find('input[name='+objName+']').val();
		$(this).val(objParent);
	}
	$.basic={
		getCodeVal:function(codeNo,codeKey){
			var obj = new Object();
			obj.codeNo = codeNo;
			obj.codeKey = codeKey;
			var keyVal="";
			$.ajax({     
	            //要用post方式      
	            type: "post",     
	            url: _ctx+"/basic/getCodeVal",     
	            async: false,
	            contentType: "application/json; charset=utf-8",     
	            dataType: "json",
	            timeout:5000,
	            data:$.toJSON(obj),
	            success:function(data){
	            	keyVal = data.keyVal;
	            },     
	            error:function(err){
	            	layer.alert("数据字典转换失败！！",{icon: 2});
	            }
	        });
			return keyVal;
		},formatDateTime:function (date) {  
	        var y = date.getFullYear();  
	        var m = date.getMonth() + 1;  
	        m = m < 10 ? ('0' + m) : m;  
	        var d = date.getDate();  
	        d = d < 10 ? ('0' + d) : d;  
	        var h = date.getHours();  
	        h=h < 10 ? ('0' + h) : h;  
	        var minute = date.getMinutes();  
	        minute = minute < 10 ? ('0' + minute) : minute;  
	        var second=date.getSeconds();  
	        second=second < 10 ? ('0' + second) : second;  
	        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;  
	    }
	}
})(jQuery);