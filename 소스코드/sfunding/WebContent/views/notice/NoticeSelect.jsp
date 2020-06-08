<%@page import="notice.model.vo.Notice" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>공지사항 내용</title>
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
	<jsp:include page="/common/header.jsp" />
    <br><br>
    <!-- 메인 콘텐츠-->
    <section class="page-section bg-light" id="portfolio">
        <div class="container">
            <!--메인 콘텐츠 위치-->
            <div class="row">
                <!--아이콘 위치-->
                <div class="col-1 col-sm-1 col-md-1">
                    <span class="h2">
                        <!--아이콘 코드 넣는곳-->
                        <svg class="bi bi-card-list" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z" />
                            <path fill-rule="evenodd" d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5z" />
                            <circle cx="3.5" cy="5.5" r=".5" /> <circle cx="3.5" cy="8" r=".5" /> <circle cx="3.5" cy="10.5" r=".5" />
                        </svg>
                    </span>
                </div>
                <!--제목 위치-->
                <div class="col-11 col-sm-11 col-md-5 col-lg-5">
                    <span class="h2">공지사항</span>
                </div>
                
                <!--버튼, 삭제/목록-->
				<div class="col-md-6 col-lg-6 d-flex justify-content-end">
					<!--삭제버튼은 로그인된 유저와 작성자 아이디가 같을때만 보이도록 한다.-->
					<span>
						<c:if test="${sessionScope.member != null && sessionScope.member.userId eq'admin' }">
                 	    	<a href="/noticeModify?noticeNo=${content.noticeNo }" class="btn btn-primary">수정하기</a>&nbsp;&nbsp;
                  	    	<a href="/noticeDelete?noticeNo=${content.noticeNo}" class="btn btn-secondary"onclick="return question();">삭제하기</a>&nbsp;&nbsp;
                   	       	<input type="hidden" name="noticeNo" value="${content.noticeNo }">
                   	    </c:if>
             			<a href="/NoticeServlet" class="btn btn-primary">목록으로</a>
					</span>
				</div>
            </div>
            <hr>
            <br>
            <!--콘텐츠 페이지 내용 위치-->
            
            <!--공지사항 글 내용-->
			<div>
				<div class="card">
					<div class="card-body">
						<!-- 공지사항 글 제목 -->
						<h4 class="card-title">${content.noticeTitle }</h4>
						<hr>
						<!-- 공지사항 글 내용 -->
						<pre class="card-text" style="white-space:pre-wrap;">${content.noticeContent }</pre>
						<br>
						<hr>
						<!-- 공지사항 글 작성자 -->
						<p>
							<span id="userId" class="font-weight-bold" style="margin:20px;">${content.userName }</span>
							<!-- 공지사항 글 작성일 -->
							<span class="float-right"><span id="rRegDate">${content.nRegdate }</span></span>
						</p>
					</div>
				</div>
			</div>
        </div><!-- container 끝 -->
    </section>

    <!-- Footer 푸터, 하단바 -->
	<jsp:include page="/common/footer.html" />
	
    <script>
		function question() {
			var result = window.confirm("정말로 삭제 하시겠습니까?");

			if (result) {
				return true;
			} else {
				return false;
			}

		}
	</script>


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