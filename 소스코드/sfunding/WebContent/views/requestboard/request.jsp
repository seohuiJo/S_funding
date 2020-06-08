
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="requestboard.model.vo.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
        <title>펀딩 요청 글 목록</title>
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
   		<jsp:include page="../../common/header.jsp"/> 
   		
        <br><br>
        <!-- 메인 콘텐츠-->
        <section class="page-section bg-light" id="portfolio">
            <div class="container">
            <!--메인 콘텐츠 위치-->
                <div class="row">
                    <!--아이콘-->
                    <div class="col-1 col-sm-1 col-md-1 col-xl-1">
                        <span class="h2">
                            <!--아이콘 코드 넣는곳-->
                            <svg class="bi bi-list-ul" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                            </svg>
                        </span>
                    </div>
                    <!--제목-->
                    <div class="col-11 col-sm-11 col-md-5 col-xl-5">
                        <span class="h2">펀딩 요청 글 목록</span>
                    </div>
                    <!-- 글쓰기 버튼 -->
					<div class="col-md-6 col-xl-6 d-flex justify-content-end">
						<form action="/views/requestboard/requestWrite.jsp" method="post">
							<button type="submit" class="btn btn-primary" onclick="return check_login();">요청 글쓰기</button>
						</form>
					</div>
				</div>
                <hr>
                <!--콘텐츠 페이지 내용-->
                <div>
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col" class="text-center" style="width: 5rem;">글번호</th>
                                <th scope="col" class="text-center">제목</th>
                                <th scope="col" style="width: 10rem;">작성자</th>
                                <th scope="col" class="text-center" style="width: 10rem;">작성일</th>
                                <th scope="col" class="text-center" style="width: 5rem;">공감</th>
                                <th scope="col" class="text-center" style="width: 5rem;">비공감</th>
                                <th scope="col" class="text-center" style="width: 10rem;">진행중인 프로젝트</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--반복되는 테이블 데이터 출력부분-->
						<c:forEach items="${PageData.pageList }" var="Request" varStatus="i">
							<tr>
								<th scope="row" class="text-center">${Request.requestNo }</th>
								<td><a href="/requestSelect?requestNo=${Request.requestNo}" style="color:black;">${Request.requestTitle}</a></td>
								<td>${Request.userId}</td>
								<td class="text-center">${Request.rRegdate}</td>
								<td class="text-center">${Request.good}</td>
								<td class="text-center">${Request.bad}</td>
								<td>${Request.projectList}</td>
							</tr>
						</c:forEach>
                        </tbody>
                    </table>
               		<br>
               		 <!--페이징 처리/페이지네이션 시작-->
					${PageData.pageNavi }
                </div>
                <!--검색 입력 폼-->
                <div class="row d-flex justify-content-end">
                	<form method="get" action="/RequestSearchServlet">
                		<label for="search" class="submit">
                			<svg class="bi bi-search" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            	<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
 	                           <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
        	                </svg>
							</label> <input type="text" name="search" id="search" size="20"
								placeholder="검색어를 입력해주세요." style="border: 2px solid orange;">
							<button type="submit" id="submit" class="btn btn-primary">검색하기</button>
					</form>
                </div>
                <!--메인 콘텐츠 끝-->
            </div>
        </section>
        
        <script>
			// 로그인 했는지 확인
			function check_login(){
				var checkLogin = '${sessionScope.member.userId}';
				console.log(checkLogin);
				if(checkLogin ==""){
					alert("로그인 후에 이용가능한 서비스입니다.");
					return false;
				} else{
					return true;
				}
			}
		</script>
        
        <!-- Footer 푸터, 하단바 -->
  		<jsp:include page="../../common/footer.html"/>
  		
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