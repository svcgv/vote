<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<script type="text/javascript">
$(function(){
	 　　$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
	 　　$('.tree li.parent_li > span').on('click', function (e) {
		  　　 var children = $(this).parent('li.parent_li').find(' > ul > li');
		  　　if (children.is(":visible")) {
			   　　children.hide('fast');
			   　　$(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
			  　　} else {
			   　　children.show('fast');
			   　　$(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
		  　　}
		  　　e.stopPropagation();
	 　　});
});
</script>
</head>
<body class="body-modle">
	<div class=content>
		<div class="tree well">
			<ul>
				<li><span><input type="checkbox" > <i class="icon-folder-open"></i> 顶级节点1</span> <a
					href="">全部展开</a>
					<ul>
						<li><span><input type="checkbox"><i class="icon-minus-sign"></i> 一级节点1</span> <a
							href=""></a>
							</li>
						<li><span><input type="checkbox"> <i class="icon-minus-sign"></i> 一级节点2</span> <a
							href=""></a>
							
						</li>
					</ul></li>
				<li><input type="checkbox"><span><i class="icon-folder-open"></i> 顶级节点2</span> <a
					href=""></a>
					<ul>
						<li><input type="checkbox"><span><i class="glyphicon glyphicon-leaf"></i>角色</span> <a href=""></a>
						</li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>