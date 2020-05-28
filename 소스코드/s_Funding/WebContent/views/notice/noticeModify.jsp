<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.*" %>
<%
	Notice notice=(Notice)request.getAttribute("content");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 수정</title>
<form action="/noticeModifyCommit" method="post">
	<input type="text" size="100" name="subject" value="<%=notice.getSubject() %>"><br><br>
	<textarea rows="30" cols="100" name="content"><%=notice.getContents() %></textarea><br><br>
	<input type="hidden" name="noticeNo" value="<%=notice.getNoticeNo() %>">
	<input type="submit" value="수정">
	<input type="reset" value="취소">
</form>
</head>
<body>

</body>
</html>