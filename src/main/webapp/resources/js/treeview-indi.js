(function($) {
	$.fn.initTreeView = function(data,opt){
		var params=$.extend({
			selectPanent:true,
			multiSelect:true
		},opt);
		var $tree = $(this).treeview({
		  	 data: data,multiSelect:params.multiSelect
		 });
		// 判断当前选中的菜单是否有子菜单，有的话全部选中
		var selectChild = function(node,divId) {
			// 不存在子菜单则直接返回，无需处理
			if (node.hasOwnProperty("nodes")) {
				var array = node.nodes;
				for (var i = 0; i < array.length; i++) {
					var childNode = $('#'+divId).treeview('getNode', array[i].nodeId);
					if (!childNode.state.selected) {
						$('#'+divId).treeview('toggleNodeSelected', [ childNode, {
							silent : true
						} ]);
					}
					selectChild(childNode,divId);
				}
			}
		}
		
		// 判断当前取消的菜单是否有子菜单，有的话全部取消
		var unSelectChild = function(node,divId) {
			if (node.hasOwnProperty("nodes")) {
				var array = node.nodes;
				for (var i = 0; i < array.length; i++) {
					var childNode = $('#'+divId).treeview('getNode', array[i].nodeId);
					if (childNode.state.selected) {
						$('#'+divId).treeview('toggleNodeSelected', [ childNode, {
							silent : true
						} ]);
					}
					unSelectChild(childNode,divId);
				}
			}
		}
		 // 选中子节点时选中父节点
		var selectParent = function(node,divId) {
			if (typeof (node.parentId) != "undefined") {
				var parentNode = $('#'+divId).treeview('getParent', node);
				if (!parentNode.state.selected) {
					$('#'+divId).treeview('toggleNodeSelected', [ parentNode, {
						silent : true
					} ]);
				}
				selectParent(parentNode,divId);
			}
		}
		
		// 取消子节点，需要判断
		var unSelectParent = function(node,divId) {
			if (typeof (node.parentId) != "undefined") {
				var parentNode =$('#'+divId).treeview('getParent', node);
				var array = parentNode.nodes;
				// 循环父菜单，如果父菜单有下有其他菜单选中，则父菜单状态不需要更改
				for (var i = 0; i < array.length; i++) {
					if (array[i].state.selected && array[i].nodeId != node.nodeId) {
						return;
					}
				}
				if (parentNode.state.selected) { // 选中了则设置未选中
					$('#'+divId).treeview('toggleNodeSelected', [ parentNode, {
						silent : true
					} ]);
				}
				unSelectParent(parentNode,divId);
			}
		}
		
		 var a = function(event, node){//节点被选中的事件
			 var divId = event.target.id;
			 selectParent(node,divId);
			 selectChild(node,divId);
		 }
		 var b = function(event, node){//节点取消
			 var divId = event.target.id;
			 unSelectParent(node,divId);
			 unSelectChild(node,divId);
		 }
		
		 if(params.selectPanent){
			 $(this).on('nodeSelected', a);
			 $(this).on('nodeUnselected', b);
		 }
	}
	
	$.fn.getNodes = function(){  //得到所有的节点
		var nodeId = "";
		return $(this).treeview('getSelected', nodeId);
	}
	
	$.fn.getNodesByField = function(field){//得到所有选中节点的某个字段信息
		var array = new Array();
		var nodeList = $(this).treeview('getSelected', '');
		for(var i=0;i<nodeList.length;i++){
			array[i] = nodeList[i][field];
		}
		return array;
	}
	
	$.fn.getNodesByFieldStr = function(field){//得到所有选中节点的某个字段信息,以|分割
		var obj = '';
		var nodeList = $(this).treeview('getSelected', '');
		for(var i=0;i<nodeList.length;i++){
			obj = obj + nodeList[i][field] + "|";
		}
		return obj.substr(0,obj.length-1);
	}
})(jQuery);

