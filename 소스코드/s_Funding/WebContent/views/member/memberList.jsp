<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체 회원 정보</title>
</head>
<body>
	<h1>관리자 : 회원관리 페이지</h1>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>닉네임</th>
			<th>주소</th>
			<th>이메일</th>
			<th>포인트</th>
			<th>계정사용가능여부</th>
			<th>가입날짜</th>
			<th>관심분야</th>
		</tr>
		<c:forEach items="${list }" var="mOne">
			<tr>
				<td>${mOne.userId }</td>
				<td>${mOne.userPwd }</td>
				<td>${mOne.userName }</td>
				<td>${mOne.phone }</td>
				<td>${mOne.nickname }</td>
				<td>${mOne.address }</td>
				<td>${mOne.email }</td>
				<td>${mOne.point }</td>
				<td>${mOne.enabled }</td>
				<td>${mOne.uRegdate }</td>
				<td>${mOne.interest }</td>


			</tr>
		</c:forEach>
	</table>

</body>
</html>








