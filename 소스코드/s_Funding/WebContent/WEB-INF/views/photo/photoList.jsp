<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사진게시판</title>
</head>
<body>
	<h1>사진게시판</h1>
	<section class="container">
	<div id="photo-wrapper" style="padding:100px;"></div>
	<div id="photo-btn-container" style="text-align:center;">
		<button id="more-btn" totalCount="${totalCount }" currentCount="0" value="">더보기</button>
		<button id="write-btn">글쓰기</button>
	</div>
	</section>
	
	<script>
		$("#write-btn").click(function(){
			location.href="/photoWriteForm";
		});
		
		function fn_more(start){
			var param = {start:start};
			$.ajax({
				url : "/photoMore",
				data : param,
				type : "post",
				dataType : "json",
				success : function(data){
					var html = "";
					for(var i=0; i<data.length;i++){
						html += "<div class='border border-dark' style='width:800px;margin:0 auto;margin-bottom:10px;'>";
						html += "<img src='/upload/photo/"+data[i].photoFilepath+"' width='100%'>";
						html += "<p class='caption'>"+data[i].photoContent+"</p></div>";
					}
					$("#photo-wrapper").append(html);
					$("#more-btn").val(Number(start)+5);
					$("#more-btn").attr("currentCount",Number($("#more-btn").attr("currentCount"))+data.length);
					var totalCount = $("#more-btn").attr("totalCount");
					var currentCount = $("#more-btn").attr("currentCount");
					if(totalCount == currentCount){
						$("#more-btn").attr("disabled",true);
						$("#more-btn").css("cursor","not-allowed");
					}
					
					
				},
				error : function(){
					console.log("�ㅽ뙣");
				}
			});
			}
			$(function(){
			fn_more(1);
			$("#more-btn").click(function(){
				fn_more($(this).val());
			});
			});
	</script>
</body>
</html>










