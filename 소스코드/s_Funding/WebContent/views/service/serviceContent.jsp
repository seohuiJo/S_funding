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
    <!-- Core theme CSS (includes Bootstrap)-->
    <!-- <link href="css/styles.css" rel="stylesheet" /> -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>

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
                        <svg class="bi bi-card-list" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z" />
                            <path fill-rule="evenodd" d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5z" />
                            <circle cx="3.5" cy="5.5" r=".5" />
                            <circle cx="3.5" cy="8" r=".5" />
                            <circle cx="3.5" cy="10.5" r=".5" />
                        </svg>
                    </span>
                </div>
                <!--제목 위치-->
                <div class="col-11">
                    <span class="h2">건의사항 글 내용(사용자)</span>
                </div>
            </div>
            <hr>
            <br>
            <!--콘텐츠 페이지 내용 위치-->
            <div>
                <div class="card">

                    <div class="card-body">

                        <div class="row">
                            <div class="col-10">
                                <h4 class="card-title">${ service.serviceCategory}</h4>
                            </div>
                            <div class="col-2">
                                <a href="#" class="btn btn-secondary">목록으로</a>
                            </div>


                        </div>
                        <br>


                        <p class="card-text">${service.serviceContent }</p>
                        <div class="row">
                            <div class="col-9">
                            </div>
                            <div class="col-3">
                                <a href="#" class="btn btn-secondary">글 수정</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" class="btn btn-primary">글 삭제</a>
                            </div>

                        </div>
                        <br>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">내용</th>
                                    <th scope="col">작성자</th>
                                    <th scope="col">등록일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>관리자 댓글 작성 부분입니다.</td>
                                    <td>관리자</td>
                                    <td>20xx.xx.xx</td>
                                </tr>


                            </tbody>
                        </table>
                    </div>
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
<!--     <script src="assets/mail/jqBootstrapValidation.js"></script>
    <script src="assets/mail/contact_me.js"></script> -->
    <!-- Core theme JS-->
<!--     <script src="js/scripts.js"></script> -->
</body></html>