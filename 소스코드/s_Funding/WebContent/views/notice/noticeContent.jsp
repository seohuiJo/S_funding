<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.*, java.util.*"%>
<%
   Notice notice = (Notice)request.getAttribute("content");
   ArrayList<NoticeComment> comments = notice.getComments();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.5.1.min.js" 
   integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세 내용</title>
</head>
<body>
   <h2>제목 : ${content.subject }</h2>
   <h6>글번호 : ${content.noticeNo }
      / 글쓴이 : ${content.userId }
      / 작성일 : ${content.regdate }
   </h6>
   <h3>${content.contents } %></h3>
   
   <a href="/noticeModify?noticeNo=${content.noticeNo }">수정</a>
   <a href="/notice">목록</a>
   <a href="/noticeDelete?noticeNo=${content.noticeNo }" onclick="return question();">삭제</a>
   
   <form action="/noticeCommentWrite" method="post">
   comment : <input type="text" name="comment" placeholder="댓글을 작성해보세요">
   <input type="hidden" name="noticeNo" value="${content.noticeNo }"/>
   <input type="submit" value="작성">
   </form>
   
   <!-- 댓글부분 -->
   <table>
      <tr><th>댓글</th><th>아이디</th><th>날짜</th></tr>
      <c:forEach items="${content.comments }" var="comment">
         <tr>
            <td>${comment.content }</td>
            <td>${comment.userId }</td>
            <td>${comment.regDate }</td>
            <td>
               <a href="#" onclick="showModifyComment(this, '${comment.content }', '${comment.userId }', '${comment.regDate }');">수정</a>
               &nbsp;
               <a href="/noticeCommentDelete?commentNo=${comment.commentNo }&noticeNo=${content.noticeNo}" o>삭제</a>
            </td>
         </tr>
         <tr style="display:none">
            <td>
               <input type="text" size="40" id="modifyMent" value="${comment.content }"/>
            </td>
            <td>
               <a href="javascript:void(0)" onclick="modifyComment(this, '${comment.commentNo}');">수정완료</a>
               <a href="javascript:void(0)" onclick="modifyCancel(this);">취소</a>
            </td>
         </tr>
      </c:forEach>
      
      <%-- <% for (NoticeComment comment : comments) { %>
         <tr>
            <td>${content.contents }</td>
            <td>${content.userId }</td>
            <td>${content.regDate }</td>
            <td>
               <a href="#" onclick="showModifyComment(this, '${content.contents }', '${content.userId }', '${content.regDate }');">수정</a>
               &nbsp;
               <a href="/deleteNoticeComment?commentNo=<%= comment.getCommentNo() %>&noticeNo=<%= comment.getNoticeNo()%>" onclick="">삭제</a>
            </td>
         </tr>
         <tr style="display:none">
            <td>
               <input type="text" size="40" id="modifyMent" value="<%= comment.getContent() %>"/>
            </td>
            <td>
               <a href="javascript:void(0)" onclick="modifyComment(this, '<%= comment.getCommentNo() %>');">수정완료</a>
               <a href="javascript:void(0)" onclick="modifyCancel(this);">취소</a>
            </td>
         </tr>
      <% } %> --%>
   </table>
   <form action="/modifyComment" id="modifyForm" method="post">
      <input type="hidden" id="modComment" name="modComment">
      <input type="hidden" id="modNoticeNo" name="modNoticeNo" value="${content.noticeNo }">
      <input type="hidden" id="modCommentNo" name="modCommentNo">
   </form>
   
   <!-- 쿠폰발급때 사용하기 -->
   <script>
      function question() {
    	 var noticeNo='${content.noticeNo}';
         var check = window.confirm("정말로 삭제 하시겠습니까?");
         if(check) {
            return true;
         } else {
            return false;
         }
      }
      function showModifyComment(obj, comment, userId, regdate) {
         console.log(obj);
         $(obj).parents("tr").next().show();
         $(obj).parents("tr").hide();
      }
      
      function modifyCancel(obj) {
         $(obj).parents("tr").prev().show()
         $(obj).parents("tr").hide();
      }
      
      function modifyComment(obj, commentNo) {
         var comment = $(obj).parent().prev().find("input").val();
         
         $("#modCommentNo").val(commentNo);
         /* $("#modComment").val($("#modifyMent").val()); */
         $("#modComment").val(comment);
         $("#modNoticeNo").val($("#modNoticeNo").val());
         $("#modifyForm").submit();
      }
   </script>
</body>
</html>
















