<!DOCTYPE html>
    <head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
   
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>스펀딩 - 고객센터</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/views/service/css/styles.css" rel="stylesheet" />
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
   <!--  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>

<body id="page-top">
    <!-- 상단바-->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color:black;">
        <div class="container">
            <a class="navbar-brand js-scroll-trigger" href="#page-top">
                <img src="assets/logo1.svg" alt=""/>스펀딩</a><button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">Menu<i class="fas fa-bars ml-1"></i></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav text-uppercase ml-auto">
                     <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#services">프로젝트 보기</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#portfolio">프로젝트 하기</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#about">자유게시판</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#team">고객센터</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#contact">로그인</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <br><br>
    <!-- 메인 콘텐츠-->
    <section class="page-section bg-light" id="portfolio">
        <div class="container">
            <!--메인 콘텐츠 위치-->
            <div class="row">
                <!--아이콘 위치-->
                <div class="col-1">
                    <span class="h2">
                        <!--아이콘 코드 넣는곳-->
                        <svg class="bi bi-pencil-square" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                        </svg>
                    </span>
                </div>
                <!--제목 위치-->
                <div class="col-11 col-md-9">
                        <span class="h2">고객센터 게시판</span>
                    </div>
                    <!--글쓰기 버튼-->
                    <div class="col-md-2 d-flex justify-content-end">
                        <button type="button" class="btn btn-primary">글쓰기</button>
                    </div>
            </div>
            <hr>
            <br>
            <!--콘텐츠 페이지 내용 위치-->
            <div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">글번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">회원아이디</th>
                            <th scope="col">작성일</th>
                        </tr>
                    </thead>
                    <tbody>
	                    <c:forEach items="${pageData.pageList }" var="service" varStatus="i">
	                    	<tr>
	                    		<th scope="row">${service.serviceNo}</th>
	                    		<td><a href="/serviceSelect?serviceNo=${service.serviceNo}">${service.serviceCategory}</td>
	                    		<td>${service.userId}</td>
	                    		<td>${service.sRegdate }</td>
	                    	</tr>
	                    </c:forEach>
                    </tbody>
                </table>
                <br>
            </div>
        </div>
    </section>

    <!-- Footer-->
    <footer>
        <div class="text-center">
            <ul class="nav list-group-horizontal justify-content-center">
                <li class="nav-item"><a class="nav-link active" href="#" style="color: black;">이용약관</a></li>
                <li class="nav-item"><a class="nav-link active" href="#" style="color: black;">개인정보처리방침</a></li>
                <li class="nav-item"> <a class="nav-link active" href="#" style="color: black;">책임의 한계와 법적고지</a></li>
                <li class="nav-item"><a class="nav-link active" href="#" style="color: black;">회원정보 고객센터</a></li>
            </ul>
            <span class="align-middle">스펀딩 Copyright 스펀딩 corp.All Rights Reserved.</span>

        </div>
    </footer>


    <!-- Bootstrap core JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
    <!-- Third party plugin JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <!-- Contact form JS-->
    <!-- <script src="assets/mail/jqBootstrapValidation.js"></script>
    <script src="assets/mail/contact_me.js"></script>
    Core theme JS
    <script src="js/scripts.js"></script> -->
</body></html>