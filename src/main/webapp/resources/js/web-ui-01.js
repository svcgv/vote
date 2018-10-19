//得到选中的记录，返回选中的记录条数
function selectRow(_str) {
	var obj = new Object();
	var keyId = ""; // 选择的用户
	$("tr td :checkbox:checked").each(function() {
		if (this.checked) {
			keyId = keyId + $(this).siblings("#" + _str).val() + ",";
		}
	});
	obj.size = $("tr td :checkbox:checked").length; // 记录条数
	obj.val = keyId.substr(0, keyId.length - 1);
	return obj;
}

function setPopUpInfo(widht, height, title) {
	if (title != "" && title.length > 0) {
		$('.layui-layer-title').html(title);
	}
	$('.layui-layer-iframe').css('height', height);
	$('.layui-layer-iframe').css('width', widht);
	// $('iframe').css('height',height);
}

/*添加选中表格的tr，自动选中tr的checkbox @zhengwei*/
function initTable(tableId,single){
	$('#'+tableId).on('click', 'tr', function(e){  
		var isChecked =$(this).find('td:eq(0) input').is(":checked");
		// 获取table中所有的行 
		var trs = $(this).parent('tbody').find('tr');
		if(single){
			$.each(trs, function(index, item){
				$(item).find('td:eq(0) input').prop('checked', false);
			});
		}
		//如果点击的当前行被选中，则点击后取消选中；如果未被选中，则点击后设置为选中
		if(isChecked){
			// 非选中当前点击的行  
			$(this).find('td:eq(0) input').prop('checked',false);  
		}else{
			// 选中当前点击的行  
			$(this).find('td:eq(0) input').prop('checked',true);  
		}
	});
}

