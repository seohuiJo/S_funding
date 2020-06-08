<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>포인트 충전</title>
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"
	crossorigin="anonymous"></script>
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
<link href="/css/styles.css" rel="stylesheet" />
<!--javascript/jQuery-->
<script>
	// point 충전을 진행할 것인지 alert로 확인하는 함수
	function checkPoint() {
		var check = window.confirm("포인트 충전을 진행하시겠습니까?");
		if (check) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body id="page-top">
	<!-- 상단바-->
	<jsp:include page="/common/header.jsp" />
	<br>
	<br>
	<!-- 메인 콘텐츠-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<div class="row">
				<!--메인 콘텐츠 위치-->
				<div class="col-lg">
					<div class="row">
						<!--아이콘-->
						<div class="col-2 col-sm-2 col-md-2 col-lg-1">
							<span class="h1"> <!--아이콘 코드 넣는곳--> <svg
									class="bi bi-credit-card" width="1.5em" height="1.5em"
									viewBox="0 0 16 16" fill="currentColor"
									xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd"
										d="M14 3H2a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1zM2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2z" />
                                        <rect width="3" height="3" x="2"
										y="9" rx="1" />
                                        <path d="M1 5h14v2H1z" />
                                    </svg>
							</span>
						</div>
						<!--제목-->
						<div class="col-10 col-sm-10 col-md-10 col-lg-11">
							<span class="h1">포인트 충전</span>
						</div>
					</div>
					<hr>
					<br>
					<!--콘텐츠 페이지 내용-->
					<div>
						<form action="/pointCharge" method="post">
							<div class="form-group row">
								<label for="point" class="col-sm-2 col-form-label">현재포인트</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" value="${member.point }"readonly>
								</div>
							</div>


							<div class="form-group row">
								<label for="userId" class="col-sm-2 col-form-label">충전
									대상 아이디</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="userId"
										name="userId" value="${member.userId }">
								</div>
							</div>
							<div class="form-group row">
								<label for="point" class="col-sm-2 col-form-label">충전금액</label>
								<div class="col-sm-10">
									<!--select option-->
									<select class="form-control" name="point">
										<option value="10000">10,000</option>
										<option value="30000">30,000</option>
										<option value="50000">50,000</option>
									</select>
								</div>
							</div>
							<br>
							<div class="form-group row justify-content-center">
								<input type="submit" class="btn btn-primary btn-lg"
									onclick="return checkPoint();" value="포인트 충전하기">
							</div>
						</form>
						<br>
					</div>
				</div>
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