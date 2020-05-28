<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Member member = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if (member != null) {
	%>
	[<%=member.getUserName()%>] 님 환영합니다.<br>
	<a href="/logout">로그아웃</a>
	<a href ="/views/member/MemberMyInfo.jsp?userId=<%= member.getUserId() %>">마이페이지</a><br>
	<br>
	<a href="/views/file/upload.html">업로드</a>
	<a href="/fileList">다운로드</a>
	<br>
	<% if (member.getUserId().equals("admin")) {%>
	<a href="/memberList">전체 회원 조회</a>
	<%} %>
	<a href="/notice">공지사항</a>
	<a href="/photoList">사진게시판</a>
	<%
		}
	%>
	<%
		if (member == null) {
	%>
	<h1>로그인 페이지</h1>
	<form action="/Login" method="post">
		ID : <input type="text" name="userId" id="userId"><br> 
		PW : <input type="password" name="userPwd" id="userPwd"><br>
		<input type="submit" value="로그인"> 
		<input type="reset"value="취소"> 
		<a href="/views/member/enroll.html">회원가입</a> 
		<a href="/notice">공지사항</a>
		<%
			}
		%>
	</form>

</body>
</html>