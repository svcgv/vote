/**
 * 公共js方法
 */
/*
	*自定义表单序列化方法
	*/
	 $.fn.serializeObject = function()    
	 {    
	    var o = {};    
	    var a = this.serializeArray();    
	    $.each(a, function() {    
	        if (o[this.name]) {    
	            if (!o[this.name].push) {    
	                o[this.name] = [o[this.name]];    
	            }    
	            o[this.name].push(this.value || '');    
	        } else {    
	            o[this.name] = this.value || '';    
	        }    
	    });    
	    return o;    
	 };  