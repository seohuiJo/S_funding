<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="구현정" />
        <title>펀딩 요청 글 작성</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>	
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- 상단바 Navigation-->
   		<jsp:include page="/common/header.jsp"/>
   		
        <br><br>
        <!-- 메인 콘텐츠-->
        <section class="page-section bg-light" id="portfolio">
            <div class="container">
            <!--메인 콘텐츠 위치-->
                <div class="row">
                    <!--아이콘-->
                    <div class="col-2">
                        <span class="h2">
                            <!--아이콘 코드 넣는곳-->
                            <svg class="bi bi-pencil-square" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/><path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                        </span>
                    </div>
                    <!--제목-->
                    <div class="col-10">
                        <span class="h2">펀딩 요청 글 작성</span>
                    </div>
                </div>
                <hr>
                <br>
                <!--콘텐츠 페이지 내용-->
                <div>
                    <form action="/RequestWriteServlet" method="post">
                        <div class="form-group row">
                            <label for="requestTitle" class="col-sm-2 col-form-label">제목</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="requestTitle" name="requestTitle" placeholder="제목을 입력하세요.">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="requestContent" class="col-sm-2 col-form-label">내용</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="requestContent" name="requestContent" rows="18" placeholder="요청하실 내용을 입력해주세요."></textarea>
                            </div>
                        </div>
                        <br>
                        <div class="form-group row justify-content-center">
                            <button class="btn btn-primary" onclick="return check_input();">등록하기</button>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/RequestServlet" class="btn btn-secondary">목록으로</a>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        
		<script>
 			// 모든 입력사항을 입력했는지 확인하는 스크립트
			function check_input(){
				var title = $("#requestTitle").val()
				var content = $("#requestContent").val();
				if(title !="" && content !=""){
					checkRequestWrite();
				} else{
					alert("제목과 내용을 모두 입력해주세요.");
					return false;
				}
			}
 			
            // 작성한 글을 등록할 것인지 묻는 함수
            function checkRequestWrite(){
                var check = window.confirm("작성하신 펀딩 요청글을 등록하시겠습니까?");
                if (check) {
                    return true;
                } else {
                    return false;
                }
            }
 		</script>        
        
        
        <!-- Footer 푸터, 하단바 -->
  		<jsp:include page="/common/footer.html"/>
        
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
