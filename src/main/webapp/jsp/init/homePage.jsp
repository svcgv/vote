<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="GBK"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<style type="text/css">
.bg-image {
	background: url(${ctx}/resources/images/sy.png) no-repeat center;
	background-size: 100% 100%;
}

.blue {
	color: #478fca !important;
}
</style>
<script type="text/javascript">
	$(function(){
		$.indi.ajaxSubmit({
			url : '${ctx}/login/queryHomeMenuInfo.do',
			isCheck : false,
			success : function(data) {
				if (data.length > 0) {
					var html = '';
					for (var i = 0; i < data.length; i++) {
						var menu = data[i];
						html+='<div class="form-group" style="margin-left:0px;margin-right:0px">';
						html+='<label class="col-sm-2 blue text-center">'+menu.menuName+'</label>';
						html+='<div class="col-sm-8"><img src="'+_ctx+'/resources/images/fgx.jpg"></div></div>';
						html+='<div class="form-group" style="margin-left:0px;margin-right:0px">';
						var menuList = data[i].menuVo;
						if(menuList.length > 0){
							for (var j = 0; j < menuList.length; j++) {
								var oper = "javascript:addTabs({id:'" + menuList[j].menuId.substring(1) + "',title: '" + menuList[j].menuName + "',close: true,url: '" + _ctx+menuList[j].menuUrl + "'});"
								if(j%5==0){
									html+='<div class="col-sm-2"></div>';
								}
								html+='<div class="col-sm-2">';
								html+='<ul class="nav align-center">';
								html+='<li class="li-a"><a href="'+oper+'" style="text-align: center;">';
								html+='<img  src="${ctx }/resources/images/'+menuList[j].homeIcon+'"></a></li>';
								html+='<li style="text-align: center;">'+menuList[j].menuName+'</li></ul></div>';
							}
						}
						html+='</div>';
					}
					$('#_row').html(html);
				}
			}
		});

	})
	

	function addTabs(options) {
		$("#menu li", parent.document).removeClass("active");
		$("#menu_li_" + options.id, parent.document).addClass("active");
		$(".nav-tabs .active", parent.document).removeClass("active");
		$(".tab-content .active", parent.document).removeClass("active");
		id = "tab_" + options.id;
		//如果TAB不存在，创建一个新的TAB
		if (!$("#" + id)[0]) {
			//固定TAB中IFRAME高度
			mainHeight = $(window.parent.document.body).height() - 90;
			//创建新TAB的title
			title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">'
					+ options.title;
			//是否允许关闭
			if (options.close) {
				title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id + '"></i>';
			}
			title += '</a></li>';
			//是否指定TAB内容
			if (options.content) {
				content = '<div role="tabpanel" class="tab-pane" id="' + id + '">'
						+ options.content + '</div>';
			} else {//没有内容，使用IFRAME打开链接
				content = '<div role="tabpanel" class="tab-pane" id="' + id + '"><iframe src="'
						+ options.url
						+ '" width="100%" height="'
						+ mainHeight
						+ '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
			}
			//加入TABS
			$(".nav-tabs", parent.document).append(title);
			$(".tab-content", parent.document).append(content);
		}
		//激活TAB
		$("#tab_" + id, parent.document).addClass('active');
		$("#" + id, parent.document).addClass("active");
	};
</script>
</head>
<body>
	<div class="right-side-content">
		<div class="content">
			<form class="form-horizontal " name="form1" method="post" role="form">
				<div class="row" id="_row">
					

					
				</div>
			</form>
		</div>
	</div>
</body>
</html>
