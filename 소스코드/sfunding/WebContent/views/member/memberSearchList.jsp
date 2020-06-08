<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>회원 검색 결과</title>
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<style>
.pagination {
	text-align: center;
	width: 300px;
	margin-left: auto;
	margin-right: auto;
}
</style>
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
				<div class="col-1">
					<span class="h2"> <!--아이콘 코드 넣는곳--> <svg class="bi bi-gear"
							width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
								d="M8.837 1.626c-.246-.835-1.428-.835-1.674 0l-.094.319A1.873 1.873 0 0 1 4.377 3.06l-.292-.16c-.764-.415-1.6.42-1.184 1.185l.159.292a1.873 1.873 0 0 1-1.115 2.692l-.319.094c-.835.246-.835 1.428 0 1.674l.319.094a1.873 1.873 0 0 1 1.115 2.693l-.16.291c-.415.764.42 1.6 1.185 1.184l.292-.159a1.873 1.873 0 0 1 2.692 1.116l.094.318c.246.835 1.428.835 1.674 0l.094-.319a1.873 1.873 0 0 1 2.693-1.115l.291.16c.764.415 1.6-.42 1.184-1.185l-.159-.291a1.873 1.873 0 0 1 1.116-2.693l.318-.094c.835-.246.835-1.428 0-1.674l-.319-.094a1.873 1.873 0 0 1-1.115-2.692l.16-.292c.415-.764-.42-1.6-1.185-1.184l-.291.159A1.873 1.873 0 0 1 8.93 1.945l-.094-.319zm-2.633-.283c.527-1.79 3.065-1.79 3.592 0l.094.319a.873.873 0 0 0 1.255.52l.292-.16c1.64-.892 3.434.901 2.54 2.541l-.159.292a.873.873 0 0 0 .52 1.255l.319.094c1.79.527 1.79 3.065 0 3.592l-.319.094a.873.873 0 0 0-.52 1.255l.16.292c.893 1.64-.902 3.434-2.541 2.54l-.292-.159a.873.873 0 0 0-1.255.52l-.094.319c-.527 1.79-3.065 1.79-3.592 0l-.094-.319a.873.873 0 0 0-1.255-.52l-.292.16c-1.64.893-3.433-.902-2.54-2.541l.159-.292a.873.873 0 0 0-.52-1.255l-.319-.094c-1.79-.527-1.79-3.065 0-3.592l.319-.094a.873.873 0 0 0 .52-1.255l-.16-.292c-.892-1.64.902-3.433 2.541-2.54l.292.159a.873.873 0 0 0 1.255-.52l.094-.319z" />
                            <path fill-rule="evenodd"
								d="M8 5.754a2.246 2.246 0 1 0 0 4.492 2.246 2.246 0 0 0 0-4.492zM4.754 8a3.246 3.246 0 1 1 6.492 0 3.246 3.246 0 0 1-6.492 0z" />
                        </svg>
					</span>
				</div>
				<!--제목 위치-->
				<div class="col-11">
					<span class="h2">회원 관리</span>
				</div>
			</div>
			<hr>
			<br>
			<!--콘텐츠 페이지 내용 위치-->
			<div>
				<form action="/searchMemberId" method="get">
					<div class="input-group mb-3">
						<div class="col-4">
							<input type="text" class="form-control" name="key"
								placeholder="회원아이디검색">
						</div>
						<div class="input-group-append">
							<input class="btn btn-outline-secondary" type="submit"
								value="검색">
						</div>
					</div>
				</form>


				<div class="col-6"></div>
				</div>
				<table class="table table-bordered">
					<thead class="thead-light">
						<tr>
							<th scope="col">번호</th>
							<th scope="col">아이디</th>
							<th scope="col">비밀번호</th>
							<th scope="col">이름</th>
							<th scope="col">휴대폰</th>
							<th scope="col">닉네임</th>
							<th scope="col">주소</th>
							<th scope="col">이메일</th>
							<th scope="col">포인트</th>
							<th scope="col">관심분야</th>
							<th scope="col" class="text-center">가입날짜</th>
							<th scope="col" class="text-center">계정여부</th>
							<th scope="col" class="text-center">블랙리스트</th>
						</tr>
					</thead>
					<tbody style="font-size: 10px">

						<c:forEach items="${MemberPageData.pageList }" var="mOne" varStatus="i">

							<tr>
								<th scope="row">${mOne.userNo }</th>
								<th>${mOne.userId }</th>
								<td>${mOne.userPwd }</td>
								<td>${mOne.userName }</td>
								<td>${mOne.phone }</td>
								<td>${mOne.nickname }</td>
								<td>${mOne.address }</td>
								<td>${mOne.email }</td>
								<td>${mOne.point }</td>
								<td>${mOne.interest }</td>
								<td class="text-center">${mOne.uRegdate }</td>
								<td class="text-center">${mOne.enabled }</td>
								<td class="text-center">
									<form action="/addBlackList" id="target" method="post">
										<input type="hidden" name="userId" id="userId"> <input
											type="hidden" name="reason" id="reason">
										<button type="button" class="btn btn-primary"
											onclick="return inputReason(this);">추가</button>
									</form>
								</td>

							</tr>

						</c:forEach>


					</tbody>
				</table>

				<br>
				<!-- 페이징 -->
				${MemberPageData.pageNavi }
				<script>
					function inputReason(obj) {
						console.log($(obj));
						var userId = $(obj).parent().parent().siblings().eq(0)
								.text();
						var reason = prompt("이유를 입력해주세요 : " + "");
						alert("블랙리스트에 추가되었습니다.");
						$("#userId").val(userId);
						$("#reason").val(reason);
						$("#target").submit();

					};
				</script>
			</div>
		</section>


	<!-- Footer 푸터, 하단바 -->
	<jsp:include page="../../common/footer.html"/>


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