// 初始化表单验证
(function($) {
	var checkval = ""; // 复选框选中的值，分页的时候，前一页的数据也会保存
	var validata = ""; //表单验证对象
	$.indi = {
		ajaxSubmit : function(opt) {
			var intiParams = {
				type : "post", // 提交类型
				url : "", // 提交地址
				contentType : "application/json; charset=utf-8", // 提交类型
				dataType : "json", // 数据提交方式
				data : "", // 请求数据
				success : "", // 后台返回成功执行函数
				error : "", // 后台返回失败执行函数
				form : "", // 提交的表单
				isFrom : true, // 是否需要取表单数据传入后台
				isCheck : true, // 是否检查文本框
				closeMode : false
			// 返回成功是否关闭弹出框
			}
			console.log(opt.data);
			var unid = layer.load();
			var params = $.extend(intiParams, opt);
			// 表单检查
			if (params.isFrom) {
				if (params.form == null || params.form == "") {
					params.form = $("form:first"); // 获取第一个表单里面的元素提交
					console.log(params.data);
					if (params.form == null || params.form == ""
							|| params.form.length == 0) {
						if (top.location != self.location) {
							parent.layer.alert("当前页面没有定义的表单对象！", {
								icon : 2
							});
						} else {
							layer.alert("当前页面没有定义的表单对象！", {
								icon : 2
							});
						}
						layer.close(unid);
						return;
					}
				}
				// 判断是否需要需要进行验证
				if (params.isCheck) {
					var validata = params.form.validate({ignore:".ignore"});
					if (!params.form.valid()) {
						//validata.resetForm();
						layer.close(unid);
						return; // 验证未通过
					}
				}
				// 如果传入参数为空，则去表单中的数据
				if (params.data == "" || params.data == null) {
					params.data = params.form.serializeJson();
				}
			}
			// 判断当前用户是否有点击权限
			$.ajax({
				// 要用post方式
				type : params.type,
				url : params.url,
				contentType : params.contentType,
				dataType : params.dataType,
				timeout : 30000,
				data : params.data,
				processData: false,
				success : function(data) {
					layer.close(unid);
					if (typeof params.success === 'function') {
						params.success(data);
					}
					/*
					 * if(params.closeMode){//关闭弹出框 $.indi.closePopup(); }
					 */
				},
				error : function(err) {
					layer.close(unid)
					if (err.statusText == "timeout") {
						if (top.location != self.location) {
							parent.layer.alert("请求处理超时，请稍候刷新查看处理结果！！", {
								icon : 0
							});
						} else {
							layer.alert("请求处理超时，请稍候刷新查看处理结果！！", {
								icon : 0
							});
						}
					}else if(err.status == 404){
						layer.alert("未找到请求的页面，错误码404！", {
							icon : 0,title:"系统异常"
						});
					} else {
						if(top.location!=self.location){ 
							parent.layer.alert(err.responseText,{icon: 2,title:"系统异常",area: ['700px', '500px']});
	            		}else{
	            			layer.alert(err.responseText,{icon: 2,title:"系统异常",area: ['700px', '500px']});
	            		}
					}

					if (typeof params.error === 'function') {
						params.error();
					}
				}
			});
		},
		submit : function(opt) {
			var unid = layer.load();
			var params = {
				url : '',
				method : 'post',
				form : ''
			};
			var params = $.extend(params, opt);
			if (params.form == '' || params.form == null) {
				params.form = $("form:first"); // 获取第一个表单里面的元素提交
			}
			// 表单检查
			if (params.form == null || params.form == ""
					|| params.form.length == 0) {
				layer.alert("当前页面没有定义form表单对象！", {
					icon : 2
				});
				layer.close(unid);
				return;
			} else if (params.isCheck) {
				params.form.validate({ignore:".ignore"});
				if (!params.form.valid()) {
					layer.close(unid);
					return; // 验证未通过
				}
			} else {
				params.form.removeAttr("noValidate");
				for (var i = 0; i < params.form[0].length; i++) {
					$(params.form[0][i]).addClass("ignore");
				} 
			}

			params.form.attr("action", params.url);
			params.form.attr("method", params.method);
			params.form.submit();
		},
		loadPages : function(opt) {// 为table创建分页
			$('#pageId').html('');
			// 初始化分页控制
			var pageHtml = '<div class="talbe-totle  col-md-4 col-xs-4">第'
					+ opt.pageNum
					+ '页/共'
					+ opt.pages
					+ '页 &nbsp;总'
					+ opt.total
					+ '条</div>'
					+ '<div class="col-md-8 col-xs-8" align="right"><ul class="pagination"><li value="111111111"><a href="javascript:void(0);">&laquo;</a></li>';
			if (opt.pages > 5) {// 当页码大于5页时，则中间显示。。
				if (opt.pageNum == 1 || opt.pageNum == 2 || opt.pageNum == 3) { // 当显示第一页的
					// 时候
					for (var i = 1; i <= 5; i++) {
						if (opt.pageNum == i) {
							pageHtml = pageHtml + '<li class="active" value="'
									+ i + '"><a href="javascript:void(0);">'
									+ i + '</a></li>';
						} else {
							pageHtml = pageHtml + '<li  value="' + i
									+ '"><a href="javascript:void(0);">' + i
									+ '</a></li>';
						}
					}
				} else if (opt.pageNum == (opt.pages - 1)
						|| opt.pageNum == (opt.pages - 2)
						|| opt.pageNum == opt.pages) { // 当显示第一页的 时候
					for (var i = (opt.pages - 4); i <= opt.pages; i++) {
						if (opt.pageNum == i) {
							pageHtml = pageHtml + '<li class="active" value="'
									+ i + '"><a href="javascript:void(0);">'
									+ i + '</a></li>';
						} else {
							pageHtml = pageHtml + '<li  value="' + i
									+ '"><a href="javascript:void(0);">' + i
									+ '</a></li>';
						}
					}
				} else {
					pageHtml = pageHtml + '<li  value="' + (opt.pageNum - 2)
							+ '"><a href="javascript:void(0);">'
							+ (opt.pageNum - 2) + '</a></li>';
					pageHtml = pageHtml + '<li  value="' + (opt.pageNum - 1)
							+ '"><a href="javascript:void(0);">'
							+ (opt.pageNum - 1) + '</a></li>';
					pageHtml = pageHtml + '<li class="active" value="'
							+ opt.pageNum + '"><a href="javascript:void(0);">'
							+ opt.pageNum + '</a></li>';
					pageHtml = pageHtml + '<li  value="' + (opt.pageNum + 1)
							+ '"><a href="javascript:void(0);">'
							+ (opt.pageNum + 1) + '</a></li>';
					pageHtml = pageHtml + '<li  value="' + (opt.pageNum + 2)
							+ '"><a href="javascript:void(0);">'
							+ (opt.pageNum + 2) + '</a></li>';
				}
			} else {
				for (var i = 1; i <= opt.pages; i++) {
					if (opt.pageNum == i) {
						pageHtml = pageHtml + '<li class="active" value="' + i
								+ '"><a href="javascript:void(0);">' + i
								+ '</a></li>';
					} else {
						pageHtml = pageHtml + '<li  value="' + i
								+ '"><a href="javascript:void(0);">' + i
								+ '</a></li>';
					}
				}
			}

			pageHtml = pageHtml
					+ '<li value="99999999"><a href="javascript:void(0);">&raquo;</a></li></ul></div>';
			$('#pageId').html(pageHtml);
			$('.pagination li a').click(function() {
				$.indi.handPagesClick(opt, this);
			});
		},
		handPagesClick : function(opt, obj) {// 点击分页的监听事件
			// 取选中li的value值
			var ulVal = $('ul .active').attr("value"); // 当前显示页
			var val = $(obj).parent().attr("value"); // 当前点击页
			var liObj = "";
			if (val == '111111111') {
				val = 1;
				liObj = $('.pagination li:eq(1)');
			} else if (val == '99999999') {
				val = opt.pages;
				liObj = $('.pagination li:eq(' + val + ')');
			} else {
				liObj = $(obj).parent();
			}
			$('.pagination li').removeClass('active');
			liObj.addClass('active')
			$('#i-pages').val(val);
			// 控制分页，默认分页显示到10,当超过10需要做特殊处理
			var liHtml = "";
			if (parseInt(opt.pages) > 11 && parseInt(val) > 6) {
				// 需要增加页数太多需要分开，待开发！！
			}
			/*
			var jsonObj = JSON.parse(opt.data);
			jsonObj.currPage = val.toString();
			opt.data = JSON.stringify(jsonObj);
			//alert("hello "+jsonObj.currPage);
			opt.currPage = val; // 当前选中页码*/
			/*
			 * //得到当前页面复选框选中的值 if(typeof opt.key != "undefined"){ var checkval =
			 * $('#_checkval').val(); var rowList = selectRow(opt.key); var row =
			 * rowList.val.split(","); for (var i = 0; i < row.length; i++) {
			 * if(checkval.indexOf(row[i]) < 0){ checkval += "," + row[i]; } }
			 * $('#_checkval').val(checkval); }
			 */
			if (typeof arr == "undefined"){
				var arr = [];
			}
			
			var obj = selectRow("_tab_info_id");
			
			if (obj.val.length != 0){
				var infoId = $("#infoId").val();
				console.log("hello: "+infoId);
				if (infoId.length != 0){
					arr = JSON.parse(infoId);	
				}	
				arr[ulVal-1] = obj.val;
				/*
				if ($("#infoId").val().length != 0){
					var infoId = $("#infoId").val() + ","+obj.val;
				}else{
					var infoId = obj.val;
				}*/
				$("#infoId").val(JSON.stringify(arr));
			}
			
			$.indi.ajaxSubmit({
				url : opt.url,
				//data: opt.data,
				success : function(data) {
					$.indi.loadTable(data, opt);
				}
			});
		},
		loadTable : function(data, params) { // 动态加载table,根据已有数据加载table
			var opt = {
				isPages : true
			// 是否加载分页
			};
			var opt = $.extend(opt, params)
			/** 取传入的集合* */
			var list;
			var array = $('.table').attr('array');
			if (array != "" && typeof (array) != "undefined")
				list = data[array];
			else
				list = data.listInfo;
			/** *取分页信息* */
			var pageInfo;
			var page = $('.table').attr('page');
			if (page != "" && typeof (page) != "undefined")
				pageInfo = data[page];
			else
				pageInfo = data.pageInfo;
			/** *组装table数据，根据target_data来取值* */
			var pageHtml = function(pageInfo, list) {
				var html = "";
				var cnt = 0;
				if (typeof pageInfo != "undefined") {
					cnt = (pageInfo.pageNum - 1) * pageInfo.pageSize;
				}
				var tbody = $('.table thead tr:eq(0)').children();
				for (var i = 0; i < list.length; i++) {
					html += "<tr>";
					var isSelect = list[i]["isSelect"];
					for (var j = 0; j < tbody.length; j++) {
						var node = tbody[j];
						var target_data = $(node).attr('target_data');
						if (target_data == 'checkbox') {
							var _tdList = $(node).children();
							html += '<td target_data="' + target_data
									+ '"><input type="checkbox"';
							if (isSelect == 'Y') {
								html += 'checked="checked"';
							}
							html += '/>';
							for (var k = 0; k < _tdList.length; k++) {
								var hidd = $(_tdList[k]).attr('type');
								if (hidd == 'hidden') {
									var _target_data = _tdList[k].attributes['target_data'].nodeValue;
									var _id = _tdList[k].id;
									var _val = list[i][_target_data];
									html += '<input type="hidden" id="' + _id
											+ '"  ' + 'target_data="'
											+ _target_data + '"   ' + 'value="'
											+ _val + '"> ';
								}
							}
							html += '</td>';
						} else if (target_data == 'count')
							html += '<td lass="center" target_data="'
									+ target_data + '">' + (cnt + i + 1)
									+ '</td>'; // 如果是序号做特殊处理
						else {
							target_data_val = list[i][target_data];
							if (target_data.indexOf('.') > 0) { // 当存在点时，则需要循环取对象的值
								target_data_val = list[i];
								var targetArray = target_data.split('.'); // 分割取值字段
								for (var h = 0; h < targetArray.length; h++) {
									target_data_val = target_data_val[targetArray[h]];
								}
							}
							if (target_data_val == null
									|| target_data_val == 'undefined') {
								target_data_val = '';
							}
							var type = $(node).attr('type'); // 查看TD的类型
							if (type == "date") {
								var dateVal = target_data_val.substring(0, 4)
										+ "-" + target_data_val.substring(4, 6)
										+ "-" + target_data_val.substring(6, 8);
								html += '<td lass="center" target_data="'
										+ target_data + '" type="date">'
										+ dateVal + '</td>';
							} else if (type == "time") {
								var dateVal = target_data_val.substring(0, 2)
										+ "-" + target_data_val.substring(2, 4)
										+ "-" + target_data_val.substring(4, 6);
								html += '<td lass="center" target_data="'
										+ target_data + '" type="date">'
										+ dateVal + '</td>';
							} else if (type == "code") {
								var codeNo = $(node).attr('codeNo'); // 查看TD的类型
								var keyVal = $.basic.getCodeVal(codeNo,
										target_data_val);
								html += '<td lass="center" target_data="'
										+ target_data
										+ '" type="code" codeNo="' + codeNo
										+ '">' + keyVal + '</td>';
							} else {
								html += '<td lass="center" target_data="'
										+ target_data + '">' + target_data_val
										+ '</td>';
							}
						}

					}
					html += "</tr>";
				}
				$('.table tbody').html('');
				return html;
			}
			$('.table tbody').html(pageHtml(pageInfo, list));
			if (opt.isPages) {
				opt = $.extend(opt, pageInfo);
				$.indi.loadPages(opt);
			}
		},
		loadTableByQry : function(opt) {// 动态加载table,自动查询
			var a = function(data) {
				$.indi.loadTable(data, opt);
				if (typeof parent.iFrameHeight != "undefined") {
					parent.iFrameHeight();
				}
			}
			$.indi.ajaxSubmit({
				url : opt.url,
				data: opt.data,
				success : a
			});
		},
		openPopup : function(opt) { // 打开一个弹出框
			var params = $.extend({
				area : [ '850px', '600px' ],
				offset : [ '30px', '50px' ],
				maxmin : true,
				form : "",
				full : false,
				isDate : true,// 是否取form表单数据
				title : '标题',
				parentOpen : false
			}, opt);
			if (params.form == "" || params.form == null) {
				params.form = $("form:first"); // 获取第一个表单里面的元素提交
			}
			if (params.form != "" && params.form != null && params.isDate) {
				var obj = params.form.serializeGet();
				if (params.url.indexOf('?') > 0)
					params.url = params.url + "&" + obj;
				else
					params.url = params.url + "?" + obj;
			}
			var openId = '';
			if (params.parentOpen) {
				// 组装请求参数
				var reqParams = {
					type : 2,
					title : params.title,
					// offset:params.offset,
					fixed : false,
					maxmin : true,
					area : params.area,
					content : params.url
				};
				openId = parent.layer.open(reqParams);
			} else {
				// 组装请求参数
				var reqParams = {
					type : 2,
					title : params.title,
					offset : params.offset,
					maxmin : true,
					area : params.area,
					content : params.url
				};
				openId = layer.open(reqParams);
			}
			return openId;
		},
		closePopup : function(index) { // 关闭弹出页面
			if (index == "" || index == null)
				index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	}
	$.fn.addRow = function(name_) { // 为表格新增行，清楚指定name的值
		var tr = $(this).find('tr:last');
		var trHtml = '<tr>' + tr.html() + '</tr>';
		$(this).find('tr:last').after(trHtml);
		// 查找新增的行，把input框设为空
		var nweTr = $(this).find('tr:last');
		if (name_ != "" && name_ != null) {
			// 处理隐藏于的ID值
			var id_ = nweTr.find('input[name="' + name_ + '"]')
			id_.val('');
		}
		// 处理显示框的值为空
		nweTr.find('input[type="text"]').val('')
	}
	$.fn.addRow = function() { // 表格新增行，清楚表格中type为text和hidden中的值
		var tr = $(this).find('tr:last');
		var trHtml = '<tr>' + tr.html() + '</tr>';
		$(this).find('tr:last').after(trHtml);
		// 查找新增的行，把input框设为空
		var nweTr = $(this).find('tr:last');
		// 处理显示框的值为空
		nweTr.find('input[type="text"]').val('');
		nweTr.find('input[type="hidden"]').val('');
	}
	$.fn.delRow = function(index) { // 删除表格中行
		var length = $(this).find('tr').length;
		if (length == 1) {
			parent.layer.alert("请至少保留一行数据！", {
				icon : 3
			});
		} else {
			var row = $(">tbody>tr:eq(" + index + ")", this)
			row.remove();
		}
	}
})(jQuery);

//判断字符是否为空的方法
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}


