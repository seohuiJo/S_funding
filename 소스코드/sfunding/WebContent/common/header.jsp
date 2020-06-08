<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav"
		style="background-color: black;">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="/index.jsp">스펀딩</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu<i class="fas fa-bars ml-1"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/project">프로젝트 보기</a></li>
						
					<li class="nav-item">
					<a class="nav-link js-scroll-trigger"
						href="/views/projectboard/projectWrite.jsp" onclick="return check_login();">프로젝트 하기</a></li>
						
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/RequestServlet">요청게시판</a></li>
						
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/service">고객센터</a></li>
						
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/NoticeServlet">공지사항</a></li>
						
					<c:if test="${sessionScope.member.userId != null}">
						<li class="nav-item"><a class="nav-link js-scroll-trigger" href="/myinfo?userId=${member.userId }">마이페이지</a></li>
						<li class="nav-item"><a class="nav-link js-scroll-trigger"
							href="/logout">로그아웃</a></li>
					</c:if>
					<c:if test="${sessionScope.member.userId == null}">
						<li class="nav-item"><a class="nav-link js-scroll-trigger"
							href="/login.jsp">로그인</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	
	<script>
		// 로그인 했는지 확인
		// 로그인 안했으면 프로젝트 하기 페이지 못감
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

</body>
</html>