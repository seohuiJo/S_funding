<%@page import="member.model.vo.Member"%>
<%@page import="notice.model.vo.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   PageData pd = (PageData) request.getAttribute("pageData");
   ArrayList<Notice> noticeList = pd.getPageList();
   Member member = (Member) session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
</head>
<body>
   <center>
      <h1>공지사항</h1>
      <table border="1">
         <tr>
            <th>글번호</th>
            <th>글제목</th>
            <th>글쓴이</th>
            <th>작성일</th>
            <c:forEach items="${pageData.pageList }" var="notice" varStatus="i">
               <tr>
                  <td><c:out value="${notice.noticeNo}" /></td>
                  <td><a href="/noticeSelect?noticeNo=${notice.noticeNo}">${notice.subject }</a></td>
                  <td>${notice.userId}</td>
                  <td>${notice.regdate}</td>
               </tr>
            </c:forEach>
         </tr>
         <%-- <%
            for (Notice notice : noticeList) {
         %>
         <tr>
            <td><%=notice.getNoticeNo()%></td>
            <td><a href ="/noticeSelect?noticeNo=<%=notice.getNoticeNo()%>"><%=notice.getSubject()%></a></td>
            <td><%=notice.getUserId()%></td>
            <td><%=notice.getRegDate()%></td>
         </tr>
         <%
            }
         %> --%>
         <tr>
            <td colspan="3" align="center">
               <form action="/noticeSearch" method="get">
                  <input type="text" name="search"> <input type="submit"
                     value="검색">
               </form>
            </td>
            <c:if test="${sessionScope.member != null && sessionScope.member.userId =='admin' }">
            <td align="right">
               <!-- <form action="/views/notice/noticeWrite.html" method="post"> -->
               <form action="/noticeWriteForm" method="post">
                  <input type="submit" value="글쓰기">
               </form>
            </td>
            </c:if>
         </tr>
         <tr>
            <td colspan="4" align="center">${pageData.pageNavi }</td>
         </tr>

      </table>
   </center>

</body>
</html>


