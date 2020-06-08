<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>스펀딩-메인페이지</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

    <script src="js/scripts.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

	<script>
		// 문서 초기 로드 서블릿 불러오기
		$(document).ready(function(){
			// 베스트 창작자 불러오기
			$("#bestCreator").load("/bestCreator");
			// 베스트 후원자 불러오기
			$("#bestSupporter").load("/supportbest");
			// 최신 등록 프로젝트 불러오기
			$("#latestProject").load("/latestProject");
			// 로그인 유저의 관심 프로젝트 추천
			$("#recommendProject").load("/recommendProject");
		});

</script>
</head>

<body id="page-top">
    <!-- 상단바 Navigation-->
   <jsp:include page="common/header.jsp"/>
   <script>
        $('.carousel').carousel({ interval: 2000 }); 

    </script>
    <div id="demo" class="carousel slide" data-ride="carousel">
    	<div class="carousel-inner">
            <!-- 슬라이드 쇼 -->
            <div class="carousel-item active">
                <!--가로--> <img class="d-block w-100" src="/Main/5.png?auto=compress&cs=tinysrgb&h=650&w=940" alt="First slide">
                <div class="carousel-caption d-none d-md-block">
                    <h5>스 펀 딩</h5>
                    <p>새로운 펀딩의 시작</p>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="Main/3.png?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260" alt="Second slide"> </div>
            <div class="carousel-item"> 
                <img class="d-block w-100" src="Main/4.png?auto=compress&cs=tinysrgb&h=650&w=940" alt="Third slide"> </div> <!-- / 슬라이드 쇼 끝 -->
            <div class="carousel-item"> 
                <img class="d-block w-100" src="Main/2.png?auto=compress&cs=tinysrgb&h=650&w=940" alt="fourth slide"> </div> <!-- / 슬라이드 쇼 끝 -->
            <!-- 왼쪽 오른쪽 화살표 버튼 --> 
            <a class="carousel-control-prev" href="#demo" data-slide="prev"> 
                <span class="carousel-control-prev-icon" aria-hidden="true"></span> 
                <!-- <span>Previous</span> --> </a> 
            <a class="carousel-control-next" href="#demo" data-slide="next"> 
                <span class="carousel-control-next-icon" aria-hidden="true"></span> <!-- <span>Next</span> --> </a> <!-- / 화살표 버튼 끝 -->
            <!-- 인디케이터 -->
            <ul class="carousel-indicators">
                <li data-target="#demo" data-slide-to="0" class="active"></li>
                <!--0번부터시작-->
                <li data-target="#demo" data-slide-to="1"></li>
                <li data-target="#demo" data-slide-to="2"></li>
                <li data-target="#demo" data-slide-to="3"></li>
            </ul> <!-- 인디케이터 끝 -->
        </div>

        <!-- 최신 등록 프로젝트 영역-->
        <section class="page-section bg-light" id="portfolio">
            <div id="latestProject"></div>
        </section>
        
        
        <!-- 추천 프로젝트로드할 영역 -->
        <section class="page-section bg-light" id="portfolio">
        	<div id="recommendProject"></div>
        </section>
        <!-- 추천 프로젝트 끝 -->
        
        <!-- 베스트 창작자 컨텐츠 -->
        <section>
        <br>
        	<div class="container">
        		<div class="row">
        			<!-- 창작자 -->
        			<div class="col-md-6">
        				<!--아이콘-->
      		            <div class="">
                        	<span class="h2 col-2">
         	                   <!--아이콘 코드 넣는곳-->
         	                   <svg class="bi bi-star" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
         	                   <path fill-rule="evenodd" d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.523-3.356c.329-.314.158-.888-.283-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767l-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288l1.847-3.658 1.846 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.564.564 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
         	                   </svg>
                        	</span>
                        	<!--제목-->
                        	<span class="col-10" style="font-size:1.5em;">베스트 창작자 !</span>
                    	</div>
        				<br>
			        	<!-- 베스트 창작자 테이블 -->
		   				<div id ="bestCreator"></div>
        			</div>
        			<br>
        			<!-- 후원자 -->
        			<div class="col-md-6">
        				<!--아이콘-->
      		            <div class="">
                        	<span class="h2 col-2">
         	                   <!--아이콘 코드 넣는곳-->
         	                   <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
           	                 		<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                          		</svg>
                        	</span>
	                   		<!--제목-->
                        	<span class="col-10" style="font-size:1.5em;">베스트 후원자 !</span>
                    	</div>
                    	<br>
   						<div id ="bestSupporter"></div>
        			</div>
        			</div><!-- row 끝 -->
        		</div><!-- container 끝 -->
        </section>
        
        <hr>
        <!-- Footer 푸터, 하단바 -->
  		<jsp:include page="/common/footer.html"/>
    	</div>
        <!-- Bootstrap core JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="assets/mail/jqBootstrapValidation.js"></script>
        <script src="assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
</body>

</html>
