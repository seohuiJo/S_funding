<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>회원 정보 수정</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/css/styles.css" rel="stylesheet" />
</head>

<body id="page-top">
    <!-- 상단바-->
    <jsp:include page="/common/header.jsp"/>
    <br><br>
    <!-- 메인 콘텐츠-->
    <section class="page-section bg-light" id="portfolio">
        <div class="container">
            <!--메인 콘텐츠 위치-->
            <div class="row">
                <!--아이콘 위치-->
                <div class="col-2">
                    <span class="h2">
                        <!--아이콘 코드 넣는곳-->
                      <svg class="bi bi-person-lines-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7 1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm2 9a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z" />
                                    </svg>
                    </span>
                </div>
                <!--제목 위치-->
                <div class="col-10">
                    <span class="h2">회원정보수정</span>
                </div>
            </div>
            <hr>
            <br>
            <!--콘텐츠 페이지 내용 위치-->
            <div class="d-flex justify-content-right">
                <form action="/mUpdate" method="post">
                        <table>
                            <tr>
                                <td>아이디</td>
                                <td><input type="text" class="form-control" id="userId" name="userId" value="${sessionScope.member.userId }" size="30" placeholder="아이디를 입력하세요" style="margin:10px" readonly="readonly">
                                </td>
                            </tr>
                            <tr>
                                <td>비밀번호</td>
                                <td><input type="password" class="form-control" id="userPwd" name="userPwd" size="30" value= "${sessionScope.member.userPwd}"placeholder="비밀번호를 입력하세요" style="margin:10px">
                                </td>
                            </tr>
                            <tr>
                                <td>비밀번호확인</td>
                                <td><input type="password" class="form-control" id="userPwdRe" name="userPwdRe" size="30" value= "${sessionScope.member.userPwd}" placeholder="비밀번호 다시입력헤주세요" style="margin:10px">
                                </td>
                            </tr>
                             <tr>
                                <td>닉네임</td>
                                <td><input type="text" class="form-control" id="nickname" name="nickname" value= "${sessionScope.member.nickname}" size="30" placeholder="이름을 입력하세요" style="margin:10px">
                                </td>
                            </tr>
                            <tr>
                                <td>이름</td>
                                <td><input type="text" class="form-control" id="userName" name="userName" value= "${sessionScope.member.userName}" size="30" placeholder="닉네임을 입력하세요" style="margin:10px" readonly="readonly">
                                </td>
                            </tr>
                            <tr>
                                <td>휴대폰</td>
                                <td><input type="text" class="form-control" id="phone" name="phone" value= "${sessionScope.member.phone}" size="30" placeholder="휴대폰 번호를 입력하세요" style="margin:10px">
                                </td>
                            </tr>
                            <tr>
                                <td>이메일</td>
                                <td><input type="text" class="form-control" id="email" name="email" size="30" value= "${sessionScope.member.email}"placeholder="email을 입력하세요" style="margin:10px">
                                </td>
                            </tr>
                            <tr>
                                <td>주소</td>
                                <td><input type="text" class="form-control" id="address" name="address" value= "${sessionScope.member.address}" size="30" placeholder="주소를 입력하세요" style="margin:10px">
                                </td>

                            <tr>
                                <td>관심분야</td>
                                <td><input type="text" class="form-control" id="interest" name="interest" value= "${sessionScope.member.interest}" size="30" placeholder="관심분야를 입력하세요" style="margin:10px" readonly="readonly">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button type="submit" class="btn btn-primary">
                                        수정하기
                                    </button>
                                </td>
                                <td>
                                    <button type="reset" class="btn btn-secondary">
                                        취소하기
                                    </button>
                                </td>
                            </tr>

                        </table>
                    </form>
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
    <script src="assets/mail/jqBootstrapValidation.js"></script>
    <script src="assets/mail/contact_me.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
</body></html>