<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>나의 펀딩 리스트</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- 상단바-->
		<!-- Navigation-->
		<jsp:include page="../../common/header.jsp" />

        <br><br>
        <!-- 메인 콘텐츠-->
        <section class="page-section bg-light" id="portfolio">
            <div class="container">
            <!--메인 콘텐츠 위치-->
                <div class="row">
                    <!--아이콘-->
                    <div class="col-2 col-sm-2 col-md-2 col-lg-1">
                        <span class="h1">
                            <!--아이콘 코드 넣는곳-->
                            <svg class="bi bi-cloud-fill" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M3.5 13a3.5 3.5 0 1 1 .59-6.95 5.002 5.002 0 1 1 9.804 1.98A2.5 2.5 0 0 1 13.5 13h-10z" />
                            </svg>
                        </span>
                    </div>
                    <!--제목-->
                    <div class="col-10 col-sm-10 col-md-10 col-lg-11">
                        <span class="h1">나의 펀딩 리스트</span>
                    </div>
                </div>
                <hr>
                <!--콘텐츠 페이지 내용-->
                <div>
                    <!--media object 테스트-->
                    <c:forEach items="${SupportPageData.pageList }" var="list" >
                    
                    <div class="media" style="background-color:white;border: 1px solid lightgray;border-radius: 8px/8px;">
                        <div class="media-body">
                            <br>
                            <h4 class="mt-0" style="margin-left:30px;">${list.projectContent}</h4>
                           <p class="mb-0" style="margin:20px;">프로젝트 창작자 ID : &nbsp;<span>${list.selluserId}</span>
                      </p>
                           <p class="mb-0" style="margin:20px;">펀딩시작일 : &nbsp;<span>${list.startDate}</span></p>
                            <p class="mb-0" style="margin:20px;">펀딩마감일 : &nbsp;<span>${list.endDate}</span></p>
                           <%--  <p class="mb-0" style="margin:20px;">남은 시간 : ${supportList.startDate -supportList.endDate}</p> --%>
                            <p class="mb-0" style="margin:20px;">내가 후원한 금액: &nbsp;<span>${list.price } </span></p> 
                            <p class="mb-0" style="margin:20px;">펀딩완료까지 남은 금액 : &nbsp;<span>${list.targetMoney-list.sumMoney }</span> </p>  
                             <br>
                        </div>
                  </div>
                  <br>
                  </c:forEach>
                    <!--media object 영역 끝, 반복-->
                    <hr>
                    <!-- 페이징 처리 -->
					${SupportPageData.pageNavi }
                </div>
            </div>
        </section>
        
        <!-- Footer 푸터, 하단바 -->
		<jsp:include page="../../common/footer.html" />

        
        
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