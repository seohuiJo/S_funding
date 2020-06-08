<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
src="/js/jquery-3.3.1.js"></script>

	<style>
		#updateFrm{
			width:1000px;
			margin: 0 auto;			
		}
	</style>
</head>
<body>
	<script>
		$(function(){
			$("#fileDelBtn").click(function(){
				$(".delFile").hide();
				$("#file").show();
				$("input[name=status]").val('delete');
			});
		});
	</script>
	
	<jsp:include page="/common/header.jsp"/>
	
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
		<form action="/updateNotice" method="post" 
		enctype="multipart/form-data" id="updateFrm">
			<h1>공지사항 수정</h1>
			<input type="hidden" name="noticeNo" value="${n.noticeNo }">
			<table class="table table-bordered">
				
				<tr>
					<th>제목</th>
					<td>
						<input type="text" class="form-control"
						name="noticeTitle" value="${n.noticeTitle }">						
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${n.noticeWriter }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${n.noticeDate }</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="hidden" name="status" value="stay">
						<c:if test="${not empty n.filename }">
							<img src="/img/file.png" width="16px" class="delFile">
							<input type="file" name="filename" 
							id="file" style="display:none;">
							<span class="delFile">${n.filename }</span>
							<button type="button" id="fileDelBtn" 
							class="btn btn-primary btn-sm delFile">
								파일삭제
							</button>
							<input type="hidden" name="oldFilename" 
							value="${n.filename }">
							<input type="hidden" name="oldFilepath" 
							value="${n.filepath }">
						</c:if>
						<c:if test="${empty n.filename }">
							<input type="file" name="filename">
						</c:if>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="noticeContent" rows="3" style="width:100%;">${n.noticeContent }</textarea>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center">
						<button type="submit" 
						class="btn btn-outline-primary">
						수정완료</button>
					</th>
				</tr>
			</table>		
		</form>
		</div>
	</section>
</body>
</html>