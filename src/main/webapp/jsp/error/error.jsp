<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/comm/mate.jsp"%>
<html>
<head>
<link href="${ctx }/resources/assets/css/font-awesome.min.css" rel="stylesheet" />
		
<link rel="stylesheet" href="${ctx }/resources/assets/css/jquery-ui-1.10.3.full.min.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${ctx }/resources/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctx }/resources/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx }/resources/assets/css/ace-skins.min.css" />
<script type="text/javascript">
//关闭当前弹出框
function close_01(){
	 $.indi.closePopup();
}

</script>
</head>
<body class="body-modle">
	<div  class=content >
		<div class="error-container">
			<div class="well">
				<h1 class="grey lighter smaller">
					<span class="blue bigger-125">
						<i class="icon-random"></i>
						700
					</span>
					系统异常
				</h1>

				<hr />
				<h3 class="lighter smaller">
					But we are working
					<i class="icon-wrench icon-animated-wrench bigger-125"></i>
					on it!
				</h3>

				<div class="space"></div>

				<div>
					<h4 class="lighter smaller">系统提示:</h4>

					<ul class="list-unstyled spaced inline bigger-110 margin-15">
						<li>
							<i class="icon-hand-right blue"></i>
							${message }
						</li>

						<li>
							<i class="icon-hand-right blue"></i>
							给我们更多的信息，这个具体的错误是如何发生的！ 
						</li>
					</ul>
				</div>

				<hr />
				<div class="space"></div>

				<div class="center">
					<a href="#" class="btn btn-grey" onclick="javascript :history.back(-1);">
						<i class="icon-arrow-left"></i>
						Go Back
					</a>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>