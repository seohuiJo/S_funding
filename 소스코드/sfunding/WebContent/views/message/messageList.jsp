<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>알림 목록</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
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
                <!--콘텐츠 페이지 제목 타이틀-->
                <div class="row">
                    <!--아이콘-->
                    <div class="col-2 col-sm-2 col-md-1 col-xl-1">
                        <span class="h1">
                            <svg class="bi bi-bell-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
                            </svg>
                        </span>
                    </div>
                    <!--제목-->
                    <div class="col-3 col-sm-3 col-md-3 col-xl-3">
                        <span class="h1">알림</span>
                    </div>
                    <!-- 닉네임 출력 -->
                    <div class="col-7 col-sm-7 col-md-8 col-xl-8 d-flex justify-content-end">
                    	<span class="h5 text-muted"><span>${userId }</span>&nbsp;의 알림 목록</span>
                    </div>
                </div>
                <hr>
                <!--콘텐츠 페이지 내용-->
                <div>
                    <!--알림 내용 리스트 시작-->

                    <!--card 하나하나가 알림 내용(반복됨)-->
                    <c:forEach items="${pageData.pageList }" var="message">
                    <div class="card">
                        <div class="card-body">
                            <span class="row">
                                <!--알람내용-->
                                <span class="col-lg-9">
                                    <h5 class="card-title" id="sendFrom">From. 관리자</h5>
                                    <p class="card-text" id="messageContent">${message.messageContent }</p>
                                    <p class="card-text"><small class="text-muted" id="mRegDate">보낸날짜 : <span>${message.mRegdate }</span></small></p>
                                </span>
                                <!--삭제버튼-->
                                <span class="col-lg-3">
                                    <a href="/messageDelete?messageNo=${message.messageNo }" onclick="return question();" class="btn btn-secondary float-right">삭제하기</a>
                                </span>
                            </span>
                        </div>
                    </div>
                    <br>
                    </c:forEach>
                    <!--알림, card 반복 끝-->

                    <!--알림 내용 끝-->
                    <br> <br>
                    <!--페이징 처리, 페이지네이션-->
                    ${pageData.pageNavi }
                </div>
            </div>
        </section>
        
        <script>
			// 게시글을 삭제할 것인지 한번 확인 과정을 가지는 스크립트
			function question(){
				// confirm은 확인 창
				var check = window.confirm("해당 알림을 삭제하시겠습니까?");
				if(check){
					return true;
				} else{
					return false;
				}
			}
        </script>
        
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