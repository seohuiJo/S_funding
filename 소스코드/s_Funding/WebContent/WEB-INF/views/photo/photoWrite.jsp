<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

<title>사진 등록</title>
</head>
<body>
	<section class="container">
		<form action="/insertPhoto" method="post" enctype="multipart/form-data" style="width:1000px;margin:0 auto;">
			<table class="table table-bordered">
				<tr>
					<th>작성자</th>
					<td>
						${sessionScope.member.userId }
						<input type="hidden" name="photoWriter" value="${sessionScope.member.userId }">
					</td>
				</tr>	
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="filename" onchange="loadImg(this);">
					</td>
				</tr>
				<tr>
					<td>이미지 보기</td>
					<td>
						<div id="img-viewer">
							<img id="img-view" width="350">
						</div>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="photoContent" rows="3" style="width:100%"></textarea>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;" colspan="2">
						<button type="submit">등록</button>
					</th>
				</tr>
			</table>
		</form>
	</section>
	
	<script>
		function loadImg(obj){
			console.log(obj);
			if(obj.files.length!=0 && obj.files[0]!=0){
				var reader=new FileReader();
				reader.readAsDataURL(obj.files[0]);
				reader.onload=function(e){
					$("#img-view").attr("src", e.target.result);
				}
			}else{
				$("#img-view").attr("src","");
			}
			
		}
	
	</script>

</body>
</html>












