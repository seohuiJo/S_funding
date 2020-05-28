<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>
</head>
<body>
<form action="/mUpdate" method="post">
아이디 : <input type = "text" name = "userId" id="userId" value= "${member.userId }"> <br>
패스워드 : <input type = "password" name = "userPwd" id="userPwd" value= "${member.userPwd }"> <br>
패스워드 확인 : <input type = "password" name = "userPwdRe" id="userPwdRe" value= "${member.userPwd }"> <br>
이름 : <input type = "text" name = "userName" id="userName" value= "${member.userName }%>"> <br>
나이 : <input type = "text" name = "age" id="age" value= "${member.age }"> <br>
이메일 : <input type = "email" name = "email" id="email" value= "${member.email }"> <br>
휴대폰 : <input type = "text" name = "phone" id="phone" value= "${member.phone }"> <br>
주소 : <input type = "text" name = "address" id="address" value= "${member.address}"> <br>
성별 : <c:if test="${member.gender eq 'M' }">
		<input type ="radio" name = "gender" value ="M" checked>남자
		<input type ="radio" name = "gender" value ="F">여자
		</c:if>
		<c:if test="${member.gender ne 'M' }">
			<input type="radio" name="gender" value="M">남자
			<input type="radio" name="gender" value="F" checked>여자
		</c:if>
<%-- <% if(member.getGender().equals("M")) { %>
<input type ="radio" name = "gender" value ="M" checked>남자
<input type ="radio" name = "gender" value ="F" checked>여자
<% }else { %>
<input type ="radio" name = "gender" value ="M" checked>남자
<input type ="radio" name = "gender" value ="F" checked>여자
<% } %> --%>
<br>
취미 : <input type = "text" name = "hobby" value= "${member.hobby }"> <br>
<input type="submit" value="정보수정">
<input type="reset" value="취소하기">
</form>
	
	
	<script>
		function question() {
			var result = window.confirm("정말로 회원 탈퇴를 할까욤?");
			if(result) {
				alert("잘가슈!! ^_^");
				return true;
				
			}else {
				return false;
			}
		}
	</script>
	
	<a href="/index.jsp">메인페이지로 이동하기</a>
	<a href="/mdelete" id="mdel" onclick="return question();">회원탈퇴 하기</a>

</body>
</html>