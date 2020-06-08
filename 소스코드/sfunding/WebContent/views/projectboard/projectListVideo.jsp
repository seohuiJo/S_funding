<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>펀딩 프로젝트 목록 - 영상</title>
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
    
    <script src="/js/scripts.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>

<body id="page-top">
    <!-- Navigation-->
    <jsp:include page="/common/header.jsp" />
    <br><br>
    <!-- Masthead-->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<!-- 콘텐츠 -->
    <section class="page-section bg-light" id="select">
        <div class="container">
        	<div class="row">
        	<div class="col-6">
            <div class="dropdown categorypage">
                <button class="btn btn btn-warning dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    카테고리
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                	<a class="dropdown-item" href="/project">전체</a>
                    <a class="dropdown-item" href="/projectart">예술</a>
                    <a class="dropdown-item" href="/projectpublishing">출판</a>
                    <a class="dropdown-item" href="/projectgame">게임</a>
                    <a class="dropdown-item" href="/projectother">기타</a>
                </div>
            </div>
            </div>
            <!-- 검색 폼 -->
            <div class="col-6 d-flex justify-content-end">
                <form method="get" action="/projectSearch">
                		<label for="search" class="submit">
                			<svg class="bi bi-search" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            	<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
 	                        	<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
        	                </svg>
						</label> 
						<input type="text" name="search" id="search" size="20" placeholder="검색어를 입력해주세요." style="border: 2px solid orange;">
						<button type="submit" id="submit" class="btn btn-warning">검색하기</button>
				</form>
            </div>
            </div>
        </div>
    </section>

    <!-- Portfolio Grid-->
    <section class="page-section bg-light" id="portfolio">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase" style="text-align: left;">영화 스펀딩</h2>
            </div>
            <div class="row">
             <c:forEach items="${pageData.pageList }" var="project" varStatus="i">
             	<div class="col-lg-3 col-sm-6 mb-4">
                    <div class="portfolio-item">
                    	<!-- 이미지 -->
                        <a href="/projectSelect?projectNo=${project.projectNo }"><img class="img-fluid" src="/upload/photo/${project.image.filePath}" alt="" style="weight:300px;height:300px;" /></a>
                        <div class="portfolio-caption" style="height:250px;">
                            <div class="portfolio-caption-heading" style="text-align: left;"><a href="/projectSelect?projectNo=${project.projectNo }" style="color:black;text-decoration:none;">${project.projectTitle}</a></div><br>
                            <div class="portfolio-caption-subheading text-muted" style="text-align: left;">${project.userId}</div><br>
                            <div class="progress progress-bar" style="width:${project.rate}%; height: 12px;">${project.rate}</div><br>
                            <div>
                                <span style="float: left;">
                                    <span><svg class="bi bi-calendar3" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zM1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857V3.857z" />
                                            <path fill-rule="evenodd" d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                                        </svg></span>
                                    <span>${project.endDate }</span>
                                </span>
                                <br>
                                <span style="float: right;">
                                    <span>${project.sumMoney }원</span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
             </c:forEach>
             
            </div>
        </div>
        <br>
        <!-- 페이징 -->
        ${pageData.pageNavi }
    </section>
    
    <!-- Footer 푸터, 하단바 -->
	<jsp:include page="/common/footer.html" />
    
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


 