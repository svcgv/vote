
;(function($){

$.fn.float=function(position,parent){
	var pos={},parent=typeof(parent)=='undefined'?window:parent;
	if(typeof position=='string'){
		var a=position.split(' ');
		switch(a.length){
			case(1):
				pos.X=pos.Y=a[0];
				break;
			case(2):
				pos.X=a[0];
				pos.Y=a[1];
				break;
			default:
				return this;
		}
	}else if(typeof position=='object'){
		pos=position;
		if(pos.x){
			pos.X=pos.x;
			delete pos.x;
		}
		if(pos.y){
			pos.Y=pos.y;
			delete pos.y;
		}
	}else{
		return this;
	}
	return this.each(function(){
		var loc={},isChild=parent==window || $(parent).children().index(this)>-1;
		if(pos.X){
			switch(pos.X){
				case('left'):
					loc.left=0;
					break;
				case('right'):
					loc.right=0;
					break;
				case('center'):
					loc.left=($(parent).width()-$(this).outerWidth())/2;
					break;
				default:
					if(parseInt(pos.X)>0){
						loc.left=parseInt(pos.X);
					}else{
						loc.right=-parseInt(pos.X);
					}
					break;
			}
		}else{
			if(pos.left) loc.left=pos.left;
			if(pos.right) loc.right=pos.right;
		}
		if(pos.Y){
			switch(pos.Y){
				case('top'):
					loc.top=0;
					break;
				case('bottom'):
					loc.bottom=0;
					break;
				case('center'):
					loc.top=($(parent).height()-$(this).outerHeight())/2;
					break;
				default:
					if(parseInt(pos.Y)>=0){
						loc.top=parseInt(pos.Y);
					}else{
						loc.bottom=-parseInt(pos.Y);
					}
					break;
			}
		}else{
			if(pos.top) loc.top=pos.top;
			if(pos.bottom) loc.bottom=pos.bottom;
		}

		if(isChild){
			var scroll=parent==window?document:parent;
			if(loc.left){
				loc.left+=$(scroll).scrollLeft();
			}
			if(loc.right){
				loc.left=$(scroll).scrollLeft()+$(parent).width()-$(this).outerWidth()-loc.right;
			}
			if(loc.top){
				loc.top+=$(scroll).scrollTop();
			}
			if(loc.bottom){
				loc.top=$(scroll).scrollTop()+$(parent).height()-$(this).outerHeight()-loc.bottom;
			}
		}else if(parent!=window && !isChild){
			var $pos=$(parent).position();
			if(loc.left) loc.left+=$pos.left;
			if(loc.top) loc.top+=$pos.top;
			if(loc.right) loc.left+=$pos.left+$(parent).outerWidth();
			if(loc.bottom) loc.top+=$pos.top+$(parent).outerHeight();
		}

		if(parent!=window && $(parent).css('position')=='static'){
			$(parent).css('position','relative');
		}

		var css_pos=(parent!=window)?'absolute':'fixed';
		if($(this).css('position')!=css_pos) $(this).css('position',css_pos);
		if(parseInt($(this).css('z-index'))<=0) $(this).css('z-index',9999);

		$(this).css($.extend({left:'auto',right:'auto',top:'auto',bottom:'auto'},loc));
	});
};

})(jQuery);