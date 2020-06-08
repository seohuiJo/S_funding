<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>마이페이지</title>
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/views/css/styles.css" rel="stylesheet" />
</head>

<body id="page-top">
	<!-- 상단바-->
	<jsp:include page="/common/header.jsp" />
	<br>
	<br>
	<!-- 메인 콘텐츠-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<!--메인 콘텐츠 위치-->
			<div class="row">
				<!--아이콘 위치-->
				<div class="col-2">
					<span class="h2"> <!--아이콘 코드 넣는곳--> <svg
							class="bi bi-person-plus-fill" width="1em" height="1em"
							viewBox="0 0 16 16" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
								d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z" />
                            <path fill-rule="evenodd"
								d="M13 7.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0v-2z" />
                        </svg>
					</span>
				</div>
				<!--제목 위치-->
				<div class="col-10">
					<span class="h2">마이페이지</span>
				</div>
			</div>
			<hr>
			<br>
			<!--콘텐츠 페이지 내용 위치-->
			<div>
				<form id="info">
					<table class="table table-borderless">
						<tr">
							<td style="width: 3rem;"><span class="h2"> <!--아이콘 코드 넣는곳--> <svg
										class="bi bi-person-lines-fill" width="1em" height="1em"
										viewBox="0 0 16 16" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd"
											d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7 1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm2 9a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z" />
                                    </svg>
							</span></td>
							<td>
								<h3>
									<a href="/views/member/memberModify.jsp">내 정보 보기 및 수정</a>
								</h3>

							</td>
						</tr>

						<tr>
							<td><span class="h2"> <!--아이콘 코드 넣는곳--> <svg
										class="bi bi-pencil-square" width="1em" height="1em"
										viewBox="0 0 16 16" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                        <path
											d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                        <path fill-rule="evenodd"
											d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                                    </svg>
							</span></td>
							<td>
								<h3>
									<a href="/supportList?userId=${member.userId }">나의 펀딩 리스트</a>
								</h3>
							</td>
						</tr>

						<tr>
							<td><span class="h2"> <!--아이콘 코드 넣는곳--> <svg
										class="bi bi-bell" width="1em" height="1em"
										viewBox="0 0 16 16" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                        <path
											d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2z" />
                                        <path fill-rule="evenodd"
											d="M8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
                                    </svg>
							</span></td>
							<td>
								<h3>
									<a href="/messageList">알림</a>
								</h3>
							</td>
						</tr>

						<tr>
							<c:if
								test="${sessionScope.member.userId != null && sessionScope.member.userId eq 'admin' }">
								<td><span class="h2"> <!--아이콘 코드 넣는곳--> <svg
											class="bi bi-people-fill" width="1em" height="1em"
											viewBox="0 0 16 16" fill="currentColor"
											xmlns="http://www.w3.org/2000/svg">
  									<path fill-rule="evenodd"
												d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5.784 6A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216zM4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z" />
										</svg>
								</span></td>
								<td>
									<h3>
										<a href="/memberList">회원 관리</a>
									</h3>
								</td>
						</tr>
						<tr>
							<td><span class="h2"> <!--아이콘 코드 넣는곳--> 
							<svg class="bi bi-emoji-frown" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  <path fill-rule="evenodd" d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
  <path d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
</svg>
							</span></td>
							<td>
								<h3>
									<a href="/blacklist">블랙리스트 관리</a>
								</h3>
							</td>
						</tr>
						</c:if>
						<tr>
							<td><span class="h2"> <!--아이콘 코드 넣는곳--> <svg
										class="bi bi-credit-card" width="1em" height="1em"
										viewBox="0 0 16 16" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd"
											d="M14 3H2a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1zM2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2z" />
                                        <rect width="3" height="3" x="2"
											y="9" rx="1" />
                                        <path d="M1 5h14v2H1z" />
                                    </svg>
							</span></td>
							<td>
								<h3>
 									<a href="/selectCharge?userId=${member.userId }">포인트 충전</a>
								</h3>
							</td>
						</tr>
						<tr>
							<td><span class="h2"> <!--아이콘 코드 넣는곳--> <svg
										class="bi bi-trash" width="1em" height="1em"
										viewBox="0 0 16 16" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                        <path
											d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                        <path fill-rule="evenodd"
											d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                    </svg>
							</span></td>
							<td>
								<h3>
									<a href="/mdelete">회원 탈퇴</a>
								</h3>
							</td>
						</tr>

					</table>
				</form>
				<br>
			</div>
		</div>
	</section>

	<!-- Footer-->
	<jsp:include page="/common/footer.html" />


	<!-- Bootstrap core JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	<!-- Third party plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<!-- Contact form JS-->
	<script src="assets/mail/jqBootstrapValidation.js"></script>
	<script src="assets/mail/contact_me.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>