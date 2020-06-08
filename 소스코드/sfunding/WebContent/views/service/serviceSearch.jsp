<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>스펀딩 - 고객센터</title>
<link rel="icon" type="image/x-icon" href="/views/service/assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
<!--  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
     .target { display: inline-block; width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
</style>
</head>

<body id="page-top">
	<!-- 상단바-->
	<jsp:include page="/common/header.jsp"/>
	<br>
	<br>
	<!-- 메인 콘텐츠-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<!--메인 콘텐츠 위치-->
			<div class="row">
				<!--아이콘 위치-->
				<div class="col-1 col-md-1">
					<span class="h2"> <!--아이콘 코드 넣는곳--> <svg
							class="bi bi-pencil-square" width="1em" height="1em"
							viewBox="0 0 16 16" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
                            <path
								d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path fill-rule="evenodd"
								d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                        </svg>
					</span>
				</div>
				<!--제목 위치-->
				<div class="col-11 col-md-9">
					<span class="h2">고객센터</span>
				</div>
				<!--글쓰기 버튼-->
				<div class="col-md-2 d-flex justify-content-end">
					<c:if test="${sessionScope.member != null }">
					<form action="/serviceWriteForm" method="post">
						<button type="submit" class="btn btn-primary">글쓰기</button>
					</form>
					</c:if>
				</div>
				
				</div>
				<hr>
				<!--콘텐츠 페이지 내용 위치-->
				<div>
					<table class="table table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col" class="text-center" style="width: 5rem;">글번호</th>
								<th scope="col" class="text-center">제목</th>
								<th scope="col">카테고리</th>
								<th scope="col" style="width: 10rem;">작성자</th>
								<th scope="col" class="text-center" style="width: 10rem;">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageData.pageList }" var="service"
								varStatus="i">
								<tr>
									<th scope="row" class="text-center">${service.serviceNo}</th>
									<td><a class="target" href="/serviceSelect?serviceNo=${service.serviceNo}" style="color:black;">${service.serviceContent}</a></td>
									<td>${service.serviceCategory }</td>
									<td>${service.userId}</td>
									<td class="text-center">${service.sRegdate }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<!-- paging 페이징 -->
					${pageData.pageNavi }
				</div>
				
				
				<div class="d-flex justify-content-end">
					<form action="/serviceSearch" method="post">
						<label for="search" class="submit"> <svg
									class="bi bi-search" width="2em" height="2em"
									viewBox="0 0 16 16" fill="currentColor"
									xmlns="http://www.w3.org/2000/svg">
                            	<path fill-rule="evenodd"
										d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
 	                           <path fill-rule="evenodd"
										d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
        	                </svg>
							</label> <input type="text" name="search" id="search" size="20"
								placeholder="검색어를 입력해주세요." style="border: 2px solid orange;">
							<button type="submit" id="submit" class="btn btn-primary">검색하기</button>
					</form>
				</div>
				<br>
				<br>
			</div>
	</section>

	<!-- Footer 푸터, 하단바 -->
  	<jsp:include page="../../common/footer.html"/>


	<!-- Bootstrap core JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	<!-- Third party plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<!-- Contact form JS-->
	<script src="/views/service/assets/mail/jqBootstrapValidation.js"></script>
	<script src="/views/service/assets/mail/contact_me.js"></script>
	<!-- Core theme JS -->
	<script src="/views/service/js/scripts.js"></script>
	-->
</body>
</html>