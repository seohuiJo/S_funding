<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>베스트 후원자</title>
</head>
<body>
   <table class="table table-border">
   		<thead class="">
   			<tr>
   				<th scope="col" class="text-center" style="width: 10rem;">랭크</th>
				<th scope="col" style="width: 20rem;">베스트 후원 유저</th>
      		</tr>
      	<c:forEach items="${list}" var="mOne" varStatus="i">
         	<tr>
         		<td scope="row" class="text-center">${i.index+1 }</td>
        	    <td scope="row">${mOne.userId }</td>
        	</tr>
	      </c:forEach>
   </table>
</body>
</html>