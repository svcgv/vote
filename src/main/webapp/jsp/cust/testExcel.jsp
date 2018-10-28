<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>电子投票表决系统</title>
</head>
<script src="/vote/resources/js/jquery.min.js"></script>
<script type="text/javascript">
//一般直接写在一个js文件中
function uploadPic() { 
  var form = document.getElementById('upload'), 
    formData = new FormData(form); 
  $.ajax({ 
   url:"/vote/excel/test", 
   type:"post", 
   data:formData, 
   processData:false, 
   contentType:false, 
   success:function(res){ 
    if(res){ 
     alert("上传成功！"); 
    } 
    console.log(res); 
    $("#pic").val(""); 
    $(".showUrl").html(res); 
    $(".showPic").attr("src",res); 
   }, 
   error:function(err){ 
    alert("网络连接失败,稍后重试",err); 
   } 
  
  }) 
  
 }
 
 function getTableHeader(){
$.ajax({ 
	   url:"/vote/excel/detailList?tableId=1", 
	   type:"GET", 
	    
	   processData:false, 
	   contentType:false, 
	   success:function(res){ 
	    if(res){ 
	     //alert("上传成功！"); 
	    } 
	    console.log(res); 
	   }, 
	   error:function(err){ 
	    //alert("网络连接失败,稍后重试",err); 
	   } 
	  
	  }) }
	  
	   
	  function getTableHeader2 (){
	var fd = new FormData()
	var a = JSON.Stringify({'tableId': 1})
	  fd.append(data, a)
	  $.ajax({ 
		   url:"/vote/excel/detailList", 
		   type:"POST", 
		    data:fd,
		   processData:false, 
		   contentType:false, 
		   success:function(res){ 
		    if(res){ 
		     //alert("上传成功！"); 
		    } 
		    console.log(res); 
		   }, 
		   error:function(err){ 
		    //alert("网络连接失败,稍后重试",err); 
		   } 
		  
		  }) 
}
	
	  
</script>
<body  class="bg-image login-layout">
	<form id="upload" action="/vote/excel/test" method="post" enctype="multipart/form-data"> 
	 <input type="file" name="file" id="pic"/> 
	 <input type="button" value="提交" onclick="uploadPic();"/> 
	 <span class="showUrl"></span> 
	 <img src="" class="showPic" alt=""> 
	 <input type="submit" value="上传文件" />
	</form> 
	
	<button onclick='getTableHeader2()'>test</button>

</body>
</html>
