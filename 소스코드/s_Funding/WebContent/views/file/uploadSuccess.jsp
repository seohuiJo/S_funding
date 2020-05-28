<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="file.model.vo.*"%>
<%
	FileData data=(FileData)request.getAttribute("fileData");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>업로드 성공</title>
</head>
<body>
	<center>
		<h1>업로드 정보</h1>
		<h3><%= "파일이름 : " +data.getFileName()%></h3><br>
		<h3><%= "파일경로 : " +data.getFilePath() %></h3><br>
		<h3><%="파일 사이즈 : "+data.getFileSize() +"Byte"%></h3><br>
		<h3><%="업로드 아이디 : "+data.getFileUser() %></h3><br>
		<h3><%="업로드 시간 : "+data.getUploadTime() %></h3><br>
		<a href="/index.jsp">메인페이지로 이동</a>
	</center>

</body>
</html>