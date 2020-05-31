<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>템플릿</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- "C:/Users/cosmo/OneDrive/문서/카카오톡 받은 파일/Service/Service/ServiceService.java"Core theme CSS (includes Bootstrap)-->
    <link href="/views/service/css/styles.css" rel="stylesheet" />
</head>

<script type="text/javascript" src="SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	
	function fnUpdate(serviceNo, serviceCategory) {
		
		<%-- var serviceNo = <%=serviceNo%>; --%>
		var serviceContent = document.getElementById("serviceContent").value;
		<%-- var serviceCategory = '<%=serviceCategory%>'; --%>
		var serviceCategory=document.getElementById("serviceCategory").value;
		
		location.href="/serviceModify?serviceNo="+serviceNo+"&serviceContent="+serviceContent+"&serviceCategory="+serviceCategory;

	}

</script>

<body id="page-top">
    <!-- 상단바-->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color:black;">
        <div class="container">
            <a class="navbar-brand js-scroll-trigger" href="#page-top">
                <img src="assets/logo1.svg" alt="" />스펀딩</a><button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">Menu<i class="fas fa-bars ml-1"></i></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav text-uppercase ml-auto">
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#services">프로젝트 보기</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#portfolio">프로젝트 하기</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#about">요청게시판</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#team">건의사항</a></li>
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
                <div class="col-11">
                    <span class="h2">고객센터 글 수정</span>
                </div>
            </div>
            <hr>
            <br>
            <!--콘텐츠 페이지 내용 위치-->
            <div>
            	<form action="/serviceModify" method="post">
                	<div class="select" width="200px">
	                	<select class="custom-select mr-sm-2" id="serviceCategory" style="width: 110px; height: 35px;" name="serviceCategory">
	                    	<option value="SUGGEST" <c:if test=="${serviceCategory=='SUGGEST'}" selected></c:if>> 건의사항</option>
	                        <option value="REPORT" <c:if test="${serviceCategory=='REPORT'}" selected> </c:if>>신고</option>
	                        <option value="QUESTION" <c:if test="${serviceCategory=='QUESTION'}" selected></c:if>>질문하기</option>
	                    	<!-- <option value="SUGGEST">건의사항</option>
	                        <option value="REPORT">신고</option>
	                        <option value="QUESTION">질문하기</option> -->
						</select>
                   	</div><br>
                    <div class="form-group">
                        <textarea class="form-control" id="serviceContent" rows="15" name="D" >${serviceContent}</textarea>
                    </div>

                	<div class="row"></div>
                    <div class="col-5"></div>
                    <div class="col-5">
                        <!-- <a href="/serviceModify?serviceNo=${serviceNo}&serviceContent=${serviceContent}&serviceCategory=${serviceCategory}" class="btn btn-primary" type="submit">글 수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
                        <input type="button" class="btn btn-primary" onclick="fnUpdate(${serviceNo},'${serviceCategory}');" value="글 수정">
                        <input type="button" class="btn btn-secondary" onclick="fnDelete();" value="글 삭제">
                        <!-- id값으로 textarea의 val값을 가져와야함 -->
                    </div>
               
				</form>

            </div>
            <br>
        </div>
    </section>

    <!-- Footer-->
    <footer>
        <div class="text-center">
            <ul class="nav list-group-horizontal justify-content-center">
                <li class="nav-item"><a class="nav-link active" href="#" style="color: black;">이용약관</a></li>
                <li class="nav-item"><a class="nav-link active" href="#" style="color: black;">개인정보처리방침</a></li>
                <li class="nav-item"><a class="nav-link active" href="#" style="color: black;">책임의 한계와 법적고지</a></li>
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
    <script src="assets/mail/jqBootstrapValidation.js"></script>
    <script src="assets/mail/contact_me.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
</body></html>