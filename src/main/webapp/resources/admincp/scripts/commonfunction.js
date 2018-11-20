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
/**
 * 通过枚举的key值获取
 * @returns {{}}
 */
function getCodeValue(key,a)
{
    $.each(a, function(index,value) {
        if (key==value.codeKey) {
            key=value.codeVal;
            return key;
        }
    });
    key = '';
    return key;
};